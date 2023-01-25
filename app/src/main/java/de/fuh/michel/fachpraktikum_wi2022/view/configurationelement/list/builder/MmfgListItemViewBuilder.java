package de.fuh.michel.fachpraktikum_wi2022.view.configurationelement.list.builder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import de.fuh.michel.fachpraktikum_wi2022.databinding.FragmentMmfgListItemBinding;
import de.fuh.michel.fachpraktikum_wi2022.model.ConfigurationElement;
import de.fuh.michel.fachpraktikum_wi2022.model.configurationelement.Mmfg;

public class MmfgListItemViewBuilder implements ConfigurationListItemViewBuilder {
    @Override
    public View buildListItemView(ViewGroup parent, LayoutInflater inflater, ConfigurationElement configurationElement, int position) {
        Mmfg mmfg = (Mmfg) configurationElement;
        FragmentMmfgListItemBinding binding = FragmentMmfgListItemBinding.inflate(inflater, parent, false);

        String joinedProcessors = String.join(", ", mmfg.getProcessor());
        binding.processorValue.setText(joinedProcessors);
        addDragHandle(binding.getRoot(), binding.dragHandle, position);
        return binding.getRoot();
    }
}
