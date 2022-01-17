package com.example.e_sports_app.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_sports_app.R;
import com.example.e_sports_app.data.Faq;
import com.example.e_sports_app.data.getFaq;

import java.util.List;

public class FaqAdapter extends RecyclerView.Adapter<FaqAdapter.ViewHolder> {
    private List<Faq> UserList;
    private Context context;
    UserListener listener;
    public FaqAdapter(List<Faq>UserList, Context context, UserListener listener){

        this.UserList=UserList;
        this.context=context;
        this.listener=listener;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_faq,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String title = UserList.get(position).getTitle();
        holder.setData(title);
//        holder.SetImage(context,image);
    }

    @Override
    public int getItemCount() {
        return UserList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_title;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_title = itemView.findViewById(R.id.faq_title);
            itemView.setOnClickListener(v->listener.onItemClicked(getAdapterPosition()));

        }

        public void setData(String title){
            tv_title.setText(title);
//            tv_time.setText(time);
        }

//        public void SetImage(final Context c,final String image){
//            ImageView imageView=itemView.findViewById(R.id.imageview);
//            Picasso.get().load(image).into(imageView);
//        }
    }

    public interface UserListener{
        void  onItemClicked(int position);
    }
}



