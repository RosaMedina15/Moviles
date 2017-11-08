package hamp.universidad.app.com.appuniversidaddelavida.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import hamp.universidad.app.com.appuniversidaddelavida.R;
import hamp.universidad.app.com.appuniversidaddelavida.adapter.DocenteAdapter;
import hamp.universidad.app.com.appuniversidaddelavida.entity.Docente;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ListarDocentesFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ListarDocentesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListarDocentesFragment extends Fragment
     implements  Response.Listener<JSONObject>,Response.ErrorListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    List<Docente> docenteList;
    RecyclerView recyclerDocente;

    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;

    ProgressDialog progreso;


    public ListarDocentesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListarDocentesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ListarDocentesFragment newInstance(String param1, String param2) {
        ListarDocentesFragment fragment = new ListarDocentesFragment();
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
        View vista= inflater.inflate(R.layout.fragment_listar_docentes, container, false);

        docenteList=new ArrayList<>();

        recyclerDocente = (RecyclerView) vista.findViewById(R.id.idRecyclerDocente);

        recyclerDocente.setLayoutManager(new LinearLayoutManager(this.getContext()));

        recyclerDocente.setHasFixedSize(true);

        request= Volley.newRequestQueue(getContext());


        loadDocentes();
        return vista;
    }

    private void loadDocentes() {
        progreso=new ProgressDialog(getContext());
        progreso.setMessage("Listando docentes...");
        progreso.show();



        String url="https://apiunivida.herokuapp.com/listaImagenes.php";

        jsonObjectRequest=new JsonObjectRequest(Request.Method.GET,url,null,this,this);

        request.add(jsonObjectRequest);
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

    @Override
    public void onErrorResponse(VolleyError error) {
        progreso.hide();
        Toast.makeText(getContext(), "Error:"+error.toString(),Toast.LENGTH_LONG).show();
    }

    @Override
    public void onResponse(JSONObject response) {
        progreso.hide();
        Docente docente=null;

        JSONArray json=response.optJSONArray("docente");

        try{

            for (int i=0;i<json.length();i++){
                docente=new Docente();
                JSONObject jsonObject=null;
                jsonObject=json.getJSONObject(i);

                docente.setCodeu(jsonObject.optInt("codeu"));
                docente.setNombre(jsonObject.optString("nombre"));
                docente.setApellido(jsonObject.optString("apellido"));
                docente.setDato(jsonObject.optString("imagen"));

                docenteList.add(docente);
            }

            DocenteAdapter adapter=new DocenteAdapter(docenteList);
            recyclerDocente.setAdapter(adapter);

        }catch (JSONException e){
            Toast.makeText(getContext(), "No se ha podido establecer conexión con el servidor" +
                    " "+response, Toast.LENGTH_LONG).show();
        }

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
