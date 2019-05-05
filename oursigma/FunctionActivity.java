package com.bignerdranch.android.oursigma;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.LinkedList;
import java.util.List;

public class FunctionActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private List<List<EditText>> listOfRow;
    private int countOfRow;
    private int countOfColumn;
    private String textOfEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_function);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitle(R.string.title_function_activity);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();// возврат на предыдущий activity
            }
        });

        ListView listView = (ListView)findViewById(R.id.listView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        if (!textOfEdit.isEmpty()) {
                            CountSigma object = new CountSigma(listOfRow, countOfRow, countOfColumn);
                            try {
                            EventBus.getDefault().postSticky(new PostFinalData(object.averageValue(Integer.parseInt(textOfEdit)), 0, textOfEdit));
                            Snackbar.make(view, "Результат во вкладке Вычисления", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                            }
                            catch (Exception e){
                                Snackbar.make(view, "Неверный формат ввода", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                            }
                        } else
                            Snackbar.make(view, "Заполните поле ввода.", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                        break;
                    case 1:
                        break;
                    case 2:
                        if (!textOfEdit.isEmpty()) {
                            CountSigma object = new CountSigma(listOfRow, countOfRow, countOfColumn);
                            try {
                                EventBus.getDefault().postSticky(new PostFinalData(object.sigmaOfAverageValue(Integer.parseInt(textOfEdit)), 2, textOfEdit));
                                Snackbar.make(view, "Результат во вкладке Вычисления", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                            }
                            catch (Exception e){
                                Snackbar.make(view, "Неверный формат ввода", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                            }
                        } else
                            Snackbar.make(view, "Заполните поле ввода.", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                        break;
                }
                if(position == 5) {
                    Intent i = new Intent(FunctionActivity.this, DerivativeActivity.class);
                    startActivity(i);
                }
            }
        });

        final List<String[]> listFunction = new LinkedList<String[]>();
        listFunction.add(new String[] { getResources().getString(R.string.best_value), getResources().getString(R.string.format_best_value) });
        listFunction.add(new String[] { getResources().getString(R.string.sigma_value), getResources().getString(R.string.format_sigma_value) });
        listFunction.add(new String[] { getResources().getString(R.string.sigma_best_value), getResources().getString(R.string.format_sigma_best_value) });
        listFunction.add(new String[] { getResources().getString(R.string.sigma_value_sum), getResources().getString(R.string.format_sigma_value_sum) });
        listFunction.add(new String[] { getResources().getString(R.string.sigma_value_mul), getResources().getString(R.string.format_sigma_value_mul) });
        listFunction.add(new String[] { getResources().getString(R.string.sigma_value_noempty), getResources().getString(R.string.format_sigma_value_noempty) });
        listFunction.add(new String[] { getResources().getString(R.string.sigma_value_square), getResources().getString(R.string.format_sigma_value_square) });

        listView.setAdapter(new ArrayAdapter<String[]>(
                this,
                android.R.layout.simple_list_item_2,
                android.R.id.text1,
                listFunction) {

            @NonNull
            @Override
            public View getView(int position, View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                String[] entry = listFunction.get(position);
                TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                TextView text2 = (TextView) view.findViewById(android.R.id.text2);

                if (Build.VERSION.SDK_INT >= 23) {
                    text1.setTextColor(getColor(R.color.primary));
                    text2.setPadding(0,0,0,30);
                    text2.setTextColor(getColor(R.color.primary_light));
                }
                else {
                    text2.setPadding(0,0,0,30);
                    text1.setTextColor(getResources().getColor(R.color.primary));
                    text2.setTextColor(getResources().getColor(R.color.secondary_text));
                }

                text1.setText(entry[0]);
                text2.setText(entry[1]);

                return view;
            }
        });
    }

    @Override
    public void onStart() {
        EventBus.getDefault().register(this);
        super.onStart();
    }

    @Override
    public void onStop() {
        PostTextOfEdit event = EventBus.getDefault().getStickyEvent(PostTextOfEdit.class);

        if(event != null )
            EventBus.getDefault().removeAllStickyEvents();

        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void getTextOfEdit(PostTextOfEdit event) {
        this.textOfEdit = event.getProcessedData();
    }

    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void getCountAndList(PostCountAndList event) {
        this.countOfColumn = event.getCountOfColumn();
        this.countOfRow = event.getCountOfRow();
        this.listOfRow = event.getListOfRow();
    }
}
