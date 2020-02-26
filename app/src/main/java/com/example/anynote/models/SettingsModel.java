package com.example.anynote.models;

import com.orm.SugarRecord;

public class SettingsModel extends SugarRecord {
    private String key;
    private String value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    protected SettingsModel(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public SettingsModel() {

    }
}
