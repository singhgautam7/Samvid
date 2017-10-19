package com.teamsix.samvid;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by admin on 19-Oct-17.
 */

public class CustomFragmentPageAdapter extends FragmentStatePagerAdapter{
    final int FRAGMENT_COUNT = 2;
    public CustomFragmentPageAdapter(FragmentManager fm) {
        super(fm);

    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new FragmentAllEvents();
            case 1:
                return new FragmentStarredEvents();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return FRAGMENT_COUNT;
    }


   /* @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "All Events";
            case 1:
                return "Starred Events";

        }
        return null;
    }*/
}
