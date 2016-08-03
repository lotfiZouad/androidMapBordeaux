package enseirb.bordeauxdiscovery.Presenter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import enseirb.bordeauxdiscovery.R;


public class ListAdapter extends ArrayAdapter<String> {

    Context context;
    int layoutResourceId;
    ArrayList<String> data = null;

    public ListAdapter(Context context, int layoutResourceId, ArrayList<String> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ApplicationHolder holder;

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new ApplicationHolder();
            holder.imgIcon = (ImageView) row.findViewById(R.id.logo);
            holder.txtName = (TextView) row.findViewById(R.id.app_name);

            row.setTag(holder);
        } else {
            holder = (ApplicationHolder) row.getTag();
        }

        String app = data.get(position);
        holder.txtName.setText(app);
        holder.imgIcon.setImageResource(R.drawable.mark);

        return row;
    }

    static class ApplicationHolder {
        ImageView imgIcon;
        TextView txtName;

    }
}

