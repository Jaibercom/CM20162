package co.edu.udea.computacionmovil.memoryservice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Obtener botón de monitoreo de memoria
        ToggleButton button = (ToggleButton) findViewById(R.id.toggleButton);

        // Setear escucha de acción
        button.setOnCheckedChangeListener(
                new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        Intent intentMemoryService = new Intent(getApplicationContext(), MemoryService.class);
                        if (isChecked) {
                            startService(intentMemoryService); //Iniciar servicio
                        } else {
                            stopService(intentMemoryService); // Detener servicio
                        }
                    }
                }
        );


    }

}
