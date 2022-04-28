package com.tf.app.payload;

import java.util.ArrayList;
import java.util.List;

import com.tf.data.Truck;

public class TruckResponsePayload {
    private int count;
    List<Truck> items = new ArrayList<Truck>();

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

}
