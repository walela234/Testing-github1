package com.kyu.therehub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class Register_Lecturer extends AppCompatActivity {
    private EditText etusername,etemail,etpassword;
    AutoCompleteTextView etcourse;
    ProgressBar progressBar;
    private TextView alreadyhave,login;
    private Button Reg_button;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_lecturer);

        insta();
        String[] Courses = { "Building Economics", "Land Surveying and Information Systems", "Land Economics", "Architecture"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.select_dialog_item, Courses);

        //Used to specify minimum number of characters the user has to type in order to display the drop down hint.
        etcourse.setThreshold(1);

        etcourse.setAdapter(arrayAdapter);
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        Reg_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Create_Lecturer();

            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Login_Activity.class));
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.auto_complete_edit_text, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(mAuth.getCurrentUser()!= null){
            //handle user login
        }
    }

    private void insta() {
        etusername=findViewById(R.id.username);//name instatiation
        etemail=findViewById(R.id.email);//email instatiation
        progressBar=findViewById(R.id.progressBar);//progressbar
        etcourse=findViewById(R.id.course);//the course
        etpassword=findViewById(R.id.password);//password part
        alreadyhave=findViewById(R.id.alreadyhave);//already te
        login=findViewById(R.id.login);//textvie wfor login
        Reg_button=findViewById(R.id.Reg_button);//button only i  tactvty
    }
    public void Create_Lecturer(){
        //create string variables to take on from edit text
        final String Usernametxt=etusername.getText().toString().trim();
        final String Emailtxt=etemail.getText().toString().trim();
        final String Coursetxt=etcourse.getText().toString().trim();
        final String Passwordtxt=etpassword.getText().toString().trim();

        if(Usernametxt.isEmpty()){
            etusername.setError("Please enter username");
            etusername.requestFocus();
            return;
        }
        if(Emailtxt.isEmpty()){
            etemail.setError("Please enter your email");
            etemail.requestFocus();
            return;
        }
        if(Coursetxt.isEmpty()){
            etcourse.setError("Please enter course");
            etcourse.requestFocus();
            return;
        }
        if(Passwordtxt.isEmpty() || Passwordtxt.length()<6){
            etpassword.setError("Please enter password of more than six characters");
            etpassword.requestFocus();
            return;
        }
        //calling progressbar]
        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(Emailtxt,Passwordtxt)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){//means that if it is successful then store other details in realtme database

                            //adding rare data too
                           User user=new User(Usernametxt,Emailtxt,Coursetxt,Passwordtxt);
                            FirebaseDatabase.getInstance().getReference("Users")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid()
                            ).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {

                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    progressBar.setEnabled(false);
                                    if(task.isSuccessful()){


                                        if(Coursetxt.equals("Architecture")){
                                            Toast.makeText(getApplicationContext(),"Registration completed wait for feature",Toast.LENGTH_SHORT).show();
                                            startActivity(new Intent(getApplicationContext(), ArchitectureActivity.class));
                                            finish();


                                        }else if(Coursetxt.equals("Building Economics")){
                                            Toast.makeText(getApplicationContext(),"Registration completed",Toast.LENGTH_SHORT).show();

                                            startActivity(new Intent(getApplicationContext(), Selection_Activity.class));
                                            finish();

                                        }else if(Coursetxt.equals("Land Economics")){
                                            Toast.makeText(getApplicationContext(),"Registration completed wait for feature",Toast.LENGTH_SHORT).show();
                                            startActivity(new Intent(getApplicationContext(), LandEconomics.class));
                                            finish();


                                        }
                                        else if(Coursetxt.equals("Land Surveying and Information Systems")){
                                            Toast.makeText(getApplicationContext(),"Registration completed wait for feature",Toast.LENGTH_SHORT).show();
                                            startActivity(new Intent(getApplicationContext(), LSActivity.class));
                                            finish();

                                        }




                    }else{
                        Toast.makeText(getApplicationContext(),"Failure occurred",Toast.LENGTH_SHORT).show();

                            }
                            }
                        });


                    }else{
                            Toast.makeText(getApplicationContext(),"Registration failed",Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);

                        }
                    }
                });

    }


}