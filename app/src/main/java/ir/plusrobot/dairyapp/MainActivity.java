package ir.plusrobot.dairyapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ir.plusrobot.dairyapp.database.SqlHelper;

public class MainActivity extends AppCompatActivity {

    private List<NoteItem> mNoteList;
    private MainListAdapter mAdapter;
    private SqlHelper mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.main_toolbar);

        ImageView ivMore = (ImageView) toolbar.findViewById(R.id.iv_more);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_add);

        RecyclerView rvMain = (RecyclerView) findViewById(R.id.rv_main);

        mDb = new SqlHelper(MainActivity.this);

        mNoteList = new ArrayList<>();

        mNoteList = mDb.getAllNotes();

        mAdapter = new MainListAdapter(mNoteList, MainActivity.this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(MainActivity.this, 1);
        rvMain.setLayoutManager(layoutManager);
        rvMain.setItemAnimator(new DefaultItemAnimator());
        rvMain.setAdapter(mAdapter);
        rvMain.addOnItemTouchListener(new MainListAdapter(MainActivity.this, rvMain,
                new MainListAdapter.OnItemClickListener() {
                    @Override
                    public void onClick(View view, int position) {
                        int id = mNoteList.get(position).getId();
                        Intent intent = new Intent(MainActivity.this, NoteActivity.class);
                        intent.putExtra("NoteId", id);
                        startActivity(intent);
                    }

                    @Override
                    public void onLongClick(View view, final int position) {

                        AlertDialog.Builder ad = new AlertDialog.Builder(MainActivity.this);
                        ad.setTitle("پاک کنم؟");
                        ad.setMessage("مطمئنین می خواین این خاطره رو پاک کنین؟");
                        ad.setPositiveButton("آره", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                mDb = new SqlHelper(MainActivity.this);
                                mDb.deleteNote(mNoteList.get(position).getId());
                                mNoteList.remove(position);
                                Toast.makeText(MainActivity.this, "خاطره پاک شد", Toast.LENGTH_SHORT).show();
                                mAdapter.notifyDataSetChanged();
                            }
                        });

                        ad.setNegativeButton("بیخیال", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });

                        ad.show();

                    }

                }));


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, NoteActivity.class));
            }
        });


        ivMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        if (mNoteList.size() > 0)
            mAdapter.notifyDataSetChanged();
        else
            Toast.makeText(this, "خاطره ای یافت نشد", Toast.LENGTH_SHORT).show();




    }


    @Override
    protected void onResume() {
        super.onResume();
        if (mAdapter != null) {

            mDb = new SqlHelper(MainActivity.this);
            mNoteList = mDb.getAllNotes();
            mAdapter.notifyDataSetChanged();

        }
    }

}
