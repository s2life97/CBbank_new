package com.saleskit.cbbank.features.appointment;

public  class CategoryEvent {
    public String value;
    public int id;
    public String category;
    public String categoryId;
    public double ratio;

    public CategoryEvent(String value, int id, String category) {
        this.value = value;
        this.id = id;
        this.category= category;
    }

    public CategoryEvent(String value, String categoryId, String category) {
        this.value = value;
        this.categoryId = categoryId;
        this.category = category;

    }

    public CategoryEvent(String value, String category) {
        this.value = value;
        this.category= category;
    }

    public CategoryEvent(String value, String categoryId,  String category, double ratio) {
        this.value = value;
        this.categoryId = categoryId;
        this.category = category;
        this.ratio = ratio;
    }
}
