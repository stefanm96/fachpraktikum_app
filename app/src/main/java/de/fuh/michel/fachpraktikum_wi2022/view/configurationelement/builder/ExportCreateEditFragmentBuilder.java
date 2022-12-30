package de.fuh.michel.fachpraktikum_wi2022.view.configurationelement.builder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;
import java.util.stream.Collectors;

import de.fuh.michel.fachpraktikum_wi2022.R;
import de.fuh.michel.fachpraktikum_wi2022.databinding.FragmentCreateExportBinding;
import de.fuh.michel.fachpraktikum_wi2022.model.ConfigurationElement;
import de.fuh.michel.fachpraktikum_wi2022.model.Definition;
import de.fuh.michel.fachpraktikum_wi2022.model.configurationelement.Export;
import de.fuh.michel.fachpraktikum_wi2022.model.definition.ExportDefinition;
import de.fuh.michel.fachpraktikum_wi2022.model.definition.ResourceDefinition;
import de.fuh.michel.fachpraktikum_wi2022.view.ProcessFlowViewModel;
import de.fuh.michel.fachpraktikum_wi2022.view.configurationelement.CreateConfigurationElementViewModel;

public class ExportCreateEditFragmentBuilder implements CreateEditFragmentBuilder {
    @Override
    public View buildFragment(@NonNull LayoutInflater inflater,
                              @Nullable ViewGroup container,
                              ProcessFlowViewModel processFlowViewModel,
                              CreateConfigurationElementViewModel createViewModel,
                              ConfigurationElement configurationElement) {
        FragmentCreateExportBinding binding =
                FragmentCreateExportBinding.inflate(inflater, container, false);

        List<Definition> resourceDefinitions = processFlowViewModel.getDefinitionsForType(
                ResourceDefinition.DEFINITION_TYPE);
        List<Definition> exportDefinitions = processFlowViewModel.getDefinitionsForType(
                ExportDefinition.DEFINITION_TYPE);

        List<String> resourceOptions = resourceDefinitions.stream()
                .map(Definition::getName)
                .collect(Collectors.toList());

        List<String> exporterOptions = exportDefinitions.stream()
                .map(Definition::getName)
                .collect(Collectors.toList());

        ArrayAdapter<String> resourceAdapter =
                new ArrayAdapter<>(inflater.getContext(), R.layout.list_item, resourceOptions);

        ArrayAdapter<String> exporterAdapter =
                new ArrayAdapter<>(inflater.getContext(), R.layout.list_item, exporterOptions);

        EditText targetInput = binding.targetInput.getEditText();
        if (targetInput instanceof AutoCompleteTextView) {
            ((AutoCompleteTextView) targetInput).setAdapter(resourceAdapter);
            targetInput.addTextChangedListener(new LiveDataTextWatcher(createViewModel.getTargetLiveData()));
        }

        EditText exporterInput = binding.exporterInput.getEditText();
        if (exporterInput instanceof AutoCompleteTextView) {
            ((AutoCompleteTextView) exporterInput).setAdapter(exporterAdapter);
            exporterInput.addTextChangedListener(new LiveDataTextWatcher(createViewModel.getExporterLiveData()));
        }

        if (configurationElement != null) {
            Export export = (Export) configurationElement;
            targetInput.setText(export.getTarget());
            exporterInput.setText(export.getExporter());
        }

        return binding.getRoot();
    }
}
