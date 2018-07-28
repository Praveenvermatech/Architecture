package developervisits.com.universalapplication.common.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import developervisits.com.universalapplication.R;


public class CustomDialog {
    private static ProgressDialog progressDialog;

    public static void singleAlert(final Activity mActivity, String titleText, String msg, boolean positiveButtonEnable, boolean negativeButtonEnable) {
        final Dialog dialog = new Dialog(mActivity);
        dialog.setCancelable(false);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.custom_dialog_layout);
        TextView title = (TextView) dialog.findViewById(R.id.header);
        title.setText(titleText);
        TextView msgtv = (TextView) dialog.findViewById(R.id.dialog_message);
        msgtv.setText(msg);
        Button buttonPositive = (Button) dialog.findViewById(R.id.positive_button);
        Button buttonNegative = (Button) dialog.findViewById(R.id.positive_button);
        if(positiveButtonEnable){
            buttonPositive.setVisibility(View.VISIBLE);
        }else {
            buttonPositive.setVisibility(View.GONE);
        }
        if(negativeButtonEnable){
            buttonNegative.setVisibility(View.VISIBLE);
        }else {
            buttonNegative.setVisibility(View.GONE);
        }

        buttonNegative.setText("OK");
        buttonNegative.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }



    public static AlertDialog.Builder showDialogWithButton(Context context, String title, CharSequence message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        if (title != null) builder.setTitle(title);

        builder.setMessage(message);
        builder.setPositiveButton("OK", null);
        //builder.setNegativeButton("Cancel", null);

        return builder;
    }

    public static void showToast(Context context, String string) {
        Toast.makeText(context, string, Toast.LENGTH_SHORT).show();
    }

    public static void showProgressDialog(Context con) {
        progressDialog = ProgressDialog.show(con, null, "Please wait...");
    }

    public static void hideProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }

    public static void ShowNetworkError(Context con) {
        CustomDialog.singleAlert((Activity) con, "Alert!", "Please check your internet !!!", true, false);
    }

    public static void showLoginDialog(final Context context, String title, String msg) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        if (title != null)
            builder.setTitle(title);

        builder.setMessage(msg);
        builder.setPositiveButton("Login", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
//                context.startActivity(new Intent(context, LoginDefaultActivity.class));
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });


        //builder.setNegativeButton("Cancel", null);
        builder.show();
    }

}
