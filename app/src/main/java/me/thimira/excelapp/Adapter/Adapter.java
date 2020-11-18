package me.thimira.excelapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import me.thimira.excelapp.R;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    LayoutInflater inflater;
    List<String> column1, column2, column3, column4;

    public Adapter(Context context, List<String> column1, List<String> column2, List<String> column3, List<String> column4) {
        this.inflater = LayoutInflater.from(context);
        this.column1 = column1;
        this.column2 = column2;
        this.column3 = column3;
        this.column4 = column4;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.custom_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        String txt_column1 = column1.get(position);
        String txt_column2 = column2.get(position);
        String txt_column3 = column3.get(position);
        String txt_column4 = column4.get(position);

        holder.myColumn1.setText(new StringBuilder("Column 01 ") + txt_column1);
        holder.myColumn2.setText(txt_column2);
        holder.myColumn3.setText(txt_column3);
        holder.myColumn4.setText(txt_column4);

    }

    @Override
    public int getItemCount() {
        return column1.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView myColumn1, myColumn2, myColumn3, myColumn4;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //listImg = itemView.findViewById(R.id.img_image);
            myColumn1 = itemView.findViewById(R.id.txt_column1);
            myColumn2 = itemView.findViewById(R.id.txt_column2);
            myColumn3 = itemView.findViewById(R.id.txt_column3);
            myColumn4 = itemView.findViewById(R.id.txt_column4);

        }
    }
}
