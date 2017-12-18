package com.teamsix.samvid;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
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

       /* View bottomSheet = view.findViewById(R.id.bottom_sheet);
        mBottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
        mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        mBottomSheetBehavior.setPeekHeight(0);*/

        /*view.post(new Runnable() {
            @Override
            public void run() {
                view.requestLayout();
            }
        }); */        //to proprly anchor main FAB everytime when the control returns to this fragment


        BottomNavigationView bottomNavigationView = (BottomNavigationView) view.findViewById(R.id.bottom_navigation);

        setupNavigationView(view);

        return view;
    }

    /*void animateFab(int position,final FloatingActionButton fab) {
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
    }*/

    private void setupNavigationView(View view) {
        BottomNavigationView bottomNavigationView = (BottomNavigationView) view.findViewById(R.id.bottom_navigation);
        if (bottomNavigationView != null) {

            // Select first menu item by default and show Fragment accordingly.
            Menu menu = bottomNavigationView.getMenu();
            selectFragment(menu.getItem(0));

            // Set action to perform when any menu-item is selected.
            bottomNavigationView.setOnNavigationItemSelectedListener(
                    new BottomNavigationView.OnNavigationItemSelectedListener() {
                        @Override
                        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                            selectFragment(item);
                            return false;
                        }
                    });
        }
    }

    /**
     * Perform action when any item is selected.
     *
     * @param item Item that is selected.
     */
    protected void selectFragment(MenuItem item) {

        item.setChecked(true);

        switch (item.getItemId()) {
            case R.id.action_item1:
                pushFragment(new FragmentAllEvents());
                break;
            case R.id.action_item2:
                pushFragment(new FragmentStarredEvents());
                break;
            case R.id.action_item3:
                pushFragment(new FragmentOrganizeEvent());
                break;
        }
    }

    /**
     * Method to push any fragment into given id.
     *
     * @param fragment An instance of Fragment to show into the given id.
     */
    protected void pushFragment(Fragment fragment) {
        if (fragment == null)
            return;

        FragmentManager fragmentManager = getFragmentManager();
        if (fragmentManager != null) {
            FragmentTransaction ft = fragmentManager.beginTransaction();
            if (ft != null) {
                ft.replace(R.id.rootLayout, fragment);
                ft.commit();
            }
        }
    }
}
