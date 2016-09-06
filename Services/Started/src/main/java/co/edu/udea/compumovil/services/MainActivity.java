package co.edu.udea.compumovil.services;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "MyService";
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intent = new Intent(getBaseContext(), MyService.class);
    }

    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.btnStartService:
                Log.v(TAG, "Start button");
                startService(intent);
                break;

            case R.id.btnStopService:
                Log.v(TAG, "Stop button");
                stopService(intent);
                break;
        }
    }
}
