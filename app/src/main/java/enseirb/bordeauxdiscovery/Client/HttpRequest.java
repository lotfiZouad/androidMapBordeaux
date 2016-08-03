package enseirb.bordeauxdiscovery.Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpRequest {
    private static final String NO_DATA = "No Data found.";
    private static final int READ_TIMEOUT = 1000;
    private static final int CONNECTION_TIMEOUT = 1000;
    private static final String HTTP_GET_METHOD = "GET";

    public String request(String url){
        InputStream is = null;
        try{
            is = connectTo(url);
            return parseResponse(is);
        } catch (IOException e){
            e.printStackTrace();
            return NO_DATA;
        } finally {
            if(is != null)
                closeInputStream(is);
        }
    }

    private String  parseResponse(InputStream in){
        StringBuilder result = new StringBuilder();
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
        }catch( Exception e) {
            e.printStackTrace();
        }
        return result.toString();
    }

    private InputStream connectTo(String urlToParse) throws  IOException {
        InputStream is;
        URL url = new URL(urlToParse);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setReadTimeout(READ_TIMEOUT);
        conn.setConnectTimeout(CONNECTION_TIMEOUT);
        conn.setRequestMethod(HTTP_GET_METHOD);
        conn.setDoInput(true);
        conn.connect();
        is = conn.getInputStream();
        return is;
    }

    private void closeInputStream(InputStream is){
        try{
            is.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
