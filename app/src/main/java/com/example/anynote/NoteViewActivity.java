package com.example.anynote;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AlertDialog;

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
    private Button editBtn, delBtn, cancelBtn;
    private TextView keyText, typeText, contextText, remarkText;
    private INoteService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_view);
        init();
    }

    private void init() {
        service = new NoteServiceImp();
        editBtn = findViewById(R.id.button9);
        delBtn = findViewById(R.id.button11);
        cancelBtn = findViewById(R.id.button10);

        keyText = findViewById(R.id.textView6);
        typeText = findViewById(R.id.textView11);
        contextText = findViewById(R.id.textView12);
        remarkText = findViewById(R.id.textView13);

        noteId = getIntent().getLongExtra("noteId", 0);

        NoteModel model = service.GetById(noteId);

        keyText.setText(model.getKey());
        typeText.setText(model.getType());
        contextText.setText("正文：" + model.getContext());
        remarkText.setText("备注：" + model.getRemark());

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
                System.exit(0);
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
