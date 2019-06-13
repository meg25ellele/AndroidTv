package com.example.myapplication;


import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v17.leanback.app.BrowseFragment;
import android.support.v17.leanback.widget.ArrayObjectAdapter;
import android.support.v17.leanback.widget.HeaderItem;
import android.support.v17.leanback.widget.ListRow;
import android.support.v17.leanback.widget.ListRowPresenter;
import android.support.v17.leanback.widget.OnItemViewClickedListener;
import android.support.v17.leanback.widget.OnItemViewSelectedListener;
import android.support.v17.leanback.widget.Presenter;
import android.support.v17.leanback.widget.Row;
import android.support.v17.leanback.widget.RowPresenter;
import android.util.Log;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends BrowseFragment {

    private static final String MainFragmentTAG = "TV App";
    private static BackgroundManager simpleBackgroundManager = null;


    private ArrayObjectAdapter mRowsAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        simpleBackgroundManager = new BackgroundManager(getActivity());
        simpleBackgroundManager.clearBackground();


    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i(MainFragmentTAG, "* MainF - onActivityCreated");

        setupUIElements();

        loadRows();

        setupEventListener();

    }

    private void setupUIElements() {
        setTitle("");
        setHeadersState(HEADERS_ENABLED);
        setHeadersTransitionOnBackEnabled(true);
        setBrandColor(getResources().getColor(R.color.header_background));
        setSearchAffordanceColor(getResources().getColor(R.color.search_color));

    }

    private void loadRows() {

        mRowsAdapter = new ArrayObjectAdapter(new ListRowPresenter());


        HeaderItem cardPresenterHeader = new HeaderItem(0, "Gallery");
        CardPresenter cardPresenter = new CardPresenter();
        ArrayObjectAdapter cardRowAdapter = new ArrayObjectAdapter(cardPresenter);


        Image image1 = new Image();
        image1.setId(1);
        image1.setTitle("sunflowers");
        image1.setCardImageUrl("https://images.unsplash.com/photo-1500320821405-8fc1732209ca?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&w=1000&q=80");
        cardRowAdapter.add(image1);

        Image image2 = new Image();
        image2.setId(2);
        image1.setTitle("cheetah");
        image2.setCardImageUrl("https://dinoanimals.pl/wp-content/uploads/2018/06/Gepardy.jpg");
        cardRowAdapter.add(image2);

        Image image3 = new Image();
        image3.setId(3);
        image3.setTitle("london");
        image3.setCardImageUrl("https://www.fodors.com/wp-content/uploads/2019/01/shutterstock_1255481941.jpg");
        cardRowAdapter.add(image3);

        mRowsAdapter.add(new ListRow(cardPresenterHeader, cardRowAdapter));


        setAdapter(mRowsAdapter);
    }

    private void setupEventListener() {

        setOnItemViewSelectedListener(new ItemViewSelectedListener());
       setOnItemViewClickedListener(new ItemViewClickedListener());
    }


    private final class ItemViewSelectedListener implements OnItemViewSelectedListener {

        @Override
        public void onItemSelected(Presenter.ViewHolder viewHolder, Object item, RowPresenter.ViewHolder viewHolder1, Row row) {

            simpleBackgroundManager.clearBackground();

        }
    }

    private final class ItemViewClickedListener implements OnItemViewClickedListener {

        @Override
        public void onItemClicked(Presenter.ViewHolder viewHolder, Object item, RowPresenter.ViewHolder viewHolder1, Row row) {

            if (item instanceof Image) {
                Image image = (Image) item;
                Log.d(MainFragmentTAG, "Image: " + item.toString());
                Intent intent = new Intent(getActivity(), ImageActivity.class);
                intent.putExtra(ImageActivity.IMAGE, image);

                getActivity().startActivity(intent);
            }
        }

    }
}






