package com.bignerdranch.android.oursigma;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.BundleCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import org.mariuszgromada.math.mxparser.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DerivativeActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private RelativeLayout percentRelativeLayout;
    private RelativeLayout contentRelativeLayout;
    private RelativeLayout frameContent;
    private Button tf;
    private Button tfh;
    private Button af;
    private Button sf;
    private Button x;
    private Button ok_botton;
    private Button sum;
    private Button min;
    private Button mult;
    private Button div;
    private Button skobka;
    private Button ce;
    private Button one;
    private Button two;
    private Button three;
    private Button four;
    private Button five;
    private Button six;
    private Button seven;
    private Button eight;
    private Button nine;
    private Button zero;
    private Button point;
    private Button comma;
    private EditText edittext1;
    private EditText columnOne;
    private EditText columnTwo;
    private EditText columnThree;
    private EditText columnFour;
    private EditText columnFive;
    private EditText functionOne;
    private EditText functionTwo;
    private EditText functionThree;
    private EditText functionFour;
    private EditText functionFive;
    private int editId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_derivative);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitle(R.string.derivative_activity);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();// возврат на предыдущий activity
            }
        });

        columnOne = (EditText) findViewById(R.id.column_one);
        columnTwo = (EditText) findViewById(R.id.column_two);
        columnThree = (EditText) findViewById(R.id.column_three);
        columnFour = (EditText) findViewById(R.id.column_four);
        columnFive = (EditText) findViewById(R.id.column_five);
        functionOne = (EditText) findViewById(R.id.function_one);
        functionTwo = (EditText) findViewById(R.id.function_two);
        functionThree = (EditText) findViewById(R.id.function_three);
        functionFour = (EditText) findViewById(R.id.function_four);
        functionFive = (EditText) findViewById(R.id.function_five);
        contentRelativeLayout = (RelativeLayout) findViewById(R.id.content_relativlayout);
        percentRelativeLayout = (RelativeLayout) findViewById(R.id.percentRelativeLayoutKeyBoard);
        one = (Button) findViewById(R.id.one);
        two = (Button) findViewById(R.id.two);
        three = (Button) findViewById(R.id.three);
        four = (Button) findViewById(R.id.four);
        five = (Button) findViewById(R.id.five);
        six = (Button) findViewById(R.id.six);
        seven = (Button) findViewById(R.id.seven);
        eight = (Button) findViewById(R.id.eight);
        nine = (Button) findViewById(R.id.nine);
        zero = (Button) findViewById(R.id.zero);
        point = (Button) findViewById(R.id.point);
        comma = (Button) findViewById(R.id.comma);
        sum = (Button) findViewById(R.id.plus);
        min = (Button) findViewById(R.id.minus);
        div = (Button) findViewById(R.id.division);
        mult = (Button) findViewById(R.id.multiplication);
        skobka = (Button) findViewById(R.id.skobka);
        ce = (Button) findViewById(R.id.ce);
        tf = (Button) findViewById(R.id.trigonometric_function);
        tfh = (Button) findViewById(R.id.trigonometricHe_function);
        af = (Button) findViewById(R.id.another_function);
        sf = (Button) findViewById(R.id.spec_function);
        x = (Button) findViewById(R.id.argument);
        ok_botton = (Button) findViewById(R.id.botton_ok);
        edittext1 = (EditText) findViewById(R.id.edit_compoz);

        edittext1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                editId = edittext1.getId();
            }
        });

        ok_botton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calculate object = new Calculate();
                edittext1.setText(object.calculateFunction(edittext1));
                edittext1.setSelection(edittext1.getText().length());
                /*Expression e = new Expression(String.valueOf(edittext1.getText()));
                edittext1.setText(String.valueOf(e.calculate()));*/
            }
        });

        columnOne.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                editId = columnOne.getId();

            }
        });

        columnTwo.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                editId = columnTwo.getId();

            }
        });

        columnThree.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                editId = columnThree.getId();
            }
        });

        columnFour.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                editId = columnFour.getId();
            }
        });

        columnFive.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                editId = columnFive.getId();
            }
        });

        functionOne.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                editId = functionOne.getId();
            }
        });

        functionTwo.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                editId = functionTwo.getId();
            }
        });

        functionThree.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                editId = functionThree.getId();
            }
        });

        functionFour.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                editId = functionFour.getId();
            }
        });

        functionFive.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                editId = functionFive.getId();

            }
        });

        zero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pasteSimbol(switchEdit(), "0");
            }
        });

        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pasteSimbol(switchEdit(), "1");
            }
        });

        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pasteSimbol(switchEdit(), "2");
            }
        });

        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pasteSimbol(switchEdit(), "3");
            }
        });

        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pasteSimbol(switchEdit(), "4");
            }
        });

        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pasteSimbol(switchEdit(), "5");
            }
        });

        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pasteSimbol(switchEdit(), "6");
            }
        });

        seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pasteSimbol(switchEdit(), "7");
            }
        });

        eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pasteSimbol(switchEdit(), "8");
            }
        });

        nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pasteSimbol(switchEdit(), "9");
            }
        });

        point.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pasteSimbol(switchEdit(), ".");
            }
        });

        comma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pasteSimbol(switchEdit(), ",");
            }
        });

        sum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pasteSimbol(switchEdit(), "+");
            }
        });

        min.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pasteSimbol(switchEdit(), "-");
            }
        });

        div.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pasteSimbol(switchEdit(), "/");
            }
        });

        mult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pasteSimbol(switchEdit(), "*");
            }
        });

        skobka.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pasteFunction(switchEdit(), "()");
            }
        });

        ce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 removeChar(switchEdit());
            }
        });

        ce.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                cleanString(switchEdit());
                return false;
            }
        });

        x.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu x_popupmenu = new PopupMenu(DerivativeActivity.this, v);
                x_popupmenu.inflate(R.menu.arguments_menu);
                x_popupmenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.arguments_x:
                                pasteSimbol(switchEdit(),"x");
                                return true;
                            case R.id.arguments_y:
                                pasteSimbol(switchEdit(),"y");
                                return true;
                            case R.id.arguments_z:
                                pasteSimbol(switchEdit(),"z");
                                return true;
                            case R.id.arguments_u:
                                pasteSimbol(switchEdit(),"u");
                                return true;
                            case R.id.arguments_v:
                                pasteSimbol(switchEdit(),"v");
                                return true;

                            default:
                                return false;
                        }
                    }
                });
                x_popupmenu.show();
            }
        });

        sf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu sf_popupmenu = new PopupMenu(DerivativeActivity.this, v);
                sf_popupmenu.inflate(R.menu.sf_menu);
                sf_popupmenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.sf_pow:
                                pasteSimbol(switchEdit(),"^");
                                return true;
                            case R.id.sf_abs:
                                pasteFunction(switchEdit(),"abs()");
                                return true;

                            default:
                                return false;
                        }
                    }
                });
                sf_popupmenu.show();
            }
        });

        af.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu af_popup_menu = new PopupMenu(DerivativeActivity.this, v);
                af_popup_menu.inflate(R.menu.af_menu);
                af_popup_menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.af_ln:
                                pasteFunction(switchEdit(),"ln()");
                                return true;
                            case R.id.af_lg:
                                pasteFunction(switchEdit(),"log10()");
                                return true;
                            case R.id.af_log:
                                pasteFunction(switchEdit(),"log()");
                                return true;
                            case R.id.af_exp:
                                pasteFunction(switchEdit(),"exp()");
                            default:
                                return false;
                        }
                    }
                });
                af_popup_menu.show();
            }
        });

        tfh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu tfh_popup_menu = new PopupMenu(DerivativeActivity.this, v);
                tfh_popup_menu.inflate(R.menu.tfh_menu);
                tfh_popup_menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.tfh_ch:
                                pasteFunction(switchEdit(),"cosh()");
                                return true;
                            case R.id.tfh_sh:
                                pasteFunction(switchEdit(),"sinh()");
                                return true;
                            case R.id.tfh_th:
                                pasteFunction(switchEdit(),"tanh()");
                                return true;
                            case R.id.tfh_cth:
                                pasteFunction(switchEdit(),"coth()");
                                return true;

                            default:
                                return false;
                        }
                    }
                });
                tfh_popup_menu.show();
            }
        });

        tf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup_menu = new PopupMenu(DerivativeActivity.this, v);
                popup_menu.inflate(R.menu.tf_menu);
                popup_menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.tf_cos:
                                pasteFunction(switchEdit(),"cos()");
                                return true;
                            case R.id.tf_sin:
                                pasteFunction(switchEdit(),"sin()");
                                return true;
                            case R.id.tf_tg:
                                pasteFunction(switchEdit(),"tan()");
                                return true;
                            case R.id.tf_ctg:
                                pasteFunction(switchEdit(),"ctan()");
                                return true;
                            case R.id.tf_arccos:
                                pasteFunction(switchEdit(),"acos()");
                                return true;
                            case R.id.tf_arcsin:
                                pasteFunction(switchEdit(),"asin()");
                                return true;
                            case R.id.tf_arctg:
                                pasteFunction(switchEdit(),"atan()");
                                return true;
                            case R.id.tf_arcctg:
                                pasteFunction(switchEdit(),"actan()");
                                return true;

                            default:
                                return false;
                        }
                    }
                });
                popup_menu.show();
            }
        });
    }

    private void pasteSimbol(EditText editText,CharSequence charSequence) {
        editText.getText().insert(editText.getSelectionStart(), charSequence);
    }

    private void pasteFunction(EditText editText,CharSequence charSequence) {
        editText.getText().insert(editText.getSelectionStart(), charSequence);
        switchEdit().setSelection(editText.getSelectionStart() - 1);
    }

    private void removeChar(EditText editText) {
        int positionCursor = editText.getSelectionStart();
        String newEditString;
        String editString = String.valueOf(editText.getText());
        if(!editString.isEmpty() && positionCursor != 0) {
            newEditString = editString.substring(0, positionCursor - 1) + editString.substring(positionCursor);
            editText.setText(newEditString);
            editText.setSelection(positionCursor - 1);
        }
    }

    private void cleanString(EditText editText) {
        editText.setText("");
    }

    private EditText switchEdit() {
        switch (Integer.parseInt(String.valueOf(editId))){
            case R.id.column_one:
                return columnOne;
            case R.id.column_two:
                return columnTwo;
            case R.id.column_three:
                return columnThree;
            case R.id.column_four:
                return columnFour;
            case R.id.column_five:
                return columnFive;
            case R.id.function_one:
                return functionOne;
            case R.id.function_two:
                return functionTwo;
            case R.id.function_three:
                return functionThree;
            case R.id.function_four:
                return functionFour;
            case R.id.function_five:
                return functionFive;
            default:
                return edittext1;
        }
    }
}

