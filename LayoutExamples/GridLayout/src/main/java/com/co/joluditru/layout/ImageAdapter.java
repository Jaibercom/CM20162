package com.co.joluditru.layout;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {
	private static final int PADDING = 8;
	private static final int WIDTH = 250;
	private static final int HEIGHT = 250;
	private Context mContext;
	private List<Integer> mListaIdImagenes;

	// Constructor que guarda la lista de IDs de las imagenes
	public ImageAdapter(Context c, List<Integer> ids) {
		mContext = c;
		this.mListaIdImagenes = ids;
	}

	// Retorna el número de items en el Adapter
	@Override
	public int getCount() {
		return mListaIdImagenes.size();
	}

	// Retorna el dato del item de la posicion
	@Override
	public Object getItem(int position) {
		return mListaIdImagenes.get(position);
	}

	// Llamado para proveer el ID pasado al OnItemClickListener.onItemClick()
	@Override
	public long getItemId(int position) {
		return mListaIdImagenes.get(position);
	}

	// Return an ImageView for each item referenced by the Adapter
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		ImageView imageView = (ImageView) convertView;

		// Imagen a mostrar con algunos atributos
		if (imageView == null) {
			imageView = new ImageView(mContext);
			imageView.setLayoutParams(new GridView.LayoutParams(WIDTH, HEIGHT));
			imageView.setPadding(PADDING, PADDING, PADDING, PADDING);
			imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
		}

		imageView.setImageResource(mListaIdImagenes.get(position));
		return imageView;
	}
}
