package enseirb.bordeauxdiscovery.model;

import java.util.ArrayList;
import java.util.List;

public class ParkSet implements IGeographicSets<Park> {

    private List<Park> parks = null;

    @Override
    public List<Park> getAllAsList() {
        return parks;
    }

    @Override
    public void addElement(Park element) {
        if (parks == null)
            parks= new ArrayList<>();
        parks.add(element);
    }

    @Override
    public void setAllFromList(List<Park> list) {
          parks = list;
    }

}
