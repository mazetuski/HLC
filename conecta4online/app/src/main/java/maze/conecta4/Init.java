package maze.conecta4;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by maze on 28/11/2016.
 */

public class Init extends Activity{
    private Button online, offline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.init);
        /*new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 1500);*/

        online = (Button) findViewById(R.id.online);
        offline = (Button) findViewById(R.id.offline);


        offline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle b = new Bundle();
                b.putString("ID", "");
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtras(b);
                startActivity(intent);
            }
        });

        online.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), InterfazOnline.class));
            }
        });

    }

    /*@Override
    public boolean onTouchEvent(MotionEvent event) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        return super.onTouchEvent(event);
    }*/
}
