package com.alas.mutec.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
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
import com.alas.mutec.MainActivity;
import com.alas.mutec.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

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

    public static final Pattern NOMBRE = Pattern.compile("^[A-Za-z áéíóúñÑÁÉÍÓÚ]{0,50}$");
    public static final Pattern APELLIDO = Pattern.compile("^[A-Za-z áéíóúñÑÁÉÍÓÚ]{0,50}$");
    public static final Pattern CARNET = Pattern.compile("\\d{2}-\\d{4}-\\d{4}$");
    public static final Pattern MAIL = Pattern.compile("^([0-9]{10})+\\@mail.utec.edu.sv|([A-za-z . 0-9])+\\@mail.utec.edu.sv|([A-za-z . 0-9])+\\@gmail.com|([A-za-z . 0-9])+\\@outlook.com|([A-za-z . 0-9])+\\@hotmail.com|([A-za-z . 0-9])+\\@yahoo.com");
    public static final Pattern TELEFONO = Pattern.compile("^[6789]\\d{3}-\\d{4}$");
    public static final Pattern PASSWORD = Pattern.compile("");

    ApiInterface apiInterface;
    View vista;
    TextInputEditText txtNombre,txtApellidos,txtCarnet,txtMail,txtTelefono,txtPass;
    Button Rbtn;
    TextView txtbtn, txtResponse;
    Spinner spinnerCarreras;
    BottomNavigationView btnv;
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
            Log.d("Error Spinner carreras",ex.toString());
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

        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);

        Rbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!ValidarNombre()|!ValidarApellido()|!ValidarCarnet()|!ValidarCorreo()|!ValidarTelefono()|!ValidarPassword()){return;}
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
            }
        });

        txtTelefono.addTextChangedListener(new TextWatcher() {
            int prevL = 0;
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                prevL = txtTelefono.getText().toString().length();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int length = s.length();
                if ((prevL < length) && (length == 4)) {
                    String data = txtTelefono.getText().toString();
                    txtTelefono.setText(data + "-");
                    txtTelefono.setSelection(length + 1);


                }

            }
        });

        txtCarnet.addTextChangedListener(new TextWatcher() {
            int prevL = 0;
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                prevL = txtCarnet.getText().toString().length();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int length = s.length();
                if ((prevL < length) && (length == 2 | length == 7)) {
                    String data = txtCarnet.getText().toString();
                    txtCarnet.setText(data + "-");
                    txtCarnet.setSelection(length + 1);


                }

            }
        });


        return vista;

    }

    public void registro(){
        int idusuario=0,idtipousuario=0;
        String Carnet = txtCarnet.getText().toString(),Nombre=txtNombre.getText().toString(),Apellido=txtApellidos.getText().toString(),Correo=txtMail.getText().toString(),Telefono=txtTelefono.getText().toString();
        int idcarrera=spinnerCarreras.getSelectedItemPosition()+1,estado=1,betado=0,clave;
        String Sclave=txtPass.getText().toString();
        //clave = Integer.parseInt(Sclave);
        Call<String> rg = apiInterface.registro(new RegistroModel(idusuario,idtipousuario,Carnet,Nombre,Apellido,Correo,Telefono,idcarrera,estado,betado,Sclave));
        rg.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful()){
                    if(response.body().equals("\"Este Usuario ya existe.\"")){
                        Toast.makeText(getContext(), "Este Usuario ya existe!", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getContext(), "Registro exitoso!", Toast.LENGTH_SHORT).show();
                        Login nuevoFragmento = new Login();
                        FragmentTransaction transaction = getFragmentManager().beginTransaction();
                        transaction.replace(R.id.nav_host_fragment, nuevoFragmento);
                        transaction.addToBackStack(null);

                        // Commit a la transacción
                        transaction.commit();
                    }
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
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://104.215.72.31:8282/")
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

    public Boolean ValidarNombre(){
        String nombre = txtNombre.getText().toString().trim();

        if(nombre.isEmpty()){
            txtNombre.setError("Por favor ingrese su nombre ");
            return false;
        } else if(!NOMBRE.matcher(nombre).matches()){
            txtNombre.setError("Expresiones permitidas [A-Z] [a-z] y letras con acentos");
            return false;
        }else{
            txtNombre.setError(null);
            return true;
        }

    }

    public Boolean ValidarApellido(){
        String apellido= txtApellidos.getText().toString().trim();

        if(apellido.isEmpty()){
            txtApellidos.setError("Por favor ingrese su apellido ");
            return false;
        } else if(!APELLIDO.matcher(apellido).matches()){
            txtApellidos.setError("Expresiones permitidas [A-Z] [a-z] y letras con acentos");
            return false;
        }else{
            txtApellidos.setError(null);
            return true;
        }

    }

    public Boolean ValidarCarnet(){
        String carnet= txtCarnet.getText().toString().trim();

        if(carnet.isEmpty()){
            txtCarnet.setError("Por favor ingrese su Carnet ");
            return false;
        } else if(!CARNET.matcher(carnet).matches()){
            txtCarnet.setError("Ejem. 25-0457-2018");
            return false;
        }else{
            txtCarnet.setError(null);
            return true;
        }

    }

    public Boolean ValidarCorreo(){
        String mail= txtMail.getText().toString().trim();

        if(mail.isEmpty()){
            txtMail.setError("Por favor ingrese su Correo ");
            return false;
        } else if(!MAIL.matcher(mail).matches()){
            txtMail.setError("Se acepta correo institucional, gmail, hotmail, yahoo");
            return false;
        }else{
            txtMail.setError(null);
            return true;
        }

    }

    public Boolean ValidarTelefono(){
        String telefono = txtTelefono.getText().toString().trim();

        if(telefono.isEmpty()){
            txtTelefono.setError("Por favor ingrese su numero celular");
            return false;
        } else if(!TELEFONO.matcher(telefono).matches()){
            txtTelefono.setError("Ejem. 6788-1000");
            return false;
        }else{
            txtTelefono.setError(null);
            return true;
        }

    }

    public Boolean ValidarPassword(){
        String pass = txtPass.getText().toString().trim();

        if(pass.isEmpty()){
            txtPass.setError("Por favor ingrese su numero password");
            return false;
        }else{
            txtPass.setError(null);
            return true;
        }

    }
}
