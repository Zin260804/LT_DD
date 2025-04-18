package vn.iotstar.baitaptuan7_vidukhac.Volley;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class VolleySingleton {
    private static VolleySingleton instance;
    private RequestQueue requestQueue;

    // Constructor riêng tư
    private VolleySingleton(Context context) {
        requestQueue = Volley.newRequestQueue(context.getApplicationContext());
    }

    // Lấy instance duy nhất
    public static synchronized VolleySingleton getInstance(Context context) {
        if (instance == null) {
            instance = new VolleySingleton(context);
        }
        return instance;
    }

    // Lấy RequestQueue
    public RequestQueue getRequestQueue() {
        return requestQueue;
    }

    // Thêm request vào queue
    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }
}