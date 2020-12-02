package com.saleskit.cbbank.features.customer.add_new_customer;

import android.content.Context;
import android.text.Editable;
import android.widget.Toast;

import com.saleskit.cbbank.R;
import com.saleskit.cbbank.features.account.APIWebservices;
import com.saleskit.cbbank.features.account.CustomerInfoRespond;
import com.saleskit.cbbank.features.base.BasePresenter;
import com.saleskit.cbbank.features.customer.add_new_customer.model.CustomerEnterpriseRespond;
import com.saleskit.cbbank.features.customer.add_new_customer.model.CustomerPostProfile;
import com.saleskit.cbbank.features.personal.CustomerInfo;
import com.saleskit.cbbank.features.personal.CustomerInfomation;
import com.saleskit.cbbank.util.NetworkUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class AddCustomerPresenter extends BasePresenter<AddCustomerView> {
    @Inject
    AddCustomerPresenter() {
    }

    @Override
    public void attachView(AddCustomerView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
    }

    public void showCustomerInfo(Context context, String token, int id) {
        APIWebservices apiInterface = NetworkUtil.getCBclient(context).create(APIWebservices.class);
        HashMap<String, String> options = new HashMap<>();
        options.put("customerId", String.valueOf(id));
        options.put("page", String.valueOf(1));
        options.put("pagesize", "10");
        Call<CustomerInfo> call = apiInterface.getCustomerInfo(token, options);
        call.enqueue(new Callback<CustomerInfo>() {
            @Override
            public void onResponse(Call<CustomerInfo> call, Response<CustomerInfo> response) {
                getView().hideLoading();
                if (response.code() == 200) {
                    CustomerInfo.DataBean dataBean = response.body().getData();
                    getView().showCustomerInfomation(dataBean);
                } else {
                    Toast.makeText(context, context.getResources().getString(R.string.cannot_get_data), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CustomerInfo> call, Throwable throwable) {
                getView().hideLoading();
            }
        });

    }

//    public void updateCustomerProfile(Context context, String token, int customerId, String
//            firstName, String lastName,
//                                      String customerCode, String birth, String gender, String identity,
//                                      String provide, String providePlace, String address,
//                                      String phone, String email, String education, String assertStatus, String
//                                              martial, String customerType, String cicResult) {
//        APIWebservices apiInterface = NetworkUtil.getCBclient(context).create(APIWebservices.class);
//        Call<MessageBean> call = apiInterface.updateCustomer(token, new CustomerPostProfile(customerId, firstName, lastName,
//                customerCode, birth, gender, identity,
//                provide, providePlace, address, phone, email, education, assertStatus, martial, customerType, cicResult));
//        call.enqueue(new Callback<MessageBean>() {
//            @Override
//            public void onResponse(Call<MessageBean> call, Response<MessageBean> response) {
//                if (response.code() == 200) {
////                    String result= response.body().getData();
//                    getView().intentNextStep();
//                } else if (response.code() == 400) {
//                    try {
//                        JSONArray jsonArray = new JSONArray(response.errorBody().string());
//                        JSONObject json_object = (JSONObject) jsonArray.get(0);
//                        String mess = json_object.getString("errorMessage");
//                        Toast.makeText(context, mess, Toast.LENGTH_SHORT).show();
//                    } catch (JSONException | IOException e) {
//                        e.printStackTrace();
//                        Timber.e(" casd " + e.getMessage());
//                    }
//                } else {
//                    Toast.makeText(context, context.getResources().getString(R.string.cannot_get_data), Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<MessageBean> call, Throwable t) {
//
//            }
//        });
//    }

//    public void insertnewProfile(Context context, String token, String customerID, String productID) {
//        APIWebservices apiInterface = NetworkUtil.getCBclient(context).create(APIWebservices.class);
//        Call<MessageBean> call = apiInterface.insertCredit(token, new InsertCreditModel(productID, customerID));
//        call.enqueue(new Callback<MessageBean>() {
//            @Override
//            public void onResponse(Call<MessageBean> call, Response<MessageBean> response) {
//                if (response.code() == 200) {
//                    getView().showProfileCustomerID(response.body().getData());
//                } else if (response.code() == 400) {
//                    try {
//                        JSONArray jsonArray = new JSONArray(response.errorBody().string());
//                        JSONObject json_object = (JSONObject) jsonArray.get(0);
//                        String mess = json_object.getString("errorMessage");
//                        Toast.makeText(context, mess, Toast.LENGTH_SHORT).show();
//                    } catch (JSONException | IOException e) {
//                        e.printStackTrace();
//                        Timber.e(" casd " + e.getMessage());
//                    }
//                } else {
//                    Toast.makeText(context, context.getResources().getString(R.string.cannot_get_data), Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<MessageBean> call, Throwable t) {
//
//            }
//        });
//
//    }

    public void confirmStep1(Context context, String token, int customerId, String customerType,
                             String firstName, String lastName,
                             String customerCode, String birth, String gender, String identity,
                             String provide, String providePlace, String address,
                             String phone, String email, String education, String assertStatus, String
                                     martial, String cicResult,
                             int customerProfileID, int productId, int customerId1) {

        APIWebservices apiInterface = NetworkUtil.getCBclient(context).create(APIWebservices.class);
        Call<CustomerInfoRespond> call = apiInterface.confirmStep1(token, new CustomerPostProfile(
                new CustomerPostProfile.CustomerBean(customerId, "1", firstName, lastName,
                        customerCode, birth, gender, identity,
                        provide, providePlace, address, phone, email, education, assertStatus, martial, cicResult),
                new CustomerPostProfile.CustomerProfileCreditBean(customerProfileID, productId, customerId)
        ));

        call.enqueue(new Callback<CustomerInfoRespond>() {
            @Override
            public void onResponse(Call<CustomerInfoRespond> call, Response<CustomerInfoRespond> response) {
                getView().hideLoading();
                if (response.code() == 200) {
                    CustomerInfoRespond.DataBeanX.DataBean dataBean = response.body().getData().getData();
                    getView().showProfileCustomerID(dataBean);
                } else {
                    try {
                        JSONArray jsonArray = new JSONArray(response.errorBody().string());
                        JSONObject json_object = (JSONObject) jsonArray.get(0);
                        String mess = json_object.getString("errorMessage");
                        Toast.makeText(context, mess, Toast.LENGTH_SHORT).show();
                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<CustomerInfoRespond> call, Throwable t) {

            }
        });
    }
}
