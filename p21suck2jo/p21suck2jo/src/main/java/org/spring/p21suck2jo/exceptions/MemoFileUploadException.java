package org.spring.p21suck2jo.exceptions;

import net.bytebuddy.implementation.bytecode.Throw;

public class MemoFileUploadException extends RuntimeException{
    public MemoFileUploadException(String message){
        super(message);
    }

    public MemoFileUploadException(String message, Throwable cause){
        super(message, cause);
    }
}
