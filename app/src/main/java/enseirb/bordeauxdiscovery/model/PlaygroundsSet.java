package enseirb.bordeauxdiscovery.model;

import java.util.ArrayList;
import java.util.List;

public class PlaygroundsSet implements IGeographicSets<Playground>{
    private List<Playground> playgrounds = null;


    @Override
    public List<Playground> getAllAsList() {
        return playgrounds;
    }

    @Override
    public void setAllFromList(List<Playground> list) {
        playgrounds = list;
    }

    @Override
    public void addElement(Playground element) {
        if (playgrounds == null)
            playgrounds = new ArrayList<>();
        playgrounds.add(element);
    }
}
