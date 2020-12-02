package com.saleskit.cbbank.features.account;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.widget.Toast;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import static android.os.Environment.*;

public class DownLoadTask {
    private static final String TAG = "Download Task";
    private Context context;

    private String downloadUrl, downloadFileName;
    private ProgressDialog progressDialog;
    public DownLoadTask(Context context, String downloadUrl, String fileName) {
        this.context = context;
        this.downloadUrl = downloadUrl;
        downloadFileName = fileName;
        Log.e(TAG, downloadFileName);
        //Start Downloading Task
        new DownloadingTask().execute();
    }
    private class DownloadingTask extends AsyncTask<Void, Void, Void> {
        File apkStorage = null;
        File outputFile = null;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(context);
            progressDialog.setMessage("Đang tải xuống...");
            progressDialog.setCancelable(true);
            progressDialog.show();
        }
        @Override
        protected void onPostExecute(Void result) {
            try {
                if (outputFile != null) {
                    progressDialog.dismiss();
                    final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                    alertDialogBuilder.setTitle("Tải thành công!");
                    alertDialogBuilder.setMessage("File đã được lưu trong thư mục CBBank");
                    alertDialogBuilder.setCancelable(false);
                    alertDialogBuilder.setNegativeButton("Xem sau", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                        }
                    });
                    alertDialogBuilder.setPositiveButton("Mở ngay",new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                            Intent intent = new Intent(Intent.ACTION_VIEW);
                            intent.setDataAndType(Uri.parse(Environment.getExternalStorageDirectory().getAbsolutePath()+"/CBBank/"+downloadFileName), "application/pdf");
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            try {
                                context.startActivity(intent);
                            }
                            catch (ActivityNotFoundException e) {
                                Toast.makeText(context,
                                        "Hãy cài ứng dụng đọc file FDF trước!",
                                        Toast.LENGTH_SHORT).show();
                            }
//                            File pdfFile = new File(getExternalStorageDirectory() + "/CBBank/" + downloadFileName);
//                            Uri path = Uri.fromFile(pdfFile);
//                            Intent pdfIntent = new Intent(Intent.ACTION_VIEW);
//                            pdfIntent.setDataAndType(path, "application/pdf");
//                            pdfIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                            try{
//                                context.startActivity(pdfIntent);
//                            } catch (Exception e)
//                            {
//                                //Chua cai ung dung doc file pdf
////                                Intent i= new Intent(context, ReadPdf.class);
////                                i.putExtra("url", downloadUrl);
////                                context.startActivity(i);
//
//                                Log.d("aaa", e.toString());
//                                Toast.makeText(context, "Hãy cài ứng dụng đọc file PDF trước!" + e.toString(), Toast.LENGTH_LONG).show();
//                            }
                        }
                    });
                    alertDialogBuilder.show();
                } else {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                        }
                    }, 3000);
                }
            } catch (Exception e) {
                e.printStackTrace();
                    new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                    }
                }, 3000);
            }
            super.onPostExecute(result);
        }
        @Override
        protected Void doInBackground(Void... arg0) {
            try {
                URL url = new URL(downloadUrl);
                HttpURLConnection c = (HttpURLConnection) url.openConnection();
                c.setRequestMethod("GET");
                c.connect();
                if (c.getResponseCode() != HttpURLConnection.HTTP_OK) {
                    Log.e(TAG, "Server returned HTTP " + c.getResponseCode()
                            + " " + c.getResponseMessage());
                }
                if (new CheckForSDCard().isSDCardPresent()) {
                    apkStorage = new File(getExternalStorageDirectory() + "/CBBank/");
                } else
                if (!apkStorage.exists()) {
                    apkStorage.mkdir();
                }
                outputFile = new File(apkStorage, downloadFileName);//Create Output file in Main File
                if (!outputFile.exists()) {
                    outputFile.createNewFile();
                    Log.e(TAG, "Da Tao File");
                }
                FileOutputStream fos = new FileOutputStream(outputFile);//Get OutputStream for NewFile Location

                InputStream is = c.getInputStream();//Get InputStream for connection

                byte[] buffer = new byte[1024];//Set buffer type
                int len1 = 0;//init length
                while ((len1 = is.read(buffer)) != -1) {
                    fos.write(buffer, 0, len1);//Write new file
                }
                //Close all connection after doing task
                fos.close();
                is.close();
            } catch (Exception e) {
                //Read exception if something went wrong
                e.printStackTrace();
                outputFile = null;
                Log.e(TAG, "Download Error Exception " + e.getMessage());
            }

            return null;
        }
    }
}