package com.example.drive_fit_.fragmentclass;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.drive_fit_.R;
import com.example.drive_fit_.adapterClass.weight_train_adapter;
import com.example.drive_fit_.adapterClass.yoga_adapter;
import com.example.drive_fit_.modelClass.weight_train;
import com.mig35.carousellayoutmanager.CenterScrollListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Home#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Home extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    RecyclerView weight_recycle;
    RecyclerView train_recycle;
    RecyclerView yoga_recycle;


    LinearSnapHelper snap = new LinearSnapHelper();


    weight_train_adapter adapter;
    yoga_adapter yadapter;

    public int pos = 2;



    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Home() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Home.
     */
    // TODO: Rename and change types and number of parameters
    public static Home newInstance(String param1, String param2) {
        Home fragment = new Home();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_home, container, false);

        weight_recycle = (RecyclerView) v.findViewById(R.id.homeworkout_recycler);
        snap.attachToRecyclerView(weight_recycle);

        adapter = new weight_train_adapter(dataque_task(), getContext());

        /*weight_recycle.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                switch (newState) {
                    case AbsListView.OnScrollListener.SCROLL_STATE_IDLE:
                        LinearLayoutManager linearLayoutManager = (LinearLayoutManager) weight_recycle.getLayoutManager();

                        assert linearLayoutManager != null;
                        pos = linearLayoutManager.findFirstVisibleItemPosition();

                        Toast.makeText(getContext(), "" + pos, Toast.LENGTH_SHORT).show();
                        adapter.notifyItemChanged(pos);


                        break;
                    default:
                        break;
                }
            }
        });*/

        /*weight_recycle.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    float position = (float) recyclerView.computeHorizontalScrollOffset() / (float) 30;
                    int itemPosition = (int) Math.floor(position);
                }
                super.onScrollStateChanged(recyclerView, newState);
            }
        });
*/

        //final CarouselLayoutManager layoutManager = new CarouselLayoutManager(CarouselLayoutManager.HORIZONTAL);

        //final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        // weight_recycle.setLayoutManager(layoutManager);
        // weight_recycle.setHasFixedSize(true);

        weight_recycle.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false));
        weight_recycle.setAdapter(adapter);
        weight_recycle.addOnScrollListener(new CenterScrollListener());

//        train_recycle = (RecyclerView) v.findViewById(R.id.weighttrain_recycler);
//        tadapter = new train_adapter(dataque_task2(), getContext());
//        train_recycle.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false));
//        train_recycle.setAdapter(tadapter);


        yoga_recycle = (RecyclerView) v.findViewById(R.id.yoga_recycler);
        yadapter = new yoga_adapter(dataque_task3(), getContext());
        yoga_recycle.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false));
        yoga_recycle.setAdapter(yadapter);



        return v;
    }



    public ArrayList<weight_train> dataque_task()
    {

        ArrayList<weight_train> holder = new ArrayList<>();

        holder.add(new weight_train(R.drawable.strecharms, "Arm Strech"));
        holder.add(new weight_train(R.drawable.squatss, "Sqauts"));
        holder.add(new weight_train(R.drawable.womenpushupss, "Push-Ups"));
        holder.add(new weight_train(R.drawable.shouldup, "Shoulder hook"));


        return holder;




    }

    public ArrayList<weight_train> dataque_task2()
    {

        ArrayList<weight_train> holder = new ArrayList<>();

        holder.add(new weight_train(R.drawable.barbelcurl, "Bicep Curls"));
        holder.add(new weight_train(R.drawable.lunges, "Lunges"));
        holder.add(new weight_train(R.drawable.chestsidedumb, "Side Chest press"));
        holder.add(new weight_train(R.drawable.legexten, "Leg Extension"));


        return holder;




    }

    public ArrayList<weight_train> dataque_task3()
    {

        ArrayList<weight_train> holder = new ArrayList<>();

        holder.add(new weight_train(R.drawable.blueyoga, "Nar-Asana"));
        holder.add(new weight_train(R.drawable.whiteyoga, "Vir-Asana"));
        holder.add(new weight_train(R.drawable.pranyam, "Pranayama"));



        return holder;




    }


}