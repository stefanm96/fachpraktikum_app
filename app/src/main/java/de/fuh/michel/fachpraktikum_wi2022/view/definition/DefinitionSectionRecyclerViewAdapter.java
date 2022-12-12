package de.fuh.michel.fachpraktikum_wi2022.view.definition;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.stream.Collectors;

import de.fuh.michel.fachpraktikum_wi2022.R;
import de.fuh.michel.fachpraktikum_wi2022.databinding.FragmentDefinitionSectionRowBinding;
import de.fuh.michel.fachpraktikum_wi2022.model.Definition;
import de.fuh.michel.fachpraktikum_wi2022.model.definition.ExportDefinition;
import de.fuh.michel.fachpraktikum_wi2022.model.definition.FusionDefinition;
import de.fuh.michel.fachpraktikum_wi2022.model.definition.PluginDefinition;
import de.fuh.michel.fachpraktikum_wi2022.model.definition.ResourceDefinition;
import de.fuh.michel.fachpraktikum_wi2022.view.ProcessFlowViewModel;

public class DefinitionSectionRecyclerViewAdapter
        extends RecyclerView.Adapter<DefinitionSectionRecyclerViewAdapter.ViewHolder>{

    @StringRes
    private static final int[] SECTION_TITLES = new int[]{
            R.string.section_plugins,
            R.string.section_fusions,
            R.string.section_exporters,
            R.string.section_resources};

    private static final String[] DEFINITION_TYPES = {PluginDefinition.DEFINITION_TYPE,
            FusionDefinition.DEFINITION_TYPE,
            ExportDefinition.DEFINITION_TYPE,
            ResourceDefinition.DEFINITION_TYPE};

    private final ProcessFlowViewModel processFlowViewModel;
    private final Context context;

    public DefinitionSectionRecyclerViewAdapter(Context context, ProcessFlowViewModel processFlowViewModel) {
        this.context = context;
        this.processFlowViewModel = processFlowViewModel;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        FragmentDefinitionSectionRowBinding binding = FragmentDefinitionSectionRowBinding.inflate(
                LayoutInflater.from(parent.getContext()));
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String currentSection = context.getResources().getString(SECTION_TITLES[position]);
        String currentDefinitionType = DEFINITION_TYPES[position];
        holder.sectionHeader.setText(currentSection);

        DefinitionRecyclerViewAdapter definitionRecyclerViewAdapter = new DefinitionRecyclerViewAdapter(currentDefinitionType);

        definitionRecyclerViewAdapter.setClickListener(processFlowViewModel::removeDefinition);

        processFlowViewModel.getDefinitionsLiveData().observe((LifecycleOwner) context, list -> {
            List<Definition> definitionsByType = getDefinitionsByType(DEFINITION_TYPES[position], list);
            definitionRecyclerViewAdapter.submitList(definitionsByType);
        });

        holder.sectionRecyclerView.setAdapter(definitionRecyclerViewAdapter);
    }

    private static List<Definition> getDefinitionsByType(String currentDefinitionType, List<Definition> list) {
        return list.stream()
                .filter(definition -> definition.getDefinitionType().equals(currentDefinitionType))
                .collect(Collectors.toList());
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView sectionHeader;
        RecyclerView sectionRecyclerView;

        public ViewHolder(@NonNull FragmentDefinitionSectionRowBinding binding) {
            super(binding.getRoot());

            sectionHeader = binding.sectionHeader;
            sectionRecyclerView = binding.sectionRecyclerView;
        }
    }
}
