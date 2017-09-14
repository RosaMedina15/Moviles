package hamp.upao.upaogram.com.ejemplosqllite.fragmentos;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import hamp.upao.upaogram.com.ejemplosqllite.ConexionSQLiteHelper;
import hamp.upao.upaogram.com.ejemplosqllite.R;
import hamp.upao.upaogram.com.ejemplosqllite.adaptadores.ListaPersonasAdapter;
import hamp.upao.upaogram.com.ejemplosqllite.entidades.Persona;
import hamp.upao.upaogram.com.ejemplosqllite.utilidades.Utilidades;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentListaPersonas.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentListaPersonas#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentListaPersonas extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    ArrayList<Persona> listaPersona;
    RecyclerView recyclerViewPersonas;

    View vista;

    ConexionSQLiteHelper conn;

    public FragmentListaPersonas() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentListaPersonas.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentListaPersonas newInstance(String param1, String param2) {
        FragmentListaPersonas fragment = new FragmentListaPersonas();
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
        vista=inflater.inflate(R.layout.fragment_fragment_lista_personas, container, false);

        conn=new ConexionSQLiteHelper(getActivity(),"bd_usuarios",null,1);

        listaPersona=new ArrayList<>();

        recyclerViewPersonas= (RecyclerView) vista.findViewById(R.id.recyclerPersonas);
        //LinearLayoutManager llm=new LinearLayoutManager(getActivity());
        //llm.setOrientation(LinearLayoutManager.VERTICAL);
        //recyclerViewPersonas.setLayoutManager(llm);
        recyclerViewPersonas.setLayoutManager(new LinearLayoutManager(getActivity()));

        consultarListaPersonas();


        ListaPersonasAdapter adapter=new ListaPersonasAdapter(listaPersona);//,getActivity());
        recyclerViewPersonas.setAdapter(adapter);

        return vista;
    }

    private void consultarListaPersonas() {

        SQLiteDatabase db=conn.getReadableDatabase();

        Persona persona=null;

        Cursor cursor=db.rawQuery("SELECT * FROM "+ Utilidades.TABLA_PERSONA,null);

        while (cursor.moveToNext()){
            persona=new Persona();
            persona.setDni(cursor.getInt(0));
            persona.setNombre(cursor.getString(1));
            persona.setTelefono(cursor.getString(2));

            listaPersona.add(persona);
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
