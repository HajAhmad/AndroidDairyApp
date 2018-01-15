package ir.plusrobot.dairyapp.rest;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class RestConfig {

    public String getData(String place) {
        HttpURLConnection connection = null;
        InputStream inputStream = null;

        try {
            connection = (HttpURLConnection) (new URL(ServerUrl.BASE_URL + place)).openConnection();
            connection.setRequestMethod("GET");
            connection.setDoInput(true); //Sets the flag indicating whether this {@code URLConnection} allows input
            connection.setDoOutput(true);
            connection.connect();

            //Read the response
            StringBuffer stringBuffer = new StringBuffer();
            inputStream = connection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = null;

            while ((line = bufferedReader.readLine()) != null)
                stringBuffer.append(line + "\r\n");

            inputStream.close();
            connection.disconnect();
            return stringBuffer.toString();

        } catch (IOException e) {

            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
                connection.disconnect();
            } catch (IOException e) {

            }


        }

        return null;
    }


}
