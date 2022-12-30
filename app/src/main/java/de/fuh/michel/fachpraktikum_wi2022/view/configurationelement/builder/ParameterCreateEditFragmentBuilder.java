package de.fuh.michel.fachpraktikum_wi2022.view.configurationelement.builder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import de.fuh.michel.fachpraktikum_wi2022.databinding.FragmentCreateParameterBinding;
import de.fuh.michel.fachpraktikum_wi2022.model.ConfigurationElement;
import de.fuh.michel.fachpraktikum_wi2022.model.configurationelement.Parameter;
import de.fuh.michel.fachpraktikum_wi2022.view.ProcessFlowViewModel;
import de.fuh.michel.fachpraktikum_wi2022.view.configurationelement.CreateConfigurationElementViewModel;

public class ParameterCreateEditFragmentBuilder implements CreateEditFragmentBuilder {

    public View buildFragment(@NonNull LayoutInflater inflater,
                              @Nullable ViewGroup container,
                              ProcessFlowViewModel processFlowViewModel,
                              CreateConfigurationElementViewModel createViewModel,
                              ConfigurationElement configurationElement) {
        FragmentCreateParameterBinding binding = FragmentCreateParameterBinding.inflate(inflater, container, false);

        binding.nameInput.getEditText().addTextChangedListener(new LiveDataTextWatcher(createViewModel.getNameLiveData()));
        binding.valueInput.getEditText().addTextChangedListener(new LiveDataTextWatcher(createViewModel.getValueLiveData()));


        if (configurationElement != null) {
            Parameter parameter = (Parameter) configurationElement;
            binding.nameInput.getEditText().setText(parameter.getName());
            binding.valueInput.getEditText().setText(parameter.getValue());
        }

        return binding.getRoot();
    }
}
