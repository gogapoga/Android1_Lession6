package ru.gb.android1_lession6;

import android.app.Application;
import android.support.v4.app.Fragment;

import java.util.Locale;

public class MyApplication extends Application { //класс для сохранения ключевых параметров при переходе из активити в активити
    private boolean showWind = true;
    private boolean showHumidity = true;
    private boolean showPressure = true;
    private int selectedCity = 0;
    private int selectedLanguage = 0;
    private int selectedStyle = 0;
    private Style style = new Day();

    public MyApplication() {
        super();
        String str = Locale.getDefault().getLanguage();
        if (Locale.getDefault().getLanguage().equals("en")) {
            selectedLanguage = 1;
        }
        if ((Locale.getDefault().getLanguage().equals("ru"))) {
            selectedLanguage = 0;
        }
    }

    public int getSelectedCity() {
        return selectedCity;
    }

    public void setSelectedCity(int selectedCity) {
        this.selectedCity = selectedCity;
    }

    public Style getStyle() {
        return style;
    }

    public void setStyle(Style style) {
        this.style = style;
    }

    public int getSelectedStyle() {
        return selectedStyle;
    }

    public void setSelectedStyle(int selectedStyle) {
        this.selectedStyle = selectedStyle;
    }

    public int getSelectedLanguage() {
        return selectedLanguage;
    }

    public void setSelectedLanguage(int selectedLanguage) {
        this.selectedLanguage = selectedLanguage;
    }

    public boolean isShowHumidity() {
        return showHumidity;
    }

    public void setShowHumidity(boolean showHumidity) {
        this.showHumidity = showHumidity;
    }

    public boolean isShowPressure() {
        return showPressure;
    }

    public void setShowPressure(boolean showPressure) {
        this.showPressure = showPressure;
    }

    public Boolean isShowWind() {
        return showWind;
    }

    public void setShowWind(boolean showWind) {
        this.showWind = showWind;
    }

    public CityWeather[] getArrayCity() { //загрузка списка нужных городов
        CityWeather[] arrayCity = {new CityWeather(selectedLanguage == 0 ? "Москва" : "Moscow"), new CityWeather(selectedLanguage == 0 ? "Воронеж" : "Voronezh"),
                new CityWeather(selectedLanguage == 0 ? "Брянск" : "Bryansk"), new CityWeather(selectedLanguage == 0 ? "Липецк" : "Lipetsk"), new CityWeather(selectedLanguage == 0 ? "Рязань" : "Ryazan"),
                new CityWeather(selectedLanguage == 0 ? "Новосибирск" : "Novosibirsk"), new CityWeather(selectedLanguage == 0 ? "Владивосток" : "Vladivostok"), new CityWeather(selectedLanguage == 0 ? "Хабаровск" : "Khabarovsk"),
                new CityWeather(selectedLanguage == 0 ? "Чита" : "Chita"), new CityWeather(selectedLanguage == 0 ? "Уфа" : "Ufa"), new CityWeather(selectedLanguage == 0 ? "Калуга" : "Kaluga")};
        return arrayCity;
    }
}
