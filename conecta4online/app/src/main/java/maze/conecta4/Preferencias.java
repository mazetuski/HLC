package maze.conecta4;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.app.Activity;
import android.net.Uri;

/**
 * Created by maze on 19/01/2017.
 */

public class Preferencias extends PreferenceActivity {

    public final static String PLAY_MUSIC = "music";
    public final static boolean PLAY_MUSIC_DEFAULT = true;
    public final static String PLAYER_INICIAL_KEY = "turnoPreference";
    public final static String PLAYER_INICIAL_DEFAULT = "1";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new preferenciasFragment()).commit();

    }

}
