package com.bignerdranch.android.oursigma;

import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class CountSigma {
    List<List<EditText>> listOfRow;
    int rowIndex;
    int columnIndex;
    final int countOfRow;
    final int countOfColumn;

    public CountSigma(List<List<EditText>> listOfRow, int countOfRow, int countOfColumn) {
        this.listOfRow = listOfRow;
        this.countOfRow = countOfRow;
        this.countOfColumn = countOfColumn;
    }
 
    public List<Double> averageValue(int columnIndex) {
        List<Double> values = new ArrayList<>();
        double sumValues = 0;
        double averageValue = 0;
        int rowIndex = 1;

        for(; rowIndex <= countOfRow; rowIndex++) {
            sumValues += readOfValues(listOfRow, rowIndex, columnIndex);
        }

        averageValue = sumValues / countOfRow;
        values.add(sumValues);
        values.add(averageValue);
        return values;
    }

    public List<Double> sigmaOfAverageValue(int columnIndex) {
        List<Double> values = new ArrayList<>();
        int rowIndex = 1;
        double sumOfDifferenceSquare = 0;
        double difference;
        double sigmaOfValue;

        for(; rowIndex <= countOfRow; rowIndex++) {
            difference = readOfValues(listOfRow, rowIndex, columnIndex) - averageValue(columnIndex).get(1);
            sumOfDifferenceSquare += Math.pow(difference, 2);
        }

        sigmaOfValue = Math.pow(sumOfDifferenceSquare/(countOfRow*(countOfRow - 1)), 0.5);
        values.add(sumOfDifferenceSquare);
        values.add(sigmaOfValue);
        return values;
    }

    public double readOfValues(List<List<EditText>> listOfRow, int row, int column) {
        return Double.parseDouble(listOfRow.get(row).get(column).getText().toString());
    }
}
