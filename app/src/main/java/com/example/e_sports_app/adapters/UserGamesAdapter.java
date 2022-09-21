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
import com.example.e_sports_app.data.Game;
import com.example.e_sports_app.data.Notice;
import com.example.e_sports_app.data.User;

import java.util.Date;
import java.util.List;

public class UserGamesAdapter extends RecyclerView.Adapter<UserGamesAdapter.ViewHolder> {
    private List<Game> UserList;
    private Context context;
    public UserGamesAdapter(List<Game>UserList, Context context){

        this.UserList=UserList;
        this.context=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_user_games,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String team_one=UserList.get(position).getTeam1_name();
        String team_two=UserList.get(position).getTeam2_name();
        String team_one_results=UserList.get(position).getScore_team1();
        String team_two_results =UserList.get(position).getScore_team_2();
        String date =UserList.get(position).getPlay_date();
        String time =UserList.get(position).getPlay_time();
        String status =UserList.get(position).getGame_status();
        holder.setData(team_one,team_two,team_one_results,team_two_results,date,time,status);


//        holder.SetImage(context,image);

    }

    @Override
    public int getItemCount() {
        return UserList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_one_name,tv_two_name,tv_one_result, tv_two_result,tv_date,tv_time,tv_status;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_one_name=itemView.findViewById(R.id.game_team_one);
            tv_two_name=itemView.findViewById(R.id.game_team_2);
            tv_one_result=itemView.findViewById(R.id.team_one_results);
            tv_two_result=itemView.findViewById(R.id.team_two_results);
            tv_date=itemView.findViewById(R.id.game_date);
            tv_time =itemView.findViewById(R.id.game_time);
            tv_status =itemView.findViewById(R.id.game_status);

        }

        public void setData(String team_one, String team_two, String team_one_results, String team_two_results, String date, String time , String status){
            tv_one_name.setText(team_one);
            tv_two_name.setText(team_two);
            tv_one_result.setText(team_one_results);
            tv_two_result.setText(team_two_results);
            tv_date.setText(date);
            tv_time.setText(time);
            tv_status.setText(status);
//            tv_time.setText(time);
        }

//        public void SetImage(final Context c,final String image){
//            ImageView imageView=itemView.findViewById(R.id.imageview);
//            Picasso.get().load(image).into(imageView);
//        }
    }

}



