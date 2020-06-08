package com.alas.mutec.Api;

public class ParametroPubs {
    public int subcategoria,categoria,carrera,take,skiped;

    public ParametroPubs(int subcategoria, int categoria, int carrera, int take, int skiped) {
        this.subcategoria = subcategoria;
        this.categoria = categoria;
        this.carrera = carrera;
        this.take = take;
        this.skiped = skiped;
    }

    public int getSubcategoria() {
        return subcategoria;
    }

    public void setSubcategoria(int subcategoria) {
        this.subcategoria = subcategoria;
    }

    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }

    public int getCarrera() {
        return carrera;
    }

    public void setCarrera(int carrera) {
        this.carrera = carrera;
    }

    public int getTake() {
        return take;
    }

    public void setTake(int take) {
        this.take = take;
    }

    public int getSkiped() {
        return skiped;
    }

    public void setSkiped(int skiped) {
        this.skiped = skiped;
    }
}
