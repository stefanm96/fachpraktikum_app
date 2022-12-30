package de.fuh.michel.fachpraktikum_wi2022.view.configurationelement.list;

import android.content.Intent;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;

import java.util.ArrayList;

import de.fuh.michel.fachpraktikum_wi2022.CreateEditConfigurationElementActivity;
import de.fuh.michel.fachpraktikum_wi2022.databinding.FragmentConfigurationListBinding;
import de.fuh.michel.fachpraktikum_wi2022.model.ConfigurationElement;
import de.fuh.michel.fachpraktikum_wi2022.view.ProcessFlowViewModel;
import de.fuh.michel.fachpraktikum_wi2022.view.configurationelement.list.builder.ConfigurationListItemViewBuilderFactory;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ConfigurationListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ConfigurationListFragment extends Fragment {

    private ProcessFlowViewModel processFlowViewModel;

    public ConfigurationListFragment() {
    }

    @SuppressWarnings("unused")
    public static ConfigurationListFragment newInstance(ProcessFlowViewModel processFlowViewModel) {
        ConfigurationListFragment fragment = new ConfigurationListFragment();
        fragment.setProcessFlowViewModel(processFlowViewModel);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentConfigurationListBinding binding =
                FragmentConfigurationListBinding.inflate(inflater, container, false);

        ConfigurationListAdapter configurationListAdapter =
                new ConfigurationListAdapter(new ConfigurationListItemViewBuilderFactory(), this::startEdit);

        processFlowViewModel.getConfigurationElementsLiveData().observe(
                (LifecycleOwner) getContext(),
                list -> {
                    configurationListAdapter.setConfigurationElements(
                            new ArrayList<>(list));
                    configurationListAdapter.notifyDataSetChanged();
                });

        binding.configurationElementList.setAdapter(configurationListAdapter);
        binding.configurationElementList.setOnItemClickListener(configurationListAdapter);
        binding.configurationElementList.setOnDragListener((view, dragEvent) -> handleDragAndDrop(binding, dragEvent));

        return binding.getRoot();
    }

    private void startEdit(int position, ConfigurationElement configurationElement) {
        Intent intent = new Intent(getActivity(), CreateEditConfigurationElementActivity.class);
        intent.putExtra(CreateEditConfigurationElementActivity.CONFIGURATION_ELEMENT_TYPE,
                configurationElement.getConfigurationElementType());
        intent.putExtra(CreateEditConfigurationElementActivity.POSITION, position);
        intent.putExtra(CreateEditConfigurationElementActivity.CONFIGURATION_ELEMENT,
                configurationElement);
        startActivity(intent);
    }

    private boolean handleDragAndDrop(FragmentConfigurationListBinding binding, DragEvent dragEvent) {
        switch (dragEvent.getAction()) {
            case DragEvent.ACTION_DROP:
                String fromPositionString =
                        dragEvent.getClipData().getItemAt(0).getText().toString();
                int fromPosition = Integer.parseInt(fromPositionString);
                int toPosition = binding.configurationElementList.pointToPosition(
                        (int) dragEvent.getX(), (int) dragEvent.getY());

                processFlowViewModel.swapConfigurationElements(fromPosition, toPosition);
                break;
            default:
                break;
        }
        return true;
    }

    private void setProcessFlowViewModel(ProcessFlowViewModel processFlowViewModel) {
        this.processFlowViewModel = processFlowViewModel;
    }
}