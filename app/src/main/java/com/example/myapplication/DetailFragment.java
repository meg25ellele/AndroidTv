package com.example.myapplication;



import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v17.leanback.app.DetailsFragment;
import android.util.Log;



/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends DetailsFragment {

    private static final String IMAGE = "Image";
    private static final String TAG = DetailFragment.class.getSimpleName();

    private Image mSelectedImage;


    private static BackgroundManager simpleBackgroundManager = null;



    @Override
    public void onCreate(Bundle savedInstanceState) {

        Log.i(TAG, "onCreate");
        super.onCreate(savedInstanceState);


        simpleBackgroundManager = new BackgroundManager(getActivity());

        mSelectedImage = (Image)getActivity().getIntent().getSerializableExtra(IMAGE);
        simpleBackgroundManager.updateBackground(chooseImage(mSelectedImage.getId()));

    }


    @Override
    public void onDestroy() {
        super.onDestroy();

        Intent intent = new Intent(getActivity(), MainActivity.class);

        getActivity().startActivity(intent);
    }

    private Drawable chooseImage (int i){



        if(i==1){
            return getActivity().getDrawable(R.drawable.sunflower);
        }
        if(i==2){
            return getActivity().getDrawable(R.drawable.cheetah);
        }
        if (i==3){
            return getActivity().getDrawable(R.drawable.london);
        }
        else return getActivity().getDrawable(R.drawable.default_background);

     }

}
