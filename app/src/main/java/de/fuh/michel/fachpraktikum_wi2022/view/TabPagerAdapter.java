package de.fuh.michel.fachpraktikum_wi2022.view;

import android.content.Context;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import de.fuh.michel.fachpraktikum_wi2022.R;

public class TabPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[] {R.string.tab_text_1, R.string.tab_text_2};

    private final Context mContext;
    private final List<Fragment> fragments;

    public TabPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
        fragments = new ArrayList<>();
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        return 2;
    }

    public void addFragment(Fragment fragment) {
        fragments.add(fragment);
    }
}