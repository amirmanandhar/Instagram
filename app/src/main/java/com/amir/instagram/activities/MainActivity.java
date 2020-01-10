package com.amir.instagram.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.amir.instagram.PostApi;
import com.amir.instagram.StoryApi;
import com.amir.instagram.Url;
import com.amir.instagram.fragments.PostFragment;
import com.amir.instagram.fragments.SearchFragment;
import com.amir.instagram.models.PostModel;
import com.amir.instagram.R;
import com.amir.instagram.adapters.StoryAdapter;
import com.amir.instagram.models.StoryModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView btmNav;
//    Fragment selectedFragment = null;

    public static List<PostModel> listPosts = new ArrayList<>();
    public static List<StoryModel> listStories = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btmNav = findViewById(R.id.nav_view);

        getSupportActionBar().hide();

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_search, R.id.navigation_add, R.id.navigation_liked, R.id.navigation_profile
        ).build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(btmNav, navController);

        loadStories();
//        loadPosts();

        listPosts.add(new PostModel("1","Brat Piit", "Game", R.drawable.post1, R.drawable.profilepic));
        listPosts.add(new PostModel("2","Ketty Perry", "Nature", R.drawable.post2, R.drawable.profilepic2));
        listPosts.add(new PostModel("3","Rambo", "Nature!", R.drawable.post3, R.drawable.profilepic));
        listPosts.add(new PostModel("4","Nicol Cage", "Life", R.drawable.post4, R.drawable.profilepic2));
        listPosts.add(new PostModel("5","Brat Piit", "Game", R.drawable.post5, R.drawable.user));
        listPosts.add(new PostModel("6","Ketty Perry", "Nature", R.drawable.post6, R.drawable.profilepic2));
        listPosts.add(new PostModel("7","Rambo", "Nature!", R.drawable.post7, R.drawable.profilepic));
        listPosts.add(new PostModel("8","Nicol Cage", "Life", R.drawable.post8, R.drawable.profilepic2));
        listPosts.add(new PostModel("9","Rambo", "Nature!", R.drawable.post2, R.drawable.profilepic));
        listPosts.add(new PostModel("10","Nicol Cage", "Life", R.drawable.post5, R.drawable.profilepic2));


        listStories.add(new StoryModel("1","Kim ", R.drawable.post1, 0));
        listStories.add(new StoryModel("Sam", R.drawable.post3));
        listStories.add(new StoryModel("1","Rocky", R.drawable.post2, 0));
        listStories.add(new StoryModel("Will Smith", R.drawable.post7));
    }

//    For changing active icon on bottom navigation bar
//    private BottomNavigationView.OnNavigationItemReselectedListener
//                selectedItem = new BottomNavigationView.OnNavigationItemSelectedListener() {
//
//        @Override
//        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
//
//            Menu menu = btmNav.getMenu();
//            menu.findItem(R.id.navigation_home).setIcon(R.drawable.on_home);
//            menu.findItem(R.id.navigation_search).setIcon(R.drawable.on_search);
//            menu.findItem(R.id.navigation_add).setIcon(R.drawable.on_add);
//            menu.findItem(R.id.navigation_liked).setIcon(R.drawable.on_like);
//            menu.findItem(R.id.navigation_profile).setIcon(R.drawable.on_profile);
//
//            switch (menuItem.getItemId()) {
//                case R.id.navigation_home:
//                    selectedFragment = new PostFragment();
//                    menuItem.setIcon(R.drawable.on_home);
//                    break;
//                case R.id.navigation_search:
//                    selectedFragment = new SearchFragment();
//                    menuItem.setIcon(R.drawable.on_search);
//                    break;
//                case  R.id.navigation_add:
//                    menuItem.setIcon(R.drawable.on_add);
//                    break;
//                case R.id.navigation_liked:
//                    menuItem.setIcon(R.drawable.on_like);
//                    break;
//                case R.id.navigation_profile:
//                    menuItem.setIcon(R.drawable.on_profile);
//                    break;
//
//            }
//            getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment,
//                    selectedFragment).commit();
//
//            if (selectedFragment != null){
//                getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, selectedFragment).commit();
//            }
//
//            return true;
//        }
//    };



    public void loadStories(){

        StoryApi storyApi = Url.getInstance().create(StoryApi.class);
        Call<List<StoryModel>> storyCall = storyApi.getStories();

        storyCall.enqueue(new Callback<List<StoryModel>>() {
            @Override
            public void onResponse(Call<List<StoryModel>> call, Response<List<StoryModel>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(MainActivity.this, "Code: " +response.body(), Toast.LENGTH_SHORT).show();
                    return;
                }

//                String imgPath = Url.imagePath +  response.body();
//                Picasso.get().load(imgPath).into(imgProgileImg);

                List<StoryModel> list = response.body();
                for(StoryModel story: list){
                    listStories.add(new StoryModel(story.getName(), story.getDailyPhoto()));
                    List stories = null;
                    stories.add(story.getName());
                }
            }

            @Override
            public void onFailure(Call<List<StoryModel>> call, Throwable t) {

            }
        });


    }

    public void loadPosts(){

        PostApi postApi = Url.getInstance().create(PostApi.class);
        Call<List<PostModel>> postCalls = postApi.getPosts();

        postCalls.enqueue(new Callback<List<PostModel>>() {
            @Override
            public void onResponse(Call<List<PostModel>> call, Response<List<PostModel>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(MainActivity.this, "Code: " + response.body(), Toast.LENGTH_SHORT).show();
                    return;
                }

                List<PostModel> list = response.body();
                assert list != null;
                listPosts.addAll(list);
            }

            @Override
            public void onFailure(Call<List<PostModel>> call, Throwable t) {

            }
        });

    }



}
