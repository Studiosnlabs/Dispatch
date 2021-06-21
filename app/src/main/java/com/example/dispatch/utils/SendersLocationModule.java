package com.example.dispatch.utils;

import com.parse.ParseGeoPoint;

import java.io.Serializable;

public class SendersLocationModule implements Serializable {

    ParseGeoPoint SendersLocation;


    public ParseGeoPoint getSendersLocation() {
        return SendersLocation;
    }

    public void setSendersLocation(ParseGeoPoint sendersLocation) {
        SendersLocation = sendersLocation;
    }
}
