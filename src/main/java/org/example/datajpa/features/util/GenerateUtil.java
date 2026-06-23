package org.example.datajpa.features.util;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.Random;


public class GenerateUtil {

    //Generate product code
    public static String generateProductCode(){
        Random random = new Random();
        int rendomDigit = random.nextInt(9000)+1000;
        return "ITE-3RD-"+rendomDigit;
    }

    //Generate slug
    public static String generateSlug(String name) {
        return name.trim()
                .toLowerCase()
                .replaceAll("[^a-z0-9\\s]", "")
                .replaceAll("\\s+", "-");
    }
}
