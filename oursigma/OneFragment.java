package com.bignerdranch.android.oursigma;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

public class OneFragment extends Fragment {
    private static final int REQUEST_CODTABLE = 0;
    private GridLayout gridLayout;
    private List<EditText> listOfColumn;
    private List<List<EditText>> listOfRow;
    private CountSigma object;
    private ImageButton addTable;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_one, container, false);

        gridLayout = (GridLayout) view.findViewById(R.id.grid_layout);

        return view;
    }

    public void creatTable(final GridLayout gridLayout, final ArrayList<Integer> arrayList) {
        int CONST_ROW = arrayList.get(0) + 1;
        int CONST_COLUMN = arrayList.get(1);

        gridLayout.setRowCount(CONST_ROW);
        gridLayout.setColumnCount(CONST_COLUMN);
        listOfRow = new ArrayList<>();

        for (int row = 0; row < CONST_ROW; row++) {
            listOfColumn = new ArrayList<>();
            for (int column = 0; column < CONST_COLUMN; column++)
                if(row != 0)
                    addCellInGrid(row,column);
            else
                addCellInGridName(row,column);

            listOfRow.add(listOfColumn);
        }

        EventBus.getDefault().postSticky(new PostCountAndList(listOfRow, arrayList.get(0), arrayList.get(1)));
    }

    private void addCellInGrid(int row, int column) {
        LayoutInflater ltInflater = getLayoutInflater(Bundle.EMPTY);
        EditText cell = (EditText) ltInflater.inflate(R.layout.edit_text, null, false);

        listOfColumn.add(cell);

        GridLayout.LayoutParams params = new GridLayout.LayoutParams(GridLayout.spec(row), GridLayout.spec(column));
        gridLayout.addView(cell, params);
    }


    private void addCellInGridName(int row, int column) {
        LayoutInflater ltInflater = getLayoutInflater(Bundle.EMPTY);
        EditText cell = (EditText) ltInflater.inflate(R.layout.edit_table_name, null, false);
        cell.setText(String.valueOf(column + 1));

        listOfColumn.add(cell);

        GridLayout.LayoutParams params = new GridLayout.LayoutParams(GridLayout.spec(row), GridLayout.spec(column));
        gridLayout.addView(cell, params);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent arrList) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }

        if (requestCode == REQUEST_CODTABLE) {
            ArrayList<Integer> arrayList = (ArrayList<Integer>) arrList.getSerializableExtra(CreateTableFragment.EXTRA_FORM);
            creatTable(gridLayout, arrayList);
        }
    }

}