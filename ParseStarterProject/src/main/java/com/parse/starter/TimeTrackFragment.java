package com.parse.starter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

import org.json.JSONArray;

import java.util.Date;

/**
 * Created by yinggao on 9/19/15.
 */
public class TimeTrackFragment extends Fragment {
    private ParseUser User = ParseUser.getCurrentUser();
    private Context context;
    private EditText mClassName;
    private Button mSetClassButton;
    private Button mStartButton;
    private Button mStopButton;
    private String mClass;
    public Date mStartDate;
    public Date mStopDate;

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
        View v = inflater.inflate(R.layout.clicker_layout, container, false);

        final Toast blanktoast = Toast.makeText(context, "Please enter a class", Toast.LENGTH_SHORT);

        mSetClassButton = (Button) v.findViewById(R.id.set_class_button);
        mSetClassButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String className = mClassName.getText().toString();
                if (className == null) {
                    blanktoast.show();
                    return;
                } else {
                    mClass = className;
                    mStartButton.setEnabled(true);
                    mStopButton.setEnabled(true);
                }

            }
        });

        mStartButton = (Button) v.findViewById(R.id.start_button);
        mStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mClass == null) {
                    blanktoast.show();
                    return;
                } else {
                    mStartDate = new Date();
                    mStartButton.setEnabled(false);
                    mStopButton.setEnabled(true);
                }

            }
        });

        mStopButton = (Button) v.findViewById(R.id.stop_button);
        mStopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mClass == null) {
                    blanktoast.show();
                    return;
                } else {
                    mStopDate = new Date();
                    mStopButton.setEnabled(false);
                    mStartButton.setEnabled(true);

                    JSONArray classes = User.getJSONArray("classes");
                }

            }
        });

        return v;
    }


}
