package com.example.csromero.a2335_final_project;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link KitchenControl.OnFragmentListItemSelectedListener} interface
 * to handle interaction events.
 * Use the {@link KitchenControl#newInstance} factory method to
 * create an instance of this fragment.
 */
public class KitchenControl extends Fragment {

    private OnFragmentListItemSelectedListener mListener;

    // ui elements
    private String[] arrayStrings = {"Microwave", "Fridge", "Light", "+"};
    private ListView kitchenList;
    private ArrayList<String> kitchenItemNameList = new ArrayList<>(Arrays.asList(arrayStrings));

    public KitchenControl() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment KitchenControl.
     */
    public static KitchenControl newInstance() {
        KitchenControl fragment = new KitchenControl();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_kitchen_control, container, false);

        // get the listView UI element from the view
        kitchenList = (ListView) view.findViewById(R.id.kitchen_list);

        // create an array adapter to connect the listview and the arraylist of items
        ArrayAdapter<String> kitchenAdapter =
                new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_list_item_1, kitchenItemNameList);

        kitchenList.setAdapter(kitchenAdapter);

        // creates and sets the on item click listener
        kitchenList.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = (String) kitchenList.getItemAtPosition(position);
                //Toast.makeText(this,"You selected : " + item,Toast.LENGTH_SHORT).show();
                if (mListener != null){
                    mListener.onFragmentListItemSelected(position);
                }
            }
        });
        return view;
    }

    /*
    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }
    */

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof OnFragmentListItemSelectedListener) {
            mListener = (OnFragmentListItemSelectedListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentListItemSelectedListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */

    /*
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
    */

    public interface OnFragmentListItemSelectedListener {
        public void onFragmentListItemSelected(int position);
    }
}
