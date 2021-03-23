package com.twitter.reader;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class TweetDTO {
    private String user;
    private String tweet;
    private long favoriteCount;
    private long rtCount;

    public TweetDTO(String user, String tweet, long favoriteCount, long rtCount) {
        this.user = user;
        this.tweet = tweet;
        this.favoriteCount = favoriteCount;
        this.rtCount = rtCount;
    }

    public String getUser() {
        return user;
    }

    public String getTweet() {
        return tweet;
    }

    public long getFavoriteCount() {
        return favoriteCount;
    }

    public long getRtCount() {
        return rtCount;
    }
}
