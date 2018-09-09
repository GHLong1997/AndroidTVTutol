package com.example.admin.androidtvn;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v17.leanback.widget.BaseCardView;
import android.support.v17.leanback.widget.ImageCardView;
import android.support.v17.leanback.widget.Presenter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;
import java.net.URI;

public class CardPresenter extends Presenter {
 
    private static final String TAG = CardPresenter.class.getSimpleName();
 
    private static Context mContext;
    private static int CARD_WIDTH = 313;
    private static int CARD_HEIGHT = 176;
 
    static class ViewHolder extends Presenter.ViewHolder {
        private Movie mMovie;
        private ImageCardView mCardView;
        private String cardImageUrl;
 
        public ViewHolder(View view) {
            super(view);
            mCardView = (ImageCardView) view;
        }
 
        public void setMovie(Movie m) {
            mMovie = m;
        }
 
        public Movie getMovie() {
            return mMovie;
        }

        public String getCardImageUrl() {
            return cardImageUrl;
        }

        public void setCardImageUrl(String cardImageUrl) {
            this.cardImageUrl = cardImageUrl;
        }
 
    }
 
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent) {
        Log.d(TAG, "onCreateViewHolder");
        mContext = parent.getContext();
 
        ImageCardView cardView = new ImageCardView(mContext);
        cardView.setFocusable(true);
        cardView.setFocusableInTouchMode(true);
        cardView.setBackgroundColor(mContext.getResources().getColor(R.color.fastlane_background));
        cardView.setInfoVisibility(BaseCardView.CARD_REGION_VISIBLE_ALWAYS); //DEFAULT: CARD_REGION_VISIBLE_ACTIVATED
        cardView.setCardType(BaseCardView.CARD_TYPE_INFO_UNDER);
        return new ViewHolder(cardView);
    }
 
    @Override
    public void onBindViewHolder(Presenter.ViewHolder viewHolder, Object item) {
        Movie movie = (Movie) item;
        ImageCardView cardView = (ImageCardView) viewHolder.view;

        Log.d(TAG, "onBindViewHolder");

            cardView.setTitleText(movie.getTitle());
            cardView.setContentText(movie.getStudio());
            cardView.setMainImageDimensions(CARD_WIDTH, CARD_HEIGHT);
            Glide.with(viewHolder.view.getContext())
                    .load(movie.getCardImageUrl())
                    .centerCrop()
                    .into(cardView.getMainImageView());

    }
 
    @Override
    public void onUnbindViewHolder(Presenter.ViewHolder viewHolder) {
        Log.d(TAG, "onUnbindViewHolder");
    }
 
    @Override
    public void onViewAttachedToWindow(Presenter.ViewHolder viewHolder) {
        // TO DO
    }
 
}