package com.example.hugo.contact;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.ResourceCursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by hugo on 06/02/2017.
 */

public class ItemAdapter extends ResourceCursorAdapter {

    private Context mContext;
    private Cursor cursor;
    private LayoutInflater inflater;
    public ItemAdapter(Context c, int layout, Cursor cursor, int flags) {
        super(c,layout, cursor,flags);
        mContext = c;
        this.cursor = cursor;
    }

    @Override
    public int getCount() {
        return cursor.getCount();
    }

    @Override
    public Object getItem(int i) {
        return null;
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

        nameView.setText(cursor.getString(1));
        firstNameView.setText(cursor.getString(cursor.getColumnIndex("COL_PRENOM")));
        telephoneView.setText(cursor.getString(cursor.getColumnIndex("COL_TELEPHONE")));
        return listView;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView firstNameView = (TextView) view.findViewById(R.id.user_firstname_item);
        TextView nameView = (TextView) view.findViewById(R.id.user_name_item);
        TextView telephoneView = (TextView) view.findViewById(R.id.user_telephone_item);

        nameView.setText(cursor.getString(1));
        firstNameView.setText(cursor.getString(2));
        telephoneView.setText(cursor.getString(3));
    }
}
