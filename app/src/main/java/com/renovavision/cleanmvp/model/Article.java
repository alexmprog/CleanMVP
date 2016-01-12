package com.renovavision.cleanmvp.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

/**
 * Created by alexmprog on 21.12.2015.
 */
public class Article implements Comparable<Article>, Parcelable {

    private static final int HASH_CODE_PRIME = 31;

    private final String title;
    private final String url;
    private final int tweetCount;
    private final String mediaUrl;

    public Article(String title, String url, int tweetCount, String mediaUrl) {
        this.title = title;
        this.url = url;
        this.tweetCount = tweetCount;
        this.mediaUrl = mediaUrl;
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public String getTweetCount() {
        return String.valueOf(tweetCount);
    }


    public String getMediaUrl() {
        return mediaUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Article article = (Article) o;

        if (tweetCount != article.tweetCount) return false;
        if (title != null ? !title.equals(article.title) : article.title != null) return false;
        return !(url != null ? !url.equals(article.url) : article
                .url != null);

    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = HASH_CODE_PRIME * result + (url != null ? url.hashCode() : 0);
        result = HASH_CODE_PRIME * result + tweetCount;
        return result;
    }

    @Override
    public int compareTo(@NonNull Article another) {
        if (this.equals(another) || this.tweetCount == another.tweetCount) return 0;
        return (this.tweetCount > another.tweetCount) ? -1 : 1;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.url);
        dest.writeInt(this.tweetCount);
        dest.writeString(this.mediaUrl);
    }

    protected Article(Parcel in) {
        this.title = in.readString();
        this.url = in.readString();
        this.tweetCount = in.readInt();
        this.mediaUrl = in.readString();
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
