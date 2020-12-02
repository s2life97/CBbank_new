package com.saleskit.cbbank.features.customer.add_new_customer;

import android.content.Context;
import android.widget.Toast;

import com.saleskit.cbbank.features.account.APIWebservices;
import com.saleskit.cbbank.features.account.CheckEvaluadate;
import com.saleskit.cbbank.features.account.ControlBean;
import com.saleskit.cbbank.features.account.MessageJson;
import com.saleskit.cbbank.features.base.BasePresenter;
import com.saleskit.cbbank.util.NetworkUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class CreditEvaluationPresenter extends BasePresenter<CreatEvaluationView> {
    @Inject
    CreditEvaluationPresenter() {
    }

    @Override
    public void attachView(CreatEvaluationView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
    }

    public void getAllControl(Context context, String token, String id) {
        APIWebservices apiInterface = NetworkUtil.getCBclient(context).create(APIWebservices.class);
        Call<ControlBean> call = apiInterface.getAllControl(token, String.valueOf(id));
        call.enqueue(new Callback<ControlBean>() {
            @Override
            public void onResponse(Call<ControlBean> call, Response<ControlBean> response) {
                if (response.code() == 200) {
                    List<ControlBean.DataBean> controlBeans = response.body().getData();
                    getView().showUi(controlBeans);
                } else {
                }
            }

            @Override
            public void onFailure(Call<ControlBean> call, Throwable t) {

            }
        });
    }

    public void postToNextStep(Context context, String token, String customerID) {
        APIWebservices apiInterface = NetworkUtil.getCBclient(context).create(APIWebservices.class);
        Call<MessageJson> call = apiInterface.confirmStep2(token, customerID);
        call.enqueue(new Callback<MessageJson>() {
            @Override
            public void onResponse(Call<MessageJson> call, Response<MessageJson> response) {
                getView().hideLoading();
                if (response.code() == 200) {
                    getView().intentNextScreen();
                }
            }

            @Override
            public void onFailure(Call<MessageJson> call, Throwable t) {

            }
        });
    }

    public void checkCondition(Context context, String token, String customerId,
                               int productId, String customerProfileId,
                               List<CheckEvaluadate.ScoringDetailModelsBean> scoringDetailModelsBeans) {
        APIWebservices apiInterface = NetworkUtil.getCBclient(context).create(APIWebservices.class);
        Call<CreatResultBean> call = apiInterface.checkEvaluadate(token, new CheckEvaluadate(customerId,
                String.valueOf(productId),
                customerProfileId, scoringDetailModelsBeans));
        call.enqueue(new Callback<CreatResultBean>() {
            @Override
            public void onResponse(Call<CreatResultBean> call, Response<CreatResultBean> response) {
                getView().hideLoading();
                if (response.code() == 200) {
                    CreatResultBean.DataBean dataBean = response.body().getData();
                    getView().showResult(dataBean);
                } else try {
                    JSONArray jsonArray = new JSONArray(response.errorBody().string());
                    JSONObject json_object = (JSONObject) jsonArray.get(0);
                    String mess = json_object.getString("errorMessage");
                    Toast.makeText(context, mess, Toast.LENGTH_SHORT).show();
                } catch (JSONException | IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<CreatResultBean> call, Throwable t) {
                getView().hideLoading();
            }
        });

    }
}
