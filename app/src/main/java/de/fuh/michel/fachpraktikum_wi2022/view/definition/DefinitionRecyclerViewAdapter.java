package de.fuh.michel.fachpraktikum_wi2022.view.definition;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Objects;

import de.fuh.michel.fachpraktikum_wi2022.databinding.FragmentDefintionBinding;
import de.fuh.michel.fachpraktikum_wi2022.model.Definition;

public class DefinitionRecyclerViewAdapter
        extends ListAdapter<Definition, DefinitionRecyclerViewAdapter.DefinitionViewHolder> {

    private final String currentDefinitionType;

    private ItemClickListener mClickListener;

    public DefinitionRecyclerViewAdapter(String currentDefinitionType) {
        super(DIFF_CALLBACK);
        this.currentDefinitionType = currentDefinitionType;
    }

    public static final DiffUtil.ItemCallback<Definition> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Definition>() {
                @Override
                public boolean areItemsTheSame(Definition oldDefinition, Definition newDefinition) {
                    return Objects.equals(oldDefinition, newDefinition);
                }
                @Override
                public boolean areContentsTheSame(Definition oldDefinition, Definition newDefinition) {
                    if (oldDefinition == null && newDefinition == null) {
                        return true;
                    }
                    if (oldDefinition == null || newDefinition == null) {
                        return false;
                    }
                    return Objects.equals(oldDefinition.getName(), newDefinition.getName()) &&
                            Objects.equals(oldDefinition.getDefinitionType(), newDefinition.getDefinitionType());
                }
            };

    @Override
    public DefinitionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        FragmentDefintionBinding binding = FragmentDefintionBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        return new DefinitionViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(final DefinitionViewHolder holder, int position) {
        Definition definition = getItem(position);

        holder.mItem = definition;
        holder.mDefinitionNameView.setText(definition.getName());
        holder.mDefinitionNameView.setOnClickListener(holder);
    }

    @Override
    public int getItemCount() {
        return getCurrentList().size();
    }

    public String getCurrentDefinitionType() {
        return currentDefinitionType;
    }

    public void setClickListener(ItemClickListener mClickListener) {
        this.mClickListener = mClickListener;
    }

    public class DefinitionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView mDefinitionNameView;
        public Definition mItem;

        public DefinitionViewHolder(@NonNull FragmentDefintionBinding binding) {
            super(binding.getRoot());

            mDefinitionNameView = binding.definitionName;
            mDefinitionNameView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            System.out.println("CLICK: " + mItem);
            if (mClickListener != null) mClickListener.onItemClick(mItem);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mDefinitionNameView.getText() + "'";
        }
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(Definition definition);
    }
}