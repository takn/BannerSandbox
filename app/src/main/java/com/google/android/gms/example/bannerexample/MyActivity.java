package com.google.android.gms.example.bannerexample;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;

import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.doubleclick.PublisherAdRequest;
import com.google.android.gms.ads.doubleclick.PublisherAdView;

/**
 * Main Activity. Inflates main activity xml and child fragments.
 */
public class MyActivity extends ActionBarActivity {

    private static final String TAG = "Activity";
    private PublisherAdView mAdView;
    private ViewGroup mRoot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        mRoot = (ViewGroup) findViewById(R.id.container);

        // Gets the ad view defined in layout/ad_fragment.xml with ad unit ID set in
        // values/strings.xml.
//        mAdView = (PublisherAdView) findViewById(R.id.ad_view);
        mAdView = new PublisherAdView(this);
        mRoot.addView(mAdView);

        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        float density = getResources().getDisplayMetrics().density;

        //with density scaling
        //1080x1776
        int adWidth = (int) Math.ceil(AndroidUtils.getScreenPixels(this).x / density);
        int adHeight = (int) Math.ceil(AndroidUtils.getScreenPixels(this).y / density);

//        int adWidth = AndroidUtils.pixelsToDP(410,this);
//        int adHeight = AndroidUtils.pixelsToDP(230,this);

        Log.d(TAG, String.format("Density: %s Ad width : %d ad Height :%d", density, adWidth, adHeight));
        mAdView.setAdUnitId(getString(R.string.ad_unit_id));
        mAdView.setAdSizes(new AdSize(adWidth, adHeight));
        PublisherAdRequest adRequest = new PublisherAdRequest.Builder()
                .addTestDevice("73D08D919474078BB052E3CFD6827EF3").addTestDevice("CAA6BC4D3F140B3676A7A976472505E8").build();

//        mAdView.setAdUnitId(getString(R.string.ad_unit_id));
        // Start loading the ad in the background.
        mAdView.loadAd(adRequest);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Called when leaving the activity
     */
    @Override
    public void onPause() {
        if (mAdView != null) {
            mAdView.pause();
        }
        super.onPause();
    }

    /**
     * Called when returning to the activity
     */
    @Override
    public void onResume() {
        super.onResume();
        if (mAdView != null) {
            mAdView.resume();
        }
    }

    /**
     * Called before the activity is destroyed
     */
    @Override
    public void onDestroy() {
        if (mAdView != null) {
            mAdView.destroy();
        }
        super.onDestroy();
    }
}
