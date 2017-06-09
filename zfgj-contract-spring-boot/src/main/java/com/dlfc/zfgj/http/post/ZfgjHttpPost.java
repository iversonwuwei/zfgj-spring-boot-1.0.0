package com.dlfc.zfgj.http.post;

import org.apache.http.client.methods.HttpPost;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by walden on 2017/3/28.
 */
@Component
public class ZfgjHttpPost {

    private HttpPost httpPost;

    private String spec = "http://10.32.156.155:8789/login";
    private URI uri;

    public HttpPost getHttpPost() throws URISyntaxException {
        httpPost = new HttpPost(getUri());
        return httpPost;
    }

    public URI getUri() throws URISyntaxException {
        uri = new URI(spec);
        return uri;
    }
}
