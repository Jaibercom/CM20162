package co.edu.udea.computacionmovil.intentservice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    private TextView memoryUsageText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    /**
     * Método onClick() personalizado para {@code turn_intent_service}
     * @param v View presionado
     */
    public void onClickTurnIntentService(View v) {
        Intent intent = new Intent(this, ProgressIntentService.class);
        intent.setAction(Constants.ACTION_RUN_ISERVICE);
        startService(intent);
    }

}
