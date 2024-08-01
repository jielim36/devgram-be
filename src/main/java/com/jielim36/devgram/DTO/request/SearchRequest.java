package com.jielim36.devgram.DTO.request;

public class SearchRequest {
    private String value;
    private Integer page;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }
}
