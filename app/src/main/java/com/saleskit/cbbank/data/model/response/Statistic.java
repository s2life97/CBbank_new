package com.saleskit.cbbank.data.model.response;

import com.google.gson.annotations.SerializedName;

public class Statistic {
    public NamedResource stat;

    @SerializedName("base_stat")
    public int baseStat;
}
