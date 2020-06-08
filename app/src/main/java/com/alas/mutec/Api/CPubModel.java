package com.alas.mutec.Api;

import java.util.List;

public class CPubModel {
    public PubModel publicacion;
    public List<ImgPubModel> ListImg;

    public CPubModel() {
    }

    public CPubModel(PubModel publicacion, List<ImgPubModel> ListImg) {
        this.publicacion = publicacion;
        this.ListImg = ListImg;
    }

    public PubModel getPublicacion() {
        return publicacion;
    }

    public void setPublicacion(PubModel publicacion) {
        this.publicacion = publicacion;
    }

    public List<ImgPubModel> getListImg() {
        return ListImg;
    }

    public void setListImg(List<ImgPubModel> listImg) {
        this.ListImg = listImg;
    }
}
