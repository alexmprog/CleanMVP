package com.renovavision.cleanmvp.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

/**
 * Created by alexmprog on 21.12.2015.
 */
public class Article implements Comparable<Article>, Parcelable {

    private static final int HASH_CODE = 31;

    private String mTitle;
    private String mUrl;
    private int mTweetCount;
    private String mMediaUrl;

    public Article() {

    }

    public Article(String title, String url, int tweetCount, String mediaUrl) {
        this.mTitle = title;
        this.mUrl = url;
        this.mTweetCount = tweetCount;
        this.mMediaUrl = mediaUrl;
    }

    @NonNull
    public String getTitle() {
        return mTitle;
    }

    public String getUrl() {
        return mUrl;
    }

    public String getTweetCount() {
        return String.valueOf(mTweetCount);
    }


    public String getMediaUrl() {
        return mMediaUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Article article = (Article) o;

        if (mTweetCount != article.mTweetCount) {
            return false;
        }

        if (mTitle != null ? !mTitle.equals(article.mTitle) : article.mTitle != null) {
            return false;
        }

        return !(mUrl != null ? !mUrl.equals(article.mUrl) : article.mUrl != null);

    }

    @Override
    public int hashCode() {
        int result = mTitle != null ? mTitle.hashCode() : 0;
        result = HASH_CODE * result + (mUrl != null ? mUrl.hashCode() : 0);
        result = HASH_CODE * result + mTweetCount;
        return result;
    }

    @Override
    public int compareTo(@NonNull Article another) {
        if (this.equals(another) || this.mTweetCount == another.mTweetCount) {
            return 0;
        }
        return (this.mTweetCount > another.mTweetCount) ? -1 : 1;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mTitle);
        dest.writeString(this.mUrl);
        dest.writeInt(this.mTweetCount);
        dest.writeString(this.mMediaUrl);
    }

    protected Article(Parcel in) {
        this.mTitle = in.readString();
        this.mUrl = in.readString();
        this.mTweetCount = in.readInt();
        this.mMediaUrl = in.readString();
    }

    public static final Parcelable.Creator<Article> CREATOR = new Parcelable.Creator<Article>() {
        public Article createFromParcel(Parcel source) {
            return new Article(source);
        }

        public Article[] newArray(int size) {
            return new Article[size];
        }
    };
}
