package enseirb.bordeauxdiscovery.Business;

import enseirb.bordeauxdiscovery.Exception.InvalidResultException;
import enseirb.bordeauxdiscovery.model.IGeographicModel;
import enseirb.bordeauxdiscovery.model.IGeographicSets;


public interface IGeographicBusiness {

    public IGeographicSets<IGeographicModel> nearestPointsReflection(final double latitude, final double longitude, String type) throws InvalidResultException;

    public <T extends IGeographicModel> IGeographicSets<T> nearestPoints(IGeographicSets<T> set, final double latitude, final double longitude) throws InvalidResultException;

    public IGeographicSets<IGeographicModel> getAllPoints(String type) throws InvalidResultException;
}
