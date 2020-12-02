package com.saleskit.cbbank.features.kpi;

class KpiPersonModel {
    private int progress;
    private String name;
    private String position;
    private boolean isHasPostion;

    public KpiPersonModel(String name) {
        this.name = name;

    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public boolean isHasPostion() {
        return isHasPostion;
    }

    public void setHasPostion(boolean hasPostion) {
        isHasPostion = hasPostion;
    }
}
