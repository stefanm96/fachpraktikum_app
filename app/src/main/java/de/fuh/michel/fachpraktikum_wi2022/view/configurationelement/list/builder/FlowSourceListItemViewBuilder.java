package de.fuh.michel.fachpraktikum_wi2022.view.configurationelement.list.builder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import de.fuh.michel.fachpraktikum_wi2022.databinding.FragmentFlowSourceListItemBinding;
import de.fuh.michel.fachpraktikum_wi2022.model.ConfigurationElement;
import de.fuh.michel.fachpraktikum_wi2022.model.configurationelement.FlowSource;

public class FlowSourceListItemViewBuilder implements ConfigurationListItemViewBuilder {
    @Override
    public View buildListItemView(ViewGroup parent, LayoutInflater inflater, ConfigurationElement configurationElement, int position) {
        FlowSource flowSource = (FlowSource) configurationElement;
        FragmentFlowSourceListItemBinding binding = FragmentFlowSourceListItemBinding.inflate(inflater, parent, false);

        binding.flowSourceValue.setText(flowSource.getName());
        addDragHandle(binding.getRoot(), binding.dragHandle, position);
        return binding.getRoot();
    }
}
