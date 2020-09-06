package com.example.andreeaproblem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;
import androidx.recyclerview.widget.RecyclerView;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UsersViewHolder> {

    Context context;
    List<UserListResponse> userListResponseData;
    private int position;

    public UsersAdapter(Context context, List<UserListResponse> userListResponseData) {
        this.userListResponseData = userListResponseData;
        this.context = context;
    }

    @Override
    public UsersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.users_list_items, null);
        UsersViewHolder usersViewHolder = new UsersViewHolder(view);
        return usersViewHolder;
    }

    @Override
    public void onBindViewHolder(UsersViewHolder holder,final int position) {
        // set the data
        holder.first_name.setText("First Name: " + userListResponseData.get(position).getFirstName());
        holder.address.setText("Last Name: " + userListResponseData.get(position).getAddress());
    }

    @Override
    public int getItemCount() {
        return userListResponseData.size(); // size of the list items
    }

    class UsersViewHolder extends RecyclerView.ViewHolder {
        // init the item view's
        TextView first_name, address;

        public UsersViewHolder(View itemView) {
            super(itemView);
            // get the reference of item view's
            first_name = itemView.findViewById(R.id.first_name);
            address = itemView.findViewById(R.id.address);
        }
    }
}

