package kr.ac.kpu.ce2019152012.hair_you.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import kr.ac.kpu.ce2019152012.hair_you.R;
import kr.ac.kpu.ce2019152012.hair_you.item.tmpChatItem;
import kr.ac.kpu.ce2019152012.hair_you.item.tmpItem;

public class MyChattingRecyclerviewAdapter  extends RecyclerView.Adapter<MyChattingRecyclerviewAdapter.ViewHolder> {

    private ArrayList<tmpChatItem> mList;

    @NonNull
    @Override
    public MyChattingRecyclerviewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_chat_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyChattingRecyclerviewAdapter.ViewHolder holder, int position) {
        holder.onBind(mList.get(position));
    }

    public void setList(@NotNull ArrayList<tmpChatItem> item) {
        this.mList = item;
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView profile;
        TextView name;
        TextView content;

        private ViewHolder (View itemView){
            super(itemView);

            profile = (CircleImageView) itemView.findViewById(R.id.profile_civ);
            name=(TextView) itemView.findViewById(R.id.name_tv);
            content = (TextView) itemView.findViewById(R.id.content);
        }


        void onBind(tmpChatItem item) {
            name.setText(item.getName());
            content.setText(item.getContent());
        }
    }
}
