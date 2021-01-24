package com.example.foodreview;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.foodreview.adapter.RecentsAdapter;
import com.example.foodreview.adapter.TopPlacesAdapter;
import com.example.foodreview.model.RecentsData;
import com.example.foodreview.model.TopPlacesData;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveAction;

public class HomeActivity extends AppCompatActivity {
    RecyclerView recentRecycler, topPlacesRecycler;
    RecentsAdapter recentsAdapter;
    TopPlacesAdapter topPlacesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ImageView iconuser =  findViewById(R.id.icon_user);
        ImageView iconhome =  findViewById(R.id.icon_home);
        ImageView iconplaces =  findViewById(R.id.icon_places);
        ImageView iconavtar =  findViewById(R.id.imageView);
        ImageView profileImage = findViewById(R.id.profileImage);
        FirebaseAuth  fAuth = FirebaseAuth.getInstance();
        FirebaseFirestore fStore = FirebaseFirestore.getInstance();
        StorageReference storageReference = FirebaseStorage.getInstance().getReference();

        StorageReference profileRef = storageReference.child("users/"+fAuth.getCurrentUser().getUid()+"/profile.jpg");
        profileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(iconavtar);
            }
        });

        iconavtar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(HomeActivity.this,MainActivity.class);
                startActivity(intent);


            }
        });

        iconuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(HomeActivity.this,MainActivity.class);
                startActivity(intent);


            }
        });

        iconhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this,HomeActivity.class);
                startActivity(intent);
            }
        });

        iconplaces.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this,Joinroom.class);
                startActivity(intent);
            }
        });

        // Now here we will add some dummy data in our model class

        List<RecentsData> recentsDataList = new ArrayList<>();
        recentsDataList.add(new RecentsData("Cơm gà","Quận 7","From $5 -> 10$",R.drawable.comga));
        recentsDataList.add(new RecentsData("Bánh xèo","quận Gò Vấp","From 3$ -> 5$",R.drawable.banhxeo));
        recentsDataList.add(new RecentsData("Bánh cuốn","quận Bình Thạnh","From $2 ->4$",R.drawable.banhcuon));
        recentsDataList.add(new RecentsData("Kem","Quận 10","From $3 ->5",R.drawable.kem));
        recentsDataList.add(new RecentsData("Buffe bò","Quận 2","From 30$ -> 40$",R.drawable.bupphe));
        recentsDataList.add(new RecentsData("Cơm Sườn","Quận Gò Vấp","From 2$ ->5$",R.drawable.suon));

        setRecentRecycler(recentsDataList);

        List<TopPlacesData> topPlacesDataList = new ArrayList<>();
        topPlacesDataList.add(new TopPlacesData("Bánh Canh","quận Bình Thạnh","From 3$ ->5$",R.drawable.banhcanh));
        topPlacesDataList.add(new TopPlacesData("Bánh tráng","Quận 2","From 2$ ->5$",R.drawable.banhtrang));
        topPlacesDataList.add(new TopPlacesData("Cá vi chiên","quận Gò Vấp","From 3$ -> 5$",R.drawable.cavichien));
        topPlacesDataList.add(new TopPlacesData("Bún riêu","quận Gò Vấp","$200 - $500",R.drawable.bunrieu));
        topPlacesDataList.add(new TopPlacesData("Ốc","quận Gò Vấp","From $5 -> 10$",R.drawable.oc));

        setTopPlacesRecycler(topPlacesDataList);
    }



    private  void setRecentRecycler(List<RecentsData> recentsDataList){

        recentRecycler = findViewById(R.id.recent_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        recentRecycler.setLayoutManager(layoutManager);
        recentsAdapter = new RecentsAdapter(this, recentsDataList);
        recentRecycler.setAdapter(recentsAdapter);

    }

    private  void setTopPlacesRecycler(List<TopPlacesData> topPlacesDataList){

        topPlacesRecycler = findViewById(R.id.top_places_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        topPlacesRecycler.setLayoutManager(layoutManager);
        topPlacesAdapter = new TopPlacesAdapter(this, topPlacesDataList);
        topPlacesRecycler.setAdapter(topPlacesAdapter);

    }
}