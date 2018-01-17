package kampus.myapplication;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private String TAG = MainActivity.class.getSimpleName();

    private ListView listView;
    private ArrayList<MEarthQuake> listObject = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView);

        new EarthquakeAsynctask().execute();
    }

    private URL creatUrl(String stringUrl) throws MalformedURLException{
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    private class EarthquakeAsynctask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(MainActivity.this,"Json Data is downloading",Toast.LENGTH_LONG).show();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();
            // Making a request to url and getting response
            String TEMP1  = "https://earthquake.usgs.gov/fdsnws/event/" +
                    "1/query?format=geojson&starttime=2017-11-01&endtime=2017-12-02&minmagnitude=5&limit=10";
            String TEMP2 = "https://earthquake.usgs.gov/fdsnws/event/" +
                    "1/query?format=geojson&starttime=2017-11-01&endtime=2017-12-02&minmagnitude=6&limit=10";
            String url = TEMP1;
            String jsonStr = null;
            try {
                jsonStr = sh.makeServiceCall(creatUrl(url));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            if (!TextUtils.isEmpty(jsonStr)) {
                try {
                    JSONObject jsonMain = new JSONObject(jsonStr);
                    JSONArray featuresArray = jsonMain.getJSONArray("features");
                    for (int i = 0 ; i < featuresArray.length(); i ++) {
                        JSONObject propertiesObject = featuresArray.getJSONObject(i).getJSONObject("properties");
                        double mag = propertiesObject.getDouble("mag");
                        int tsunami = propertiesObject.getInt("tsunami");
                        MEarthQuake mEarthQuake = new MEarthQuake(mag, tsunami);
                        listObject.add(mEarthQuake);
                    }

                } catch (final JSONException e) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    });

                }

            } else {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG).show();
                    }
                });
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            ListAdapter adapter = new ListAdapter();
            adapter.setListItems(listObject);
            listView.setAdapter(adapter);
        }
    }
}
