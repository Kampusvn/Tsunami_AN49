package kampus.myapplication;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by TanNT on 12/5/17.
 */

public class ListAdapter extends BaseAdapter {
    private List<MEarthQuake> listItems;

    public void setListItems(List<MEarthQuake> listItems) {
        this.listItems = listItems;
    }

    @Override
    public int getCount() {
        return listItems.size();
    }

    @Override
    public Object getItem(int i) {
        return listItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View convertView = view;
        if (convertView == null){
            convertView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_item, viewGroup, false);
        }
        TextView mag = convertView.findViewById(R.id.mag);
        LinearLayout mainLayout = convertView.findViewById(R.id.mainLayout);
        TextView tsunami = convertView.findViewById(R.id.tsunami);

        MEarthQuake currentEarthQuake = listItems.get(i);
        mag.setText(String.valueOf(currentEarthQuake.getMag()));
        mainLayout.setBackgroundColor(currentEarthQuake.getTsunami() == 1 ? Color.RED : Color.WHITE);
        tsunami.setText(currentEarthQuake.getTsunami() == 1 ? "Warning Tsunami" : "None Tsunami" );
        return convertView;
    }
}
