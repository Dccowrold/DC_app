package com.assoftek.splashscreen.Onboarding;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;

import com.assoftek.splashscreen.LoginActivity;
import com.assoftek.splashscreen.R;
import com.bumptech.glide.Glide;

public class ViewPagerAdapter extends PagerAdapter {

    Context context;
    public TextView textView;
    String[] text = {"We're so glad you're here!", "Your data is encrypted and SSL secured",
            "A budget is telling your money where to go instead of wondering where it went",
            "Click here to be onboarded!"};

    int[] images = {R.drawable.four, R.drawable.three, R.drawable.two, R.drawable.one};

    public ViewPagerAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        LayoutInflater inflater = (LayoutInflater) container.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.view_pager_layout, container, false);

        textView = view.findViewById(R.id.textView);
        ImageView imageView = view.findViewById(R.id.image);

        textView.setText(text[position]);
        Glide.with(container.getContext()).load(images[position]).into(imageView);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position == text.length - 1) {
                    Intent intent = new Intent(context, LoginActivity.class);
                    context.startActivity(intent);
                    ((AppCompatActivity) (context)).finish();
                }
            }
        });

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return text.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
}
