package com.teamsix.samvid;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by admin on 18-Oct-17.
 */

public class FragmentEvents extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_events, container, false);
        TabLayout tabLayout = (TabLayout)view.findViewById(R.id.tabs);
        ViewPager viewPager = (ViewPager)view.findViewById(R.id.view_pager);
        viewPager.setAdapter(new CustomFragmentPageAdapter(getChildFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        // Create an instance of the tab layout from the view.
        tabLayout = (TabLayout) view.findViewById(R.id.tabs);
        // Set the text for each tab.
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_all_events_white);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_star);
        // Set the tabs to fill the entire layout.
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#ffffff"));

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position=tab.getPosition();
                if(position==0)
                    tab.setIcon(R.drawable.ic_all_events_white);
                else if (position==1)
                    tab.setIcon(R.drawable.ic_star_white);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                int position=tab.getPosition();
                if(position==0)
                    tab.setIcon(R.drawable.ic_all_events);
                else if (position==1)
                    tab.setIcon(R.drawable.ic_star);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                int position=tab.getPosition();
                if(position==0)
                    tab.setIcon(R.drawable.ic_all_events_white);
                else if (position==1)
                    tab.setIcon(R.drawable.ic_star_white);
            }
        });
        return view;
    }

}
