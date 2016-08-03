package enseirb.bordeauxdiscovery.DAO;

import enseirb.bordeauxdiscovery.Client.HttpRequest;
import enseirb.bordeauxdiscovery.DTO.Parks;
import enseirb.bordeauxdiscovery.Parser.IParksParser;
import enseirb.bordeauxdiscovery.Parser.JSONParksParser;

public class ParkDAO implements IParksDAO {
    private static final String URL = "http://odata.bordeaux.fr/v1/databordeaux/parcjardin/?format=json";

    @Override
    public Parks getParks() {
        IParksParser parser = new JSONParksParser();
        HttpRequest client = new HttpRequest();
        String data = client.request(URL);
        return parser.parseParks(data);
    }
}
