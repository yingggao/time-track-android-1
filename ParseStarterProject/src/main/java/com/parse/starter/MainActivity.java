/*
 * Copyright (c) 2015-present, Parse, LLC.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */
package com.parse.starter;

import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.parse.ParseAnalytics;


public class MainActivity extends FragmentActivity {

  //Fragment signupFragment;
    //private static MainActivity mMainActivity;
    SignupFragment newSignupFragment = new SignupFragment();
    //ClassListFragment newClassListFragment = new ClassListFragment();

//    public static MainActivity getInstance() {
//        if (mMainActivity == null) {
//            mMainActivity = new MainActivity();
//        }
//        return mMainActivity;
//    }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ParseAnalytics.trackAppOpenedInBackground(getIntent());

    FragmentManager fm = getSupportFragmentManager();
    //signupFragment = fm.findFragmentByTag("SignupFragment");
    //if (signupFragment == null) {
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.main_activity_layout,newSignupFragment,"SignupFragment");
        ft.commit();
    //}

  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

//  public void changeFragment(String fragmentTag) {
//      FragmentManager fm = getSupportFragmentManager();
//      Fragment old = fm.findFragmentByTag(fragmentTag);
//      //signupFragment = fm.findFragmentByTag("SignupFragment");
//      //if (signupFragment == null) {
//      FragmentTransaction ft = fm.beginTransaction();
//      ft.add(R.id.main_activity_layout,old,fragmentTag);
//      ft.commit();
//    };
}
