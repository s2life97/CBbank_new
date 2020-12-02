package com.saleskit.cbbank.features.kpi;

import android.content.Context;

import com.saleskit.cbbank.features.account.APIWebservices;
import com.saleskit.cbbank.features.base.BasePresenter;
import com.saleskit.cbbank.util.NetworkUtil;

import java.util.HashMap;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class KpiPresenter extends BasePresenter<DetailKpiView> {
    @Inject
    public KpiPresenter() {
    }

    @Override
    public void attachView(DetailKpiView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
    }

    public void getDetailEmployeeByMonth(boolean rewnewModel,Context context, String token, String userName, int choosenYear, int month) {
//        getView().showLoading();
        APIWebservices apiInterface = NetworkUtil.getCBclient(context).create(APIWebservices.class);
        HashMap<String, String> option = new HashMap<>();
        option.put("year", String.valueOf(choosenYear));
        option.put("month", String.valueOf(month));
        option.put("userName", userName);
        Call<MonthDetailEmployee> call = apiInterface.getDetailEmployeeByMoth(token
                , option);
        call.enqueue(new Callback<MonthDetailEmployee>() {
            @Override
            public void onResponse(Call<MonthDetailEmployee> call, Response<MonthDetailEmployee> response) {
                getView().hideLoading();
                if(response.code()==200){
                    MonthDetailEmployee.DataBean dataBean = response.body().getData();
                    getView().showMonthDetailEmployee(rewnewModel, dataBean);
                }
            }

            @Override
            public void onFailure(Call<MonthDetailEmployee> call, Throwable t) {
                getView().hideLoading();
            }
        });
    }

    public void getDetailEmployeeByYear(boolean rewnewModel, Context context, String token, String userName, int choosenYear) {
//        getView().showLoading();
        APIWebservices apiInterface = NetworkUtil.getCBclient(context).create(APIWebservices.class);
        HashMap<String, String> option = new HashMap<>();
        option.put("year", String.valueOf(choosenYear));
        option.put("userName", userName);
        Call<YearDetailEmployee> call = apiInterface.getDetailEmployeeByYear(token
                , option);
        call.enqueue(new Callback<YearDetailEmployee>() {
            @Override
            public void onResponse(Call<YearDetailEmployee> call, Response<YearDetailEmployee> response) {
                getView().hideLoading();
                if(response.code()==200){
                    YearDetailEmployee.DataBean dataBean = response.body().getData();
                    getView().showDetailYearEmployee(rewnewModel, dataBean);
                }
            }

            @Override
            public void onFailure(Call<YearDetailEmployee> call, Throwable t) {
                getView().hideLoading();
            }
        });
    }

    public void getDetailEmployeeByQuater(boolean rewnewModel, Context context, String token, String userName, int choosenYear, int choosenQuater) {
//        getView().showLoading();
        APIWebservices apiInterface = NetworkUtil.getCBclient(context).create(APIWebservices.class);
        HashMap<String, String> option = new HashMap<>();
        option.put("year", String.valueOf(choosenYear));
        option.put("quarter", String.valueOf(choosenQuater));
        option.put("userName", userName);
        Call<QuaterDetailEmployee> call = apiInterface.getDetailEmployeeByQuater(token
                , option);
        call.enqueue(new Callback<QuaterDetailEmployee>() {
            @Override
            public void onResponse(Call<QuaterDetailEmployee> call, Response<QuaterDetailEmployee> response) {
                getView().hideLoading();
                if(response.code()==200){
                    QuaterDetailEmployee.DataBean dataBean = response.body().getData();
                    getView().showDetailEmployee(rewnewModel, dataBean);
                }
            }

            @Override
            public void onFailure(Call<QuaterDetailEmployee> call, Throwable t) {
                getView().hideLoading();
            }
        });
    }
}
