package com.tf.app.service;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.ft.util.Utils;
import com.tf.data.Distance;
import com.tf.data.DistanceCalculator;
import com.tf.data.DistanceHeap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class TruckFinderService {

    public static final String COMMA_DELIMITER = ",";

    @Value("${truck.location.data}")
    private String templateLocation;

    // 37.805885350100986, -122.41594524663745
    // latitude - 14 Index
    // Longitude - 15 Index

    @GetMapping("/first")
    public String getUrlStatusMessage(@RequestParam String url) {

        var cLatitude = 37.7620192003564;
        var cLongtitude = -122.427306422513;

        System.out.println("this is test*************************************");
        List<List<String>> records = new ArrayList<>();

        // BufferedReader br = new BufferedReader(new FileReader(templateLocation));
        System.out.println("templateLocation" + templateLocation);
        double distance = 0.0;
        boolean isFirst = true;
        int noOflines = Utils.getNoOfLines(templateLocation).intValue();
        Distance[] ftDistanceItems = new Distance[noOflines];
        ftDistanceItems[0] = new Distance("root", 0.0);

        DistanceHeap heap = new DistanceHeap(noOflines);
        heap.insert(ftDistanceItems[0]);

        try (BufferedReader br = new BufferedReader(new FileReader(templateLocation))) {
            String line;
            Distance locationDistance = null;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(COMMA_DELIMITER);
                records.add(Arrays.asList(values));
                if (!isFirst) {
                    Double varLat = Utils.getDouble(values[14]);
                    Double varLong = Utils.getDouble(values[15]);
                    if (varLat == 0.0 || varLong == 0.0)
                        continue;
                    distance = DistanceCalculator.distanceInMiles(cLatitude, cLongtitude,
                            Utils.getDouble(values[14]),
                            Utils.getDouble(values[15]));
                    locationDistance = new Distance(values[0], distance);
                    heap.insert(locationDistance);
                    System.out.println("Distance # " + values[5] + "   " + distance);

                }
                isFirst = false;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Please return67";
    }
}
