package com.example.drive_fit_.adapterClass;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drive_fit_.R;
import com.example.drive_fit_.fragmentclass.Home;
import com.example.drive_fit_.modelClass.weight_train;

import java.util.ArrayList;

public class yoga_adapter extends RecyclerView.Adapter<yoga_adapter.myviewholder>{


    public ArrayList<weight_train> data;
    public Context context;


    public yoga_adapter(ArrayList<weight_train> data, Context context) {
        this.data = data;
        this.context = context;
    }


    @NonNull
    @Override
    public yoga_adapter.myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflator = LayoutInflater.from(parent.getContext());
        View v;

        v = inflator.inflate(R.layout.homeworkout_customlayout, parent, false);
        return new yoga_adapter.myviewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull yoga_adapter.myviewholder holder, int position) {

        holder.exerpic.setImageResource(data.get(position).getExercisepic());
        holder.exername.setText(data.get(position).getExercisename());


        if(new Home().pos == position)
        {
            holder.exername.setText("Soumen");
        }


        //setAnimation(holder.itemView, position);


    }


    /*void setAnimation(View view, int position) {

        int last = data.size()-1;
//this allows new views coming in the recycle view to slide to left while scrolling down and slide to right while scrolling up.
        if (last < position) {
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            view.startAnimation(animation);
        } else {
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_out_right);
            view.startAnimation(animation);
        }
        last = position;
    }*/

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class myviewholder extends RecyclerView.ViewHolder
    {

        ImageView exerpic;
        TextView exername;


        public myviewholder(@NonNull View itemView) {
            super(itemView);
            exername = (TextView) itemView.findViewById(R.id.exercisename);
            exerpic = (ImageView) itemView.findViewById(R.id.exercisepic);


        }
    }



}
