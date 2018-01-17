package kampus.myapplication;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

/**
 * Created by TanNT on 12/8/17.
 */

public class TempsData {

    private void doSomething(){
        StringBuilder builder = new StringBuilder();
        builder.append("World");
        builder.deleteCharAt(3);
        String str = " builder";
        builder.append(str).append(".");
        String strBuilder = builder.toString();
    }












    public void doExample(Context context){
        ArrayList<String> listString = new ArrayList<>();
        listString.add("Hello"); //=> It is ok
        //listString.add(1); // => It is not Ok
        String add1 = listString.get(0); //=> It is ok
        //int a = listString.get(0); // => It is not ok

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, R.layout.layout_item, listString);

        ArrayList<MEarthQuake> listEarthquakes = new ArrayList<>();
        ArrayAdapter<MEarthQuake> adapterEarthQuake = new ArrayAdapter<MEarthQuake>(context, R.layout.layout_item, listEarthquakes);
        MEarthQuake mEarthQuake = adapterEarthQuake.getItem(1); //=> It is ok
        //long a = adapterEarthQuake.getItem(1);

    }


}
