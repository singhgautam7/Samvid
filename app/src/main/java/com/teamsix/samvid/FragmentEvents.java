package com.teamsix.samvid;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
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
        mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        mBottomSheetBehavior.setPeekHeight(0);

        final FloatingActionButton fab=(FloatingActionButton)view.findViewById(R.id.fab);
        final FloatingActionButton smallfab=(FloatingActionButton)view.findViewById(R.id.smallfab);

        view.post(new Runnable() {
            @Override
            public void run() {
                view.requestLayout();
            }
        });         //to proprly anchor main FAB everytime when the control returns to this fragment

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mBottomSheetBehavior.getState() == BottomSheetBehavior.STATE_COLLAPSED) {
                    mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                    changeFABdesign(Color.parseColor("#00C853") , R.drawable.ic_done_white);
                    smallFABvisibility("SHOW");
                }
                else {
                    mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    changeFABdesign(getResources().getColor(R.color.colorAccent) , R.drawable.ic_filter_list_white);
                    smallFABvisibility("HIDE");
                }
            }
        });

        smallfab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                changeFABdesign(getResources().getColor(R.color.colorAccent) , R.drawable.ic_filter_list_white);
                smallFABvisibility("HIDE");
            }
        });

        TabLayout tabLayout = (TabLayout)view.findViewById(R.id.tabs);
        ViewPager viewPager = (ViewPager)view.findViewById(R.id.view_pager);
        viewPager.setAdapter(new CustomFragmentPageAdapter(getChildFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);

        tabLayout = (TabLayout) view.findViewById(R.id.tabs);
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
        shrink.setDuration(125);
        ScaleAnimation expand =  new ScaleAnimation(0, 1f, 0, 1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        expand.setDuration(125);

        switch (position) {
            case 0:
                fab.setVisibility(View.VISIBLE);
                fab.startAnimation(expand);
                break;
            case 1:
                fab.startAnimation(shrink);
                final Handler mHandler=new Handler();
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        fab.setVisibility(View.INVISIBLE);
                        fab.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorAccent)));
                        fab.setImageResource(R.drawable.ic_filter_list_white);
                    }
                },125);
                if (mBottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {        //bottom sheet expanded condition used slightly differnetly
                    mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    smallFABvisibility("HIDE");
                }
                break;
            default:
                fab.setVisibility(View.VISIBLE);
                fab.startAnimation(expand);
                break;
        }
    }

    void changeFABdesign(final int color ,final int icon )
    {
        final FloatingActionButton fab=(FloatingActionButton)getView().findViewById(R.id.fab);
        fab.clearAnimation();
        final ScaleAnimation shrink =  new ScaleAnimation(1f, 0.2f, 1f, 0.2f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        shrink.setDuration(125);
        final ScaleAnimation expand =  new ScaleAnimation(0.2f, 1f, 0.2f, 1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        expand.setDuration(125);
        final Handler mHandler = new Handler();

        fab.startAnimation(shrink);
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                fab.setBackgroundTintList(ColorStateList.valueOf(color));
                fab.setImageResource(icon);
                fab.startAnimation(expand);
            }
        },125);

    }

    void smallFABvisibility(String str)
    {
        final FloatingActionButton smallfab=(FloatingActionButton)getView().findViewById(R.id.smallfab);
        smallfab.clearAnimation();
        final ScaleAnimation shrink =  new ScaleAnimation(1f, 0.2f, 1f, 0.2f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        shrink.setDuration(250);
        final ScaleAnimation expand =  new ScaleAnimation(0.2f, 1f, 0.2f, 1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        expand.setDuration(250);
        final Handler mHandler = new Handler();

        if (str.equals("SHOW")) {
            smallfab.setVisibility(View.INVISIBLE);
            smallfab.startAnimation(expand);
            smallfab.setVisibility(View.VISIBLE);
        }

        else if (str.equals("HIDE")) {
            smallfab.startAnimation(shrink);
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    smallfab.setVisibility(View.GONE);
                }
            },250);
        }
    }
}
