package com.teamsix.samvid;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.ScaleAnimation;

/**
 * Created by admin on 18-Oct-17.
 */

public class FragmentEvents extends Fragment {

    BottomSheetBehavior mBottomSheetBehavior;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final  View view=inflater.inflate(R.layout.fragment_events, container, false);

        View bottomSheet = view.findViewById(R.id.bottom_sheet);
        mBottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
        //By default set BottomSheet Behavior as Collapsed and Height 0
        mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        mBottomSheetBehavior.setPeekHeight(0);

        final FloatingActionButton fab=(FloatingActionButton)view.findViewById(R.id.fab);
        fab.setVisibility(View.VISIBLE);

        view.post(new Runnable() {
            @Override
            public void run() {
                view.requestLayout();
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mBottomSheetBehavior.getState() == BottomSheetBehavior.STATE_COLLAPSED)
                    //If state is in collapse mode expand it
                    mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                else
                    //else if state is expanded collapse it
                    mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            }
        });

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

                animateFab(tab.getPosition(),fab);
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

    void animateFab(int position,final FloatingActionButton fab) {
        fab.clearAnimation();
        ScaleAnimation shrink =  new ScaleAnimation(1f, 0, 1f, 0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        shrink.setDuration(150);     // animation duration in milliseconds
        ScaleAnimation expand =  new ScaleAnimation(0, 1f, 0, 1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        expand.setDuration(150);     // animation duration in milliseconds

        switch (position) {
            case 0:
                fab.setVisibility(View.VISIBLE);
                fab.startAnimation(expand);
                break;
            case 1:
                fab.startAnimation(shrink);
                Handler mHandler=new Handler();
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        fab.setVisibility(View.INVISIBLE);
                    }
                },150);
                break;
                default:
                    fab.setVisibility(View.VISIBLE);
                    fab.startAnimation(expand);
                    break;
        }
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
