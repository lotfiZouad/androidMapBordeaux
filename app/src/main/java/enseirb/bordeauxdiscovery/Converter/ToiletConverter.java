package enseirb.bordeauxdiscovery.Converter;


import java.util.List;

import enseirb.bordeauxdiscovery.DTO.ToiletDTO;


import enseirb.bordeauxdiscovery.DAO.IToiletDAO;
import enseirb.bordeauxdiscovery.DAO.ToiletDAO;
import enseirb.bordeauxdiscovery.DTO.Toilets;
import enseirb.bordeauxdiscovery.model.Toilet;
import enseirb.bordeauxdiscovery.model.ToiletsSet;

public class ToiletConverter implements IToiletConverter {

    private ToiletsSet convert(Toilets toilets) {
        ToiletsSet toiletsSet = new ToiletsSet();
        List<ToiletDTO> list = toilets.getToilets();
        for (ToiletDTO dto : list) {
            Toilet toilet = new Toilet();
            toilet.setAddress(dto.getAddress());
            toilet.setTypology(dto.getTypology());
            toilet.setOptions(dto.getOptions());
            toilet.setLatitude(dto.getLatitude());
            toilet.setLongitude(dto.getLongitude());
            toiletsSet.addElement(toilet);
        }
        return toiletsSet;
    }

    @Override
    public ToiletsSet getConvertedSet() {
        IToiletDAO toiletDAO = new ToiletDAO();
        Toilets toilets = toiletDAO.getToilets();
        return convert(toilets);
    }
}
