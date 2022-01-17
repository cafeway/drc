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
import com.example.e_sports_app.data.Team;
import com.example.e_sports_app.data.User;

import java.util.Date;
import java.util.List;

public class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.ViewHolder> {
    private List<Team> UserList;
    private Context context;
    UserListener listener;
    public TeamAdapter(List<Team>UserList, Context context, UserListener listener){

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
        String description = UserList.get(position).getDescription();
        holder.setData(name,description);
//        holder.SetImage(context,image);

    }

    @Override
    public int getItemCount() {
        return UserList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_name;
        Button btn_add_player;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_name=itemView.findViewById(R.id.game_name);

            btn_add_player = itemView.findViewById(R.id.add_player_btn);

            btn_add_player.setOnClickListener(v -> listener.onAddBtnClick(getAdapterPosition()));

        }

        public void setData(String name, String description){
            tv_name.setText(name);
//            tv_time.setText(time);
        }

//        public void SetImage(final Context c,final String image){
//            ImageView imageView=itemView.findViewById(R.id.imageview);
//            Picasso.get().load(image).into(imageView);
//        }
    }

    public interface UserListener{
        void  onAddBtnClick(int position);
    }
}



