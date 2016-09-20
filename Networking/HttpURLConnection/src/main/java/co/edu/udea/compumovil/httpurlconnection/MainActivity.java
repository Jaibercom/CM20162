package co.edu.udea.compumovil.httpurlconnection;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class MainActivity extends AppCompatActivity {

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = (TextView) findViewById(R.id.textView1);
    }

    public void onClick(View v){

        HttpGetTask task = new HttpGetTask();
        String request = null;

        switch(v.getId()){

            case R.id.buttonJSON:
                    request = "earthquakesJSON";

                break;

            case R.id.buttonXML:
                    request = "earthquakes";
                break;
        }
        task.execute(request);
    }

    private class HttpGetTask extends AsyncTask<String, Void, String> {

        private static final String TAG = "HttpGetTask";

        // Get your own user name at http://www.geonames.org/login
        private String url = null;
        private String host = "http://api.geonames.org/";
        private String request = null;
        private String params = "?north=20&south=-20&east=-60&west=-80&username=" ;
        private String userName = "aporter";

        @Override
        protected String doInBackground(String... params) {
            String data = "";
            request = params[0];
            //url = "http://api.geonames.org/earthquakesJSON?north=20&south=-20&east=-60&west=-80&username=" + USER_NAME;
            url = this.host + this.request + this.params + this.userName;

            HttpClient client = new HttpClient(url);
            data = client.httpGet();

            return data;
        }

        @Override
        protected void onPostExecute(String result) {

            mTextView.setText(result);
        }

    }

}
