package co.edu.udea.compumovil.services;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "MyService";
    private Intent intent;
    private Button startService;
    private Button stopService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startService = (Button) findViewById(R.id.btnStartService);
        stopService = (Button) findViewById(R.id.btnStopService);

        stopService.setEnabled(false);
    }

    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.btnStartService:
                Log.v(TAG, "Start button");
                intent = new Intent(this, MyService.class);
                startService(intent);

                startService.setEnabled(false);
                stopService.setEnabled(true);
                break;

            case R.id.btnStopService:
                Log.v(TAG, "Stop button");
                stopService(intent);

                startService.setEnabled(true);
                stopService.setEnabled(false);
                break;
        }
    }
}
