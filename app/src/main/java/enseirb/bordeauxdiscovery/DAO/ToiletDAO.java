package enseirb.bordeauxdiscovery.DAO;

import enseirb.bordeauxdiscovery.Client.HttpRequest;
import enseirb.bordeauxdiscovery.DTO.Toilets;
import enseirb.bordeauxdiscovery.Parser.IToiletsParser;
import enseirb.bordeauxdiscovery.Parser.JSONToiletsParser;

public class ToiletDAO implements IToiletDAO{

    private static final String URL = "http://odata.bordeaux.fr/v1/databordeaux/sigsanitaire/?format=json";

    @Override
    public Toilets getToilets() {
        IToiletsParser parser = new JSONToiletsParser();
        HttpRequest client = new HttpRequest();
        String data = client.request(URL);
        return parser.parseToilets(data);
    }
}
