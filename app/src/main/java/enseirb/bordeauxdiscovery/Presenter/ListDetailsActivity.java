package enseirb.bordeauxdiscovery.Presenter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import enseirb.bordeauxdiscovery.R;
import enseirb.bordeauxdiscovery.model.IGeographicModel;


public class ListDetailsActivity extends AppCompatActivity {
    Toolbar toolbar;
    List<IGeographicModel> list_data;
    Bundle extras;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_activity);

        extras=getIntent().getExtras();
        if(extras !=null)
            list_data=extras.getParcelableArrayList(MapsActivity.DATA_LIST);

        setupToolbar();
        initializeDetailsList();

 }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

    private void setupToolbar(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();
        if (ab != null) {
           ab.setDisplayHomeAsUpEnabled(true);
        }
    }

    public void initializeDetailsList(){
        ListView details_list=(ListView) findViewById(R.id.id_details_list);
        List<String> list=new ArrayList<String>();
        for(int i=0;i<list_data.size();i++){
            list.add(list_data.get(i).getName());
        }

        ListAdapter adapter=new ListAdapter(this,R.layout.list_app_item, (ArrayList<String>) list);
        details_list.setAdapter(adapter);
        details_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(ListDetailsActivity.this,MapsActivity.class);
                i.putExtra(MapsActivity.NAME,list_data.get(position).getName());
                i.putExtra(MapsActivity.LAT_VALUE,list_data.get(position).getLatitude());
                i.putExtra(MapsActivity.LNG_VALUE, list_data.get(position).getLongitude());
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                setResult(MapsActivity.RESULT_CODE, i);
                finish();
            }
        });

    }

}
