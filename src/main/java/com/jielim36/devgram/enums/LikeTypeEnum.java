package com.jielim36.devgram.enums;

public enum LikeTypeEnum {

    POST("post"),COMMENT("comment");

    private final String type;

    LikeTypeEnum(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
