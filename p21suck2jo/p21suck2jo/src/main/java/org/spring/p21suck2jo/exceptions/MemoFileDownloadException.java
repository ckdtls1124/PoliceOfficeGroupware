package org.spring.p21suck2jo.exceptions;

import net.bytebuddy.implementation.bytecode.Throw;

public class MemoFileDownloadException extends RuntimeException{

    public MemoFileDownloadException(String message){
        super(message);
    }

    public MemoFileDownloadException(String message, Throwable cause){
        super(message, cause);
    }


}
