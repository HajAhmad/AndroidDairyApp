package ir.plusrobot.dairyapp;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MainListAdapter extends RecyclerView.Adapter<MainListAdapter.MainViewHolder>
        implements RecyclerView.OnItemTouchListener {

    private List<NoteItem> mNoteList;
    private Context mContext;

    private GestureDetector mGestureDetector;
    private OnItemClickListener mOnItemClickListener;
    private RecyclerView mRecyclerView;

    public MainListAdapter(List<NoteItem> mNoteList, Context mContext) {
        this.mNoteList = mNoteList;
        this.mContext = mContext;
    }

    public MainListAdapter(Context context, RecyclerView recyclerView,
                           OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
        this.mRecyclerView = recyclerView;
        mGestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                View childView = mRecyclerView.findChildViewUnder(e.getX(), e.getY());
                if (childView != null && mOnItemClickListener != null) {
                    mOnItemClickListener.onClick(childView,
                            mRecyclerView.getChildAdapterPosition(childView));
                }
                return super.onSingleTapUp(e);
            }

            @Override
            public void onLongPress(MotionEvent e) {
                super.onLongPress(e);
                View childView = mRecyclerView.findChildViewUnder(e.getX(), e.getY());
                if (childView != null && mOnItemClickListener != null) {
                    mOnItemClickListener.onLongClick(childView,
                            mRecyclerView.getChildAdapterPosition(childView));
                }
            }
        });
    }

    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View vh = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_list_item, parent, false);
        return new MainViewHolder(vh);
    }

    @Override
    public void onBindViewHolder(MainViewHolder holder, int position) {
        NoteItem note = mNoteList.get(position);
        holder.tvDate.setText(note.getDate());
        holder.tvTitle.setText(note.getTitle());

        if (note.isFav() == true) {
            holder.ivFav.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_favorite_red_700_24dp));
        } else {
            holder.ivFav.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_favorite_border_red_700_24dp));
        }

    }

    @Override
    public int getItemCount() {
        return mNoteList.size();
    }


    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        View v = rv.findChildViewUnder(e.getX(), e.getY());
        if (v != null && mOnItemClickListener != null && mGestureDetector.onTouchEvent(e)) {
            mOnItemClickListener.onClick(v, rv.getChildAdapterPosition(v));
        }
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }

    public interface OnItemClickListener {

        void onClick(View view, int position);

        void onLongClick(View view, int position);

    }


    class MainViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvDate;
        ImageView ivFav;

        public MainViewHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.mli_tv_title);
            tvDate = (TextView) itemView.findViewById(R.id.mli_tv_date);
            ivFav = (ImageView) itemView.findViewById(R.id.mli_iv_like);
        }

    }


}
