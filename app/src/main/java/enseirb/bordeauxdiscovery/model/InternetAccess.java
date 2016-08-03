package enseirb.bordeauxdiscovery.model;


import android.os.Parcel;
import android.os.Parcelable;

public class InternetAccess implements IGeographicModel{

    private double longitude;
    private double latitude;
    private String name;
    private String situation;
    private String publicType;
    private String operator;
    private String payable;
    private String accessType;

    public InternetAccess(){}

    protected InternetAccess(Parcel in) {
        longitude = in.readDouble();
        latitude = in.readDouble();
        name = in.readString();
        situation = in.readString();
        publicType = in.readString();
        operator = in.readString();
        payable = in.readString();
        accessType = in.readString();
    }

    public static final Creator<InternetAccess> CREATOR = new Creator<InternetAccess>() {
        @Override
        public InternetAccess createFromParcel(Parcel in) {
            return new InternetAccess(in);
        }

        @Override
        public InternetAccess[] newArray(int size) {
            return new InternetAccess[size];
        }
    };

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSituation(String situation) {
        this.situation = situation;
    }

    public void setPublicType(String publicType) {
        this.publicType = publicType;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public void setPayable(String payable) {
        this.payable = payable;
    }

    public void setAccessType(String accessType) {
        this.accessType = accessType;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getLongitude() {
        return longitude;
    }

    @Override
    public String getDescription() {
        return "Name='" + name + '\n' +
                "\tSituation='" + situation + '\n' +
                "\tPublic Type='" + publicType + '\n' +
                "\tOperator='" + operator + '\n' +
                "\tPayable='" + payable + '\n' +
                "\tAccess Type='" + accessType ;
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
        dest.writeString(name);
        dest.writeString(situation);
        dest.writeString(publicType);
        dest.writeString(operator);
        dest.writeString(payable);
        dest.writeString(accessType);
    }
}
