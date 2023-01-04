package de.fuh.michel.fachpraktikum_wi2022;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import de.fuh.michel.fachpraktikum_wi2022.databinding.ActivityCreateEditProcessFlowBinding;
import de.fuh.michel.fachpraktikum_wi2022.view.ProcessFlowViewModel;

public class CreateEditProcessFlowActivity extends AppCompatActivity {

    public static final String PROCESS_FLOW = "PROCESS_FLOW";

    private ActivityCreateEditProcessFlowBinding binding;
    private ProcessFlowViewModel processFlowViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        processFlowViewModel = ((GmafApplication) getApplication()).getProcessFlowViewModel();
        binding = ActivityCreateEditProcessFlowBinding.inflate(getLayoutInflater());

        boolean isEditActivity = getIntent().getBooleanExtra(PROCESS_FLOW, false);
        setTitle(R.string.title_edit_process_flow);

        if (isEditActivity) {
            binding.nameInput.getEditText().setText(processFlowViewModel.getName());
            binding.extensionInput.getEditText().setText(processFlowViewModel.getExtension());
            binding.isGeneralInput.setChecked(processFlowViewModel.getIsGeneral());
        }

        setContentView(binding.getRoot());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_edit_process_flow, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_new_process_flow) {
            String title = getResources().getString(R.string.dialog_new_process_flow_title);
            showAlertDialog(title, (dialog, which) -> {
                resetValues();
                dialog.dismiss();
            });
        }
        if (id == R.id.action_save_process_flow) {
            saveProcessFlowValues();
            finish();
        }
        if (id == android.R.id.home) {
            finish();
        }
        return true;
    }

    private void resetValues() {
        processFlowViewModel.newProcessFlow();
        binding.nameInput.getEditText().setText("");
        binding.extensionInput.getEditText().setText("");
        binding.isGeneralInput.setChecked(false);
    }

    private void saveProcessFlowValues() {
        processFlowViewModel.setName(binding.nameInput.getEditText().getText().toString());
        processFlowViewModel.setExtension(binding.extensionInput.getEditText().getText().toString());
        processFlowViewModel.setIsGeneral(binding.isGeneralInput.isChecked());
    }

    private void showAlertDialog(String title, DialogInterface.OnClickListener onClickListener) {
        MaterialAlertDialogBuilder alertDialogBuilder = new MaterialAlertDialogBuilder(this)
                .setTitle(title)
                .setMessage(getResources().getString(R.string.dialog_new_process_flow_message));

        alertDialogBuilder.setNeutralButton(getResources().getString(R.string.cancel),
                (dialog, which) -> dialog.cancel());

        alertDialogBuilder.setPositiveButton(getResources().getString(R.string.accept), onClickListener);

        alertDialogBuilder.show();
    }
}