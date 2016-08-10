package co.edu.udea.compumovil.listview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Get reference to list view
        ListView listView = (ListView) findViewById(R.id.listView);

        //Get colors
        String[] colors = getResources().getStringArray(R.array.colors);

        // Create a new Adapter containing a list of colors
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_item, colors);                        //Set a custom layout
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, colors);         //Set a default layout


        // Set the adapter on this ListActivity's built-in ListView
        listView.setAdapter(adapter);

        // Enable filtering when the user types in the virtual keyboard
        listView.setTextFilterEnabled(true);

        listView.setOnItemClickListener(this);

    /*
        // Set an setOnItemClickListener on the ListView
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // Display a Toast message indicting the selected item
                Toast.makeText(getApplicationContext(),
                        ((TextView) view).getText(), Toast.LENGTH_SHORT).show();
            }
        });
    */

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

        // Display a Toast message indicting the selected item
        Toast.makeText(this, ((TextView) view).getText(), Toast.LENGTH_SHORT).show();

        Log.v("MainActivity", "text: " + ((TextView) view).getText());

    }

}
