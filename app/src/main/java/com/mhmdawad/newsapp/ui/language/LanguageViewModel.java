package com.mhmdawad.newsapp.ui.language;

import android.content.SharedPreferences;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mhmdawad.newsapp.models.Country;
import com.mhmdawad.newsapp.ui.main.MainRepository;
import com.mhmdawad.newsapp.utils.Constants;

import javax.inject.Inject;

public class LanguageViewModel extends ViewModel {

    private SharedPreferences.Editor editor;
    private MutableLiveData<Boolean> isCountryChanged;
    private MainRepository mainRepository;

    @Inject
    LanguageViewModel(SharedPreferences.Editor editor, MainRepository mainRepository) {
        this.editor = editor;
        this.mainRepository = mainRepository;
        isCountryChanged = new MutableLiveData<>();
    }

    public void changeCountry(Country country) {
        editor.putString(Constants.COUNTRY_PREFS_NAME, country.getSmallName());
        editor.putString(Constants.COUNTRY_PREFS_IMAGE, country.getImage());
        editor.apply();
        mainRepository.removeDB();
        isCountryChanged.setValue(true);
    }

    MutableLiveData<Boolean> getIsCountryChanged() {
        return isCountryChanged;
    }
}
