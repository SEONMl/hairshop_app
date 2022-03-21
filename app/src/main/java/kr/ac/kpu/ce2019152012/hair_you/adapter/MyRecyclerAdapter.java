package kr.ac.kpu.ce2019152012.hair_you.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import kr.ac.kpu.ce2019152012.hair_you.R;
import kr.ac.kpu.ce2019152012.hair_you.item.tmpItem;

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.ViewHolder> {

    private ArrayList<tmpItem> mList;

    @NonNull
    @Override
    public MyRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_current_reservation_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(mList.get(position));

    }

    public void setList(@NotNull ArrayList<tmpItem> tmpItems) {
        this.mList = tmpItems;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView reser_date;
        TextView reser_name;
        TextView reser_phone;
        TextView reser_content;

        private ViewHolder(View itemView) {
            super(itemView);

            reser_date = (TextView) itemView.findViewById(R.id.reser_date_tv);
            reser_name = (TextView) itemView.findViewById(R.id.reser_name_tv);
            reser_phone = (TextView) itemView.findViewById(R.id.reser_phone_tv);
            reser_content = (TextView) itemView.findViewById(R.id.reser_content_tv);
        }


        void onBind(tmpItem item) {
            reser_date.setText(item.getDate());
            reser_name.setText(item.getName());
            reser_content.setText(item.getContent());
            reser_phone.setText(item.getPhone());
        }

    }
}
/*
MyAdapter 클래스 내의 onCreatViewHolder, onBindViewHolder, getItemCount 메서드는 RecyclerView.Adapter 인터페이스의 구성원으로 Ctrl+I 를 눌러오버라이드 합니다.

onCreateViewHolder : 어떤 목록 레이아웃(껍데기)을 만들 것인지 반환(어떤 뷰를 생성 할 것인가)
onBindViewHolder : 생성된 뷰(껍데기)에 무슨 데이터를 넣을 것인가
getItemCount() : 몇 개의 목록을 만들지를 반환
Holder클래스(ViewHolder) : 목록의 개별 항목 레이아웃을 포함하는 View 래퍼로, 각 목록 레이아웃에 필요한 기능들을 구현 하는 공간. ex) 아이템 레이아웃에 버튼이 있는 경우 버튼 리스너는 Holder 클래스에서 구현


 */
