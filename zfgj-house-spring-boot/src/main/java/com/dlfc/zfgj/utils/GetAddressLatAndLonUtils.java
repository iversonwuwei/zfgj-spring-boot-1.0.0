package com.dlfc.zfgj.utils;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 *
 * Created by wangna on 2017/5/15.
 */
@Slf4j
public class GetAddressLatAndLonUtils {
    public static String getAddressLatAndLon(String address) {

        HttpClient httpClient = HttpClients.createDefault();
        address = address.replace(" ", "");
        System.out.println("ADSASDASD"
                + String.format("http://restapi.amap.com/v3/geocode/geo?address=%s&output=JSON&key=7f792de5b31a9f403cc5dc39b595f197", address));
        HttpGet httpGet =
                new HttpGet(String.format("http://restapi.amap.com/v3/geocode/geo?address=%s&output=JSON&key=7f792de5b31a9f403cc5dc39b595f197", address));
        try {
            HttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            String reseult = convertStreamToString(entity.getContent());
            JSONObject jsonObject = JSONObject.parseObject(reseult);
            if (jsonObject.getJSONArray("geocodes").size() > 0) {
                return jsonObject.getJSONArray("geocodes").getJSONObject(0).getString("location");
            }
        } catch (ClientProtocolException e) {
            log.error(e.getMessage());
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return null;
    }

    public static String convertStreamToString(InputStream is) {

        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            log.error(e.getMessage());
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                log.error(e.getMessage());
            }
        }
        return sb.toString();
    }
}
