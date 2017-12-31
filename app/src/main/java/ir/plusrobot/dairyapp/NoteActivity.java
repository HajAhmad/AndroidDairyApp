package ir.plusrobot.dairyapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import ir.plusrobot.dairyapp.database.SqlHelper;

public class NoteActivity extends AppCompatActivity {

    SqlHelper mDb;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.main_toolbar);

        toolbar.findViewById(R.id.iv_more).setVisibility(View.GONE);
        toolbar.findViewById(R.id.tv_title).setVisibility(View.GONE);
        int noteId = 0;

        if (getIntent().getExtras().isEmpty() == false)// Check if it is not null
            noteId = getIntent().getExtras().getInt("NoteId");

        final EditText etTitle = (EditText) toolbar.findViewById(R.id.et_title);
        etTitle.setVisibility(View.VISIBLE);

        ImageView ivSave = (ImageView) toolbar.findViewById(R.id.iv_save);
        ivSave.setVisibility(View.VISIBLE);

        final EditText etDate = (EditText) findViewById(R.id.et_date);
        final EditText etTime = (EditText) findViewById(R.id.et_time);
        final EditText etContent = (EditText) findViewById(R.id.et_content);

        ivSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etTitle.getText().toString().isEmpty() ||
                        etDate.getText().toString().isEmpty() ||
                        etTime.getText().toString().isEmpty() ||
                        etContent.getText().toString().isEmpty()) {

                    Toast.makeText(NoteActivity.this, "لطفا تمام کادر ها را پر نمایید", Toast.LENGTH_SHORT).show();
                } else {
                    mDb = new SqlHelper(NoteActivity.this);
                    long id = mDb.insertNote(new Note(etTitle.getText().toString(),
                            etContent.getText().toString(),
                            etDate.getText().toString()));
                    if (id > 0) {
                        Toast.makeText(NoteActivity.this, "خاطره افزوده شد", Toast.LENGTH_SHORT).show();
                        NoteActivity.this.finish();
                    }
                }
            }
        });

        if (noteId > 0) {
            SqlHelper db = new SqlHelper(NoteActivity.this);
            Note note = new Note();
            note = db.getNote(noteId);
            etTitle.setText(note.getTitle());
            etDate.setText(note.getDate());
            etContent.setText(note.getContent());
        }


    }


}
