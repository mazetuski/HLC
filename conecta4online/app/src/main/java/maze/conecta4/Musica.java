package maze.conecta4;

import android.content.Context;
import android.media.MediaPlayer;

/**
 * Created by maze on 28/11/2016.
 */

public class Musica {
    private static MediaPlayer mediaPlayer;

    public static void play(Context context, int id) {
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(context, id);
            mediaPlayer.setLooping(true);
        }
        mediaPlayer.start();
    }

    public static void stop() {
        if (mediaPlayer != null) {
           /* mediaPlayer.release();
            mediaPlayer = null;*/
            mediaPlayer.pause();
        }
    }
}
