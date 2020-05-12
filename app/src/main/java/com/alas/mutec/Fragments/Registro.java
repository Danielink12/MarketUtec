package com.alas.mutec.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.alas.mutec.Api.ApiClient;
import com.alas.mutec.Api.ApiInterface;
import com.alas.mutec.Api.CarrerasModel;
import com.alas.mutec.Api.RegistroModel;
import com.alas.mutec.R;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Registro#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Registro extends Fragment {

    ApiInterface apiInterface;
    View vista;
    EditText txtNombre,txtApellidos,txtCarnet,txtMail,txtTelefono,txtPass;
    Button Rbtn;
    TextView txtbtn, txtResponse;
    Spinner spinnerCarreras;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Registro() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Registro.
     */
    // TODO: Rename and change types and number of parameters
    public static Registro newInstance(String param1, String param2) {
        Registro fragment = new Registro();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            Carreras();
        }
        catch (Exception ex){
            Toast.makeText(getContext(), "Server error", Toast.LENGTH_SHORT).show();
            Log.d("EL pedo",ex.toString());
        }

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vista = inflater.inflate(R.layout.fragment_registro, container, false);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        txtNombre = vista.findViewById(R.id.txtNombre);
        txtApellidos = vista.findViewById(R.id.txtApellidos);
        txtCarnet = vista.findViewById(R.id.txtCarnet);
        txtMail = vista.findViewById(R.id.txtMail);
        txtTelefono = vista.findViewById(R.id.txtTelefono);
        txtPass = vista.findViewById(R.id.txtPass);
        Rbtn = vista.findViewById(R.id.Rbtn);
        txtbtn = vista.findViewById(R.id.txtbtn);
        spinnerCarreras = vista.findViewById(R.id.spinnerCarreras);

        Rbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registro();
            }
        });

        txtbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Login nuevoFragmento = new Login();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment, nuevoFragmento);
                transaction.addToBackStack(null);

                // Commit a la transacción
                transaction.commit();

                Carreras();
            }
        });


        return vista;

    }

    public void registro(){
        int idusuario=0,idtipousuario=0;
        String Carnet = txtCarnet.getText().toString(),Nombre=txtNombre.getText().toString(),Apellido=txtApellidos.getText().toString(),Correo=txtMail.getText().toString(),Telefono=txtTelefono.getText().toString();
        int idcarrera=1,estado=1,betado=0,clave;
        String Sclave=txtPass.getText().toString();
        clave = Integer.parseInt(Sclave);
        Call<String> rg = apiInterface.registro(new RegistroModel(idusuario,idtipousuario,Carnet,Nombre,Apellido,Correo,Telefono,idcarrera,estado,betado,clave));
        rg.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful()){
                    Toast.makeText(getContext(), response.toString(), Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getContext(), response.toString(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(getContext(), t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void Carreras() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://13.66.170.249:8282/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiInterface jsonPlaceHolderApi = retrofit.create(ApiInterface.class);
        Call<List<CarrerasModel>> crs = jsonPlaceHolderApi.carreras();
       crs.enqueue(new Callback<List<CarrerasModel>>() {
           @Override
           public void onResponse(Call<List<CarrerasModel>> call, Response<List<CarrerasModel>> response) {

               List<CarrerasModel> posts = response.body();

               String[] items = new String[posts.size()];
               for(int i=0; i<posts.size(); i++){
                   items[i] = posts.get(i).getNombre();
               }
               ArrayAdapter<String> adapter;
               adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, items);
               spinnerCarreras.setAdapter(adapter);

              /* for (CarrerasModel post : posts) {
                   String content = "";
                   content += "idcarrera: " + post.getIdcarrera() + "\n";
                   content += "Nombre: " + post.getNombre() + "\n";
                   content += "Descripcion: " + post.getDescripcion() + "\n";

                   txtResponse.append(content);

               } */

           }

           @Override
           public void onFailure(Call<List<CarrerasModel>> call, Throwable t) {

           }
       });
    }
}
