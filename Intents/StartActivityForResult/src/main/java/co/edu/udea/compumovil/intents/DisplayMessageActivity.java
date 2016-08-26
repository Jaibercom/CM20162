package co.edu.udea.compumovil.intents;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DisplayMessageActivity extends AppCompatActivity {

    public final static String KEY1 = "co.edu.udea.compumovil.intents.KEY1";
    public final static String KEY2 = "co.edu.udea.compumovil.intents.KEY2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        TextView textView = (TextView)findViewById(R.id.message);
        textView.setTextSize(40);
        textView.setText(message);
    }

    @Override
    public void finish() {
        // Prepare data intent
        Intent data = new Intent();
        data.putExtra(KEY1, "Mensaje de retorno 1");
        data.putExtra(KEY2, "Mensaje de retorno 2");
        // Activity finished ok, return the data
        setResult(RESULT_OK, data);
        super.finish();
    }

}
