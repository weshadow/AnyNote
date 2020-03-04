package com.example.anynote;

import android.annotation.SuppressLint;
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

import java.util.List;


public class NoteItemAdapter extends ArrayAdapter<NoteModel> {
    private int layoutId;


    NoteItemAdapter(@NonNull Context context, int resource, List<NoteModel> list) {
        super(context, resource, list);
        this.layoutId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        NoteModel item = getItem(position);
        @SuppressLint("ViewHolder") View view = LayoutInflater.from(getContext()).inflate(layoutId, parent, false);
        TextView title = view.findViewById(R.id.textView);
        TextView author = view.findViewById(R.id.textView2);
        TextView type = view.findViewById(R.id.textView3);
        TextView year = view.findViewById(R.id.textView16);

        try {
            assert item != null;
            title.setText(StringUtil.SubReplays(item.getTitle(), 6));
        } catch (Exception e) {
            e.printStackTrace();
        }
        author.setText(item.getAuthor());
        type.setText(item.getType());
        year.setText(item.getYear());
        return view;
    }
}
