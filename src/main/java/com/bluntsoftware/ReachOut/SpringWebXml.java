package com.bluntsoftware.ReachOut;

//import com.bluntsoftware.ReachOut.config.WebConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
/**
* Created by Alexander on 7/21/2014.
*/
public class SpringWebXml extends SpringBootServletInitializer {
    private final Logger log = LoggerFactory.getLogger(SpringWebXml.class);

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
                    return application
                    .showBanner(false)
                    .sources(SpringApp.class);
    }

}