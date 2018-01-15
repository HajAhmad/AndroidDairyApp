package ir.plusrobot.dairyapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
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
        mAdapter.setOnItemClickListener(new MainListAdapter.OnItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                int id = mNoteList.get(position).getId();
                Intent intent = new Intent(MainActivity.this, NoteActivity.class);
                intent.putExtra("NoteId", id);
                startActivity(intent);
            }

            @Override
            public void onLongClick(final View view, final int position) {
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
                        mAdapter.notifyItemRemoved(position);
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
        });

        //TODO: Solve Unlike Click Problem
        mAdapter.setOnLikeClickListener(new MainListAdapter.OnLikeClickListener() {
            @Override
            public boolean onLikeClick(int position) {
                SqlHelper db = new SqlHelper(MainActivity.this);
                boolean isLiked = db.setLike(mNoteList.get(position).getId());
                if (isLiked == true) {
                    mNoteList.get(position).setFav(true);
                    return true;
                } else {
                    mNoteList.get(position).setFav(false);
                    return false;
                }
            }
        });


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, NoteActivity.class));
            }
        });


        ivMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu menu = new PopupMenu(MainActivity.this, v);
                menu.inflate(R.menu.more_menu);
                menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.item_about_us:
                                AboutDialog dialog = new AboutDialog(MainActivity.this);
                                dialog.show();
                                break;


                            case R.id.item_backup:

                                break;

                            case R.id.item_restore:

                                break;

                        }

                        return false;
                    }
                });

                menu.show();

            }
        });


    }




}
