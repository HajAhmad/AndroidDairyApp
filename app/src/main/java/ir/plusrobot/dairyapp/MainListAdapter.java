package ir.plusrobot.dairyapp;


import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainListAdapter extends RecyclerView.Adapter<MainListAdapter.MainViewHolder> {

    private List<NoteItem> mNoteList;
    private Context mContext;

    private OnItemClickListener mOnItemClickListener;
    private OnLikeClickListener mOnLikeClick;
    private RecyclerView mRecyclerView;



    public MainListAdapter(List<NoteItem> mNoteList, Context mContext) {
        this.mNoteList = new ArrayList<>();
        this.mNoteList = mNoteList;
        this.mContext = mContext;
    }


    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View vh = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_list_item, parent, false);
        return new MainViewHolder(vh);
    }

    @Override
    public void onBindViewHolder(final MainViewHolder holder, int position) {
        NoteItem note = mNoteList.get(position);
        holder.tvDate.setText(note.getDate());
        holder.tvTitle.setText(note.getTitle());
        final int id = note.getId();

        if (note.isFav() == true) {
            holder.ivFav.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_favorite_red_700_24dp));
        } else {
            holder.ivFav.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_favorite_border_red_700_24dp));
        }

        holder.ivFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnLikeClick != null) {
                    mOnLikeClick.onLikeClick(id);
                }
            }
        });

        holder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null){
                    mOnItemClickListener.onClick(v, holder.getAdapterPosition());
                }
            }
        });

        holder.cv.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mOnItemClickListener != null)
                    mOnItemClickListener.onLongClick(v, holder.getAdapterPosition());
                return false;
            }
        });

    }

    @Override
    public int getItemCount() {
        return mNoteList.size();
    }



    public interface OnItemClickListener {

        void onClick(View view, int position);

        void onLongClick(View view, int position);

    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.mOnItemClickListener = onItemClickListener;
    }

    public interface OnLikeClickListener{
        void onLikeClick(int id);
    }

    public void setOnLikeClickListener(OnLikeClickListener onLikeClickListener){
        this.mOnLikeClick = onLikeClickListener;
    }


    class MainViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvDate;
        ImageView ivFav;
        CardView cv;
        public MainViewHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.mli_tv_title);
            tvDate = (TextView) itemView.findViewById(R.id.mli_tv_date);
            ivFav = (ImageView) itemView.findViewById(R.id.mli_iv_like);
            cv = (CardView) itemView.findViewById(R.id.mli_cv);
        }

    }


}
