package enseirb.bordeauxdiscovery.model;

import java.util.ArrayList;
import java.util.List;

public class InternetAccessSet implements IGeographicSets<InternetAccess>{
    private List<InternetAccess> internetAccesses = null;

    @Override
    public List<InternetAccess> getAllAsList() {
        return internetAccesses;
    }

    @Override
    public void setAllFromList(List<InternetAccess> list) {
        internetAccesses = list;
    }

    @Override
    public void addElement(InternetAccess element) {
        if(internetAccesses == null)
            internetAccesses = new ArrayList<>();
        internetAccesses.add(element);
    }
}
