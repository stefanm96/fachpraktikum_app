package de.fuh.michel.fachpraktikum_wi2022;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.tabs.TabLayout;

import de.fuh.michel.fachpraktikum_wi2022.databinding.ActivityMainBinding;
import de.fuh.michel.fachpraktikum_wi2022.model.ProcessFlow;
import de.fuh.michel.fachpraktikum_wi2022.model.configurationelement.Export;
import de.fuh.michel.fachpraktikum_wi2022.model.configurationelement.FlowSource;
import de.fuh.michel.fachpraktikum_wi2022.model.configurationelement.Fusion;
import de.fuh.michel.fachpraktikum_wi2022.model.configurationelement.Mmfg;
import de.fuh.michel.fachpraktikum_wi2022.model.configurationelement.Parameter;
import de.fuh.michel.fachpraktikum_wi2022.view.configurationelement.list.ConfigurationListFragment;
import de.fuh.michel.fachpraktikum_wi2022.view.ProcessFlowViewModel;
import de.fuh.michel.fachpraktikum_wi2022.view.TabPagerAdapter;
import de.fuh.michel.fachpraktikum_wi2022.view.definition.DefinitionListFragment;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private ActivityMainBinding binding;
    private ProcessFlowViewModel processFlowViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        processFlowViewModel = ((GmafApplication) getApplication()).getProcessFlowViewModel();

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Tabs
        TabPagerAdapter sectionsPagerAdapter = new TabPagerAdapter(this, getSupportFragmentManager());

        sectionsPagerAdapter.addFragment(DefinitionListFragment.newInstance(processFlowViewModel));
        sectionsPagerAdapter.addFragment(ConfigurationListFragment.newInstance(processFlowViewModel));

        ViewPager viewPager = binding.viewPager;
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = binding.tabs;
        tabs.setupWithViewPager(viewPager);

        setSupportActionBar(binding.toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add_definition) {
            Intent intent = new Intent(this, BarcodeScannerActivity.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.action_add_parameter) {
            startCreateActivity(Parameter.CONFIGURATION_ELEMENT_TYPE);
            return true;
        }
        if (id == R.id.action_add_flow_source) {
            startCreateActivity(FlowSource.CONFIGURATION_ELEMENT_TYPE);
            return true;
        }
        if (id == R.id.action_add_fusion) {
            startCreateActivity(Fusion.CONFIGURATION_ELEMENT_TYPE);
            return true;
        }
        if (id == R.id.action_add_mmfg) {
            startCreateActivity(Mmfg.CONFIGURATION_ELEMENT_TYPE);
            return true;
        }
        if (id == R.id.action_add_export) {
            startCreateActivity(Export.CONFIGURATION_ELEMENT_TYPE);
            return true;
        }
        if (id == R.id.action_edit) {
            Intent intent = new Intent(this, CreateEditProcessFlowActivity.class);
            intent.putExtra(CreateEditProcessFlowActivity.PROCESS_FLOW, true);
            startActivity(intent);
            return true;
        }
        if (id == R.id.action_import) {
            String title = getResources().getString(R.string.dialog_import_process_flow_title);
            showAlertDialog(title, (dialog, which) -> {
                startSelectFileActivity();
                dialog.dismiss();
            });
            return true;
        }
        if (id == R.id.action_export) {
            try {
                processFlowViewModel.exportProcessFlow();
                Toast.makeText(this, "Export successful!", Toast.LENGTH_LONG).show();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void startSelectFileActivity() {
        Intent intent = new Intent(this, SelectFileActivity.class);
        startActivity(intent);
    }

    private void startCreateActivity(String configurationElementType) {
        Intent intent = new Intent(this, CreateEditConfigurationElementActivity.class);
        intent.putExtra(
                CreateEditConfigurationElementActivity.CONFIGURATION_ELEMENT_TYPE,
                configurationElementType);
        startActivity(intent);
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

    public ActivityMainBinding getBinding() {
        return binding;
    }

    public ProcessFlowViewModel getProcessFlowViewModel() {
        return processFlowViewModel;
    }
}