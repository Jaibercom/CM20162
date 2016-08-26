package co.edu.udea.computacionmovil.sendemail;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity {

    final String TAG = "MainActivity";
    EditText edtTo;
    EditText edtSubject;
    EditText edtComposeEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtTo = (EditText) findViewById(R.id.edtTo);
        edtSubject = (EditText) findViewById(R.id.edtSubject);
        edtComposeEmail = (EditText) findViewById(R.id.edtComposeEmail);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        menu.findItem(R.id.action_send).setVisible(true);

        if(menu.getClass().getSimpleName().equals("MenuBuilder")){
            try{
                Method m = menu.getClass().getDeclaredMethod(
                        "setOptionalIconsVisible", Boolean.TYPE);
                m.setAccessible(true);
                m.invoke(menu, true);
            }
            catch(NoSuchMethodException e){
                Log.e(TAG, "onMenuOpened", e);
            }
            catch(Exception e){
                throw new RuntimeException(e);
            }
        }


        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_send) {

            composeEmail();
            item.setVisible(true);
        }
        if (id == R.id.menu_exit) {

            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void composeEmail() {
        String[] addresses = {edtTo.getText().toString()};
        String subject = edtSubject.getText().toString();
        String emailCompose = edtComposeEmail.getText().toString();

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:myemail@gmail.com"));   // only Email apps will handle this
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, emailCompose);

        // Verify the original intent will resolve to at least one activity
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
        else{
            Log.d(TAG, "No activity was found that matched with the intent-filter");
            Toast.makeText(this, "No activity was found that matched with the intent-filter", Toast.LENGTH_SHORT).show();
        }
    }
}
