package com.alas.mutec.Fragments;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.StrictMode;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.alas.mutec.Api.ApiInterface;
import com.alas.mutec.Api.CarrerasModel;
import com.alas.mutec.Api.SCatModel;
import com.alas.mutec.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import org.jibble.simpleftp.*;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddArticulo#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddArticulo extends Fragment {

    View vista;
    Spinner spinnerCarreras,subcspinner,estspinner;
    ImageView uno,dos,tres,cuatro,cinco;
    Button btnpub;

    private static final int uno_imagen = 100;
    private static final int dos_imagen = 200;
    private static final int tres_imagen = 300;
    private static final int cuatro_imagen = 400;
    private static final int cinco_imagen = 500;

    Uri imageUri;
    ImageView foto_gallery;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddArticulo() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddArticulo.
     */
    // TODO: Rename and change types and number of parameters
    public static AddArticulo newInstance(String param1, String param2) {
        AddArticulo fragment = new AddArticulo();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);

        try {
            Carreras();
            SCat();
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
        vista = inflater.inflate(R.layout.fragment_add_art, container, false);
        spinnerCarreras = vista.findViewById(R.id.carreraspinner);
        subcspinner = vista.findViewById(R.id.SubCSpinner);
        estspinner = vista.findViewById(R.id.spinnerEstado);
        btnpub = vista.findViewById(R.id.btnPublicar);
        uno = vista.findViewById(R.id.firstImageView);
        dos = vista.findViewById(R.id.secImageView);
        tres = vista.findViewById(R.id.thirdImageView);
        cuatro = vista.findViewById(R.id.fouthImageView);
        cinco = vista.findViewById(R.id.fifthImageView);

        uno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                galeriauno();

            }
        });

        dos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                galeriados();

            }
        });

        tres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                galeriatres();

            }
        });

        cuatro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                galeriacuatro();

            }
        });

        cinco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                galeriacinco();

            }
        });

        btnpub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ftpc();
            }
        });


        return vista;
    }

    private void galeriauno(){
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, uno_imagen);
    }
    private void galeriados(){
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, dos_imagen);
    }
    private void galeriatres(){
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, tres_imagen);
    }
    private void galeriacuatro(){
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, cuatro_imagen);
    }
    private void galeriacinco(){
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, cinco_imagen);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(resultCode == RESULT_OK && requestCode == uno_imagen){
            imageUri = data.getData();
            uno.setImageURI(imageUri);
        }else if (resultCode == RESULT_OK && requestCode == dos_imagen){
            imageUri = data.getData();
            dos.setImageURI(imageUri);
        }else if (resultCode == RESULT_OK && requestCode == tres_imagen){
            imageUri = data.getData();
            tres.setImageURI(imageUri);
        }else if (resultCode == RESULT_OK && requestCode == cuatro_imagen){
            imageUri = data.getData();
            cuatro.setImageURI(imageUri);
        }else if (resultCode == RESULT_OK && requestCode == cinco_imagen){
            imageUri = data.getData();
            cinco.setImageURI(imageUri);
        }
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

    public void SCat() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://13.66.170.249:8282/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiInterface jsonPlaceHolderApi = retrofit.create(ApiInterface.class);
        Call<List<SCatModel>> crs = jsonPlaceHolderApi.scat();
        crs.enqueue(new Callback<List<SCatModel>>() {
            @Override
            public void onResponse(Call<List<SCatModel>> call, Response<List<SCatModel>> response) {

                List<SCatModel> getscat = response.body();

                String[] items = new String[getscat.size()];
                for(int i=0; i<getscat.size(); i++){
                    items[i] = getscat.get(i).getSubcategoria();
                }
                ArrayAdapter<String> adapter;
                adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, items);
                subcspinner.setAdapter(adapter);

              /* for (CarrerasModel post : posts) {
                   String content = "";
                   content += "idcarrera: " + post.getIdcarrera() + "\n";
                   content += "Nombre: " + post.getNombre() + "\n";
                   content += "Descripcion: " + post.getDescripcion() + "\n";

                   txtResponse.append(content);

               } */

            }

            @Override
            public void onFailure(Call<List<SCatModel>> call, Throwable t) {

            }
        });
    }

    public void ftpc(){

        try {
            SimpleFTP ftp = new SimpleFTP();

            // Connect to an FTP server on port 21.
            ftp.connect("13.66.170,249", 21, "angel", "abcd1234");

            // Set binary mode.
            ftp.bin();

            // Change to a new working directory on the FTP server.
            ftp.cwd("public_html/imgmu");

            // Upload some files.
            ftp.stor(new File(String.valueOf(imageUri)));
            ftp.stor(new File("comicbot-latest.png"));

            // You can also upload from an InputStream, e.g.
           // ftp.stor(new FileInputStream(new File("test.png")), "test.png");
           // ftp.stor(someSocket.getInputStream(), "blah.dat");

            // Quit from the FTP server.
            ftp.disconnect();
        }
        catch (IOException e) {
            Log.d("erooorrrrftpppp",e.toString());
        }
        Log.d("imageeeeeeeeeee",imageUri.toString());

    }



}
