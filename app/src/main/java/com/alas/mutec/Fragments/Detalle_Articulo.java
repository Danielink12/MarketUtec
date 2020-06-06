package com.alas.mutec.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.alas.mutec.R;
import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Detalle_Articulo#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Detalle_Articulo extends Fragment {

    View vista;
    int idpublic;
    ImageView imageView;
    ArrayList<String> imageUrls = new ArrayList<String>();
    String url;

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

      //  Bundle getidpublic = getActivity().getIntent().getExtras();
      //  idpublic = getidpublic.getInt("idpubli");


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
       /* url = "http://www.markutecda.info/imgmu/cover3.jpg";
        imageUrls.add(url);
        url = "http://www.markutecda.info/imgmu/yo.jpg";
        imageUrls.add(url);
       // Glide.with(getContext()).load(imageUrls).into(imageView);
        Picasso.get().load(imageUrls.get(0)).into(imageView);
        Picasso.get().load(imageUrls.get(1)).into(imageView); */
        return vista;
    }
}