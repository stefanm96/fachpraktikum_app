package de.fuh.michel.fachpraktikum_wi2022.view.configurationelement.builder;

import static com.google.android.material.checkbox.MaterialCheckBox.STATE_CHECKED;
import static com.google.android.material.checkbox.MaterialCheckBox.STATE_UNCHECKED;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import de.fuh.michel.fachpraktikum_wi2022.databinding.CheckboxBinding;
import de.fuh.michel.fachpraktikum_wi2022.databinding.FragmentCreateMmfgBinding;
import de.fuh.michel.fachpraktikum_wi2022.model.ConfigurationElement;
import de.fuh.michel.fachpraktikum_wi2022.model.Definition;
import de.fuh.michel.fachpraktikum_wi2022.model.configurationelement.Mmfg;
import de.fuh.michel.fachpraktikum_wi2022.model.definition.PluginDefinition;
import de.fuh.michel.fachpraktikum_wi2022.view.ProcessFlowViewModel;
import de.fuh.michel.fachpraktikum_wi2022.view.configurationelement.CreateConfigurationElementViewModel;

public class MmfgCreateEditFragmentBuilder implements CreateEditFragmentBuilder {
    @Override
    public View buildFragment(@NonNull LayoutInflater inflater,
                              @Nullable ViewGroup container,
                              ProcessFlowViewModel processFlowViewModel,
                              CreateConfigurationElementViewModel createViewModel,
                              ConfigurationElement configurationElement) {
        FragmentCreateMmfgBinding binding = FragmentCreateMmfgBinding.inflate(inflater, container, false);

        List<Definition> pluginDefinitions =
                processFlowViewModel.getDefinitionsForType(PluginDefinition.DEFINITION_TYPE);

        Mmfg mmfg = (Mmfg) configurationElement;

        pluginDefinitions.forEach(pluginDefinition -> {
            CheckboxBinding checkboxBinding = CheckboxBinding.inflate(inflater, container, false);
            checkboxBinding.getRoot().setText(pluginDefinition.getName());
            checkboxBinding.getRoot().addOnCheckedStateChangedListener((checkBox, state) -> {
                if (state == STATE_CHECKED) {
                    createViewModel.addProcessor(pluginDefinition.getName());
                } else {
                    createViewModel.removeProcessor(pluginDefinition.getName());
                }
            });

            if (mmfg != null) {
                int initialState = mmfg.getProcessor().contains(pluginDefinition.getName())
                        ? STATE_CHECKED : STATE_UNCHECKED;
                checkboxBinding.getRoot().setCheckedState(initialState);
            }

            binding.linearLayout.addView(checkboxBinding.getRoot());
        });

        return binding.getRoot();
    }
}
