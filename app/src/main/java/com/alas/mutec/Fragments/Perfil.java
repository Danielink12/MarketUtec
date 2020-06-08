package com.alas.mutec.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.alas.mutec.Api.AdaptadorRetrofitArticulos;
import com.alas.mutec.Api.ApiInterface;
import com.alas.mutec.Api.CarrerasModel;
import com.alas.mutec.Api.IDModel;
import com.alas.mutec.Api.LoginModel;
import com.alas.mutec.Api.ParametroPubs;
import com.alas.mutec.Api.PerfilModel;
import com.alas.mutec.Api.PreferenceHelper;
import com.alas.mutec.Api.PublicacionesGetModel;
import com.alas.mutec.Api.Pubs;
import com.alas.mutec.DetalleArticulo;
import com.alas.mutec.R;
import com.google.gson.JsonObject;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Perfil#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Perfil extends Fragment {

    private Button btnlogout;
    private PreferenceHelper preferenceHelper;
    CircleImageView imagenPerfil;
    View vista;
    ApiInterface apiInterface;
    TextView txtnombre,txtcorreo,txtcarnet,txttelefono, txtcarrera;

    RecyclerView RR;
    List<Pubs> lra;
    AdaptadorRetrofitArticulos ara;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Perfil() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Perfil.
     */
    // TODO: Rename and change types and number of parameters
    public static Perfil newInstance(String param1, String param2) {
        Perfil fragment = new Perfil();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        preferenceHelper = new PreferenceHelper(getContext());
        try {
            getP();
            Carreras();
        }
        catch (Exception ex){
            Toast.makeText(getContext(), ex.toString(), Toast.LENGTH_SHORT).show();
            Log.d("aaaaaaaaaaaaaa",ex.toString());
        }

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
        vista = inflater.inflate(R.layout.fragment_perfil, container, false);
        btnlogout = vista.findViewById(R.id.btnlogout);
        txtnombre = vista.findViewById(R.id.txtNombre);
        txtcarnet = vista.findViewById(R.id.blood_group);
        txtcorreo = vista.findViewById(R.id.mobileNumber);
        txttelefono =vista.findViewById(R.id.occupation);
        txtcarrera = vista.findViewById(R.id.education);
        RR = vista.findViewById(R.id.popularItemRecyclerView);
        imagenPerfil = vista.findViewById(R.id.profile);

        lra = new ArrayList<>();
        ara = new AdaptadorRetrofitArticulos(lra);
        RR.setAdapter(ara);

        RR.setLayoutManager(new GridLayoutManager(getContext(),1, LinearLayoutManager.HORIZONTAL,false));

        getPub();

        ara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), DetalleArticulo.class);
                intent.putExtra("idpubli", lra.get(RR.getChildAdapterPosition(view)).getPublicacion().idpublicacion);
                intent.putExtra("img",lra.get(RR.getChildAdapterPosition(view)).getPublicacionImagen());
                intent.putExtra("subcat",lra.get(RR.getChildAdapterPosition(view)).getSubcategoria());
                intent.putExtra("user",lra.get(RR.getChildAdapterPosition(view)).getUsuario());
                intent.putExtra("celular",lra.get(RR.getChildAdapterPosition(view)).getTelefonoUsuario());
                intent.putExtra("iduserpub",lra.get(RR.getChildAdapterPosition(view)).publicacion.getIdusuario());
                startActivity(intent);
            }
        });



        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preferenceHelper.logueado(false);

                Login nuevoFragmento = new Login();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment, nuevoFragmento);
                transaction.addToBackStack(null);
                transaction.commit();


                /*
                Intent intent = new Intent(WelcomeActivity.this,MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                WelcomeActivity.this.finish(); */
            }
        });
        return vista;
    }

    public void getP(){
        String id =preferenceHelper.getID();
        String tokrn = "Bearer "+preferenceHelper.getToken().replace("\"","");
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://104.215.72.31:8282/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiInterface jsonPlaceHolderApi = retrofit.create(ApiInterface.class);
        Call<PerfilModel> gp = jsonPlaceHolderApi.getPerfil(id,tokrn);
        gp.enqueue(new Callback<PerfilModel>() {
            @Override
            public void onResponse(Call<PerfilModel> call, Response<PerfilModel> response) {
                if(response.isSuccessful()) {
                    PerfilModel profile = response.body();

                    preferenceHelper.setNombre(profile.getNombre());
                    preferenceHelper.setApellido(profile.getApellido());
                    preferenceHelper.setCorreo(profile.getCorreo());
                    preferenceHelper.setTelefono(profile.getTelefono_());
                    preferenceHelper.setCarnet(profile.getCarnet());
                    preferenceHelper.setIdcarrera(profile.getIdcarrera()-1);
                    txtnombre.setText(preferenceHelper.getNombre()+" "+preferenceHelper.getApellido());
                    txtcarnet.setText(preferenceHelper.getCarnet());
                    txtcorreo.setText(preferenceHelper.getCorreo());
                    txttelefono.setText(preferenceHelper.getTelefono());
                    Log.d("Imaen de perfil",profile.getImagen());
                    if(profile.getImagen().equals("noimage.jpg")){
                        imagenPerfil.setImageResource(R.drawable.man);
                    }else {
                        Picasso.get().load(profile.getImagen()).into(imagenPerfil);

                    }

                }else{
                    Toast.makeText(getContext(), "Sesion Expirada", Toast.LENGTH_SHORT).show();
                    preferenceHelper.logueado(false);
                    Login nuevoFragmento = new Login();
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.nav_host_fragment, nuevoFragmento);
                    transaction.addToBackStack(null);
                    transaction.commit();

                }
            }

            @Override
            public void onFailure(Call<PerfilModel> call, Throwable t) {

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
                    txtcarrera.setText( items[preferenceHelper.getIdcarrera()] = posts.get(preferenceHelper.getIdcarrera()).getNombre());

            }

            @Override
            public void onFailure(Call<List<CarrerasModel>> call, Throwable t) {

            }
        });
    }

    public void getPub_(){
        int a=0,b=0,c=0,d=50,e=0;
        String id =preferenceHelper.getID();
        String tokrn = "Bearer "+preferenceHelper.getToken().replace("\"","");
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://104.215.72.31:8282/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiInterface jsonPlaceHolderApi = retrofit.create(ApiInterface.class);
        Call<List<Pubs>> gp = jsonPlaceHolderApi.gpostpub(new ParametroPubs(a,b,c,d,e));
        gp.enqueue(new Callback<List<Pubs>>() {
            @Override
            public void onResponse(Call<List<Pubs>> call, Response<List<Pubs>> response) {
                // if(lra!=null){
                List<Pubs> reverse = response.body();
                Collections.reverse(reverse);


                ara.addAllItems(reverse);
                Calendar c = Calendar.getInstance();
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                String F_Registro = df.format(c.getTime());
                Log.d("HORRARARARARArA",F_Registro);

            }

            @Override
            public void onFailure(Call<List<Pubs>> call, Throwable t) {

            }
        });


    }

    public void getPub(){
        String id =preferenceHelper.getID();
        String tokrn = "Bearer "+preferenceHelper.getToken().replace("\"","");
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://104.215.72.31:8282/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiInterface jsonPlaceHolderApi = retrofit.create(ApiInterface.class);
        Call<List<Pubs>> gp = jsonPlaceHolderApi.getpubiduser(Integer.parseInt(id));
        gp.enqueue(new Callback<List<Pubs>>() {
            @Override
            public void onResponse(Call<List<Pubs>> call, Response<List<Pubs>> response) {
//                lra.removeAll(lra);
                List<Pubs> reverse = response.body();
                Collections.reverse(reverse);


                ara.addAllItems(reverse);

                Log.d("PUUBSSSRETROFITTT", String.valueOf(response.body()));

            }

            @Override
            public void onFailure(Call<List<Pubs>> call, Throwable t) {

            }
        });


    }

}