package com.alas.mutec.Api;

import java.util.List;

public class CPubModel {
    public PubModel publicacion;
    public List<ImgPubModel> listImg;

    public CPubModel(PubModel publicacion, List<ImgPubModel> listImg) {
        this.publicacion = publicacion;
        this.listImg = listImg;
    }

    public PubModel getPublicacion() {
        return publicacion;
    }

    public void setPublicacion(PubModel publicacion) {
        this.publicacion = publicacion;
    }

    public List<ImgPubModel> getListImg() {
        return listImg;
    }

    public void setListImg(List<ImgPubModel> listImg) {
        this.listImg = listImg;
    }
}
