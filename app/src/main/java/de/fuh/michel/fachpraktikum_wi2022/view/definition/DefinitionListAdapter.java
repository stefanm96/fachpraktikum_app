package de.fuh.michel.fachpraktikum_wi2022.view.definition;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import de.fuh.michel.fachpraktikum_wi2022.databinding.DefinitionListItemBinding;
import de.fuh.michel.fachpraktikum_wi2022.model.Definition;

public class DefinitionListAdapter extends BaseAdapter {

    private List<Definition> definitions;

    @Override
    public int getCount() {
        return definitions.size();
    }

    @Override
    public Definition getItem(int position) {
        return definitions.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Definition definition = getItem(position);

        if (convertView != null) {
            TextView textView = (TextView) convertView;
            textView.setText(definition.getName());
            return textView;
        }

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        DefinitionListItemBinding binding = DefinitionListItemBinding.inflate(inflater, parent, false);

        binding.getRoot().setText(definition.getName());

        return binding.getRoot();
    }

    public void setList(List<Definition> definitionsByType) {
        this.definitions = new ArrayList<>(definitionsByType);
        notifyDataSetChanged();
    }
}
