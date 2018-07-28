package developervisits.com.universalapplication.common.network.volley;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;
import developervisits.com.universalapplication.application.UniversalApplication;
import developervisits.com.universalapplication.common.network.interfaces.VolleyCompleteListener;
import developervisits.com.universalapplication.common.network.interfaces.VolleyDownloadCompleteListener;
import developervisits.com.universalapplication.common.network.interfaces.VolleyErrorListener;
import developervisits.com.universalapplication.common.utils.CommonUtility;
import developervisits.com.universalapplication.common.utils.CustomDialog;

/**
 * Created by Praveen on 20/7/16.
 */
public class VolleyMethod {
    private String BASEURL = CommonUtility.url;
    private Context context;
    private VolleyDownloadCompleteListener callback;
    private VolleyErrorListener errorListener;
    private VolleyCompleteListener completeListener;

    public VolleyMethod(Context context, VolleyDownloadCompleteListener cb) {
        this.context = context;
        this.callback = cb;
    }

    public VolleyMethod(Context context, VolleyDownloadCompleteListener cb, VolleyErrorListener errorListener) {
        this.context = context;
        this.callback = cb;
        this.errorListener = errorListener;
    }

    public VolleyMethod(Context context, VolleyCompleteListener cb, VolleyErrorListener errorListener) {
        this.context = context;
        this.completeListener = cb;
        this.errorListener = errorListener;
    }

    public void requestGetString(final String task, boolean progressDialog) {
        String url = BASEURL + task;
        Log.e("url", url);
        if (progressDialog)
            CustomDialog.showProgressDialog(context);

        StringRequest strReq = new StringRequest(Request.Method.GET,
                url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.e("response " + task, response);
                CustomDialog.hideProgressDialog();
                if (response != null) {
                    try {
                        callback.onTaskComplete(response, task);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    CustomDialog.showToast(context, "Error");
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error " + task, "Error: " + error.getMessage());
                CustomDialog.hideProgressDialog();
                if (error.getMessage() != null) {
                    CustomDialog.showToast(context, error.getMessage());
                } else {
                    CustomDialog.showToast(context, "Connection Failed");
                }

                if (errorListener != null) {
                    errorListener.onError("", task);
                }

            }
        });
        UniversalApplication.getInstance().addToRequestQueue(strReq, task);
    }

    public void requestPostString(final String task, JSONObject jsonObject, boolean progressDialog, View view) {
        if (CommonUtility.isNetworkAvailable(context)) {
            final HashMap<String, String> params = new HashMap<>();
            params.put("json", jsonObject.toString());

            String url = BASEURL + task;
            Log.e("url", url);
            Log.e("data", params.toString());

            if (progressDialog)
                CustomDialog.showProgressDialog(context);

            StringRequest strReq = new StringRequest(Request.Method.POST,
                    url, new Response.Listener<String>() {

                @Override
                public void onResponse(String response) {
                    Log.e("response " + task, response);
                    //GlobalAlerts.showToast(context, ""+response);
                    CustomDialog.hideProgressDialog();
                    if (response != null) {
                        try {
                            callback.onTaskComplete(response, task);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    } else {
                        CustomDialog.showToast(context, "Error");
                    }
                }
            }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("Error " + task, "Error: " + error.getMessage());
                    CustomDialog.hideProgressDialog();

                    if (error.equals("com.android.volley.TimeoutError")) {
                        CustomDialog.showToast(context, "Slow Connection.");
                    }

                    if (errorListener != null) {
                        errorListener.onError("", task);
                    }
                }
            }) {
                @Override
                protected Map<String, String> getParams() {
                    return params;
                }
            };
            UniversalApplication.getInstance().addToRequestQueue(strReq, task);
        } else if (view != null) {
            CustomDialog.singleAlert((Activity) context, "Alert!", "No internet connection !", true, false);
        } else {
            if (errorListener != null) {
                errorListener.onError("", task);
            }
        }
    }


    public void requestPostString(final String task, JSONObject jsonObject, final int position, boolean progressDialog, View view) {
        if (CommonUtility.isNetworkAvailable(context)) {
            final HashMap<String, String> params = new HashMap();
            params.put("json", jsonObject.toString());

            String url = BASEURL + task;
            Log.e("url", url);
            Log.e("data", params.toString());

            if (progressDialog)
                CustomDialog.showProgressDialog(context);

            StringRequest strReq = new StringRequest(Request.Method.POST,
                    url, new Response.Listener<String>() {

                @Override
                public void onResponse(String response) {
                    Log.e("response", response);
                    CustomDialog.hideProgressDialog();
                    if (response != null) {
                        try {
                            completeListener.onTaskComplete(response, task, position);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    } else {
                        CustomDialog.showToast(context, "Error");
                    }
                }
            }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("Error", "Error: " + error.getMessage());
                    CustomDialog.hideProgressDialog();

                    if (errorListener != null) {
                        errorListener.onError("", task);
                    }
                }
            }) {
                @Override
                protected Map<String, String> getParams() {
                    return params;
                }
            };
            UniversalApplication.getInstance().addToRequestQueue(strReq, task);
        } else {
            CustomDialog.singleAlert((Activity) context, "Alert!", "No internet connection !", true, false);
        }
    }

}
