package com.saleskit.cbbank.features.appointment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.view.TimePickerView;
import com.saleskit.cbbank.R;
import com.saleskit.cbbank.features.news.OnItemClicklistener;
import com.saleskit.cbbank.util.AppUtil;

import org.greenrobot.eventbus.EventBus;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class FilterAppointActivity extends AppCompatActivity implements OnItemClicklistener {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_refresh)
    TextView tvRefresh;
    @BindView(R.id.rl_head)
    RelativeLayout rlHead;
    @BindView(R.id.rl_result)
    RelativeLayout rlResult;
    @BindView(R.id.rl_start_date)
    RelativeLayout rlStartDate;
    @BindView(R.id.rl_end_date)
    RelativeLayout rlEndDate;
    @BindView(R.id.bt_search)
    Button btSearch;
    @BindView(R.id.tv_result)
    TextView tvResult;
    @BindView(R.id.tv_start_date)
    TextView tvStartDate;
    @BindView(R.id.tv_end_date)
    TextView tvEndDate;
    private String startDate = "";
    private String endDate = "";
    private int resultStatus = -1;
    private TimePickerView pvPickStartDate;
    private String formatStartDate;
    private TimePickerView pvPickEndDate;
    private String formatEndDate;
    private ArrayList<String> statusList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_appoint);
        ButterKnife.bind(this);
        statusList.add("Chưa có kết quả");
        statusList.add("Hẹn gặp lại");
        statusList.add("Đồng ý gặp");
        statusList.add("Hủy bỏ");
        statusList.add("Tất cả");
        setupPickerStartDate();
        setupPickerEndDate();
        setupUi();
    }

    private void setupPickerEndDate() {
        pvPickEndDate = new TimePickerBuilder(this, (date, v) -> {
            System.out.println(date);
            @SuppressLint("SimpleDateFormat") DateFormat dateFormatView = new SimpleDateFormat("dd 'thg' MM',' yyyy");
            @SuppressLint("SimpleDateFormat") DateFormat dateFormatText = new SimpleDateFormat("yyyy-MM-dd");
            formatEndDate = dateFormatText.format(date);
            endDate = formatEndDate + "T" + "00:00:00.000" + "Z";
            tvEndDate.setText(dateFormatView.format(date));
        }).isCenterLabel(false).setContentTextSize(21).setTitleText(getResources().getString(R.string.date_time)).build();
    }

    private void setupPickerStartDate() {
        pvPickStartDate = new TimePickerBuilder(this, (date, v) -> {
            System.out.println(date);
            @SuppressLint("SimpleDateFormat") DateFormat dateFormatView = new SimpleDateFormat("dd 'thg' MM',' yyyy");
            @SuppressLint("SimpleDateFormat") DateFormat dateFormatText = new SimpleDateFormat("yyyy-MM-dd");
            formatStartDate = dateFormatText.format(date);
            startDate = formatStartDate + "T" + "00:00:00.000" + "Z";
            tvStartDate.setText(dateFormatView.format(date));
        }).isCenterLabel(false).setContentTextSize(21).setTitleText(getResources().getString(R.string.date_time)).build();
    }

    private void setupUi() {
        rlStartDate.setOnClickListener(v -> pvPickStartDate.show());

        rlEndDate.setOnClickListener(v -> pvPickEndDate.show());

        rlResult.setOnClickListener(v -> {
            AppUtil.getDialogwithID(this, statusList, getString(R.string.status), this, 3).show();

        });

        tvRefresh.setOnClickListener(v -> {
            startDate = "";
            endDate = "";
            resultStatus = -1;
            tvEndDate.setText("Chưa chọn");
            tvStartDate.setText("Chưa chọn");
            tvResult.setText("Chưa chọn");
        });
        ivBack.setOnClickListener(v -> {
            finish();
        });
        btSearch.setOnClickListener(v -> {

            if (!TextUtils.isEmpty(endDate) && TextUtils.isEmpty(startDate)) {
                Toast.makeText(this, "Nhập ngày bắt đầu", Toast.LENGTH_SHORT).show();
                return;
            }
            if (!TextUtils.isEmpty(startDate) && TextUtils.isEmpty(endDate)) {
                Toast.makeText(this, "Nhập ngày kết thúc", Toast.LENGTH_SHORT).show();
                return;
            }
            if (!TextUtils.isEmpty(startDate) && !TextUtils.isEmpty(endDate)) {
                if (!AppUtil.compareBiggerDate(startDate, endDate)) {
                    Toast.makeText(this, "Ngày bắt đầu phải trước ngày kết thúc", Toast.LENGTH_SHORT).show();
                    return;
                }
            }

//            if (resultStatus == -1) {
//                Toast.makeText(this, "Chọn trạng thái cuộc hẹn", Toast.LENGTH_SHORT).show();
//                return;
//            }
            EventBus.getDefault().post(new FilterEvent(formatStartDate, formatEndDate, resultStatus));
            finish();
        });


    }

    @Override
    public void onItemClick(int position) {
        resultStatus = position;
        tvResult.setText(statusList.get(position));
    }
}
