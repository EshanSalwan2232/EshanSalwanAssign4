package eshan.salwan.n01422232;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SalwanDownload#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SalwanDownload extends Fragment implements AdapterView.OnItemClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SalwanDownload() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment SalwanDownload.
     */
    // TODO: Rename and change types and number of parameters
    public static SalwanDownload newInstance() {
        SalwanDownload fragment = new SalwanDownload();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = getActivity();
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    String downloadList[] = {"Dogs", "Flowers", "Planets"};
    Activity activity;
    ListView productListView;
    ArrayAdapter<String> productListAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.salwan_download, container, false);

        productListView = (ListView) view.findViewById(R.id.listView);

        productListAdapter = new ArrayAdapter<String>(activity, R.layout.salwan_download, R.id.textView4, downloadList);
        productListView.setAdapter(productListAdapter);
        productListView.setOnItemClickListener(this);


        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {

        //Product product = (Product) parent.getItemAtPosition(position);
        Toast.makeText(activity, "you clicked on: " + downloadList[position], Toast.LENGTH_SHORT).show();

        if (position == 0){
            //code
        }else if(position == 1){
            //code
        }else {
            //code
        }
    }
}