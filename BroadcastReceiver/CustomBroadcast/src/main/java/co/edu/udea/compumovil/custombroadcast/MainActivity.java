package co.edu.udea.compumovil.custombroadcast;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static final String ACTION_CUSTOM = "co.edu.udea.compumovil.custombroadcast.action.CUSTOM";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View v) {

        Intent intent = new Intent();
        intent.setAction(ACTION_CUSTOM);
        sendBroadcast(intent);
    }
}
