package developervisits.com.universalapplication.common.persistance;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by developervisits on 28/07/18.
 * @author Praveen.verma | Noida
 */


public class SharedPreferenceSave {
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;

    public SharedPreferenceSave(Context context) {
        prefs = context.getSharedPreferences(
                "Data", Context.MODE_PRIVATE);
        editor = prefs.edit();
    }

    public String getEmail() {
        return prefs.getString("email", "");
    }

    public void setEmail(String email) {
        editor.putString("email", email);
        editor.commit();
    }


    public String getPassword() {
        return prefs.getString("password", "");
    }

    public void setPassword(String password) {
        editor.putString("password", password);
        editor.commit();
    }

    public String getCustomerId() {
        return prefs.getString("customer_id", "");
    }

    public void setCustomerId(String customerId) {
        editor.putString("customer_id", customerId);
        editor.commit();
    }


    public boolean getLogin() {
        return prefs.getBoolean("login", false);
    }

    public void setLogin(boolean login) {
        editor.putBoolean("login", login);
        editor.commit();
    }


    public int getCartCount() {
        return prefs.getInt("count", 0);
    }

    public void setCartCount(int count) {
        if (count >= 0) {
            editor.putInt("count", count);
            editor.commit();
        }
    }

   /* public boolean isSkip() {
        return prefs.getBoolean("skip", false);
    }

    public void setSkip(boolean skip) {
        editor.putBoolean("skip", skip);
        editor.commit();
    }*/
}
