package com.saleskit.cbbank.features.appointment;

class OptionModel {
    String name;
    String id;
    double ratio;

    public OptionModel(String name, String id, double ratio) {
        this.name = name;
        this.id = id;
        this.ratio = ratio;
    }

    public OptionModel(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public double getRatio() {
        return ratio;
    }

    public void setRatio(double ratio) {
        this.ratio = ratio;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public OptionModel(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    int value;
}
