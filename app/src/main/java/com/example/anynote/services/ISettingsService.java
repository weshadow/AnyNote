package com.example.anynote.services;

import com.example.anynote.models.SettingsModel;

public interface ISettingsService {
    /**
     * 获取设置
     *
     * @param key
     * @return
     */
    SettingsModel GetByKey(String key);

    /**
     * 删除设置
     *
     * @param Id
     * @return
     */
    boolean Del(Long Id);

    /**
     * 删除设置
     *
     * @param key
     * @return
     */
    boolean Del(String key);

    /**
     * 设置系统配置
     *
     * @param model
     * @return
     */
    boolean Setting(SettingsModel model);
}
