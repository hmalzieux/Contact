package com.example.hugo.contact;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.ResourceCursorAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by hugo on 06/02/2017.
 */

public class ItemAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater inflater;
    private ArrayList<String[]> contenu;
    public ItemAdapter(Context c, ArrayList<String[]> content) {
        mContext = c;
        this.contenu = content;
    }

    @Override
    public int getCount() {
        return contenu.size();
    }

    @Override
    public String[] getItem(int i) {
        return contenu.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View listView = view;

        if (view == null) {
            inflater =(LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            listView = inflater.inflate(R.layout.item_list, null);
        }
        TextView firstNameView = (TextView) listView.findViewById(R.id.user_firstname_item);
        TextView nameView = (TextView) listView.findViewById(R.id.user_name_item);
        TextView telephoneView = (TextView) listView.findViewById(R.id.user_telephone_item);

        nameView.setText(contenu.get(position)[0]);
        firstNameView.setText(contenu.get(position)[1]);
        telephoneView.setText(contenu.get(position)[2]);
        return listView;
    }

}
