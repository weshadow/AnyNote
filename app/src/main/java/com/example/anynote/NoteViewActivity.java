package com.example.anynote;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AlertDialog;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.anynote.models.NoteModel;
import com.example.anynote.services.INoteService;
import com.example.anynote.services.serviceImp.NoteServiceImp;

public class NoteViewActivity extends AppCompatActivity {

    private long noteId;
    private TextView titleText, authorText, yearText, typeText, contentText, remarkText;
    private INoteService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_view);
        init();
    }

    @Override
    protected void onResume() {
        super.onResume();
        load();
    }

    @SuppressLint("SetTextI18n")
    private void load() {
        NoteModel model = service.GetById(noteId);
        titleText.setText(model.getTitle());
        authorText.setText("作者：" + model.getAuthor());
        yearText.setText("朝代：" + model.getYear());
        typeText.setText("类型：" + model.getType());
        contentText.setText(model.getContent());
        remarkText.setText("备注：" + model.getRemark());
    }

    private void init() {
        service = new NoteServiceImp();
        Button editBtn = findViewById(R.id.button9);
        Button delBtn = findViewById(R.id.button11);
        Button cancelBtn = findViewById(R.id.button10);

        titleText = findViewById(R.id.textView4);
        authorText = findViewById(R.id.textView6);
        yearText = findViewById(R.id.textView7);
        typeText = findViewById(R.id.textView11);
        contentText = findViewById(R.id.textView12);
        remarkText = findViewById(R.id.textView13);

        noteId = getIntent().getLongExtra("noteId", 0);
        load();
//        NoteModel model = service.GetById(noteId);
//
//        titleText.setText(model.getTitle());
//        authorText.setText("作者：" + model.getAuthor());
//        yearText.setText("朝代：" + model.getYear());
//        typeText.setText("类型：" + model.getType());
//        contentText.setText(model.getContent());
//        remarkText.setText("备注：" + model.getRemark());

        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent editview = new Intent(NoteViewActivity.this, AddNoteActivity.class);
                editview.putExtra("noteId", noteId);
                startActivity(editview);
//                finish();
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        delBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(NoteViewActivity.this);
                dialog.setTitle("提示");
                dialog.setMessage("是否确认删除？");
                dialog.setCancelable(false);
                dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (service.Del(noteId)) {
                            Toast toast = Toast.makeText(NoteViewActivity.this, "删除成功！", Toast.LENGTH_LONG);
                            toast.setGravity(Gravity.CENTER, 0, 0);
                            toast.show();
                            finish();
                        } else {
                            Toast toast = Toast.makeText(NoteViewActivity.this, "删除失败！", Toast.LENGTH_LONG);
                            toast.setGravity(Gravity.CENTER, 0, 0);
                            toast.show();
                        }
                    }
                });
                dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                dialog.show();
            }
        });
    }
}
