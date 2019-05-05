package com.bignerdranch.android.oursigma;

import android.widget.EditText;

import java.util.List;

public class PostCountAndList {
    private int countOfRow;
    private int countOfColumn;
    private List<List<EditText>> listOfRow;

    public PostCountAndList(List<List<EditText>> listOfRow, int countOfRow, int countOfColumn) {
        this.listOfRow = listOfRow;
        this.countOfRow = countOfRow;
        this.countOfColumn = countOfColumn;
    }

    public int getCountOfRow() {
        return countOfRow;
    }

    public int getCountOfColumn() {
        return countOfColumn;
    }

    public List<List<EditText>> getListOfRow() {
        return listOfRow;
    }
}
