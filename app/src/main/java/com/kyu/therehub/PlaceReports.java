package com.kyu.therehub;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class PlaceReports extends AppCompatActivity {
    // INITIALIZE ALL VIEWS
    ImageView selectPdf;
    Button uploadBtn, pdfListsBtn;
    ProgressBar progressBar;
    EditText editText;
    StorageReference storageReference;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_reports);
        getSupportActionBar().hide();
        //  GET ALL VIEW BY ID
        selectPdf = findViewById(R.id.uploadpdf);
        editText = findViewById(R.id.editText);
        progressBar=findViewById(R.id.progressBar);
        uploadBtn = findViewById(R.id.uploadBtn);
        pdfListsBtn = findViewById(R.id.pdflist);


        uploadBtn.setEnabled(false);

        // AFTER CLICKING ON selectPdF BUTTON WE WILL REDIRECTED TO CHOOSE PDF
        selectPdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent();
                galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("application/pdf");
                startActivityForResult(galleryIntent, 1);
                Toast.makeText(getApplicationContext(), "Select Pdf ", Toast.LENGTH_SHORT).show();
            }
        });
        // AFTER CLICKING ON pdfListsBtn BUTTON WE WILL REDIRECTED SHOW PDF FILES ACTIVITY
        pdfListsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ViewReport.class);
                startActivity(intent);
            }
        });
    }



    // OVERRIDE A METHOD onActivityResult METHOD WHICH REDIRECT PDF FILE SELECTED SUCCESSFULLY
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {

            if (editText.getText().toString().isEmpty()) {
                editText.setError("Required");
            } else {
                uploadBtn.setEnabled(true);
            }
            // AFTER CLICKING ON upload BUTTON WE WILL REDIRECTED TO UPLOAD PDF FILES METHOD
            uploadBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    progressBar.setVisibility(View.VISIBLE);
                    uploadPdfFiles(data.getData());




                }
            });
        }
    }

    // THIS IS METHOD FOR UPLOADS PDF FILES
    private void uploadPdfFiles(Uri data) {
        // GET REFERENCES OF DATABASE AND STORAGE
        storageReference = FirebaseStorage.getInstance().getReference("uploads/");
        databaseReference = FirebaseDatabase.getInstance().getReference("uploads");
        // CODE FOR UPLOAD PDF
        StorageReference reference = storageReference.child("pdf_" + System.currentTimeMillis() + ".pdf");
        reference.putFile(data).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                while (!uriTask.isComplete()) ;
                Uri uri = uriTask.getResult();
                UploadPdfModel model = new UploadPdfModel(editText.getText().toString(), uri.toString());
                databaseReference.child(databaseReference.push().getKey()).setValue(model);
                progressBar.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(),"succesfully added",Toast.LENGTH_LONG).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
    }
