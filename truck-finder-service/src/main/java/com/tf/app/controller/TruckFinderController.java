package com.tf.app.controller;

import java.util.List;

import javax.validation.Valid;

import com.tf.app.exception.DataParserException;
import com.tf.app.exception.TruckRepoNotFoundException;
import com.tf.app.payload.TruckResponsePayload;
import com.tf.app.service.TruckFinderService;
import com.tf.data.Truck;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TruckFinderController implements ErrorController {

    public static final String COMMA_DELIMITER = ",";
    private static final Logger logger = LoggerFactory.getLogger(TruckFinderController.class);
    private static final String PATH = "/error";

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
    public ResponseEntity<TruckResponsePayload> getNearestTrucksbyPoints(
            @Valid @RequestParam(required = true) Double latitude,
            @RequestParam(required = true) Double longitude) {

        List<Truck> trucks = null;
        truckFinderService.setInputLatitude(latitude);
        truckFinderService.setInputLongtitude(longitude);
        try {
            trucks = truckFinderService.getTrucks();
            if (trucks.isEmpty()) {
                logger.info("Trucks not avaialble from the given location");
                return ResponseEntity
                        .ok(getErrorResponsePayload(HttpStatus.OK,
                                "Trucks not avaialble from the given location"));
            }
            return ResponseEntity.ok(getTruckResponsePayload(trucks));

        } catch (DataParserException e) {
            logger.error("Invalid input data + { 0 }", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(getErrorResponsePayload(HttpStatus.INTERNAL_SERVER_ERROR, "Invalid input data"));
        } catch (TruckRepoNotFoundException e) {
            logger.error("Truck repository not available+ { 0 }", e.getMessage());
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body(getErrorResponsePayload(HttpStatus.NO_CONTENT, "Truck repository not available"));
        } catch (Exception e) {
            logger.error("Unexpected Error occured + { 0 }", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(getErrorResponsePayload(HttpStatus.INTERNAL_SERVER_ERROR, "Unexpected Error occured "));
        }
    }

    private TruckResponsePayload getErrorResponsePayload(HttpStatus status, String errorMessage) {
        TruckResponsePayload reponsePayload = new TruckResponsePayload();
        reponsePayload.setError(status + "");
        reponsePayload.setErrorMessage(errorMessage);
        return reponsePayload;
    }

    /*
     * There are ways to do detailed error handling. But in the interest of time,
     * currently broad message is given.
     */
    @RequestMapping(value = PATH)
    public ResponseEntity<TruckResponsePayload> error() {
        TruckResponsePayload reponsePayload = new TruckResponsePayload();
        reponsePayload.setError(HttpStatus.BAD_REQUEST + "");
        reponsePayload
                .setErrorMessage("Either required input fields longitude, latitde are missing or Invalid input data");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(reponsePayload);
    }

    private TruckResponsePayload getTruckResponsePayload(List<Truck> trucks) {
        TruckResponsePayload reponsePayload = new TruckResponsePayload();
        if (!trucks.isEmpty()) {
            reponsePayload.setItems(trucks);
            reponsePayload.setCount(trucks.size());
        }
        return reponsePayload;

    }
}
