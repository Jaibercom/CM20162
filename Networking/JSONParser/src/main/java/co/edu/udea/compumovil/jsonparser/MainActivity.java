package co.edu.udea.compumovil.jsonparser;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private ProgressDialog Dialog;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listView);

    }

    public void onClick(View v){

        new HttpGetTask().execute();
    }

    private class HttpGetTask extends AsyncTask<Void, Void, List<String>> {

        private static final String TAG = "HttpGetTask";

        private final String USER_NAME = "aporter";
        private final String URL = "http://api.geonames.org/earthquakesJSON?north=20&south=-20&east=-60&west=-80&username=" + USER_NAME;

        private ProgressDialog Dialog = new ProgressDialog(MainActivity.this);
        private String data;
        private final String LONGITUDE_TAG = "lng";
        private final String LATITUDE_TAG = "lat";
        private final String MAGNITUDE_TAG = "magnitude";
        private final String EARTHQUAKE_TAG = "earthquakes";

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //Start Progress Dialog (Message)
            Dialog.setMessage("Please wait..");
            Dialog.show();
        }

        @Override
        protected List<String> doInBackground(Void... params) {
            //String data = "";
            ArrayList<String> result = new ArrayList<String>();
            HttpClient client = new HttpClient(URL);
            data = client.httpGet();


            /****************** Start Parse Response JSON Data *************/
            //String OutputData = "";
            JSONObject responseObject;
            try {
                /****** Creates a new JSONObject with name/value mappings from the JSON string. ********/
                responseObject = new JSONObject(data);
                JSONArray earthquakes = responseObject.getJSONArray(EARTHQUAKE_TAG);

                // Iterate over earthquakes list
                for (int idx = 0; idx < earthquakes.length(); idx++) {

                    // Get single earthquake data - a Map
                    JSONObject earthquake = (JSONObject) earthquakes.get(idx);

                    // Summarize earthquake data as a string and add it to
                    // result
                    result.add("Magnitude: "
                            + earthquake.get(MAGNITUDE_TAG) + ", "
                            + LATITUDE_TAG + " :"
                            + earthquake.getString(LATITUDE_TAG) + ", "
                            + LONGITUDE_TAG + " :"
                            + earthquake.get(LONGITUDE_TAG));

                }
                Log.v(TAG, "Result:" + result);
            } catch (JSONException e) {

                e.printStackTrace();
            }

            return result;
        }

        @Override
        protected void onPostExecute(List<String> dataList) {

            // Close progress dialog
            Dialog.dismiss();

            ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(), R.layout.list_item, dataList);
            listView.setAdapter(adapter);

        }


    }

}
