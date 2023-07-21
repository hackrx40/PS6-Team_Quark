package com.example.drive_fit_.adapterClass;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drive_fit_.R;
import com.example.drive_fit_.activityclass.module;
import com.example.drive_fit_.modelClass.weight_train;

import java.util.ArrayList;

public class weight_train_adapter extends RecyclerView.Adapter<weight_train_adapter.myviewholder> {


    public ArrayList<weight_train> data;
    public Context context;


    public weight_train_adapter(ArrayList<weight_train> data, Context context) {
        this.data = data;
        this.context = context;
    }


    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflator = LayoutInflater.from(parent.getContext());
        View v;

        v = inflator.inflate(R.layout.homeworkout_customlayout, parent, false);
        return new weight_train_adapter.myviewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {

        holder.exerpic.setImageResource(data.get(position).getExercisepic());
        holder.exername.setText(data.get(position).getExercisename());

        if(position == 2)
        {
            holder.exerpic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent i = new Intent(context, module.class);

                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); //add this line

                    context.startActivity(i);

                }
            });
        }




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
