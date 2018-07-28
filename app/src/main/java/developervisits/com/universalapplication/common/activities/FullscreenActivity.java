package developervisits.com.universalapplication.common.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import developervisits.com.universalapplication.R;

/**
 * Created by developervisits on 28/07/18.
 * @author Praveen.verma | Noida
 */
public class FullscreenActivity extends AppCompatActivity {

    @BindView(R.id.button_login)
    Button button_login;
    @BindView(R.id.button_register)
    Button button_register;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fullscreen);
        ButterKnife.bind(this);

    }

    @Override
    protected void onResume() {
        super.onResume();

        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent mainIntent = new Intent(FullscreenActivity.this, LoginActivity.class);
                startActivity(mainIntent);
                finish();

            }
        });

        button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent mainIntent = new Intent(FullscreenActivity.this, RegisterActivity.class);
                startActivity(mainIntent);
                finish();
            }
        });

    }
}
