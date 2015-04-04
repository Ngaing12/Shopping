package com.OOD.malissa.shoopingcart.Activities;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.OOD.malissa.shoopingcart.Activities.HelperClasses.User;


import com.OOD.malissa.shoopingcart.Controllers.StoreClerk;
import com.OOD.malissa.shoopingcart.R;

import java.util.ArrayList;

/**
 * This is the Login Activity
 */
public class Login extends Activity {

    public static TextView logInFail;

    //region INSTANCE VARIABLES
    private CheckBox _checkBoxSeller;
    private Button _loginBtn;
    private EditText _userName;
    private EditText _password;
    private StoreClerk Clerk = StoreClerk.getInstance();
    private static Context context; // used to get the context of this activity. only use when onCreate of Activity has been called!

    private User _userType;
    private boolean _isSeller = false;
    private String _usernameString;
    private String _passwordString;
    //endregion


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Login.context = getApplicationContext();

        setUpListeners();
        setContentView(R.layout.login);
    }

    /**
     * onStart() is called after onCreate(). Used to initialize teh models
     */
    @Override
    protected void onStart() {
        super.onStart();

        //Initialize all models
        Clerk.initializeAllModel(context);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.login, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long0
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Function used to get the application's context. Only use if the application exists!
     * @return The context of this activity
     */
    public static Context getAppContext() {
        return Login.context;
    }

    private void setUpListeners(){

        //LINK UI OBJECTS TO XML HERE
        this._loginBtn = (Button)  findViewById(R.id.logInButton);
        this._password = (EditText) findViewById(R.id.passwordField);
        this._userName = (EditText) findViewById(R.id.usernameField);
        this._checkBoxSeller = (CheckBox)  findViewById(R.id.userTypeCheck);
        this.logInFail = (TextView) findViewById(R.id.logInFailText);

        /**
         * Setup the listener that takes the input from the
         * username edittext and places it into instance variable.
         */
        this._userName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                this._usernameString = this._userName.getText().toString();
            }

        });

        /**
         * Setup the listener that takes the input from the
         * password edittext and places it into instance variable.
         */
        this._password.setOnClickListener(new View.OnClickListener() {
           @Override
            public void onClick(View v) {
            this._passwordString = this._password.getText().toString();
           }

        });

        /**
         * Setup the listener that determines if the user is logging
         * in as a seller or not.
         */
        _checkBoxSeller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                this._isSeller = !this._isSeller;
            }
        });

        /**
         * This is the login listener where logging in calls a function
         * from storeclerk.
         */
        _loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //todo:  hey Paul, looking at this, it doesn't seem like we actually use _userType. I think this should be deleted
                if(_isSeller)
                    this._userType = User.SELLER;
                else
                    this._userType = User.BUYER;

           Clerk.login(_usernameString, _passwordString, _isSeller);

            }
        });

    }
}
