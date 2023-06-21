package com.example.assignmate;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;

public class adapter extends FirebaseRecyclerAdapter<file_model,adapter.ViewHolder> {
    RecyclerView recyclerView;


    ProgressBar progressBar;
    Context context;

    ArrayList<String> urls = new ArrayList<>();

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public adapter(@NonNull FirebaseRecyclerOptions<file_model> options,Context context) {
        super(options);
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //create views for recycler view items
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.document_design,parent,false);
        return new adapter.ViewHolder(view); //return object of viewholder  which contains all the views

    }
//    @Override
//    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        //Initialize the elements of individual objects
//        file_model model = items.get(position)
//        holder.name.setText(model.getFile_Name());
//        holder.descrption.setText(model.getDescription());
//
//    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull file_model model) {
        holder.name.setText("File Name:- "+model.getFile_Name());
        holder.descrption.setText("Description:- "+model.getDescription());
        urls.add(model.getUrl());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(model.getUrl()));
                intent.setPackage("com.android.chrome");
                context.startActivity(intent);// This will launch a browser
                try {
                    context.startActivity(intent);
                } catch (ActivityNotFoundException ex) {
                    // Chrome browser presumably not installed so allow user to choose instead
                    intent.setPackage(null);
                    context.startActivity(intent);
                }
            }
        });

    }



    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView descrption,name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            progressBar=itemView.findViewById(R.id.progressBarID);
            name=itemView.findViewById(R.id.put_filename);
            descrption=itemView.findViewById(R.id.put_description);
            RecyclerView recyclerView=itemView.findViewById(R.id.recyclerView);

        }

    }


}