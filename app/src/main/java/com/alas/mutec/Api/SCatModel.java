package com.alas.mutec.Api;

public class SCatModel {
    public int idsubcategoria,idcategoria;
    public String Subcategoria;

    public SCatModel(int idsubcategoria, int idcategoria, String subcategoria) {
        this.idsubcategoria = idsubcategoria;
        this.idcategoria = idcategoria;
        Subcategoria = subcategoria;
    }

    public int getIdsubcategoria() {
        return idsubcategoria;
    }

    public void setIdsubcategoria(int idsubcategoria) {
        this.idsubcategoria = idsubcategoria;
    }

    public int getIdcategoria() {
        return idcategoria;
    }

    public void setIdcategoria(int idcategoria) {
        this.idcategoria = idcategoria;
    }

    public String getSubcategoria() {
        return Subcategoria;
    }

    public void setSubcategoria(String subcategoria) {
        Subcategoria = subcategoria;
    }
}
