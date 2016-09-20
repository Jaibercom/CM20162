package co.edu.udea.compumovil.volley;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private TextView mTextView;
    private final String TAG = "Volley";

    private static final String USER_NAME = "aporter";
    private static final String PARAMS = "?north=20&south=-20&east=-60&west=-80&username=" + USER_NAME;
    private static final String REQUEST = "/earthquakesJSON";
    private static final String BASE_URL = "http://api.geonames.org";
    private String URL = null;

    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = (TextView) findViewById(R.id.textView1);
    }

    public void onClick(View v) {

        URL = BASE_URL+REQUEST+PARAMS;
        sendRequest();
    }

    private void sendRequest() {

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);

        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Method.GET,
                        URL,
                        new Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject response) {
                                mTextView.setText("Response: " + response.toString());
                            }
                        }, new Response.ErrorListener() {

                            @Override
                            public void onErrorResponse(VolleyError error) {
                                mTextView.setText("Error: " + error.toString());

                            }
                        }
                );
        queue.add(jsObjRequest);
    }
}
