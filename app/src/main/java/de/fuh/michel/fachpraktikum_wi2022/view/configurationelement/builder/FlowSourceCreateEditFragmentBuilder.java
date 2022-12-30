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
import de.fuh.michel.fachpraktikum_wi2022.databinding.FragmentCreateFlowSourceBinding;
import de.fuh.michel.fachpraktikum_wi2022.model.ConfigurationElement;
import de.fuh.michel.fachpraktikum_wi2022.model.Definition;
import de.fuh.michel.fachpraktikum_wi2022.model.configurationelement.FlowSource;
import de.fuh.michel.fachpraktikum_wi2022.model.definition.ResourceDefinition;
import de.fuh.michel.fachpraktikum_wi2022.view.ProcessFlowViewModel;
import de.fuh.michel.fachpraktikum_wi2022.view.configurationelement.CreateConfigurationElementViewModel;

public class FlowSourceCreateEditFragmentBuilder implements CreateEditFragmentBuilder {

    @Override
    public View buildFragment(@NonNull LayoutInflater inflater,
                              @Nullable ViewGroup container,
                              ProcessFlowViewModel processFlowViewModel,
                              CreateConfigurationElementViewModel createViewModel,
                              ConfigurationElement configurationElement) {
        FragmentCreateFlowSourceBinding binding =
                FragmentCreateFlowSourceBinding.inflate(inflater, container, false);

        List<Definition> resourceDefinitions = processFlowViewModel.getDefinitionsForType(
                ResourceDefinition.DEFINITION_TYPE);

        List<String> options = resourceDefinitions.stream()
                .map(Definition::getName)
                .collect(Collectors.toList());

        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(inflater.getContext(), R.layout.list_item, options);

        EditText nameInput = binding.nameInput.getEditText();
        if (nameInput instanceof AutoCompleteTextView) {
            ((AutoCompleteTextView) nameInput).setAdapter(adapter);
            nameInput.addTextChangedListener(new LiveDataTextWatcher(createViewModel.getNameLiveData()));
        }


        if (configurationElement != null) {
            FlowSource flowSource = (FlowSource) configurationElement;
            nameInput.setText(flowSource.getName());
        }

        return binding.getRoot();
    }
}
