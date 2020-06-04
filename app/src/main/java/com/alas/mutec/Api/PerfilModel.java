package com.alas.mutec.Api;

public class PerfilModel {
    public int idusuario,idtipousuario;
    public String Carnet,Nombre,Apellido,Correo,Telefono_;
    public int idcarrera,Estado,Betado;
    public String Clave;
    public PerfilModel(int idusuario, int idtipousuario, String carnet, String nombre, String apellido, String correo, String telefono_, int idcarrera, int estado, int betado, String clave) {
        this.idusuario = idusuario;
        this.idtipousuario = idtipousuario;
        Carnet = carnet;
        Nombre = nombre;
        Apellido = apellido;
        Correo = correo;
        Telefono_ = telefono_;
        this.idcarrera = idcarrera;
        Estado = estado;
        Betado = betado;
        Clave = clave;
    }

    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }

    public int getIdtipousuario() {
        return idtipousuario;
    }

    public void setIdtipousuario(int idtipousuario) {
        this.idtipousuario = idtipousuario;
    }

    public String getCarnet() {
        return Carnet;
    }

    public void setCarnet(String carnet) {
        Carnet = carnet;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String apellido) {
        Apellido = apellido;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String correo) {
        Correo = correo;
    }

    public String getTelefono_() {
        return Telefono_;
    }

    public void setTelefono_(String telefono_) {
        Telefono_ = telefono_;
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

    public int getBetado() {
        return Betado;
    }

    public void setBetado(int betado) {
        Betado = betado;
    }

    public String getClave() {
        return Clave;
    }

    public void setClave(String clave) {
        Clave = clave;
    }
}
