package developervisits.com.universalapplication.common.utils;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.Fragment;
import android.util.Patterns;
import android.view.View;
import android.view.inputmethod.InputMethodManager;


/**
 * Created by Praveen on 28/11/18.
 */
public class CommonUtility {

    public static String url = "http://www.4u2u.in/make/demo/";
    public static String imageUrl = "http://www.4u2u.in/make/images/";
    public static String bannerUrl = "http://www.4u2u.in/banner_images/";

   /* signUp
    user_name,password*/

//    public static RegistrationModel registrationModel;

    //for checking null in string
    public static String checkNull(String string) {
        if (string == null || string.equalsIgnoreCase("null")) {
            return "";
        } else {
            return string;
        }
    }

    //    checking Network
    public static boolean isNetworkAvailable(Context con) {
        boolean tempReturn = false;
        try {
            ConnectivityManager cm = (ConnectivityManager) con.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = cm.getActiveNetworkInfo();

            if (networkInfo != null && networkInfo.isConnected()) {
                tempReturn = true;
            } else {
//                GlobalAlerts.ShowNetworkError(con);
                tempReturn = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tempReturn;
    }

    public static String setImageUrl(String url) {
        String imageUrl = url.replaceAll("'\'", "");
        return imageUrl;
    }


    public static boolean isValidEmail(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }

   /* public static void setFragment(Fragment fragment, boolean back, Fragment mainFragment) {
        FragmentTransaction ft = mainFragment.getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_container, fragment, null);
        if (back) {
            ft.addToBackStack(null);
        }
        ft.commit();
    }*/

    /*to hide keyboard*/
    public static void hideFragmentKeyboard(Fragment fragment) {
        Activity activity = fragment.getActivity();
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(fragment.getView().getWindowToken(), 0);
    }

    /*to hide keyboard*/
    public static void hideActivityKeyboard(Activity activity) {
        View view = activity.getCurrentFocus();
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (view != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        } else {
            imm.hideSoftInputFromWindow(activity.findViewById(android.R.id.content).getWindowToken(), 0);
        }
    }
}
