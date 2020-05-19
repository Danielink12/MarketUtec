package com.alas.mutec.Api;

public class Articulo {
    String imagenPerfil, nombrePerfil, tiempo, imagenArticulo, nombreArticulo;
    String precio;
    String lugar,vendecompra,estadoArticulo;
    String megusta;

    public Articulo(){}

    public Articulo(String imagenPerfil, String nombrePerfil, String tiempo, String imagenArticulo, String nombreArticulo, String precio, String lugar, String vendecompra, String estadoArticulo, String megusta) {
        this.imagenPerfil = imagenPerfil;
        this.nombrePerfil = nombrePerfil;
        this.tiempo = tiempo;
        this.imagenArticulo = imagenArticulo;
        this.nombreArticulo = nombreArticulo;
        this.precio = precio;
        this.lugar = lugar;
        this.vendecompra = vendecompra;
        this.estadoArticulo = estadoArticulo;
        this.megusta = megusta;
    }

    public String getImagenPerfil() {
        return imagenPerfil;
    }

    public void setImagenPerfil(String imagenPerfil) {
        this.imagenPerfil = imagenPerfil;
    }

    public String getNombrePerfil() {
        return nombrePerfil;
    }

    public void setNombrePerfil(String nombrePerfil) {
        this.nombrePerfil = nombrePerfil;
    }

    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }

    public String getImagenArticulo() {
        return imagenArticulo;
    }

    public void setImagenArticulo(String imagenArticulo) {
        this.imagenArticulo = imagenArticulo;
    }

    public String getNombreArticulo() {
        return nombreArticulo;
    }

    public void setNombreArticulo(String nombreArticulo) {
        this.nombreArticulo = nombreArticulo;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getVendecompra() {
        return vendecompra;
    }

    public void setVendecompra(String vendecompra) {
        this.vendecompra = vendecompra;
    }

    public String getEstadoArticulo() {
        return estadoArticulo;
    }

    public void setEstadoArticulo(String estadoArticulo) {
        this.estadoArticulo = estadoArticulo;
    }

    public String getMegusta() {
        return megusta;
    }

    public void setMegusta(String megusta) {
        this.megusta = megusta;
    }

    @Override
    public String toString() {
        return "Articulo{" +
                "imagenPerfil='" + imagenPerfil + '\'' +
                ", nombrePerfil='" + nombrePerfil + '\'' +
                ", tiempo='" + tiempo + '\'' +
                ", imagenArticulo='" + imagenArticulo + '\'' +
                ", nombreArticulo='" + nombreArticulo + '\'' +
                ", precio=" + precio +
                ", lugar='" + lugar + '\'' +
                ", vendecompra='" + vendecompra + '\'' +
                ", estadoArticulo='" + estadoArticulo + '\'' +
                ", megusta=" + megusta +
                '}';
    }
}
