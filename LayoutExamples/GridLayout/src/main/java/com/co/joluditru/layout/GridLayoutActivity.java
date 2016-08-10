package com.co.joluditru.layout;

import java.util.ArrayList;
import java.util.Arrays;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

//This application uses some deprecated methods. 
//See UIViewPager for a more modern version of this application

public class GridLayoutActivity extends Activity {

	protected static final String EXTRA_RES_ID = "POS";

	//Lista de los ID's de las imagenes
	private ArrayList<Integer> flowersList = new ArrayList<>(
			Arrays.asList(R.drawable.image1, R.drawable.image2,
					R.drawable.image3, R.drawable.image4, R.drawable.image5,
					R.drawable.image6, R.drawable.image7, R.drawable.image8,
					R.drawable.image9, R.drawable.image10, R.drawable.image11,
					R.drawable.image12));

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_grid_layout);

		GridView gridview = (GridView) findViewById(R.id.gridView);

		// Se crea un nuevo ImageAdapter y se usa como Adapter para esta GridView
		gridview.setAdapter(new ImageAdapter(this, flowersList));

		// Implementar setOnItemClickListener en la GridView
		gridview.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				
				//Crear un Intent para iniciar la ImageViewActivity
				Intent intent = new Intent(GridLayoutActivity.this,ImageViewActivity.class);
				
				// Agregar el ID de la imagen seleccionada como Intent Extra
				intent.putExtra(EXTRA_RES_ID, (int) id);
				
				// Iniciar la ImageViewActivity
				startActivity(intent);
			}
		});
	}
}