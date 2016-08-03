package enseirb.bordeauxdiscovery.Parser;

import enseirb.bordeauxdiscovery.DTO.Toilets;

public interface IToiletsParser {
    Toilets parseToilets(String data);
}
