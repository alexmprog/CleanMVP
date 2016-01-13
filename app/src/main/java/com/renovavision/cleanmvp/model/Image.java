package com.renovavision.cleanmvp.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

/**
 * Created by alexmprog on 21.12.2015.
 */
public class Image implements Comparable<Image>, Parcelable {

    private static final int HASH_CODE = 31;

    private String mTitle;
    private int mTweetCount;
    private String mMediaUrl;

    public Image(String title, int tweetCount, String mediaUrl) {
        this.mTitle = title;
        this.mTweetCount = tweetCount;
        this.mMediaUrl = mediaUrl;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getTweetCount() {
        return String.valueOf(mTweetCount);
    }

    @NonNull
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

        Image image = (Image) o;

        if (mTweetCount != image.mTweetCount) {
            return false;
        }

        if (mTitle != null ? !mTitle.equals(image.mTitle) : image.mTitle != null) {
            return false;
        }
        return !(mMediaUrl != null ? !mMediaUrl.equals(image.mMediaUrl) : image.mMediaUrl != null);

    }

    @Override
    public int hashCode() {
        int result = mTitle != null ? mTitle.hashCode() : 0;
        result = HASH_CODE * result + mTweetCount;
        result = HASH_CODE * result + (mMediaUrl != null ? mMediaUrl.hashCode() : 0);
        return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mTitle);
        dest.writeInt(this.mTweetCount);
        dest.writeString(this.mMediaUrl);
    }

    protected Image(Parcel in) {
        this.mTitle = in.readString();
        this.mTweetCount = in.readInt();
        this.mMediaUrl = in.readString();
    }

    public static final Parcelable.Creator<Image> CREATOR = new Parcelable.Creator<Image>() {
        public Image createFromParcel(Parcel source) {
            return new Image(source);
        }

        public Image[] newArray(int size) {
            return new Image[size];
        }
    };

    @Override
    public int compareTo(@NonNull Image another) {
        if (this.equals(another) || this.mTweetCount == another.mTweetCount) return 0;
        return (this.mTweetCount > another.mTweetCount) ? -1 : 1;
    }
}
