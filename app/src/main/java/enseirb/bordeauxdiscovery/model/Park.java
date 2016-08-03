package enseirb.bordeauxdiscovery.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Park implements IGeographicModel{

    private double xCoord;
    private double yCoord;
    private String name;
    private String type;
    private String typology;

    public Park(){}

    protected Park(Parcel in) {
        xCoord = in.readDouble();
        yCoord = in.readDouble();
        name = in.readString();
        type = in.readString();
        typology = in.readString();
    }

    public static final Creator<Park> CREATOR = new Creator<Park>() {
        @Override
        public Park createFromParcel(Parcel in) {
            return new Park(in);
        }

        @Override
        public Park[] newArray(int size) {
            return new Park[size];
        }
    };

    public void setXCoord(double xCoord){
        this.xCoord = xCoord;
    }
    public void setYCoord(double yCoord){
        this.yCoord = yCoord;
    }

    public void setName(String name){
        this.name = name;
    }
    public void setType(String type){
       this.type = type;
    }
    public void setTypology(String typology){
        this.typology = typology;
    }




    @Override
    public double getLatitude() {
        return yCoord;
    }

    @Override
    public double getLongitude() {
        return xCoord;
    }

    @Override
    public String getDescription() {
        return "Park's name: " + name + '\n' +
                "\tType: " + type + '\n' +
                "\tTypology: " + typology;
    }

    public String getName() {
        return name;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(xCoord);
        dest.writeDouble(yCoord);
        dest.writeString(name);
        dest.writeString(type);
        dest.writeString(typology);
    }
}
