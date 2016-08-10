package com.co.joluditru.layout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

public class ImageViewActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

        //No se asocia un recurso xml debido a que la imagen se crea de forma dinamica
	
		// Obtiene el Intent usado para iniciar esta actividad
		Intent intent = getIntent();
		
		// Crear un ImageView
		ImageView imageView = new ImageView(getApplicationContext());
		
		// Obtener el ID de la imagen a mostrar y pasarla al ImageView
		int resID = intent.getIntExtra(GridLayoutActivity.EXTRA_RES_ID, 0);
		imageView.setImageResource(resID);
		
		setContentView(imageView);
	}
}