package com.tf.app.service;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class TruckFinderService {

    public static final String COMMA_DELIMITER = ",";

    @Value("${truck.location.data}")
    private String templateLocation;

    @GetMapping("/first")
    public String getUrlStatusMessage(@RequestParam String url) {

        System.out.println("this is test*************************************");
        List<List<String>> records = new ArrayList<>();

        // BufferedReader br = new BufferedReader(new FileReader(templateLocation));
        System.out.println("templateLocation" + templateLocation);
        try (BufferedReader br = new BufferedReader(new FileReader(templateLocation))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(COMMA_DELIMITER);
                records.add(Arrays.asList(values));
            }
        } catch (Exception e) {

            e.printStackTrace();
        }
        return "Please return Something";
    }
}
