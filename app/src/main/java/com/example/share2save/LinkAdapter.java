package com.example.share2save;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.share2save.model.Bookmark;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public class LinkAdapter extends ArrayAdapter<LinkItem>{
	LinkItem linkItems[] = null;
	int layoutResourceId;
	Context context;
	
	public LinkAdapter(Context context, int layoutResourceId, LinkItem[] linkItems){
		super(context, layoutResourceId, linkItems);
		this.context = context;
		this.layoutResourceId = layoutResourceId;
		this.linkItems = linkItems;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent){
		View row = convertView; 
		LinkHolder holder = null; 
		
		if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            
            holder = new LinkHolder();
            holder.txtUrl = (TextView)row.findViewById(R.id.txtTitle);
            
            row.setTag(holder);
        }
        else
        {
            holder = (LinkHolder)row.getTag();
        }
        
        LinkItem item = linkItems[position];
        holder.txtUrl.setText(item.url);
        
        return row;
	}
	
	static class LinkHolder{
		TextView txtUrl; 
	}
}
