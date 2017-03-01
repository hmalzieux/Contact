package com.example.hugo.contact;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

/**
 * Created by hugo on 10/02/2017.
 */

public class myCursorAdapter extends CursorAdapter {
    private LayoutInflater cursorInflater;

    public myCursorAdapter(Context context, Cursor cursor){
        super(context, cursor, 0);
        cursorInflater = (LayoutInflater) context.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.item_list, viewGroup, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView firstNameView = (TextView) view.findViewById(R.id.user_firstname_item);
        TextView nameView = (TextView) view.findViewById(R.id.user_name_item);
        TextView telephoneView = (TextView) view.findViewById(R.id.user_telephone_item);
        Log.d("testcur",""+cursor.getColumnIndex("NOM"));
        nameView.setText(cursor.getString(2));
        firstNameView.setText(cursor.getString(3));
        telephoneView.setText(cursor.getString(4));
    }
}
