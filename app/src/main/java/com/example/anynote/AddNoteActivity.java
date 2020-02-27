package com.example.anynote;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.anynote.models.NoteModel;
import com.example.anynote.services.INoteService;
import com.example.anynote.services.serviceImp.NoteServiceImp;

public class AddNoteActivity extends AppCompatActivity {

    private Button okBtn, cancelBtn;
    private EditText contextText, keyText, typeText, remarkText;
    private long noteId;

    private INoteService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        init();
    }

    /**
     * 初始化
     */
    private void init() {
        service = new NoteServiceImp();


        keyText = findViewById(R.id.editText3);
        typeText = findViewById(R.id.editText4);
        remarkText = findViewById(R.id.editText5);
        contextText = findViewById(R.id.editText);

        noteId = getIntent().getLongExtra("noteId", 0);
        if (noteId > 0) {
            NoteModel model = service.GetById(noteId);
            keyText.setText(model.getKey());
            typeText.setText(model.getType());
            remarkText.setText(model.getRemark());
            contextText.setText(model.getContext());
        }

        okBtn = findViewById(R.id.button6);
        cancelBtn = findViewById(R.id.button7);

        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String errMessage = "";
                if (contextText.getText().toString().isEmpty())
                    errMessage = "请输入正文内容";
                if (!errMessage.isEmpty()) {
                    Toast toast = Toast.makeText(AddNoteActivity.this, errMessage, Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                    return;
                }

                NoteModel model = new NoteModel();
                if (noteId > 0)
                    model.setId(noteId);
                model.setContext(contextText.getText().toString());
                model.setKey(keyText.getText().toString());
                model.setType(typeText.getText().toString());
                model.setRemark(remarkText.getText().toString());
                if (service.Edit(model) == null) {
                    Toast toast = Toast.makeText(AddNoteActivity.this, "操作失败", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                    return;
                } else {
                    Toast toast = Toast.makeText(AddNoteActivity.this, "操作成功", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                    return;
                }
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(AddNoteActivity.this);
                dialog.setTitle("提示");
                dialog.setMessage("是否放弃编辑？");
                dialog.setCancelable(false);
                dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        System.exit(0);
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
