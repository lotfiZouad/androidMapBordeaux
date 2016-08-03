package enseirb.bordeauxdiscovery.Presenter;

import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import enseirb.bordeauxdiscovery.R;
import enseirb.bordeauxdiscovery.model.IGeographicModel;

public class ListDetailsFragment extends ListFragment {

    public static final String AUCUN_POINT = "Aucun point";

    List<IGeographicModel> list_data;
    OnDetailClicked onDetailClickedListener;
    private Context context;

    public interface OnDetailClicked {
        void updatePosition(String name,double latitude,double longitude);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.context=inflater.getContext();
        onDetailClickedListener = (OnDetailClicked) context;

        ListAdapter adapter=new ListAdapter(context,R.layout.list_app_item, new ArrayList<String>(Arrays.asList(AUCUN_POINT)));
        adapter.notifyDataSetChanged();

        setListAdapter(adapter);
        return super.onCreateView(inflater,container,savedInstanceState);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public void initializeDetailsList() {

        List<String> list=new ArrayList<String>();
        for (int i = 0; i < list_data.size(); i++) {
            list.add(list_data.get(i).getName());
        }
        ListAdapter adapter=new ListAdapter(context,R.layout.list_app_item, (ArrayList<String>) list);
        adapter.notifyDataSetChanged();

        setListAdapter(adapter);
    }

    public void setListData(List<IGeographicModel> data){
        this.list_data = data;
        initializeDetailsList();

    }

    public void onListItemClick(ListView l, View v, int position, long id) {
        onDetailClickedListener.updatePosition(list_data.get(position).getName(), list_data.get(position).getLatitude(), list_data.get(position).getLongitude());
    }


}
