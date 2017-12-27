package ir.plusrobot.dairyapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<NoteItem> mNoteList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.main_toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_add);

        RecyclerView rvMain = (RecyclerView) findViewById(R.id.rv_main);

        fab.bringToFront();


        mNoteList = new ArrayList<>();

        mNoteList.add(new NoteItem("Title1", "9999/99/99", false, 1));
        mNoteList.add(new NoteItem("Title2", "9999/99/99", true, 1));
        mNoteList.add(new NoteItem("Title3", "9999/99/99", false, 1));
        mNoteList.add(new NoteItem("Title4", "9999/99/99", true, 1));
        mNoteList.add(new NoteItem("Title41", "9999/99/99", false, 1));
        mNoteList.add(new NoteItem("Title5", "9999/99/99", false, 1));
        mNoteList.add(new NoteItem("Title6", "9999/99/99", true, 1));
        mNoteList.add(new NoteItem("Title7", "9999/99/99", false, 1));
        mNoteList.add(new NoteItem("Title8", "9999/99/99", true, 1));
        mNoteList.add(new NoteItem("Title9", "9999/99/99", false, 1));

        MainListAdapter adapter = new MainListAdapter(mNoteList, MainActivity.this);
        LinearLayoutManager layoutManager = new GridLayoutManager(MainActivity.this, 1);
        rvMain.setLayoutManager(layoutManager);
        rvMain.setItemAnimator(new DefaultItemAnimator());
        rvMain.setAdapter(adapter);
        rvMain.addOnItemTouchListener(new MainListAdapter(MainActivity.this, rvMain,
                new MainListAdapter.OnItemClickListener() {
                    @Override
                    public void onClick(View view, int position) {
                        Toast.makeText(MainActivity.this, "کلیک شد", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onLongClick(View view, int position) {
                        Toast.makeText(MainActivity.this, "کلیک طولانی", Toast.LENGTH_SHORT).show();
                    }

                }));


    }
}
