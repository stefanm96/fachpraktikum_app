package de.fuh.michel.fachpraktikum_wi2022;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import de.fuh.michel.fachpraktikum_wi2022.databinding.ActivityDefinitionDetailsBinding;
import de.fuh.michel.fachpraktikum_wi2022.model.Definition;
import de.fuh.michel.fachpraktikum_wi2022.model.definition.ExportDefinition;
import de.fuh.michel.fachpraktikum_wi2022.model.definition.FusionDefinition;
import de.fuh.michel.fachpraktikum_wi2022.model.definition.PluginDefinition;
import de.fuh.michel.fachpraktikum_wi2022.model.definition.ResourceDefinition;
import de.fuh.michel.fachpraktikum_wi2022.view.ProcessFlowViewModel;

public class DefinitionDetailsActivity extends AppCompatActivity {

    public static final String DEFINITION_POSITION = "definition_position";

    private Definition definition;
    private ProcessFlowViewModel processFlowViewModel;
    private ActivityDefinitionDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        processFlowViewModel = ((GmafApplication) getApplication()).getProcessFlowViewModel();
        int definitionPosition = getIntent().getIntExtra(DEFINITION_POSITION, -1);
        definition = processFlowViewModel.getDefinition(definitionPosition);

        binding = ActivityDefinitionDetailsBinding.inflate(getLayoutInflater());

        setTitle("Definition Details");
        setContentView(getView());
    }

    private View getView() {
        binding.nameValue.setText(definition.getName());

        if (ResourceDefinition.DEFINITION_TYPE.equals(definition.getDefinitionType())) {
            ResourceDefinition resourceDefinition = (ResourceDefinition) definition;
            binding.text1Key.setText(R.string.type);
            binding.text1Value.setText(resourceDefinition.getType());
            binding.text2Key.setText(R.string.location);
            binding.text2Value.setText(resourceDefinition.getLocation());
            return binding.getRoot();
        }

        binding.text1Key.setText(R.string.clazz);
        binding.text2Key.setVisibility(View.INVISIBLE);
        binding.text2Value.setVisibility(View.INVISIBLE);

        switch (definition.getDefinitionType()) {
            case PluginDefinition.DEFINITION_TYPE:
                PluginDefinition pluginDefinition = (PluginDefinition) definition;
                binding.text1Value.setText(pluginDefinition.getClazz());
                break;
            case FusionDefinition.DEFINITION_TYPE:
                FusionDefinition fusionDefinition = (FusionDefinition) definition;
                binding.text1Value.setText(fusionDefinition.getClazz());
                break;
            case ExportDefinition.DEFINITION_TYPE:
                ExportDefinition exportDefinition = (ExportDefinition) definition;
                binding.text1Value.setText(exportDefinition.getClazz());
                break;
        }

        return binding.getRoot();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_definition_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_delete_definition) {
            showAlertDialog("Deleting " + definition.getName(), (dialog, which) -> {
                deleteDefinition();
                dialog.dismiss();
                finish();
            });
        }
        if (id == android.R.id.home) {
            finish();
        }
        return true;
    }

    private void showAlertDialog(String title, DialogInterface.OnClickListener onClickListener) {
        MaterialAlertDialogBuilder alertDialogBuilder = new MaterialAlertDialogBuilder(this)
                .setTitle(title)
                .setMessage(getResources().getString(R.string.dialog_delete_definition));

        alertDialogBuilder.setNeutralButton(getResources().getString(R.string.cancel),
                (dialog, which) -> dialog.cancel());

        alertDialogBuilder.setPositiveButton(getResources().getString(R.string.accept), onClickListener);

        alertDialogBuilder.show();
    }

    private void deleteDefinition() {
        processFlowViewModel.removeDefinition(definition);
    }
}