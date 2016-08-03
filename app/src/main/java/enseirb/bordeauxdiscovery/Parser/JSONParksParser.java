package enseirb.bordeauxdiscovery.Parser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;

import enseirb.bordeauxdiscovery.DTO.ParkDTO;
import enseirb.bordeauxdiscovery.DTO.Parks;

public class JSONParksParser implements IParksParser {
    private static final String PARK_DATA = "d";
    private static final String PARK_NAME = "nom_espace_entretien";
    private static final String PARK_TYPE = "type_de_gestion_differenciee";
    private static final String PARK_TYPOLOGY = "typologie_patrimoin";
    private static final String PARK_X_LONG = "x_long";
    private static final String PARK_Y_LAT = "y_lat";
    private static final String UNKNOWN = "Inconnu";

    @Override
    public Parks parseParks(String data) {

        ParkDTO parkDTO;
        List<ParkDTO> parksList = new LinkedList<>();

        try {
            JSONObject ParksJson = new JSONObject(data);
            JSONArray parksArray = ParksJson.getJSONArray(PARK_DATA);
            for (int i = 0; i < parksArray.length(); i++) {
                parkDTO = parsePark(parksArray.getJSONObject(i));
                if (parkDTO != null) {
                    parksList.add(parkDTO);
                }
            }
        } catch (JSONException je) {
            je.printStackTrace();
        }

        Parks parks = new Parks();
        parks.setParks(parksList);

        return parks;
    }


    private ParkDTO parsePark(JSONObject park) {
        String name = UNKNOWN;
        String type = UNKNOWN;
        String typology = UNKNOWN;
        double x, y;

        try {
            if (park.has(PARK_NAME))
                name = park.getString(PARK_NAME);
            if (park.has(PARK_TYPE))
                type = park.getString(PARK_TYPE);
            if (park.has(PARK_TYPOLOGY))
                typology = park.getString(PARK_TYPOLOGY);
            x = park.getDouble(PARK_X_LONG);
            y = park.getDouble(PARK_Y_LAT);

            return getParkDTO(name, type, typology, x, y);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    private ParkDTO getParkDTO(String name, String type, String typology, double x, double y) {
        ParkDTO parkDTO = new ParkDTO();
        parkDTO.setName(name);
        parkDTO.setType(type);
        parkDTO.setTypology(typology);
        parkDTO.setLongitude(x);
        parkDTO.setLatitude(y);
        return parkDTO;
    }
}
