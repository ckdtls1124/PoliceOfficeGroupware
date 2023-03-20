package org.spring.p21suck2jo.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "file")
public class FileUploadProperties {
    private String dir;

    public String getDir(){
        return dir;
    }

    public void setDir(String dir){
        this.dir=dir;
    }

}
