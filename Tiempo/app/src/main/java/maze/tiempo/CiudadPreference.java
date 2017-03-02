package maze.tiempo;

import android.app.Activity;
import android.content.SharedPreferences;

/**
 * Created by maze on 01/03/2017.
 */

public class CiudadPreference {
    SharedPreferences prefs;

    public CiudadPreference(Activity activity){
        prefs = activity.getPreferences(Activity.MODE_PRIVATE);
    }

    String getCity(){
        return prefs.getString("city", "Cordoba, ES");
    }

    void setCity(String city){
        prefs.edit().putString("city", city).commit();
    }
}
