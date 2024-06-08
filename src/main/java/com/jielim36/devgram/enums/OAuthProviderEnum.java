package com.jielim36.devgram.enums;

public enum OAuthProviderEnum {
    GOOGLE("google"),
    GITHUB("github");

    private final String providerName;

    OAuthProviderEnum(String providerName) {
        this.providerName = providerName;
    }

    public String getProviderName() {
        return providerName;
    }
}
