package co.edu.udea.computacionmovil.materialdesign.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import co.edu.udea.computacionmovil.materialdesign.R;
import co.edu.udea.computacionmovil.materialdesign.model.Person;

/**
 * Created by joluditru on 11/08/2016.
 */
public class AdapterRecyclerView extends RecyclerView.Adapter<AdapterRecyclerView.PersonViewHolder> {

    List<Person> persons;

    public AdapterRecyclerView(List<Person> persons) {
        this.persons = persons;
    }

    //Métodos que se deben implementar en un RecyclerView
    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_person,parent,false);
        PersonViewHolder pvh = new PersonViewHolder(view);
        return pvh;
    }

    @Override
    public void onBindViewHolder(PersonViewHolder holder, int pos) {
        holder.personName.setText(persons.get(pos).getName());
        holder.personAge.setText(persons.get(pos).getAge());
        holder.personPhoto.setImageResource(persons.get(pos).getPhotoId());
    }

    @Override
    public int getItemCount() {
        return persons.size();
    }


    //Clase necesaria para la implementación del RecyclerView
    public static class PersonViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        TextView personName;
        TextView personAge;
        ImageView personPhoto;

        PersonViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.card_view);
            personName = (TextView) itemView.findViewById(R.id.person_name);
            personAge = (TextView) itemView.findViewById(R.id.person_age);
            personPhoto = (ImageView) itemView.findViewById(R.id.person_photo);
        }
    }
}
