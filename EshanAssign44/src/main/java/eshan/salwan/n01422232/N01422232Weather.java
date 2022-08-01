//Eshan Salwan, N01422232, RNB
package eshan.salwan.n01422232;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link N01422232Weather#newInstance} factory method to
 * create an instance of this fragment.
 */
public class N01422232Weather extends Fragment implements AdapterView.OnItemSelectedListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public N01422232Weather() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment N01422232Weather.
     */
    // TODO: Rename and change types and number of parameters
    public static N01422232Weather newInstance() {
        N01422232Weather fragment = new N01422232Weather();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    String[] cityNames={"Toronto","Tokyo","Moscow","Miami"};
    private TextView displayWeather;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.n01422232_weather, container, false);
        displayWeather = (TextView) view.findViewById(R.id.displayWeather);

        Spinner spin = (Spinner) view.findViewById(R.id.eshan_simpleSpinner);
        spin.setOnItemSelectedListener(this);

        ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, cityNames);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(arrayAdapter);
        return view;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (position == 0) {
            openWeather(43.6532, -79.3832);
        } else if (position == 1) {
            openWeather(35.6762, 139.6503);
        } else if (position == 2){
            openWeather(55.7558, 37.6173);
        } else {
            openWeather(25.7617, -80.1918);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {}

    private void openWeather(double latitude, double longitude) {
        String url = getString(R.string.Url);
        url += getString(R.string.urlLat) + latitude;
        url += getString(R.string.urlLon) + longitude;
        url += getString(R.string.urlKey);

        Log.d("URL",url);
        new ReadJSONFeedTask().execute(url);
    }

    private class ReadJSONFeedTask extends AsyncTask<String, Void, String> {

        protected void onPreExecute(){
            // anything to display prior to work such as display progressbar!
        }

        protected String doInBackground(String... urls) {
            URL url = null;
            HttpURLConnection httpURLConnection = null;
            StringBuilder bufferReader = null;
            try {
                url = new URL(urls[0]);
                bufferReader = new StringBuilder();
                httpURLConnection = (HttpURLConnection) url.openConnection();
                if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    InputStream content = new BufferedInputStream(httpURLConnection.getInputStream());
                    BufferedReader reader = new BufferedReader(new InputStreamReader(content));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        bufferReader.append(line);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                assert httpURLConnection != null;
                httpURLConnection.disconnect();
            }
            return bufferReader.toString();

        }
        protected void onPostExecute(String result) {
            try {
                //JSONArray jsonArray = new JSONArray(result);
                //Uncomment the two rows below to parse weather data from OpenWeatherMap
                JSONObject weatherJson = new JSONObject(result);
                JSONArray dataArray1= weatherJson.getJSONArray(getString(R.string.weatherJSON));
                String strResults=getString(R.string.weatherJSON) + "\n";

                JSONObject dataLatlon = weatherJson.getJSONObject(getString(R.string.coordJSON));
                strResults += getString(R.string.latJSON)+ ": " +dataLatlon.getString(getString(R.string.latJSON));
                strResults += "\n" +getString(R.string.lonJSON)+ ": " +dataLatlon.getString(getString(R.string.lonJSON));

                strResults += "\n"+getString(R.string.nameJSON)+ ": " +weatherJson.getString(getString(R.string.nameJSON));

                for (int i = 0; i < dataArray1.length(); i++) {
                    JSONObject jsonObject = dataArray1.getJSONObject(i);
                    strResults +="\n" +getString(R.string.descriptionJSON)+ ": " +jsonObject.getString(getString(R.string.descriptionJSON));
                }
                //
                JSONObject dataObject= weatherJson.getJSONObject(getString(R.string.mainJSON));
                strResults +="\n" +getString(R.string.humidityJSON)+ ": " +dataObject.getString(getString(R.string.humidityJSON));

                JSONObject dataCord= weatherJson.getJSONObject(getString(R.string.sysJSON));
                strResults +="\n" +getString(R.string.countryJSON)+ ": " +dataCord.getString(getString(R.string.countryJSON));

                displayWeather.setText(strResults);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}