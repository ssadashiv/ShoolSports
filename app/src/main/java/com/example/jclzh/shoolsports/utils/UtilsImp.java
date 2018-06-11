package com.example.jclzh.shoolsports.utils;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.util.Log;

import com.example.jclzh.shoolsports.model.Application.ApplicationDate;
import com.example.jclzh.shoolsports.model.Application.AppApplication;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by lzh on 2018/5/27.
 */

public class UtilsImp {

    private static SharedPreferences.Editor edit;
    private static SharedPreferences sharedPreferences;

    public final static int TYPE_TAKE_PHOTO = 1;//Uri获取类型判断

    public final static int CODE_TAKE_PHOTO = 1;//相机RequestCode


    /**
     * Sp存储数据
     *
     * @param k
     * @param v
     */
    public static void spput(String k, Object v) {

        sharedPreferences = AppApplication.getContext().getSharedPreferences(ApplicationDate.APPSPNAME, Context.MODE_PRIVATE);
        edit = sharedPreferences.edit();

        if (v instanceof String) {
            edit.putString(k, (String) v);
        } else if (v instanceof Integer) {
            edit.putInt(k, (Integer) v);
        } else if (v instanceof Boolean) {
            edit.putBoolean(k, (Boolean) v);
        } else if (v instanceof Float) {
            edit.putFloat(k, (Float) v);
        } else if (v instanceof Long) {
            edit.putLong(k, (Long) v);
        } else {
            Log.e("工具类", "spput: " + "您的储存格式value无法识别格式");
        }
        edit.commit();
    }

    /**
     * SP获取数据
     *
     * @param k
     * @param v
     * @return
     */
    public static Object spget(String k, Object v) {
        sharedPreferences = AppApplication.getContext().getSharedPreferences(ApplicationDate.APPSPNAME, Context.MODE_PRIVATE);

        if (v instanceof String) {
            return sharedPreferences.getString(k, (String) v);
        } else if (v instanceof Integer) {
            return sharedPreferences.getInt(k, (Integer) v);
        } else if (v instanceof Boolean) {
            return sharedPreferences.getBoolean(k, (Boolean) v);
        } else if (v instanceof Float) {
            return sharedPreferences.getFloat(k, (Float) v);
        } else if (v instanceof Long) {
            return sharedPreferences.getLong(k, (Long) v);
        } else {
            Log.e("工具类", "spget: " + "您的获取SP格式value无法识别格式");
            return null;
        }

    }


    /**
     * 获取当前的时间  （Long类型）
     *
     * @return
     */
    public static Long getnowtimelong() {
        return System.currentTimeMillis();
    }


    /**
     * 获取当前的时间（String 类型）
     */
    public static String getnowtimestring() {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日    HH:mm:ss     ");
        Date curDate = new Date(System.currentTimeMillis());
        return formatter.format(curDate);
    }


    /**
     * 获取当前的时间自定义格式(String 类型)
     */
    public static String gettimeofstring(String style) {

        SimpleDateFormat formatter = new SimpleDateFormat(style);
        Date curDate = new Date(System.currentTimeMillis());
        return formatter.format(curDate);

    }


    /**
     * 获取IP
     */
    public static String getip() {
        return ApplicationDate.IP;
    }


    /**
     * 获取端口
     */
    public static int getport() {
        return ApplicationDate.PORT;
    }

    /**
     * 拍摄相机文件的Uri
     */
    public static Uri getMediaFileUri(int type) {
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "相册名字");
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                return null;
            }
        }
        //创建Media File
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File mediaFile;
        if (type == TYPE_TAKE_PHOTO) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator + "IMG_" + timeStamp + ".jpg");
        } else {
            return null;
        }
        return Uri.fromFile(mediaFile);
    }

    /**
     * 版本24以上 拍摄相机文件的Uri
     */
    public static Uri get24MediaFileUri(int type) {
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "相册名字");
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                return null;
            }
        }
        //创建Media File
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File mediaFile;
        if (type == TYPE_TAKE_PHOTO) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator + "IMG_" + timeStamp + ".jpg");
        } else {
            return null;
        }
        return FileProvider.getUriForFile(AppApplication.getContext(), AppApplication.getContext().getPackageName() + ".fileprovider", mediaFile);
    }

    /**
     *
     * 在权限获取后，判断SDK版本，然后进行相应操作，如下：
     public void startXiangji() {
     if (Build.VERSION.SDK_INT >= 24) {
     Intent takeIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
     Uri photoUri = get24MediaFileUri(TYPE_TAKE_PHOTO);
     takeIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
     startActivityForResult(takeIntent, CODE_TAKE_PHOTO);
     } else {
     Intent takeIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
     Uri photoUri = getMediaFileUri(TYPE_TAKE_PHOTO);
     takeIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
     startActivityForResult(takeIntent, CODE_TAKE_PHOTO);
     }
     }
     */

    /**
     *
     *拍照完毕后，获取返回数据显示照片，api24以后，需要转换输入流来获取Bitmap，而输入流的获取需要通过getContentResolvrer.openInputStream()来获取，具体代码如下：
     *
     * @Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    switch (requestCode) {
    case CODE_TAKE_PHOTO:
    if (resultCode == RESULT_OK) {
    if (data != null) {
    if (data.hasExtra("data")) {
    Log.i("URI", "data is not null");
    Bitmap bitmap = data.getParcelableExtra("data");
    imageView.setImageBitmap(bitmap);//imageView即为当前页面需要展示照片的控件，可替换
    }
    } else {
    Log.i("URI", "Data is null");
    if (Build.VERSION.SDK_INT >= 24){
    Bitmap bitmap = null;
    try {
    bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(photoUri));
    } catch (FileNotFoundException e) {
    e.printStackTrace();
    }
    imageView.setImageBitmap(bitmap);
    }else {
    Bitmap bitmap = BitmapFactory.decodeFile(photoUri.getPath());
    imageView.setImageBitmap(bitmap);
    }
    }
    }
    break;
    }
    }
     */


    /**
     * 选择照片
     * @return 照片路劲
     */
    public static String getPicPath(Context context, Intent intent) {
        Uri selectImageUri = intent.getData();
        String[] filePathColumn = {MediaStore.Images.Media.DATA};
        Cursor cursor = context.getContentResolver().query(selectImageUri, filePathColumn, null, null, null);
        cursor.moveToFirst();
        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
        String picturePath = cursor.getString(columnIndex);
        cursor.close();
        return picturePath;
    }


}
