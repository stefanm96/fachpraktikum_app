package de.fuh.michel.fachpraktikum_wi2022.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.tabs.TabLayout;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

import de.fuh.michel.fachpraktikum_wi2022.GmafApplication;
import de.fuh.michel.fachpraktikum_wi2022.R;
import de.fuh.michel.fachpraktikum_wi2022.databinding.ActivityMainBinding;
import de.fuh.michel.fachpraktikum_wi2022.domain.MainService;
import de.fuh.michel.fachpraktikum_wi2022.view.definition.DefinitionListFragment;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private ActivityMainBinding binding;
    private MainService mainService;
    private ProcessFlowViewModel processFlowViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mainService = ((GmafApplication) getApplication()).getMainService();
        processFlowViewModel = ((GmafApplication) getApplication()).getProcessFlowViewModel();

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Tabs
        TabPagerAdapter sectionsPagerAdapter = new TabPagerAdapter(this, getSupportFragmentManager());

        sectionsPagerAdapter.addFragment(DefinitionListFragment.newInstance(processFlowViewModel, 1));
        sectionsPagerAdapter.addFragment(new ConfigurationListFragment());

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
        if (id == R.id.action_add_configuration_element) {
            return true;
        }
        if (id == R.id.action_new) {
            String title = getResources().getString(R.string.dialog_new_process_flow_title);
            showAlertDialog(title, (dialog, which) -> {
                mainService.createNewProcessFlow();
                dialog.dismiss();
            });
            return true;
        }
        if (id == R.id.action_import) {
            String title = getResources().getString(R.string.dialog_import_process_flow_title);
            showAlertDialog(title, (dialog, which) -> {
                try {
                    mainService.importProcessFlow(null);
                    dialog.dismiss();
                } catch (IOException | XmlPullParserException e) {
                    e.printStackTrace();
                }
            });
            return true;
        }
        if (id == R.id.action_export) {
            try {
                mainService.exportProcessFlow();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
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