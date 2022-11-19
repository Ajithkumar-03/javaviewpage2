package com.example.javaviewpage2ajith;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TextAdapter extends RecyclerView.Adapter<TextAdapter.ViewHolder> {
    String TAG = TextAdapter.class.getSimpleName();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
         Log.d(TAG,"------------>onCreateViewHolder");

         View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.text_holder,parent,false);

         return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG,"------------>onBindHolder: position:" + position);
        holder.textViewQuotes.setText(AppData.quotes[position]);
    }

    @Override
    public int getItemCount() {
        Log.d(TAG,"----------->getItemCount");
        return AppData.quotes.length;
    }

    class  ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewQuotes;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            Log.d(TAG, "------------>viewHolder");
            textViewQuotes = itemView.findViewById(R.id.textView2);
        }
    }
}
