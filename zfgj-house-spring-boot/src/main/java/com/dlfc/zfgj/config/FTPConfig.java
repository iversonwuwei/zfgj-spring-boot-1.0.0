package com.dlfc.zfgj.config;


import com.dlfc.zfgj.upload.UploadUtils;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

/**
 * Created by walden on 2017/3/21.
 */
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "dlfc.ftp")
public class FTPConfig {

    @Value("host")
    private String host;
    @Value("username")
    private String username;
    @Value("password")
    private String password;
    @Value("workingDirectory")
    private String workingDirectory;
    @Value("workingDirectoryForHouse")
    private String workingDirectoryForHouse;


    @Bean
    public FTPClient ftpClient() {
        FTPClient ftpClient = new FTPClient();
        try {
            ftpClient.connect(host);
            ftpClient.login(username, password);
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            ftpClient.makeDirectory(workingDirectory);
            ftpClient.changeWorkingDirectory(workingDirectory);
            makeDir(ftpClient);
            ftpClient.changeWorkingDirectory(workingDirectoryForHouse);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ftpClient;
    }

    private void makeDir(FTPClient ftpClient) throws IOException {
        UploadUtils.makeDir(ftpClient, workingDirectoryForHouse);
    }
}
