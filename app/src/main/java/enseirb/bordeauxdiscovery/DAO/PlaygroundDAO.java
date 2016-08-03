package enseirb.bordeauxdiscovery.DAO;

import enseirb.bordeauxdiscovery.Client.HttpRequest;
import enseirb.bordeauxdiscovery.DTO.Playgrounds;
import enseirb.bordeauxdiscovery.Parser.IPlaygroundsParser;
import enseirb.bordeauxdiscovery.Parser.JSONPlaygroundParser;

public class PlaygroundDAO implements IPlaygroundDAO {
    private static final String URL = "http://odata.bordeaux.fr/v1/databordeaux/airejeux/?format=json";

    @Override
    public Playgrounds getPlaygrounds() {
        IPlaygroundsParser parser = new JSONPlaygroundParser();
        HttpRequest client = new HttpRequest();
        String data = client.request(URL);
        return parser.parsePlaygrounds(data);
    }
}
