package enseirb.bordeauxdiscovery.DTO;

public class ParkDTO {

    private String name;
    private String type;
    private String typology;
    private double longitude;
    private double latitude;

    public String getName(){
        return this.name;
    }

    public  void setName(String name){
        this.name = name;
    }

    public String getType(){
        return this.type;
    }

    public  void setType(String type){
        this.type = type;
    }

    public String getTypology(){
        return this.typology;
    }

    public void setTypology(String typology){
        this.typology = typology;
    }

    public double getLongitude(){
        return this.longitude;
    }

    public void setLongitude(double longitude){
        this.longitude = longitude;
    }

    public double getLatitude(){
        return this.latitude;
    }

    public void setLatitude(double latitude){
        this.latitude = latitude;
    }

}
