package com.github.yoruhinda.tavernabot.api;

import com.github.yoruhinda.tavernabot.utils.Config;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class TenorGifApi {

    public static String searchGif(String search) {
        String apiKey = Config.tenorGifApiKey;
        String urlString = "https://tenor.googleapis.com/v2/search?q=" + search + "&key=" + apiKey + "&limit=1&random=true&media_filter=gif";
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            BufferedReader response = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder responsebuilder = getResponsebuilder(response);
            return getUrlGifResultJson(responsebuilder.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static StringBuilder getResponsebuilder(BufferedReader response) throws IOException {
        StringBuilder responsebuilder = new StringBuilder();
        String responseString;
        while ((responseString = response.readLine()) != null) {
            responsebuilder.append(responseString);
        }
        return responsebuilder;
    }

    private static String getUrlGifResultJson(String response) {
        JSONObject jsonObject = new JSONObject(response);
        JSONArray results = jsonObject.getJSONArray("results");
        JSONObject result = results.getJSONObject(0);
        JSONObject mediaFormats = result.getJSONObject("media_formats");
        JSONObject gif = mediaFormats.getJSONObject("gif");
        return gif.getString("url");
    }
}
