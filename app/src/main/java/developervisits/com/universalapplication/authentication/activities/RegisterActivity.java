package developervisits.com.universalapplication.authentication.activities;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import developervisits.com.universalapplication.R;
import developervisits.com.universalapplication.common.activities.MainActivity;
import developervisits.com.universalapplication.common.activities.SplashScreenActivity;

/**
 * Created by developervisits on ${DATE}.
 * @author Praveen.verma | Noida
 */
public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.edittext_mobile)
    EditText edittext_mobile;
    @BindView(R.id.edittext_password)
    EditText edittext_password;
    @BindView(R.id.button_register)
    Button button_register;

    private boolean isVisible=false ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

    }

    @Override
    protected void onResume() {
        super.onResume();



        edittext_password.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_LEFT = 0;
                final int DRAWABLE_TOP = 1;
                final int DRAWABLE_RIGHT = 2;
                final int DRAWABLE_BOTTOM = 3;

                if(event.getAction() == MotionEvent.ACTION_UP) {
                    if(event.getRawX() >= (edittext_password.getRight() - edittext_password.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        // your action here
                        if(!isVisible) {
                            isVisible = true;
                            edittext_password.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_mobile, 0);
                            edittext_password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                            edittext_password.setSelection(edittext_password.getText().length());
                        }else {
                            isVisible = false;
                            edittext_password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                            edittext_password.setSelection(edittext_password.getText().length());
                            edittext_password.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_password, 0);
                        }
                        edittext_password.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_password, 0, R.drawable.ic_mobile, 0);

                        return true;
                    }
                }
                return false;
            }
        });


        // Register here
        button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToOTPScreen();
            }
        });

    }

    private void goToOTPScreen() {

        Intent mainIntent = new Intent(RegisterActivity.this, LoginViaOTPActivity.class);
        startActivity(mainIntent);
        finish();
    }



}
