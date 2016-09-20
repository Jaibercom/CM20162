package co.edu.udea.compumovil.socket;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class MainActivity extends AppCompatActivity {

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = (TextView) findViewById(R.id.textView1);
    }


    public void onClick(View v){

        new HttpGetTask().execute();
    }


    private class HttpGetTask extends AsyncTask<Void, Void, String> {

        private static final String HOST = "api.geonames.org";

        // Get your own user name at http://www.geonames.org/login
        private static final String USER_NAME = "aporter";
        private static final String HTTP_GET_COMMAND = "GET /earthquakesJSON?north=20&south=-20&east=-60&west=-80&username="
                + USER_NAME
                + " HTTP/1.1"
                + "\n"
                + "Host: "
                + HOST
                + "\n"
                + "Connection: close" + "\n\n";

        private static final String TAG = "HttpGet";

        @Override
        protected String doInBackground(Void... params) {
            Socket socket = null;
            String data = "";

            try {
                socket = new Socket(HOST, 80);
                PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
                Log.d(TAG, HTTP_GET_COMMAND);
                pw.println(HTTP_GET_COMMAND);

                data = readStream(socket.getInputStream());

            } catch (UnknownHostException exception) {
                exception.printStackTrace();
            } catch (IOException exception) {
                exception.printStackTrace();
            } finally {
                if (null != socket)
                    try {
                        socket.close();
                    } catch (IOException e) {
                        Log.e(TAG, "IOException");
                    }
            }
            return data;
        }

        @Override
        protected void onPostExecute(String result) {
            mTextView.setText(result);
            Log.d(TAG, "Result: " + result);
        }

        private String readStream(InputStream in) {
            BufferedReader reader = null;
            StringBuffer data = new StringBuffer();
            try {
                reader = new BufferedReader(new InputStreamReader(in));
                String line = "";
                while ((line = reader.readLine()) != null) {
                    data.append(line);
                }
            } catch (IOException e) {
                Log.e(TAG, "IOException");
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        Log.e(TAG, "IOException");
                    }
                }
            }
            return data.toString();
        }
    }

}
