package eshan.salwan.n01422232;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.canvas.CanvasCompat;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EshanHome#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EshanHome extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public EshanHome() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment EshanHome.
     */
    // TODO: Rename and change types and number of parameters
    public static EshanHome newInstance() {
        EshanHome fragment = new EshanHome();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = getActivity();
        sharedPreference = new SharedPreference();
    }

    SharedPreference sharedPreference;
    Activity activity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.eshan_home, container, false);
        CurrentDate(view);

        Button btn = view.findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               CreateFile(v, activity);
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        sharedPreference.createPreference(activity);
    }

    Calendar calendar;
    SimpleDateFormat simpleDateFormat;
    String Date;
    TextView textView;

    private void CurrentDate(View view) {
        textView = view.findViewById(R.id.date);
        calendar = Calendar.getInstance();
        simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date = simpleDateFormat.format(calendar.getTime());
        textView.setText(Date);
    }

    private void CreateFile(View v, Context context) {
        EditText editText = v.findViewById(R.id.editText);

        try {
            File file = new File(context.getFilesDir(), "Eshan.txt");
                if (!file.exists()) {
                    file.createNewFile();
                }
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(editText.getText().toString().getBytes(Charset.forName("UTF-8")));
            Toast.makeText(context, "Write to %s successful", Toast.LENGTH_SHORT).show();
            editText.setText("");
        }catch (NullPointerException | IOException e) {
            e.printStackTrace();
            Toast.makeText(context, "Write to file %s failed",  Toast.LENGTH_SHORT).show();
        }
    }

}