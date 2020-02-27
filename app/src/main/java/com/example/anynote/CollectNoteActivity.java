package com.example.anynote;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.NumberPicker;

import com.example.anynote.dto.NoteQueryParameter;
import com.example.anynote.models.NoteModel;
import com.example.anynote.services.INoteService;
import com.example.anynote.services.serviceImp.NoteServiceImp;

import java.util.List;

public class CollectNoteActivity extends AppCompatActivity {

    private ListView notelist;
    private List<NoteModel> listData;
    private EditText searchText;
    private Button searchBtn;
    private Integer pageIndex, pageSize;
    private INoteService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect_note);
        service = new NoteServiceImp();
        init();
    }

    private void init() {
        pageIndex = 1;
        pageSize = 20;
        searchText = findViewById(R.id.editText2);
        searchBtn = findViewById(R.id.button8);
        final NoteQueryParameter param = new NoteQueryParameter();
        param.setPageIndex(pageIndex);
        param.setPageSize(pageSize);
        listData = service.QueryList(param);
        notelist = findViewById(R.id.noteList);
        final NoteItemAdapter noteItemAdapter = new NoteItemAdapter(CollectNoteActivity.this, R.layout.note_collect_item, listData);
        View headerView = getLayoutInflater().inflate(R.layout.note_collect_title, null);
        headerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        notelist.addHeaderView(headerView);
        notelist.setAdapter(noteItemAdapter);

        /**
         * 搜索
         */
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NoteQueryParameter params = new NoteQueryParameter();
                if (searchText.getText() != null) {
                    String search_text = searchText.getText().toString();
                    if (!search_text.isEmpty()) {
                        params.setContextSearch(search_text);
                        params.setContextSearch(search_text);
                        params.setTypeSearch(search_text);

                    }
                }
                params.setPageIndex(pageIndex);
                params.setPageSize(pageSize);
                listData = service.QueryList(params);
                noteItemAdapter.clear();
                noteItemAdapter.addAll(listData);
            }
        });

        notelist.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

                if (notelist.getCount() != 0 && notelist.getLastVisiblePosition() >= (notelist.getCount() - 1)) {
                    long count = service.QueryCount(param);
                    if (count > notelist.getCount() * param.getPageIndex()) {
                        param.setPageIndex(pageIndex + 1);
                        List<NoteModel> pageData = service.QueryList(param);
                        noteItemAdapter.addAll(pageData);
                    }
                }
            }
        });

        notelist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                NoteModel model = (NoteModel) parent.getItemAtPosition(position);
                Intent noteDetail = new Intent(CollectNoteActivity.this, NoteViewActivity.class);
                noteDetail.putExtra("noteId", model.getId());
                startActivity(noteDetail);
//                finish();
            }
        });

    }
}
