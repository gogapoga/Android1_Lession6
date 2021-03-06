package ru.gb.android1_lession6;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements CitiesFragment.OnCityListener {

    private Fragment fragmentCities;
    private Fragment fragmentWeather;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        MyApplication myApplication = (MyApplication) getApplication();
        if (item.getItemId() == R.id.action_day) {
            if (myApplication.getSelectedStyle() != 0) {
                myApplication.setSelectedStyle(0);
                myApplication.setStyle(new Day());
                recreate();
            }
            return true;
        } else if (item.getItemId() == R.id.action_night) {
            if (myApplication.getSelectedStyle() != 1) {
                myApplication.setSelectedStyle(1);
                myApplication.setStyle(new Night());
                recreate();
            }
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        savedInstanceState = null; //неправильно но по другому не получается((((, какие-то ошибки, непроресовки
        MyApplication myApplication = (MyApplication) getApplication();
        setTheme(myApplication.getStyle().getColors());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
     //   if (savedInstanceState == null) {
            fragmentCities = new CitiesFragment();
            ft.add(R.id.fragment_cities, fragmentCities);
            fragmentWeather = CityWeatherFragment.newInstance(myApplication.getSelectedCity());
            ft.add(R.id.fragment_city_weather, fragmentWeather);
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
                ft.hide(fragmentWeather);
                setTitle(makeTitle(getString(R.string.wordWeather)));
            }
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
                ft.show(fragmentCities);
                ft.show(fragmentWeather);
                setTitle(makeTitle(myApplication.getArrayCity()[myApplication.getSelectedCity()].getName()));
            }
            ft.commitAllowingStateLoss();
      //  }
    }

    @Override
    public void onBackPressed() { //обработка нажатия кнопки назад
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            setTitle(makeTitle(getString(R.string.wordWeather)));
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.show(fragmentCities);
            ft.hide(fragmentWeather);
            ft.commitAllowingStateLoss();
        }
    }


    public void selectedCity(int index) {//обработка изменения города
        MyApplication myApplication = (MyApplication) getApplication();
        myApplication.setSelectedCity(index);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft = fm.beginTransaction();
        fragmentWeather = CityWeatherFragment.newInstance(index);
        ft.replace(R.id.fragment_city_weather, fragmentWeather);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
        ft.hide(fragmentCities);
        ft.commitAllowingStateLoss();
        setTitle(makeTitle(myApplication.getArrayCity()[myApplication.getSelectedCity()].getName()));
    }

    public void changeCheckBox(){ //обработка изменения чекбоксов
        MyApplication myApplication = (MyApplication) getApplication();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft = fm.beginTransaction();
        fragmentWeather = CityWeatherFragment.newInstance(myApplication.getSelectedCity());
        ft.replace(R.id.fragment_city_weather, fragmentWeather);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
            ft.hide(fragmentWeather);
        ft.commitAllowingStateLoss();
    }
    private String makeTitle(String cityName) { //создание заголовка
        StringBuilder str = new StringBuilder("");
        str.append(cityName);
        str.append("     ");
        SimpleDateFormat sdf = new SimpleDateFormat("E  d MMM");
        str.append(sdf.format(Calendar.getInstance().getTime()));
        return str.toString();
    }



}
