package com.example.ivyng.danhba;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.ivyng.danhba.adapter.ContactAdapter;
import com.example.ivyng.danhba.domain.Contact;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Contact> arrayContact;
    private ContactAdapter adapter;
    private EditText edtname,edtnumber;
    private RadioButton radmale,radfemale;
    private Button btnContact;
    private ListView lvContact;
    private ImageButton btnisAdd;
    private boolean isAdd=true;
    private LinearLayout linear;
    private  Button btnCall, btnMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        AnhXa();
        arrayContact = new ArrayList<>(  );
        adapter = new ContactAdapter( this,R.layout.item_contact_listview,arrayContact );
        lvContact.setAdapter( adapter );
        checkAndRequestPermissions();

        btnContact.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isMale = true;
                if(radmale.isChecked() == false)
                    isMale=false;
                String name = edtname.getText().toString().trim();
                String number = edtnumber.getText().toString().trim();
                if(TextUtils.isEmpty( name ) || TextUtils.isEmpty( number )){
                    Toast.makeText(MainActivity.this,
                            "Plaese fill out this form",Toast.LENGTH_SHORT ).show();
                }else {
                    Contact contact = new Contact( isMale,name,number );
                    arrayContact.add( contact );
                    edtname.setText( "" );
                    edtnumber.setText( "" );
                    radmale.setChecked( true );
                    edtname.requestFocus();
                }
                adapter.notifyDataSetChanged();
            }
        } );
        btnisAdd.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isAdd)
                {
                    linear.setVisibility( View.VISIBLE );
                    isAdd=false;
                }
                else {
                    linear.setVisibility( View.GONE );
                    isAdd=true;
                }
            }
        } );
        // xử lý gọi và nhắn tin

        lvContact.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                arrayContact.get( i );
                showDialogConfirm( i );
            }
        } );
    }

    private void showDialogConfirm (final int i){
        Dialog dialog = new Dialog( this );
        dialog.setContentView( R.layout.dialog_layout );
        btnCall = (Button) dialog.findViewById( R.id.btncall );
        btnMessage = (Button) dialog.findViewById( R.id.btnmessage );

        btnCall.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intentCall( i );
            }
        } );
        
        btnMessage.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intentMessage( i );
            }
        } );
        dialog.show();
    }
    private void intentMessage( int i) {
        Intent intent = new Intent( Intent.ACTION_VIEW, Uri.parse( "sms:" + arrayContact.get( i ).getNumber() ) );
        startActivity( intent );
    }

    private void intentCall(int i) {
        Intent intent = new Intent(  );
        intent.setAction( Intent.ACTION_CALL );
        intent.setData( Uri.parse( "tel:" + arrayContact.get( i ).getNumber() ) );
        startActivity( intent );
    }
    private void checkAndRequestPermissions(){
        String [] permissions = new String[]{
                Manifest.permission.CALL_PHONE,
                Manifest.permission.SEND_SMS
        };
        List<String> listPermissionsNeeded = new ArrayList<>(  );
        for (String permission : permissions){
            if(ContextCompat.checkSelfPermission( this,permission ) != PackageManager.PERMISSION_GRANTED){
                listPermissionsNeeded.add( permission );
            }
        }
        if(!listPermissionsNeeded.isEmpty()){
            ActivityCompat.requestPermissions(
                    this,listPermissionsNeeded.toArray(
                            new String[listPermissionsNeeded.size()] ),1 );
        }
    }

    private void AnhXa(){
        edtname = (EditText) findViewById( R.id.edtname );
        edtnumber = (EditText) findViewById( R.id.edtnum );
        radmale = (RadioButton) findViewById( R.id.radmale );
        radfemale = (RadioButton) findViewById( R.id.radfemale );
        btnContact = (Button) findViewById( R.id.btnadd );
        btnisAdd = (ImageButton) findViewById( R.id.btnisAdd );
        lvContact = (ListView) findViewById( R.id.lvcontact );
        linear = (LinearLayout) findViewById( R.id.formadd);
    }
}
