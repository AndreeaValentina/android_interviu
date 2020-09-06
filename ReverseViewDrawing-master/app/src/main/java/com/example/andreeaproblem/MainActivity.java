package com.example.andreeaproblem;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    public static final String MY_TAG = "MyTag";
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        getUserListData();
     /*   LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);
        // call the constructor of UsersAdapter to send the reference and data to Adapter
        UsersAdapter usersAdapter = new UsersAdapter(MainActivity.this, userListResponseData);
        recyclerView.setAdapter(usersAdapter); // set the Adapter to RecyclerView */
//        getUserListData(); // call a method in which we have implement our GET type web API
    }

    private void getUserListData() {
        // display a progress dialog
        final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set message
//        progressDialog.show(); // show progress dialog


        (Api.getClient().getUsersList()).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String myResponse = response.body().string();
                    JSONObject responseObject = new JSONObject(myResponse);
                    if(responseObject.get("data") instanceof JSONArray){
                        JSONArray dataArray = responseObject.getJSONArray("data");
                        List<UserListResponse> myList = new ArrayList<>();
                        for(int i = 0; i < dataArray.length(); i++){
                            UserListResponse userListResponse = new UserListResponse();
                            userListResponse.setFirstName(dataArray.getJSONObject(i).getString("first_name"));
                            userListResponse.setAddress(dataArray.getJSONObject(i).getString("address"));
                            myList.add(userListResponse);
                        }
                        //Now you have your complete response in a list format
                        setDataInRecyclerView(myList);
                    }
                    Log.d(MY_TAG, "onResponse: "+myResponse);
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    private void setDataInRecyclerView(List<UserListResponse> userListResponseData) {
        // set a LinearLayoutManager with default vertical orientation
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);
        // call the constructor of UsersAdapter to send the reference and data to Adapter
        UsersAdapter usersAdapter = new UsersAdapter(MainActivity.this, userListResponseData);
        recyclerView.setAdapter(usersAdapter); // set the Adapter to RecyclerView
    }
}
