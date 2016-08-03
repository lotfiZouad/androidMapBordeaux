package enseirb.bordeauxdiscovery.model;


import android.os.Parcel;
import android.os.Parcelable;

public class Toilet implements IGeographicModel {

    private double longitude;
    private double latitude;
    private String address;
    private String options;
    private String typology;

    public Toilet(){}

    protected Toilet(Parcel in) {
        longitude = in.readDouble();
        latitude = in.readDouble();
        address = in.readString();
        options = in.readString();
        typology = in.readString();
    }

    public static final Creator<Toilet> CREATOR = new Creator<Toilet>() {
        @Override
        public Toilet createFromParcel(Parcel in) {
            return new Toilet(in);
        }

        @Override
        public Toilet[] newArray(int size) {
            return new Toilet[size];
        }
    };

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }


    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public void setOptions(String options) {
        this.options = options;
    }


    public void setTypology(String typology) {
        this.typology = typology;
    }

    @Override
    public double getLongitude() {
        return longitude;
    }

    @Override
    public String getDescription() {
        return "Address: " + address + '\n' +
                "\tOptions: " + options + '\n' +
                "\tTypology: " + typology ;
    }

    @Override
    public String getName() {
        return address;
    }

    @Override
    public double getLatitude() {
        return latitude;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(longitude);
        dest.writeDouble(latitude);
        dest.writeString(address);
        dest.writeString(options);
        dest.writeString(typology);
    }
}
