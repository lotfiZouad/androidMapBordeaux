package enseirb.bordeauxdiscovery.Parser;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;

import enseirb.bordeauxdiscovery.DTO.ToiletDTO;
import enseirb.bordeauxdiscovery.DTO.Toilets;

public class JSONToiletsParser implements IToiletsParser {
    private static final String TOILET_DATA = "d";
    private static final String TOILET_ADDRESS = "adresse";
    private static final String TOILET_OPTIONS = "options";
    private static final String TOILET_TYPOLOGY = "typology";
    private static final String TOILET_X_LONG = "x_long";
    private static final String TOILET_Y_LAT = "y_lat";
    private static final String UNKOWN = "Inconnu";

    public Toilets parseToilets(String data) {

        ToiletDTO toiletDTO;
        List<ToiletDTO> toiletsList = new LinkedList<>();

        try {
            JSONObject toiletsJson = new JSONObject(data);
            JSONArray toiletsArray = toiletsJson.getJSONArray(TOILET_DATA);
            int length = toiletsArray.length();
            for (int i = 0; i < length; i++) {
                toiletDTO = parseToilet(toiletsArray.getJSONObject(i));
                if (toiletDTO != null) {
                    toiletsList.add(toiletDTO);
                }
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
        Toilets toilets = new Toilets();
        toilets.setToilets(toiletsList);
        return toilets;
    }

    private ToiletDTO parseToilet(JSONObject toilet) {
        String address = UNKOWN;
        String options = UNKOWN;
        String typology = UNKOWN;
        double longitude, latitude;

        try {
            longitude = toilet.getDouble(TOILET_X_LONG);
            latitude = toilet.getDouble(TOILET_Y_LAT);
            if (toilet.has(TOILET_ADDRESS))
                address = toilet.getString(TOILET_ADDRESS);
            if (toilet.has(TOILET_OPTIONS))
                options = toilet.getString(TOILET_OPTIONS);
            if (toilet.has(TOILET_TYPOLOGY))
                typology = toilet.getString(TOILET_TYPOLOGY);
            return getToiletDTO(address, options, typology, latitude, longitude);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }


    private ToiletDTO getToiletDTO(String address, String options, String typology, double latitude, double longitude) {
        ToiletDTO toilet = new ToiletDTO();
        toilet.setAddress(address);
        toilet.setOptions(options);
        toilet.setTypology(typology);
        toilet.setLatitude(latitude);
        toilet.setLongitude(longitude);
        return toilet;
    }
}
