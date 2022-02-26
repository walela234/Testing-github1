package com.kyu.therehub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ViewReport extends AppCompatActivity {
    ListView listView;
    //database reference to get uploads data
    DatabaseReference mDatabaseReference;
    //list to store uploads data
    List<UploadPdfModel> uploadList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_report);
        getSupportActionBar().hide();
        uploadList = new ArrayList<>();
        listView = (ListView) findViewById(R.id.listview);
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "SOMEFOLDER" + File.separator + "pdffile.pdf");


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {



                //getting the upload
                UploadPdfModel upload = uploadList.get(i);

                //Opening the upload file in
                Intent intent = new Intent(Intent.ACTION_VIEW);
                //default app using url
                intent.setDataAndType(Uri.parse(upload.getFileUrl()), "application/pdf");
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                Intent intent1 = Intent.createChooser(intent, "Open With");
                try {
                    startActivity(intent1);
                } catch (ActivityNotFoundException e) {
                    // Instruct the user to install a PDF reader here, or something
                    Toast.makeText(getApplicationContext(),"Install a reader please",Toast.LENGTH_SHORT).show();
                }
                startActivity(intent);
            }
        });


        //getting the database reference
        mDatabaseReference = FirebaseDatabase.getInstance().getReference("uploads");

        //retrieving upload data from firebase database
        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    UploadPdfModel upload = postSnapshot.getValue(UploadPdfModel.class);
                    uploadList.add(upload);
                }

                String[] uploads = new String[uploadList.size()];

                for (int i = 0; i < uploads.length; i++) {
                    uploads[i] = uploadList.get(i).getFilename();
                }

                //displaying it to list
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

    })

;}


}
