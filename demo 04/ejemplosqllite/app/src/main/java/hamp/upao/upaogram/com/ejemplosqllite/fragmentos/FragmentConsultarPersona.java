package hamp.upao.upaogram.com.ejemplosqllite.fragmentos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import hamp.upao.upaogram.com.ejemplosqllite.ConexionSQLiteHelper;
import hamp.upao.upaogram.com.ejemplosqllite.R;
import hamp.upao.upaogram.com.ejemplosqllite.utilidades.Utilidades;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentConsultarPersona.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentConsultarPersona#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentConsultarPersona extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    View vista;
    EditText campoDni,campoNombre,campoTelefono;
    Button btnConsultar,btnActualizar,btnEliminar;

    ConexionSQLiteHelper conn;

    public FragmentConsultarPersona() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentConsultarPersona.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentConsultarPersona newInstance(String param1, String param2) {
        FragmentConsultarPersona fragment = new FragmentConsultarPersona();
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
        vista= inflater.inflate(R.layout.fragment_fragment_consultar_persona, container, false);
        campoDni= (EditText) vista.findViewById(R.id.documentodni);
        campoNombre= (EditText) vista.findViewById(R.id.campoNombreConsulta);
        campoTelefono= (EditText) vista.findViewById(R.id.campoTelefonoConsulta);

        btnConsultar=(Button) vista.findViewById(R.id.btnConsultar);
        btnActualizar=(Button) vista.findViewById(R.id.btnActualizar);
        btnEliminar=(Button) vista.findViewById(R.id.btnEliminar);

        conn=new ConexionSQLiteHelper(getActivity(),"bd_usuarios",null,1);

        btnConsultar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                consultarSql();
            }
        });

        btnActualizar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                actualizarPersona();
            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                eliminarPersona();
            }
        });

        return vista;
    }



    private void eliminarPersona() {
        SQLiteDatabase db=conn.getWritableDatabase();
        String[] parametros={campoDni.getText().toString()};

        db.delete(Utilidades.TABLA_PERSONA,Utilidades.CAMPO_DNI+"=?",parametros);
        Toast.makeText(getActivity(),"Ya se Eliminó la persona",Toast.LENGTH_LONG).show();
        campoDni.setText("");
        limpiar();
        db.close();
    }

    private void actualizarPersona() {
        SQLiteDatabase db=conn.getWritableDatabase();
        String[] parametros={campoDni.getText().toString()};
        ContentValues values=new ContentValues();
        values.put(Utilidades.CAMPO_NOMBRE,campoNombre.getText().toString());
        values.put(Utilidades.CAMPO_TELEFONO,campoTelefono.getText().toString());

        db.update(Utilidades.TABLA_PERSONA,values,Utilidades.CAMPO_DNI+"=?",parametros);
        Toast.makeText(getActivity(),"Ya se actualizó la persona",Toast.LENGTH_LONG).show();
        db.close();

    }
    private void consultar() {
        SQLiteDatabase db=conn.getReadableDatabase();
        String[] parametros={campoDni.getText().toString()};
        String[] campos={Utilidades.CAMPO_NOMBRE,Utilidades.CAMPO_TELEFONO};

        try {
            Cursor cursor =db.query(Utilidades.TABLA_PERSONA,campos,Utilidades.CAMPO_DNI+"=?",parametros,null,null,null);
            cursor.moveToFirst();
            campoNombre.setText(cursor.getString(0));
            campoTelefono.setText(cursor.getString(1));
            cursor.close();
        }catch (Exception e){
            Toast.makeText(getActivity(),"El dni no existe",Toast.LENGTH_LONG).show();
            limpiar();
        }
    }

    private void consultarSql() {
        SQLiteDatabase db=conn.getReadableDatabase();
        String[] parametros={campoDni.getText().toString()};

        try {

            Cursor cursor=db.rawQuery("SELECT "+ Utilidades.CAMPO_NOMBRE+","+Utilidades.CAMPO_TELEFONO+
                    " FROM "+Utilidades.TABLA_PERSONA+" WHERE "+Utilidades.CAMPO_DNI+"=? ",parametros);

            cursor.moveToFirst();
            campoNombre.setText(cursor.getString(0));
            campoTelefono.setText(cursor.getString(1));

        }catch (Exception e){
            Toast.makeText(getActivity(),"El dni no existe",Toast.LENGTH_LONG).show();
            limpiar();
        }

    }


    private void limpiar() {
        campoNombre.setText("");
        campoTelefono.setText("");
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
