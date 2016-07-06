package com.bluntsoftware.ReachOut.security;

import com.genx.framework.jpa.repository.support.HqlBuilder;
import com.bluntsoftware.ReachOut.modules.user_manager.domain.ApplicationAuthority;
import com.bluntsoftware.ReachOut.modules.user_manager.domain.ApplicationPersistentToken;
import com.bluntsoftware.ReachOut.modules.user_manager.domain.ApplicationUser;
import com.bluntsoftware.ReachOut.modules.user_manager.domain.ApplicationUserAuthority;
import com.bluntsoftware.ReachOut.modules.user_manager.repository.ApplicationAuthorityRepository;
import com.bluntsoftware.ReachOut.modules.user_manager.repository.ApplicationPersistentTokenRepository;
import com.bluntsoftware.ReachOut.modules.user_manager.repository.ApplicationUserAuthorityRepository;
import com.bluntsoftware.ReachOut.modules.user_manager.repository.ApplicationUserRepository;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.context.IWebContext;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.context.SpringWebContext;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.*;

/**
 * Created by Alexander on 8/8/2014.
 */
@RestController
@RequestMapping("/user_manager") //
public class AccountService {

    private static final int DEF_COUNT = 20;

    private final Logger log = LoggerFactory.getLogger(AccountService.class);

    @Autowired
    private ServletContext servletContext;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private SpringTemplateEngine templateEngine;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ApplicationUserRepository userRepository;

    @Autowired
    private ApplicationUserAuthorityRepository userAuthorityRepository;

    @Autowired
    private ApplicationPersistentTokenRepository persistentTokenRepository;

    @Autowired
    private ApplicationAuthorityRepository authorityRepository;

    @Autowired
    private MailService mailService;

    @PostConstruct
    @Transactional(readOnly = false)
    private void insertAdmin(){
        ApplicationUser userFromDatabase = getByLogin("admin");
        if (userFromDatabase == null) {
            userFromDatabase = new ApplicationUser();
            userFromDatabase.setEmail("admin@somewhere.com");
            userFromDatabase.setFirstName("Administrator");
            userFromDatabase.setLastName("");
            userFromDatabase.setLogin("admin");
            userFromDatabase.setActivated(true);
            userFromDatabase.setLangKey("en");
            userFromDatabase.setActivationKey("test");
            userFromDatabase.setPassword(passwordEncoder.encode("admin"));

            ApplicationAuthority authority = findAuthorityByRole(AuthoritiesConstants.ADMIN);
            if(authority == null){
                authority = new ApplicationAuthority();
                authority.setAuthority(AuthoritiesConstants.ADMIN);
            }

            ApplicationUserAuthority userAuthority = new ApplicationUserAuthority();
            userAuthority.setAuthority(authority);
            userAuthority.setAppuser(userFromDatabase);
            userAuthorityRepository.saveAndUpdate(userAuthority);
        }
    }
    public static String generatePassword() {
        return RandomStringUtils.randomAlphanumeric(DEF_COUNT);
    }

    public static String generateActivationKey() {
        return RandomStringUtils.randomNumeric(DEF_COUNT);
    }
    public ApplicationUser activateRegistration(String key) {
        log.debug("Activating user for activation key {}", key);
        ApplicationUser user =  getUserByActivationKey(key);

        // activate given user for the registration key.
        if (user != null) {
            user.setActivated(true);
            user.setActivationKey(null);
            userRepository.save(user);
            log.debug("Activated user: {}", user);
        }
        return user;
    }

    private ApplicationUser getUserByActivationKey(String key) {
        HqlBuilder builder = new HqlBuilder(ApplicationUser.class).eq("activationKey",key);
        return userRepository.findOne(builder);
    }
    List<ApplicationUser> findNotActivatedUsersByCreationDateBefore(DateTime dateTime){
        HqlBuilder builder = new HqlBuilder(ApplicationUser.class).eq("activated", false);//.gt("createdDate",dateTime)
        return userRepository.findAll(builder);
    }


    public ApplicationUser getByLogin(final String login){
        HqlBuilder builder = new HqlBuilder(ApplicationUser.class).ieq("login",login);
        return userRepository.findOne(builder);
    }

