package enseirb.bordeauxdiscovery.Converter;


import java.util.List;

import enseirb.bordeauxdiscovery.DAO.IPlaygroundDAO;
import enseirb.bordeauxdiscovery.DAO.PlaygroundDAO;
import enseirb.bordeauxdiscovery.DTO.PlaygroundDTO;
import enseirb.bordeauxdiscovery.DTO.Playgrounds;
import enseirb.bordeauxdiscovery.model.Playground;
import enseirb.bordeauxdiscovery.model.PlaygroundsSet;


public class PlaygroundConverter implements IPlaygroundConverter {

    private PlaygroundsSet convert(Playgrounds playgrounds) {
        PlaygroundsSet playgroundsSet = new PlaygroundsSet();
        List<PlaygroundDTO> list = playgrounds.getPlaygrounds();
        for (PlaygroundDTO p : list) {
            Playground playground = new Playground();
            playground.setName(p.getName());
            playground.setY_lat(p.getLatitude());
            playground.setX_long(p.getLongitude());
            playground.setMaxAge(p.getMaxAge());
            playground.setMinAge(p.getMinAge());
            playground.setNumberOfGames(p.getNumberOfGames());
            playgroundsSet.addElement(playground);
        }
        return playgroundsSet;
    }

    @Override
    public PlaygroundsSet getConvertedSet() {
        IPlaygroundDAO dao = new PlaygroundDAO();
        Playgrounds playgrounds = dao.getPlaygrounds();
        return convert(playgrounds);
    }
}
