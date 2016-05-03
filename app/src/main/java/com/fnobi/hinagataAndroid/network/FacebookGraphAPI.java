package com.fnobi.hinagataAndroid.network;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

public class FacebookGraphAPI {
    
    private final static String API_ROOT = "http://graph.facebook.com/";
    
    public interface GetSharesListener {
        void onShares(int shares);
        void onError(Exception e);
    }
    
    public static JsonObjectRequest getShares(String url, final GetSharesListener listener) {
        String api = API_ROOT + url;
        
        return new JsonObjectRequest(Request.Method.GET, api, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                String sharesString = response.optString("shares");
                if (sharesString == null) {
                    listener.onError(new RuntimeException("Parse Error."));
                    return;
                }
                
                int shares = -1;
                try {
                    shares = Integer.parseInt(sharesString);
                } catch (NumberFormatException e) { /* do nothing */ }
                
                if (shares >= 0) {
                    listener.onShares(shares);
                } else {
                    listener.onError(new RuntimeException("Invalid response."));
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onError(error);
            }
        });
    }
}