package com.example.recycler_view_test_1004;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {
    private ArrayList<User> arrayList;
    private Context context;

    public CustomAdapter(ArrayList<User> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    //최초 뷰 리스트 아이템에 대한 뷰를 연결을 해주는 역할을 한다.
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent,false);
        CustomViewHolder holder = new CustomViewHolder(view);
        return holder;
    }

    @Override
    //실질적으로 각 아이템들에 대한 매칭을 시켜주는 역할을 한다.
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
    //이미지를 편하게 로딩할 수 있는 메소드 사용 _ import 따로 해줘야함(gradle)
        Glide.with(holder.itemView)
                .load(arrayList.get(position).getProfile())
                .into(holder.iv_profile);                   // arrayList를 User에 연결했었음.[위에서]
                                                            // -> mainActivity 등에서 fireBase Date 받아온다.
                                                            // -> User 객체가 있는 arrayList에 담아서 Adapter에 쏜다
                                                            // -> onbindViewholder에서 그걸 받아서 glider로 Load 한다.

        holder.tv_id.setText(String.valueOf(arrayList.get(position).getId()));   // ListColumn 여러가지를 만들건데, 그 ArrayList 다 담아서
                                                                                 // 객체 User에 담은 다음에 여기에 뿌려줌
        holder.tv_pw.setText(String.valueOf(arrayList.get(position).getPw()));   // String.valueOf를 달아준 이유 : 1234와 같은거 하면 숫자로 인식->에러
        holder.tv_userName.setText(arrayList.get(position).getUserName());
    }

    @Override
    public int getItemCount() {
        //삼항 연산자 앞 조건문?true:false 로 실행됨
        return(arrayList != null ? arrayList.size() : 0);
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_profile;
        TextView tv_id;
        TextView tv_pw;
        TextView tv_userName;
        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.iv_profile = itemView.findViewById(R.id.iv_profile);
            this.tv_id = itemView.findViewById(R.id.tv_id);
            this.tv_pw = itemView.findViewById(R.id.tv_pw);
            this.tv_userName = itemView.findViewById(R.id.tv_userName);
        }
    }
}
