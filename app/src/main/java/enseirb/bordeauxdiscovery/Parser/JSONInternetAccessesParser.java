package enseirb.bordeauxdiscovery.Parser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;

import enseirb.bordeauxdiscovery.DTO.InternetAccessDTO;
import enseirb.bordeauxdiscovery.DTO.InternetAccesses;


public class JSONInternetAccessesParser implements IInternetAccessesParser {
    private static final String INTERNETACCESS_DATA = "d";
    private static final String INTERNETACCESS_NAME = "nom";
    private static final String INTERNETACCESS_OPERATOR = "operateur";
    private static final String INTERNETACCESS_PAYABLE = "payant";
    private static final String INTERNETACCESS_SITUATION = "situation";
    private static final String INTERNETACCESS_TYPE_PUBLIC = "type_public";
    private static final String INTERNETACCESS_TYPE_ACCESS = "type_acces";
    private static final String INTERNETACCESS_X_LONG = "x_long";
    private static final String INTERNETACCESS_Y_LAT = "y_lat";
    private static final String UNKNOWN = "Inconnu";

    public InternetAccesses parseInternetAccesses(String data) {

        InternetAccessDTO internetAccessDTO;
        List<InternetAccessDTO> internetAccessesList = new LinkedList<>();

        try {
            JSONObject jsonResponse = new JSONObject(data);
            JSONArray jsonInternetAccesses = jsonResponse.getJSONArray(INTERNETACCESS_DATA);
            for (int i = 0; i < jsonInternetAccesses.length(); i++) {
                internetAccessDTO = parseInternetAccess(jsonInternetAccesses.getJSONObject(i));
                if (internetAccessDTO != null) {
                    internetAccessesList.add(internetAccessDTO);
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        InternetAccesses internetAccesses = new InternetAccesses();
        internetAccesses.setInternetAccesses(internetAccessesList);

        return internetAccesses;
    }

    private InternetAccessDTO parseInternetAccess(JSONObject internetAccess) {
        String name = UNKNOWN;
        String operator = UNKNOWN;
        String payable = UNKNOWN;
        String situation = UNKNOWN;
        String publicType = UNKNOWN;
        String accessType = UNKNOWN;
        double x, y;

        try {
            x = internetAccess.getDouble(INTERNETACCESS_X_LONG);
            y = internetAccess.getDouble(INTERNETACCESS_Y_LAT);
            if (internetAccess.has(INTERNETACCESS_NAME))
                name = internetAccess.getString(INTERNETACCESS_NAME);
            if (internetAccess.has(INTERNETACCESS_SITUATION))
                situation = internetAccess.getString(INTERNETACCESS_SITUATION);
            if (internetAccess.has(INTERNETACCESS_TYPE_PUBLIC))
                publicType = internetAccess.getString(INTERNETACCESS_TYPE_PUBLIC);
            if (internetAccess.has(INTERNETACCESS_OPERATOR))
                operator = internetAccess.getString(INTERNETACCESS_OPERATOR);
            if (internetAccess.has(INTERNETACCESS_PAYABLE))
                payable = internetAccess.getString(INTERNETACCESS_PAYABLE);
            if (internetAccess.has(INTERNETACCESS_TYPE_ACCESS))
                accessType = internetAccess.getString(INTERNETACCESS_TYPE_ACCESS);

            return getInternetAccess(x, y, name, situation, publicType, operator, payable, accessType);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    private InternetAccessDTO getInternetAccess(double x, double y, String name, String situation, String publicType, String operator, String payable, String accessType) {
        InternetAccessDTO internetAccess = new InternetAccessDTO();
        internetAccess.setName(name);
        internetAccess.setSituation(situation);
        internetAccess.setPublicType(publicType);
        internetAccess.setOperator(operator);
        internetAccess.setPayable(payable);
        internetAccess.setAccessType(accessType);
        internetAccess.setLongitude(x);
        internetAccess.setLatitude(y);
        return internetAccess;
    }

}
