package de.fuh.michel.fachpraktikum_wi2022;

import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

import de.fuh.michel.fachpraktikum_wi2022.databinding.ActivitySelectFileBinding;
import de.fuh.michel.fachpraktikum_wi2022.domain.xml.FileProvider;
import de.fuh.michel.fachpraktikum_wi2022.view.ProcessFlowViewModel;

public class SelectFileActivity extends AppCompatActivity {

    private FileProvider fileProvider;
    private ProcessFlowViewModel processFlowViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setProcessFlowViewModel(((GmafApplication) getApplication()).getProcessFlowViewModel());
        setFileProvider(((GmafApplication) getApplication()).getFileProvider());

        ActivitySelectFileBinding binding = ActivitySelectFileBinding.inflate(getLayoutInflater());

        ArrayAdapter<String> selectFileListAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, fileProvider.getAllFiles());

        binding.listView.setAdapter(selectFileListAdapter);

        binding.listView.setOnItemClickListener((parent, view, position, id) ->
                handleItemClick(parent, position));

        setContentView(binding.getRoot());
    }

    private void handleItemClick(AdapterView<?> parent, int position) {
        String selectedFilename = (String) parent.getItemAtPosition(position);

        try {
            String fileContent = fileProvider.getFileContent(selectedFilename);
            processFlowViewModel.importProcessFlow(fileContent);
            finish();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Import failed!", Toast.LENGTH_SHORT).show();
        }
    }

    public void setFileProvider(FileProvider fileProvider) {
        this.fileProvider = fileProvider;
    }

    public void setProcessFlowViewModel(ProcessFlowViewModel processFlowViewModel) {
        this.processFlowViewModel = processFlowViewModel;
    }
}