package com.alas.mutec.Api;

public class PublicacionesGetModel {
    public int idpublicacion,idusuario,idsubcategoria;
    public String Descripcion,Titulo,F_Registro;
    public float Precio,idtipublicacion,idcarrera,Estado;

    public PublicacionesGetModel() {
    }

    public PublicacionesGetModel(int idpublicacion, int idusuario, int idsubcategoria, String descripcion, String titulo, String f_Registro, float precio, float idtipublicacion, float idcarrera, float estado) {
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

    public String getF_Registro() {
        return F_Registro;
    }

    public void setF_Registro(String f_Registro) {
        F_Registro = f_Registro;
    }

    public float getPrecio() {
        return Precio;
    }

    public void setPrecio(float precio) {
        Precio = precio;
    }

    public float getIdtipublicacion() {
        return idtipublicacion;
    }

    public void setIdtipublicacion(float idtipublicacion) {
        this.idtipublicacion = idtipublicacion;
    }

    public float getIdcarrera() {
        return idcarrera;
    }

    public void setIdcarrera(float idcarrera) {
        this.idcarrera = idcarrera;
    }

    public float getEstado() {
        return Estado;
    }

    public void setEstado(float estado) {
        Estado = estado;
    }
}
