package de.fuh.michel.fachpraktikum_wi2022.view.configurationelement.builder;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;

import de.fuh.michel.fachpraktikum_wi2022.model.ConfigurationElement;
import de.fuh.michel.fachpraktikum_wi2022.view.ProcessFlowViewModel;
import de.fuh.michel.fachpraktikum_wi2022.view.configurationelement.CreateConfigurationElementViewModel;

public interface CreateEditFragmentBuilder {

    View buildFragment(@NonNull LayoutInflater inflater,
                       @Nullable ViewGroup container,
                       ProcessFlowViewModel processFlowViewModel,
                       CreateConfigurationElementViewModel createViewModel,
                       ConfigurationElement configurationElement);

    class LiveDataTextWatcher implements TextWatcher {
        private final MutableLiveData<String> liveData;

        public LiveDataTextWatcher(MutableLiveData<String> liveData) {
            this.liveData = liveData;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            // Not used
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            // Not used
        }

        @Override
        public void afterTextChanged(Editable s) {
            // Update the LiveData object with the latest user input
            liveData.postValue(s.toString());
        }
    }
}
