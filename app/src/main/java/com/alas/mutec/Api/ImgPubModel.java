package com.alas.mutec.Api;

public class ImgPubModel {
    public int idpublicacion;
    public String url,Titulo;

    public ImgPubModel(int idpublicacion, String url, String titulo) {
        this.idpublicacion = idpublicacion;
        this.url = url;
        Titulo = titulo;
    }

    public int getIdpublicacion() {
        return idpublicacion;
    }

    public void setIdpublicacion(int idpublicacion) {
        this.idpublicacion = idpublicacion;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String titulo) {
        Titulo = titulo;
    }
}
