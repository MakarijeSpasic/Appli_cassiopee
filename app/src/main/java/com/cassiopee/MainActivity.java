package com.cassiopee;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    EditText etName,etLastName;
    Button button;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.editTextPersonName);
        etLastName = findViewById(R.id.editTextPersonLastName);
        button = findViewById(R.id.buttonbegin);
        DB = new DBHelper(this);

        button.setOnClickListener(new View.OnClickListener(){
            String patientName = etName.getText().toString();
            String patientLastName = etLastName.getText().toString();
            public void onClick(View v){
                if (DB.isInDB(patientName,patientLastName)==true) {
                    //on crée le fichier dans le dossier nom_prenom
                } else {
                    //on insère le patient dans la base de données
                    DB.insertPatient(patientName,patientLastName);
                    //on crée le dossier nom_prénom puis le fichier dessin associé dedans
                    String FolderName= patientName + patientLastName;
                    createDirectory(FolderName);
                }
            }
        });
    }

    private void createDirectory(String folderName) {
        File file = new File(Environment.getDataDirectory(),folderName);
        if (!file.exists()) {
            file.mkdir();
            Toast.makeText(MainActivity.this, "Successful", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(MainActivity.this, "This folder already exists", Toast.LENGTH_SHORT).show();
        }

    }
}