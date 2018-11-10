package com.android.project.xmlparsing;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {

    private ArrayList<PostValue> postValueArrayList;
    private Context context;

    public MyAdapter(ArrayList<PostValue> postValueArrayList, Context applicationContext) {
        this.postValueArrayList = postValueArrayList;
        this.context = applicationContext;
    }

    @Override
    public int getCount() {
        return postValueArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return postValueArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater inflater=LayoutInflater.from(context);
        View v=inflater.inflate(R.layout.indiview,null);

        TextView tv_id = v.findViewById(R.id.tv_id);
        TextView tv_name = v.findViewById(R.id.tv_name);
        TextView tv_cost = v.findViewById(R.id.tv_cost);
        TextView tv_description = v.findViewById(R.id.tv_description);

        tv_id.setText(postValueArrayList.get(i).getId());
        tv_name.setText(postValueArrayList.get(i).getName());
        tv_cost.setText(postValueArrayList.get(i).getCost());
        tv_description.setText(postValueArrayList.get(i).getDescription());

        return v;
    }
}
