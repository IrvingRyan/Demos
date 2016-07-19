package com.irvingryan.okhttpdemo.framework.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public abstract class BaseActivity extends AppCompatActivity {

    public String TAG;
    public static List<BaseActivity> activityList;
    Configuration config;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        if (activityList == null) {
            activityList = new ArrayList<BaseActivity>();
        }

        TAG = getLocalClassName();
        Log.i(TAG, "onCreate");
        // fix the font scale size
        config = getResources().getConfiguration();
        Resources res = getResources();
        config.fontScale = 1;
        res.updateConfiguration(config, res.getDisplayMetrics());
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause");
    }

    @Override
    protected void onStart() {
        Log.i(TAG, "onStart");
        super.onStart();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.i(TAG, "onRestoreInstanceState");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG, "onSaveInstanceState");
    }

    // *****************************************//
    // *****************top bar init************//
    // *****************************************//

    @Override
    public void finish() {
        super.finish();

    }

    Object loker = new Object();

    protected ProgressDialog mAlertDialog;

    protected ProgressDialog showProgressDialog(int pTitelResID, String pMessage) {
        String title = getResources().getString(pTitelResID);
        return showProgressDialog(title, pMessage, null);
    }

    public synchronized ProgressDialog createProgressDialog(String pTitle, String pMessage,
                                                            DialogInterface.OnCancelListener pCancelClickListener) {

        ProgressDialog mDialog = ProgressDialog.show(this, pTitle, pMessage, true, true);
        mDialog.setCancelable(true);
        mDialog.setOnCancelListener(pCancelClickListener);

        return (ProgressDialog) mDialog;
    }

    public synchronized ProgressDialog showProgressDialog(String pTitle, String pMessage,
                                                          DialogInterface.OnCancelListener pCancelClickListener) {
        if (mAlertDialog != null) {
            mAlertDialog.setTitle(pTitle);
            mAlertDialog.setMessage(pMessage);
            return mAlertDialog;
        }
        mAlertDialog = ProgressDialog.show(this, pTitle, pMessage, true, true);
        mAlertDialog.setCancelable(true);
        mAlertDialog.setOnCancelListener(pCancelClickListener);

        return (ProgressDialog) mAlertDialog;
    }

    public synchronized void cancelProgressDialog() {
        if (mAlertDialog != null) {
            if (mAlertDialog.isShowing()) {
                mAlertDialog.cancel();
            }
            mAlertDialog = null;
        }
    }

    private Toast mInfoToast;

    public void showToast(String msg) {
        if (msg == null) {
            return;
        }
        if (VERSION.SDK_INT >= VERSION_CODES.ICE_CREAM_SANDWICH) {
            Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
        } else {
            if (mInfoToast == null) {
                mInfoToast = Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT);
            }
            mInfoToast.cancel();
            mInfoToast.setText(msg);
            mInfoToast.show();
        }
    }

    // close input method
    public void closeInputMethod() {
        // hide input method and emotion mothod
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        // switch input status
        if (inputMethodManager.isActive()) {
            try {
                inputMethodManager
                        .hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            } catch (Exception e) {
//                inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }

    public  void HideKeyboard()
    {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
    }

    public void ShowKeyboard(){
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
        System.gc();
        activityList.remove(this);
    }

}
