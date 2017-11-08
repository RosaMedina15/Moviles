package hamp.universidad.app.com.appuniversidaddelavida.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import hamp.universidad.app.com.appuniversidaddelavida.R;
import hamp.universidad.app.com.appuniversidaddelavida.entity.Docente;

/**
 * Created by hamp on 08/11/2017.
 */

public class DocenteAdapter extends RecyclerView.Adapter<DocenteAdapter.DocenteHolder> {

    List<Docente> listDocente;

    public DocenteAdapter(List<Docente> listDocente) {
        this.listDocente = listDocente;
    }

    @Override
    public DocenteHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View vista= LayoutInflater.from(parent.getContext()).inflate(R.layout.docente_list,parent,false);

        RecyclerView.LayoutParams layoutParams=new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        vista.setLayoutParams(layoutParams);

        return new DocenteHolder(vista);
    }

    @Override
    public void onBindViewHolder(DocenteHolder holder, int position) {

        holder.txtCodeu.setText(listDocente.get(position).getCodeu().toString());
        holder.txtNombre.setText(listDocente.get(position).getNombre().toString());
        holder.txtApellido.setText(listDocente.get(position).getApellido().toString());

        if(listDocente.get(position).getImagen()!=null){
            holder.imagen.setImageBitmap(listDocente.get(position).getImagen());
        }else{
            holder.imagen.setImageResource(R.drawable.img_base);
        }
    }

    @Override
    public int getItemCount() {
        return listDocente.size();
    }

    public class DocenteHolder extends  RecyclerView.ViewHolder {

        TextView txtCodeu,txtNombre,txtApellido;
        ImageView imagen;


        public DocenteHolder(View itemView) {
            super(itemView);

            txtCodeu= (TextView) itemView.findViewById(R.id.idCodeu);
            txtNombre= (TextView) itemView.findViewById(R.id.idNombre);
            txtApellido= (TextView) itemView.findViewById(R.id.idApellido);

            imagen=(ImageView) itemView.findViewById(R.id.idImagen);
        }
    }
}
