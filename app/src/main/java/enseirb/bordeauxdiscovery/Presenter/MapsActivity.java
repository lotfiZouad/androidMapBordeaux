package enseirb.bordeauxdiscovery.Presenter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.clustering.view.DefaultClusterRenderer;
import com.google.maps.android.ui.IconGenerator;

import java.util.ArrayList;
import java.util.List;

import enseirb.bordeauxdiscovery.Business.GeographicBusiness;
import enseirb.bordeauxdiscovery.Exception.InvalidResultException;
import enseirb.bordeauxdiscovery.R;
import enseirb.bordeauxdiscovery.model.IGeographicModel;
import enseirb.bordeauxdiscovery.model.IGeographicSets;


public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback, ListDetailsFragment.OnDetailClicked {

    public static final String EXTRA_APPLICATION = "list_data";
    public static final String LAT_VALUE = "lat";
    public static final String LNG_VALUE = "lng";
    public static final String NAME = "name";
    public static final String DATA_LIST = "data";
    public static final String INFORMATION_TYPE = "InformationType";
    public static final String INFORMATION_TYPEID = "InformationTypeId";
    public static final String DEFAULT_TYPE = "";
    public static final int RESULT_CODE = 102;
    public static final int DEFAULT_TYPEID = 0;
    public static final String STRING_EMPTY = "";
    public static final String STRING_ESPACE = " ";
    public static final String SELECTED = " Selected";
    public static final double LATITUDE_CENTRE = 44.8404400;
    public static final double LONGITUDE_CENTRE = -0.5805000;
    public static final int ZOOM_CENTRE = 11;
    public static final int ZOOM = 16;

    private List<IGeographicModel> data_list = new ArrayList<>();
    private DrawerLayout drawerLayout;
    private GoogleMap mMap;
    private Toolbar toolbar;
    private NavigationView navigationView;
    private Double lat_value;
    private Double lng_value;
    private String name = STRING_EMPTY;
    private String type = STRING_EMPTY;
    private int typeId = 0;
    private LatLng place;
    private ClusterManager<MyItem> mClusterManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadInformationType();
        setContentView(R.layout.activity_maps);
        Bundle extras = getIntent().getExtras();
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        setupToolbar();
        setupNavigationView();
        OnPressButtonDetails();
        if ((extras == null) && ((typeId == R.id.id_parks) || (typeId == R.id.id_wc) || (typeId == R.id.id_play) || (typeId == R.id.id_internet))) {
            displayInformations();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_CODE) {
            Bundle extras = data.getExtras();
            if (extras != null) {
                lat_value = extras.getDouble(LAT_VALUE);
                lng_value = extras.getDouble(LNG_VALUE);
                name = extras.getString(NAME);
                System.out.println(lat_value + STRING_ESPACE + lng_value + STRING_ESPACE + name);
            }
            setUpMap();
        }
    }


    private void setupNavigationView() {

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.NavigationApp);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                menuItem.setChecked(true);
                setTitle(menuItem.getTitle());
                drawerLayout.closeDrawers();
                type = menuItem.getTitle().toString();
                typeId = menuItem.getItemId();
                if (typeId != R.id.id_exit) {
                    saveInformationType();
                    mMap.clear();
                }
                return displayInformations();

            }
        });

    }

    private boolean displayInformations() {
        if (ServiceLocator.contains(typeId)) {
            String type = ServiceLocator.getType(typeId);
            Toast.makeText(getApplicationContext(), type + SELECTED, Toast.LENGTH_SHORT).show();
            retrieveData(type);
        } else if (typeId == R.id.id_exit) {
            onBackPressed();
        }
        return true;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public void setUpMap() {

        if ((lat_value == null) || (lng_value == null)) {
            mClusterManager = new ClusterManager<>(this, mMap);
            mMap.setOnCameraChangeListener(mClusterManager);
            mMap.setOnMarkerClickListener(mClusterManager);
            mClusterManager.setRenderer(new MyClusterRenderer(this, mMap,
                    mClusterManager));
            mMap.setInfoWindowAdapter(mClusterManager.getMarkerManager());
            mMap.setOnInfoWindowClickListener(mClusterManager);

            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(LATITUDE_CENTRE, LONGITUDE_CENTRE), ZOOM_CENTRE));
        } else {
            place = new LatLng(lat_value, lng_value);
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(place, ZOOM));
            mMap.addMarker(new MarkerOptions().position(place).title(name).icon(BitmapDescriptorFactory.fromResource(R.drawable.play)));

        }
        lat_value = null;
        lng_value = null;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        setUpMap();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setHomeAsUpIndicator(R.mipmap.ic_menu);
            ab.setDisplayHomeAsUpEnabled(true);
        }

    }

    public void OnPressButtonDetails() {
        Button button_details = (Button) findViewById(R.id.id_details);
        if (button_details != null) {
            button_details.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final Intent i = new Intent(MapsActivity.this, ListDetailsActivity.class);
                    i.putExtra(EXTRA_APPLICATION, type);
                    i.putParcelableArrayListExtra(MapsActivity.DATA_LIST, (ArrayList<? extends Parcelable>) data_list);
                    startActivityForResult(i, RESULT_CODE);
                }
            });
        }
    }

    private void addItems() {

        double lat;
        double lng;
        ListDetailsFragment listDetailsFragment = (ListDetailsFragment) getFragmentManager().findFragmentById(R.id.detail_fragment);
        if (listDetailsFragment != null)
            listDetailsFragment.setListData(data_list);


        for (int i = 0; i < data_list.size(); i++) {
            lat = data_list.get(i).getLatitude();
            lng = data_list.get(i).getLongitude();
            MyItem offsetItem = new MyItem(lat, lng);
            mClusterManager.addItem(offsetItem);
        }
    }

    @Override
    public void updatePosition(String Name, double latitude, double longitude) {
        lat_value = latitude;
        lng_value = longitude;
        name = Name;
        setUpMap();
    }

    public class MyClusterRenderer extends DefaultClusterRenderer<MyItem> {

        public static final int CLUSTER_MAXSIZE = 10;
        public static final int LEFT = 40;
        public static final int TOP = 20;
        public static final int RIGHT = 0;
        public static final int BOTTOM = 0;
        public static final int LEFT_MAX = 30;
        public static final int TOP1 = 20;
        private final IconGenerator mClusterIconGenerator = new IconGenerator(getApplicationContext());

        public MyClusterRenderer(Context context, GoogleMap map,
                                 ClusterManager<MyItem> clusterManager) {
            super(context, map, clusterManager);
        }

        @Override
        protected void onBeforeClusterItemRendered(MyItem item,
                                                   MarkerOptions markerOptions) {
            BitmapDescriptor markerDescriptor = BitmapDescriptorFactory.fromResource(R.mipmap.pinmarker);
            markerOptions.icon(markerDescriptor);
        }

        @Override
        protected void onClusterItemRendered(MyItem clusterItem, Marker marker) {
            super.onClusterItemRendered(clusterItem, marker);
        }

        @Override
        protected void onBeforeClusterRendered(Cluster<MyItem> cluster, MarkerOptions markerOptions) {
            final Drawable clusterIcon = getResources().getDrawable(R.drawable.cluster);
            clusterIcon.setColorFilter(getResources().getColor(android.R.color.holo_red_dark), PorterDuff.Mode.SRC_ATOP);

            mClusterIconGenerator.setBackground(clusterIcon);
            mClusterIconGenerator.setTextAppearance(R.style.iconGenText);
            if (cluster.getSize() < CLUSTER_MAXSIZE) {
                mClusterIconGenerator.setContentPadding(LEFT, TOP, RIGHT, BOTTOM);
            } else {
                mClusterIconGenerator.setContentPadding(LEFT_MAX, TOP, RIGHT, BOTTOM);
            }
            Bitmap icon = mClusterIconGenerator.makeIcon(String.valueOf(cluster.getSize()));
            markerOptions.icon(BitmapDescriptorFactory.fromBitmap(icon));
        }
    }

    public void retrieveData(String type) {
        DataTask task = new DataTask();
        task.execute(type);
    }

    class DataTask extends AsyncTask<String, Void, List<IGeographicModel>> {

        List<IGeographicModel> str;

        protected List<IGeographicModel> doInBackground(String... arg0) {
            String type = arg0[0];
            GeographicBusiness business = GeographicBusiness.getInstance();
            IGeographicSets set;
            try {
                set = business.nearestPointsReflection(LATITUDE_CENTRE, LONGITUDE_CENTRE, type);
                str = set.getAllAsList();
            } catch (InvalidResultException e) {
                e.printStackTrace();
                str = null;
            }

            return str;
        }

        protected void onPostExecute(List<IGeographicModel> app) {
            super.onPostExecute(app);
            if (app == null) {
                displayError();
                return;
            }
            data_list = app;
            setUpMap();
            addItems();
        }

    }

    private void saveInformationType() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(INFORMATION_TYPEID, typeId);
        editor.putString(INFORMATION_TYPE, type);
        editor.apply();
    }

    private void loadInformationType() {
        SharedPreferences Preferences = PreferenceManager.getDefaultSharedPreferences(this);
        typeId = Preferences.getInt(INFORMATION_TYPEID, DEFAULT_TYPEID);
        type = Preferences.getString(INFORMATION_TYPE, DEFAULT_TYPE);
        setTitle(type);
    }

    public void displayError() {
        new AlertDialog.Builder(this)
                .setMessage("An error occurred. Check your Internet connection and try Again")
                .setTitle("Error")
                .show();
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        MapsActivity.this.finish();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }


}
