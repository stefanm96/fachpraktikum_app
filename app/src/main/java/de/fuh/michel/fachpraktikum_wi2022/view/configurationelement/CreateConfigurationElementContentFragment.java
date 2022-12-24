package de.fuh.michel.fachpraktikum_wi2022.view.configurationelement;

import static com.google.android.material.checkbox.MaterialCheckBox.STATE_CHECKED;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import de.fuh.michel.fachpraktikum_wi2022.CreateEditConfigurationElementActivity;
import de.fuh.michel.fachpraktikum_wi2022.GmafApplication;
import de.fuh.michel.fachpraktikum_wi2022.R;
import de.fuh.michel.fachpraktikum_wi2022.databinding.CheckboxBinding;
import de.fuh.michel.fachpraktikum_wi2022.databinding.FragmentCreateExportBinding;
import de.fuh.michel.fachpraktikum_wi2022.databinding.FragmentCreateFlowSourceBinding;
import de.fuh.michel.fachpraktikum_wi2022.databinding.FragmentCreateFusionBinding;
import de.fuh.michel.fachpraktikum_wi2022.databinding.FragmentCreateMmfgBinding;
import de.fuh.michel.fachpraktikum_wi2022.databinding.FragmentCreateParameterBinding;
import de.fuh.michel.fachpraktikum_wi2022.model.Definition;
import de.fuh.michel.fachpraktikum_wi2022.model.configurationelement.Export;
import de.fuh.michel.fachpraktikum_wi2022.model.configurationelement.FlowSource;
import de.fuh.michel.fachpraktikum_wi2022.model.configurationelement.Fusion;
import de.fuh.michel.fachpraktikum_wi2022.model.configurationelement.Mmfg;
import de.fuh.michel.fachpraktikum_wi2022.model.configurationelement.Parameter;
import de.fuh.michel.fachpraktikum_wi2022.model.definition.ExportDefinition;
import de.fuh.michel.fachpraktikum_wi2022.model.definition.FusionDefinition;
import de.fuh.michel.fachpraktikum_wi2022.model.definition.PluginDefinition;
import de.fuh.michel.fachpraktikum_wi2022.model.definition.ResourceDefinition;
import de.fuh.michel.fachpraktikum_wi2022.view.ProcessFlowViewModel;

public class CreateConfigurationElementContentFragment extends Fragment {

    private CreateConfigurationElementViewModel createViewModel;
    private ProcessFlowViewModel processFlowViewModel;
    private String configurationElementType;

    public static CreateConfigurationElementContentFragment newInstance() {
        Bundle args = new Bundle();
        CreateConfigurationElementContentFragment fragment = new CreateConfigurationElementContentFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createViewModel = new ViewModelProvider(getActivity()).get(CreateConfigurationElementViewModel.class);
        processFlowViewModel = ((GmafApplication) getActivity().getApplication()).getProcessFlowViewModel();

        configurationElementType = getActivity().getIntent().getStringExtra(CreateEditConfigurationElementActivity.CONFIGURATION_ELEMENT_TYPE);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        //TODO: create factory for this
        // make other elements editable
        switch (configurationElementType) {
            case Parameter.CONFIGURATION_ELEMENT_TYPE:
                return getParameterLayout(inflater, container);
            case FlowSource.CONFIGURATION_ELEMENT_TYPE:
                return getFlowSourceLayout(inflater, container);
            case Mmfg.CONFIGURATION_ELEMENT_TYPE:
                return getMmfgLayout(inflater, container);
            case Fusion.CONFIGURATION_ELEMENT_TYPE:
                return getFusionLayout(inflater, container);
            case Export.CONFIGURATION_ELEMENT_TYPE:
                return getExportLayout(inflater, container);
        }

        return inflater.inflate(R.layout.fragment_create_configuration_element, container, false);
    }

