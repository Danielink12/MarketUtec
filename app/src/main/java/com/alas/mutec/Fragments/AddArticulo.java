package com.alas.mutec.Fragments;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.StrictMode;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.alas.mutec.AddArt;
import com.alas.mutec.Api.ApiInterface;
import com.alas.mutec.Api.CPubModel;
import com.alas.mutec.Api.CarrerasModel;
import com.alas.mutec.Api.ImgPubModel;
import com.alas.mutec.Api.LoginModel;
import com.alas.mutec.Api.PreferenceHelper;
import com.alas.mutec.Api.PubModel;
import com.alas.mutec.Api.SCatModel;
import com.alas.mutec.MainActivity;
import com.alas.mutec.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import org.jibble.simpleftp.*;


import it.sauronsoftware.ftp4j.FTPAbortedException;
import it.sauronsoftware.ftp4j.FTPClient;
import it.sauronsoftware.ftp4j.FTPDataTransferException;
import it.sauronsoftware.ftp4j.FTPDataTransferListener;
import it.sauronsoftware.ftp4j.FTPException;
import it.sauronsoftware.ftp4j.FTPIllegalReplyException;


import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddArticulo#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddArticulo extends Fragment {

    ArrayList<ImgPubModel> aimg = new ArrayList<>();
    ImgPubModel auxaimg = new ImgPubModel();

    View vista;
    Spinner spinnerCarreras,subcspinner,estspinner;
    ImageView uno,dos,tres,cuatro,cinco;
    Button btnpub;
    String path1,path2,path3,path4,path5;
    ApiInterface apiInterface;
    private PreferenceHelper preferenceHelper;
    EditText txtDes,txtTitulo,txtprecio;

    private static final int uno_imagen = 100;
    private static final int dos_imagen = 200;
    private static final int tres_imagen = 300;
    private static final int cuatro_imagen = 400;
    private static final int cinco_imagen = 500;

    Uri imageUri;


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
        preferenceHelper = new PreferenceHelper(getContext());
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
        txtDes = vista.findViewById(R.id.etxtDescripcion);
        txtTitulo = vista.findViewById(R.id.etxtTitulo);
        txtprecio =vista.findViewById(R.id.etxtPrecio);

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
                try {
                    CrearPublicacion();
                    ftp4j();
                } catch (FTPException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (FTPIllegalReplyException e) {
                    e.printStackTrace();
                } catch (FTPDataTransferException e) {
                    e.printStackTrace();
                } catch (FTPAbortedException e) {
                    e.printStackTrace();
                } catch (Exception ex){
                    ex.printStackTrace();
                }
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
            String[] projection = {MediaStore.Images.Media.DATA};

            Cursor cursor = getContext().getContentResolver().query(imageUri, projection, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(projection[0]);
            path1 = cursor.getString(columnIndex);

            String[] urls = path1.split("Download/");
            String url="http://www.markutecda.info/imgmu/"+urls[1];
            auxaimg.setUrl(url);
            auxaimg.setTitulo("Imagen 1");
            auxaimg.setIdpublicacion(0);
            aimg.add(auxaimg);

            cursor.close();
            uno.setImageURI(imageUri);
        }else if (resultCode == RESULT_OK && requestCode == dos_imagen){
            imageUri = data.getData();
            String[] projection = {MediaStore.Images.Media.DATA};

            Cursor cursor = getContext().getContentResolver().query(imageUri, projection, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(projection[0]);
            path2 = cursor.getString(columnIndex);

            String[] urls = path2.split("Download/");
            String url="http://www.markutecda.info/imgmu/"+urls[1];
            auxaimg.setUrl(url);
            auxaimg.setTitulo("Imagen 2");
            auxaimg.setIdpublicacion(0);
            aimg.add(auxaimg);


            cursor.close();
            dos.setImageURI(imageUri);
        }else if (resultCode == RESULT_OK && requestCode == tres_imagen){
            imageUri = data.getData();
            String[] projection = {MediaStore.Images.Media.DATA};

            Cursor cursor = getContext().getContentResolver().query(imageUri, projection, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(projection[0]);
            path3 = cursor.getString(columnIndex);

            String[] urls = path3.split("Download/");
            String url="http://www.markutecda.info/imgmu/"+urls[1];
            auxaimg.setUrl(url);
            auxaimg.setTitulo("Imagen 3");
            auxaimg.setIdpublicacion(0);
            aimg.add(auxaimg);


            cursor.close();
            tres.setImageURI(imageUri);
        }else if (resultCode == RESULT_OK && requestCode == cuatro_imagen){
            imageUri = data.getData();
            String[] projection = {MediaStore.Images.Media.DATA};

            Cursor cursor = getContext().getContentResolver().query(imageUri, projection, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(projection[0]);
            path4 = cursor.getString(columnIndex);

            String[] urls = path4.split("Download/");
            String url="http://www.markutecda.info/imgmu/"+urls[1];
            auxaimg.setUrl(url);
            auxaimg.setTitulo("Imagen 4");
            auxaimg.setIdpublicacion(0);
            aimg.add(auxaimg);


            cursor.close();
            cuatro.setImageURI(imageUri);
        }else if (resultCode == RESULT_OK && requestCode == cinco_imagen){
            imageUri = data.getData();
            String[] projection = {MediaStore.Images.Media.DATA};

            Cursor cursor = getContext().getContentResolver().query(imageUri, projection, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(projection[0]);
            path5 = cursor.getString(columnIndex);

            String[] urls = path5.split("Download/");
            String url="http://www.markutecda.info/imgmu/"+urls[1];
            auxaimg.setUrl(url);
            auxaimg.setTitulo("Imagen 5");
            auxaimg.setIdpublicacion(0);
            aimg.add(auxaimg);


            cursor.close();
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

    public void ftp4j() throws FTPException, IOException, FTPIllegalReplyException, FTPDataTransferException, FTPAbortedException {

        FTPClient client = new FTPClient();
        client.connect("markutecda.info", 21);
        client.login("danielito@markutecda.info", "Hackerman12");
        client.changeDirectory("/public_html/imgmu");
        client.setType(FTPClient.TYPE_BINARY);
       // String dir = client.currentDirectory();
       // client.createDirectory("newfolder");

        if(path1!=null) {
            client.upload(new java.io.File(path1));
        }
        if(path2!=null) {
            client.upload(new java.io.File(path2));
        }
        if(path3!=null) {
            client.upload(new java.io.File(path3));
        }
        if(path4!=null) {
            client.upload(new java.io.File(path4));
        }
        if(path5!=null) {
            client.upload(new java.io.File(path5));
        }

        //client.upload(new java.io.File("/storage/emulated/0/ingles.pdf"));
        client.disconnect(true);
    }

    public void CrearPublicacion(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://13.66.170.249:8282/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiInterface jsonPlaceHolderApi = retrofit.create(ApiInterface.class);
        String tokn =preferenceHelper.getToken();
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //Download/
        int idpublicacion=0,idusuario=Integer.parseInt(preferenceHelper.getID()),idsubcategoria=subcspinner.getSelectedItemPosition()+1;
        String Descripcion=txtDes.getText().toString(),Titulo=txtTitulo.getText().toString();
        String F_Registro = df.format(c.getTime());
        double Precio=Integer.parseInt(txtprecio.getText().toString());
        int idtipublicacion=0,idcarrera=preferenceHelper.getIdcarrera(),Estado=1;

        Call<ResponseBody> cp = jsonPlaceHolderApi.crearpub(new CPubModel(new PubModel(idpublicacion,idusuario,idsubcategoria,Descripcion,Titulo,F_Registro,Precio,idtipublicacion,idcarrera,Estado), aimg),tokn);
        cp.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()){
                    Toast.makeText(getContext(), "Publicacion creada con exito!", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getContext(), MainActivity.class);
                    startActivity(i);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("EROOOROROROROOROR",t.toString());
            }
        });

    }

    /*
    public void CrearPublicacion(){
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //Download/
        int idpublicacion=0,idusuario=Integer.parseInt(preferenceHelper.getID()),idsubcategoria=subcspinner.getSelectedItemPosition()+1;
        String Descripcion=txtDes.getText().toString(),Titulo=txtTitulo.getText().toString();
        String F_Registro = df.format(c.getTime());
        double Precio=Integer.parseInt(txtprecio.getText().toString());
        int idtipublicacion=0,idcarrera=preferenceHelper.getIdcarrera(),Estado=1;
        String[] urls = path1.split("Download/");
        String url="http://www.markutecda.info/imgmu/"+urls[1],Tituloimg="imagen de publicacion";
         Call<String> cp = apiInterface.crearpub(new PubModel(idpublicacion,idusuario,idsubcategoria,Descripcion,Titulo,F_Registro,Precio,idtipublicacion,idcarrera,Estado),new ImgPubModel(idpublicacion,url,Tituloimg));
        cp.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.d("resposeeeee",response.toString());

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d("errrrrrorrr",t.toString());

            }
        });

    }
     */



}
