package hamp.universidad.app.com.appuniversidaddelavida.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
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

import hamp.universidad.app.com.appuniversidaddelavida.R;
import hamp.universidad.app.com.appuniversidaddelavida.entity.Docente;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ConsultarDocenteFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ConsultarDocenteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ConsultarDocenteFragment extends Fragment
    implements Response.Listener<JSONObject>,Response.ErrorListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    EditText campoCodeu;
    TextView txtNombre,txtApellido;
    Button btnConsultarDocente;

    ImageView campoImagen;

    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;

    ProgressDialog progreso;

    public ConsultarDocenteFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ConsultarDocenteFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ConsultarDocenteFragment newInstance(String param1, String param2) {
        ConsultarDocenteFragment fragment = new ConsultarDocenteFragment();
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
        View vista=inflater.inflate(R.layout.fragment_consultar_docente, container, false);

        campoCodeu= (EditText) vista.findViewById(R.id.campoCodeu);
        txtNombre= (TextView) vista.findViewById(R.id.txtNombre);
        txtApellido= (TextView) vista.findViewById(R.id.txtApellido);
        btnConsultarDocente= (Button) vista.findViewById(R.id.btnConsultarDocente);
        campoImagen=(ImageView) vista.findViewById(R.id.imagenId);

        request= Volley.newRequestQueue(getContext());

        btnConsultarDocente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadDocente();
            }
        });

        return vista;
    }

    private void loadDocente() {
        progreso=new ProgressDialog(getContext());
        progreso.setMessage("Consultando docente...");
        progreso.show();

        String url="https://apiunivida.herokuapp.com/consultarDocente.php?codeu="
                +campoCodeu.getText().toString();

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
        Toast.makeText(getContext(),"No se pudo Consultar "+error.toString(),Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onResponse(JSONObject response) {
        progreso.hide();

        Docente docente=new Docente();
        JSONArray json=response.optJSONArray("docente");
        JSONObject jsonObject=null;

        try {
            jsonObject=json.getJSONObject(0);
            docente.setNombre(jsonObject.optString("nombre"));
            docente.setApellido(jsonObject.optString("apellido"));
            docente.setDato(jsonObject.optString("imagen"));
        }catch (JSONException e){
            e.printStackTrace();
        }

        txtNombre.setText("Nombre:"+docente.getNombre());
        txtApellido.setText("Apellido:"+docente.getApellido());

        if(docente.getImagen()!=null){
            campoImagen.setImageBitmap(docente.getImagen());
        }else{
            campoImagen.setImageResource(R.drawable.img_base);
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
