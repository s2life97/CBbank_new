package com.saleskit.cbbank.features.appointment;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.widget.Toast;

import com.saleskit.cbbank.R;
import com.saleskit.cbbank.features.account.APIWebservices;
import com.saleskit.cbbank.features.account.Appointment;
import com.saleskit.cbbank.features.base.BasePresenter;
import com.saleskit.cbbank.util.NetworkUtil;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class AddAppointPresenter extends BasePresenter<AppointView> {
    @Inject
    public AddAppointPresenter() {
    }

    @Override
    public void attachView(AppointView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
    }

    public void postImageToServer(Context context, Bitmap bitmap, String token) {

        File f = new File(context.getCacheDir(), "image.jpg");
        try {
            f.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 80 /*ignored for PNG*/, bos);
        byte[] bitmapdata = bos.toByteArray();
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            fos.write(bitmapdata);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Timber.e("file " + f);
        RequestBody reqFile = RequestBody.create(MediaType.parse("multipart/form-data"), f);
        MultipartBody.Part body = MultipartBody.Part.createFormData("file", f.getName(), reqFile);
        Timber.e("  ád" + body);
        APIWebservices apiInterface = NetworkUtil.getCBclient(context).create(APIWebservices.class);
        Call<Imgage> call = apiInterface.postImage(token, body);
        call.enqueue(new Callback<Imgage>() {
            @Override
            public void onResponse(Call<Imgage> call, Response<Imgage> response) {

                if (getView() != null) {
                    getView().hideLoading();
                    getView().hideSwipe();
                }
                if (response.code() == 200) {
                    Imgage.DataBean image = response.body().getData();
                    getView().showResult(image);
                } else {
                    Toast.makeText(context, context.getResources().getString(R.string.cannot_get_data), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Imgage> call, Throwable throwable) {
                getView().hideLoading();
            }
        });

    }

    public void getNearestAppoint(Context context, String token, String userName) {
        APIWebservices apiInterface = NetworkUtil.getCBclient(context).create(APIWebservices.class);
        HashMap<String, String> options = new HashMap<>();
        options.put("userId", userName);
        options.put("numberOfRecords", "3");
        Call<Appointment> call = apiInterface.getNearestAppoint(token, options);
        call.enqueue(new Callback<Appointment>() {
            @Override
            public void onResponse(Call<Appointment> call, Response<Appointment> response) {
                if (getView() != null) {
                    getView().hideSwipe();
                    getView().hideLoading();
                }
                if (response.code() == 200) {
                    List<Appointment.DataBean> nearestAppoint = response.body().getData();
                    getView().showNearestAppoint(nearestAppoint);
                } else {
                    Toast.makeText(context, context.getResources().getString(R.string.cannot_get_data), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Appointment> call, Throwable throwable) {
                getView().hideLoading();
                Timber.e("error " + throwable.getMessage().toString());
            }
        });
    }

    public void getAllAppoint(Context context, String token, int page, String key, String userName) {
        APIWebservices apiInterface = NetworkUtil.getCBclient(context).create(APIWebservices.class);
        HashMap<String, String> options = new HashMap<>();
        options.put("userId", userName);
        options.put("page", String.valueOf(page));
        options.put("pageSize", "10");
        if (!TextUtils.isEmpty(key)) {
            options.put("searchKey", key);
        }
        Call<Appointment> call = apiInterface.getAllAppoint(token, options);
        call.enqueue(new Callback<Appointment>() {
            @Override
            public void onResponse(Call<Appointment> call, Response<Appointment> response) {
                if (getView()!= null) {
                    getView().hideLoading();
                }
                if (response.code() == 200) {
                    if (getView() != null) {
                        getView().hideSwipe();
                    }
                    List<Appointment.DataBean> list = response.body().getData();
                    int totalItems = response.body().getTotalRecords();
                    int pages = totalItems / 10;
                    if (totalItems % 10 != 0) {
                        pages += 1;
                    }
                    if (getView() != null) {
                        getView().showALlAppoint(list, pages);
                    }


                } else {
                    Toast.makeText(context, context.getResources().getString(R.string.cannot_get_data), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Appointment> call, Throwable throwable) {
                getView().hideLoading();
                Timber.e("error " + throwable.getMessage().toString());
            }
        });
    }

    public void searchAppointment(Context context, String token, String key) {
        APIWebservices apiInterface = NetworkUtil.getCBclient(context).create(APIWebservices.class);
        HashMap<String, String> options = new HashMap<>();
        options.put("userId", "322794b4-d7db-446e-8295-cf29a2a333f3");
        options.put("page", "1");
        options.put("pageSize", "10");

        options.put("searchKey", TextUtils.isEmpty(key) ? null : key);
        Call<Appointment> call = apiInterface.getAllAppoint(token, options);
        call.enqueue(new Callback<Appointment>() {
            @Override
            public void onResponse(Call<Appointment> call, Response<Appointment> response) {
                if (response.code() == 200) {
                    if (getView() != null) {
                        getView().hideLoading();
                    }
                    List<Appointment.DataBean> list = response.body().getData();
                    if (getView() != null) {
                        getView().showListSearchedAppoint(list);
                    }

                } else {
                    Toast.makeText(context, context.getResources().getString(R.string.cannot_get_data), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Appointment> call, Throwable throwable) {
                getView().hideLoading();
                Timber.e("error " + throwable.getMessage().toString());
            }
        });
    }

    public void getFilterAppoint(Context context, String token, String startDate, String endDate, int status,
                                 int page, String key, String userName) {
        APIWebservices apiInterface = NetworkUtil.getCBclient(context).create(APIWebservices.class);
        HashMap<String, String> options = new HashMap<>();
        options.put("userId", userName);
        options.put("startDate", startDate);
        options.put("endDate", endDate);
        if (status != -1) {
            options.put("appointmentResultStatus", String.valueOf(status));
        }
        options.put("page", String.valueOf(page));
        options.put("pageSize", "10");
        if (!TextUtils.isEmpty(key)) {
            options.put("searchKey", key);
        }
        Call<FilterAppointment> call = apiInterface.getFilterAppoint(token, options);
        call.enqueue(new Callback<FilterAppointment>() {
            @Override
            public void onResponse(Call<FilterAppointment> call, Response<FilterAppointment> response) {
                if (getView() != null) {
                    getView().hideLoading();
                }
                if (response.code() == 200) {
                    if (getView() != null) {
                        getView().hideSwipe();
                    }
                    List<FilterAppointment.DataBean.AppointmentBelongPeriodsBean> list = response.body().getData().getAppointmentBelongPeriods();
                    FilterAppointment.DataBean dataBeans =response.body().getData();
                    int totalItems = response.body().getTotalRecords();
                    int pages = totalItems / 10;
                    if (totalItems % 10 != 0) {
                        pages += 1;
                    }
                    if (getView() != null) {
                        getView().showFilterAppoint(list, pages, dataBeans, totalItems);
                    }

                } else {
                    Toast.makeText(context, context.getResources().getString(R.string.cannot_get_data), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<FilterAppointment> call, Throwable t) {
                getView().hideLoading();
                Timber.e("error " + t.getMessage().toString());
            }
        });

    }

    public void getNearestAppointByCustomerId(Context context, String token, String userName, String customerId) {
        APIWebservices apiInterface = NetworkUtil.getCBclient(context).create(APIWebservices.class);
        HashMap<String, String> options = new HashMap<>();
        options.put("customerId", customerId);
        options.put("userId", userName);
        options.put("numberOfRecords", "3");
        Call<Appointment> call = apiInterface.getNearestAppointByCustomerId(token, options);
        call.enqueue(new Callback<Appointment>() {
            @Override
            public void onResponse(Call<Appointment> call, Response<Appointment> response) {
                if (getView() != null) {
                    getView().hideSwipe();
                }
                if (response.code() == 200) {
                    List<Appointment.DataBean> nearestAppoint = response.body().getData();
                    getView().showNearestAppoint(nearestAppoint);
                } else {
                    Toast.makeText(context, context.getResources().getString(R.string.cannot_get_data), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Appointment> call, Throwable t) {

            }
        });

    }

    public void getAppointByCustomerId(Context context, String token, int page, String key, String userName, String customerId) {
        APIWebservices apiInterface = NetworkUtil.getCBclient(context).create(APIWebservices.class);
        HashMap<String, String> options = new HashMap<>();
        options.put("customerId", customerId);
        options.put("userId", userName);
        options.put("page", String.valueOf(page));
        options.put("pageSize", "10");
        if (!TextUtils.isEmpty(key)) {
            options.put("searchKey", key);
        }
        Call<Appointment> call = apiInterface.getAppointmentByCustomerId(token, options);
        call.enqueue(new Callback<Appointment>() {
            @Override
            public void onResponse(Call<Appointment> call, Response<Appointment> response) {
                if (getView() != null) {
                    getView().hideLoading();
                }
                if (response.code() == 200) {
                    if (getView() != null) {
                        getView().hideSwipe();
                    }
                    List<Appointment.DataBean> list = response.body().getData();
                    int totalItems = response.body().getTotalRecords();
                    int pages = totalItems / 10;
                    if (totalItems % 10 != 0) {
                        pages += 1;
                    }
                    if (getView() != null) {
                        getView().showALlAppoint(list, pages);
                    }


                } else {
                    Toast.makeText(context, context.getResources().getString(R.string.cannot_get_data), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Appointment> call, Throwable throwable) {
                getView().hideLoading();
                Timber.e("error " + throwable.getMessage().toString());
            }
        });
    }
}
