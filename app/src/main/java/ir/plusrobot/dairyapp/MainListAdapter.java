package ir.plusrobot.dairyapp;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MainListAdapter extends RecyclerView.Adapter<MainListAdapter.MainViewHolder> {

    private List<NoteItem> mNoteList;
    private Context mContext;

    public MainListAdapter(List<NoteItem> mNoteList, Context mContext) {
        this.mNoteList = mNoteList;
        this.mContext = mContext;
    }

    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View vh = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_list_item, parent);
        return new MainViewHolder(vh);
    }

    @Override
    public void onBindViewHolder(MainViewHolder holder, int position) {
        NoteItem note = mNoteList.get(position);
        holder.tvDate.setText(note.getDate());
        holder.tvTitle.setText(note.getTitle());
    }

    @Override
    public int getItemCount() {
        return mNoteList.size();
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
