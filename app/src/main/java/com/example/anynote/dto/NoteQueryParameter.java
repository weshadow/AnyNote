package com.example.anynote.dto;


public class NoteQueryParameter extends QueryParameterBase {
    private String titleSearch;
    private String authorSearch;
    private String yearSearch;
    private String contentSearch;
    private String orderBy;

    public String getTitleSearch() {
        return titleSearch;
    }

    public void setTitleSearch(String titleSearch) {
        this.titleSearch = titleSearch;
    }

    public String getAuthorSearch() {
        return authorSearch;
    }

    public void setAuthorSearch(String authorSearch) {
        this.authorSearch = authorSearch;
    }

    public String getYearSearch() {
        return yearSearch;
    }

    public void setYearSearch(String yearSearch) {
        this.yearSearch = yearSearch;
    }

    public String getContentSearch() {
        return contentSearch;
    }

    public void setContentSearch(String contentSearch) {
        this.contentSearch = contentSearch;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }
}
