package com.saleskit.cbbank.features.account;

import android.content.Context;
import android.widget.Toast;

import com.saleskit.cbbank.Constants;
import com.saleskit.cbbank.R;
import com.saleskit.cbbank.features.base.BasePresenter;
import com.saleskit.cbbank.features.database.HawkHelper;
import com.saleskit.cbbank.util.NetworkUtil;
import com.saleskit.cbbank.util.rx.netmodel.Token;

import org.json.JSONObject;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class LoginPresenter extends BasePresenter<LoginView> {

    private int wrongTurn = 0;

    @Inject
    LoginPresenter() {
    }

    @Override
    public void attachView(LoginView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
    }


//    public void doLogin(Context context, String userName, String password) {
//        HashMap<String, String> map = new HashMap<>();
//        map.put("UserName", userName);
//        map.put("Password", password);
//        map.put("grant_type", Constants.PASSWORD);
//        APIWebservices apiInterface = NetworkUtil.getCBclient(context).create(APIWebservices.class);
//        Call<Token> call = apiInterface.doLogin(new LoginModel(userName, password));
//        call.enqueue(new Callback<Token>() {
//            @Override
//            public void onResponse(Call<Token> call, Response<Token> response) {
//                if (getView() != null) {
//                    getView().hideLoading();
//                }
//                Timber.e(" code " + response.code());
//                if (response.code() == 200) {
//                    Token.DataBean token = response.body().getData();
//                    HawkHelper.getInstance(context).saveTokenModel(token);
//                    String authenToken = Constants.BASE_TOKEN + token.getToken();
//                    getView().onSuccessLogin(authenToken);
//                } else if (response.code() == 400) {
//                    wrongTurn++;
//                    if (wrongTurn == 5) {
//                        getView().showLockNoti();
//                        wrongTurn = 0;
//                        return;
//                    }
//                    try {
//                        JSONObject jObjError = new JSONObject(response.errorBody().string());
//                        String mess = jObjError.getString("Message");
//                        Timber.e(mess);
//                        Toast.makeText(context, mess, Toast.LENGTH_SHORT).show();
////                        switch (mess) {
////                            case "invalid_grant":
////                                Toast.makeText(context, "Tên đăng nhập hoặc mật khẩu chưa đúng!", Toast.LENGTH_LONG).show();
////                                break;
////                            default:
////                                Toast.makeText(context, "Có lỗi xảy ra !", Toast.LENGTH_LONG).show();
////                        }
//                    } catch (Exception e) {
//                        Timber.e(e.getMessage());
//                    }
//                } else {
//                    Toast.makeText(context, context.getResources().getString(R.string.cannot_get_data), Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Token> call, Throwable throwable) {
//                Timber.e("fail " + throwable.getMessage());
//            }
//        });
//    }

    public void getEmployeeInfo(Context context, String authenToken) {
        APIWebservices apiInterface = NetworkUtil.getCBclient(context).create(APIWebservices.class);
        Call<EmployeeInfomation> call = apiInterface.getEmployeeInfo(authenToken);
        call.enqueue(new Callback<EmployeeInfomation>() {
            @Override
            public void onResponse(Call<EmployeeInfomation> call, Response<EmployeeInfomation> response) {
                if (response.code() == 200) {
                    EmployeeInfomation.DataBean dataBean = response.body().getData();
                    getView().saveEmployeeInfo(dataBean);
                }
            }

            @Override
            public void onFailure(Call<EmployeeInfomation> call, Throwable t) {
            }
        });
    }

    public void doLoginWithToken(Context context, String userName, String passWord, String firebaseToken) {
        APIWebservices apiInterface = NetworkUtil.getCBclient(context).create(APIWebservices.class);
        Call<Token> call = apiInterface.postToken(new LoginModel(userName, passWord), firebaseToken);
        call.enqueue(new Callback<Token>() {
            @Override
            public void onResponse(Call<Token> call, Response<Token> response) {
                if (getView() != null) {
                    getView().hideLoading();
                }
                if (response.code() == 200) {
                    Token.DataBean token = response.body().getData();
                    HawkHelper.getInstance(context).saveTokenModel(token);
                    String authenToken = Constants.BASE_TOKEN + token.getToken();
                    getView().onSuccessLogin(authenToken);
                } else if (response.code() == 400) {
                    wrongTurn++;
                    if (wrongTurn == 5) {
                        getView().showLockNoti();
                        wrongTurn = 0;
                        return;
                    }
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        String mess = jObjError.getString("Message");
                        Timber.e(mess);
                        getView().showErrorMess(mess);

//                        switch (mess) {
//                            case "invalid_grant":
//                                Toast.makeText(context, "Tên đăng nhập hoặc mật khẩu chưa đúng!", Toast.LENGTH_LONG).show();
//                                break;
//                            default:
//                                Toast.makeText(context, "Có lỗi xảy ra !", Toast.LENGTH_LONG).show();
//                        }
                    } catch (Exception e) {
                        Timber.e(e.getMessage());
                    }
                } else {
                    Toast.makeText(context, context.getResources().getString(R.string.cannot_get_data), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Token> call, Throwable t) {

            }
        });
    }
}
