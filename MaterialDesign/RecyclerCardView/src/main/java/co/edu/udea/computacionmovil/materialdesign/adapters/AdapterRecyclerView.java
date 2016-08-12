package co.edu.udea.computacionmovil.materialdesign.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import co.edu.udea.computacionmovil.materialdesign.R;
import co.edu.udea.computacionmovil.materialdesign.activities.MainActivity;
import co.edu.udea.computacionmovil.materialdesign.model.Person;

/**
 * Created by joluditru on 11/08/2016.
 */
public class AdapterRecyclerView extends RecyclerView.Adapter<AdapterRecyclerView.PersonViewHolder>{

    List<Person> persons;

    // Provide a suitable constructor (depends on the kind of dataset)
    public AdapterRecyclerView(List<Person> persons) {
        this.persons = persons;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_person, parent, false);
        PersonViewHolder pvh = new PersonViewHolder(view);
        return pvh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(PersonViewHolder holder, int pos) {

        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.personName.setText(persons.get(pos).getName());
        holder.personAge.setText(persons.get(pos).getAge());
        holder.personPhoto.setImageResource(persons.get(pos).getPhotoId());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return persons.size();
    }


    //Clase necesaria para la implementación del RecyclerView
    public static class PersonViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public CardView cardView;
        public TextView personName;
        public TextView personAge;
        public ImageView personPhoto;

        PersonViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            cardView = (CardView) itemView.findViewById(R.id.card_view);
            personName = (TextView) itemView.findViewById(R.id.person_name);
            personAge = (TextView) itemView.findViewById(R.id.person_age);
            personPhoto = (ImageView) itemView.findViewById(R.id.person_photo);
        }

        @Override
        public void onClick(View view) {
            int pos = getAdapterPosition();
            Log.d("AdapterRecyclerView", "onClick: " + pos + "  Name: "+ personName.getText() );
            Toast.makeText(itemView.getContext(), "Hello: "+ personName.getText(), Toast.LENGTH_SHORT).show();
        }
    }
}
