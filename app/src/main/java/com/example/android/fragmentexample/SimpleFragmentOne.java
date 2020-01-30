package com.example.android.fragmentexample;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;


public class SimpleFragmentOne extends Fragment {
    private static RadioGroup radioGroup;
    private static final int YES = 0;
    private static final int NO = 1;
    private static final int NONE =2;

    private int btnTerpilih=0;
    OnCheckradioButtonlistener listener;

    public SimpleFragmentOne() {
        // Required empty public constructor
    }

    public interface OnCheckradioButtonlistener{
        void onCheck(int pilihan);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment.
        final View rootView = inflater.inflate(R.layout.fragment_simple_fragment_one,
                container, false);
        radioGroup = rootView.findViewById(R.id.radio_group);

        radioGroup.setOnCheckedChangeListener(
                new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        View radioButton = radioGroup.findViewById(checkedId);
                        int index = radioGroup.indexOfChild(radioButton);
                        TextView textView =
                                rootView.findViewById(R.id.fragment_one);
                        switch (index) {
                            case YES:
                                textView.setText(R.string.yes_message);
                                break;
                            case NO:
                                textView.setText(R.string.no_message);
                                break;
                            default:
                                break;
                        }
                        listener.onCheck(index);
                    }
                });

        if (getArguments().containsKey("pilihanku")){
            btnTerpilih = getArguments().getInt("pilihanku");
            if (btnTerpilih!=NONE){
                radioGroup.check(radioGroup.getChildAt(btnTerpilih).getId());
            }
        }
        return rootView;
    }

    public static SimpleFragmentOne newInstance(int pilihanku) {
        SimpleFragmentOne simpleFragmentOne = new SimpleFragmentOne();

        Bundle arguments = new Bundle();
        arguments.putInt("pilihanku",pilihanku);

        simpleFragmentOne.setArguments(arguments);
        return simpleFragmentOne;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnCheckradioButtonlistener){
            listener=(OnCheckradioButtonlistener) context;
        }
        else {
            throw new ClassCastException(context.toString()+ "harus mengimplementasikan OncheckradioButton listener");
        }
    }

    public static void Checkradiokuya(){
        radioGroup.check(radioGroup.getChildAt(0).getId());
    }
    public static void CheckradiokuNo(){
        radioGroup.check(radioGroup.getChildAt(1).getId());
    }
    public static void CheckradiokuNone(){
        radioGroup.check(radioGroup.getChildAt(2).getId());
    }
}