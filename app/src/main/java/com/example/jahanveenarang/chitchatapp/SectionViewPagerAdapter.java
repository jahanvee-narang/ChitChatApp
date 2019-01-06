package com.example.jahanveenarang.chitchatapp;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

class SectionViewPagerAdapter extends FragmentPagerAdapter {
    public SectionViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0 :
                FriendRequests friendRequests = new FriendRequests();
                return friendRequests;
            case  1:

                Messages messages = new Messages();
                return  messages;
            case 2 :
                BlankFragment blankFragment = new BlankFragment();
                return  blankFragment;

                default:
                    return null;

        }

    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

         super.getPageTitle(position);
        switch (position)
        {
            case 0 : return "REQUESTS";
            case 1 : return "CHATS";
            case 2 : return  "FRIENDS";
            default: return  null ;
        }

    }
}
