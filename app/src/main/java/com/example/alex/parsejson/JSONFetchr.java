package com.example.alex.parsejson;

import android.net.Uri;
import android.renderscript.ScriptGroup;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class JSONFetchr {

    private static final String TAG = "JSONFetchr";
private String uurl="http://cashadvanceapp.pp.ua/json/example.json";
    public byte[] getUrlBytes(String urlSpec) throws IOException {
        URL url = new URL(urlSpec);
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            InputStream in = connection.getInputStream();
            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new IOException(connection.getResponseMessage() +
                        ": with " +
                        urlSpec);
            }
            int bytesRead = 0;
            byte[] buffer = new byte[1024];
            while ((bytesRead = in.read(buffer)) > 0) {
                out.write(buffer, 0, bytesRead);
            }
            out.close();
            return out.toByteArray();


        } finally {
            connection.disconnect();
        }
    }
    public String getUrlString(String urlSpec) throws IOException {
        return new String(getUrlBytes(urlSpec));
    }

    public List<Offer> fetchItems() {

        List<Offer> items = new ArrayList<>();
        try {
            String jsonString = getUrlString(uurl);
            Log.i(TAG, "Received JSON: " + jsonString);
           JSONObject jsonBody = new JSONObject(jsonString);
            parseItems(items, jsonBody);

        } catch (IOException ioe) {
            Log.e(TAG, "Failed to fetch items", ioe);
        } catch (JSONException je) {
            Log.e(TAG, "Failed to parse JSON", je);

        }

        return items;
    }

  /*  public static String convertStandardJSONString(String data_json){
        data_json = data_json.replaceAll("\\\\r\\\\n","");
        data_json = data_json.replace(",\"{","{");
        data_json = data_json.replace("}\",","},");
        data_json = data_json.replace("}\"","}");
        String s[] = data_json.split("}");
        for(String ss: s){
            ss.substring(0,ss.lastIndexOf(","));
        }
        data_json="";
        for(String ss:s){
            data_json+=ss+"}";
        }
        return data_json;
    }*/
    private void parseItems(List<Offer> items, JSONObject jsonBody)
            throws IOException, JSONException {

       JSONArray offerJsonArray = jsonBody.getJSONArray("ru_offers");

        for (int i = 0; i < offerJsonArray.length(); i++) {
            JSONObject offerJsonObject = offerJsonArray.getJSONObject(i);

            Offer offer = new Offer();
            offer.setId(offerJsonObject.getString("id"));
            offer.setTitle(offerJsonObject.getString("title"));
            offer.setDescription(offerJsonObject.getString("description"));
            offer.setAdvancedDescription(offerJsonObject.getString("advanced_description"));
            offer.setOfferLink(offerJsonObject.getString("offer"+offer.getId()+"_link"));
            items.add(offer);}
    }

}
