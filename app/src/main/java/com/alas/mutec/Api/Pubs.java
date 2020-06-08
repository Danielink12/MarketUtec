package com.alas.mutec.Api;

public class Pubs {

    public PublicacionesGetModel publicacion;
    String PublicacionImagen,Subcategoria,Usuario,Imagenusuario,TelefonoUSuario;

    public Pubs() {
    }

    public Pubs(PublicacionesGetModel publicacion, String publicacionImagen, String subcategoria, String usuario, String imagenusuario, String telefonoUsuario) {
        this.publicacion = publicacion;
        PublicacionImagen = publicacionImagen;
        Subcategoria = subcategoria;
        Usuario = usuario;
        Imagenusuario = imagenusuario;
        TelefonoUSuario = telefonoUsuario;
    }

    public PublicacionesGetModel getPublicacion() {
        return publicacion;
    }

    public void setPublicacion(PublicacionesGetModel publicacion) {
        this.publicacion = publicacion;
    }

    public String getPublicacionImagen() {
        return PublicacionImagen;
    }

    public void setPublicacionImagen(String publicacionImagen) {
        PublicacionImagen = publicacionImagen;
    }

    public String getSubcategoria() {
        return Subcategoria;
    }

    public void setSubcategoria(String subcategoria) {
        Subcategoria = subcategoria;
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String usuario) {
        Usuario = usuario;
    }

    public String getImagenusuario() {
        return Imagenusuario;
    }

    public void setImagenusuario(String imagenusuario) {
        Imagenusuario = imagenusuario;
    }

    public String getTelefonoUsuario() {
        return TelefonoUSuario;
    }

    public void setTelefonoUsuario(String telefonoUsuario) {
        TelefonoUSuario = telefonoUsuario;
    }

    /* {
        "publicacion": {
        "idpublicacion": 11,
                "idusuario": 10,
                "idsubcategoria": 6,
                "Descripcion": "libro de matematicas 1",
                "Titulo": "libro mate 1",
                "F_Registro": "2020-06-05T03:25:50.9",
                "Precio": 15.0,
                "idtipublicacion": 1,
                "idcarrera": 1,
                "Estado": 1
    },
        "PublicacionImagen": "http://dpweb01.tonohost.com/imgmu/imagen.jpeg",
            "Subcategoria": "Seminuevo",
            "Usuario": "Daniel Alas",
            "ImagenUsuario": "noimage.jpg",
            "TelefonoUSuario": "70430648"
    }*/
}
