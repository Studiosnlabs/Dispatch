package com.example.dispatch.utils;

import com.parse.ParseGeoPoint;

import java.io.Serializable;

import javax.xml.namespace.QName;

public class OrderModule implements Serializable {

    String PackageDescription;
    String RecipientName;
    String RecipientPhone;
    String VehicleType;
    ParseGeoPoint RecipientLocation;
    ParseGeoPoint SendersLocation;




    public String getPackageDescription() {
        return PackageDescription;
    }

    public void setPackageDescription(String packageDescription) {
        PackageDescription = packageDescription;
    }

    public String getRecipientName() {
        return RecipientName;
    }

    public void setRecipientName(String recipientName) {
        RecipientName = recipientName;
    }

    public String getRecipientPhone() {
        return RecipientPhone;
    }

    public void setRecipientPhone(String recipientPhone) {
        RecipientPhone = recipientPhone;
    }

    public String getVehicleType() {
        return VehicleType;
    }

    public void setVehicleType(String vehicleType) {
        VehicleType = vehicleType;
    }

    public ParseGeoPoint getRecipientLocation() {
        return RecipientLocation;
    }

    public void setRecipientLocation(ParseGeoPoint recipientLocation) {
        RecipientLocation = recipientLocation;
    }

    public ParseGeoPoint getSendersLocation() {
        return SendersLocation;
    }

    public void setSendersLocation(ParseGeoPoint sendersLocation) {
        SendersLocation = sendersLocation;
    }
}
