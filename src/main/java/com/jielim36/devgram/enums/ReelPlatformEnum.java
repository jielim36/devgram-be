package com.jielim36.devgram.enums;

public enum ReelPlatformEnum {

    YOUTUBE("YOUTUBE");

    private final String platform;

    ReelPlatformEnum(String platform) {
        this.platform = platform;
    }

    public String getPlatform() {
        return this.platform;
    }
}
