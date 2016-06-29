package com.bluntsoftware.ReachOut.integration;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Alex Mcknight on 6/27/2016.
 */
public class CSVImportExport {
    /*
    First Name,
    Middle Name,
    Last Name,
    Title,
    Suffix,
    Initials,
    Web Page,
    Gender,
    Birthday,
    Anniversary,
    Location,
    Language,
    Internet Free Busy,
    Notes,
    E-mail Address,
    E-mail 2 Address,
    E-mail 3 Address,
    Primary Phone,
    Home Phone,
    Home Phone 2,
    Mobile Phone,
    Pager,
    Home Fax,
    Home Address,
    Home Street,
    Home Street 2,
    Home Street 3,
    Home Address PO Box,
    Home City,
    Home State,
    Home Postal Code,
    Home Country,
    Spouse,
    Children,
    Manager's Name,
    Assistant's Name,
    Referred By,
    Company Main Phone,
    Business Phone,
    Business Phone 2,
    Business Fax,
    Assistant's Phone,
    Company,
    Job Title,
    Department,
    Office Location,
    Organizational ID Number,
    Profession,
    Account,
    Business Address,
    Business Street,
    Business Street 2,
    Business Street 3,
    Business Address PO Box,
    Business City,
    Business State,
    Business Postal Code,
    Business Country,
    Other Phone,
    Other Fax,
    Other Address,
    Other Street,
    Other Street 2,
    Other Street 3,
    Other Address PO Box,
    Other City,
    Other State,
    Other Postal Code,
    Other Country,
    Callback,
    Car Phone,
    ISDN,
    Radio Phone,
    TTY/TDD Phone,
    Telex,
    User 1,
    User 2,
    User 3,
    User 4,
    Keywords,
    Mileage,
    Hobby,
    Billing Information,
    Directory Server,
    Sensitivity,
    Priority,
    Private,
    Categories
    */
    public static void main(String[] args) {
        File csvFile = new File("C:\\Users\\Alex Mcknight\\Desktop\\contacts.csv");
        CsvMapper mapper = new CsvMapper();
        CsvSchema schema = CsvSchema.emptySchema().withHeader(); // use first row as header; otherwise defaults are fine
        MappingIterator<Map<String,String>> it = null;
        try {
            it = mapper.reader(Map.class)
                    .with(schema)
                    .readValues(csvFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map<String,String> keys = new HashMap<String, String>();
        while (it.hasNext()) {
            Map<String,String> rowAsMap = it.next();

            for(String key:rowAsMap.keySet()){
                String data =  rowAsMap.get(key);
                if(data != null && !data.equalsIgnoreCase("")){
                    System.out.println(key + " --> " + data);
                    keys.put(key,key);
                }
            }
            System.out.println("----------------------------------");

        }
        System.out.println("---------- Unique Keys --------------");
        for(String key :keys.keySet()){

            System.out.println(key);
        }

    }

}
