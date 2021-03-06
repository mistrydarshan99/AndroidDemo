package com.android.test.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.NavUtils;
import android.support.v4.util.Pair;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.android.test.R;
import com.android.test.fragment.DetailFragment;
import com.android.test.view.ObservableScrollView;
import com.android.test.view.VenueItemView;


public class DetailActivity extends AbstractActionBarActivity implements DetailFragment.Callback {

    private Toolbar toolbar;

    private Drawable mActionBarBackgroundDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        toolbar = (Toolbar) findViewById(R.id.material_toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mActionBarBackgroundDrawable = getResources().getDrawable(R.color.md_green_500);
        mActionBarBackgroundDrawable.setAlpha(0);

        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.JELLY_BEAN) {
            mActionBarBackgroundDrawable.setCallback(mDrawableCallback);
        }

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            toolbar.setBackgroundDrawable(mActionBarBackgroundDrawable);
        }else {
            toolbar.setBackground(mActionBarBackgroundDrawable);
        }

    }

    private Drawable.Callback mDrawableCallback = new Drawable.Callback() {
        @Override
        public void invalidateDrawable(Drawable who) {
            toolbar.setBackgroundDrawable(who);
        }

        @Override
        public void scheduleDrawable(Drawable who, Runnable what, long when) {
        }

        @Override
        public void unscheduleDrawable(Drawable who, Runnable what) {
        }
    };

    @Override
    public void onSetupFadingActionBar(ObservableScrollView observableScrollView, final View header) {
        observableScrollView.setObservableScrollViewListener(new ObservableScrollView.ObservableScrollViewListener() {
            @Override
            public void onScrollChanged(int l, int t, int oldl, int oldt) {
                final int headerHeight = (header.getHeight() - toolbar.getHeight());
                final float ratio = (float) Math.min(Math.max(t, 0), headerHeight) / headerHeight;
                final int newAlpha = (int) (ratio * 255);
                mActionBarBackgroundDrawable.setAlpha(newAlpha);
            }
        });
    }



    @Nullable
    @Override
    public Intent getParentActivityIntent() {
        return super.getParentActivityIntent();
    }

    @Override
    protected void setInitialFragment() {

        String url = null;
        String name = null;
        String distance = null;

        if(getIntent().getExtras() != null) {
            Bundle bundle = getIntent().getExtras();
            url = bundle.getString(DetailFragment.ANIMATED_IMAGE);
            name = bundle.getString(DetailFragment.ANIMATED_NAME);
            distance = bundle.getString(DetailFragment.ANIMATED_DISTANCE);
        }

        setInitialFragment(R.layout.activity_main_overlay, R.id.container, DetailFragment.newInstance(url, name, distance));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpTo(this, new Intent(this, ResultListActivity.class));
                animate();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        animate();
    }

    public static void startActivity(AbstractActionBarActivity activity, View transitionView, String url) {
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity,
                Pair.create(transitionView, DetailFragment.ANIMATED_IMAGE),
                Pair.create(transitionView, DetailFragment.ANIMATED_NAME),
                Pair.create(transitionView, DetailFragment.ANIMATED_DISTANCE));

        Intent intent = new Intent(activity, DetailActivity.class);
        intent.putExtra(DetailFragment.ANIMATED_IMAGE, url);
        intent.putExtra(DetailFragment.ANIMATED_NAME, ((VenueItemView)transitionView).getNameText() );
        intent.putExtra(DetailFragment.ANIMATED_DISTANCE, ((VenueItemView)transitionView).getDistanceText() );

        ActivityCompat.startActivity(activity, intent, options.toBundle());
        animate(activity);
    }

    private static void animate(AbstractActionBarActivity activity) {
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            activity.overridePendingTransition(R.anim.bottom_in, R.anim.top_out);
        }

    }

    private void animate() {
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            overridePendingTransition(R.anim.top_in, R.anim.bottom_out);
        }

    }



}
