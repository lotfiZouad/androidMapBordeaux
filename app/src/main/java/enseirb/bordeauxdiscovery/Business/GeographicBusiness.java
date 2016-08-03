package enseirb.bordeauxdiscovery.Business;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import enseirb.bordeauxdiscovery.Exception.InvalidResultException;
import enseirb.bordeauxdiscovery.model.IGeographicModel;
import enseirb.bordeauxdiscovery.model.IGeographicSets;


public class GeographicBusiness implements IGeographicBusiness {
    private static final String packageName = "enseirb.bordeauxdiscovery.Converter.";
    private static GeographicBusiness instance = null;

    private GeographicBusiness() {}

    public static GeographicBusiness getInstance() {
        if (instance == null) {
            instance = new GeographicBusiness();
        }
        return instance;
    }

    @Override
    public IGeographicSets<IGeographicModel> getAllPoints(String type) throws InvalidResultException {
        IGeographicSets resultat = null;
        try {
            Class setClass = Class.forName( packageName + type + "Converter");
            Object business = setClass.newInstance();
            Method m = setClass.getMethod("getConvertedSet");
            resultat = (IGeographicSets) m.invoke(business);
        } catch (Exception e){
            e.printStackTrace();
        }
        if (resultat.getAllAsList() == null)
            throw new InvalidResultException();
        return resultat;
    }

    @Override
    public IGeographicSets<IGeographicModel> nearestPointsReflection(final double latitude,final double longitude,String type) throws InvalidResultException {
        IGeographicSets resultat = getAllPoints(type);
        return nearestPoints(resultat, latitude,longitude);
    }


    public  <T extends IGeographicModel> IGeographicSets<T> nearestPoints(IGeographicSets<T> set, final double latitude,final double longitude) throws InvalidResultException {
        List<T> points = set.getAllAsList();
        if(points == null){
            throw new InvalidResultException();
        }
        Collections.sort(points, new Comparator<T>() {
            @Override
            public int compare(T point1, T point2) {
                double latitude1 = point1.getLatitude();
                double latitude2 = point2.getLatitude();
                double longitude1 = point1.getLongitude();
                double longitude2 = point1.getLongitude();

                double distance = Math.sqrt((latitude1 - latitude) * (latitude1 - latitude) + (longitude1 - longitude) * (longitude1 - longitude))
                        - Math.sqrt((latitude2 - latitude) * (latitude2 - latitude) + (longitude2 - longitude) * (longitude2 - longitude));
                if (distance > 0)
                    return 1;
                if (distance == 0)
                    return 0;
                return -1;
            }

        });


        return set;
    }



}