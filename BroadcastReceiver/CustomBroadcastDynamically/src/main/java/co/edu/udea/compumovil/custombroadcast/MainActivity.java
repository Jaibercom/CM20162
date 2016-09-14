package co.edu.udea.compumovil.custombroadcast;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static final String ACTION_CUSTOM = "co.edu.udea.compumovil.custombroadcast.action.CUSTOM";
    private MyReceiver myReceiver;
    private LocalBroadcastManager mBroadcastManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Creating the receiver
        myReceiver = new MyReceiver();

        //Creating the filter
        IntentFilter filter = new IntentFilter();
        filter.addAction(ACTION_CUSTOM);

        //Registering the receiver
        mBroadcastManager = LocalBroadcastManager.getInstance(getApplicationContext());
        mBroadcastManager.registerReceiver(myReceiver, filter);

    }

    public void onClick(View v) {

        Intent intent = new Intent();
        intent.setAction(ACTION_CUSTOM);
        mBroadcastManager.sendBroadcast(intent);
    }

    @Override
    protected void onDestroy() {
        mBroadcastManager.unregisterReceiver(myReceiver);
        super.onDestroy();
    }
}
