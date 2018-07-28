package developervisits.com.universalapplication.common.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import org.json.JSONException;
import org.json.JSONObject;
import developervisits.com.universalapplication.R;
import developervisits.com.universalapplication.common.network.interfaces.VolleyDownloadCompleteListener;
import developervisits.com.universalapplication.common.network.interfaces.VolleyErrorListener;
import developervisits.com.universalapplication.common.network.volley.VolleyMethod;
import developervisits.com.universalapplication.common.persistance.SharedPreferenceSave;
import developervisits.com.universalapplication.common.utils.CommonUtility;
import static developervisits.com.universalapplication.common.constant.AppConstant.SPLASH_DISPLAY_TIME;


/**
 * Created by developervisits on 28/07/18.
 * @author Praveen.verma | Noida
 */

public class SplashScreenActivity extends AppCompatActivity implements VolleyDownloadCompleteListener, VolleyErrorListener {

    private Handler handler = new Handler() ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);

        initData();
    }

    private void initData() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
//        callApi();
        handler.postDelayed(new Runnable() {

            public void run() {

                if(getIntent().getStringExtra("hasExtras")!=null) {
                    Intent mainIntent = new Intent(SplashScreenActivity.this, MainActivity.class);
                    startActivity(mainIntent);
                    finish();
                } else {
                    Intent mainIntent = new Intent(SplashScreenActivity.this, FullscreenActivity.class);
                    startActivity(mainIntent);
                    finish();
                }


            }
        }, SPLASH_DISPLAY_TIME);
    }

    private void callApi() {
        if (CommonUtility.isNetworkAvailable(this)) {
            JSONObject jsonObject = new JSONObject();
            try {
                SharedPreferenceSave sharedPreferenceSave = new SharedPreferenceSave(this);
                jsonObject.put("fld_user_id", sharedPreferenceSave.getCustomerId());
            } catch (JSONException e) {
                e.printStackTrace();
            }

            new VolleyMethod(this, this, this).requestPostString("", jsonObject, false,null);
        } else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    setIntent();
                }
            }, 3000);
        }
    }

    @Override
    public void onTaskComplete(String result, String task) throws JSONException {
        JSONObject jsonObject = new JSONObject(result);
        if (jsonObject.getBoolean("result")) {
            SharedPreferenceSave sharedPreferenceSave = new SharedPreferenceSave(this);
            sharedPreferenceSave.setCartCount(Integer.parseInt(jsonObject.getString("data")));
            setIntent();
        } else {
            setIntent();
        }
    }

    @Override
    public void onError(Object result, String task) {
        setIntent();
    }

    private void setIntent() {
        Intent in = new Intent(SplashScreenActivity.this, MainActivity.class);
        startActivity(in);
        finish();
    }
}
