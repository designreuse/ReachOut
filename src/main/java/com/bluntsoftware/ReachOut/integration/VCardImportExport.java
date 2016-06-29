package com.bluntsoftware.ReachOut.integration;

import ezvcard.Ezvcard;
import ezvcard.VCard;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by Alex Mcknight on 6/27/2016.
 */
public class VCardImportExport {


    public static void main(String[] args) {
        File file = new File("C:\\Users\\Alex Mcknight\\Desktop\\contacts.vcf");
        try {
            List<VCard> vcards = Ezvcard.parse(file).all();
            for(VCard card:vcards){
                  System.out.println(card.getEmails());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
