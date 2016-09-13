package co.edu.udea.compumovil.custombroadcast;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static final String CUSTOM_INTENT = "co.edu.udea.compumovil.custombroadcast.show_toast";
    private final IntentFilter intentFilter = new IntentFilter(CUSTOM_INTENT);
    private final MyReceiver myReceiver = new MyReceiver();
    private LocalBroadcastManager mBroadcastManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBroadcastManager = LocalBroadcastManager.getInstance(getApplicationContext());
        mBroadcastManager.registerReceiver(myReceiver, intentFilter);

    }

    public void onClick(View v) {

        mBroadcastManager.sendBroadcast(new Intent(CUSTOM_INTENT));
    }

    @Override
    protected void onDestroy() {
        mBroadcastManager.unregisterReceiver(myReceiver);
        super.onDestroy();
    }
}
