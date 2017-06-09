package com.dlfc.zfgj.security.mobile.http.actions;

import com.dlfc.zfgj.security.mobile.entity.UsernamePassword;
import com.dlfc.zfgj.security.mobile.exception.CustomRuntimeException;
import com.dlfc.zfgj.security.mobile.http.client.ZfgjHttpClient;
import com.dlfc.zfgj.security.mobile.http.post.ZfgjHttpPost;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by walden on 2017/3/28.
 */
@Repository
@ConfigurationProperties(locations = "application.properties")
public class DoPostImpl implements DoPost {

    @Autowired
    private ZfgjHttpClient zfgjHttpClient;
    @Autowired
    private ZfgjHttpPost zfgjHttpPost;
    @Value("${dlfc.http.sessionTimeout}")
    private String sessionTimeout;

    @Override
    public String post(Object object) throws CustomRuntimeException {
        UsernamePassword usernamePassword = (UsernamePassword) object;
        CloseableHttpClient httpClient = null;
        HttpPost httpPost = null;
        CloseableHttpResponse response2 = null;
        String entityString = "";
        try {
            httpPost = zfgjHttpPost.getHttpPost();
            httpClient = zfgjHttpClient.getHttpClient();
            List<NameValuePair> nvps = new ArrayList<>();
            nvps.add(new BasicNameValuePair("username", usernamePassword.getUsername()));
            nvps.add(new BasicNameValuePair("password", usernamePassword.getPassword()));
            nvps.add(new BasicNameValuePair("validateCode", usernamePassword.getValidateCode()));
            httpPost.setEntity(new UrlEncodedFormEntity(nvps));
            httpPost.addHeader("sessionTimeout", sessionTimeout);
            response2 = httpClient.execute(httpPost);
            HttpEntity entity2 = response2.getEntity();
            entityString = EntityUtils.toString(entity2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            response2.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return entityString;
    }
}
