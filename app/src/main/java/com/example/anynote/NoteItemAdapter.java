package com.example.anynote;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.anynote.common.StringUtil;
import com.example.anynote.models.NoteModel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class NoteItemAdapter extends ArrayAdapter<NoteModel> {
    private int layoutId;


    public NoteItemAdapter(@NonNull Context context, int resource, List<NoteModel> list) {
        super(context, resource, list);
        this.layoutId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        NoteModel item = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(layoutId, parent, false);
        TextView key = view.findViewById(R.id.textView);
        TextView context = view.findViewById(R.id.textView2);
        TextView time = view.findViewById(R.id.textView3);

        key.setText(item.getKey());
        SimpleDateFormat stf = new SimpleDateFormat("yyyy-MM-dd");
        if (item.getTime() != null)
            time.setText(stf.format(new Date(item.getTime())));
        try {
            context.setText(StringUtil.SubReplays(item.getContext(), 8));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return view;

    }

    @Override
    public boolean isEnabled(int position) {
        return true;
    }
}
