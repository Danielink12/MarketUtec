package com.alas.mutec.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.alas.mutec.Api.ApiInterface;
import com.alas.mutec.Api.CarrerasModel;
import com.alas.mutec.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddArticulo#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddArticulo extends Fragment {

    View vista;
    Spinner spinnerCarreras;

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

        try {
            Carreras();
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

        return vista;
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
}
