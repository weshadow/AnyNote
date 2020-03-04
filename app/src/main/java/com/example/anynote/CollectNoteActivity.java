package com.example.anynote;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.anynote.dto.NoteQueryParameter;
import com.example.anynote.models.NoteModel;
import com.example.anynote.services.INoteService;
import com.example.anynote.services.serviceImp.NoteServiceImp;

import java.util.ArrayList;
import java.util.List;

public class CollectNoteActivity extends AppCompatActivity {

    private ListView notelist;
    private EditText searchText;
    private Integer pageIndex, pageSize;
    private INoteService service;
    private NoteQueryParameter params;
    private String OrderByField = "";
    private String SortString = "";
    private NoteItemAdapter noteItemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect_note);
        service = new NoteServiceImp();
        init();
    }

    @Override
    protected void onResume() {
        super.onResume();
        noteItemAdapter.clear();
        Query(params);
    }

    private void init() {
        params = new NoteQueryParameter();
        pageIndex = 1;
        pageSize = 20;
        searchText = findViewById(R.id.editText2);
        Button searchBtn = findViewById(R.id.button8);
        params.setPageIndex(pageIndex);
        params.setPageSize(pageSize);

        notelist = findViewById(R.id.noteList);

        noteItemAdapter = new NoteItemAdapter(CollectNoteActivity.this, R.layout.note_collect_item, service.QueryList(params));
        Query(params);
        notelist.setAdapter(noteItemAdapter);
        @SuppressLint("InflateParams") View headerView = getLayoutInflater().inflate(R.layout.note_collect_title, null);

        notelist.addHeaderView(headerView);
        final TextView titleHeader = headerView.findViewById(R.id.textView8);
        final TextView authorHeader = headerView.findViewById(R.id.textView9);
        final TextView typeHeader = headerView.findViewById(R.id.textView10);
        final TextView yearHeader = headerView.findViewById(R.id.textView17);

        titleHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                params.setPageIndex(1);
                if (OrderByField.isEmpty()) {
                    titleHeader.setText("标题⬆");//⬇
                    OrderByField = "title";
                    SortString = "ASC";
                } else if (OrderByField.equals("title")) {
                    if (SortString.equals("ASC")) {
                        SortString = "DESC";
                        titleHeader.setText("标题⬇");
                    } else {
                        SortString = "ASC";
                        titleHeader.setText("标题⬆");
                    }
                } else {
                    titleHeader.setText("标题⬆");//⬇
                    OrderByField = "title";
                    SortString = "ASC";
                }
                authorHeader.setText("作者");
                typeHeader.setText("分类");
                yearHeader.setText("朝代");
                noteItemAdapter.clear();
                Query(params);
            }
        });
        authorHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                params.setPageIndex(1);
                if (OrderByField.isEmpty()) {
                    authorHeader.setText("作者⬆");//⬇
                    OrderByField = "author";
                    SortString = "ASC";
                } else if (OrderByField.equals("author")) {
                    if (SortString.equals("ASC")) {
                        SortString = "DESC";
                        authorHeader.setText("作者⬇");
                    } else {
                        SortString = "ASC";
                        authorHeader.setText("作者⬆");
                    }
                } else {
                    authorHeader.setText("作者⬆");//⬇
                    OrderByField = "author";
                    SortString = "ASC";
                }
                titleHeader.setText("标题");
                typeHeader.setText("分类");
                yearHeader.setText("朝代");
                noteItemAdapter.clear();
                Query(params);
            }
        });
        typeHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                params.setPageIndex(1);
                if (OrderByField.isEmpty()) {
                    typeHeader.setText("类型⬆");//⬇
                    OrderByField = "type";
                    SortString = "ASC";
                } else if (OrderByField.equals("type")) {
                    if (SortString.equals("ASC")) {
                        SortString = "DESC";
                        typeHeader.setText("类型⬇");
                    } else {
                        SortString = "ASC";
                        typeHeader.setText("类型⬆");
                    }
                } else {
                    typeHeader.setText("类型⬆");//⬇
                    OrderByField = "type";
                    SortString = "ASC";
                }
                titleHeader.setText("标题");
                authorHeader.setText("作者");
                yearHeader.setText("朝代");
                noteItemAdapter.clear();
                Query(params);
            }
        });
        yearHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                params.setPageIndex(1);
                if (OrderByField.isEmpty()) {
                    yearHeader.setText("朝代⬆");//⬇
                    OrderByField = "year";
                    SortString = "ASC";
                } else if (OrderByField.equals("year")) {
                    if (SortString.equals("ASC")) {
                        SortString = "DESC";
                        yearHeader.setText("朝代⬇");
                    } else {
                        SortString = "ASC";
                        yearHeader.setText("朝代⬆");
                    }
                } else {
                    yearHeader.setText("朝代⬆");//⬇
                    OrderByField = "year";
                    SortString = "ASC";
                }
                titleHeader.setText("标题");
                authorHeader.setText("作者");
                typeHeader.setText("分类");
                noteItemAdapter.clear();
                Query(params);
            }
        });



        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                params.setPageIndex(1);
                if (searchText.getText() != null) {
                    String search_text = searchText.getText().toString();
                    if (!search_text.isEmpty()) {
                        params.setTitleSearch(search_text);
                        params.setAuthorSearch(search_text);
                        params.setYearSearch(search_text);

                    }
                }
                params.setPageIndex(pageIndex);
                params.setPageSize(pageSize);
                noteItemAdapter.clear();
                Query(params);
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

        notelist.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

                if (notelist.getCount() != 0 && notelist.getLastVisiblePosition() >= (notelist.getCount() - 1)) {
                    long count = service.QueryCount(params);
                    if (count > notelist.getCount() * params.getPageIndex()) {
                        params.setPageIndex(pageIndex + 1);
                        Query(params);
//                        noteItemAdapter.addAll(pageData);
                    }
                }
            }
        });

    }

    private void Query(NoteQueryParameter params) {
        if (!OrderByField.isEmpty())
            params.setOrderBy(OrderByField + " " + SortString);
        noteItemAdapter.addAll(service.QueryList(params));

    }
}
