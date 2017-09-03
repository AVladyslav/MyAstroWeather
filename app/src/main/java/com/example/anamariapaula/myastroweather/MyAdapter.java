package com.example.anamariapaula.myastroweather;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Ana Maria Paula on 09.06.2017.
 */

public class MyAdapter extends FragmentPagerAdapter {

    private FragmentManager myFragmentManager = null;
    private FragmentSun fgSun = null;
    private FragmentMoon fgMoon = null;
    private Basic_information fgBasic = null;
    private Additional_information fgAdditional = null;
    private Weather_forecast fgForecast = null;
    private boolean sunNeedUpdate = false;
    private boolean moonNeedUpdate = false;
    private boolean basicNeedUpdate = false;
    private boolean additionalNeedUpdate =  false;
    private boolean forecastNeedUpdate = false;

    private final static int NUMBER_OF_PAGES = 5;

    public MyAdapter(FragmentManager fm) {
        super(fm);
        myFragmentManager = fm;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                fgSun = FragmentSun.newInstance("FragmentSun, Instance 0", sunNeedUpdate);
                if (sunNeedUpdate) {
                    sunNeedUpdate = false;
                }
                return fgSun;
            case 1:
                fgMoon = FragmentMoon.newInstance("FragmentMoon, Instance 1", moonNeedUpdate);
                if (moonNeedUpdate) {
                    moonNeedUpdate = false;
                }
                return fgMoon;
            case 2:
                fgBasic = Basic_information.newInstance("FragmentBasic, Instance 2", basicNeedUpdate);
                if(basicNeedUpdate) {
                    basicNeedUpdate = false;
                }
                return fgBasic;
            case 3:
                fgAdditional = Additional_information.newInstance("FragmentAdditional, Instance 3", additionalNeedUpdate);
                if(additionalNeedUpdate) {
                    additionalNeedUpdate = false;
                }
                return fgAdditional;
            case 4:
                fgForecast = Weather_forecast.newInstance("FragmentForecast, Instance 4", forecastNeedUpdate);
                if(forecastNeedUpdate) {
                    forecastNeedUpdate = false;
                }
                return fgForecast;
            default:
                fgSun = FragmentSun.newInstance("FragmentSun, Default", sunNeedUpdate);
                if (sunNeedUpdate) {
                    sunNeedUpdate = false;
                }
                return fgSun;
        }
    }

    @Override
    public int getCount() {
        return NUMBER_OF_PAGES;
    }

    public void setIsNeedToUpdate(boolean in){
        if (in) {
            sunNeedUpdate = true;
            moonNeedUpdate = true;
            basicNeedUpdate = true;
            additionalNeedUpdate = true;
            forecastNeedUpdate = true;
        }
    }



/*

    LayoutInflater inflater;
    int[] fragments = {R.layout.fragment_sun, R.layout.fragment_moon};

    @Override
    public int getCount() {
        return fragments.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {

        return (view == (RelativeLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE );
        View one = inflater.inflate(R.layout.fragment_sun, container, false);
        View two = inflater.inflate(R.layout.fragment_moon, container, false);
        View views[] = {one, two};
        container.addView(views[position]);
        return views[position];
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }

    */
}
