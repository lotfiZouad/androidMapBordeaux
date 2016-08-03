package enseirb.bordeauxdiscovery.model;

import android.os.Parcel;
import android.os.Parcelable;


public class Playground implements IGeographicModel {
    private String name;
    private String minAge;
    private String maxAge;
    private String numberOfGames;
    private double x_long;
    private double y_lat;

    public Playground(){}

    protected Playground(Parcel in) {
        name = in.readString();
        minAge = in.readString();
        maxAge = in.readString();
        numberOfGames = in.readString();
        x_long = in.readDouble();
        y_lat = in.readDouble();
    }

    public static final Creator<Playground> CREATOR = new Creator<Playground>() {
        @Override
        public Playground createFromParcel(Parcel in) {
            return new Playground(in);
        }

        @Override
        public Playground[] newArray(int size) {
            return new Playground[size];
        }
    };

    public void setMinAge(String minAge) {
        this.minAge = minAge;
    }

    public void setMaxAge(String maxAge) {
        this.maxAge = maxAge;
    }

    public void setNumberOfGames(String numberOfGames) {
        this.numberOfGames = numberOfGames;
    }

    public void setX_long(double x_long) {
        this.x_long = x_long;
    }

    public void setY_lat(double y_lat) {
        this.y_lat = y_lat;
    }

    public void setName(String name) {
        this.name = name;
    }



    @Override
    public double getLatitude() {
        return y_lat;
    }

    @Override
    public double getLongitude() {
        return x_long;
    }

    @Override
    public String getDescription() {
        return "Playground's name: " + name + '\n' +
                "\tNumber of games: " + numberOfGames + '\n' +
                "\tMax Age: " + maxAge + '\n' +
                "\tMin Age: " + minAge  ;
    }

    @Override
    public String getName() {
        return name;
    }




    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(minAge);
        dest.writeString(maxAge);
        dest.writeString(numberOfGames);
        dest.writeDouble(x_long);
        dest.writeDouble(y_lat);
    }
}
