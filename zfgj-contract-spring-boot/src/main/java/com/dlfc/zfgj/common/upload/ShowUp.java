package com.dlfc.zfgj.common.upload;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by walden on 2017/2/17.
 */
public interface ShowUp<F> {

    void showUp(F f, HttpServletResponse response) throws IOException;
}
