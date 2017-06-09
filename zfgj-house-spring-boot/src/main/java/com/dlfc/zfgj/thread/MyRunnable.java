package com.dlfc.zfgj.thread;

import com.dlfc.admin.common.exception.ApplicationException;
import com.dlfc.zfgj.service.web.UploadService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by walden on 2017/4/28.
 */
@Getter
@Setter
@Component
public class MyRunnable implements Runnable {

    @Autowired
    private UploadService uploadService;

    private String path;

    private HttpServletResponse response;

    @Override
    public void run() {
        try {
            uploadService.showUp(this.path, this.response);
        } catch (ApplicationException e) {
            e.printStackTrace();
        }
    }
}
