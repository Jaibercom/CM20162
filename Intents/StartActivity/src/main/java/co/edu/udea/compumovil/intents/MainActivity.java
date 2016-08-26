package co.edu.udea.compumovil.intents;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    public final static String EXTRA_MESSAGE = "co.edu.udea.compumovil.intents.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.edit_message);

    }

    /**
     * Called when the user clicks the Send button
     */
    public void sendMessage(View view) {
        String msg = editText.getText().toString();
        editText.setText("");
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        intent.putExtra(EXTRA_MESSAGE, msg);
        startActivity(intent);
    }

}
