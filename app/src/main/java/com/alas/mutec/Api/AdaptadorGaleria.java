package com.alas.mutec.Api;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alas.mutec.R;
import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class AdaptadorGaleria extends RecyclerView.Adapter<AdaptadorGaleria.GaleriaHolder> implements View.OnClickListener {

    List<ImgPubModel> imagenes;
    private View.OnClickListener listener;
    Context context;

    public AdaptadorGaleria(List<ImgPubModel> listimagenes) {
        listimagenes = imagenes;
    }


    @Override
    public void onClick(View view) {
        if(listener!=null){
            listener.onClick(view);
        }
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    public  void addAllItems(List<ImgPubModel> items) {
        imagenes.addAll(items);
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public GaleriaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_imagen_galeria_adaptador,parent,false);
        v.setOnClickListener(listener);
        AdaptadorGaleria.GaleriaHolder holder = new AdaptadorGaleria.GaleriaHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull GaleriaHolder holder, int position) {
        ImgPubModel ipm = imagenes.get(position);

        Picasso.get().load(ipm.getUrl()).into(holder.imagen);
       // Glide.with(context).load(imagenes.get(position)).into(holder.imagen);

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class GaleriaHolder extends RecyclerView.ViewHolder {
        ImageView imagen;

        public GaleriaHolder(@NonNull View articulo_adaptador) {
            super(articulo_adaptador);
            imagen = articulo_adaptador.findViewById(R.id.imgcontainer);
        }
    }
}
