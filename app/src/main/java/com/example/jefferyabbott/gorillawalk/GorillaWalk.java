package com.example.jefferyabbott.gorillawalk;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.graphics.drawable.AnimationDrawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Switch;


public class GorillaWalk extends Activity
{
    ImageView gorilla;
    AnimationDrawable ad;
    int speed = 250;
    boolean go = false;

    private static final String savedSpeed = "savedSpeed";
    private static final String savedSwitch = "savedSwitch";


    // This method is used to save the speed value and switch status
    // so when user switches to landscape mode, animation continues
    // with previous setting.
    @Override public void onSaveInstanceState(Bundle savedInstanceState)
    {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt(savedSpeed, speed);

        final Switch stopAndGoSwitch = (Switch)findViewById(R.id.onOff);
        if (stopAndGoSwitch.isChecked())
        {
            savedInstanceState.putInt(savedSwitch, 1);
        }
        else
        {
            savedInstanceState.putInt(savedSwitch, 0);
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null)
        {
            speed = savedInstanceState.getInt(savedSpeed, 0);
            if (savedInstanceState.getInt(savedSwitch, 0) == 1)
            {
                go = true;
            }
        }
        else
        {
            speed = 250;
        }


        if (isTablet(getApplicationContext()))
        {
            setContentView(R.layout.activity_gorilla_walk_tablet);
        }
        else
        {
            setContentView(R.layout.activity_gorilla_walk);
        }


        gorilla = (ImageView)findViewById(R.id.gorillaImageView);
        createAnimation(speed);

        final Switch stopAndGoSwitch = (Switch)findViewById(R.id.onOff);

        stopAndGoSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (stopAndGoSwitch.isChecked()) {
                    ad.start();
                } else {
                    ad.stop();
                }
            }
        });



        final SeekBar speedSelector = (SeekBar)findViewById(R.id.speedSlider);

        speedSelector.setProgress(speed);

        if (go)
        {
            ad.start();
        }

        speedSelector.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progressValue, boolean fromUser)
            {
                // required method, no implementation necessary
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar)
            {
                // required method, no implementation necessary
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar)
            {
                speed = 500 - seekBar.getProgress();
                createAnimation(speed);

                if (stopAndGoSwitch.isChecked())
                {
                    ad.start();
                }
            }
        });


    }


    private void createAnimation(int speed)
    {
        ad = new AnimationDrawable();

        ad.addFrame(getResources().getDrawable(R.drawable.gorilla1), speed);
        ad.addFrame(getResources().getDrawable(R.drawable.gorilla2), speed);
        ad.addFrame(getResources().getDrawable(R.drawable.gorilla3), speed);
        ad.addFrame(getResources().getDrawable(R.drawable.gorilla4), speed);
        ad.addFrame(getResources().getDrawable(R.drawable.gorilla5), speed);
        ad.addFrame(getResources().getDrawable(R.drawable.gorilla6), speed);
        ad.addFrame(getResources().getDrawable(R.drawable.gorilla7), speed);
        ad.addFrame(getResources().getDrawable(R.drawable.gorilla8), speed);
        ad.addFrame(getResources().getDrawable(R.drawable.gorilla9), speed);
        ad.addFrame(getResources().getDrawable(R.drawable.gorilla10), speed);
        ad.addFrame(getResources().getDrawable(R.drawable.gorilla11), speed);
        ad.addFrame(getResources().getDrawable(R.drawable.gorilla12), speed);
        ad.addFrame(getResources().getDrawable(R.drawable.gorilla13), speed);
        ad.addFrame(getResources().getDrawable(R.drawable.gorilla14), speed);
        ad.addFrame(getResources().getDrawable(R.drawable.gorilla15), speed);
        ad.addFrame(getResources().getDrawable(R.drawable.gorilla16), speed);

        ad.setOneShot(false);
        gorilla.setBackground(ad);
    }


    public static boolean isTablet(Context context) {
        return (context.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK)
                >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }
}
