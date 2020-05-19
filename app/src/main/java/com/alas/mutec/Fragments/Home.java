package com.alas.mutec.Fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.alas.mutec.Api.AdaptadorFirebase;
import com.alas.mutec.Api.Articulo;
import com.alas.mutec.MainActivity;
import com.alas.mutec.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static androidx.constraintlayout.widget.Constraints.TAG;

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


    private LinearLayout searchRootLayout;



    public Home() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vista= inflater.inflate(R.layout.fragment_home, container, false);
        searchRootLayout    = vista.findViewById(R.id.search_root_layout);
        scrollView          = vista.findViewById(R.id.scrollView);
        recyclerView = vista.findViewById(R.id.popularItemRecyclerView);

        //recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        lArticulo = new ArrayList<>();

        FirebaseDatabase database = FirebaseDatabase.getInstance();

        af = new AdaptadorFirebase(lArticulo);

        recyclerView.setAdapter(af);

        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),1,
                LinearLayoutManager.HORIZONTAL,false));

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