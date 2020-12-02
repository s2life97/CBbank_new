package com.saleskit.cbbank.features.account;

import java.util.List;

public class CustomerID {


    /**
     * Ids : [0]
     * OldUserId : string
     * NewUserId : string
     */

    private String OldUserId;
    private String NewUserId;
    private List<String> Ids;

    public CustomerID(String oldUserId, String newUserId, List<String> ids) {
        OldUserId = oldUserId;
        NewUserId = newUserId;
        Ids = ids;
    }

    public String getOldUserId() {
        return OldUserId;
    }

    public void setOldUserId(String OldUserId) {
        this.OldUserId = OldUserId;
    }

    public String getNewUserId() {
        return NewUserId;
    }

    public void setNewUserId(String NewUserId) {
        this.NewUserId = NewUserId;
    }

    public List<String> getIds() {
        return Ids;
    }

    public void setIds(List<String> Ids) {
        this.Ids = Ids;
    }
}
