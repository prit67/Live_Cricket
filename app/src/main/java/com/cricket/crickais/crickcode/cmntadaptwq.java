package com.cricket.crickais.crickcode;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class cmntadaptwq extends RecyclerView.Adapter<cmntadaptwq.ViewHolder> {
    LayoutInflater inflater;
    List<song> songs;

    public cmntadaptwq(Context ctx, List<song> songs){
        this.inflater=LayoutInflater.from(ctx);
        this.songs=songs;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.cmnt,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.over.setText(songs.get(position).getPname());
        holder.status.setText(songs.get(position).getPrun()+"");
        holder.cmnts.setText(songs.get(position).getPsr()+"");
        holder.cmntsd.setText(songs.get(position).getPfour());

        holder.temp.setText(songs.get(position).getPsix());
        int idd=Integer.parseInt(songs.get(position).getPsix());

        if(songs.get(position).getPrun()==1 || songs.get(position).getPrun()==2 || songs.get(position).getPrun()==3) {
            holder.main1.setBackgroundColor(Color.parseColor("#1068D5"));
        }else if(songs.get(position).getPrun()==4 || songs.get(position).getPrun()==6 ){
            holder.main1.setBackgroundColor(Color.parseColor("#29C146"));
        }
         else if(idd==54|| idd==56 || idd==58 || idd==63 || idd==78 || idd==79 || idd==83 || idd==87){
            holder.main1.setBackgroundResource(R.drawable.dsn6);
            holder.status.setText("W");
            holder.cmntsd.setTextColor(Color.parseColor("#FF9C112C"));
        }

        else{
            holder.main1.setBackgroundResource(R.drawable.dsn6);
        }

    }

    @Override
    public int getItemCount() {
        return songs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView over,status,cmnts,cmntsd,temp;
        ImageView icon;
        RelativeLayout main1;
        public ViewHolder(View itemview){
            super(itemview);
            over=itemview.findViewById(R.id.over);
            status=itemview.findViewById(R.id.status);
            cmnts=itemview.findViewById(R.id.cmnts);
            cmntsd=itemview.findViewById(R.id.cmntsd);
            main1=itemview.findViewById(R.id.main1);
            temp=itemview.findViewById(R.id.temp);
        }
    }
}
