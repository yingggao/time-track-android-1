package com.parse.starter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import org.json.JSONArray;

import java.util.Date;

/**
 * Created by yinggao on 9/19/15.
 */
public class SignupFragment extends Fragment {

    public ParseUser user = new ParseUser();
    private Button mLoginButton;
    private Button mSignupButton;
    private EditText mLoginUsername;
    private EditText mSigninUsername;
    private EditText mLoginPassword;
    private EditText mSigninPassword;
    private Context context;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        context = this.getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.login_layout, container, false);

        mLoginUsername = (EditText) v.findViewById(R.id.enter_username_textfield);
        mLoginPassword = (EditText) v.findViewById(R.id.enter_password_textfield);
        mSigninUsername = (EditText) v.findViewById(R.id.create_username_textfield);
        mSigninPassword = (EditText) v.findViewById(R.id.create_password_textfield);
        final Toast errortoast = Toast.makeText(context, "Please Enter a valid username and password", Toast.LENGTH_SHORT);
        final Toast wrongtoast = Toast.makeText(context, "Username and Password do not match", Toast.LENGTH_SHORT);

        mLoginButton = (Button) v.findViewById(R.id.login_go_button);
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String loginUsername = mLoginUsername.getText().toString();
                String loginPassword = mLoginPassword.getText().toString();
                if (loginUsername == null || loginPassword == null) {
                    errortoast.show();
                    return;
                } else {
                    ParseUser.logInInBackground(loginUsername, loginPassword, new LogInCallback() {
                        public void done(ParseUser user, ParseException e) {
                            if (user != null) {
                                String classes = ParseUser.getCurrentUser().get("classes").toString();
                                System.out.println(classes);
                                // Hooray! The user is logged in.
                            } else {
                                wrongtoast.show();
                                // Signup failed. Look at the ParseException to see what happened.
                            }
                        }
                    });
                }

            }
        });

        mSignupButton = (Button) v.findViewById(R.id.signup_go_button);
        mSignupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String signinUsername = mSigninUsername.getText().toString();
                String signinPassword = mSigninPassword.getText().toString();
                if (signinUsername == null || signinPassword == null) {
                    errortoast.show();
                    return;
                } else {
                    user.setUsername(signinUsername);
                    user.setPassword(signinPassword);

                    user.put("email", signinUsername);

                    user.signUpInBackground(new SignUpCallback() {
                        public void done(ParseException e) {
                            if (e == null) {

                                //MainActivity.changeFragment("ClassListFragment");
                                // Hooray! Let them use the app now.
                            } else {
                                wrongtoast.show();
                                // Sign up didn't succeed. Look at the ParseException
                                // to figure out what went wrong
                            }
                        }
                    });
                }

            }
        });

        return v;
    }
}
