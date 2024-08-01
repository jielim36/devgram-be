package com.jielim36.devgram.DTO;

public class SearchResult<T> {

    private T[] data;
    private Integer total;
    private Integer page;
    private Integer limit;

    public SearchResult(T[] data, Integer total, Integer page, Integer limit) {
        this.data = data;
        this.total = total;
        this.page = page;
        this.limit = limit;
    }

    public SearchResult() {
    }

    public T[] getData() {
        return data;
    }

    public void setData(T[] data) {
        this.data = data;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }
}
