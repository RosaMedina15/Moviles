package hamp.upao.upaogram.com.ejemplosqllite.fragmentos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import hamp.upao.upaogram.com.ejemplosqllite.ConexionSQLiteHelper;
import hamp.upao.upaogram.com.ejemplosqllite.R;
import hamp.upao.upaogram.com.ejemplosqllite.entidades.Persona;
import hamp.upao.upaogram.com.ejemplosqllite.utilidades.Utilidades;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentRegMascota.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentRegMascota#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentRegMascota extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    EditText razaMascota,nombreMascota;
    Spinner comboDuenio;

    ArrayList<String> listaPersonas;
    ArrayList<Persona> personasList;

    View vista;
    Button btnRegistroMascota;

    ConexionSQLiteHelper conn;

    public FragmentRegMascota() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentRegMascota.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentRegMascota newInstance(String param1, String param2) {
        FragmentRegMascota fragment = new FragmentRegMascota();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vista=inflater.inflate(R.layout.fragment_fragment_reg_mascota, container, false);

        btnRegistroMascota=(Button) vista.findViewById(R.id.btnRegistroMascota);

        razaMascota= (EditText) vista.findViewById(R.id.campoRaza);
        nombreMascota= (EditText) vista.findViewById(R.id.campoNombreMascota);
        comboDuenio= (Spinner) vista.findViewById(R.id.comboDuenioMascota);

        consultarListaPersonas();

        ArrayAdapter<CharSequence> adaptador=new ArrayAdapter
                (vista.getContext(),android.R.layout.simple_spinner_item,listaPersonas);

        comboDuenio.setAdapter(adaptador);

        btnRegistroMascota.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                registrarMascota();
            }
        });

//        comboDuenio.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long idl) {
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });

        return vista;
    }



    private void registrarMascota() {

        conn=new ConexionSQLiteHelper(getActivity(),"bd_usuarios",null,1);

        SQLiteDatabase db=conn.getWritableDatabase();

        ContentValues values=new ContentValues();
        values.put(Utilidades.CAMPO_NOMBRE_MASCOTA,nombreMascota.getText().toString());
        values.put(Utilidades.CAMPO_RAZA_MASCOTA,razaMascota.getText().toString());

        int idCombo= (int) comboDuenio.getSelectedItemId();
        /**
         * Valida la seleccion del combo de dueños, si el usuario elige "seleccione" entonces
         * se retorna el id 0 ya que la palabra "seleccione" se encuentra en la pos 0 del combo,
         * sinó entonces se retorna la posicion del combo para consultar el usuario almacenado en la lista
         */
        if (idCombo!=0){
            Log.i("TAMAÑO",personasList.size()+"");
            Log.i("id combo",idCombo+"");
            Log.i("id combo - 1",(idCombo-1)+"");//se resta 1 ya que se quiere obtener la posicion de la lista, no del combo
            int idDuenio=personasList.get(idCombo-1).getDni();
            Log.i("id DUEÑO",idDuenio+"");

            values.put(Utilidades.CAMPO_ID_DUENIO,idDuenio);

            Long idResultante=db.insert(Utilidades.TABLA_MASCOTA,Utilidades.CAMPO_ID_MASCOTA,values);

            Toast.makeText(getContext(),"Id Registro: "+idResultante,Toast.LENGTH_SHORT).show();
            db.close();

        }else{
            Toast.makeText(getContext(),"Debe seleccionar un Dueño",Toast.LENGTH_LONG).show();
        }
    }

    private void consultarListaPersonas() {
        conn=new ConexionSQLiteHelper(vista.getContext(),"bd_usuarios",null,1);
        SQLiteDatabase db=conn.getReadableDatabase();

        Persona persona=null;
        personasList =new ArrayList<Persona>();
        //select * from usuarios
        Cursor cursor=db.rawQuery("SELECT * FROM "+ Utilidades.TABLA_PERSONA,null);

        while (cursor.moveToNext()){
            persona=new Persona();
            persona.setDni(cursor.getInt(0));
            persona.setNombre(cursor.getString(1));
            persona.setTelefono(cursor.getString(2));



            personasList.add(persona);

        }
        obtenerLista();
    }

    private void obtenerLista() {
        listaPersonas=new ArrayList<String>();
        listaPersonas.add("Seleccione");

        for(int i=0;i<personasList.size();i++){
            listaPersonas.add(personasList.get(i).getDni()+" - "+personasList.get(i).getNombre());
        }

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
