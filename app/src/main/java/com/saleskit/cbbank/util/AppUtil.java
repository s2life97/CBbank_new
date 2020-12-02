package com.saleskit.cbbank.util;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateUtils;
import android.util.Base64;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.loader.content.CursorLoader;

import com.bigkoo.pickerview.adapter.ArrayWheelAdapter;
import com.saleskit.cbbank.R;
import com.saleskit.cbbank.features.news.OnItemClicklistener;
import com.contrarywind.view.WheelView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import timber.log.Timber;

import static android.content.Context.INPUT_METHOD_SERVICE;

public class AppUtil {
    public static void openFragment(FragmentManager fragmentManager, int layoutID,
                                    Fragment fragment, String tag) {

        boolean fragmentPopped = fragmentManager.popBackStackImmediate(tag, 0);

        if (!fragmentPopped) { //fragment not in back stack, create it.
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(layoutID, fragment, tag);
            fragmentTransaction.addToBackStack(tag);
            fragmentTransaction.commit();
        }
    }

    public static void setBackgroundForView(View view, Context context, int drawable) {
        final int version = Build.VERSION.SDK_INT;
        if (version >= Build.VERSION_CODES.JELLY_BEAN) {
            view.setBackground(ContextCompat.getDrawable(context, drawable));
        } else {
            view.getResources().getDrawable(drawable);
        }
    }

