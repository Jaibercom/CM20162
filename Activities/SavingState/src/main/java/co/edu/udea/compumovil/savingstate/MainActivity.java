package co.edu.udea.compumovil.savingstate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //String for LogCat
    private final String TAG = "LifeCycle";
    //// Use these as keys when you're saving state between reconfigurations
    private static final String RESTART_KEY = "restart";
    private static final String RESUME_KEY = "resume";
    private static final String START_KEY = "start";
    private static final String CREATE_KEY = "create";

    //variables for each of the TextViews
    private TextView mTvCreate;
    private TextView mTvRestart;
    private TextView mTvStart;
    private TextView mTvResume;

    //Counters variables
    private int mCreate;
    private int mRestart;
    private int mStart;
    private int mResume;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Assign the appropriate TextViews to the TextView variables
        mTvCreate = (TextView) findViewById(R.id.create);
        mTvRestart = (TextView) findViewById(R.id.restart);
        mTvStart = (TextView) findViewById(R.id.start);
        mTvResume = (TextView) findViewById(R.id.resume);

        // Has previous state been saved?
        if (savedInstanceState != null) {

            Log.d(TAG, "in onCreate - Restoring from save bundle");
            // TODO:
            // Restore value of counters from saved state
            // Only need 4 lines of code, one for every count variable
            mCreate = savedInstanceState.getInt(CREATE_KEY);
            mRestart = savedInstanceState.getInt(RESTART_KEY);
            mResume = savedInstanceState.getInt(RESUME_KEY);
            mStart = savedInstanceState.getInt(START_KEY);

        }

        // Emit LogCat message
        Log.d(TAG, "onCreate");

        mCreate++;
        // Update the user interface
        displayCounts();

    }

    @Override
    protected void onStart() {
        super.onStart();
        // Emit LogCat message
        Log.d(TAG, "onStart");
        mStart++;
        // Update the user interface
        displayCounts();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart");
        mRestart++;
        // Update the user interface
        displayCounts();

    }

    @Override
    protected void onResume() {
        super.onResume();
        // Emit LogCat message
        Log.d(TAG, "onResume");

        // Update the appropriate count variable
        mResume++;
        // Update the user interface
        displayCounts();
    }

    @Override
    protected void onStop() {
        super.onStop();

        Log.d(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Emit LogCat message
        Log.d(TAG, "onDestroy");
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Emit LogCat message
        Log.v(TAG, "onPause");
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {

        // Saving counter state information with a collection of key-value pairs
        // 4 lines of code, one for every count variable

        Log.d(TAG, "onSaveInstanceState");
        savedInstanceState.putInt(CREATE_KEY, mCreate);
        savedInstanceState.putInt(RESUME_KEY, mResume);
        savedInstanceState.putInt(RESTART_KEY, mRestart);
        savedInstanceState.putInt(START_KEY, mStart);
        super.onSaveInstanceState(savedInstanceState);

    }


    // Updates the displayed counters
    public void displayCounts() {

        mTvCreate.setText("onCreate() calls: " + mCreate);
        mTvStart.setText("onStart() calls: " + mStart);
        mTvResume.setText("onResume() calls: " + mResume);
        mTvRestart.setText("onRestart() calls: " + mRestart);

    }

}
