package com.saleskit.cbbank.features.database;

import android.content.Context;

import com.saleskit.cbbank.Constants;
import com.saleskit.cbbank.features.account.EmployeeInfomation;
import com.saleskit.cbbank.features.appointment.SavedAppointModel;
import com.saleskit.cbbank.features.home.Product;
import com.saleskit.cbbank.util.rx.netmodel.Token;
import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;
import java.util.List;

public class HawkHelper {
    private static final String KEY_LOGIN_STATUS = "key_login_status";
    private static final String KEY_LIST_PRODUCT = "key_list_product";
    private static final String KEY_LIST_ENTERPIRSE_PRODUCT = "key_list_enterprise_product";
    private static final String KEY_LIST_PRODUCT_HD = "key_list_product_hd";
    private static final String TOKEN_MODEL = "token_model";
    private static final String EMLPOYEE_INFO = "employee_info";
    private static final String APPOINT_MODEL = "appoint_model";
    public Context mContext;
    public static HawkHelper sInstance;

    public HawkHelper(Context context) {
        this.mContext = context;
    }

    public static HawkHelper getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new HawkHelper(context.getApplicationContext());
        }
        return sInstance;
    }

    public void saveSwtichStatus(boolean isEnabled) {
        Hawk.put(Constants.KEY_STATUS, isEnabled);

    }

    public void putSwitchStatus(boolean status) {
        Hawk.put(Constants.KEY_STATUS, status);
    }

    public Boolean getSwitchStatus() {
        return Hawk.get(Constants.KEY_STATUS, true);
    }

    public void putLoginStatus(boolean status) {
        Hawk.put(KEY_LOGIN_STATUS, status);
    }

    public Boolean getLoginStatus() {
        return Hawk.get(KEY_LOGIN_STATUS, false);
    }

    public void saveAuthorToken(String authenToken) {
        Hawk.put(Constants.KEY_AUTHOR, authenToken);
    }

    public String getToken() {
        return Hawk.get(Constants.KEY_AUTHOR);
    }

    public void saveListProduct(List<Product.DataBean> datas) {
        Hawk.put(KEY_LIST_PRODUCT, datas);
    }

    public List<Product.DataBean> getListProduct() {
        return Hawk.get(KEY_LIST_PRODUCT, new ArrayList<>());
    }

    public void saveListEnterpriseProduct(List<Product.DataBean> datas) {
        Hawk.put(KEY_LIST_ENTERPIRSE_PRODUCT, datas);
    }

    public List<Product.DataBean> getListEnterpriseProduct() {
        return Hawk.get(KEY_LIST_ENTERPIRSE_PRODUCT, new ArrayList<>());
    }

    public void saveListHDProduct(List<Product.DataBean> datas) {
        Hawk.put(KEY_LIST_PRODUCT_HD, datas);
    }

    public List<Product.DataBean> getListHDProduct() {
        return Hawk.get(KEY_LIST_PRODUCT_HD, new ArrayList<>());
    }
    public Token.DataBean getTokenModel() {
        return Hawk.get(TOKEN_MODEL, new Token.DataBean());
    }
    public void saveTokenModel(Token.DataBean token) {
        Hawk.put(TOKEN_MODEL, token);
    }

    public Boolean getstatusNoti() {
        return Hawk.get(Constants.KEY_NOTI, true);
    }
    public void saveStatusNoti(boolean isActived) {
        Hawk.put(Constants.KEY_NOTI, isActived);

    }

    public void saveEmployeeInfo(EmployeeInfomation.DataBean dataBean) {
        Hawk.put(EMLPOYEE_INFO, dataBean);
    }
    public EmployeeInfomation.DataBean getEmployeeInfo() {
        return Hawk.get(EMLPOYEE_INFO, new EmployeeInfomation.DataBean());
    }
    public void saveAppointmentModels (List<SavedAppointModel> savedAppointModels) {
        Hawk.put(APPOINT_MODEL, savedAppointModels);
    }
    public List<SavedAppointModel> getListSavedAppoint() {
        return Hawk.get(APPOINT_MODEL, new ArrayList<>());
    }
}
