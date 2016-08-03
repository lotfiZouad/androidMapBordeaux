package enseirb.bordeauxdiscovery.Converter;

import java.util.List;

import enseirb.bordeauxdiscovery.DAO.IParksDAO;
import enseirb.bordeauxdiscovery.DAO.ParkDAO;
import enseirb.bordeauxdiscovery.DTO.ParkDTO;
import enseirb.bordeauxdiscovery.DTO.Parks;
import enseirb.bordeauxdiscovery.model.Park;
import enseirb.bordeauxdiscovery.model.ParkSet;


public class ParkConverter implements IParkConverter {

    private ParkSet convert(Parks parks) {
        ParkSet parkSet = new ParkSet();
        List<ParkDTO> list = parks.getParks();
        for (ParkDTO dto : list) {
            Park park = new Park();
            park.setName(dto.getName());
            park.setTypology(dto.getTypology());
            park.setType(dto.getType());
            park.setXCoord(dto.getLongitude());
            park.setYCoord(dto.getLatitude());
            parkSet.addElement(park);
        }
        return parkSet;
    }

    @Override
    public ParkSet getConvertedSet() {
        IParksDAO dao = new ParkDAO();
        Parks parks = dao.getParks();
        return convert(parks);
    }

}
