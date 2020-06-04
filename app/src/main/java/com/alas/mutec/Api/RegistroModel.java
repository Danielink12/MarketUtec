package com.alas.mutec.Api;

import java.util.regex.Pattern;

public class RegistroModel {

   public int idusuario,idtipousuario;
   public String Carnet,Nombre,Apellido,Correo,Telefono_;
   public int idcarrera,Estado,Betado;
   public String Clave;

    public RegistroModel(int idusuario, int idtipousuario, String carnet, String nombre, String apellido, String correo, String telefono, int idcarrera, int estado, int betado, String clave) {
        this.idusuario = idusuario;
        this.idtipousuario = idtipousuario;
        Carnet = carnet;
        Nombre = nombre;
        Apellido = apellido;
        Correo = correo;
        Telefono_ = telefono;
        this.idcarrera = idcarrera;
        this.Estado = estado;
        this.Betado = betado;
        this.Clave = clave;
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

    public String getTelefono() {
        return Telefono_;
    }

    public void setTelefono(String telefono) {
        Telefono_ = telefono;
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
        this.Estado = estado;
    }

    public int getBetado() {
        return Betado;
    }

    public void setBetado(int betado) {
        this.Betado = betado;
    }

    public String getClave() {
        return Clave;
    }

    public void setClave(String clave) {
        this.Clave = clave;
    }

    /* {
         "idusuario":0,
         "idtipousuario":0,
         "Carnet":"25-2857-2018",
         "Nombre":"Felipe",
         "Apellido":"Pineada",
         "Correo":"pinedaalgo",
         "Telefono_":"22762316",
         "idcarrera":1,
         "Estado":1,
         "Betado":0,
         "Clave":1234
         }*/
}
