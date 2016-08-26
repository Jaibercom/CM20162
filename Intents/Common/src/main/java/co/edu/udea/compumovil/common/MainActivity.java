package co.edu.udea.compumovil.common;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this,
                R.array.intents, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    public void onClick(View view) {
        int position = spinner.getSelectedItemPosition();
        Intent intent = null;

        switch (position) {
            case 0:         //Open Browser
                intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://sites.google.com/site/udeacomputacionmovil15/clases"));
                break;
            case 1:         //Call Someone
                intent = new Intent(Intent.ACTION_CALL,
                        Uri.parse("tel:(+57)12345789"));
                break;
            case 2:         //Dial
                intent = new Intent(Intent.ACTION_DIAL,
                        Uri.parse("tel:(+57)12345789"));
                //startActivity(intent);
                break;
            case 3:         //Show Map in Medellin
                intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("geo:6.25,-75.56?z=12"));
                break;
            case 4:         //Search on Map
                intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("geo:0,0?q=query"));
                break;
            case 5:         //Take picture
                intent = new Intent("android.media.action.IMAGE_CAPTURE");
                break;
            case 6:         //Show contacts
                intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("content://contacts/people/"));
                break;
            case 7:         //Edit first contact
                intent = new Intent(Intent.ACTION_EDIT,
                        Uri.parse("content://contacts/people/1"));
                break;

        }
        if (intent != null) {
            startActivity(intent);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK && requestCode == 0) {
            String result = data.toURI();
            Toast.makeText(this, result, Toast.LENGTH_LONG);
        }
    }


}
