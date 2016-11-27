package com.tutorials.hp.spinnermysql;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.tutorials.hp.spinnermysql.mModel.Spacecraft;
import com.tutorials.hp.spinnermysql.mMySQL.MySQLClient;

public class MainActivity extends AppCompatActivity {

    //INSTANCE FIELDS
    private TextInputEditText txtName,txtPropellant,txtDestination;
    private Spinner sp;
    private Button btnAdd,btnRetrieve;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //REFERENCE VIEWS
        this.initializeViews();

        //HANDLE EVENTS
        this.handleClickEvents();

    }

    /*
    REFERENCE VIEWS
     */
    private void initializeViews()
    {

        txtName= (TextInputEditText) findViewById(R.id.nameTxt);
        txtPropellant= (TextInputEditText) findViewById(R.id.propellantTxt);
        txtDestination= (TextInputEditText) findViewById(R.id.destinationTxt);

        btnAdd= (Button) findViewById(R.id.addBtn);
        btnRetrieve= (Button) findViewById(R.id.refreshBtn);

        sp= (Spinner) findViewById(R.id.sp);

    }

    /*
    HANDLE CLICK EVENTS
     */
    private void handleClickEvents()
    {
        //EVENTS : ADD
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //GET VALUES
                String name=txtName.getText().toString();
                String propellant=txtPropellant.getText().toString();
                String destination=txtDestination.getText().toString();

                //BASIC CLIENT SIDE VALIDATION
                if((name.length()<1 || propellant.length()<1 || destination.length()<1 ))
                {
                    Toast.makeText(MainActivity.this, "Please Enter all Fields", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    //SAVE

                    Spacecraft s=new Spacecraft();
                    s.setName(name);
                    s.setPropellant(propellant);
                    s.setDestination(destination);

                    new MySQLClient(MainActivity.this).add(s,txtName,txtPropellant,txtDestination);
                }
            }
        });

        //EVENTS : RETRIEVE
        btnRetrieve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new MySQLClient(MainActivity.this).retrieve(sp);

            }
        });

    }



}
