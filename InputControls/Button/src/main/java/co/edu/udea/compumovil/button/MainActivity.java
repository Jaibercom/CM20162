package co.edu.udea.compumovil.button;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.text_view);
        updateUI();

    }


    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.count_button:
                count++;
                updateUI();
                break;

            case R.id.clear_button:
                count = 0;
                updateUI();
                break;
        }

    }

    private void updateUI(){
        textView.setText(this.getString(R.string.text_view_msg) +" "+ count);
    }

}
