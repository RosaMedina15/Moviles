package hamp.upao.upaogram.com.ejemplosqllite.adaptadores;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import hamp.upao.upaogram.com.ejemplosqllite.R;
import hamp.upao.upaogram.com.ejemplosqllite.entidades.Persona;

/**
 * Created by hamp on 13/09/2017.
 */

public class ListaPersonasAdapter extends RecyclerView.Adapter<ListaPersonasAdapter.PersonasViewHolder> {

    ArrayList<Persona> listaPersona;
    //Activity activity;

    public ListaPersonasAdapter(ArrayList<Persona> listaPersona){//, Activity activity) {
        this.listaPersona = listaPersona;
        //this.activity = activity;
    }

    @Override
    public PersonasViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_personas,parent,false);
        return new PersonasViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PersonasViewHolder holder, int position) {
        holder.documento.setText(listaPersona.get(position).getDni().toString());
        holder.nombre.setText(listaPersona.get(position).getNombre());
        holder.telefono.setText(listaPersona.get(position).getTelefono());
    }



    @Override
    public int getItemCount() {
        return listaPersona.size();
    }

    public class PersonasViewHolder extends RecyclerView.ViewHolder {
        TextView documento,nombre,telefono;

        public PersonasViewHolder(View itemView) {
            super(itemView);
            documento = (TextView) itemView.findViewById(R.id.textDocumento);
            nombre = (TextView) itemView.findViewById(R.id.textNombre);
            telefono = (TextView) itemView.findViewById(R.id.textTelefono);
        }
    }
}
