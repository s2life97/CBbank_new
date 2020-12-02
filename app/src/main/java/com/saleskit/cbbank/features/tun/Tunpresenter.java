package com.saleskit.cbbank.features.tun;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.saleskit.cbbank.R;
import com.saleskit.cbbank.features.account.APIWebservices;
import com.saleskit.cbbank.features.account.EmployeeJson;
import com.saleskit.cbbank.features.account.RegionJson;
import com.saleskit.cbbank.features.account.TunDepartmentJson;
import com.saleskit.cbbank.features.account.TunJson;
import com.saleskit.cbbank.features.base.BasePresenter;
import com.saleskit.cbbank.util.NetworkUtil;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class Tunpresenter extends BasePresenter<TunView> {
    private static final String TAG = "Tunpresenter";

    @Inject
    public Tunpresenter() {
    }

    @Override
    public void attachView(TunView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
    }

    public void getAllBranch(Context context, SwipeRefreshLayout swAll, String id, int page, String key, String token) {
        getView().showLoading();
        APIWebservices apiInterface = NetworkUtil.getCBclient(context).create(APIWebservices.class);
        HashMap<String, String> option = new HashMap<>();
        option.put("regionId", id);
        option.put("page", String.valueOf(page));
        option.put("pageSize", "10");
        if (!TextUtils.isEmpty(key)) {
            option.put("keyword", key);
        }
        Call<TunJson> call = apiInterface.getBranchbyId(token, option);
        call.enqueue(new Callback<TunJson>() {
            @Override
            public void onResponse(Call<TunJson> call, Response<TunJson> response) {
                swAll.setRefreshing(false);
                getView().hideLoading();
                if (response.code() == 200) {
//                    List<KpiBranch.DataBean> list = response.body().getData();
//                    getView().showAllBranch(list);
                    List<TunJson.DataBean.ResultsBean> list = response.body().getData().getResults();
                    int totalItem = response.body().getData().getRowCount();
                    int pages = totalItem / 10;
                    if (totalItem % 10 != 0) {
                        pages += 1;
                    }
                    getView().showBranchlist(list, pages);
                } else {
                    Toast.makeText(context, context.getResources().getString(R.string.cannot_get_data), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<TunJson> call, Throwable t) {
                if (getView() != null) {
                    getView().hideLoading();
                }
                ;
            }
        });
    }

    public void getAllDepartment(Context context, SwipeRefreshLayout swAll,
                                 String id, int page, String key, String token) {
        getView().showLoading();
        APIWebservices apiInterface = NetworkUtil.getCBclient(context).create(APIWebservices.class);
        HashMap<String, String> option = new HashMap<>();
        option.put("branchId", id);
        option.put("page", String.valueOf(page));
        option.put("pageSize", "10");
        if (!TextUtils.isEmpty(key)) {
            option.put("keyword", key);
        }

        Call<TunDepartmentJson> call = apiInterface.getDepartmentById(token, option
        );
        call.enqueue(new Callback<TunDepartmentJson>() {
            @Override
            public void onResponse(Call<TunDepartmentJson> call, Response<TunDepartmentJson> response) {
                swAll.setRefreshing(false);
                getView().hideLoading();
                if (response.code() == 200) {
//                    List<KpiBranch.DataBean> list = response.body().getData();
//                    getView().showAllBranch(list);
                    List<TunDepartmentJson.DataBean.ResultsBean> list = response.body().getData().getResults();
                    int totalItem = response.body().getData().getRowCount();
                    int pages = totalItem / 10;
                    if (totalItem % 10 != 0) {
                        pages += 1;
                    }
                    getView().showAllDepartment(list, pages);
                } else {
                    Toast.makeText(context, context.getResources().getString(R.string.cannot_get_data), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<TunDepartmentJson> call, Throwable t) {

            }
        });
    }


    public void getAllEmployee(Context context, SwipeRefreshLayout swAll, String id, int page, String key, String token) {
        getView().showLoading();
        APIWebservices apiInterface = NetworkUtil.getCBclient(context).create(APIWebservices.class);
        HashMap<String, String> option = new HashMap<>();
        option.put("departmentId", id);
        option.put("page", String.valueOf(page));
        option.put("pageSize", "10");
        if (!TextUtils.isEmpty(key)) {
            option.put("keyword", key);
        }
        Call<EmployeeJson> call = apiInterface.getAllEmplyee(token, option
        );
        call.enqueue(new Callback<EmployeeJson>() {
            @Override
            public void onResponse(Call<EmployeeJson> call, Response<EmployeeJson> response) {
                swAll.setRefreshing(false);
                if (getView() != null) {
                    getView().hideLoading();
                }
                if (response.code() == 200) {
//                    List<KpiBranch.DataBean> list = response.body().getData();
//                    getView().showAllBranch(list);
                    List<EmployeeJson.DataBean.ResultsBean> list = response.body().getData().getResults();
                    int totalItem = response.body().getData().getRowCount();
                    int pages = totalItem / 10;
                    if (totalItem % 10 != 0) {
                        pages += 1;
                    }
                    getView().showAllEmployee(list, pages);
                } else {
                    Toast.makeText(context, context.getResources().getString(R.string.cannot_get_data), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<EmployeeJson> call, Throwable t) {

            }
        });
    }

    public void getRegion(Context context, SwipeRefreshLayout swAll, int page, String key, String token) {
        getView().showLoading();
        APIWebservices apiInterface = NetworkUtil.getCBclient(context).create(APIWebservices.class);
        HashMap<String, String> option = new HashMap<>();
        option.put("page", String.valueOf(page));
        option.put("pageSize", "10");
        if (!TextUtils.isEmpty(key)) {
            option.put("keyword", key);
        }
        Call<RegionJson> call = apiInterface.getAllRegion(token, option);
        call.enqueue(new Callback<RegionJson>() {
            @Override
            public void onResponse(Call<RegionJson> call, Response<RegionJson> response) {
                swAll.setRefreshing(false);
                if (getView() != null) {
                    getView().hideLoading();
                }
                if (response.code() == 200) {
                    if (response.body() != null && response.body().getData() != null && response.body().getData().getResults() != null) {
                        List<RegionJson.DataBean.ResultsBean> dataBeans = response.body().getData().getResults();
                        int totalItem = response.body().getData().getRowCount();
                        int pages = totalItem / 10;
                        if (totalItem % 10 != 0) {
                            pages += 1;
                        }
                        getView().showAllRegion(dataBeans, pages);
                    }
                }
            }

            @Override
            public void onFailure(Call<RegionJson> call, Throwable t) {

            }
        });
    }

}
