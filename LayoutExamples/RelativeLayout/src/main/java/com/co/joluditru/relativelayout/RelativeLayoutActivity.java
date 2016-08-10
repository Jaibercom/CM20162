package com.co.joluditru.relativelayout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RelativeLayoutActivity extends AppCompatActivity implements View.OnClickListener {


    private EditText txtMensaje;
    private Button btnCancel;
    private Button btnOk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtMensaje = (EditText) findViewById(R.id.input_text);
        btnCancel = (Button) findViewById(R.id.cancel_button);
        btnOk = (Button) findViewById(R.id.ok_button);

        btnCancel.setOnClickListener(this);
        btnOk.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cancel_button:
                //Limpiar el EditText
                txtMensaje.setText("");
                break;
            case R.id.ok_button:
                //Mostar mensaje
                Toast.makeText(RelativeLayoutActivity.this, txtMensaje.getText().toString(),
                        Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
