//Eshan Salwan, N01422232, RNB
package eshan.salwan.n01422232;

import static eshan.salwan.n01422232.EshanHome.FILE_NAME;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EshanFileContent#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EshanFileContent extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public EshanFileContent() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment EshanFileContent.
     */
    // TODO: Rename and change types and number of parameters
    public static EshanFileContent newInstance() {
        EshanFileContent fragment = new EshanFileContent();
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
    Activity activity;
    TextView textView;
    Button btnShow, btnDelete;
    File file;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.eshan_file, container, false);
    textView = (TextView) view.findViewById(R.id.fileContent);

    btnShow = (Button) view.findViewById(R.id.btnShow);

    btnShow.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            readFile(activity);
        }
    });

    btnDelete = (Button) view.findViewById(R.id.btnDelete);
    btnDelete.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            deleteFile(activity);
        }
    });
        return view;
    }

    private void readFile(Context context) {
        try {
            FileInputStream fileInputStream;

                fileInputStream = context.openFileInput(FILE_NAME);


            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, Charset.forName("UTF-8"));
            List<String> lines = new ArrayList<String>();
            BufferedReader reader = new BufferedReader(inputStreamReader);

            String line = reader.readLine();
                while (line != null) {
                    lines.add(line);
                    line = reader.readLine();
                }

            if (line == null){
                textView.setText("No Content");
            }else {
                textView.setText(TextUtils.join("\n", lines));
            }

            Toast.makeText(context, String.format("Read from file %s successful", FILE_NAME), Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, String.format("Read from file %s failed", FILE_NAME), Toast.LENGTH_SHORT).show();
            textView.setText("No Content");

        }
    }

    private void deleteFile(Context context) {
        file = new File(context.getFilesDir(), FILE_NAME);

        if (file.exists()) {
            file.delete();
            textView.setText("File Deleted");
            Toast.makeText(context, String.format("File %s has been deleted", FILE_NAME), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, String.format("File %s doesn't exist", FILE_NAME), Toast.LENGTH_SHORT).show();
        }
    }
}