package de.fuh.michel.fachpraktikum_wi2022.view.definition;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.dialog.MaterialDialogs;

import de.fuh.michel.fachpraktikum_wi2022.CreateEditConfigurationElementActivity;
import de.fuh.michel.fachpraktikum_wi2022.DefinitionDetailsActivity;
import de.fuh.michel.fachpraktikum_wi2022.R;
import de.fuh.michel.fachpraktikum_wi2022.databinding.FragmentDefinitionListBinding;
import de.fuh.michel.fachpraktikum_wi2022.model.Definition;
import de.fuh.michel.fachpraktikum_wi2022.view.ProcessFlowViewModel;

/**
 * A fragment representing a list of Items.
 */
public class DefinitionListFragment extends Fragment {

    private ProcessFlowViewModel processFlowViewModel;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public DefinitionListFragment() {
    }

    @SuppressWarnings("unused")
    public static DefinitionListFragment newInstance(ProcessFlowViewModel processFlowViewModel) {
        DefinitionListFragment fragment = new DefinitionListFragment();
        fragment.setProcessFlowViewModel(processFlowViewModel);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentDefinitionListBinding binding = FragmentDefinitionListBinding.inflate(inflater, container, false);

        DefinitionSectionRecyclerViewAdapter definitionSectionAdapter =
                new DefinitionSectionRecyclerViewAdapter(getContext(), processFlowViewModel,
                        this::showDefinitionDetailsActivity);
        binding.definitionList.setAdapter(definitionSectionAdapter);

        return binding.definitionList;
    }

    private void showDefinitionDetailsActivity(Definition definition) {
        Intent intent = new Intent(getActivity(), DefinitionDetailsActivity.class);
        int position = processFlowViewModel.getDefinitionsLiveData().getValue().indexOf(definition);
        intent.putExtra(DefinitionDetailsActivity.DEFINITION_POSITION, position);
        startActivity(intent);
    }

    private void setProcessFlowViewModel(ProcessFlowViewModel processFlowViewModel) {
        this.processFlowViewModel = processFlowViewModel;
    }
}