package enseirb.bordeauxdiscovery.DAO;

import enseirb.bordeauxdiscovery.Client.HttpRequest;
import enseirb.bordeauxdiscovery.DTO.InternetAccesses;
import enseirb.bordeauxdiscovery.Parser.IInternetAccessesParser;
import enseirb.bordeauxdiscovery.Parser.JSONInternetAccessesParser;

public class InternetAccessDAO implements IInternetAccessDAO {
    private static final String URL = "http://odata.bordeaux.fr/v1/databordeaux/sigaccesinternet/?format=json";

    @Override
    public InternetAccesses getInternetAccesses() {
        IInternetAccessesParser parser = new JSONInternetAccessesParser();
        HttpRequest client = new HttpRequest();
        String data = client.request(URL);
        return parser.parseInternetAccesses(data);
    }
}

