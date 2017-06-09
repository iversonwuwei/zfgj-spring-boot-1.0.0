package com.dlfc.zfgj.security.http.actions;

import com.dlfc.zfgj.security.http.client.ZfgjHttpClient;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;

/**
 * Created by walden on 2017/3/28.
 */
@Repository
public class DoGetImpl implements DoGet {
    @Autowired
    private ZfgjHttpClient zfgjHttpClient;
    //10.39.12.14
    private String request = "http://10.32.156.155:8789/principal";

    @Override
    public String get(Object object) {
        String token = (String) object;
        CloseableHttpClient httpClient = null;
        String entityString = "";
        try {
            httpClient = zfgjHttpClient.getHttpClient();
            HttpGet httpGet = new HttpGet(request);
            httpGet.addHeader("token", token);
            CloseableHttpResponse response1 = httpClient.execute(httpGet);
            try {
                HttpEntity entity1 = response1.getEntity();
                entityString = EntityUtils.toString(entity1);
            } finally {
                response1.close();
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return entityString;
    }
}
