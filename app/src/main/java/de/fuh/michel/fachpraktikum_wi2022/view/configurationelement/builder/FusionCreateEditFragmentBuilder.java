package de.fuh.michel.fachpraktikum_wi2022.view.configurationelement.builder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;
import java.util.stream.Collectors;

import de.fuh.michel.fachpraktikum_wi2022.R;
import de.fuh.michel.fachpraktikum_wi2022.databinding.FragmentCreateFusionBinding;
import de.fuh.michel.fachpraktikum_wi2022.model.ConfigurationElement;
import de.fuh.michel.fachpraktikum_wi2022.model.Definition;
import de.fuh.michel.fachpraktikum_wi2022.model.configurationelement.Fusion;
import de.fuh.michel.fachpraktikum_wi2022.model.definition.FusionDefinition;
import de.fuh.michel.fachpraktikum_wi2022.view.ProcessFlowViewModel;
import de.fuh.michel.fachpraktikum_wi2022.view.configurationelement.CreateConfigurationElementViewModel;

public class FusionCreateEditFragmentBuilder implements CreateEditFragmentBuilder {
    @Override
    public View buildFragment(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, ProcessFlowViewModel processFlowViewModel, CreateConfigurationElementViewModel createViewModel, ConfigurationElement configurationElement) {
        FragmentCreateFusionBinding binding = FragmentCreateFusionBinding.inflate(inflater, container, false);

        List<Definition> fusionDefinitions = processFlowViewModel.getDefinitionsForType(
                FusionDefinition.DEFINITION_TYPE);

        List<String> options = fusionDefinitions.stream()
                .map(Definition::getName)
                .collect(Collectors.toList());

        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(inflater.getContext(), R.layout.list_item, options);

        if (binding.processorInput.getEditText() instanceof AutoCompleteTextView) {
            ((AutoCompleteTextView) binding.processorInput.getEditText()).setAdapter(adapter);
            binding.processorInput.getEditText().addTextChangedListener(new LiveDataTextWatcher(createViewModel.getProcessorLiveData()));
        }

        if (configurationElement != null) {
            Fusion fusion = (Fusion) configurationElement;
            binding.processorInput.getEditText().setText(fusion.getProcessor());
        }

        return binding.getRoot();
    }
}
