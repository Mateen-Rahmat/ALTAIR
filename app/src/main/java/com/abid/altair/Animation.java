package com.abid.altair;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.transition.ChangeBounds;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.EditText;
import android.widget.ImageView;

public class Animation {

    // App Start Animation
    public void startAnimation (final Context context, final ImageView firstAImage, final ImageView UImage, final ImageView secondAImage, final ImageView FImage, final ConstraintLayout backFrame, EditText searchQuery) {

        searchQuery.animate().setDuration(90).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);

                logoAnimation(context, firstAImage, UImage, secondAImage, FImage, backFrame);
            }
        }).start();

        backFrame.animate().setDuration(1940).setListener(new AnimatorListenerAdapter() {

            @Override
            public void onAnimationEnd (Animator animation) {
                super.onAnimationStart(animation);

                mainFrameAnimation(context, backFrame);
            }
        }).start();

    }

    private void mainFrameAnimation (Context context, ConstraintLayout backFrame) {

        ConstraintSet constraintSet = new ConstraintSet();

        constraintSet.load(context, R.layout.home_layout);

        Transition transition = new ChangeBounds();
        transition.setDuration(270).setInterpolator(new OvershootInterpolator());

        TransitionManager.beginDelayedTransition(backFrame, transition);

        constraintSet.applyTo(backFrame);
    }

    private void logoAnimation (final Context context, ImageView firstAImage, ImageView UImage, ImageView secondAImage, ImageView FImage, final ConstraintLayout backFrame) {

        AnimatorSet firstAAnim = LogoSlideAnimation(firstAImage);
        AnimatorSet UAnim = LogoSlideAnimation(UImage);
        AnimatorSet secondAAnim = LogoSlideAnimation(secondAImage);
        AnimatorSet FAnim = LogoSlideAnimation(FImage);

        AnimatorSet set = new AnimatorSet();
        set.playSequentially(firstAAnim,UAnim,secondAAnim,FAnim);
        set.start();

        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);

                logoResizeAnimation(context, backFrame);
            }
        });
    }

    // Search bar Animation
    public void bringUpSearchBar (int stringId, ViewGroup childLayout, EditText searchQuery, ViewGroup searchBar, float originalSearchPosition) {

        // show the dim background
        childLayout.setVisibility(ViewGroup.VISIBLE);

        searchQuery.setHint(stringId);
        // animate the search bar
        ObjectAnimator searchBarAnimator = ObjectAnimator.ofFloat(searchBar,"translationY",originalSearchPosition,0).setDuration(200);
        searchBarAnimator.setInterpolator(new OvershootInterpolator(1));
        searchBarAnimator.start();
    }

    private AnimatorSet LogoSlideAnimation (final ImageView logo) {

        ObjectAnimator positionAnimation = ObjectAnimator.ofFloat(logo, "translationX", -80 , 0).setDuration(300);
        ObjectAnimator alphaAnimation = ObjectAnimator.ofFloat(logo, "alpha", 0 , 1).setDuration(300);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(positionAnimation, alphaAnimation);
        animatorSet.setInterpolator(new AccelerateDecelerateInterpolator());

        return animatorSet;
    }

    private void logoResizeAnimation (Context context, ConstraintLayout backFrame) {

        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.load(context, R.layout.auaf_resize_layout);

        Transition transition = new ChangeBounds();
        transition.setInterpolator(new AccelerateDecelerateInterpolator()).setDuration(250);

        TransitionManager.beginDelayedTransition(backFrame, transition);

        constraintSet.applyTo(backFrame);
    }
}
