package co.edu.udea.compumovil.intents;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    public final static String EXTRA_MESSAGE = "co.edu.udea.compumovil.intents.MESSAGE";
    public final static int REQUEST_CODE = 25;

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

        startActivityForResult(intent, REQUEST_CODE);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {

            if (data.hasExtra(DisplayMessageActivity.KEY1)) {
                Toast.makeText(this, data.getExtras().
                                getString(DisplayMessageActivity.KEY1),
                        Toast.LENGTH_SHORT).show();
            }

        }
    }

}
