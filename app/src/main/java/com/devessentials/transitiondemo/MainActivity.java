package com.devessentials.transitiondemo;

import android.annotation.SuppressLint;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.ChangeBounds;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private ConstraintLayout mLayout;
    private Button mButton;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLayout = findViewById(R.id.myLayout);
        mButton = findViewById(R.id.myButton);

        mButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                handleTouch();
                return true;
            }
        });
    }

    private void handleTouch() {
        Transition changeBounds = new ChangeBounds()
                .setDuration(3000)
                .setInterpolator(new BounceInterpolator());

        TransitionManager.beginDelayedTransition(mLayout, changeBounds);

        mButton.setMinWidth(500);
        mButton.setMinHeight(350);

        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.connect(R.id.myButton, ConstraintSet.BOTTOM,
                ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, 0);
        constraintSet.connect(R.id.myButton, ConstraintSet.END,
                ConstraintSet.PARENT_ID, ConstraintSet.END, 0);
        constraintSet.constrainWidth(R.id.myButton, ConstraintSet.WRAP_CONTENT);

        constraintSet.applyTo(mLayout);
    }
}
