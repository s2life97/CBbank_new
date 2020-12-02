package com.saleskit.cbbank.features.personal;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
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

public class UserLookupActivity extends AppCompatActivity {
    @BindView(R.id.tv_title)
    TextView tvTitle;
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
    private String firstUser = "";
    private String token;
    private int id;
    private List<String> ids = new ArrayList<>();
    private String employeeFullName;
    private String employeeId;
    private String departmentId;
    private String userId = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_lookup);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        token = HawkHelper.getInstance(this).getToken();
        Intent i = getIntent();
        id = i.getIntExtra(Constants.ALLOW_ID, 0);
        ids.add(String.valueOf(id));
        employeeFullName = i.getStringExtra(Constants.USER_FULL_NAME);
        departmentId = i.getStringExtra(Constants.DEPARTMENT_ID);
        employeeId = i.getStringExtra(Constants.USER_ID);
        getAllowPerson(id);
        btBack.setOnClickListener(v -> finish());

        //Phân công
        btComplete.setOnClickListener(v -> {
            if (TextUtils.isEmpty(userId)) {
                Toast.makeText(this, "Chưa chọn cán bộ nào!", Toast.LENGTH_SHORT).show();
                return;
            }
            APIWebservices apiInterface = NetworkUtil.getCBclient(this).create(APIWebservices.class);
            Call<MessageBean> call = apiInterface.rePickUserLookup(token, new CustomerID("", userId, ids));
            call.enqueue(new Callback<MessageBean>() {
                @Override
                public void onResponse(Call<MessageBean> call, Response<MessageBean> response) {
                    if (response.code() == 200) {
                        finish();
                        Toast.makeText(UserLookupActivity.this, "Phân công tra cứu thành công!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(UserLookupActivity.this, "Có lỗi xảy ra!", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<MessageBean> call, Throwable t) {

                }
            });
        });

        btPickPerson.setOnClickListener(v -> {
            if (!TextUtils.isEmpty(firstUser)) {
                Intent i1 = new Intent(UserLookupActivity.this, OptionActivity.class);
                i1.putExtra(Constants.CATEGORY_TYPE, Constants.EMPLOYEE);
                i1.putExtra(Constants.USER_ID, firstUser);
                i1.putExtra(Constants.CATEGORY_VALUE, -1);
                i1.putExtra(Constants.DEPARTMENT_ID, departmentId);
                startActivity(i1);
                btPickPerson.setEnabled(false);
                new Handler().postDelayed(() -> btPickPerson.setEnabled(true), 1000);
            }
        });

    }

    private void getAllowPerson(int id) {
        APIWebservices apiInterface = NetworkUtil.getCBclient(this).create(APIWebservices.class);
        Call<CustomerAllow> call = apiInterface.getCustomerAllow(token, String.valueOf(id));
        call.enqueue(new Callback<CustomerAllow>() {
            @Override
            public void onResponse(Call<CustomerAllow> call, Response<CustomerAllow> response) {
                if (response.code() == 200) {
                    CustomerAllow.DataBean dataBean = response.body().getData();
                    tvPerson.setText(dataBean.getSearchUserFullName());
                    firstUser = dataBean.getSearchUserId();
                }
            }

            @Override
            public void onFailure(Call<CustomerAllow> call, Throwable t) {
            }
        });
    }

    @Subscribe
    public void onReceiveValue(CategoryEvent categoryEvent) {
        userId = categoryEvent.categoryId;
        etRateOfDebt.setText(categoryEvent.value);
    }
}
