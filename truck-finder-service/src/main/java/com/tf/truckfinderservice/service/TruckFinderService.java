package com.tf.truckfinderservice.service;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class TruckFinderService {
    @GetMapping("/first")
    public String getUrlStatusMessage(@RequestParam String url) {
        return "Please return Something";
    }
}
