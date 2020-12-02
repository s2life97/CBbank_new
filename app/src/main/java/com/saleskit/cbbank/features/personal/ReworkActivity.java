package com.saleskit.cbbank.features.personal;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.saleskit.cbbank.Constants;
import com.saleskit.cbbank.R;
import com.saleskit.cbbank.features.account.APIWebservices;
import com.saleskit.cbbank.features.account.CustomerID;
import com.saleskit.cbbank.features.account.MessageBean;
import com.saleskit.cbbank.features.account.MessageJson;
import com.saleskit.cbbank.features.appointment.CategoryEvent;
import com.saleskit.cbbank.features.appointment.OptionActivity;
import com.saleskit.cbbank.features.database.HawkHelper;
import com.saleskit.cbbank.util.NetworkUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReworkActivity extends AppCompatActivity {
    private static final String TAG = "ReworkActivity";
    @BindView(R.id.actionBar)
    RelativeLayout actionBar;
    @BindView(R.id.tv_person)
    TextView tvPerson;
    @BindView(R.id.bt_area)
    LinearLayout btArea;
    @BindView(R.id.et_rate_of_debt)
    TextView etRateOfDebt;
    @BindView(R.id.bt_pick_person)
    LinearLayout btPickPerson;
    @BindView(R.id.bt_back)
    Button btBack;
    @BindView(R.id.bt_complete)
    Button btComplete;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    private String token;
    private int id;
    private String type;
    private String userId, employeeId = "";
    private int customerProfileAllowcationId;
    private int customerProfileId;
    private String name;
    private String departmentId;
    private List<String> ids = new ArrayList<>();
    private String customerId;
    private String employeeFullName;
    private List<String> customerIds = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rework);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        token = HawkHelper.getInstance(this).getToken();
        Intent i = getIntent();
        type = i.getStringExtra(Constants.REWORK_TYPE);
        if (type.equals(Constants.FILE_REWORK)) {
//            getData();
            id = i.getIntExtra(Constants.ALLOW_ID, 0);
            ids.add(String.valueOf(id));
            tvTitle.setText("Phân công lại hồ sơ");
            employeeFullName = i.getStringExtra(Constants.USER_FULL_NAME);
            tvPerson.setText(employeeFullName);
            customerIds.add(String.valueOf(id));
            departmentId = i.getStringExtra(Constants.DEPARTMENT_ID);
            employeeId = i.getStringExtra(Constants.USER_ID);
        } else {
            tvTitle.setText("Phân công lại nhân sự");
            name = i.getStringExtra(Constants.USER_FULL_NAME);
            tvPerson.setText(name);
            employeeId = i.getStringExtra(Constants.USER_ID);
            departmentId = i.getStringExtra(Constants.DEPARTMENT_ID);
            customerIds = getIntent().getExtras().getStringArrayList(Constants.CUSTOMER_ID);
            Log.e(TAG, "onCreate: customr id " + customerId);
        }
        btBack.setOnClickListener(v -> {
            finish();
        });
        btComplete.setOnClickListener(v -> {
            if (TextUtils.isEmpty(userId)) {
                Toast.makeText(this, "Chưa chọn cán bộ phân công!", Toast.LENGTH_SHORT).show();
                return;
            }
            if (type.equals(Constants.FILE_REWORK)) {
                APIWebservices apiInterface = NetworkUtil.getCBclient(this).create(APIWebservices.class);
                Call<MessageBean> call = apiInterface.getRework(token, new CustomerID("",  userId, ids));
                call.enqueue(new Callback<MessageBean>() {
                    @Override
                    public void onResponse(Call<MessageBean> call, Response<MessageBean> response) {
                        if (response.code() == 200) {
                            finish();
                            Toast.makeText(ReworkActivity.this, "Phân công lại hồ sơ thành công!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(ReworkActivity.this, "Có lỗi xảy ra!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<MessageBean> call, Throwable t) {

                    }
                });
            } else {
                Log.e(TAG, "onCreate: list size " + customerIds.size() + customerIds.get(0));
                APIWebservices apiInterface = NetworkUtil.getCBclient(this).create(APIWebservices.class);
                Call<MessageJson> call = apiInterface.reworkEmployee(token, new CustomerID(employeeId, userId, customerIds));
                call.enqueue(new Callback<MessageJson>() {
                    @Override
                    public void onResponse(Call<MessageJson> call, Response<MessageJson> response) {
                        if (response.code() == 200) {
                            finish();
                            Toast.makeText(ReworkActivity.this, "Phân công lại hồ sơ thành công!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(ReworkActivity.this, "Có lỗi xảy ra!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<MessageJson> call, Throwable t) {

                    }
                });
            }
        });

        btPickPerson.setOnClickListener(v -> {
            Intent i1 = new Intent(ReworkActivity.this, OptionActivity.class);
            i1.putExtra(Constants.CATEGORY_TYPE, Constants.EMPLOYEE);
            i1.putExtra(Constants.USER_ID, employeeId);
            i1.putExtra(Constants.CATEGORY_VALUE, -1);
            i1.putExtra(Constants.DEPARTMENT_ID, departmentId);
            startActivity(i1);
            btPickPerson.setEnabled(false);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    btPickPerson.setEnabled(true);
                }
            }, 1000);
        });
    }


    @Subscribe
    public void onReceiveValue(CategoryEvent categoryEvent) {
        userId = categoryEvent.categoryId;
        etRateOfDebt.setText(categoryEvent.value);
    }
}
