package com.bignerdranch.android.oursigma;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;


public class CreateTableFragment extends DialogFragment {
    public static final String EXTRA_FORM = "form";

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceSaved) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_create_table, null);
        final EditText oneForm = (EditText) view.findViewById(R.id.oneForm);
        final EditText twoForm = (EditText) view.findViewById(R.id.twoForm);

        return new AlertDialog.Builder(getActivity())
                .setView(view)
                .setPositiveButton("Создать", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sendResult(Activity.RESULT_OK, oneForm, twoForm);
                    }
                })
                .setNegativeButton("Отмена",null)
                .create();
    }

    private void sendResult(int resultCode,EditText oneForm,EditText twoForm) {
        if (getTargetFragment() == null) {
            return;
        }

        ArrayList<Integer> valuesTable = new ArrayList<>();
        valuesTable.add(Integer.parseInt(oneForm.getText().toString()));
        valuesTable.add(Integer.parseInt(twoForm.getText().toString()));

        Intent intent = new Intent();
        intent.putExtra(EXTRA_FORM, valuesTable);
        getTargetFragment()
                .onActivityResult(getTargetRequestCode(), resultCode, intent);
    }
}
