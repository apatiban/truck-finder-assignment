package com.tf.entity;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class TruckResponsePayload {
    private int count;
    List<Truck> items;

    private String error;
    private String errorMessage;

    public int getCount() {
        return count;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Truck> getItems() {
        return items;
    }

    public void setItems(List<Truck> items) {
        this.items = items;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        // String itemString = Optional.ofNullable(items.stream().map(e ->
        // e.toString()).collect(Collectors.joining("\n"))).orelse;
        String itemString = Optional
                .ofNullable(items.stream().map(e -> e.toString()).collect(Collectors.joining("\n\n")))
                .orElse("");
        return "Here are the top " + count + " nearest trucks found for the given location \n\n" + itemString;
    }

    public String getErrorDetails() {
        return "Error : code = " + error + " message = " + errorMessage;
    }

}
