package com.example.e_sports_app.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_sports_app.R;
import com.example.e_sports_app.data.Notice;

import java.util.List;

public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.ViewHolder> {
    private List<Notice> UserList;
    private Context context;
    public NoticeAdapter(List<Notice>UserList, Context context){

        this.UserList=UserList;
        this.context=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_notices,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String title=UserList.get(position).getTitle();
        String description=UserList.get(position).getDescription();
        String time=UserList.get(position).getTime();
        holder.setData(title,description,time);
//        holder.SetImage(context,image);

    }

    @Override
    public int getItemCount() {
        return UserList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_title,tv_description,tv_time;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_title=itemView.findViewById(R.id.not_title);
            tv_description=itemView.findViewById(R.id.not_description);
            tv_time=itemView.findViewById(R.id.not_time);


        }

        public void setData(String title, String description, String time){
            tv_time.setText(title);
            tv_description.setText(description);
            tv_time.setText(time);
        }

//        public void SetImage(final Context c,final String image){
//            ImageView imageView=itemView.findViewById(R.id.imageview);
//            Picasso.get().load(image).into(imageView);
//        }
    }
}



