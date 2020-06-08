package com.alas.mutec.Fragments;

import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.telephony.PhoneNumberUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alas.mutec.Api.ApiInterface;
import com.alas.mutec.Api.CPubModel;
import com.alas.mutec.Api.ParametroPubs;
import com.alas.mutec.Api.PreferenceHelper;
import com.alas.mutec.Api.Pubs;
import com.alas.mutec.Galeria;
import com.alas.mutec.MainActivity;
import com.alas.mutec.R;
import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Detalle_Articulo#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Detalle_Articulo extends Fragment {

    View vista;
    int idpublic,iduserpub;
    ImageView imageView;
    ArrayList<String> imageUrls = new ArrayList<String>();
    String img,subcat,user,cel;
    TextView txtnombreArticulo,txtHora,txtPrecio,txtLike,txtSCategoria,txtDescripcion,txtCompraVenta,txtUser,txtCel,txtContadorFotos;
    Button btnBorrar,btnWhatsapp,btnLlamada;
    private PreferenceHelper preferenceHelper;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Detalle_Articulo() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Detalle_Articulo.
     */
    // TODO: Rename and change types and number of parameters
    public static Detalle_Articulo newInstance(String param1, String param2) {
        Detalle_Articulo fragment = new Detalle_Articulo();
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
        Bundle getidpublic = getActivity().getIntent().getExtras();
        idpublic = getidpublic.getInt("idpubli");
        img = getidpublic.getString("img");
        subcat = getidpublic.getString("subcat");
        user = getidpublic.getString("user");
        cel = getidpublic.getString("celular");
        iduserpub = getidpublic.getInt("iduserpub");

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vista = inflater.inflate(R.layout.fragment_item, container, false);
        imageView = vista.findViewById(R.id.coverUserImageView);
        txtnombreArticulo = vista.findViewById(R.id.itemNameTextView);
        txtHora =vista.findViewById(R.id.activeHourTextView);
        txtPrecio=vista.findViewById(R.id.priceTextView);
        txtLike=vista.findViewById(R.id.likesTextView);
        txtSCategoria = vista.findViewById(R.id.categoryAndSubCategoryTextView);
        txtDescripcion = vista.findViewById(R.id.highlightInfoTextView);
        txtCompraVenta =vista.findViewById(R.id.saleBuyTextView);
        txtUser = vista.findViewById(R.id.userNameTextView);
        txtCel = vista.findViewById(R.id.phoneTextView);
        btnBorrar = vista.findViewById(R.id.btnBorrar);
        btnWhatsapp = vista.findViewById(R.id.chatButton);
        btnLlamada = vista.findViewById(R.id.callButton);
        txtContadorFotos = vista.findViewById(R.id.photoCountTextView);

        Log.d("cel",cel);


        if(preferenceHelper.getID()!=null) {
            if (iduserpub == Integer.parseInt(preferenceHelper.getID())) {
                btnBorrar.setVisibility(View.VISIBLE);
            }
        }

        btnWhatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent _intencion = new Intent("android.intent.action.MAIN");
                _intencion.setComponent(new ComponentName("com.whatsapp","com.whatsapp.Conversation"));
                _intencion.putExtra("jid", PhoneNumberUtils.stripSeparators("503" + cel)+"@s.whatsapp.net");
                startActivity(_intencion);
            }
        });

        btnLlamada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + cel.replace("-","")));
                startActivity(intent);
            }
        });

        btnBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(getContext())
                        //.setIcon(R.drawable.alacran)
                        .setTitle("¿Realmente desea eliminar la publicacion?")
                        .setCancelable(false)
                        .setNegativeButton(android.R.string.cancel, null)
                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                try {
                                    delpub();
                                    Toast.makeText(getContext(), "Publicacion eliminada!", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getContext(), MainActivity.class);
                                    startActivity(intent);
                                }
                                catch (Exception ex){
                                    Log.d("Errorazno alv",ex.toString());
                                }
                            }
                        }).show();
            }
        });


        Picasso.get().load(img).into(imageView);

        publicacion();

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), Galeria.class);
                intent.putExtra("id",idpublic);
                startActivity(intent);
            }
        });

        return vista;
    }

    public void publicacion() {
        int a = 0, b = 0, c = 0, d = 50, e = 0;
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://104.215.72.31:8282/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiInterface jsonPlaceHolderApi = retrofit.create(ApiInterface.class);
        Call<CPubModel> gp = jsonPlaceHolderApi.getidpub(idpublic);
        gp.enqueue(new Callback<CPubModel>() {
            @Override
            public void onResponse(Call<CPubModel> call, Response<CPubModel> response) {
                CPubModel cpm = response.body();
                txtnombreArticulo.setText(cpm.publicacion.getTitulo());
                txtHora.setText(cpm.publicacion.getF_Registro());
                txtPrecio.setText("$"+String.valueOf(cpm.publicacion.getPrecio()));
                txtLike.setText("0");
                txtSCategoria.setText(subcat);
                txtDescripcion.setText(cpm.publicacion.getDescripcion());
                txtCompraVenta.setText("De venta");
                txtUser.setText(user);
                txtCel.setText(cel);
                txtContadorFotos.setText(response.body().ListImg.size()+" Fotos");

                Picasso.get().load(img).into(imageView);
                //Glide.with(getContext()).load(cpm.listImg.get().getUrl()).into(imageView);

            }

            @Override
            public void onFailure(Call<CPubModel> call, Throwable t) {

            }
        });
    }

    public void delpub() {
        String tok = "Bearer "+preferenceHelper.getToken().replace("\"","");
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://104.215.72.31:8282/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiInterface jsonPlaceHolderApi = retrofit.create(ApiInterface.class);
        Call<ResponseBody> gp = jsonPlaceHolderApi.eliminarpub(idpublic,tok);
        gp.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()) {

                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

    }

}