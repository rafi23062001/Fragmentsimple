/*
 * Copyright (C) 2017 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.fragmentexample;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements SimpleFragmentOne.OnCheckradioButtonlistener{

    private Button mButton;
    private boolean isFragmentDisplayed = false;
    private int pilihanku = 2;

    static final String STATE_FRAGMENT ="state_of_fragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButton=findViewById(R.id.open_btn);

        if (savedInstanceState !=null){
            isFragmentDisplayed=savedInstanceState.getBoolean(STATE_FRAGMENT);
            if (isFragmentDisplayed){
                mButton.setText(R.string.close);
            }
        }

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isFragmentDisplayed){
                    displayFragment();
                } else {
                    closeFragment();
                }
            }
        });

    }
    public void displayFragment(){
        SimpleFragmentOne simpleFragmentOne=SimpleFragmentOne.newInstance(pilihanku);
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container,simpleFragmentOne).addToBackStack(null).commit();
        mButton.setText(R.string.close);
        isFragmentDisplayed = true;
    }

    public void closeFragment(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        SimpleFragmentOne simpleFragmentOne = (SimpleFragmentOne)fragmentManager.findFragmentById(R.id.fragment_container);
        if (simpleFragmentOne !=null){
            FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
            fragmentTransaction.remove(simpleFragmentOne).commit();
        }
        mButton.setText(R.string.open);
        isFragmentDisplayed=false;
    }
    public void onSaveInstanceState(Bundle savedInstanceState){
        savedInstanceState.putBoolean(STATE_FRAGMENT, isFragmentDisplayed);
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onCheck(int pilihan) {
        this.pilihanku=pilihan;
    }
}
