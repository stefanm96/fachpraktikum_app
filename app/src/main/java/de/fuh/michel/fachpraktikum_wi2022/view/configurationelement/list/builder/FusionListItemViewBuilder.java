package de.fuh.michel.fachpraktikum_wi2022.view.configurationelement.list.builder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import de.fuh.michel.fachpraktikum_wi2022.databinding.FragmentFusionListItemBinding;
import de.fuh.michel.fachpraktikum_wi2022.model.ConfigurationElement;
import de.fuh.michel.fachpraktikum_wi2022.model.configurationelement.Fusion;

public class FusionListItemViewBuilder implements ConfigurationListItemViewBuilder {
    @Override
    public View buildListItemView(ViewGroup parent, LayoutInflater inflater, ConfigurationElement configurationElement, int position) {
        Fusion fusion = (Fusion) configurationElement;
        FragmentFusionListItemBinding binding = FragmentFusionListItemBinding.inflate(inflater, parent, false);

        binding.processorValue.setText(fusion.getProcessor());
        addDragHandle(binding.getRoot(), binding.dragHandle, position);
        return binding.getRoot();
    }
}