    public List<String> getUserRoles(ApplicationUser user){
        List<String> authorities = new ArrayList<String>();
        HqlBuilder builder = new HqlBuilder(ApplicationUserAuthority.class).eq("appuser",user);
        List<ApplicationUserAuthority> userAuthorities = userAuthorityRepository.findAll(builder);
        for(ApplicationUserAuthority authority:userAuthorities){
            authorities.add(authority.getAuthority().getAuthority());
        }
        return authorities;
    }

    @Transactional(readOnly = true)
    public UserDTO getUserWithAuthorities() {
        ApplicationUser user = getByLogin(SecurityUtils.getCurrentLogin());
        List<String> userRoles = getUserRoles(user);
        return new  UserDTO(user.getLogin(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                userRoles);
    }

    ApplicationAuthority findAuthorityByRole(String role){
        HqlBuilder builder = new HqlBuilder(ApplicationAuthority.class).eq("authority",role);
        return authorityRepository.findOne(builder);
    }
    @Transactional(readOnly = false)
    public ApplicationUser createUserInformation(String login, String password, String firstName, String lastName, String email,
                                                 String langKey) {
        ApplicationUser newUser = new ApplicationUser();

        ApplicationAuthority authority = findAuthorityByRole(AuthoritiesConstants.USER);
        if(authority == null){
            authority = new ApplicationAuthority();
            authority.setAuthority(AuthoritiesConstants.USER);
        }

        String encryptedPassword = passwordEncoder.encode(password);
        newUser.setLogin(login);
        // new user gets initially a generated password
        newUser.setPassword(encryptedPassword);
        newUser.setFirstName(firstName);
        newUser.setLastName(lastName);
        newUser.setEmail(email);
        newUser.setLangKey(langKey);
        // new user is not active
        newUser.setActivated(false);
        // new user gets registration key
        newUser.setActivationKey( generateActivationKey());



        ApplicationUserAuthority userAuthority = new ApplicationUserAuthority();
        userAuthority.setAuthority(authority);
        userAuthority.setAppuser(newUser);
        userAuthorityRepository.saveAndUpdate(userAuthority);

        log.debug("Created Information for User: {}", newUser);
        return newUser;
    }

    @RequestMapping(value = "/register",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> registerAccount(@RequestBody UserDTO userDTO, HttpServletRequest request, HttpServletResponse response) {
        ApplicationUser user = getByLogin(userDTO.getLogin());
        if (user != null) {
            return new ResponseEntity(HttpStatus.NOT_MODIFIED);
        } else {
            user = createUserInformation(userDTO.getLogin(), userDTO.getPassword(), userDTO.getFirstName(),
                    userDTO.getLastName(), userDTO.getEmail().toLowerCase(), userDTO.getLangKey());
            final Locale locale = Locale.forLanguageTag(user.getLangKey());
            String content = createHtmlContentFromTemplate(user, locale, request, response);
            mailService.sendActivationEmail(user.getEmail(), content, locale);
            return new ResponseEntity(HttpStatus.CREATED);
        }
    }


    @RequestMapping(value = "/activate",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> activateAccount(@RequestParam(value = "key") String key) {
        ApplicationUser user = activateRegistration(key);
        if (user == null) {
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<String>(user.getLogin(), HttpStatus.OK);
    }

    @RequestMapping(value = "/authenticate",
            method = RequestMethod.GET,
            produces = "application/json")

    public String isAuthenticated(HttpServletRequest request) {
        log.debug("REST request to check if the current user is authenticated");
        return request.getRemoteUser();
    }

    @RequestMapping(value = "/account",
            method = RequestMethod.GET,
            produces = "application/json")

    public UserDTO getAccount(HttpServletResponse response) {
        UserDTO user =  getUserWithAuthorities();
        if (user == null) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return null;
        }

        return new UserDTO(user.getLogin(), user.getFirstName(), user.getLastName(),
                user.getEmail(), user.getRoles());
    }


    @RequestMapping(value = "/account",
            method = RequestMethod.POST,
            produces = "application/json")
    @Transactional(readOnly = false)
    public void saveAccount(@RequestBody UserDTO userDTO) throws IOException {
        ApplicationUser currentUser = getByLogin(SecurityUtils.getCurrentLogin());
        currentUser.setFirstName(userDTO.getFirstName());
        currentUser.setLastName(userDTO.getLastName());
        currentUser.setEmail(userDTO.getEmail());
        userRepository.saveAndUpdate(currentUser);
        log.debug("Changed Information for User: {}", currentUser);

    }
    @RequestMapping(value = "/account/set_password",
    method =  {RequestMethod.GET, RequestMethod.POST},
    produces = "application/json")
    @Transactional(readOnly = false)
    public void setPasswordByLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String login =  request.getParameter("login");
        String password =  request.getParameter("password");
        if (password == null || password.equals("")) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Password should not be empty");
        } else if(login == null || login.equalsIgnoreCase("")){
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Login should not be empty");
        }else {
            ApplicationUser currentUser = getByLogin(SecurityUtils.getCurrentLogin());
            if(getUserRoles(currentUser).contains(AuthoritiesConstants.ADMIN)){
                ApplicationUser user = getByLogin(login);
                if(user != null){
                    String encryptedPassword = passwordEncoder.encode(password);
                    user.setPassword(encryptedPassword);
                    userRepository.saveAndUpdate(user);
                    log.debug("Changed password for User: {}", user);
                }else{
                    response.sendError(HttpServletResponse.SC_FORBIDDEN, login + " not found");
                }
            }else{
                response.sendError(HttpServletResponse.SC_FORBIDDEN, "You are not an Admin");
            }
        }
    }

    @RequestMapping(value = "/account/change_password",
            method = RequestMethod.POST,
            produces = "application/json")
    @Transactional(readOnly = false)
    public void changePassword(@RequestBody Map data, HttpServletResponse response) throws IOException {
        String password = (String)data.get("password");
        if (password == null || password.equals("")) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Password should not be empty");
        } else {
            ApplicationUser currentUser = getByLogin(SecurityUtils.getCurrentLogin());
            String encryptedPassword = passwordEncoder.encode(password);
            currentUser.setPassword(encryptedPassword);
            userRepository.saveAndUpdate(currentUser);
            log.debug("Changed password for User: {}", currentUser);
        }
    }

