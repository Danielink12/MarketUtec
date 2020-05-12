package com.alas.mutec.Api;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CarrerasModel {

    private int idcarrera;

    private String Nombre;

    private String Descripcion;

    public CarrerasModel(int idcarrera, String nombre, String descripcion) {
        this.idcarrera = idcarrera;
        Nombre = nombre;
        Descripcion = descripcion;
    }

    public int getIdcarrera() {
        return idcarrera;
    }

    public void setIdcarrera(int idcarrera) {
        this.idcarrera = idcarrera;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }
}