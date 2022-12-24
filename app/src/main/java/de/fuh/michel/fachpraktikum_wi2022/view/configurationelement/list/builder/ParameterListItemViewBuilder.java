package de.fuh.michel.fachpraktikum_wi2022.view.configurationelement.list.builder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import de.fuh.michel.fachpraktikum_wi2022.databinding.FragmentParameterListItemBinding;
import de.fuh.michel.fachpraktikum_wi2022.model.ConfigurationElement;
import de.fuh.michel.fachpraktikum_wi2022.model.configurationelement.Parameter;

public class ParameterListItemViewBuilder implements ConfigurationListItemViewBuilder {
    @Override
    public View buildListItemView(ViewGroup parent,
                                  LayoutInflater inflater,
                                  ConfigurationElement configurationElement,
                                  int position) {
        Parameter parameter = (Parameter) configurationElement;
        FragmentParameterListItemBinding binding = FragmentParameterListItemBinding.inflate(inflater, parent, false);

        binding.parameterName.setText(parameter.getName());
        binding.parameterValue.setText(parameter.getValue());

        addDragHandle(binding.getRoot(), binding.dragHandle, position);

        return binding.getRoot();
    }
}
