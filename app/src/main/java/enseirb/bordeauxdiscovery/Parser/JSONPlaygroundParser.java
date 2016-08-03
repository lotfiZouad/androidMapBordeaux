package enseirb.bordeauxdiscovery.Parser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;

import enseirb.bordeauxdiscovery.DTO.PlaygroundDTO;
import enseirb.bordeauxdiscovery.DTO.Playgrounds;


public class JSONPlaygroundParser implements IPlaygroundsParser {
    private static final String PLAYGROUND_DATA = "d";
    private static final String PLAYGROUND_NAME = "nom";
    private static final String PLAYGROUND_MIN_AGE = "age_min";
    private static final String PLAYGROUND_MAX_AGE = "age_max";
    private static final String PLAYGROUND_GAMES_NUMBER = "nombre_jeux";
    private static final String PLAYGROUND_X_LONG = "x_long";
    private static final String PLAYGROUND_Y_LAT = "y_lat";
    private static final String UNKNOWN = "inconnu";

    public Playgrounds parsePlaygrounds(String data) {

        PlaygroundDTO playgroundDTO;
        List<PlaygroundDTO> playgroundsList = new LinkedList<>();

        Playgrounds playgrounds = new Playgrounds();

        try {
            JSONObject obj = new JSONObject(data);
            JSONArray arrayOfPlaygrounds = obj.getJSONArray(PLAYGROUND_DATA);
            int length = arrayOfPlaygrounds.length();

            for (int i = 0; i < length; i++) {
                playgroundDTO = parsePlayground(arrayOfPlaygrounds.getJSONObject(i));
                if (playgroundDTO != null) {
                    playgroundsList.add(playgroundDTO);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        playgrounds.setPlaygrounds(playgroundsList);
        return playgrounds;
    }


    private PlaygroundDTO parsePlayground(JSONObject playground) {
        String name = UNKNOWN;
        String minAge = UNKNOWN;
        String maxAge = UNKNOWN;
        String numberOfGames = UNKNOWN;
        try {
            if (playground.has(PLAYGROUND_NAME))
                name = playground.getString(PLAYGROUND_NAME);
            if (playground.has(PLAYGROUND_MIN_AGE))
                minAge = playground.getString(PLAYGROUND_MIN_AGE);
            if (playground.has(PLAYGROUND_MAX_AGE))
                maxAge = playground.getString(PLAYGROUND_MAX_AGE);
            if (playground.has(PLAYGROUND_GAMES_NUMBER))
                numberOfGames = playground.getString(PLAYGROUND_GAMES_NUMBER);
            double x_long = playground.getDouble(PLAYGROUND_X_LONG);
            double y_lat = playground.getDouble(PLAYGROUND_Y_LAT);

            return getPlaygroundDTO(name, minAge, maxAge, numberOfGames, x_long, y_lat);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    private PlaygroundDTO getPlaygroundDTO(String name, String minAge, String maxAge, String numberOfGames, double x_long, double y_lat) {
        PlaygroundDTO playground = new PlaygroundDTO();
        playground.setName(name);
        playground.setMinAge(minAge);
        playground.setMaxAge(maxAge);
        playground.setNumberOfGames(numberOfGames);
        playground.setLongitude(x_long);
        playground.setLatitude(y_lat);
        return playground;
    }
}
