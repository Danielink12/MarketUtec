package com.alas.mutec.Api;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alas.mutec.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdaptadorRetrofitArticulos extends RecyclerView.Adapter<AdaptadorRetrofitArticulos.ArticuloHolder> implements View.OnClickListener  {
    List<Pubs> articulo;
    private View.OnClickListener listener;


    public AdaptadorRetrofitArticulos(List<Pubs> articulo) {
        this.articulo = articulo;
    }

    @NonNull
    @Override
    public AdaptadorRetrofitArticulos.ArticuloHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //return null;
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_articulo_adap,parent,false);
        v.setOnClickListener(listener);
        AdaptadorRetrofitArticulos.ArticuloHolder holder = new AdaptadorRetrofitArticulos.ArticuloHolder(v);
        return holder;
    }


    public  void addAllItems(List<Pubs> items) {
        articulo.addAll(items);
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorRetrofitArticulos.ArticuloHolder viewHolder, int position) {

        Pubs at = articulo.get(position);

        viewHolder.estadoArticulo.setText(at.getSubcategoria());
        viewHolder.lugar.setText("Utec");
        viewHolder.megusta.setText("0");
        viewHolder.nombreArticulo.setText(at.publicacion.getTitulo());
        viewHolder.nombrePerfil.setText(at.getUsuario());
        viewHolder.precio.setText("$"+String.valueOf(at.publicacion.getPrecio()));
        viewHolder.tiempo.setText(at.publicacion.getF_Registro());
        viewHolder.vendecompra.setText("De venta");
        if(at.publicacion.getEstado()==1){
            viewHolder.Vendido.setVisibility(View.INVISIBLE);
        }else{
            viewHolder.Vendido.setVisibility(View.VISIBLE);
        }
        if(at.getImagenusuario().equals("noimage.jpg")){
            Picasso.get().load(R.drawable.man).into(viewHolder.imagenPerfil);
        }else{
            Picasso.get().load(at.getImagenusuario()).into(viewHolder.imagenPerfil);
        }
        //Picasso.get().load(at.getImagenusuario()).into(viewHolder.imagenPerfil);
        if(at.getPublicacionImagen()!="noimage.jpg") {
            Picasso.get().load(at.getPublicacionImagen()).into(viewHolder.imagenArticulo);
        }
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


    public class ArticuloHolder extends RecyclerView.ViewHolder {
        ImageView imagenPerfil, imagenArticulo;
        TextView nombrePerfil, tiempo, nombreArticulo;
        TextView precio;
        TextView lugar,vendecompra,estadoArticulo;
        TextView megusta;
        TextView Vendido;

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
            Vendido =  articulo_adaptador.findViewById(R.id.isSoldTextView);
        }
    }
}
