package com.saleskit.cbbank.features.appointment;

public class SavedAppointModel {
    private String id;
    private String dateTime;
    private String userName;

    public SavedAppointModel(String id, String dateTime, String userName) {
        this.id = id;
        this.dateTime = dateTime;
        this.userName = userName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
