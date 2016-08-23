package co.edu.udea.computacionmovil.dynamic;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    Fragment fragmentA;
    Fragment fragmentB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState == null){

            fragmentA = new FragmentA();
            fragmentB = new FragmentB();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.fragment_container, fragmentA, "A");
            transaction.commit();
        }

        Log.d("MainActivity", "onCreate");

    }

    public void onClick(View view) {

        Log.d("MainActivity", "onClick");

        // Create new fragment and transaction

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();


        // Capture the fragment from the activity layout
        FragmentA fragment = (FragmentA)
                getSupportFragmentManager().findFragmentByTag("A");

        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack
        if (fragment != null) {
            Log.d("MainActivity", "es el Fragment A");
            transaction.replace(R.id.fragment_container, fragmentB, "B");
        }else
        {
            Log.d("MainActivity", "es el Fragment B");
            transaction.replace(R.id.fragment_container, fragmentA, "A");
        }

        //transaction.addToBackStack(null);         //Para que sirve ??

        // Commit the transaction
        transaction.commit();

    }


}