    @NonNull
    private ConstraintLayout getParameterLayout(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        FragmentCreateParameterBinding binding = FragmentCreateParameterBinding.inflate(inflater, container, false);

        Parameter parameter = (Parameter) getActivity().getIntent()
                .getSerializableExtra(CreateEditConfigurationElementActivity.CONFIGURATION_ELEMENT);

        if (parameter != null) {
            createViewModel.setName(parameter.getName());
            binding.nameInput.getEditText().setText(parameter.getName());
            createViewModel.setValue(parameter.getValue());
            binding.valueInput.getEditText().setText(parameter.getValue());
        }

        binding.nameInput.getEditText().addTextChangedListener(new LiveDataTextWatcher(createViewModel.getNameLiveData()));
        binding.valueInput.getEditText().addTextChangedListener(new LiveDataTextWatcher(createViewModel.getValueLiveData()));
        return binding.getRoot();
    }

    @NonNull
    private ConstraintLayout getMmfgLayout(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        FragmentCreateMmfgBinding binding = FragmentCreateMmfgBinding.inflate(inflater, container, false);

        List<Definition> pluginDefinitions =
                processFlowViewModel.getDefinitionsForType(PluginDefinition.DEFINITION_TYPE);

        pluginDefinitions.forEach(pluginDefinition -> {
            CheckboxBinding checkboxBinding = CheckboxBinding.inflate(inflater, container, false);
            checkboxBinding.getRoot().setText(pluginDefinition.getName());
            checkboxBinding.getRoot().addOnCheckedStateChangedListener((checkBox, state) -> {
                if(state == STATE_CHECKED) {
                    createViewModel.addProcessor(pluginDefinition.getName());
                } else {
                    createViewModel.removeProcessor(pluginDefinition.getName());
                }
            });
            binding.linearLayout.addView(checkboxBinding.getRoot());
        });

        return binding.getRoot();
    }

    @NonNull
    private ConstraintLayout getFlowSourceLayout(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        FragmentCreateFlowSourceBinding binding =
                FragmentCreateFlowSourceBinding.inflate(inflater, container, false);

        List<Definition> resourceDefinitions = processFlowViewModel.getDefinitionsForType(
                ResourceDefinition.DEFINITION_TYPE);

        List<String> options = resourceDefinitions.stream()
                .map(Definition::getName)
                .collect(Collectors.toList());

        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(getContext(), R.layout.list_item, options);

        EditText nameInput = binding.nameInput.getEditText();
        if (nameInput instanceof AutoCompleteTextView) {
            ((AutoCompleteTextView) nameInput).setAdapter(adapter);
            nameInput.addTextChangedListener(new LiveDataTextWatcher(createViewModel.getNameLiveData()));
        }

        return binding.getRoot();
    }

    @NonNull
    private ConstraintLayout getExportLayout(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
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
                new ArrayAdapter<>(getContext(), R.layout.list_item, resourceOptions);

        ArrayAdapter<String> exporterAdapter =
                new ArrayAdapter<>(getContext(), R.layout.list_item, exporterOptions);

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

        return binding.getRoot();
    }

    @NonNull
    private ConstraintLayout getFusionLayout(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        FragmentCreateFusionBinding binding = FragmentCreateFusionBinding.inflate(inflater, container, false);

        List<Definition> fusionDefinitions = processFlowViewModel.getDefinitionsForType(
                FusionDefinition.DEFINITION_TYPE);

        List<String> options = fusionDefinitions.stream()
                .map(Definition::getName)
                .collect(Collectors.toList());

        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(getContext(), R.layout.list_item, options);

        if (binding.processorInput.getEditText() instanceof AutoCompleteTextView) {
            ((AutoCompleteTextView) binding.processorInput.getEditText()).setAdapter(adapter);
            binding.processorInput.getEditText().addTextChangedListener(new LiveDataTextWatcher(createViewModel.getProcessorLiveData()));
        }

        return binding.getRoot();
    }

    private static class LiveDataTextWatcher implements TextWatcher {
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