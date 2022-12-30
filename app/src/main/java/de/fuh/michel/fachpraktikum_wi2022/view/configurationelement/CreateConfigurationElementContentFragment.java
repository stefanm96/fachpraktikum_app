package de.fuh.michel.fachpraktikum_wi2022.view.configurationelement;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import de.fuh.michel.fachpraktikum_wi2022.CreateEditConfigurationElementActivity;
import de.fuh.michel.fachpraktikum_wi2022.GmafApplication;
import de.fuh.michel.fachpraktikum_wi2022.model.ConfigurationElement;
import de.fuh.michel.fachpraktikum_wi2022.view.ProcessFlowViewModel;
import de.fuh.michel.fachpraktikum_wi2022.view.configurationelement.builder.CreateEditFragmentBuilder;
import de.fuh.michel.fachpraktikum_wi2022.view.configurationelement.builder.CreateEditFragmentBuilderFactory;

public class CreateConfigurationElementContentFragment extends Fragment {

    private CreateConfigurationElementViewModel createViewModel;
    private ProcessFlowViewModel processFlowViewModel;
    private CreateEditFragmentBuilderFactory builderFactory;
    private String configurationElementType;
    private ConfigurationElement configurationElement;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createViewModel = new ViewModelProvider(getActivity()).get(CreateConfigurationElementViewModel.class);
        processFlowViewModel = ((GmafApplication) getActivity().getApplication()).getProcessFlowViewModel();
        setBuilderFactory(new CreateEditFragmentBuilderFactory());

        configurationElementType = getActivity().getIntent().getStringExtra(CreateEditConfigurationElementActivity.CONFIGURATION_ELEMENT_TYPE);
        configurationElement = (ConfigurationElement) getActivity().getIntent()
                .getSerializableExtra(CreateEditConfigurationElementActivity.CONFIGURATION_ELEMENT);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        CreateEditFragmentBuilder fragmentBuilder =
                builderFactory.getFragmentBuilder(configurationElementType);

        return fragmentBuilder.buildFragment(inflater, container,
                processFlowViewModel, createViewModel, configurationElement);
    }

    public void setBuilderFactory(CreateEditFragmentBuilderFactory builderFactory) {
        this.builderFactory = builderFactory;
    }
}