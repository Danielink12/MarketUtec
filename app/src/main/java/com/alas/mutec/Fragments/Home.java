package com.alas.mutec.Fragments;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alas.mutec.AddArt;
import com.alas.mutec.Api.AdaptadorFirebase;
import com.alas.mutec.Api.AdaptadorRetrofitArticulos;
import com.alas.mutec.Api.ApiInterface;
import com.alas.mutec.Api.Articulo;
import com.alas.mutec.Api.ParametroPubs;
import com.alas.mutec.Api.PreferenceHelper;
import com.alas.mutec.Api.Pubs;
import com.alas.mutec.DetalleArticulo;
import com.alas.mutec.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class Home extends Fragment {

    private static final int MY_PERMISSIONS_REQUEST_READ_CALLPHONE = 1;
    private static final int MY_PERMISSIONS_REQUEST_READ_STORAGE = 1;
    private View vista;
    private NestedScrollView scrollView;
    RecyclerView recyclerView;
    List<Articulo> lArticulo;
    AdaptadorFirebase af;
    private String mId;
    Button btnadd;
    private PreferenceHelper preferenceHelper;


    //Region de articulos retrofit
    RecyclerView RR;
    List<Pubs> lra;
    AdaptadorRetrofitArticulos ara;

    RecyclerView RR2;
    List<Pubs> lra2;
    AdaptadorRetrofitArticulos ara2;

    RecyclerView RR3;
    List<Pubs> lra3;
    AdaptadorRetrofitArticulos ara3;

    RecyclerView RR4;
    List<Pubs> lra4;
    AdaptadorRetrofitArticulos ara4;

    private LinearLayout searchRootLayout;



    public Home() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vista= inflater.inflate(R.layout.fragment_home, container, false);

        PermisoLeerStorage();
        PermisoLlamadas();

        preferenceHelper = new PreferenceHelper(getContext());
        searchRootLayout    = vista.findViewById(R.id.search_root_layout);
        scrollView          = vista.findViewById(R.id.scrollView);
        recyclerView = vista.findViewById(R.id.popularItemRecyclerView);
        btnadd = vista.findViewById(R.id.addItemButton);
        RR = vista.findViewById(R.id.popularItemRecyclerView2);
        RR3 = vista.findViewById(R.id.RR3);
        RR4 = vista.findViewById(R.id.RR4);

        //recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

      //  lArticulo = new ArrayList<>();

        //FirebaseDatabase database = FirebaseDatabase.getInstance();

        //af = new AdaptadorFirebase(lArticulo);

        //recyclerView.setAdapter(af);

        //recyclerView.setLayoutManager(new GridLayoutManager(getContext(),1,
          //      LinearLayoutManager.HORIZONTAL,false));

        getPub25();
        getPub50();
        getPubPC();
        getPubIngSis();

       /* af.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Toast.makeText(getContext(), lArticulo.get(recyclerView.getChildAdapterPosition(view)).getImagenPerfil(), Toast.LENGTH_SHORT).show();

             //  Intent i = new Intent(getContext(), DetalleArticulo.class);
              // startActivity(i);

              /*  String telefono = "7747-4067";
                Toast.makeText(getContext(), lArticulo.get(recyclerView.getChildAdapterPosition(view)).getNombreArticulo(), Toast.LENGTH_SHORT).show();
                Intent _intencion = new Intent("android.intent.action.MAIN");
                _intencion.setComponent(new ComponentName("com.whatsapp","com.whatsapp.Conversation"));
                _intencion.putExtra("jid", PhoneNumberUtils.stripSeparators("503" + telefono)+"@s.whatsapp.net");
                startActivity(_intencion);
            }
        });*/

        //Region recycler view retrofit
       lra = new ArrayList<>();
        ara = new AdaptadorRetrofitArticulos(lra);
        recyclerView.setAdapter(ara);

        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),1,LinearLayoutManager.HORIZONTAL,false));

        ara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),DetalleArticulo.class);
                intent.putExtra("idpubli", lra.get(recyclerView.getChildAdapterPosition(view)).getPublicacion().idpublicacion);
                intent.putExtra("img",lra.get(recyclerView.getChildAdapterPosition(view)).getPublicacionImagen());
                intent.putExtra("subcat",lra.get(recyclerView.getChildAdapterPosition(view)).getSubcategoria());
                intent.putExtra("user",lra.get(recyclerView.getChildAdapterPosition(view)).getUsuario());
                intent.putExtra("celular",lra.get(recyclerView.getChildAdapterPosition(view)).getTelefonoUsuario());
                intent.putExtra("iduserpub",lra.get(recyclerView.getChildAdapterPosition(view)).publicacion.getIdusuario());
                intent.putExtra("imagenprofile",lra.get(recyclerView.getChildAdapterPosition(view)).getImagenusuario());
                startActivity(intent);
            }
        });

        lra2 = new ArrayList<>();
        ara2 = new AdaptadorRetrofitArticulos(lra2);
        RR.setAdapter(ara2);

        RR.setLayoutManager(new GridLayoutManager(getContext(),1,LinearLayoutManager.HORIZONTAL,false));

        ara2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),DetalleArticulo.class);
                intent.putExtra("idpubli", lra2.get(RR.getChildAdapterPosition(view)).getPublicacion().idpublicacion);
                intent.putExtra("img",lra2.get(RR.getChildAdapterPosition(view)).getPublicacionImagen());
                intent.putExtra("subcat",lra2.get(RR.getChildAdapterPosition(view)).getSubcategoria());
                intent.putExtra("user",lra2.get(RR.getChildAdapterPosition(view)).getUsuario());
                intent.putExtra("celular",lra2.get(RR.getChildAdapterPosition(view)).getTelefonoUsuario());
                intent.putExtra("iduserpub",lra2.get(RR.getChildAdapterPosition(view)).publicacion.getIdusuario());
                intent.putExtra("imagenprofile",lra2.get(RR.getChildAdapterPosition(view)).getImagenusuario());
                startActivity(intent);
            }
        });

        lra3 = new ArrayList<>();
        ara3 = new AdaptadorRetrofitArticulos(lra3);
        RR3.setAdapter(ara3);

        RR3.setLayoutManager(new GridLayoutManager(getContext(),1,LinearLayoutManager.HORIZONTAL,false));

        ara3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),DetalleArticulo.class);
                intent.putExtra("idpubli", lra3.get(RR3.getChildAdapterPosition(view)).getPublicacion().idpublicacion);
                intent.putExtra("img",lra3.get(RR3.getChildAdapterPosition(view)).getPublicacionImagen());
                intent.putExtra("subcat",lra3.get(RR3.getChildAdapterPosition(view)).getSubcategoria());
                intent.putExtra("user",lra3.get(RR3.getChildAdapterPosition(view)).getUsuario());
                intent.putExtra("celular",lra3.get(RR3.getChildAdapterPosition(view)).getTelefonoUsuario());
                intent.putExtra("iduserpub",lra3.get(RR3.getChildAdapterPosition(view)).publicacion.getIdusuario());
                intent.putExtra("imagenprofile",lra3.get(RR3.getChildAdapterPosition(view)).getImagenusuario());
                startActivity(intent);
            }
        });

        lra4 = new ArrayList<>();
        ara4 = new AdaptadorRetrofitArticulos(lra4);
        RR4.setAdapter(ara4);

        RR4.setLayoutManager(new GridLayoutManager(getContext(),1,LinearLayoutManager.HORIZONTAL,false));

        ara4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),DetalleArticulo.class);
                intent.putExtra("idpubli", lra4.get(RR4.getChildAdapterPosition(view)).getPublicacion().idpublicacion);
                intent.putExtra("img",lra4.get(RR4.getChildAdapterPosition(view)).getPublicacionImagen());
                intent.putExtra("subcat",lra4.get(RR4.getChildAdapterPosition(view)).getSubcategoria());
                intent.putExtra("user",lra4.get(RR4.getChildAdapterPosition(view)).getUsuario());
                intent.putExtra("celular",lra4.get(RR4.getChildAdapterPosition(view)).getTelefonoUsuario());
                intent.putExtra("iduserpub",lra4.get(RR4.getChildAdapterPosition(view)).publicacion.getIdusuario());
                intent.putExtra("imagenprofile",lra4.get(RR4.getChildAdapterPosition(view)).getImagenusuario());
                startActivity(intent);
            }
        });

       /* database.getReference().getRoot().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                lArticulo.removeAll(lArticulo);
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Articulo articulo = snapshot.getValue(Articulo.class);
                    lArticulo.add(articulo);
                }
                af.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        }); */

        //DatabaseReference referencia = database.getReference("marketutec");


        /*AdaptadorFirebase adaptadorFirebase = new AdaptadorFirebase(Articulo.class,R.layout.articulo_adaptador
                , ArticuloHolder.class,referencia, getContext());

        recyclerView.setAdapter(adaptadorFirebase);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2,
                LinearLayoutManager.HORIZONTAL,false));*/

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(preferenceHelper.getIsLog()) {
                    Intent i = new Intent(getContext(), AddArt.class);
                    startActivity(i);
                }else{
                    Toast.makeText(getContext(), "Primer debe Loguearse", Toast.LENGTH_SHORT).show();
                    Login nuevoFragmento = new Login();
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.nav_host_fragment, nuevoFragmento);
                    transaction.addToBackStack(null);

                    // Commit a la transacción
                    transaction.commit();
                }
            }
        });



        scrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY < oldScrollY) { // up
                    animateSearchBar(false);
                }
                if (scrollY > oldScrollY) { // down
                    animateSearchBar(true);
                }
            }
        });
        return vista;

    }

    boolean isSearchBarHide = false;

    private void animateSearchBar(final boolean hide) {
        if (isSearchBarHide && hide || !isSearchBarHide && !hide) return;
        isSearchBarHide = hide;
        int moveY = hide ? -(2 * searchRootLayout.getHeight()) : 0;
        searchRootLayout.animate().translationY(moveY).setStartDelay(100).setDuration(300).start();
    }

    /*public void getPub(){
        String id =preferenceHelper.getID();
        String tokrn = "Bearer "+preferenceHelper.getToken().replace("\"","");
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://104.215.72.31:8282/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiInterface jsonPlaceHolderApi = retrofit.create(ApiInterface.class);
        Call<List<PublicacionesGetModel>> gp = jsonPlaceHolderApi.getpubiduser(Integer.parseInt(id));
        gp.enqueue(new Callback<List<PublicacionesGetModel>>() {
            @Override
            public void onResponse(Call<List<PublicacionesGetModel>> call, Response<List<PublicacionesGetModel>> response) {
//                lra.removeAll(lra);
                ara.addAllItems(response.body());

                Log.d("PUUBSSSRETROFITTT", String.valueOf(response.body()));

            }

            @Override
            public void onFailure(Call<List<PublicacionesGetModel>> call, Throwable t) {

            }
        });


    }*/

    public void getPub25(){
        int a=0,b=0,c=0,d=50,e=0;
       // String id =preferenceHelper.getID();
       // String tokrn = "Bearer "+preferenceHelper.getToken().replace("\"","");
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


            }

            @Override
            public void onFailure(Call<List<Pubs>> call, Throwable t) {

            }
        });


    }

    public void getPub50(){
        int a=0,b=0,c=0,d=25,e=25;
        // String id =preferenceHelper.getID();
        // String tokrn = "Bearer "+preferenceHelper.getToken().replace("\"","");
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


                ara2.addAllItems(reverse);
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

    public void getPubPC(){
        int a=8,b=0,c=0,d=25,e=0;
        // String id =preferenceHelper.getID();
        // String tokrn = "Bearer "+preferenceHelper.getToken().replace("\"","");
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


                ara3.addAllItems(reverse);

            }

            @Override
            public void onFailure(Call<List<Pubs>> call, Throwable t) {

            }
        });


    }

    public void getPubIngSis(){
        int a=0,b=0,c=1,d=25,e=0;
        // String id =preferenceHelper.getID();
        // String tokrn = "Bearer "+preferenceHelper.getToken().replace("\"","");
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


                ara4.addAllItems(reverse);

            }

            @Override
            public void onFailure(Call<List<Pubs>> call, Throwable t) {

            }
        });


    }


    public void PermisoLlamadas(){
        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.CALL_PHONE)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.CALL_PHONE},
                        MY_PERMISSIONS_REQUEST_READ_CALLPHONE);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {
            // Permission has already been granted
        }
    }

    public void PermisoLeerStorage(){
        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST_READ_STORAGE);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {
            // Permission has already been granted
        }
    }



}

 /*   RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference referencia = database.getReference("Lenguajes");


        AdaptadorFirebase adaptadorFirebase = new AdaptadorFirebase(Lenguaje.class,R.layout.item_recycler
                ,LenguajeHolder.class,referencia,MainActivity.this);

        recyclerView.setAdapter(adaptadorFirebase);
        recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this,2,
                LinearLayoutManager.VERTICAL,false));
     */