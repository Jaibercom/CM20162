package co.edu.udea.computacionmovil.materialdesign.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import co.edu.udea.computacionmovil.materialdesign.adapters.AdapterRecyclerView;
import co.edu.udea.computacionmovil.materialdesign.model.Person;
import co.edu.udea.computacionmovil.materialdesign.R;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvContent;
    private List<Person> personList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeDataPersons();

        rvContent = (RecyclerView) findViewById(R.id.rv_content);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        rvContent.setLayoutManager(linearLayoutManager);

        //Se crea el adaptador y se pasa la lista
        AdapterRecyclerView adapter = new AdapterRecyclerView(personList);
        rvContent.setAdapter(adapter);


    }

    //Se crea una lista de personas
    private void initializeDataPersons(){
        personList = new ArrayList<>();
        personList.add(new Person("Amy", "35 años", R.drawable.person1));
        personList.add(new Person("Jack", "20 años", R.drawable.person2));
        personList.add(new Person("Caroline", "17 años", R.drawable.person3));
        personList.add(new Person("Arianna", "26 años", R.drawable.person4));
        personList.add(new Person("Alice", "40 años", R.drawable.person5));
        personList.add(new Person("Peter", "21 años", R.drawable.person6));
    }
}
