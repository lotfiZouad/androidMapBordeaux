package enseirb.bordeauxdiscovery.model;

import java.util.List;

public interface IGeographicSets <T> {
    List<T> getAllAsList();
    void setAllFromList(List<T> list);
    void addElement(T element);
}
