package com.jielim36.devgram.common;

public enum OAuthProvider {
    GOOGLE("google"),
    GITHUB("github");

    private final String providerName;

    OAuthProvider(String providerName) {
        this.providerName = providerName;
    }

    public String getProviderName() {
        return providerName;
    }
}
