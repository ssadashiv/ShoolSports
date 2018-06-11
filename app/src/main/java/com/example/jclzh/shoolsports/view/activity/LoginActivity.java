package com.example.jclzh.shoolsports.view.activity;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jclzh.shoolsports.R;
import com.example.jclzh.shoolsports.model.Application.ApplicationDate;
import com.example.jclzh.shoolsports.utils.MLog;
import com.example.jclzh.shoolsports.utils.Net.NetListener;
import com.example.jclzh.shoolsports.utils.Net.NetUtils;
import com.example.jclzh.shoolsports.utils.ToastUtil;
import com.example.jclzh.shoolsports.utils.UtilsImp;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity implements LoaderCallbacks<Cursor> {

    /**
     * Id to identity READ_CONTACTS permission request.
     */
    private static final int REQUEST_READ_CONTACTS = 0;

    /**
     * A dummy authentication store containing known user names and passwords.
     */
    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "foo@example.com:hello", "bar@example.com:world"
    };


    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;
    private AlertDialog loginingadialog;
    private CheckBox mCbIspass;
    private CheckBox mCbIslogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.activity_login);
        initView();
        // Set up the login form.
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
        populateAutoComplete();

        mPasswordView = (EditText) findViewById(R.id.password);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
        inituserdata();
    }


    private void populateAutoComplete() {
        if (!mayRequestContacts()) {
            return;
        }

        getLoaderManager().initLoader(0, null, this);
    }

    private boolean mayRequestContacts() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        if (checkSelfPermission(READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        if (shouldShowRequestPermissionRationale(READ_CONTACTS)) {
            Snackbar.make(mEmailView, R.string.permission_rationale, Snackbar.LENGTH_INDEFINITE)
                    .setAction(android.R.string.ok, new OnClickListener() {
                        @Override
                        @TargetApi(Build.VERSION_CODES.M)
                        public void onClick(View v) {
                            requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
                        }
                    });
        } else {
            requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
        }
        return false;
    }

    /**
     * Callback received when a permissions request has been completed.
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == REQUEST_READ_CONTACTS) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                populateAutoComplete();
            }
        }
    }


    /**
     * app验证后登录的逻辑
     */
    private void attemptLogin() {


        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // 网络请求进行登录
            showProgress(true);
            MLog.i("登录邮箱:", mEmailView.getText().toString());
            MLog.i("登录密码:", mPasswordView.getText().toString());

            Map<String, String> map = new HashMap();
            map.put("study_code", mEmailView.getText().toString());
            map.put("pwd", mPasswordView.getText().toString());
            NetUtils.jsonget(ApplicationDate.API_LOGIN_URL, map, new NetListener() {
                @Override
                public void yeslistener(JSONObject jsonObject) {
                    showProgress(false);
                    try {
                        //todo 控制用户登录
                        if (jsonObject.getInt("status") == 1) {
                            UtilsImp.spput("user",jsonObject.toString());
                                    gotohome();

                        } else {
                            ToastUtil.show(LoginActivity.this, "密码错误请重试", 1 * 1000);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void errorlistener(String error) {
                    showProgress(false);
                    ToastUtil.show(LoginActivity.this, "当前网络连接失败", 1 * 1000);
                }
            });


//            new Handler().postDelayed(new Runnable() {
//                @Override
//                public void run() {
//
//                    startActivity(new Intent(LoginActivity.this,HomeActivity.class));
//                }
//            },1000);
        }
    }

    /**
     * 登录成功进入到主界面
     */
    private void gotohome() {
        boolean checked_islogin = mCbIslogin.isChecked();
        boolean checked_ispass = mCbIspass.isChecked();

        if (checked_islogin){
            UtilsImp.spput("checked_islogin",true);
        }else {
            UtilsImp.spput("checked_islogin",false);

        }

        if (checked_ispass){

            UtilsImp.spput("checked_ispass",true);
            UtilsImp.spput("ck_user",mEmailView.getText().toString().trim());
            UtilsImp.spput("ck_pass",mPasswordView.getText().toString().trim());


        }else {

            UtilsImp.spput("checked_ispass",false);
            UtilsImp.spput("ck_user","");
            UtilsImp.spput("ck_pass","");

        }

        startActivity(new Intent(LoginActivity.this, HomeActivity.class));
    }

    /**
     * 判断是否登陆过记录密码
     */
    private void inituserdata() {

        if ((boolean)UtilsImp.spget("checked_ispass",false)){
            mEmailView.setText((String)UtilsImp.spget("ck_user",""));
            mPasswordView.setText((String)UtilsImp.spget("ck_pass",""));
            mCbIspass.setChecked(true);
        }else {
            mCbIspass.setChecked(false);
        }

    }


    /**
     * 判断用户名是否符合规则
     *
     * @param email
     * @return
     */
    private boolean isEmailValid(String email) {

        if (email.length() > 5) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断密码是否符合规则
     *
     * @param password
     * @return
     */
    private boolean isPasswordValid(String password) {
        return password.length() > 4;
    }

    /**
     * 网络请求过程中加载Ptgress
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {


        if (show) {

            AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
            View view = getLayoutInflater().inflate(R.layout.logininglayout, null);
            ImageView dialogimgview = view.findViewById(R.id.ivProgress);
            //设置旋转动画
            RotateAnimation rotate = new RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            LinearInterpolator lin = new LinearInterpolator();
            rotate.setInterpolator(lin);
            rotate.setDuration(2000);//设置动画持续周期
            rotate.setRepeatCount(-1);//设置重复次数
            rotate.setFillAfter(true);//动画执行完后是否停留在执行完的状态
            rotate.setStartOffset(10);//执行前的等待时间
            dialogimgview.setAnimation(rotate);

            builder.setView(view);
            loginingadialog = builder.show();
            loginingadialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.touming));//设置透明的背景

        } else {
            loginingadialog.dismiss();
        }


    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(this,
                // Retrieve data rows for the device user's 'profile' contact.
                Uri.withAppendedPath(ContactsContract.Profile.CONTENT_URI,
                        ContactsContract.Contacts.Data.CONTENT_DIRECTORY), ProfileQuery.PROJECTION,

                // Select only email addresses.
                ContactsContract.Contacts.Data.MIMETYPE +
                        " = ?", new String[]{ContactsContract.CommonDataKinds.Email
                .CONTENT_ITEM_TYPE},

                // Show primary email addresses first. Note that there won't be
                // a primary email address if the user hasn't specified one.
                ContactsContract.Contacts.Data.IS_PRIMARY + " DESC");
    }

    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
        List<String> emails = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            emails.add(cursor.getString(ProfileQuery.ADDRESS));
            cursor.moveToNext();
        }

        addEmailsToAutoComplete(emails);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader) {

    }

    private void addEmailsToAutoComplete(List<String> emailAddressCollection) {
        //Create adapter to tell the AutoCompleteTextView what to show in its dropdown list.
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(LoginActivity.this,
                        android.R.layout.simple_dropdown_item_1line, emailAddressCollection);

        mEmailView.setAdapter(adapter);
    }

    private void initView() {
        mCbIspass = (CheckBox) findViewById(R.id.cb_ispass);
        mCbIslogin = (CheckBox) findViewById(R.id.cb_islogin);
    }


    private interface ProfileQuery {
        String[] PROJECTION = {
                ContactsContract.CommonDataKinds.Email.ADDRESS,
                ContactsContract.CommonDataKinds.Email.IS_PRIMARY,
        };

        int ADDRESS = 0;
    }


}

