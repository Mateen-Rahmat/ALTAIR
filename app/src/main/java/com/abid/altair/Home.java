package com.abid.altair;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;

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

    ArrayAdapter adapter;
    Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pre_home_layout);
        DatabaseHelper mDatabaseHelper = new DatabaseHelper(this, getFilesDir().getAbsolutePath());

        try {

            mDatabaseHelper.prepareDatabase();

        } catch (IOException e) {

            e.printStackTrace();
        }

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


        getFilesDir().getAbsoluteFile();



        add = findViewById(R.id.add);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        ListView mListView = findViewById(R.id.ui_list);


        Cursor data = mDatabaseHelper.getData();
        ArrayList<String> listData = new ArrayList<>();
        while(data.moveToNext()){


            listData.add(data.getString(1)+"\n| "+data.getString(2)+" |");
        }
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
        mListView.setAdapter(adapter);


        searchQuery.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                (Home.this).adapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

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
