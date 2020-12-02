package com.saleskit.cbbank.features.database;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity(nameInDb = "optionItems")
public class OptionItem {

    @Id(autoincrement = true)
    private Long id;

    private String group;
    private int value;
    private String display;
    private int valueCategory;
    
    public OptionItem(String group, int value, String display) {
        this.group = group;
        this.value = value;
        this.display = display;
        this.valueCategory = -1;
    }

    public OptionItem(String group, int value, String display, int valueCategory) {
        this.group = group;
        this.value = value;
        this.display = display;
        this.valueCategory = valueCategory;
    }

    @Generated(hash = 1733751024)
    public OptionItem(Long id, String group, int value, String display,
            int valueCategory) {
        this.id = id;
        this.group = group;
        this.value = value;
        this.display = display;
        this.valueCategory = valueCategory;
    }

    @Generated(hash = 1735370362)
    public OptionItem() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGroup() {
        return this.group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public int getValue() {
        return this.value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getDisplay() {
        return this.display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public int getValueCategory() {
        return this.valueCategory;
    }

    public void setValueCategory(int valueCategory) {
        this.valueCategory = valueCategory;
    }
}
