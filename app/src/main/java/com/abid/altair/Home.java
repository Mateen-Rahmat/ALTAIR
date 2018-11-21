package com.abid.altair;

import android.app.Activity;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;

public class Home extends Activity {

    // Instance of Animation Class
    Animation animation = new Animation();

    // primitives
    float originalSearchPosition;

    // UI classes declarations
    ViewGroup childLayout, searchBar;
    ConstraintLayout mainFrame, backFrame, parentLayout;
    EditText searchQuery;

    // logo references
    ImageView firstAImage, UImage, secondAImage, FImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pre_home_layout);

        // assigning to UI elements
        childLayout = findViewById(R.id.dim_trans);
        searchBar = findViewById(R.id.ui_search_bar);
        searchQuery = findViewById(R.id.ui_searchQuery);
        mainFrame = findViewById(R.id.ui_mainFrame);
        parentLayout = findViewById(R.id.newhome);

        // logo Image Reference
        firstAImage = findViewById(R.id.ui_firstAImage);
        UImage = findViewById(R.id.ui_UImage);
        secondAImage = findViewById(R.id.ui_secondAImage);
        FImage = findViewById(R.id.ui_FImage);

        // value Used for Search bar animation
        originalSearchPosition = searchBar.getTranslationY();

        // App Start Animation
        backFrame = findViewById(R.id.ui_backframe); // DO NOT CHANGE THIS VALUE, OTHERWISE THE START ANIMATION COULD BREAK
        animation.startAnimation(Home.this, firstAImage, UImage, secondAImage, FImage, backFrame, searchQuery);
    }

    // IT Button Event
    public void itClicked(View view) {

        animation.bringUpSearchBar(R.string.IT, childLayout, searchQuery, searchBar, originalSearchPosition);
    }

    // Math Button Event
    public void mathClicked(View view) {

        animation.bringUpSearchBar(R.string.Math, childLayout, searchQuery, searchBar, originalSearchPosition);

    }

    // Physics Button Event
    public void physicsClicked(View view) {

        animation.bringUpSearchBar(R.string.Physics, childLayout, searchQuery, searchBar, originalSearchPosition);

    }

    // Political Science Button Event
    public void politicalScienceClicked(View view) {

        animation.bringUpSearchBar(R.string.Political_Science, childLayout, searchQuery, searchBar, originalSearchPosition);

    }

    // Law Button Event
    public void lawClicked(View view) {

        animation.bringUpSearchBar(R.string.Law, childLayout, searchQuery, searchBar, originalSearchPosition);

    }

    // Business Button Event
    public void businessClicked(View view) {

        animation.bringUpSearchBar(R.string.Business, childLayout, searchQuery, searchBar, originalSearchPosition);

    }

    //==========================================================================================================================================================================================================

    // dim layout event
    public void sLayoutClicked (View view) {

        // Hide the Keyboard
        view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Home.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }

        // Hide the dim background and make search query empty
        childLayout.setVisibility(ViewGroup.GONE);
        searchQuery.setText("");
    }
}
