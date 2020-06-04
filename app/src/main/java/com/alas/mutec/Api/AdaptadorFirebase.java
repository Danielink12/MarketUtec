package com.alas.mutec.Api;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alas.mutec.Fragments.Login;
import com.alas.mutec.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdaptadorFirebase extends RecyclerView.Adapter<AdaptadorFirebase.ArticuloHolder> implements View.OnClickListener {
    List<Articulo> articulo;
   Context context;
   private View.OnClickListener listener;

    public AdaptadorFirebase(List<Articulo> articulo) {
        this.articulo = articulo;
    }

   /*  public AdaptadorFirebase(Class<Articulo> modelClass, int modelLayout, Class<ArticuloHolder> viewHolderClass, DatabaseReference ref, Context c)
    {
        super(modelClass, modelLayout, viewHolderClass, ref);
        context = c;
    } */

    @NonNull
    @Override
    public ArticuloHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //return null;
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_articulo_adap,parent,false);
        v.setOnClickListener(listener);
        ArticuloHolder holder = new ArticuloHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ArticuloHolder viewHolder, int position) {

        Articulo at = articulo.get(position);

        viewHolder.estadoArticulo.setText(at.getEstadoArticulo());
        viewHolder.lugar.setText(at.getLugar());
        viewHolder.megusta.setText( at.getMegusta());
        viewHolder.nombreArticulo.setText(at.getNombreArticulo());
        viewHolder.nombrePerfil.setText(at.getNombrePerfil());
        viewHolder.precio.setText(at.getPrecio());
        viewHolder.tiempo.setText(at.getTiempo());
        viewHolder.vendecompra.setText(at.getVendecompra());
        Picasso.get().load(at.getImagenPerfil()).into(viewHolder.imagenPerfil);
        Picasso.get().load(at.getImagenArticulo()).into(viewHolder.imagenArticulo);
      /*  Picasso.with(context).load(at.getImagenArticulo()).into(viewHolder.imagenArticulo);
        Picasso.with(context).load(at.getImagenPerfil()).into(viewHolder.imagenPerfil); */

    }

    @Override
    public int getItemCount() {
        return articulo.size();
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if(listener!=null){
            listener.onClick(view);
        }
    }

    public static class ArticuloHolder extends RecyclerView.ViewHolder{
        ImageView imagenPerfil, imagenArticulo;
        TextView nombrePerfil, tiempo, nombreArticulo;
        TextView precio;
        TextView lugar,vendecompra,estadoArticulo;
        TextView megusta;
        Context context;

        public ArticuloHolder(@NonNull View articulo_adaptador) {
            super(articulo_adaptador);
            imagenPerfil = articulo_adaptador.findViewById(R.id.profileCircleImageView);
            imagenArticulo = articulo_adaptador.findViewById(R.id.imagenArticulo);
            nombrePerfil = articulo_adaptador.findViewById(R.id.nameTextView);
            tiempo = articulo_adaptador.findViewById(R.id.addedDateStrTextView);
            nombreArticulo = articulo_adaptador.findViewById(R.id.titleTextView);
            precio = articulo_adaptador.findViewById(R.id.priceTextView);
            lugar = articulo_adaptador.findViewById(R.id.addressTextView);
            vendecompra = articulo_adaptador.findViewById(R.id.itemTypeNameTextView);
            estadoArticulo = articulo_adaptador.findViewById(R.id.conditionTextView);
            this.megusta = articulo_adaptador.findViewById(R.id.favCountTextView);
        }
    }
}
