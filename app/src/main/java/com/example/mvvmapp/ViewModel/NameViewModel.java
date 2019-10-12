package com.example.mvvmapp.ViewModel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mvvmapp.Model.NameModel;
import com.example.mvvmapp.Repository.RepositoryFirebase;

import java.util.ArrayList;

public class NameViewModel extends ViewModel {

   private MutableLiveData<ArrayList<NameModel>>nameLiveData;

    public void init(Context context){
        if(nameLiveData!=null){
        }
        nameLiveData= RepositoryFirebase.getInstance(context).getNames();
    }

    public LiveData<ArrayList<NameModel>> getNames(){
        return nameLiveData;
    }
}
