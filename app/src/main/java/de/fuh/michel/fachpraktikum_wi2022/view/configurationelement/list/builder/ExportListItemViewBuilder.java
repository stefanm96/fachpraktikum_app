package de.fuh.michel.fachpraktikum_wi2022.view.configurationelement.list.builder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import de.fuh.michel.fachpraktikum_wi2022.databinding.FragmentExportListItemBinding;
import de.fuh.michel.fachpraktikum_wi2022.model.ConfigurationElement;
import de.fuh.michel.fachpraktikum_wi2022.model.configurationelement.Export;

public class ExportListItemViewBuilder implements ConfigurationListItemViewBuilder {
    @Override
    public View buildListItemView(ViewGroup parent, LayoutInflater inflater, ConfigurationElement configurationElement, int position) {
        Export export = (Export) configurationElement;
        FragmentExportListItemBinding binding =
                FragmentExportListItemBinding.inflate(inflater, parent, false);

        binding.targetValue.setText(export.getTarget());
        binding.exporterValue.setText(export.getExporter());

        if (export.getExporter() == null || export.getExporter().trim().equals("")) {
            binding.exporter.setVisibility(View.INVISIBLE);
            binding.exporterValue.setVisibility(View.INVISIBLE);
            binding.colon2.setVisibility(View.INVISIBLE);
        }

        addDragHandle(binding.getRoot(), binding.dragHandle, position);
        return binding.getRoot();
    }
}
