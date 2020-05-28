package com.alas.mutec.Api;

import java.sql.Date;

public class PubModel {
    public int idpublicacion,idusuario,idsubcategoria;
    public String Descripcion,Titulo;
    public Date F_Registro;
    public double Precio;
    public int idtipublicacion,idcarrera,Estado;

    public PubModel(int idpublicacion, int idusuario, int idsubcategoria, String descripcion, String titulo, Date f_Registro, double precio, int idtipublicacion, int idcarrera, int estado) {
        this.idpublicacion = idpublicacion;
        this.idusuario = idusuario;
        this.idsubcategoria = idsubcategoria;
        Descripcion = descripcion;
        Titulo = titulo;
        F_Registro = f_Registro;
        Precio = precio;
        this.idtipublicacion = idtipublicacion;
        this.idcarrera = idcarrera;
        Estado = estado;
    }

    public int getIdpublicacion() {
        return idpublicacion;
    }

    public void setIdpublicacion(int idpublicacion) {
        this.idpublicacion = idpublicacion;
    }

    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }

    public int getIdsubcategoria() {
        return idsubcategoria;
    }

    public void setIdsubcategoria(int idsubcategoria) {
        this.idsubcategoria = idsubcategoria;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String titulo) {
        Titulo = titulo;
    }

    public Date getF_Registro() {
        return F_Registro;
    }

    public void setF_Registro(Date f_Registro) {
        F_Registro = f_Registro;
    }

    public double getPrecio() {
        return Precio;
    }

    public void setPrecio(double precio) {
        Precio = precio;
    }

    public int getIdtipublicacion() {
        return idtipublicacion;
    }

    public void setIdtipublicacion(int idtipublicacion) {
        this.idtipublicacion = idtipublicacion;
    }

    public int getIdcarrera() {
        return idcarrera;
    }

    public void setIdcarrera(int idcarrera) {
        this.idcarrera = idcarrera;
    }

    public int getEstado() {
        return Estado;
    }

    public void setEstado(int estado) {
        Estado = estado;
    }
}
