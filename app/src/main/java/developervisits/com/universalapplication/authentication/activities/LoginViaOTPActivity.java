package developervisits.com.universalapplication.authentication.activities;

import android.Manifest;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import developervisits.com.universalapplication.R;
import developervisits.com.universalapplication.common.activities.FullscreenActivity;
import developervisits.com.universalapplication.common.activities.MainActivity;
import developervisits.com.universalapplication.common.activities.SplashScreenActivity;

import static developervisits.com.universalapplication.common.constant.AppConstant.SPLASH_DISPLAY_TIME;


/**
 * Created by developervisits on 28/07/18.
 * @author Praveen.verma | Noida
 */

public class LoginViaOTPActivity extends Activity {

    @BindView(R.id.textview_otp_message)
    TextView textview_otp_message;
    @BindView(R.id.textview_otp)
    TextView textview_otp;

    private Handler handler = new Handler() ;
    private boolean flag = false;

    public static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_via_otp);
        ButterKnife.bind(this);
        if (checkAndRequestPermissions()) {
            // carry on the normal flow, as the case of  permissions  granted.
        }
    }


    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equalsIgnoreCase("otp")) {
                final String message = intent.getStringExtra("message");

                textview_otp.setText(message);
            }
        }
    };

    private  boolean checkAndRequestPermissions() {
        int permissionSendMessage = ContextCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS);
        int receiveSMS = ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS);
        int readSMS = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS);
        List<String> listPermissionsNeeded = new ArrayList<>();
        if (receiveSMS != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.RECEIVE_MMS);
        }
        if (readSMS != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.READ_SMS);
        }
        if (permissionSendMessage != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.SEND_SMS);
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this,
                    listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]),
                    REQUEST_ID_MULTIPLE_PERMISSIONS);
            return false;
        }
        return true;
    }

    @Override
    public void onResume() {
        LocalBroadcastManager.getInstance(this).registerReceiver(receiver, new IntentFilter("otp"));
        super.onResume();

        textview_otp_message.setText(getResources().getString(R.string.text_otp_message)+"7503390877 "+
                getResources().getString(R.string.text_otp_message2));
        handler.postDelayed(new Runnable() {

            public void run() {
                Log.e("LoginViaOTP","Call Main Activity");
                if(!flag && getIntent().getStringExtra("hasExtras")==null) {
                    flag = true;
                    Intent mainIntent = new Intent(LoginViaOTPActivity.this, MainActivity.class);
                    startActivity(mainIntent);
                    finish();
                }


            }
        }, SPLASH_DISPLAY_TIME);
    }

    @Override
    public void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver);
    }
}
