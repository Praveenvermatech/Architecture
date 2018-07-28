package developervisits.com.universalapplication.common.network.interfaces;


import org.json.JSONException;

/**
 * Created by developervisits on 28/07/18.
 * @author Praveen.verma | Noida
 */

public interface VolleyCompleteListener {
    void onTaskComplete(String result, String task,int position) throws JSONException;
}
