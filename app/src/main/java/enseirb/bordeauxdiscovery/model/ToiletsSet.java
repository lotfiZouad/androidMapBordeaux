package enseirb.bordeauxdiscovery.model;


import java.util.ArrayList;
import java.util.List;


public class ToiletsSet implements IGeographicSets<Toilet>{
    private List<Toilet> toilets = null;

    @Override
    public List<Toilet> getAllAsList() {
        return toilets;
    }

    @Override
    public void setAllFromList(List<Toilet> list) {
        toilets = list;
    }

    @Override
    public void addElement(Toilet element) {
        if(toilets == null)
            toilets = new ArrayList<>();
        toilets.add(element);
    }
}
