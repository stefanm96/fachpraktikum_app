package de.fuh.michel.fachpraktikum_wi2022;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import de.fuh.michel.fachpraktikum_wi2022.databinding.ActivityCreateConfigurationElementBinding;
import de.fuh.michel.fachpraktikum_wi2022.model.ConfigurationElement;
import de.fuh.michel.fachpraktikum_wi2022.view.ProcessFlowViewModel;
import de.fuh.michel.fachpraktikum_wi2022.view.configurationelement.CreateConfigurationElementViewModel;

public class CreateEditConfigurationElementActivity extends AppCompatActivity {

    public static final String CONFIGURATION_ELEMENT_TYPE = "configuration_element_type";
    public static final String CONFIGURATION_ELEMENT = "configuration_element";
    public static final String POSITION = "position";

    private static final String TAG = "CreateEditConfigurationElementActivity";

    private ProcessFlowViewModel processFlowViewModel;
    private CreateConfigurationElementViewModel createViewModel;
    private String configurationElementType;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        de.fuh.michel.fachpraktikum_wi2022.databinding.ActivityCreateConfigurationElementBinding binding = ActivityCreateConfigurationElementBinding.inflate(getLayoutInflater());
        createViewModel = new ViewModelProvider(this).get(CreateConfigurationElementViewModel.class);
        processFlowViewModel = ((GmafApplication) getApplication()).getProcessFlowViewModel();
        configurationElementType = getIntent().getStringExtra(CONFIGURATION_ELEMENT_TYPE);
        position = getIntent().getIntExtra(POSITION, -1);

        if (isCreateView()) {
            setTitle("Create " + getIntent().getStringExtra(CONFIGURATION_ELEMENT_TYPE));
        } else {
            setTitle("Edit " + getIntent().getStringExtra(CONFIGURATION_ELEMENT_TYPE));
        }

        setContentView(binding.getRoot());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_create_configuration_element, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem deleteButton = menu.findItem(R.id.action_delete_configuration_element);
        if (isCreateView()) {
            deleteButton.setVisible(false);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_create_edit_configuration_element) {
            ConfigurationElement configurationElement =
                    createViewModel.createConfigurationElementOfType(configurationElementType);

            if (isCreateView()) {
                addConfigurationElement(configurationElement);
            } else {
                editConfigurationElement(position, configurationElement);
            }

            finish();
        } if (id == R.id.action_delete_configuration_element) {
            showAlertDialog("Deleting " + configurationElementType, (dialog, which) -> {
                deleteConfigurationElement(position);
                dialog.dismiss();
                finish();
            });
        }
        if (id == android.R.id.home) {
            finish();
        }
        return true;
    }

    private boolean isCreateView() {
        return getIntent().getIntExtra(POSITION, -1) == -1;
    }

    private void deleteConfigurationElement(int position) {
        processFlowViewModel.removeConfigurationElement(position);
        Toast.makeText(this,
                        "Removed at: " + position,
                        Toast.LENGTH_SHORT)
                .show();
    }

    private void editConfigurationElement(int position, ConfigurationElement configurationElement) {
        processFlowViewModel.editConfigurationElement(position, configurationElement);
        Toast.makeText(this,
                        "Edited: " + configurationElement.toString(),
                        Toast.LENGTH_SHORT)
                .show();
    }

    private void addConfigurationElement(ConfigurationElement configurationElement) {
        processFlowViewModel.addConfigurationElement(configurationElement);
        Toast.makeText(this,
                        "Added: " + configurationElement.toString(),
                        Toast.LENGTH_SHORT)
                .show();
    }

    private void showAlertDialog(String title, DialogInterface.OnClickListener onClickListener) {
        MaterialAlertDialogBuilder alertDialogBuilder = new MaterialAlertDialogBuilder(this)
                .setTitle(title)
                .setMessage(getResources().getString(R.string.dialog_delete_confirmation_message));

        alertDialogBuilder.setNeutralButton(getResources().getString(R.string.cancel),
                (dialog, which) -> dialog.cancel());

        alertDialogBuilder.setPositiveButton(getResources().getString(R.string.accept), onClickListener);

        alertDialogBuilder.show();
    }
}