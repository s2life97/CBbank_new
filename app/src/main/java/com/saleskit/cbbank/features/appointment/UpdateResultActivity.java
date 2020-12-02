package com.saleskit.cbbank.features.appointment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.view.TimePickerView;
import com.saleskit.cbbank.Constants;
import com.saleskit.cbbank.R;
import com.saleskit.cbbank.features.account.APIWebservices;
import com.saleskit.cbbank.features.account.Appointment;
import com.saleskit.cbbank.features.account.AppointmentDetail;
import com.saleskit.cbbank.features.account.AppointmentResponse;
import com.saleskit.cbbank.features.base.BaseActivity;
import com.saleskit.cbbank.features.database.HawkHelper;
import com.saleskit.cbbank.features.news.OnItemClicklistener;
import com.saleskit.cbbank.injection.component.ActivityComponent;
import com.saleskit.cbbank.util.AppUtil;
import com.saleskit.cbbank.util.NetworkUtil;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class UpdateResultActivity extends BaseActivity implements AppointView, UpdateResultView {
    private static final int CAMERA_CODE = 1002;
    private static final int LIBRARY_CODE = 1001;
    private static final int REMEET_CODE = 1;
    private static final int ACCEPT_MEET = 2;
    private static final int CANCEL = 3;

    @Inject
    AddAppointPresenter addAppointPresenter;
    @BindView(R.id.btnBack)
    ImageView btnBack;
    @BindView(R.id.actionBar)
    RelativeLayout actionBar;
    @BindView(R.id.rl_status)
    RelativeLayout rlStatus;
    @BindView(R.id.ll_1)
    LinearLayout ll1;
    @BindView(R.id.rv_image)
    RecyclerView rvImage;
    @BindView(R.id.bt_save)
    Button btSave;
    @BindView(R.id.ll_status)
    LinearLayout llStatus;
    @BindView(R.id.tv_time_meeting)
    TextView tvTimeMeeting;
    @BindView(R.id.tv_status)
    TextView tvStatus;
    @BindView(R.id.ll_time_mettting)
    LinearLayout llTimeMettting;
    @BindView(R.id.ll_date_meeting)
    LinearLayout llDateMeeting;
    @BindView(R.id.ll_result)
    LinearLayout llResult;
    @BindView(R.id.fl_add)
    FloatingActionButton flAdd;
    @BindView(R.id.et_result_des)
    ClearableEditText etResultDes;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.ll_image)
    LinearLayout llImage;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.iv_next)
    ImageView ivNext;
    private List<String> images = new ArrayList<>();
    private ImageAdapter imagaApdapter;
    private BottomSheetDialog pictureDialog;
    private String imgagePath;
    private String token;
    private String value = "Đồng ý gặp";
    private int id = -1;
    private OnItemClicklistener onItemClicklistener;
    private AppointmentDetail.DataBean appointmentInsertUpdateModel;
    private int actionType = 0; // 0 for open gallery, 1 for open camera

    List<AppointmentDetail.DataBean.AppointmentImagesBean> appointmentImagesBeanList = new ArrayList<>();
    private TimePickerView pvPickDate;
    private String currentDateText;
    private TimePickerView pvPickTime;
    private String currentTimeText;
    private String dateTime;
    private String currentTime, currentDate;
    private boolean isReMeet = false;
    private int appointmentID;
    private boolean canEdit = false;
    private boolean onlySee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_result);
        ButterKnife.bind(this);
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);

        }
        token = HawkHelper.getInstance(this).getToken();
        setupPictureDialog();
        btnBack.setOnClickListener(v -> finish());
        setupPickerTime();
        setupPickerDate();
        setupUI();

        Intent intent = getIntent();
        appointmentID = intent.getIntExtra(Constants.APPOINT_ID, 0);
        onlySee = intent.getBooleanExtra(Constants.EDIT_DETAIL, false);
        if (onlySee) {
            disableView();
        }
        getData(String.valueOf(appointmentID));

        llDateMeeting.setOnClickListener(v -> {
            AppUtil.hideKeyboard(this);
            pvPickDate.show();
        });

        llTimeMettting.setOnClickListener(v -> {
            AppUtil.hideKeyboard(this);
            pvPickTime.show();
        });


        flAdd.setOnClickListener(v -> {
            pictureDialog.show();
        });

        btSave.setOnClickListener(v -> {
            Toast.makeText(this, "Đang Lưu...", Toast.LENGTH_SHORT).show();
            if (isReMeet) {
                if (TextUtils.isEmpty(tvTimeMeeting.getText()) || TextUtils.isEmpty(tvDate.getText())) {
                    Toast.makeText(this, "Bạn chưa điền đủ ngày giờ!", Toast.LENGTH_SHORT).show();
                    return;
                }
            }

            appointmentInsertUpdateModel.setAppointmentImages(appointmentImagesBeanList);
//            appointmentInsertUpdateModel.setResultStatus(id);
            if (isReMeet) {
                appointmentInsertUpdateModel.setAppointmentTime(currentDateText + "T" + currentTimeText + "Z");
            } else {
                appointmentInsertUpdateModel.setAppointmentTime(dateTime);
            }
            APIWebservices apiInterface = NetworkUtil.getCBclient(this).create(APIWebservices.class);
            Call<AppointmentResponse> call = apiInterface.updateAppointment(token, new AppointmentDetail.DataBean(appointmentID,
                    appointmentInsertUpdateModel.getCustomerName(),
                    appointmentInsertUpdateModel.getIdentityNumber(),
                    appointmentInsertUpdateModel.getPhoneNumber(),
                    appointmentInsertUpdateModel.getEmail(),
                    appointmentInsertUpdateModel.getAppointmentAddress(),
                    appointmentInsertUpdateModel.getExpectedProduct(),
                    appointmentInsertUpdateModel.getDescription(),
                    appointmentInsertUpdateModel.getAppointmentTime(),
                    appointmentInsertUpdateModel.getResultStatus(),
                    etResultDes.getText().toString(),
                    0,
                    appointmentImagesBeanList
            ));
            call.enqueue(new Callback<AppointmentResponse>() {
                @Override
                public void onResponse(Call<AppointmentResponse> call, Response<AppointmentResponse> response) {
                    if (response.code() == 200) {
                        Toast.makeText(UpdateResultActivity.this, "Cập nhật cuộc hẹn thành công", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(UpdateResultActivity.this, "Có lỗi xảy ra", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<AppointmentResponse> call, Throwable t) {

                }
            });
        });


    }

    private void getData(String idAppointment) {
        showLoading();
        APIWebservices apiInterface = NetworkUtil.getCBclient(this).create(APIWebservices.class);
        Call<AppointmentDetail> call = apiInterface.getAppointDetail(token, idAppointment);
        call.enqueue(new Callback<AppointmentDetail>() {
            @Override
            public void onResponse(Call<AppointmentDetail> call, Response<AppointmentDetail> response) {
                if (response.code() == 200) {
                    hideLoading();
                    AppointmentDetail.DataBean dataBean = response.body().getData();
                    appointmentInsertUpdateModel = dataBean;
                    dateTime = appointmentInsertUpdateModel.getAppointmentTime();
                    String status = "";
                    switch (appointmentInsertUpdateModel.getResultStatus()) {
                        case 0: {
                            status = getResources().getString(R.string.none);
                            canEdit = true;
                            if (!onlySee) {
                                llStatus.setOnClickListener(v -> {
                                    Intent i = new Intent(UpdateResultActivity.this, OptionActivity.class);
                                    i.putExtra(Constants.CATEGORY_TYPE, Constants.APPOINT_STATUS);
                                    i.putExtra(Constants.CATEGORY_VALUE, id);
                                    startActivity(i);
                                });
                            }
                            break;
                        }
                        case REMEET_CODE: {
                            status = getResources().getString(R.string.remeet);
                            disableView();
                            llDateMeeting.setVisibility(View.VISIBLE);
                            llTimeMettting.setVisibility(View.VISIBLE);
                            String dateTime = dataBean.getAppointmentTime();
                            String date = AppUtil.format(dateTime);
                            String time = AppUtil.fomatTime(dateTime);
                            tvTimeMeeting.setText(time);
                            tvDate.setText(date);
                            break;
                        }
                        case ACCEPT_MEET: {
                            disableView();
                            status = getResources().getString(R.string.accpet_meet);
                            llDateMeeting.setVisibility(View.GONE);
                            llTimeMettting.setVisibility(View.GONE);
                            break;
                        }
                        case CANCEL: {
                            disableView();
                            status = getResources().getString(R.string.cancel_meet);

                            llDateMeeting.setVisibility(View.GONE);
                            llTimeMettting.setVisibility(View.GONE);
                            break;
                        }
                    }
                    if (TextUtils.isEmpty(dataBean.getResultDescription())) {
//                        etResultDes.setText(getResources().getString(R.string.empty));
                    } else {
                        etResultDes.setText(dataBean.getResultDescription());
                    }
                    id = appointmentInsertUpdateModel.getResultStatus();
                    Timber.e("status ID " + id);
                    tvStatus.setText(status);
                    List<AppointmentDetail.DataBean.AppointmentImagesBean> imagesBeans = response.body().getData().getAppointmentImages();
                    for (AppointmentDetail.DataBean.AppointmentImagesBean imageBean : imagesBeans) {
                        images.add(imageBean.getPath());
                        appointmentImagesBeanList.add(new AppointmentDetail.DataBean.AppointmentImagesBean(
                                0,
                                appointmentInsertUpdateModel.getAppointmentId(),
                                imageBean.getFileName(),
                                imageBean.getPath(),
                                imageBean.getExtension(),
                                imageBean.getSize()
                        ));
                    }
                    onItemClicklistener = position -> {
                        images.remove(images.get(position));

                    };
                    imagaApdapter = new ImageAdapter(images, UpdateResultActivity.this, UpdateResultActivity.this, canEdit);
                    imagaApdapter.notifyDataSetChanged();
                    rvImage.setAdapter(imagaApdapter);
                }
            }

            @Override
            public void onFailure(Call<AppointmentDetail> call, Throwable throwable) {

            }
        });
    }

    private void disableView() {
        etResultDes.setFocusable(false);
        flAdd.hide();
        btSave.setVisibility(View.GONE);
        llStatus.setFocusable(false);
        llTimeMettting.setEnabled(false);
        llDateMeeting.setEnabled(false);
        ivNext.setVisibility(View.GONE);

    }

    private void setupPickerDate() {
        pvPickDate = new TimePickerBuilder(this, (date, v) -> {
            System.out.println(date);
            @SuppressLint("SimpleDateFormat") DateFormat dateFormatView = new SimpleDateFormat("dd 'thg' MM',' yyyy");
            @SuppressLint("SimpleDateFormat") DateFormat dateFormatText = new SimpleDateFormat("yyyy-MM-dd");
            currentDateText = dateFormatText.format(date);
            tvDate.setText(dateFormatView.format(date));
        }).isCenterLabel(false).setContentTextSize(21).setTitleText(getResources().getString(R.string.date_time)).build();
    }

    private void setupPickerTime() {
        pvPickTime = new TimePickerBuilder(this, (time, v) -> {
            System.out.println(time);
            @SuppressLint("SimpleDateFormat") DateFormat timeFormatView = new SimpleDateFormat("hh:mm aa");
            @SuppressLint("SimpleDateFormat") DateFormat timeFormatText = new SimpleDateFormat("HH:mm:ss.SSS");
            currentTimeText = timeFormatText.format(time);
            tvTimeMeeting.setText(timeFormatView.format(time));
        })
                .setTitleText("Chọn giờ")
                .setType(new boolean[]{false, false, false, true, true, false})
                .build();
    }

    private void setupPictureDialog() {
        pictureDialog = new BottomSheetDialog(this);
        pictureDialog.setContentView(R.layout.dialog_picture);
        LinearLayout btLibrary = pictureDialog.findViewById(R.id.bt_library);
        LinearLayout btCamera = pictureDialog.findViewById(R.id.bt_camera);
        LinearLayout btClose = pictureDialog.findViewById(R.id.bt_close);
        btLibrary.setOnClickListener(v -> {
            actionType = 0;
            if (AppUtil.isStoragePermissionGranted(this)) {
                galleryIntent();
            }
            pictureDialog.dismiss();
        });
        btCamera.setOnClickListener(v -> {
            openCamera();
            pictureDialog.dismiss();
        });
        btClose.setOnClickListener(v -> {
            pictureDialog.dismiss();
        });
    }

    private void openCamera() {
        actionType = 1;
        if (AppUtil.isStoragePermissionGranted(this)) {
            dispatchTakePictureIntent();
        }
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            File photoFile = null;
            photoFile = createImageFile();
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        getString(R.string.app_file_provider),
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, CAMERA_CODE);
            }
        }
    }

    private File createImageFile() {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = null;
        try {
            image = File.createTempFile(
                    imageFileName,  /* prefix */
                    ".jpg",         /* suffix */
                    storageDir      /* directory */
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
        imgagePath = image.getAbsolutePath();
        return image;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LIBRARY_CODE) {
            if (AppUtil.isStoragePermissionGranted(this)) {
                if (actionType == 0) {
                    galleryIntent();
                } else if (actionType == 1) {
                    dispatchTakePictureIntent();
                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == LIBRARY_CODE) {
            if (resultCode == RESULT_OK) {
                showLoading();
                Uri uri = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
//                    Glide.with(UpdateResultActivity.this).load(bitmap)
//                            .apply(RequestOptions.circleCropTransform())
//                            .apply(new RequestOptions().placeholder(R.drawable.placeholder)
////                                    .diskCacheStrategy(DiskCacheStrategy.ALL)).into(iv);
//
//                    File file = new File(uri.getPath());
//                    final String[] split = file.getPath().split(":");//split the path.
//                    String galleryPath = split[1];
                    String path = AppUtil.getRealPathFromUri(this, uri);
                    ExifInterface exif = null;
                    try {
                        exif = new ExifInterface(path);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                            ExifInterface.ORIENTATION_UNDEFINED);
                    Bitmap newBit = AppUtil.rotateBitmap(bitmap, orientation);
                    addAppointPresenter.postImageToServer(this, newBit, token);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else if (requestCode == CAMERA_CODE) {
            if (resultCode == RESULT_OK) {
                showLoading();
                Timber.e(" camera" + imgagePath);
                Bitmap bitmap = BitmapFactory.decodeFile(imgagePath);
                ExifInterface exif = null;
                try {
                    exif = new ExifInterface(imgagePath);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                        ExifInterface.ORIENTATION_UNDEFINED);
                Bitmap newBit = AppUtil.rotateBitmap(bitmap, orientation);
//                String image = AppUtil.BitMapToString(newBit);
//                images.add(newBit);
//                OnItemClicklistener onItemClicklistener = position -> images.remove(bitmap);
//                imagaApdapter = new ImageAdapter(images, this, this);
//                imagaApdapter.notifyDataSetChanged();
//                rvImage.setAdapter(imagaApdapter);
                addAppointPresenter.postImageToServer(this, newBit, token);

            }
        }
    }

    private void galleryIntent() {

        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, LIBRARY_CODE);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_update_result;
    }

    @Override
    protected void inject(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    @Override
    protected void attachView() {
        addAppointPresenter.attachView(this);
    }

    @Override
    protected void detachPresenter() {
        addAppointPresenter.detachView();
    }

    private void setupUI() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        rvImage.setLayoutManager(linearLayoutManager);
    }

    @Subscribe
    public void onReceiveValue(CategoryEvent categoryEvent) {
        id = categoryEvent.id;
        appointmentInsertUpdateModel.setResultStatus(id);
        value = categoryEvent.value;
        tvStatus.setText(value);
        switch (id) {
            case CANCEL:
                tvTitle.setText("Lý do");
                isReMeet = false;
                llDateMeeting.setVisibility(View.GONE);
                llTimeMettting.setVisibility(View.GONE);
                break;
            case ACCEPT_MEET:
                tvTitle.setText("Kết quả");
                isReMeet = false;
                llDateMeeting.setVisibility(View.GONE);
                llTimeMettting.setVisibility(View.GONE);
                break;
            case REMEET_CODE:
                tvTitle.setText("Ghi chú");
                isReMeet = true;
                llDateMeeting.setVisibility(View.VISIBLE);
                llTimeMettting.setVisibility(View.VISIBLE);
                break;
            default:
                tvTitle.setText("Kết quả");
                isReMeet = false;
                llDateMeeting.setVisibility(View.GONE);
                llTimeMettting.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    public void showResult(Imgage.DataBean image) {
        images.add(image.getUploadFileModels().get(0).getPath());
        onItemClicklistener = position -> {
            images.remove(images.get(position));

        };
        imagaApdapter = new ImageAdapter(images, this, this, canEdit);
        imagaApdapter.notifyDataSetChanged();
        rvImage.setAdapter(imagaApdapter);

        appointmentImagesBeanList.add(new AppointmentDetail.DataBean.AppointmentImagesBean(
                0,
                appointmentInsertUpdateModel.getAppointmentId(),
                image.getUploadFileModels().get(0).getFileName(),
                image.getUploadFileModels().get(0).getPath(),
                image.getUploadFileModels().get(0).getExtension(),
                image.getUploadFileModels().get(0).getSize()
        ));

        if (image != null) {
            Toast.makeText(this, "Đăng ảnh thành công!", Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public void showNearestAppoint(List<Appointment.DataBean> list) {

    }

    @Override
    public void showALlAppoint(List<Appointment.DataBean> list, int pages) {

    }

    @Override
    public void showListSearchedAppoint(List<Appointment.DataBean> list) {

    }

    @Override
    public void hideSwipe() {

    }

    @Override
    public void clearList() {

    }

    @Override
    public void setLoaded() {

    }

    @Override
    public void showFilterAppoint(List<FilterAppointment.DataBean.AppointmentBelongPeriodsBean> list, int pages, FilterAppointment.DataBean dataBean, int totalItems) {

    }

    @Override
    public void onDelete(int position) {
        images.remove(position);
        appointmentImagesBeanList.remove(position);
        imagaApdapter.notifyDataSetChanged();
    }
}
