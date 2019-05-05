package com.bignerdranch.android.oursigma;

import android.os.Bundle;
 import android.support.annotation.Nullable;
 import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
 import android.view.View;
 import android.view.ViewGroup;
 import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.poi.ss.formula.functions.T;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

public class TwoFragment extends Fragment {
    private int numberFunction;
    private List<Double> data;
    private String numberColumn;
    private TextView textView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_two,container,false);

        textView = (TextView) view.findViewById(R.id.final_data);
        textView.setMovementMethod(new ScrollingMovementMethod());

        return view;
    }

    private void pasteData(List<Double> data, int numberFunction, String numberColumn, TextView textView) {
        switch (numberFunction){
            case 0:
                textView.append(getString(R.string.average_value) + numberColumn + ":\n" +"x1 + ... + xn = " + data.get(0)
                        + "\n" + "xcp = (x1 + ... + xn) / n = " + data.get(1) + "\n\n");
                break;
            case 1:
                break;
            case 2:
                textView.append("Погрешность наилучшего значения столбца" + numberColumn + ":\n" +"∑(xi - xср)² = "
                        + data.get(0) + "\n" + "σ(xcp) = √(∑(xi - xср)²/n(n-1)) = " + data.get(1) + "\n\n");
        }
    }

    @Override
    public void onStart() {
        EventBus.getDefault().register(this);
        super.onStart();
    }

    @Override
    public void onStop() {
        PostFinalData event = EventBus.getDefault().getStickyEvent(PostFinalData.class);

        if(event != null )
            EventBus.getDefault().removeAllStickyEvents();

        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void getFinalData(PostFinalData event) {
        this.numberFunction = event.getNumberFunction();
        this.data = event.getData();
        this.numberColumn = event.getNumberColumn();
        pasteData(data,numberFunction,numberColumn,textView);
    }
}