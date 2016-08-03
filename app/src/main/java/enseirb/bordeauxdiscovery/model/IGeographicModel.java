package enseirb.bordeauxdiscovery.model;

import android.os.Parcelable;

public interface IGeographicModel extends Parcelable {
    double getLatitude();
    double getLongitude();
    // s'il y a une string de description
    String getDescription();
    String getName();

}
