package com.saleskit.cbbank.features.kpi;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.saleskit.cbbank.features.account.APIWebservices;
import com.saleskit.cbbank.features.account.KPI;
import com.saleskit.cbbank.features.account.KpiEmplyee;
import com.saleskit.cbbank.features.base.BasePresenter;
import com.saleskit.cbbank.util.NetworkUtil;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class KpiPresent extends BasePresenter<KpiView> {
    private static final String TAG = "KpiPresent";

    @Inject
    public KpiPresent() {
    }

    @Override
    public void attachView(KpiView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
    }


    public void getAllCustomer(Context context) {
    }

    public void getRegion(String token, Context context, SwipeRefreshLayout swAll, int month, int year, String key, int page) {
        if (getView() != null) {
            getView().showLoading();
        }
        APIWebservices apiInterface = NetworkUtil.getCBclient(context).create(APIWebservices.class);
        HashMap<String, String> option = new HashMap<>();
        option.put("page", String.valueOf(page));
        option.put("pageSize", "10");
        if (!TextUtils.isEmpty(key)) {
            option.put("keyword", key);
        }
        option.put("year", String.valueOf(year));
        option.put("month", String.valueOf(month));
        Call<KPI> call = apiInterface.getRegionByMonth(token
                , option);
        call.enqueue(new Callback<KPI>() {
            @Override
            public void onResponse(Call<KPI> call, Response<KPI> response) {
                swAll.setRefreshing(false);
                if (getView() != null) {
                    getView().hideLoading();
                }
                if (response.code() == 200) {
                    List<KPI.DataBean.ResultsBean> list = response.body().getData().getResults();
                    int totalItem = response.body().getData().getRowCount();
                    int pages = totalItem / 10;
                    if (totalItem % 10 != 0) {
                        pages += 1;
                    }
                    if (getView() != null) {
                        getView().showRegionList(list, pages);
                    }
                }
            }

            @Override
            public void onFailure(Call<KPI> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.toString());
            }
        });
    }

    public void getRegionByYear(String token, Context context, SwipeRefreshLayout swAll, int year, String key, int page) {
        if (getView() != null) {
            getView().showLoading();
        }
        APIWebservices apiInterface = NetworkUtil.getCBclient(context).create(APIWebservices.class);
        HashMap<String, String> option = new HashMap<>();
        option.put("page", String.valueOf(page));
        option.put("pageSize", "10");
        if (!TextUtils.isEmpty(key)) {
            option.put("keyword", key);
        }
        option.put("year", String.valueOf(year));
        Call<KPI> call = apiInterface.getRegionByYear(token
                , option);
        call.enqueue(new Callback<KPI>() {
            @Override
            public void onResponse(Call<KPI> call, Response<KPI> response) {
                swAll.setRefreshing(false);
                if (getView() != null) {
                    getView().hideLoading();
                }
                if (response.code() == 200) {
                    List<KPI.DataBean.ResultsBean> list = response.body().getData().getResults();
                    int totalItem = response.body().getData().getRowCount();
                    int pages = totalItem / 10;
                    if (totalItem % 10 != 0) {
                        pages += 1;
                    }
                    getView().showRegionList(list, pages);
                }
            }

            @Override
            public void onFailure(Call<KPI> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.toString());
            }
        });
    }

    public void getRegionByQuater(String token, Context context, SwipeRefreshLayout swAll, int quarter, int year, String key, int page) {
        if (getView() != null) {
            getView().showLoading();
        }
        APIWebservices apiInterface = NetworkUtil.getCBclient(context).create(APIWebservices.class);
        HashMap<String, String> option = new HashMap<>();
        option.put("page", String.valueOf(page));
        option.put("pageSize", "10");
        if (!TextUtils.isEmpty(key)) {
            option.put("keyword", key);
        }
        option.put("year", String.valueOf(year));
        option.put("quarter", String.valueOf(quarter));
        Call<KPI> call = apiInterface.getRegionByQuater(token
                , option);
        call.enqueue(new Callback<KPI>() {
            @Override
            public void onResponse(Call<KPI> call, Response<KPI> response) {
                swAll.setRefreshing(false);
                if (getView() != null) {
                    getView().hideLoading();
                }
                if (response.code() == 200) {
                    List<KPI.DataBean.ResultsBean> list = response.body().getData().getResults();
                    int totalItem = response.body().getData().getRowCount();
                    int pages = totalItem / 10;
                    if (totalItem % 10 != 0) {
                        pages += 1;
                    }
                    getView().showRegionList(list, pages);
                }
            }

            @Override
            public void onFailure(Call<KPI> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.toString());
            }
        });
    }

    public void getBranchByYear(String token, Context context, SwipeRefreshLayout swAll, int choosenYear, String key, String id, int page) {
        if (getView() != null) {
            getView().showLoading();
        }
        APIWebservices apiInterface = NetworkUtil.getCBclient(context).create(APIWebservices.class);
        HashMap<String, String> option = new HashMap<>();
        option.put("page", String.valueOf(page));
        option.put("pageSize", "10");
        option.put("regionId", id);
        if (!TextUtils.isEmpty(key)) {
            option.put("keyword", key);
        }
        option.put("year", String.valueOf(choosenYear));
        Call<KpiBranch> call = apiInterface.getBranchByYear(token
                , option);
        call.enqueue(new Callback<KpiBranch>() {
            @Override
            public void onResponse(Call<KpiBranch> call, Response<KpiBranch> response) {
                swAll.setRefreshing(false);
                if (getView() != null) {
                    getView().hideLoading();
                }
                if (response.code() == 200) {
                    List<KpiBranch.DataBean.ResultsBean> list = response.body().getData().getResults();
                    int totalItem = response.body().getData().getRowCount();
                    int pages = totalItem / 10;
                    if (totalItem % 10 != 0) {
                        pages += 1;
                    }
                    if (getView() != null) {
                        getView().showAllBranch(list, pages);
                    }
                }
            }

            @Override
            public void onFailure(Call<KpiBranch> call, Throwable t) {

            }
        });
    }

    public void getBranchByMoth(String token, Context context, SwipeRefreshLayout swAll, int choosenMonth,
                                int choosenYear, String key, String id, int page) {
        if (getView() != null) {
            getView().showLoading();
        }
        APIWebservices apiInterface = NetworkUtil.getCBclient(context).create(APIWebservices.class);
        HashMap<String, String> option = new HashMap<>();
        option.put("page", String.valueOf(page));
        option.put("pageSize", "10");
        if (!TextUtils.isEmpty(key)) {
            option.put("keyword", key);
        }
        option.put("regionId", id);
        option.put("year", String.valueOf(choosenYear));
        option.put("month", String.valueOf(choosenMonth));
        Call<KpiBranch> call = apiInterface.getBranchByMonth(token
                , option);
        call.enqueue(new Callback<KpiBranch>() {
            @Override
            public void onResponse(Call<KpiBranch> call, Response<KpiBranch> response) {
                swAll.setRefreshing(false);
                if (getView() != null) {
                    getView().hideLoading();
                }
                if (response.code() == 200) {
                    List<KpiBranch.DataBean.ResultsBean> list = response.body().getData().getResults();
                    int totalItem = response.body().getData().getRowCount();
                    int pages = totalItem / 10;
                    if (totalItem % 10 != 0) {
                        pages += 1;
                    }
                    if (getView() != null) {
                        getView().showAllBranch(list, pages);
                    }
                }
            }

            @Override
            public void onFailure(Call<KpiBranch> call, Throwable t) {

            }
        });
    }

    public void getBranchByQuater(String token, Context context, SwipeRefreshLayout swAll,
                                  int choosenQuater, int choosenYear, String key, String id, int page) {
        if (getView() != null) {
            getView().showLoading();
        }
        APIWebservices apiInterface = NetworkUtil.getCBclient(context).create(APIWebservices.class);
        HashMap<String, String> option = new HashMap<>();
        option.put("page", String.valueOf(page));
        option.put("pageSize", "10");
        option.put("regionId", id);
        if (!TextUtils.isEmpty(key)) {
            option.put("keyword", key);
        }
        option.put("year", String.valueOf(choosenYear));
        option.put("quarter", String.valueOf(choosenQuater));
        Call<KpiBranch> call = apiInterface.getBranchByQuater(token
                , option);
        call.enqueue(new Callback<KpiBranch>() {
            @Override
            public void onResponse(Call<KpiBranch> call, Response<KpiBranch> response) {
                swAll.setRefreshing(false);
                if (getView() != null) {
                    getView().hideLoading();
                }
                if (response.code() == 200) {
                    List<KpiBranch.DataBean.ResultsBean> list = response.body().getData().getResults();
                    int totalItem = response.body().getData().getRowCount();
                    int pages = totalItem / 10;
                    if (totalItem % 10 != 0) {
                        pages += 1;
                    }
                    getView().showAllBranch(list, pages);
                }
            }

            @Override
            public void onFailure(Call<KpiBranch> call, Throwable t) {

            }
        });
    }

    public void getAllDepartmentByMonth(String token, Context context, SwipeRefreshLayout swAll,
                                        int currentMonth, int currentYear, String key, String id, int page) {
        if (getView() != null) {
            getView().showLoading();
        }
        APIWebservices apiInterface = NetworkUtil.getCBclient(context).create(APIWebservices.class);
        HashMap<String, String> option = new HashMap<>();
        option.put("page", String.valueOf(page));
        option.put("pageSize", "10");
        if (!TextUtils.isEmpty(key)) {
            option.put("keyword", key);
        }
        option.put("branchId", id);
        option.put("year", String.valueOf(currentYear));
        option.put("month", String.valueOf(currentMonth));
        Call<KpiDepartment> call = apiInterface.getDepartMentByMonth(token
                , option);
        call.enqueue(new Callback<KpiDepartment>() {
            @Override
            public void onResponse(Call<KpiDepartment> call, Response<KpiDepartment> response) {
                swAll.setRefreshing(false);
                if (getView() != null) {
                    getView().hideLoading();
                }
                if (response.code() == 200) {
                    List<KpiDepartment.DataBean.ResultsBean> list = response.body().getData().getResults();
                    int totalItem = response.body().getData().getRowCount();
                    int pages = totalItem / 10;
                    if (totalItem % 10 != 0) {
                        pages += 1;
                    }
                    getView().showAllDepartment(list, pages);
                }
            }

            @Override
            public void onFailure(Call<KpiDepartment> call, Throwable t) {
            }
        });

    }

    public void getDepartMentByYear(String token,
                                    Context context, SwipeRefreshLayout swAll, int choosenYear, String key, String id, int page) {
        if (getView() != null) {
            getView().showLoading();
        }
        APIWebservices apiInterface = NetworkUtil.getCBclient(context).create(APIWebservices.class);
        HashMap<String, String> option = new HashMap<>();
        option.put("page", String.valueOf(page));
        option.put("pageSize", "10");
        option.put("branchId", id);
        if (!TextUtils.isEmpty(key)) {
            option.put("keyword", key);
        }
        option.put("year", String.valueOf(choosenYear));
        Call<KpiDepartment> call = apiInterface.getDepartmentByYear(token
                , option);
        call.enqueue(new Callback<KpiDepartment>() {
            @Override
            public void onResponse(Call<KpiDepartment> call, Response<KpiDepartment> response) {
                swAll.setRefreshing(false);
                if (getView() != null) {
                    getView().hideLoading();
                }
                if (response.code() == 200) {
                    List<KpiDepartment.DataBean.ResultsBean> list = response.body().getData().getResults();
                    int totalItem = response.body().getData().getRowCount();
                    int pages = totalItem / 10;
                    if (totalItem % 10 != 0) {
                        pages += 1;
                    }
                    getView().showAllDepartment(list, pages);
                }
            }

            @Override
            public void onFailure(Call<KpiDepartment> call, Throwable t) {

            }
        });
    }

    public void getDepartmentByQuater(String token, Context context,
                                      SwipeRefreshLayout swAll, int choosenQuater,
                                      int choosenYear, String key, String id, int page) {
        APIWebservices apiInterface = NetworkUtil.getCBclient(context).create(APIWebservices.class);
        HashMap<String, String> option = new HashMap<>();
        option.put("page", String.valueOf(page));
        option.put("pageSize", "10");
        option.put("branchId", id);
        if (!TextUtils.isEmpty(key)) {
            option.put("keyword", key);
        }
        option.put("year", String.valueOf(choosenYear));
        option.put("quarter", String.valueOf(choosenQuater));
        Call<KpiDepartment> call = apiInterface.getDepartmentByQuater(token
                , option);
        call.enqueue(new Callback<KpiDepartment>() {
            @Override
            public void onResponse(Call<KpiDepartment> call, Response<KpiDepartment> response) {
                swAll.setRefreshing(false);
                if (getView() != null) {
                    getView().hideLoading();
                }
                if (response.code() == 200) {
                    List<KpiDepartment.DataBean.ResultsBean> list = response.body().getData().getResults();
                    int totalItem = response.body().getData().getRowCount();
                    int pages = totalItem / 10;
                    if (totalItem % 10 != 0) {
                        pages += 1;
                    }
                    getView().showAllDepartment(list, pages);
                }
            }

            @Override
            public void onFailure(Call<KpiDepartment> call, Throwable t) {

            }
        });
    }

    public void getEmployeeByMonth(String token, Context context,
                                   SwipeRefreshLayout swAll, int currentMonth, int currentYear, String key, String id, int page) {
        getView().showLoading();
        APIWebservices apiInterface = NetworkUtil.getCBclient(context).create(APIWebservices.class);
        HashMap<String, String> option = new HashMap<>();
        option.put("page", String.valueOf(page));
        option.put("pageSize", "10");
        if (!TextUtils.isEmpty(key)) {
            option.put("keyword", key);
        }
        option.put("deparmentId", id);
        option.put("year", String.valueOf(currentYear));
        option.put("month", String.valueOf(currentMonth));
        Call<KpiEmplyee> call = apiInterface.getEmployeeByMonth(token
                , option);
        call.enqueue(new Callback<KpiEmplyee>() {
            @Override
            public void onResponse(Call<KpiEmplyee> call, Response<KpiEmplyee> response) {
                swAll.setRefreshing(false);
                if (getView() != null) {
                    getView().hideLoading();
                }
                if (response.code() == 200) {
                    List<KpiEmplyee.DataBean.ResultsBean> list = response.body().getData().getResults();
                    int totalItem = response.body().getData().getRowCount();
                    int pages = totalItem / 10;
                    if (totalItem % 10 != 0) {
                        pages += 1;
                    }
                    getView().showAllEmployee(list, pages);
                }
            }

            @Override
            public void onFailure(Call<KpiEmplyee> call, Throwable t) {
            }
        });
    }

    public void getEmployeeByYear(String token, Context context,
                                  SwipeRefreshLayout swAll, int choosenYear, String key, String id, int page) {
        if (getView() != null) {
            getView().showLoading();
        }
        APIWebservices apiInterface = NetworkUtil.getCBclient(context).create(APIWebservices.class);
        HashMap<String, String> option = new HashMap<>();
        option.put("page", String.valueOf(page));
        option.put("pageSize", "10");
        option.put("deparmentId", id);
        if (!TextUtils.isEmpty(key)) {
            option.put("keyword", key);
        }
        option.put("year", String.valueOf(choosenYear));
        Call<KpiEmplyee> call = apiInterface.getEmployeeByYear(token
                , option);
        call.enqueue(new Callback<KpiEmplyee>() {
            @Override
            public void onResponse(Call<KpiEmplyee> call, Response<KpiEmplyee> response) {
                swAll.setRefreshing(false);
                if (getView() != null) {
                    getView().hideLoading();
                }
                if (response.code() == 200) {
                    List<KpiEmplyee.DataBean.ResultsBean> list = response.body().getData().getResults();
                    int totalItem = response.body().getData().getRowCount();
                    int pages = totalItem / 10;
                    if (totalItem % 10 != 0) {
                        pages += 1;
                    }
                    getView().showAllEmployee(list, pages);
                }
            }

            @Override
            public void onFailure(Call<KpiEmplyee> call, Throwable t) {

            }
        });

    }

    public void getEmployeeByQuater(String token, Context context, SwipeRefreshLayout swAll,
                                    int choosenQuater, int choosenYear, String key, String id, int page) {
        APIWebservices apiInterface = NetworkUtil.getCBclient(context).create(APIWebservices.class);
        HashMap<String, String> option = new HashMap<>();
        option.put("page", String.valueOf(page));
        option.put("pageSize", "10");
        option.put("deparmentId", id);
        if (!TextUtils.isEmpty(key)) {
            option.put("keyword", key);
        }
        option.put("year", String.valueOf(choosenYear));
        option.put("quarter", String.valueOf(choosenQuater));
        Call<KpiEmplyee> call = apiInterface.getEmployeeByQuter(token
                , option);
        call.enqueue(new Callback<KpiEmplyee>() {
            @Override
            public void onResponse(Call<KpiEmplyee> call, Response<KpiEmplyee> response) {
                swAll.setRefreshing(false);
                if (getView() != null) {
                    getView().hideLoading();
                }
                if (response.code() == 200) {
                    List<KpiEmplyee.DataBean.ResultsBean> list = response.body().getData().getResults();
                    int totalItem = response.body().getData().getRowCount();
                    int pages = totalItem / 10;
                    if (totalItem % 10 != 0) {
                        pages += 1;
                    }
                    getView().showAllEmployee(list, pages);
                }
            }

            @Override
            public void onFailure(Call<KpiEmplyee> call, Throwable t) {

            }
        });
    }
}
