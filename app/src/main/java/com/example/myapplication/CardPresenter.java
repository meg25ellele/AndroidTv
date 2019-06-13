package com.example.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v17.leanback.widget.BaseCardView;
import android.support.v17.leanback.widget.ImageCardView;
import android.support.v17.leanback.widget.Presenter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;


import java.net.URI;

public class CardPresenter extends Presenter {

    private static final String TAG = CardPresenter.class.getSimpleName();

    private static Context mContext;
    private static int C_WIDTH = 313;
    private static int C_HEIGHT = 176;

    static class ViewHolder extends Presenter.ViewHolder {

        private Image mImage;
        private ImageCardView mCardView;
        private Drawable mDeafaultCardImage;
        private PicassoImageCardViewTarget mImageCardViewTarget;

        public ViewHolder(View view) {
            super(view);
            mCardView = (ImageCardView) view;
            mImageCardViewTarget = new PicassoImageCardViewTarget(mCardView);
            mDeafaultCardImage = mContext.getResources().getDrawable(R.drawable.default_image);
        }

        public void setImage(Image mImage) {
            this.mImage = mImage;
        }

        public Image getImage() {
            return mImage;
        }

        public ImageCardView getCardView() {
            return mCardView;
        }

        public Drawable getDeafaultCardImage() {
            return mDeafaultCardImage;
        }

        protected void updateCardViewImage(URI uri){
            Picasso.get().load(uri.toString())
                    .resize(Utils.convertDpToPixel(mContext,C_WIDTH),
                            Utils.convertDpToPixel(mContext,C_HEIGHT))
                    .error(mDeafaultCardImage)
                    .into(mImageCardViewTarget);
        }
    }
    public static class PicassoImageCardViewTarget implements Target {
        private ImageCardView mImageCardView;

        public PicassoImageCardViewTarget(ImageCardView imageCardView){
            mImageCardView = imageCardView;
        }


        @Override
        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
            Drawable bitmapDrawable = new BitmapDrawable((mContext.getResources()),bitmap);
            mImageCardView.setMainImage(bitmapDrawable);
        }

        @Override
        public void onBitmapFailed(Exception e, Drawable errorDrawable) {
            mImageCardView.setMainImage(errorDrawable);

        }

        @Override
        public void onPrepareLoad(Drawable placeHolderDrawable) {
            //

        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup) {
        Log.d(TAG, "onCreateViewHolder");
        mContext = viewGroup.getContext();

        ImageCardView cardView = new ImageCardView(mContext);
       cardView.setCardType(BaseCardView.CARD_TYPE_MAIN_ONLY);
        //cardView.setExtraVisibility(BaseCardView.CARD_REGION_VISIBLE_SELECTED);
        cardView.setFocusable(true);
        cardView.setFocusableInTouchMode(true);
        cardView.setBackgroundColor(mContext.getResources().getColor(R.color.default_background));
        return new ViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(Presenter.ViewHolder viewHolder, Object o) {
        Image image = (Image) o;
        ((ViewHolder) viewHolder).setImage(image);

        Log.d(TAG, "onBindHolder");
        ((ViewHolder) viewHolder).mCardView.setTitleText(image.getTitle());
        ((ViewHolder) viewHolder).mCardView.setMainImageDimensions(C_WIDTH,C_HEIGHT);
        ((ViewHolder)viewHolder).updateCardViewImage(image.getCardImageURI());
        //((ViewHolder) viewHolder).mCardView.setMainImage(((ViewHolder) viewHolder).getDeafaultCardImage());
    }

    @Override
    public void onUnbindViewHolder(Presenter.ViewHolder viewHolder) {

        Log.d(TAG, "onUnbindViewHolder");
    }

    @Override
    public void onViewAttachedToWindow(Presenter.ViewHolder holder) {
        //
    }
}
