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
import com.alas.mutec.Api.ApiInterface;
import com.alas.mutec.Api.CPubModel;
import com.alas.mutec.Api.ImgPubModel;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Galeria extends AppCompatActivity {

    RecyclerView recyclerView;
    List<ImgPubModel> listimagenes,listaux;
    AdaptadorGaleria radapterimagenes;
    ImgPubModel auxaimg = new ImgPubModel();
    int idpublic;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galeria);
        Objects.requireNonNull(getSupportActionBar()).hide();
        Bundle getidpublic = getIntent().getExtras();
        idpublic = getidpublic.getInt("id");
        Log.d("idididdididii", String.valueOf(idpublic));

        recyclerView = findViewById(R.id.recyclerImg);

        listimagenes = new ArrayList<>();
        listaux = new ArrayList<>();

        radapterimagenes = new AdaptadorGaleria(listimagenes);

        recyclerView.setAdapter(radapterimagenes);

        recyclerView.setLayoutManager(new GridLayoutManager(this,2,
                LinearLayoutManager.VERTICAL,false));

        publicacion();

    /*    auxaimg.setIdpublicacion(1);
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


        Log.d("imagenes",listimagenes.toString()); */

        radapterimagenes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    public void publicacion() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://104.215.72.31:8282/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiInterface jsonPlaceHolderApi = retrofit.create(ApiInterface.class);
        Call<CPubModel> gp = jsonPlaceHolderApi.getidpub(idpublic);
        gp.enqueue(new Callback<CPubModel>() {
            @Override
            public void onResponse(Call<CPubModel> call, Response<CPubModel> response) {
               radapterimagenes.addAllItems(response.body().ListImg);
            }

            @Override
            public void onFailure(Call<CPubModel> call, Throwable t) {
                Log.d("error",t.toString());
            }
        });

    }
}