    public static String convertTimeUTCToLocalTimeZone(String stringTime) {
        try {
            DateFormat utcFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            utcFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            Date date = utcFormat.parse(stringTime);
            DateFormat pstFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            TimeZone vnTimeZone = java.util.TimeZone.getDefault();
            pstFormat.setTimeZone(vnTimeZone);
            stringTime = pstFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return stringTime;
    }

    public static String getTimeAgo(String stringTimeDate) {
        long time = 0;

        try {
            DateFormat pstFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            pstFormat.setTimeZone(java.util.TimeZone.getDefault());
            Date date = pstFormat.parse(convertTimeUTCToLocalTimeZone(stringTimeDate));
            time = date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long now = System.currentTimeMillis();

        String ago =
                (String) DateUtils.getRelativeTimeSpanString(time, now, DateUtils.MINUTE_IN_MILLIS);
        return ago;
    }

    public static AlertDialog getDialogwithID(Activity activity, ArrayList<String> mOptionsItems, String name, final OnItemClicklistener onItemClickListener, int curentPos) {
        final View dialogView = activity.getLayoutInflater().inflate(R.layout.wheel_dialog, null);
        TextView txt_cancel = dialogView.findViewById(R.id.txt_cancel);
        TextView txt_done = dialogView.findViewById(R.id.txt_done);
        TextView title = dialogView.findViewById(R.id.title);
        title.setText(name);
        final WheelView wheelView = dialogView.findViewById(R.id.wheelview);
        wheelView.setCyclic(false);
        wheelView.setCurrentItem(curentPos);
        ArrayList<String> listString = new ArrayList<>(mOptionsItems);
        wheelView.setAdapter(new ArrayWheelAdapter<>(listString));
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(activity, R.style.MyDialogThemeWeel)
                .setView(dialogView)
                .setCancelable(true);
        final AlertDialog dialog = alertDialog.create();
        Window window;
        window = dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        WindowManager.LayoutParams wmlp = dialog.getWindow()
                .getAttributes();
        wmlp.width = android.view.WindowManager.LayoutParams.MATCH_PARENT;
        wmlp.height = android.view.WindowManager.LayoutParams.WRAP_CONTENT;
        wlp.gravity = Gravity.BOTTOM;
        wlp.flags &= ~WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        window.setAttributes(wlp);
//        BottomSheetDialog dialog = new BottomSheetDialog(activity);
//        dialog.setContentView(dialogView);
        dialog.show();
        txt_cancel.setOnClickListener(v -> dialog.dismiss());
        txt_done.setOnClickListener(v -> {
            dialog.dismiss();
            onItemClickListener.onItemClick(wheelView.getCurrentItem());

        });
        return dialog;
    }

    @SuppressLint("SimpleDateFormat")
    public static String formatDateTime(String date) {
        if (date == null)
            return "";
        SimpleDateFormat spf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date newDate = null;
        try {
            newDate = spf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        spf = new SimpleDateFormat("dd/MM/yyyy");
        return spf.format(newDate);
    }

    @SuppressLint("SimpleDateFormat")
    public static String fomatTime(String time) {
        if (time == null)
            return "";
        SimpleDateFormat spf1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date newDate1 = null;
        try {
            newDate1 = spf1.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        spf1 = new SimpleDateFormat("HH':'mm a");
        return spf1.format(newDate1);
    }

    @SuppressLint("SimpleDateFormat")
    public static String format(String date) {
        if (date == null)
            return "";
        SimpleDateFormat spf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date newDate = null;
        try {
            newDate = spf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        spf = new SimpleDateFormat("dd 'thg' MM',' yyyy");
        return spf.format(newDate);
    }

    @SuppressLint("SimpleDateFormat")
    public static String formatServerTime(String date) {
        if (date == null)
            return "";
        SimpleDateFormat spf = new SimpleDateFormat("yyyy-MM-dd");
        Date newDate = null;
        try {
            newDate = spf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        spf = new SimpleDateFormat("dd 'thg' MM',' yyyy");
        return spf.format(newDate);
    }

    @SuppressLint("SimpleDateFormat")
    public static String formatCompare(String date) {
        if (date == null)
            return "";
        SimpleDateFormat spf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date newDate = null;
        try {
            newDate = spf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        spf = new SimpleDateFormat("dd/MM/yyyy'T'HH:mm");
        return spf.format(newDate);
    }


    @SuppressLint("SimpleDateFormat")
    public static String formatCompareAppoint(String date) {
        if (date == null)
            return "";
        SimpleDateFormat spf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSS");
        Date newDate = null;
        try {
            newDate = spf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        spf = new SimpleDateFormat("dd/MM/yyyy'T'HH:mm");
        return spf.format(newDate);
    }

    @SuppressLint("SimpleDateFormat")
    public static String formatTimeDate(String date) {
        if (date == null)
            return "";
        SimpleDateFormat spf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date newDate = null;
        try {
            newDate = spf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        spf = new SimpleDateFormat("HH:mm '-' dd 'thg' MM',' yyyy");
        return spf.format(newDate);
    }

    @SuppressLint("SimpleDateFormat")
    public static String fomatTimeDeal(String time) {
        if (time == null)
            return "";
        SimpleDateFormat spf1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date newDate1 = null;
        try {
            newDate1 = spf1.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        spf1 = new SimpleDateFormat("HH':'mm");
        return spf1.format(newDate1);
    }

    public static boolean isStoragePermissionGranted(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(
                    activity,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED
            ) {
                return true;
            } else {
                ActivityCompat.requestPermissions(activity, new String[]{(Manifest.permission.WRITE_EXTERNAL_STORAGE)}, 1001);
                return false;
            }
        } else {
            return true;
        }
    }

    public static Bitmap rotateBitmap(Bitmap bitmap, int orientation) {

        Matrix matrix = new Matrix();
        switch (orientation) {
            case ExifInterface.ORIENTATION_NORMAL:
                return bitmap;
            case ExifInterface.ORIENTATION_FLIP_HORIZONTAL:
                matrix.setScale(-1, 1);
                break;
            case ExifInterface.ORIENTATION_ROTATE_180:
                matrix.setRotate(180);
                break;
            case ExifInterface.ORIENTATION_FLIP_VERTICAL:
                matrix.setRotate(180);
                matrix.postScale(-1, 1);
                break;
            case ExifInterface.ORIENTATION_TRANSPOSE:
                matrix.setRotate(90);
                matrix.postScale(-1, 1);
                break;
            case ExifInterface.ORIENTATION_ROTATE_90:
                matrix.setRotate(90);
                break;
            case ExifInterface.ORIENTATION_TRANSVERSE:
                matrix.setRotate(-90);
                matrix.postScale(-1, 1);
                break;
            case ExifInterface.ORIENTATION_ROTATE_270:
                matrix.setRotate(-90);
                break;
            default:
                return bitmap;
        }
        try {
            Bitmap bmRotated = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
            bitmap.recycle();
            return bmRotated;
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
            return null;
        }

    }

    public static void deleteExistFile(String path) {
        File file = new File(path);
        if (file.exists()) {
            file.delete();
        }
    }

    public static String getSavePath() {
        File folder = new File(Environment.getExternalStorageDirectory() +
                File.separator + "CBBank");
        if (!folder.exists()) {
            folder.mkdirs();
        }
        Timber.e(folder.getAbsolutePath());
        return folder.getAbsolutePath();
    }

    public static String BitMapToString(Bitmap newBit) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        newBit.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] b = baos.toByteArray();
        String temp = Base64.encodeToString(b, Base64.DEFAULT);
        return temp;
    }

    public static Bitmap StringToBitMap(String encodedString) {
        try {
            byte[] encodeByte = Base64.decode(encodedString, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }

    public static void hideKeyboard(Activity context) {
        if (context.getCurrentFocus() != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(context.getCurrentFocus().getWindowToken(), 0);
        }
    }

    public static TextWatcher onTextChangedListener(final EditText editText) {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                boolean userChange = Math.abs(count - before) == 1;
                if (userChange) {
                    editText.removeTextChangedListener(this);
                    try {
                        String value = charSequence
                                .toString()
                                .replace(",", "");

                        if (value.contains(".")) {
                            long beforeDotValue = Long.parseLong(value.split(".")[0]);
                            String beforeDotDisplay = String.format(Locale.US, "%,d", beforeDotValue);
                            String afterDotDisplay = value.split(".")[1];

                            if (afterDotDisplay.length() > 0) {
                                editText.setText(beforeDotDisplay + "." + afterDotDisplay);
                            } else {
                                editText.setText(beforeDotDisplay);
                            }
                        } else {
                            editText.setText(String.format(Locale.US, "%,d", Long.parseLong(value)));
                        }

                        editText.setSelection(editText.getText().length());

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    editText.addTextChangedListener(this);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        };
    }

    public static String convertDate(SimpleDateFormat format, String date) {
        Date myDate = null;
        try {
            myDate = format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat timeFormat = new SimpleDateFormat("dd/MM/yyyy");
        return timeFormat.format(myDate);

    }

    public static void setProgressDrawable(ProgressBar pbMediaProgress, Context context, int drawbale) {
        final int version = Build.VERSION.SDK_INT;
        if (version >= Build.VERSION_CODES.JELLY_BEAN) {
            pbMediaProgress.setProgressDrawable(ContextCompat.getDrawable(context, drawbale));
        } else {
            pbMediaProgress.getResources().getDrawable(drawbale);
        }
    }

    public static boolean compareBiggerDate(String startDate, String endDate) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

            Date date1 = formatter.parse(startDate);
            Date date2 = formatter.parse(endDate);
            if (date2.compareTo(date1) >= 0) {
                return true;
            } else {
                return false;
            }

        } catch (ParseException e1) {
            e1.printStackTrace();
        }
        return false;
    }

    public static String getRealPathFromUri(Context context, Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = {MediaStore.Images.Media.DATA};
            cursor = context.getContentResolver().query(contentUri, proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    @SuppressLint("NewApi")
    public static String getRealPathFromURI_API19(Context context, Uri uri) {
        String filePath = "";
        String wholeID = DocumentsContract.getDocumentId(uri);

        // Split at colon, use second item in the array
        String id = wholeID.split(":")[1];

        String[] column = {MediaStore.Images.Media.DATA};

        // where id is equal to
        String sel = MediaStore.Images.Media._ID + "=?";

        Cursor cursor = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                column, sel, new String[]{id}, null);

        int columnIndex = cursor.getColumnIndex(column[0]);

        if (cursor.moveToFirst()) {
            filePath = cursor.getString(columnIndex);
        }
        cursor.close();
        return filePath;
    }


    @SuppressLint("NewApi")
    public static String getRealPathFromURI_API11to18(Context context, Uri contentUri) {
        String[] proj = {MediaStore.Images.Media.DATA};
        String result = null;

        CursorLoader cursorLoader = new CursorLoader(
                context,
                contentUri, proj, null, null, null);
        Cursor cursor = cursorLoader.loadInBackground();

        if (cursor != null) {
            int column_index =
                    cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            result = cursor.getString(column_index);
        }
        return result;
    }

    public static String getRealPathFromURI_BelowAPI11(Context context, Uri contentUri) {
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = context.getContentResolver().query(contentUri, proj, null, null, null);
        int column_index
                = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }

    public static Date getNewDate(String date) {
        SimpleDateFormat spf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date newDate = null;
        try {
            newDate = spf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return newDate;
    }

    public static String getEnterpriseName(int enterpriseType) {
        switch (enterpriseType) {
            case 5:
                return "Công ty TNHH một thành viên";
            case 1:
                return "DN tư nhân";
            case 2:
                return "Công ty TNHH";
            case 3:
                return "Công ty hợp danh";
            case 4:
                return "Công ty TNHH một thành viên";
        }
        return "";
    }
}
