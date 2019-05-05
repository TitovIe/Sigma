package com.bignerdranch.android.oursigma;

import java.util.List;

public class PostFinalData {
    private List<Double> data;
    private int numberFunction;
    private String numberColumn;

    public List<Double> getData() {
        return data;
    }

    public int getNumberFunction() {
        return numberFunction;
    }

    public String getNumberColumn() {
        return numberColumn;
    }

    public PostFinalData(List<Double> data, int numberFunction, String numberColumn) {
        this.data = data;
        this.numberFunction = numberFunction;
        this.numberColumn = numberColumn;
    }

}
