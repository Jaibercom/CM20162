package co.edu.udea.compumovil.birthdayprovider;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "BirthdayProvider";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void addBirthday(View view) {

        EditText name = (EditText) findViewById(R.id.name);
        EditText birthday = (EditText) findViewById(R.id.birthday);

        // Add a new birthday record
        ContentValues values = new ContentValues();

        values.put(DBContract.Column.NAME, name.getText().toString());
        values.put(DBContract.Column.BIRTHDAY, birthday.getText().toString());

        Uri uri = getContentResolver().insert(BirthDayProvider.CONTENT_URI, values);

        Toast.makeText(getBaseContext(),
                "example: " + uri.toString() + " inserted!", Toast.LENGTH_LONG).show();
        Log.d(TAG, "example: " + uri.toString() + " inserted!");
        name.setText("");
        birthday.setText("");

    }

    public void showAllBirthdays(View view) {
        // Show all the birthdays sorted by friend's name
        Cursor c = getContentResolver().query(BirthDayProvider.CONTENT_URI, null, null, null, "name");
        String result = "example Results:";

        if (!c.moveToFirst()) {
            Toast.makeText(this, result + " no content yet!", Toast.LENGTH_LONG).show();
        } else {
            do {
                result = result + "\n" + c.getString(c.getColumnIndex(DBContract.Column.NAME)) +
                        " with id " + c.getString(c.getColumnIndex(DBContract.Column.ID)) +
                        " has birthday: " + c.getString(c.getColumnIndex(DBContract.Column.BIRTHDAY));
            } while (c.moveToNext());
            Toast.makeText(this, result, Toast.LENGTH_LONG).show();
            Log.d(TAG, result);
        }
    }

    public void deleteAllBirthdays(View view) {
        // delete all the records and the table of the database provider
        int count = getContentResolver().delete(BirthDayProvider.CONTENT_URI, null, null);
        String countNum = "example: " + count + " records are deleted.";
        Toast.makeText(getBaseContext(),
                countNum, Toast.LENGTH_LONG).show();
        Log.d(TAG, countNum);

    }

}
