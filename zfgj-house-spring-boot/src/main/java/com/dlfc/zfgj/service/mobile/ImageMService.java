package com.dlfc.zfgj.service.mobile;

import java.io.IOException;

/**
 * Created by wangna on 2017/5/9.
 */
public interface ImageMService {
    String generateLeaseImage(String srcFilePath, int wmType) throws IOException;
}
