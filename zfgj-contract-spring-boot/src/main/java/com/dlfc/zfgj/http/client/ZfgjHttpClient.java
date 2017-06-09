package com.dlfc.zfgj.http.client;

import lombok.Getter;
import lombok.Setter;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.stereotype.Component;

/**
 * Created by walden on 2017/3/28.
 */
@Getter
@Setter
@Component
public class ZfgjHttpClient {

    private CloseableHttpClient httpClient;

    public CloseableHttpClient getHttpClient(){
        try {
            httpClient = HttpClients.createDefault();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return httpClient;
    }
}
