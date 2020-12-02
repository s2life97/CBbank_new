package com.saleskit.cbbank.features.home;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import com.saleskit.cbbank.Constants;
import com.saleskit.cbbank.R;
import com.saleskit.cbbank.util.AppUtil;
import com.saleskit.cbbank.util.PermissionUtil;
import com.koushikdutta.ion.Ion;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class ReportDetailActivity extends AppCompatActivity {

    @BindView(R.id.btnBack)
    ImageView btnBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.actionBar)
    RelativeLayout actionBar;
    @BindView(R.id.wv_main)
    WebView wvMain;
    @BindView(R.id.bt_down_report)
    Button btDownReport;
    private int PERMISSION_REQUEST_CODE = 0;
    private ProgressDialog progressDialog;
    private ProgressDialog progressLoadDialog;
    private String url;
    private String onlUrl;
    private String fileName;
    private String wvOnlUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_detail);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        url = intent.getStringExtra(Constants.REPORT_ID);
        onlUrl = Constants.BASE_ONLINE_URL + url;
        wvOnlUrl = "http://docs.google.com/gview?embedded=true&url=" + Constants.BASE_ONLINE_URL + url;
        String[] names = onlUrl.split("-");
        fileName = names[names.length - 1];
        progressDialog = new ProgressDialog(this);
        progressLoadDialog = new ProgressDialog(this);
        progressLoadDialog.setMessage("Tải thông tin báo cáo ...");
        progressDialog.setMessage("Tải xuống ...");
        setupUi();
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void setupUi() {
        progressLoadDialog.show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(progressLoadDialog.isShowing()){
                    progressLoadDialog.hide();
                    progressLoadDialog.dismiss();
                }
            }
        }, 7000);
        btDownReport.setOnClickListener(v -> {
            if (PermissionUtil.hasPermissions(ReportDetailActivity.this)) {
                downloadSelectedFile();
            } else {
                PermissionUtil.requestPermissions(ReportDetailActivity.this, PERMISSION_REQUEST_CODE);
            }
        });

        btnBack.setOnClickListener(v -> finish());
        wvMain.setWebViewClient(
                new SSLTolerentWebViewClient()
        );
        wvMain.setInitialScale(getScale());
        wvMain.setOverScrollMode(View.OVER_SCROLL_NEVER);
        wvMain.getSettings().setJavaScriptEnabled(true);
        wvMain.getSettings().setBuiltInZoomControls(true);
        wvMain.getSettings().setDisplayZoomControls(false);
        wvMain.getSettings().setUseWideViewPort(true);
        wvMain.loadUrl(wvOnlUrl);
        wvMain.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                progressLoadDialog.hide();
                progressLoadDialog.dismiss();
            }
        });
    }

    private void downloadSelectedFile() {
        progressDialog.show();
        AppUtil.deleteExistFile(AppUtil.getSavePath() + fileName);
        String filePath = AppUtil.getSavePath();
        Ion.with(this)
                .load(onlUrl)
                .progressDialog(progressDialog)
                .progress((downloaded, total) -> {
                })
                .write(new File(filePath, fileName))
                .setCallback((e, file) -> {
                    sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(file)));
                    progressDialog.hide();
                    Toast.makeText(ReportDetailActivity.this, getResources().getString(R.string.success_download), Toast.LENGTH_SHORT).show();
                    openXLS(file);
                });
    }

    private int getScale() {
        Display display = ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        int width = display.getWidth();
        Double val = new Double(width) / new Double(550);
        val = val * 100d;
        return val.intValue();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (PermissionUtil.hasPermissions(this)) {
                downloadSelectedFile();
            } else {
                Toast.makeText(this, "Please allow permissions to download this file", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void openXLS(final File file) {
//        AppUtil.deleteExistFile(AppUtil.getSavePath()+ fileName);
//        String filePath = AppUtil.getSavePath();
//        File file = new File(filePath);
        Uri uri;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            uri = FileProvider.getUriForFile(ReportDetailActivity.this, getString(R.string.app_file_provider), file);
        } else {
            uri = Uri.fromFile(file);
        }

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.setDataAndType(uri, "application/vnd.ms-excel");
        intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        try {
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, "Không tìm thấy úng dụng để mở file báo cáo!", Toast.LENGTH_SHORT).show();
        }
    }

}
