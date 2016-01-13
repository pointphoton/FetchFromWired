package sample.org.fetchfromwired.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import sample.org.fetchfromwired.R;

/**
 * TODO: Add a class header comment!
 */
public abstract class BaseActivity extends Activity {

    private ProgressDialog mProgressDialog;

    protected void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    protected void showProgress(String msg) {
        if (mProgressDialog != null && mProgressDialog.isShowing()) dismissProgress();

        mProgressDialog = ProgressDialog.show(this, getResources().getString(R.string.app_name), msg);
    }

    protected void dismissProgress() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
            mProgressDialog = null;
        }
    }

    protected void writeLog(String key, String value) {
        Log.d(key, value);
    }
}