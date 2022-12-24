package de.fuh.michel.fachpraktikum_wi2022.view.configurationelement.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.List;

import de.fuh.michel.fachpraktikum_wi2022.databinding.FragmentConfigurationListItemBinding;
import de.fuh.michel.fachpraktikum_wi2022.databinding.FragmentFlowSourceListItemBinding;
import de.fuh.michel.fachpraktikum_wi2022.databinding.FragmentFusionListItemBinding;
import de.fuh.michel.fachpraktikum_wi2022.databinding.FragmentParameterListItemBinding;
import de.fuh.michel.fachpraktikum_wi2022.model.ConfigurationElement;
import de.fuh.michel.fachpraktikum_wi2022.model.configurationelement.Export;
import de.fuh.michel.fachpraktikum_wi2022.model.configurationelement.FlowSource;
import de.fuh.michel.fachpraktikum_wi2022.model.configurationelement.Fusion;
import de.fuh.michel.fachpraktikum_wi2022.model.configurationelement.Mmfg;
import de.fuh.michel.fachpraktikum_wi2022.model.configurationelement.Parameter;
import de.fuh.michel.fachpraktikum_wi2022.view.configurationelement.list.builder.ConfigurationListItemViewBuilder;
import de.fuh.michel.fachpraktikum_wi2022.view.configurationelement.list.builder.ConfigurationListItemViewBuilderFactory;
import de.fuh.michel.fachpraktikum_wi2022.view.configurationelement.list.builder.ParameterListItemViewBuilder;

public class ConfigurationListAdapter extends BaseAdapter implements AdapterView.OnItemClickListener {

    private final ConfigurationListItemViewBuilderFactory viewBuilderFactory;
    private final ItemClickListener mClickListener;
    private List<ConfigurationElement> configurationElements;

    public ConfigurationListAdapter(ConfigurationListItemViewBuilderFactory viewBuilderFactory,
                                    ItemClickListener mClickListener) {
        this.viewBuilderFactory = viewBuilderFactory;
        this.mClickListener = mClickListener;
    }

    @Override
    public int getCount() {
        return configurationElements.size();
    }

    @Override
    public ConfigurationElement getItem(int position) {
        return configurationElements.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        return getViewByConfigurationElement(parent, inflater, position);
    }

    @NonNull
    private View getViewByConfigurationElement(ViewGroup parent,
                                               LayoutInflater inflater,
                                               int position) {
        ConfigurationElement configurationElement = getItem(position);
        ConfigurationListItemViewBuilder viewBuilder = viewBuilderFactory.getViewBuilder(configurationElement);

        return viewBuilder.buildListItemView(parent, inflater, configurationElement, position);
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public void setConfigurationElements(List<ConfigurationElement> configurationElements) {
        this.configurationElements = configurationElements;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        System.out.println("CLICK: " + getItem(position));
        mClickListener.onItemClick(position, getItem(position));
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(int position, ConfigurationElement configurationElement);
    }
}