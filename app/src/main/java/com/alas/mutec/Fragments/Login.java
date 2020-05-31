package com.alas.mutec.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alas.mutec.Api.ApiClient;
import com.alas.mutec.Api.ApiInterface;
import com.alas.mutec.Api.LoginModel;
import com.alas.mutec.Api.PreferenceHelper;
import com.alas.mutec.Api.RegistroModel;
import com.alas.mutec.Api.User;
import com.alas.mutec.MainActivity;
import com.alas.mutec.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.stream.JsonToken;

import java.util.regex.Pattern;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.GET;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Login#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Login extends Fragment {

    public static final Pattern CARNET = Pattern.compile("\\d{2}-\\d{4}-\\d{4}$");
    public static final Pattern PASSWORD = Pattern.compile("");


    View vista;

    ApiInterface apiInterface;
    TextInputEditText txtUser, txtPass;
    Button btn;
    TextView txtbtn;

    private PreferenceHelper preferenceHelper;

    String token ="";
    String[] id= token.split("id:");


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Login() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Login.
     */
    // TODO: Rename and change types and number of parameters
    public static Login newInstance(String param1, String param2) {
        Login fragment = new Login();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        preferenceHelper = new PreferenceHelper(getContext());
        super.onCreate(savedInstanceState);

        if(preferenceHelper.getIsLog()){
          /*  Intent intent = new Intent(MainActivity.this,WelcomeActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            getActivity().finish(); */

            Registro nuevoFragmento = new Registro();
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.nav_host_fragment, nuevoFragmento);
            transaction.addToBackStack(null);

            // Commit a la transacción
            transaction.commit();
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
        vista = inflater.inflate(R.layout.fragment_login, container, false);
        btn = vista.findViewById(R.id.button);
        txtUser = vista.findViewById(R.id.txtMail);
        txtPass = vista.findViewById(R.id.txtPass);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        txtbtn = vista.findViewById(R.id.txtbtn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!ValidarCarnet()){return;}
                log();
            }
        });

        txtbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Registro nuevoFragmento = new Registro();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment, nuevoFragmento);
                transaction.addToBackStack(null);

                // Commit a la transacción
                transaction.commit();
            }
        });

        return vista;
    }

    public void log(){
        String user = txtUser.getText().toString();
        String pass = txtPass.getText().toString();
        final Call<String> lg = apiInterface.login(new LoginModel(user,pass));
        lg.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, @NonNull Response<String> response) {
                token =response.body();

                if(response.isSuccessful()){
                    token =response.body();
                    String[] id= token.split("id:");
                    Toast.makeText(getContext(), id[1].replace("\"",""), Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getContext(), "Usuario o Password incorrecto.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(getContext(), "error", Toast.LENGTH_SHORT).show();
                Log.d("RESP",t.toString());
            }
        });

    }

    public Boolean ValidarCarnet(){
        String carnet= txtUser.getText().toString().trim();

        if(carnet.isEmpty()){
            txtUser.setError("Por favor ingrese su apellido ");
            return false;
        } else if(!CARNET.matcher(carnet).matches()){
            txtUser.setError("Ejem. 25-0457-2018");
            return false;
        }else{
            txtUser.setError(null);
            return true;
        }

    }

    public Boolean ValidarPassword(){
        String pass = txtPass.getText().toString().trim();

        if(pass.isEmpty()){
            txtPass.setError("Por favor ingrese su numero celular");
            return false;
        } else if(!PASSWORD.matcher(pass).matches()){
            txtPass.setError("Ejem. 6788-1000");
            return false;
        }else{
            txtPass.setError(null);
            return true;
        }

    }

}
