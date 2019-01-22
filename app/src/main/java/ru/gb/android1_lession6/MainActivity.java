package ru.gb.android1_lession4;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
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

public class MainActivity extends AppCompatActivity {

    private LinearLayout cities;
    private CityWeather[] arrayCity;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_settings) {  //cохранение  параметров checkBox-ов перед закрытием активити
            ((MyApplication)getApplication()).setShowHumidity(((CheckBox) findViewById(R.id.checkHumidity)).isChecked());
            ((MyApplication)getApplication()).setShowPressure(((CheckBox) findViewById(R.id.checkPressure)).isChecked());
            ((MyApplication)getApplication()).setShowWind(((CheckBox) findViewById(R.id.checkWind)).isChecked());
            Intent SetAct = new Intent(getApplicationContext(), SettingsActivity.class);
            startActivity(SetAct);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(((MyApplication)getApplication()).getStyle().getColors());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(makeTitle());
        //установка параметров checkBox-ов
        CheckBox par = (CheckBox) findViewById(R.id.checkWind);
        par.setText(((MyApplication)getApplication()).getLanguage().getWordWind());
        par.setChecked(((MyApplication)getApplication()).isShowWind());
        par = (CheckBox) findViewById(R.id.checkHumidity);
        par.setText(((MyApplication)getApplication()).getLanguage().getWordHumidity());
        par.setChecked(((MyApplication)getApplication()).isShowHumidity());
        par = (CheckBox) findViewById(R.id.checkPressure);
        par.setText(((MyApplication)getApplication()).getLanguage().getWordPressure());
        par.setChecked(((MyApplication)getApplication()).isShowPressure());
        //

        cities = (LinearLayout) findViewById(R.id.cities);
        cities.setBackgroundResource(((MyApplication)getApplication()).getStyle().getBackground());
        arrayCity = getArrayCity();
        for (int i = 0; i < arrayCity.length; i++) {
            Button button = new Button(this);
            button.setTypeface(null, Typeface.BOLD);
            button.setTextColor(getResources().getColor(R.color.colorButton));
            button.setText(arrayCity[i].getName());
            button.setTag(i);
            cities.addView(button);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) { //cохранение  параметров checkBox-ов перед закрытием активити
                    ((MyApplication)getApplication()).setShowHumidity(((CheckBox) findViewById(R.id.checkHumidity)).isChecked());
                    ((MyApplication)getApplication()).setShowPressure(((CheckBox) findViewById(R.id.checkPressure)).isChecked());
                    ((MyApplication)getApplication()).setShowWind(((CheckBox) findViewById(R.id.checkWind)).isChecked());
                    arrayCity[(int) v.getTag()].loadWeather();
                    Intent SecAct = new Intent(getApplicationContext(), CityWeatherActivity.class);
                    SecAct.putExtra(ParamShow.CityWeather.toString(), arrayCity[(int) v.getTag()]);
                    startActivity(SecAct);
                }
            });
        }

    }

    private CityWeather[] getArrayCity() { //загрузка списка нужных городов
        CityWeather[] arrayCity = {new CityWeather(((MyApplication) this.getApplication()).getLanguage().getClass().getSimpleName().equals("Russian") ? "Москва" : "Moscow"), new CityWeather(((MyApplication) this.getApplication()).getLanguage().getClass().getSimpleName().equals("Russian") ? "Воронеж" : "Voronezh"),
                new CityWeather(((MyApplication) this.getApplication()).getLanguage().getClass().getSimpleName().equals("Russian") ? "Брянск" : "Bryansk"), new CityWeather(((MyApplication) this.getApplication()).getLanguage().getClass().getSimpleName().equals("Russian") ? "Липецк" : "Lipetsk"), new CityWeather(((MyApplication) this.getApplication()).getLanguage().getClass().getSimpleName().equals("Russian") ? "Рязань" : "Ryazan"),
                new CityWeather(((MyApplication) this.getApplication()).getLanguage().getClass().getSimpleName().equals("Russian") ? "Новосибирск" : "Novosibirsk"), new CityWeather(((MyApplication) this.getApplication()).getLanguage().getClass().getSimpleName().equals("Russian") ? "Владивосток" : "Vladivostok"), new CityWeather(((MyApplication) this.getApplication()).getLanguage().getClass().getSimpleName().equals("Russian") ? "Хабаровск" : "Khabarovsk"),
                new CityWeather(((MyApplication) this.getApplication()).getLanguage().getClass().getSimpleName().equals("Russian") ? "Чита" : "Chita"), new CityWeather(((MyApplication) this.getApplication()).getLanguage().getClass().getSimpleName().equals("Russian") ? "Уфа" : "Ufa"), new CityWeather(((MyApplication) this.getApplication()).getLanguage().getClass().getSimpleName().equals("Russian") ? "Калуга" : "Kaluga")};
        return arrayCity;
    }

    private String makeTitle() {
        StringBuilder str = new StringBuilder(((MyApplication) this.getApplication()).getLanguage().getWordWeather());
        SimpleDateFormat sdf = new SimpleDateFormat("E  d MMM", Locale.forLanguageTag(((MyApplication) this.getApplication()).getLanguage().getWordDate()));
        str.append(sdf.format(Calendar.getInstance().getTime()));
        return str.toString();
    }
 }
