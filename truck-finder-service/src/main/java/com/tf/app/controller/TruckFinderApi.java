package com.tf.app.controller;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.tf.app.service.TruckFinderService;
import com.tf.app.util.Utils;
import com.tf.data.Distance;
import com.tf.data.DistanceCalculator;
import com.tf.data.DistanceHeap;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

@RestController
public class TruckFinderApi {

    public static final String COMMA_DELIMITER = ",";
    private static final Logger logger = LoggerFactory.getLogger(TruckFinderApi.class);

    @Value("${truck.location.data}")
    private String templateLocation;

    @Autowired
    TruckFinderService truckFinderService;

    @Autowired
    ResourceLoader resourceLoader;
    // 37.805885350100986, -122.41594524663745
    // latitude - 14 Index
    // Longitude - 15 Index

    @GetMapping("/trucks")
    public ResponseEntity<List<Distance>> getNearestTrucksbyPoints(@RequestParam Double latitude,
            @RequestParam Double longitude) {
        // List<Distance> list = truckFinderService.getNeartestTrucks(latitude,
        // longitude);
        truckFinderService.setInputLatitude(latitude);
        truckFinderService.setInputLongtitude(longitude);
        List<Distance> list = truckFinderService.getTruckResults();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/first")
    public ResponseEntity<List<Distance>> getUrlStatusMessage(@RequestParam String url) {
        // parseCSV();
        var cLatitude = 37.78520535858918;
        var cLongtitude = -122.43015974411763;

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
                    // System.out.println(locationDistance);
                }
                isFirst = false;
            }
            while (!heap.isEmpty()) {
                System.out.println("Remove " + heap.remove());
            }
            System.out.println("Done");

        } catch (Exception e) {
            e.printStackTrace();
        }
        List<Distance> list = new ArrayList<Distance>();
        list.add(new Distance("locationId", 0.0));
        list.add(new Distance("locationId4444", 0.0));
        return ResponseEntity.ok(list);
    }

    public void parseCSV() {
        URL url = null;
        try {
            url = new URL(
                    "https://raw.githubusercontent.com/timfpark/take-home-engineering-challenge/main/Mobile_Food_Facility_Permit.csv");
        } catch (MalformedURLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        CSVFormat csvFormat = CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase();

        try (CSVParser csvParser = CSVParser.parse(url, StandardCharsets.UTF_8, csvFormat)) {
            for (CSVRecord csvRecord : csvParser) {
                String locationid = csvRecord.get("locationid");

                logger.info("location id ={} ", locationid);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
