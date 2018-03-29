package com.example.ivyng.danhba.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ivyng.danhba.R;
import com.example.ivyng.danhba.domain.Contact;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by ivyng on 03/20/2018.
 */

public class ContactAdapter extends ArrayAdapter<Contact> {

    private Context context;
    private int resource;
    private List<Contact> arrayContact;


    public ContactAdapter(@NonNull Context context, int resource, @NonNull List<Contact> objects) {
        super( context, resource, objects );
        this.context=context;
        this.resource=resource;
        this.arrayContact=objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null){
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from( context ).inflate( R.layout.item_contact_listview,parent,false );
            viewHolder.imgAvatar = (ImageView) convertView.findViewById( R.id.img_avatar );
            viewHolder.txtvName = (TextView) convertView.findViewById( R.id.txtv_name );
            viewHolder.txtvNum = (TextView) convertView.findViewById( R.id.txtv_number );
            convertView.setTag( viewHolder );
        }
        else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Contact contact = arrayContact.get( position );
        viewHolder.txtvName.setText( contact.getName() );
        viewHolder.txtvNum.setText( contact.getNumber() );

        if (contact.getAvatar()){
            viewHolder.imgAvatar.setBackgroundResource( R.drawable.male );
        }
        else viewHolder.imgAvatar.setBackgroundResource( R.drawable.female );


        return convertView;
    }

    public class ViewHolder{
        ImageView imgAvatar;
        TextView txtvName;
        TextView txtvNum;

    }
}
