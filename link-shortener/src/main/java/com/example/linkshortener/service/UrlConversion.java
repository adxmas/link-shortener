package com.example.linkshortener.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;

@Service
public class UrlConversion {

    ArrayList<Integer> results = new ArrayList<>();
    String base62 = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public String encode(int keyFromDB) {

        while(keyFromDB >0) {
            int remanider = keyFromDB % 62;
            keyFromDB = keyFromDB / 62;
            results.add(remanider);
        }

        Collections.reverse(results);

        String inputString = "";
        for( int num :results) {
            inputString += base62.charAt(num);
        }
        System.out.println("mapped base62 number: "+inputString);
        return inputString;
    }

    public int decode(String inputString) {
        int counter = 0;
        int mapped;
        double result1 = 0;

        for (int j = 0; j < inputString.length(); j++) {
            counter = j + 1;
            mapped = base62.indexOf(inputString.charAt(j));
            int exp = (inputString.length() - counter);
            result1 = result1 + mapped * Math.pow(62, exp);
        }
        return (int) result1;
    }

}
