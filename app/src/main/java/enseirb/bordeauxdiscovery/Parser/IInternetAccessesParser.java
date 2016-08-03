package enseirb.bordeauxdiscovery.Parser;

import enseirb.bordeauxdiscovery.DTO.InternetAccesses;

public interface IInternetAccessesParser {
    InternetAccesses parseInternetAccesses(String data);
}
