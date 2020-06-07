package com.alas.mutec;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.alas.mutec.Api.AdaptadorFirebase;
import com.alas.mutec.Api.AdaptadorGaleria;
import com.alas.mutec.Api.AdaptadorRetrofitArticulos;
import com.alas.mutec.Api.CPubModel;
import com.alas.mutec.Api.ImgPubModel;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Galeria extends AppCompatActivity {

    RecyclerView recyclerView;
    List<ImgPubModel> listimagenes,listaux;
    AdaptadorGaleria radapterimagenes;
    ImgPubModel auxaimg = new ImgPubModel();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galeria);
        Objects.requireNonNull(getSupportActionBar()).hide();

        recyclerView = findViewById(R.id.recyclerImg);

        listimagenes = new ArrayList<>();
        listaux = new ArrayList<>();

        radapterimagenes = new AdaptadorGaleria(listimagenes);

        recyclerView.setAdapter(radapterimagenes);

        recyclerView.setLayoutManager(new GridLayoutManager(this,1,
                LinearLayoutManager.VERTICAL,false));

        auxaimg.setIdpublicacion(1);
        auxaimg.setTitulo("imagen");
        auxaimg.setUrl("http://dpweb01.tonohost.com/imgmu/imagen.jpeg");
        listaux.add(auxaimg);

        auxaimg.setIdpublicacion(1);
        auxaimg.setTitulo("imagen");
        auxaimg.setUrl("http://dpweb01.tonohost.com/imgmu/imagen.jpeg");
        listaux.add(auxaimg);

        auxaimg.setIdpublicacion(2);
        auxaimg.setTitulo("imagen");
        auxaimg.setUrl("http://dpweb01.tonohost.com/imgmu/cover3.jpg");
        listaux.add(auxaimg);

        //listimagenes.addAll(listaux);

        //radapterimagenes.addAllItems(listimagenes);


        Log.d("imagenes",listimagenes.toString());

        radapterimagenes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}