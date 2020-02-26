package com.example.anynote.dto;

import java.time.LocalDateTime;

public class NoteQueryParameter extends QueryParameterBase {
    private String contextSearch;
    private String typeSearch;
    private String keySearch;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String orderBy;

    public String getContextSearch() {
        return contextSearch;
    }

    public void setContextSearch(String contextSearch) {
        this.contextSearch = contextSearch;
    }

    public String getTypeSearch() {
        return typeSearch;
    }

    public void setTypeSearch(String typeSearch) {
        this.typeSearch = typeSearch;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getKeySearch() {
        return keySearch;
    }

    public void setKeySearch(String keySearch) {
        this.keySearch = keySearch;
    }
}
