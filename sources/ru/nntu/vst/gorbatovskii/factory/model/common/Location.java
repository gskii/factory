package ru.nntu.vst.gorbatovskii.factory.model.common;

import org.springframework.beans.factory.annotation.Required;

public class Location {
    private String locationId;

    public String getLocationId() {
        return locationId;
    }

    @Required
    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    @Override
    public String toString() {
        return locationId;
    }
}
