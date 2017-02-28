package maze.conecta4;

import android.os.Bundle;
import android.preference.PreferenceFragment;

/**
 * Created by maze on 19/01/2017.
 */

public class preferenciasFragment extends PreferenceFragment{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);
    }
}
