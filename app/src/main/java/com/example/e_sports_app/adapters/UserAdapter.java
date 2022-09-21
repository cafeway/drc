package com.example.e_sports_app.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_sports_app.R;
import com.example.e_sports_app.data.Notice;
import com.example.e_sports_app.data.User;

import java.util.Date;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    private List<User> UserList;
    private Context context;
    UserListener listener;
    public UserAdapter(List<User>UserList, Context context, UserListener listener){

        this.UserList=UserList;
        this.context=context;
        this.listener=listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_manage_users,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String name = UserList.get(position).getName();
        String email = UserList.get(position).getEmail();
        String user_type = UserList.get(position).getUsertype();
        String phone_no = UserList.get(position).getPhone_number();
        holder.setData(name,email,user_type,phone_no);
//        holder.SetImage(context,image);

    }

    @Override
    public int getItemCount() {
        return UserList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_name,tv_email,tv_type, tv_number;
        Button btn_approve,btn_reject,btn_disable;
        ImageView btn_delete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_name=itemView.findViewById(R.id.manage_user_name);
            tv_email=itemView.findViewById(R.id.manage_user_email);
            tv_type=itemView.findViewById(R.id.manage_user_type);
            tv_number=itemView.findViewById(R.id.manage_user_phone_number);

            btn_approve = itemView.findViewById(R.id.manage_user_approve);
            btn_delete = itemView.findViewById(R.id.manage_user_delete);
            btn_disable = itemView.findViewById(R.id.manage_user_disable);
            btn_reject = itemView.findViewById(R.id.manage_user_reject);

            btn_approve.setOnClickListener(v -> listener.onApproveUserClick(getAdapterPosition()));
            btn_delete.setOnClickListener(v -> listener.onDeleteUserClick(getAdapterPosition()));
            btn_disable.setOnClickListener(v -> listener.onDisableUserClick(getAdapterPosition()));
            btn_reject.setOnClickListener(v -> listener.onRejectUserClick(getAdapterPosition()));

        }

        public void setData(String name, String email, String user_type, String phone_no){
            tv_name.setText(name);
            tv_email.setText(email);
            tv_type.setText(user_type);
            tv_number.setText(phone_no);
//            tv_time.setText(time);
        }

//        public void SetImage(final Context c,final String image){
//            ImageView imageView=itemView.findViewById(R.id.imageview);
//            Picasso.get().load(image).into(imageView);
//        }
    }

    public interface UserListener{
        void  onApproveUserClick(int position);
        void  onRejectUserClick(int position);
        void  onDeleteUserClick(int position);
        void  onDisableUserClick(int position);
    }
}



