package com.example.e_sports_app.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_sports_app.R;
import com.example.e_sports_app.data.EquipMent;
import com.example.e_sports_app.data.Faq;
import com.example.e_sports_app.data.getFaq;

import java.util.List;

public class AdminEquipmentAdapter extends RecyclerView.Adapter<AdminEquipmentAdapter.ViewHolder> {
    private List<EquipMent> UserList;
    private Context context;
    UserListener listener;
    public AdminEquipmentAdapter(List<EquipMent>UserList, Context context, UserListener listener){

        this.UserList=UserList;
        this.context=context;
        this.listener=listener;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_captain_equipment,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String title = UserList.get(position).getTitle();
        String message = UserList.get(position).getMessage();
        String status = UserList.get(position).getStatus();
        holder.setData(title,message,status);
//        holder.SetImage(context,image);
    }

    @Override
    public int getItemCount() {
        return UserList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_title,tv_message,tv_status;
        Button approve_btn,reject_btn;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_title = itemView.findViewById(R.id.tv_title);
            tv_message = itemView.findViewById(R.id.tv_message);
            tv_status = itemView.findViewById(R.id.tv_status);
            approve_btn = itemView.findViewById(R.id.approve_request);
            reject_btn = itemView.findViewById(R.id.reject_request);
            itemView.setOnClickListener(v->listener.onItemClicked(getAdapterPosition()));

        }

        public void setData(String title,String message,String status){
            tv_title.setText(title);
            tv_message.setText(message);
            tv_status.setText(status);
//            tv_time.setText(time);
        }

//        public void SetImage(final Context c,final String image){
//            ImageView imageView=itemView.findViewById(R.id.imageview);
//            Picasso.get().load(image).into(imageView);
//        }
    }

    public interface UserListener{
        void  onItemClicked(int position);
        void onRejectButtonClicked(int position);
        void onApproveButtonClicked(int position);
    }
}



