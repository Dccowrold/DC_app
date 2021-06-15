package com.assoftek.splashscreen.Onboarding;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.viewpager.widget.ViewPager;

import com.assoftek.splashscreen.LoginActivity;
import com.assoftek.splashscreen.R;
import com.assoftek.splashscreen.databinding.ActivityOnBoardingScreenBinding;

import java.util.Objects;

public class OnBoardingScreen extends AppCompatActivity {

    private static final String TAG = "mTAG";

    ImageView[] imageViews;
    ViewPagerAdapter adapter;
    ActivityOnBoardingScreenBinding binding;
    boolean isHandlerRunning = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOnBoardingScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Objects.requireNonNull(getSupportActionBar()).hide();



        // Setting ViewPager
        adapter = new ViewPagerAdapter(this);
        binding.viewPager.setAdapter(adapter);


        // handler for auto - sliding viewPager
        final Handler handler = new Handler(getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isHandlerRunning) {
                    if (binding.viewPager.getCurrentItem() == adapter.getCount() - 1)
                        isHandlerRunning = false;
                    else {
                        binding.viewPager.setCurrentItem(binding.viewPager.getCurrentItem() + 1, true);
                        // again calling the handler thread.
                        handler.postDelayed(this, 4000);
                    }
                }
            }
        }, 4000);

        setUpDotIndicator(adapter.getCount());
        setListeners();
    }

    private void setUpDotIndicator(int count) {

        imageViews = new ImageView[count];
        binding.dotIndicator.removeAllViews();
        for (int i = 0; i < count; i++) {
            imageViews[i] = new ImageView(this);
            imageViews[i].setImageResource(R.drawable.inactive_circle_indicator);
            imageViews[i].setPadding(0, 0, 8, 0);
            binding.dotIndicator.addView(imageViews[i]);
        }

        // makes first dot active.
        imageViews[0].setColorFilter(ResourcesCompat.getColor(getResources(), R.color.colorAccent, null));
    }

    private void setListeners() {

        // Hiding Skip button when current page is equal to last page.
        binding.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position == adapter.getCount() - 1) {
                    binding.skipBtn.setVisibility(View.GONE);
                } else
                    binding.skipBtn.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageSelected(int position) {
                // For changing indicator as soon as the  page is scrolled.
                for (int i = 0; i < adapter.getCount(); i++) {
                    imageViews[i].setImageResource(R.drawable.inactive_circle_indicator);
                    imageViews[i].clearColorFilter();
                    imageViews[position].setColorFilter(ResourcesCompat.getColor(getResources()
                            , R.color.colorAccent, null));
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        binding.skipBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isHandlerRunning = false;
                Intent intent = new Intent(OnBoardingScreen.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}