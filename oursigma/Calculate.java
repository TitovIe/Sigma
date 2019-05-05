package com.bignerdranch.android.oursigma;

import android.widget.EditText;

import org.mariuszgromada.math.mxparser.Expression;

/**
 * Created by USERR on 11.08.2017.
 */

public class Calculate {

    public String calculateFunction(EditText editText) {
        Expression e = new Expression(String.valueOf(editText.getText()));
        return String.valueOf(e.calculate());

    }
}
