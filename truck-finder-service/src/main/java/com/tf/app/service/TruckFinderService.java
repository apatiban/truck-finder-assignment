package com.tf.app.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.tf.app.util.Utils;
import com.tf.data.Distance;
import com.tf.data.DistanceCalculator;
import com.tf.data.DistanceHeap;
import com.tf.data.Truck;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

@Service
public class TruckFinderService {

    public static final String COMMA_DELIMITER = ",";
    private static final Logger logger = LoggerFactory.getLogger(TruckFinderService.class);

    private static final int NO_OF_TRUCK_RESULTS = 5;

    @Value("${food.truck.data}")
    private String dataLocation;

    @Autowired
    ResourceLoader resourceLoader;

    private Double inputLatitude;
    private Double inputLongtitude;

    private Map<String, String[]> dataMap = new HashMap<String, String[]>();

    private Resource getResource() {
        return resourceLoader.getResource(dataLocation);
        /*
         * try {
         * InputStream input = resource.getInputStream();
         * } catch (IOException e1) {
         * // TODO Auto-generated catch block
         * e1.printStackTrace();
         * }
         */

    }

    public List<Distance> getTruckResults() {
        DistanceHeap heap = buildHeap(getInputStream());
        return getKValuesFromSortedHeap(heap, NO_OF_TRUCK_RESULTS);
    }

    public List<Truck> getTrucks() {
        DistanceHeap heap = buildHeap(getInputStream());
        List<Distance> truckDistanceList = getKValuesFromSortedHeap(heap, NO_OF_TRUCK_RESULTS);
        return truckDistanceList.stream().map(e -> parseTruckData(dataMap.get(e.getLocationId()), e.getDistance()))
                .collect(Collectors.toList());
    }

    /**
     * Could use some parser here. But given the Time constraint just taking the
     * required data.
     * 
     * @param truckDetails
     * @return
     */
    private Truck parseTruckData(String[] truckDetails, Double distance) {
        Truck truck = new Truck();
        truck.setLocationId(truckDetails[0]);
        truck.setLocationDesc(truckDetails[4]);
        truck.setAddress(truckDetails[5]);
        truck.setFoodItem(truckDetails[11]);
        truck.setSchedule(truckDetails[11]);
        truck.setDayHours(truckDetails[17]);
        truck.setLocation(truckDetails[23]);
        truck.setZipCode(truckDetails[27]);
        truck.setDistance(distance);
        return truck;
    }

    private String getFileName() {
        File f = null;
        String path = null;
        try {
            f = getResource().getFile();
            path = f.getAbsolutePath();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return path;
    }

    private InputStream getInputStream() {
        try {
            return getResource().getInputStream();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    private DistanceHeap buildHeap(InputStream in) {
        boolean isFirst = true;
        DistanceHeap heap = null;
        int noOflines = Utils.getNoOfLines(getFileName()).intValue();
        heap = new DistanceHeap(noOflines);
        heap.insert(root());
        try (BufferedReader br = new BufferedReader(new InputStreamReader(getInputStream()))) {
            String line;
            Distance locationDistance = null;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(COMMA_DELIMITER);
                dataMap.put(values[0], values);
                // records.add(Arrays.asList(values));
                if (!isFirst) {
                    Double varLat = Utils.getDouble(values[14]);
                    Double varLong = Utils.getDouble(values[15]);
                    if (varLat == 0.0 || varLong == 0.0)
                        continue;
                    locationDistance = new Distance(values[0], distanceFromCurrenttoTruck(values[14], values[15]));
                    heap.insert(locationDistance);
                    // System.out.println(locationDistance);
                }
                isFirst = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return heap;
    }

    private List<Distance> getKValuesFromSortedHeap(DistanceHeap heap, int noofTrucks) {
        List<Distance> knearestTrucks = new ArrayList<Distance>();
        int count = 0;
        while (!heap.isEmpty()) {

            if (count == 0)
                heap.remove();
            else if (count == noofTrucks + 1)
                break;
            else
                knearestTrucks.add(heap.remove());

            // System.out.println("Remove " + heap.remove());
            count++;

        }
        return knearestTrucks;
    }

    private Distance root() {
        return new Distance("root", 0.0);
    }

    public Double distanceFromCurrenttoTruck(String truckLatitude, String truckLongtitude) {
        return DistanceCalculator.distanceInMiles(getInputLatitude(), getInputLongtitude(),
                Utils.getDouble(truckLatitude),
                Utils.getDouble(truckLongtitude));
    }

    public Double getInputLatitude() {
        return inputLatitude;
    }

    public void setInputLatitude(Double inputLatitude) {
        this.inputLatitude = inputLatitude;
    }

    public Double getInputLongtitude() {
        return inputLongtitude;
    }

    public void setInputLongtitude(Double inputLongtitude) {
        this.inputLongtitude = inputLongtitude;
    }

    public Map<String, String[]> getDatabMap() {
        return dataMap;
    }

}
