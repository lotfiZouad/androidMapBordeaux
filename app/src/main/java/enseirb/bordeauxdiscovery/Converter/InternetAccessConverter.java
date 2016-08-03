package enseirb.bordeauxdiscovery.Converter;


import java.util.List;

import enseirb.bordeauxdiscovery.DAO.IInternetAccessDAO;
import enseirb.bordeauxdiscovery.DAO.InternetAccessDAO;
import enseirb.bordeauxdiscovery.DTO.InternetAccessDTO;
import enseirb.bordeauxdiscovery.DTO.InternetAccesses;
import enseirb.bordeauxdiscovery.model.InternetAccess;
import enseirb.bordeauxdiscovery.model.InternetAccessSet;

public class InternetAccessConverter implements IInternetAccessConverter {

    private InternetAccessSet convert(InternetAccesses internetAccesses) {
        InternetAccessSet internetAccessesSet = new InternetAccessSet();
        List<InternetAccessDTO> list = internetAccesses.getInternetAccesses();
        for (InternetAccessDTO p : list) {
            InternetAccess internetAccess = new InternetAccess();
            internetAccess.setName(p.getName());
            internetAccess.setLatitude(p.getLatitude());
            internetAccess.setLongitude(p.getLongitude());
            internetAccess.setOperator(p.getOperator());
            internetAccess.setSituation(p.getSituation());
            internetAccess.setPayable(p.getPayable());
            internetAccess.setPublicType(p.getPublicType());
            internetAccess.setAccessType(p.getAccessType());
            internetAccessesSet.addElement(internetAccess);
        }
        return internetAccessesSet;
    }

    @Override
    public InternetAccessSet getConvertedSet() {
        IInternetAccessDAO dao = new InternetAccessDAO();
        InternetAccesses internetAccesses = dao.getInternetAccesses();
        return convert(internetAccesses);
    }
}
