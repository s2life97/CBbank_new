package com.saleskit.cbbank.features.appointment;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.view.TimePickerView;
import com.saleskit.cbbank.Constants;
import com.saleskit.cbbank.R;
import com.saleskit.cbbank.features.account.APIWebservices;
import com.saleskit.cbbank.features.account.Appointment;
import com.saleskit.cbbank.features.account.AppointmentDetail;
import com.saleskit.cbbank.features.account.AppointmentResponse;
import com.saleskit.cbbank.features.account.MessageBean;
import com.saleskit.cbbank.features.base.BaseActivity;
import com.saleskit.cbbank.features.database.HawkHelper;
import com.saleskit.cbbank.injection.component.ActivityComponent;
import com.saleskit.cbbank.util.AppUtil;
import com.saleskit.cbbank.util.NetworkUtil;
import com.saleskit.cbbank.util.PermissionUtil;
import com.saleskit.cbbank.util.rx.netmodel.Customer;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class AddApointmentActivity extends BaseActivity {
    @BindView(R.id.btnBack)
    ImageView btnBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.actionBar)
    RelativeLayout actionBar;
    @BindView(R.id.tv_name)
    ClearableEditText tvName;
    @BindView(R.id.iv_identify)
    ClearableEditText ivIdentify;
    @BindView(R.id.tv_address)
    ClearableEditText tvAddress;
    @BindView(R.id.tv_phone)
    ClearableEditText tvPhone;
    @BindView(R.id.tv_email)
    ClearableEditText tvEmail;
    @BindView(R.id.et_product)
    ClearableEditText etProduct;
    @BindView(R.id.tv_time_new)
    TextView tvTimeNew;
    @BindView(R.id.bt_time_pick)
    LinearLayout btTimePick;
    @BindView(R.id.tv_date_time)
    TextView tvDateTime;
    @BindView(R.id.bt_date_time)
    LinearLayout btDateTime;
    @BindView(R.id.bt_save)
    Button btSave;
    @BindView(R.id.et_note_appoint)
    ClearableEditText etNoteAppoint;
    private int appointId;
    private String token;
    private long currentDate;
    private String birtDay;
    private TimePickerView pvPickTime, pvPickDate;
    private String currentDateText, currentTimeText;
    private Appointment.DataBean appointment;
    private String imgagePath;
    private Customer.DataBean customer;
    private int appointmentId = 0;
    private boolean isEdit = false;
    private List<SavedAppointModel> savedAppointModelList = new ArrayList<>();
    private SavedAppointModel savedAppointModel;
    private boolean isRead = true;
    private String notificationId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_apointment);
        ButterKnife.bind(this);
        token = HawkHelper.getInstance(this).getToken();
        Intent intent = getIntent();
        String type = intent.getStringExtra(Constants.SEE_DETAIL);

        if (type.equals(Constants.FROM_NOTI)) {
            tvName.setFocusable(false);
            tvAddress.setFocusable(false);
            tvPhone.setFocusable(false);
            tvEmail.setFocusable(false);
            etProduct.setFocusable(false);
            ivIdentify.setFocusable(false);
            etNoteAppoint.setFocusable(false);
            tvTitle.setText("Chi tiết cuộc hẹn");
            btSave.setVisibility(View.GONE);
            String appointId = intent.getStringExtra(Constants.APPOINT_ID);
            getDetailAppoint(appointId);
            isRead = intent.getBooleanExtra(Constants.READ_NOTI, true);
            if (!isRead) {
                notificationId = intent.getStringExtra(Constants.NOTIFICATION_ID);
                readNoti(Integer.valueOf(notificationId));
            }
        } else {
            appointment = (Appointment.DataBean) intent.getSerializableExtra(Constants.APPOINT_ID);
            customer = (Customer.DataBean) intent.getSerializableExtra(Constants.CUSTOMER_ID);
            Timber.e(" cus" + customer + " type " + type);
            if (appointment != null) {
                appointmentId = appointment.getAppointmentId();
            }
            setupPickerTime();
            setupPickerDate();
            switch (type) {
                case Constants.EDIT_DETAIL:
                    getInfoAppoint(appointment);
                    isEdit = true;
                    tvTitle.setText("Sửa cuộc hẹn");
                    setupAddView(false);
                    break;
                case Constants.CUSTOMER_TYPE:
                    setupView(true);
                    tvEmail.setText(customer.getEmail());
                    tvName.setText(customer.getLastName() + " " + customer.getFirstName());
                    tvAddress.setText(customer.getAddress());
                    tvPhone.setText(customer.getPhoneNumber());
                    ivIdentify.setText(customer.getIdentityNumber());
                    setupAddView(true);
                    break;
                case Constants.SEE_DETAIL:
//                showLoading();
                    setupView(false);
                    etNoteAppoint.setFocusable(false);
                    tvTitle.setText("Chi tiết cuộc hẹn");
                    btSave.setVisibility(View.GONE);
                    break;
                default:
                    tvTitle.setText("Thêm cuộc hẹn");
                    if (customer != null) {
                        String name = customer.getLastName() + " " + customer.getFirstName();
                        tvName.setText(name);
                        tvEmail.setText(customer.getEmail());
                        tvPhone.setText(customer.getPhoneNumber());
                        ivIdentify.setText(customer.getIdentityNumber());
                        tvAddress.setText(customer.getAddress());
                        tvName.setFocusable(false);
                        ivIdentify.setFocusable(false);
                        tvAddress.setFocusable(false);
                        tvPhone.setFocusable(false);
                        tvEmail.setFocusable(false);
                    }
                    setupAddView(true);
                    break;
            }
        }
        btnBack.setOnClickListener(v -> {
            finish();
        });

    }

    private void readNoti(int id) {
        APIWebservices apiInterface = NetworkUtil.getCBclient(this).create(APIWebservices.class);
        Call<MessageBean> call = apiInterface.readNoti(token, id);
        call.enqueue(new Callback<MessageBean>() {
            @Override
            public void onResponse(Call<MessageBean> call, Response<MessageBean> response) {
                if (response.code() == 200) {
                } else {
                    Toast.makeText(AddApointmentActivity.this, "Có lỗi xảy ra!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MessageBean> call, Throwable t) {

            }
        });
    }

    private void getDetailAppoint(String appointId) {
        APIWebservices apiInterface = NetworkUtil.getCBclient(this).create(APIWebservices.class);
        Call<AppointmentDetail> call = apiInterface.getAppointDetail(token, appointId);
        call.enqueue(new Callback<AppointmentDetail>() {
            @Override
            public void onResponse(Call<AppointmentDetail> call, Response<AppointmentDetail> response) {
                if (response.code() == 200) {
                    if (response.body() != null && response.body().getData() != null) {
                        AppointmentDetail.DataBean dataBean = response.body().getData();
                        tvName.setText(dataBean.getCustomerName());
                        ivIdentify.setText(dataBean.getIdentityNumber());
                        tvAddress.setText(dataBean.getAppointmentAddress());
                        tvPhone.setText(dataBean.getPhoneNumber());
                        tvEmail.setText(dataBean.getEmail());
                        if (TextUtils.isEmpty(dataBean.getEmail())) {

                        } else {
                            tvEmail.setText(dataBean.getEmail());
                        }

                        if (TextUtils.isEmpty(dataBean.getExpectedProduct())) {

                        } else {
                            etProduct.setText(dataBean.getExpectedProduct());
                        }

                        try {
                            @SuppressLint("SimpleDateFormat") Date appointmentTime = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                                    .parse(dataBean.getAppointmentTime());

                            @SuppressLint("SimpleDateFormat") DateFormat dateFormatView = new SimpleDateFormat("dd 'thg' MM',' yyyy");
                            @SuppressLint("SimpleDateFormat") DateFormat timeFormatView = new SimpleDateFormat("hh:mm aa");
                            @SuppressLint("SimpleDateFormat") DateFormat dateFormatText = new SimpleDateFormat("yyyy-MM-dd");
                            @SuppressLint("SimpleDateFormat") DateFormat timeFormatText = new SimpleDateFormat("HH:mm:ss.SSS");

                            currentDateText = dateFormatText.format(appointmentTime);
                            currentTimeText = timeFormatText.format(appointmentTime);
                            tvDateTime.setText(dateFormatView.format(appointmentTime));
                            tvTimeNew.setText(timeFormatView.format(appointmentTime));
                            if (TextUtils.isEmpty(dataBean.getResultDescription())) {
                                etNoteAppoint.setText("");
                            } else {
                                etNoteAppoint.setText(dataBean.getResultDescription());
                            }
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    } else {
                        Toast.makeText(AddApointmentActivity.this, "Không tìm thấy thông tin cuộc hẹn!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(AddApointmentActivity.this, "Không thể tìm thấy thông tin cuộc hẹn!", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

            @Override
            public void onFailure(Call<AppointmentDetail> call, Throwable t) {

            }
        });
    }

    private void setupAddView(boolean canUpdate) {
        if (!canUpdate) {
            btSave.setText("Sửa");
            tvTitle.setText("Sửa cuộc hẹn");
        } else {
            btSave.setText("Lưu lại");
        }
        btSave.setOnClickListener(v -> {
            String name = tvName.getText().toString().trim();
            String lastName = "";
            String firstName = "";
            if (name.split("\\w+").length > 1) {
                firstName = name.substring(name.lastIndexOf(" ") + 1);
                lastName = name.substring(0, name.lastIndexOf(' '));
            } else {
                lastName = name;
            }
            if (TextUtils.isEmpty(tvName.getText().toString().trim())) {
//                tvName.setError("Vui lòng nhập họ và tên đệm");
                Toast.makeText(this, "Vui lòng nhập họ và tên", Toast.LENGTH_SHORT).show();
                tvName.requestFocus();
                return;
            }
            if (TextUtils.isEmpty(tvAddress.getText().toString())) {
                Toast.makeText(this, "Địa chỉ không được bỏ trống!", Toast.LENGTH_SHORT).show();
//                tvAddress.setError(getResources().getString(R.string.file_must_not_empty));
                tvAddress.requestFocus();
                return;
            }
//            if(TextUtils.isEmpty(tvPhone.getText().toString())){
//                Toast.makeText(this, "Bạn chưa nhập số điện thoại!", Toast.LENGTH_SHORT).show();
//                tvPhone.requestFocus();
//                return;
//            }
//            if (TextUtils.isEmpty(tvEmail.getText().toString().trim())) {
//                Toast.makeText(this, "Địa chỉ email không được bỏ trống!", Toast.LENGTH_SHORT).show();
////                tvEmail.setError(getResources().getString(R.string.file_must_not_empty));
//                tvEmail.requestFocus();
//                return;
//            }
//
//            if (!Patterns.EMAIL_ADDRESS.matcher(tvEmail.getText().toString().trim()).matches()) {
////                Toast.makeText(AddApointmentActivity.this, getResources().getString(R.string.email_not_format), Toast.LENGTH_SHORT).show();
//                Toast.makeText(this, getResources().getString(R.string.email_not_format), Toast.LENGTH_SHORT).show();
////                tvEmail.setError(getResources().getString(R.string.email_not_format));
//                tvEmail.requestFocus();
//                return;
//            }
            if (TextUtils.isEmpty(tvPhone.getText().toString())) {
//                Toast.makeText(this, "Bạn chưa nhập email!", Toast.LENGTH_SHORT).show();
                Toast.makeText(this, "Số điện thoại không được bỏ trống!", Toast.LENGTH_SHORT).show();
                tvPhone.requestFocus();
                return;
            }
            if (!Patterns.PHONE.matcher(tvPhone.getText().toString()).matches() || !tvPhone.getText().toString().startsWith("0")
                    || tvPhone.getText().length() < 9) {
//                Toast.makeText(AddApointmentActivity.this, "Số điện thoại không đúng định dạng!", Toast.LENGTH_SHORT).show();
                Toast.makeText(this, "Số điện thoại không đúng định dạng!", Toast.LENGTH_SHORT).show();
                tvPhone.requestFocus();
                return;
            }
            if (TextUtils.isEmpty(tvTimeNew.getText().toString())) {
//                Toast.makeText(this, "Bạn chưa nhập email!", Toast.LENGTH_SHORT).show();
                Toast.makeText(this, "Giờ hẹn không được bỏ trống!", Toast.LENGTH_SHORT).show();
                tvTimeNew.requestFocus();
                return;
            }
            if (TextUtils.isEmpty(tvDateTime.getText().toString())) {
//                Toast.makeText(this, "Bạn chưa nhập email!", Toast.LENGTH_SHORT).show();
                Toast.makeText(this, "Ngày hẹn không được bỏ trống!", Toast.LENGTH_SHORT).show();
                tvDateTime.requestFocus();
                return;
            }

            String dateTime = currentDateText + "T" + currentTimeText + "Z";
            String dateTime1 = currentDateText + "T" + currentTimeText;
            Date currentDateFormat = Calendar.getInstance().getTime();
            String dateCompare = AppUtil.formatCompareAppoint(dateTime1);
            Date strDate = null;
            @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy'T'HH:mm");
            if (isEdit) {
                try {
                    strDate = sdf.parse(dateCompare);
//                currentDateFormat = sdf.parse(currentTime);
                    if (currentDateFormat.after(strDate)) {
                        Toast.makeText(this, "Vui lòng chọn ngày hẹn, giờ hẹn sau thời điểm hiện tại", Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            if (appointmentId == 0) {
                APIWebservices apiInterface = NetworkUtil.getCBclient(this).create(APIWebservices.class);
                Call<AppointmentResponse> call = apiInterface.addNewAppointment(token,
                        new AppointmentInsertUpdateModel(appointmentId,
                                tvName.getText().toString(),
                                ivIdentify.getText().toString(),
                                tvPhone.getText().toString(),
                                tvEmail.getText().toString().trim(),
                                tvAddress.getText().toString(),
                                etProduct.getText().toString(),
                                dateTime,
                                0, etNoteAppoint.getText().toString()
                                , customer == null ? "" : String.valueOf(customer.getCustomerId())
                        ));
                call.enqueue(new Callback<AppointmentResponse>() {
                    @Override
                    public void onResponse(Call<AppointmentResponse> call, Response<AppointmentResponse> response) {
                        if (response.code() == 200) {
                            AppointmentResponse appointmentResponse = response.body();
                            String appointId = appointmentResponse.getData();
//                            savedAppointModel = new SavedAppointModel(appointId, dateTime, tvName.getText().toString());
//                            savedAppointModelList.add(savedAppointModel);
//                            HawkHelper.getInstance(AddApointmentActivity.this).saveAppointmentModels(savedAppointModelList);
//                        Intent i = new Intent(AddApointmentActivity.this, AppointListActivity.class);
//                        startActivity(i);
                            Toast.makeText(AddApointmentActivity.this, "Thành công!", Toast.LENGTH_SHORT).show();
                            finish();
                            overridePendingTransition(R.anim.animation_in_screen, R.anim.animation_outt_screen);
//                            saveToCalendar(savedAppointModel);
                        } else {
                            Toast.makeText(AddApointmentActivity.this, "Kiểm tra lại thông tin đã nhập!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<AppointmentResponse> call, Throwable throwable) {

                    }
                });
            } else {
                APIWebservices apiInterface = NetworkUtil.getCBclient(this).create(APIWebservices.class);
                Call<AppointmentResponse> call = apiInterface.updateInfoAppoint(token,
                        new AppointmentInsertUpdateModel(appointmentId,
                                tvName.getText().toString(),
                                ivIdentify.getText().toString(),
                                tvPhone.getText().toString(),
                                tvEmail.getText().toString(),
                                tvAddress.getText().toString(),
                                etProduct.getText().toString(),
                                dateTime,
                                0, etNoteAppoint.getText().toString()
                                , customer == null ? "" : String.valueOf(customer.getCustomerId())
                        ));
                call.enqueue(new Callback<AppointmentResponse>() {
                    @Override
                    public void onResponse(Call<AppointmentResponse> call, Response<AppointmentResponse> response) {
                        if (response.code() == 200) {
                            Toast.makeText(AddApointmentActivity.this, "Thành công!", Toast.LENGTH_SHORT).show();
//                        Intent i = new Intent(AddApointmentActivity.this, AppointListActivity.class);
//                        startActivity(i);
                            finish();
                            overridePendingTransition(R.anim.animation_in_screen, R.anim.animation_outt_screen);
                        } else {
                            Toast.makeText(AddApointmentActivity.this, "Kiểm tra lại thông tin đã nhập!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<AppointmentResponse> call, Throwable throwable) {

                    }
                });
            }

        });

        btDateTime.setOnClickListener(v -> {
            AppUtil.hideKeyboard(this);
            if (currentDateText == null)
                pvPickDate.setDate(toCalendar(new Date(Calendar.getInstance().getTime().getTime())));
            pvPickDate.show();
        });

        btTimePick.setOnClickListener(v -> {
            AppUtil.hideKeyboard(this);
            if (currentTimeText == null)
                pvPickDate.setDate(toCalendar(new Date(Calendar.getInstance().getTime().getTime())));
            pvPickTime.show();
        });

    }

    private void saveToCalendar(SavedAppointModel savedAppointModel) {
        final ContentValues event = new ContentValues();
        event.put(CalendarContract.Events.CALENDAR_ID, 1);

        event.put(CalendarContract.Events.TITLE, savedAppointModel.getUserName());
        event.put(CalendarContract.Events.DESCRIPTION, savedAppointModel.getUserName());

        Date dateFromAppointment = new Date(
                Integer.parseInt(savedAppointModel.getDateTime().substring(0, 4)) - 1900,
                Integer.parseInt(savedAppointModel.getDateTime().substring(5, 7)) - 1,
                Integer.parseInt(savedAppointModel.getDateTime().substring(8, 10)),
                Integer.parseInt(savedAppointModel.getDateTime().substring(11, 13)),
                Integer.parseInt(savedAppointModel.getDateTime().substring(14, 16)),
                Integer.parseInt(savedAppointModel.getDateTime().substring(17, 19))
        );

        event.put(CalendarContract.Events.DTSTART, dateFromAppointment.getTime());
        event.put(CalendarContract.Events.DTEND, dateFromAppointment.getTime() + 60 * 60 * 1000);
        event.put(CalendarContract.Events.HAS_ALARM, 1); // 0 for false, 1 for true

        String timeZone = TimeZone.getDefault().getID();
        event.put(CalendarContract.Events.EVENT_TIMEZONE, timeZone);

        Uri baseUri = Uri.parse("content://com.android.calendar/events");

        if (PermissionUtil.hasPermissionCalendar(this)) {
            Uri url = getContentResolver().insert(baseUri, event);

            long eventId = Long.parseLong(url.getLastPathSegment());

            ContentValues reminder = new ContentValues();
            reminder.put(CalendarContract.Reminders.EVENT_ID, eventId);
            reminder.put(CalendarContract.Reminders.MINUTES, 30);
            reminder.put(CalendarContract.Reminders.METHOD, CalendarContract.Reminders.METHOD_ALERT);
            getContentResolver().insert(CalendarContract.Reminders.CONTENT_URI, reminder);


        } else {
            PermissionUtil.requestPermissionCalendar(this, 1);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (PermissionUtil.hasPermissionCalendar(this)) {
                saveToCalendar(savedAppointModel);
            } else {
                Toast.makeText(this, "Please allow permissions to download this file", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void setupPickerDate() {
        pvPickDate = new TimePickerBuilder(AddApointmentActivity.this, (date, v) -> {
            System.out.println(date);
            @SuppressLint("SimpleDateFormat") DateFormat dateFormatView = new SimpleDateFormat("dd 'thg' MM',' yyyy");
            @SuppressLint("SimpleDateFormat") DateFormat dateFormatText = new SimpleDateFormat("yyyy-MM-dd");
            currentDateText = dateFormatText.format(date);
            tvDateTime.setText(dateFormatView.format(date));
        }).isCenterLabel(false).setContentTextSize(21).setTitleText(getResources().getString(R.string.date_time)).build();
    }

    private void setupPickerTime() {
        pvPickTime = new TimePickerBuilder(AddApointmentActivity.this, (time, v) -> {
            System.out.println(time);
            @SuppressLint("SimpleDateFormat") DateFormat timeFormatView = new SimpleDateFormat("hh:mm aa");
            @SuppressLint("SimpleDateFormat") DateFormat timeFormatText = new SimpleDateFormat("HH:mm:ss.SSS");
            currentTimeText = timeFormatText.format(time);
            tvTimeNew.setText(timeFormatView.format(time));
        })
                .setTitleText("Chọn giờ")
                .setType(new boolean[]{false, false, false, true, true, false})
                .build();
    }

    private int getTime(Date date) {
        return 0;
    }

    @SuppressLint("SimpleDateFormat")
    private void setupView(boolean isFromCustomer) {
        tvName.setFocusable(false);
        ivIdentify.setFocusable(false);
        tvAddress.setFocusable(false);
        tvPhone.setFocusable(false);
        tvEmail.setFocusable(false);
        if (!isFromCustomer) {
            etProduct.setFocusable(false);
        }

        getInfoAppoint(appointment);
    }

    private void getInfoAppoint(Appointment.DataBean appointment) {
        if (appointment != null) {
            tvName.setText(appointment.getCustomerName());
            ivIdentify.setText(appointment.getIdentityNumber());
            tvAddress.setText(appointment.getAppointmentAddress());
            tvPhone.setText(appointment.getPhoneNumber());
            tvEmail.setText(appointment.getEmail());
            if (TextUtils.isEmpty(appointment.getEmail())) {

            } else {
                tvEmail.setText(appointment.getEmail());
            }

            if (TextUtils.isEmpty(appointment.getExpectedProduct())) {

            } else {
                etProduct.setText(appointment.getExpectedProduct());
            }

            try {
                @SuppressLint("SimpleDateFormat") Date appointmentTime = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                        .parse(appointment.getAppointmentTime());

                @SuppressLint("SimpleDateFormat") DateFormat dateFormatView = new SimpleDateFormat("dd 'thg' MM',' yyyy");
                @SuppressLint("SimpleDateFormat") DateFormat timeFormatView = new SimpleDateFormat("hh:mm aa");
                @SuppressLint("SimpleDateFormat") DateFormat dateFormatText = new SimpleDateFormat("yyyy-MM-dd");
                @SuppressLint("SimpleDateFormat") DateFormat timeFormatText = new SimpleDateFormat("HH:mm:ss.SSS");

                currentDateText = dateFormatText.format(appointmentTime);
                currentTimeText = timeFormatText.format(appointmentTime);
                tvDateTime.setText(dateFormatView.format(appointmentTime));
                tvTimeNew.setText(timeFormatView.format(appointmentTime));
                etNoteAppoint.setText(appointment.getResultDescription());
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }


    public static Calendar toCalendar(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_add_apointment;
    }

    @Override
    protected void inject(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    @Override
    protected void attachView() {

    }

    @Override
    protected void detachPresenter() {

    }
}
