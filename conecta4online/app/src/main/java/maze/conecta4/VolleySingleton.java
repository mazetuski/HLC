package maze.conecta4;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by maze on 24/01/2017.
 */

public class VolleySingleton {

    private  static VolleySingleton instance;
    private RequestQueue requestQueue;

    private VolleySingleton(Context context){
        requestQueue = Volley.newRequestQueue(context);
    }

    public static VolleySingleton getInstance(Context context){
        if(instance == null)
            instance = new VolleySingleton(context);
        return instance;
    }

    public RequestQueue getRequestQueue(){
        return requestQueue;
    }

}
