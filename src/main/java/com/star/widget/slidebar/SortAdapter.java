package com.star.widget.slidebar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SectionIndexer;
import android.widget.TextView;


import com.star.widget.R;

import java.util.List;

public class SortAdapter extends BaseAdapter implements SectionIndexer{
	private List<SortModel> list = null;
	private Context mContext;
	private onTextClickListener mListener;
	
	public SortAdapter(Context mContext, List<SortModel> list) {
		this.mContext = mContext;
		this.list = list;
	}

	public void setOnTextClickListener(onTextClickListener listener)
	{
		mListener = listener;
	}

	public void updateListView(List<SortModel> list){
		this.list = list;
		notifyDataSetChanged();
	}

	public int getCount() {
		return this.list.size();
	}

	public Object getItem(int position) {
		return list.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(final int position, View view, ViewGroup arg2) {
		ViewHolder viewHolder = null;
		final SortModel mContent = list.get(position);
		if (view == null) {
			viewHolder = new ViewHolder();
			view = LayoutInflater.from(mContext).inflate(R.layout.sort_item, null);
			viewHolder.tvTitle = (TextView) view.findViewById(R.id.title);
			viewHolder.tvLetter = (TextView) view.findViewById(R.id.catalog);
			viewHolder.line =  view.findViewById(R.id.line);
			view.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) view.getTag();
		}

		int section = getSectionForPosition(position);

		if(position == getPositionForSection(section)){
			viewHolder.tvLetter.setVisibility(View.VISIBLE);
			viewHolder.tvLetter.setText(mContent.getSortLetters());
			viewHolder.line.setVisibility(View.VISIBLE);
		}else{
			viewHolder.tvLetter.setVisibility(View.GONE);
			viewHolder.line.setVisibility(View.GONE);
		}
		viewHolder.tvTitle.setText(mContent.getName());
		viewHolder.tvTitle.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (mListener != null)
				{
					mListener.onClick(position);
				}

			}
		});
		return view;

	}
	


	final static class ViewHolder {
		View line;
		TextView tvLetter;
		TextView tvTitle;
	}

	public int getSectionForPosition(int position) {
		return list.get(position).getSortLetters().charAt(0);
	}

	public int getPositionForSection(int section) {
		for (int i = 0; i < getCount(); i++) {
			String sortStr = list.get(i).getSortLetters();
			int firstChar = sortStr.toUpperCase().charAt(0);
			if (firstChar == section) {
				return i;
			}
		}
		
		return -1;
	}

	private String getAlpha(String str) {
		String  sortStr = str.trim().substring(0, 1).toUpperCase();
		if (sortStr.matches("[A-Z]")) {
			return sortStr;
		} else {
			return "#";
		}
	}

	@Override
	public Object[] getSections() {
		return null;
	}

	public interface onTextClickListener
	{
		public void onClick(int position);
	}
}