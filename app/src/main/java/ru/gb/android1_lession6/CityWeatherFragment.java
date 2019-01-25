package ru.gb.android1_lession6;


import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import java.text.SimpleDateFormat;
import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class CityWeatherFragment extends Fragment {

    private LinearLayout timeweather;
    private Button timeWeather;
    private Drawable img;

    public CityWeatherFragment() {
        // Required empty public constructor
    }

    public static CityWeatherFragment newInstance(int citySelected) {
        CityWeatherFragment cityWeatherFragment = new CityWeatherFragment();
        Bundle args = new Bundle();
        args.putInt(ParamShow.CityWeather.toString(), citySelected);
        cityWeatherFragment.setArguments(args);
        return cityWeatherFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_city_weather, container, false);
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        CityWeather city = ((MyApplication)getActivity().getApplication()).getArrayCity()[getArguments().getInt(ParamShow.CityWeather.toString())];
        city.loadWeather();
        timeweather = (LinearLayout) getActivity().findViewById(R.id.timeweather);
        timeweather.setBackgroundResource(((MyApplication) getActivity().getApplication()).getStyle().getBackground());
        for (int i = 0; i < 24; i++) {
            timeWeather = new Button(getActivity());
            timeWeather.setTypeface(null, Typeface.ITALIC);
            timeWeather.setTextColor(getResources().getColor(R.color.colorButton));
            timeWeather.setClickable(false);
            timeWeather.setGravity(Gravity.LEFT | Gravity.TOP);
            StringBuilder str = new StringBuilder(getString(R.string.wordTime));
            str.append(i);
            str.append(":00\n");
            str.append(getString(R.string.wordTemperature));
            int t = city.getTemp(i);
            if (t > 0) str.append("+");
            str.append(t);
            str.append(getString(R.string.wordC));
            t = city.getHumidity(i);
            if (((MyApplication) getActivity().getApplication()).isShowHumidity()) {
                str.append("\n");
                str.append(getString(R.string.wordHumidity));
                str.append(": ");
                str.append(t);
                str.append("%");
            }
            if (((MyApplication) getActivity().getApplication()).isShowWind()) {
                str.append("\n");
                str.append(getString(R.string.wordWind));
                str.append(": ");
                str.append(city.getWind(i));
                str.append(getString(R.string.wordMC));
            }
            if (((MyApplication) getActivity().getApplication()).isShowPressure()) {
                str.append("\n");
                str.append(getString(R.string.wordPressure));
                str.append(": ");
                str.append(city.getPressure(i));
                str.append(getString(R.string.wordMMPTST));
            }
            timeWeather.setText(str);
            if (t < 40) img = getResources().getDrawable(R.drawable.sun);
            else if (t > 80) img = getResources().getDrawable(R.drawable.cloud_r);
            else img = getResources().getDrawable(R.drawable.cloud);
            img.setBounds(0, 0, 60, 60);
            timeWeather.setCompoundDrawables(null, null, img, null);
            timeweather.addView(timeWeather);
        }
    }
    @Override
    public void onDestroyView() {
        timeweather = null;
        timeWeather = null;
        img = null;
        super.onDestroyView();
    }

}
