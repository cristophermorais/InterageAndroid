package com.interage.app.interage;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashMap;
import java.util.Map;

public class MainFragment extends Fragment implements GeralFragment.OnFragmentInteractionListener, EventsFragment.OnFragmentInteractionListener, SettingsFragment.OnFragmentInteractionListener {

    private OnFragmentInteractionListener mListener;

    private static final Map<Integer, Fragment> menuMap = new HashMap<>();
    private BottomNavigationView bottomNavigationView;
    private static int selectedId = R.id.navigation_geral;

    static {
        menuMap.put(R.id.navigation_geral, GeralFragment.newInstance());
        menuMap.put(R.id.navigation_eventos, EventsFragment.newInstance());
        menuMap.put(R.id.navigation_settings, SettingsFragment.newInstance());
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            if(menuMap.containsKey(item.getItemId())) {
                return switchFragments(item.getItemId());
            }
            return false;
        }

    };

    public MainFragment() {

    }

    public static MainFragment newInstance() {

        MainFragment mainFragment = new MainFragment();
        Bundle args = new Bundle();
        mainFragment.setArguments(args);

        return mainFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        switchFragments(R.id.navigation_geral);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        bottomNavigationView = (BottomNavigationView) getView().findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        switchFragments(R.id.navigation_geral);
    }

    private boolean switchFragments(int id) {
        if( id != selectedId) {
            FragmentManager fm = getActivity().getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            Fragment f = menuMap.get(id);
            ft.replace(R.id.content_tab, f);
            ft.commit();
            selectedId = id;
        }
        return true;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        switchFragments(R.id.navigation_geral);
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()+ " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