    public List<ApplicationPersistentToken> findByUser(ApplicationUser user){
        HqlBuilder builder = new HqlBuilder(ApplicationPersistentToken.class).eq("appuser",user);
        return persistentTokenRepository.findAll(builder);
    }


    @RequestMapping(value = "/sessions",
            method = RequestMethod.GET,
            produces = "application/json")

    public List<ApplicationPersistentToken> getCurrentSessions(HttpServletResponse response) {

        ApplicationUser user =  getByLogin(SecurityUtils.getCurrentLogin());
        if (user == null) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
        return  findByUser(user);
    }


    @RequestMapping(value = "/sessions/{series}",
            method = RequestMethod.DELETE)

    public void invalidateSession(@PathVariable String series, HttpServletRequest request) throws UnsupportedEncodingException {
        String decodedSeries = URLDecoder.decode(series, "UTF-8");

        ApplicationUser user =  getByLogin(SecurityUtils.getCurrentLogin());
        List<ApplicationPersistentToken> persistentTokens = findByUser(user);
        for (ApplicationPersistentToken persistentToken : persistentTokens) {
            if (StringUtils.equals(persistentToken.getSeries(), decodedSeries)) {
                persistentTokenRepository.delete(persistentToken);
            }
        }
    }

    private String createHtmlContentFromTemplate(final ApplicationUser user, final Locale locale, final HttpServletRequest request, final HttpServletResponse response) {
        Map<String, Object> variables = new HashMap<String,Object>();
        String contextPath =  request.getContextPath();
        variables.put("user", user);
        variables.put("baseUrl", request.getScheme() + "://" +   // "http" + "://
                request.getServerName() +       // "myhost"
                ":" + request.getServerPort()
                + contextPath);
        IWebContext context = new SpringWebContext(request, response, servletContext,locale, variables, applicationContext);
        return templateEngine.process("activationEmail", context);
    }
}
