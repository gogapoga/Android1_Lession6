package ru.gb.android1_lession6;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;


/**
 * A simple {@link Fragment} subclass.
 */
public class CitiesFragment extends Fragment {

    private LinearLayout cities;
    private CheckBox par;
    private Button button;
    private CityWeather[] arrayCity;
    private OnCityListener cityListener;

    public CitiesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        cityListener = (OnCityListener) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        cityListener = null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_cities, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //установка параметров checkBox-ов
        par = (CheckBox) getActivity().findViewById(R.id.checkWind);
        par.setChecked(((MyApplication) getActivity().getApplication()).isShowWind());
        par.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                ((MyApplication) getActivity().getApplication()).setShowWind(((CheckBox) getActivity().findViewById(R.id.checkWind)).isChecked());
                cityListener.changeCheckBox();
            }
        });
        par = (CheckBox) getActivity().findViewById(R.id.checkHumidity);
        par.setChecked(((MyApplication) getActivity().getApplication()).isShowHumidity());
        par.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                ((MyApplication) getActivity().getApplication()).setShowHumidity(((CheckBox) getActivity().findViewById(R.id.checkHumidity)).isChecked());
                cityListener.changeCheckBox();
            }
        });
        par = (CheckBox) getActivity().findViewById(R.id.checkPressure);
        par.setChecked(((MyApplication) getActivity().getApplication()).isShowPressure());
        par.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                ((MyApplication) getActivity().getApplication()).setShowPressure(((CheckBox) getActivity().findViewById(R.id.checkPressure)).isChecked());
                cityListener.changeCheckBox();
            }
        });
        //
        cities = (LinearLayout) getActivity().findViewById(R.id.cities);
        cities.setBackgroundResource(((MyApplication) getActivity().getApplication()).getStyle().getBackground());
        arrayCity = ((MyApplication) getActivity().getApplication()).getArrayCity();
        for (int i = 0; i < arrayCity.length; i++) {
            button = new Button(getActivity());
            button.setTypeface(null, Typeface.BOLD);
            button.setTextColor(getResources().getColor(R.color.colorButton));
            button.setText(arrayCity[i].getName());
            button.setTag(i);
            cities.addView(button);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cityListener.selectedCity((int) v.getTag());
                }

            });
        }

    }

    @Override
    public void onDestroyView() {
        par = null;
        cities = null;
        button = null;
        super.onDestroyView();
    }

    public interface OnCityListener {
        public void selectedCity(int index);
        public void changeCheckBox();
    }
}
