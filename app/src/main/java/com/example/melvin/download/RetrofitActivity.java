package com.example.melvin.download;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitActivity extends AppCompatActivity {

    private Button button;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);

        textView = (TextView) findViewById(R.id.text_view);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final JsonPlaceHolder service = retrofit.create(JsonPlaceHolder.class);

        button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                download(service);

                Call<List<User>> users = service.listUsers();
                users.enqueue(new Callback<List<User>>() {
                    @Override
                    public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                        if (response.isSuccessful()) {
                            List<User> userList = response.body();
                            StringBuilder stringBuilder = new StringBuilder();
                            for (User user : userList) {
                                stringBuilder.append(user.getName() + " - " + user.getEmail() + "\n");
                            }

                            System.out.println(stringBuilder.toString());

                            textView.setText(stringBuilder.toString());
                        }
                    }

                    @Override
                    public void onFailure(Call<List<User>> call, Throwable t) {

                    }
                });
            }
        });
    }
//
//    public void download(JsonPlaceHolder service) {
//        textView = (TextView) findViewById(R.id.text_view);
//
//
//    }
}
