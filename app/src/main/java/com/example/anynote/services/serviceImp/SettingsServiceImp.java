package com.example.anynote.services.serviceImp;

import com.example.anynote.models.SettingsModel;
import com.example.anynote.services.ISettingsService;

import java.util.List;

public class SettingsServiceImp implements ISettingsService {
    @Override
    public SettingsModel GetByKey(String key) {
        List<SettingsModel> result = SettingsModel.find(SettingsModel.class, "key = ?", new String[]{key}, null, null, "1");
        if (result.size() == 0)
            return null;
        return result.get(0);
    }

    @Override
    public boolean Del(Long Id) {
        int i = SettingsModel.deleteAll(SettingsModel.class, "id = ?", new String[]{Id.toString()});
        return i > 0;
    }

    @Override
    public boolean Del(String key) {
        int i = SettingsModel.deleteAll(SettingsModel.class, "key = ?", new String[]{key});
        return i > 0;
    }

    @Override
    public boolean Setting(SettingsModel model) {
        if (model.getKey().isEmpty())
            return false;
        if (model.getValue().isEmpty())
            return false;
        SettingsModel data_model = GetByKey(model.getKey());
        if (data_model == null)
            return SettingsModel.save(model) > 0;
        else
            return SettingsModel.update(model) > 0;
    }
}
