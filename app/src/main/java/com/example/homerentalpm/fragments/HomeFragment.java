package com.example.homerentalpm.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.homerentalpm.R;
import com.example.homerentalpm.adapter.HomeAdapter;
import com.example.homerentalpm.listeners.ItemListener;
import com.example.homerentalpm.model.Item;
import com.example.homerentalpm.screens.DetailsActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import de.hdodenhof.circleimageview.CircleImageView;
import kotlin.collections.ArrayDeque;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class HomeFragment extends Fragment implements ItemListener {

    private RecyclerView topDealRV;
    private HomeAdapter adapter;
    private List<Item> itemList;
    private CircleImageView profileImage;
    private TextView username;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        topDealRV = view.findViewById(R.id.top_deal_RV);
        profileImage = view.findViewById(R.id.profile_image);
        username = view.findViewById(R.id.user_name);



        itemList = new ArrayList<>();
//        itemList.add(new Item("Street # 1 , Pakistan", "$ 200", "1 bedroom"));
//        itemList.add(new Item("Street # 1 , Pakistan", "$ 200", "1 bedroom"));
//        itemList.add(new Item("Street # 1 , Pakistan", "$ 200", "1 bedroom"));
//        itemList.add(new Item("Street # 1 , Pakistan", "$ 200", "1 bedroom"));

        FirebaseDatabase.getInstance().getReference().child("images").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    itemList.add(new Item(
                            Objects.requireNonNull(dataSnapshot.child("location").getValue()).toString(),
                            Objects.requireNonNull(dataSnapshot.child("price").getValue()).toString(),
                            Objects.requireNonNull(dataSnapshot.child("description").getValue()).toString(),
                            Objects.requireNonNull(dataSnapshot.child("shortDescription").getValue()).toString(),
                            Objects.requireNonNull(dataSnapshot.child("image").getValue()).toString()
                    ));
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });

        adapter = new HomeAdapter(getContext(), itemList, (ItemListener) this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        topDealRV.setLayoutManager(linearLayoutManager);
        topDealRV.setAdapter(adapter);
    }

    @Override
    public void OnItemPosition(int position) {
        Intent intent = new Intent(getContext(), DetailsActivity.class);
        intent.putExtra("price", itemList.get(position).getPrice());
        intent.putExtra("location", itemList.get(position).getLocation());
        intent.putExtra("description", itemList.get(position).getDescription());
        intent.putExtra("shortDescription", itemList.get(position).getShortDescription());
        intent.putExtra("image", itemList.get(position).getImage());
        startActivity(intent);
    }
}