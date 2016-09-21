package co.edu.udea.compumovil.volleygson;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView mTextView;
    private ProgressDialog Dialog;
    private ListView listView;

    private final String TAG = "VolleyGSON";

    private RequestQueue requestQueue;

    private ArrayList<String> result ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //mTextView = (TextView) findViewById(R.id.textView1);
        listView = (ListView) findViewById(R.id.listView);
        result = new ArrayList<String>();

    }

    public void onClick(View v) {

        final String USER_NAME = "aporter";
        final String PARAMS = "?north=20&south=-20&east=-60&west=-80&username=" + USER_NAME;
        final String REQUEST = "/earthquakesJSON";
        final String BASE_URL = "http://api.geonames.org";
        String URL = BASE_URL+REQUEST+PARAMS;
        sendRequest(URL);
    }

    private void sendRequest(String URL) {

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);

        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Method.GET,
                        URL,
                        new Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject response) {
                                //mTextView.setText("Response: " + response.toString());

                                JsonParser jsonParser = new JsonParser();
                                JsonObject jo = (JsonObject) jsonParser.parse(response.toString());
                                JsonArray jsonArr = jo.getAsJsonArray("earthquakes");

                                Gson gson;
                                gson = new Gson();
                                EarthQuake[] earthQuakes = gson.fromJson(jsonArr, EarthQuake[].class);

                                if(earthQuakes != null) {
                                    for(int i=0;i<earthQuakes.length; i++){
                                        Log.d(TAG, "*earthQuakes: " + earthQuakes[i].getMagnitude());
                                        result.add("Magnitude: " + earthQuakes[i].getMagnitude()
                                                + ", Lat :" + earthQuakes[i].getLat()
                                                + ", Lng :" + earthQuakes[i].getLng());
                                    }

                                    ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(), R.layout.list_item, result);
                                    listView.setAdapter(adapter);

                                }
                                else
                                    Log.d(TAG, "**earthQuakes is null " );

                            }
                        }, new Response.ErrorListener() {

                            @Override
                            public void onErrorResponse(VolleyError error) {
                                //mTextView.setText("Error: " + error.toString());
                                Log.d(TAG, "Error: " + error.toString());

                            }
                        }
                );
        queue.add(jsObjRequest);
    }
}
