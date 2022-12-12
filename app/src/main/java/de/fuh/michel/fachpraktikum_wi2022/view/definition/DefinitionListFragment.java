package de.fuh.michel.fachpraktikum_wi2022.view.definition;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import de.fuh.michel.fachpraktikum_wi2022.R;
import de.fuh.michel.fachpraktikum_wi2022.view.ProcessFlowViewModel;

/**
 * A fragment representing a list of Items.
 */
public class DefinitionListFragment extends Fragment {


    private static final String ARG_COLUMN_COUNT = "column-count";

    private ProcessFlowViewModel processFlowViewModel;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public DefinitionListFragment() {
    }

    @SuppressWarnings("unused")
    public static DefinitionListFragment newInstance(ProcessFlowViewModel processFlowViewModel,
                                                     int columnCount) {
        DefinitionListFragment fragment = new DefinitionListFragment();
        fragment.setProcessFlowViewModel(processFlowViewModel);
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_definition_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            RecyclerView recyclerView = (RecyclerView) view;
            DefinitionSectionRecyclerViewAdapter definitionSectionAdapter =
                    new DefinitionSectionRecyclerViewAdapter(getContext(), processFlowViewModel);
            recyclerView.setAdapter(definitionSectionAdapter);
        }
        return view;
    }

    private void setProcessFlowViewModel(ProcessFlowViewModel processFlowViewModel) {
        this.processFlowViewModel = processFlowViewModel;
    }
}