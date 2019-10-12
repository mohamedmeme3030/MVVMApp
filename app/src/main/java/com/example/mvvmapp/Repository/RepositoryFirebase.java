package com.example.mvvmapp.Repository;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.mvvmapp.DataLoadListener;
import com.example.mvvmapp.Model.NameModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RepositoryFirebase {

    private static RepositoryFirebase instance;
    private ArrayList<NameModel> nameModels = new ArrayList<>();
    static Context mContext;
    static DataLoadListener dataLoadListener;

    public static RepositoryFirebase getInstance(Context context) {
        if (instance == null) {
            mContext = context;
            instance = new RepositoryFirebase();
        }
        dataLoadListener = (DataLoadListener) context;
        return instance;
    }

    public MutableLiveData<ArrayList<NameModel>> getNames() {
        loadsNames();
        MutableLiveData<ArrayList<NameModel>> names = new MutableLiveData<>();
        names.postValue(nameModels);
        return names;
    }

    private void loadsNames() {

        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        Query query = databaseReference.child("Data");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                    nameModels.add(snapshot.getValue(NameModel.class));
                }
                dataLoadListener.onNameLoads();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
