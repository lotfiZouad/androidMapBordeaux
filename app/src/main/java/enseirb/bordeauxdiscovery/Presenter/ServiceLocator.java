package enseirb.bordeauxdiscovery.Presenter;

import java.util.HashMap;
import java.util.Map;

import enseirb.bordeauxdiscovery.R;

public class ServiceLocator {
    private static Map<Integer,String> services;

    public static final String PARK = "Park";
    public static final String INTERNET_ACCESS = "InternetAccess";
    public static final String PLAYGROUND = "Playground";
    public static final String TOILET = "Toilet";

    static{
        services= new HashMap<Integer,String>();
        services.put(R.id.id_parks, PARK);
        services.put(R.id.id_internet, INTERNET_ACCESS);
        services.put(R.id.id_play, PLAYGROUND);
        services.put(R.id.id_wc, TOILET);
    }

    public static boolean contains(int id){
        return services.containsKey(id);
    }

    public static String getType(int id){
        return services.get(id);
    }



}
