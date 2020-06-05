package com.alas.mutec.Fragments;


import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.telephony.PhoneNumberUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.alas.mutec.AddArt;
import com.alas.mutec.Api.AdaptadorFirebase;
import com.alas.mutec.Api.AdaptadorRetrofitArticulos;
import com.alas.mutec.Api.ApiInterface;
import com.alas.mutec.Api.Articulo;
import com.alas.mutec.Api.CPubModel;
import com.alas.mutec.Api.CarrerasModel;
import com.alas.mutec.Api.PreferenceHelper;
import com.alas.mutec.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
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
    List<CPubModel> lra;
    AdaptadorRetrofitArticulos ara;

    private LinearLayout searchRootLayout;



    public Home() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vista= inflater.inflate(R.layout.fragment_home, container, false);
        preferenceHelper = new PreferenceHelper(getContext());
        searchRootLayout    = vista.findViewById(R.id.search_root_layout);
        scrollView          = vista.findViewById(R.id.scrollView);
        recyclerView = vista.findViewById(R.id.popularItemRecyclerView);
        btnadd = vista.findViewById(R.id.addItemButton);

        //recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        lArticulo = new ArrayList<>();

        FirebaseDatabase database = FirebaseDatabase.getInstance();

        af = new AdaptadorFirebase(lArticulo);

        recyclerView.setAdapter(af);

        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),1,
                LinearLayoutManager.HORIZONTAL,false));

        af.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String telefono = "77474067";
                Toast.makeText(getContext(), lArticulo.get(recyclerView.getChildAdapterPosition(view)).getNombreArticulo(), Toast.LENGTH_SHORT).show();
                Intent _intencion = new Intent("android.intent.action.MAIN");
                _intencion.setComponent(new ComponentName("com.whatsapp","com.whatsapp.Conversation"));
                _intencion.putExtra("jid", PhoneNumberUtils.stripSeparators("503" + telefono)+"@s.whatsapp.net");
                startActivity(_intencion);
            }
        });

        //Region recycler view retrofit
      /*  lra = new ArrayList<>();
        ara = new AdaptadorRetrofitArticulos(lra);
        RR.setAdapter(ara);

        RR.setLayoutManager(new GridLayoutManager(getContext(),1,LinearLayoutManager.HORIZONTAL,false));

        ara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        }); */


        database.getReference().getRoot().addValueEventListener(new ValueEventListener() {
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
        });

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

    public void Pubs(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://13.66.170.249:8282/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiInterface jsonPlaceHolderApi = retrofit.create(ApiInterface.class);
        Call<List<CPubModel>> gpub = jsonPlaceHolderApi.getpub();
        gpub.enqueue(new Callback<List<CPubModel>>() {
            @Override
            public void onResponse(Call<List<CPubModel>> call, Response<List<CPubModel>> response) {
                List<CPubModel> pubs = response.body();
                ara.addAllItems(pubs);

               /* List<CPubModel> pubs = response.body();

                String[] items = new String[pubs.size()];

                for(int i=0; i<pubs.size(); i++){
                    items[i] = pubs.get(i).getNombre();
                }


                /*lArticulo.removeAll(lArticulo);
                for(Response response1 : response.getClass()){
                    Articulo articulo = snapshot.getValue(Articulo.class);
                    lArticulo.add(articulo);
                }
                af.notifyDataSetChanged();*/
            }

            @Override
            public void onFailure(Call<List<CPubModel>> call, Throwable t) {

            }
        });
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