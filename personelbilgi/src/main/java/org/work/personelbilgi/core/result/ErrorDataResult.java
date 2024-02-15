package org.work.personelbilgi.core.result;

public class ErrorDataResult <T> extends DataResult<T>{

    public ErrorDataResult(T data , String message){
        super(false, message, data);
    }
    public ErrorDataResult(T data){
        super(data, false);
    }
    public ErrorDataResult(String message){
        super(null, false);
    }
    public ErrorDataResult(){
        super(null, false);
    }
}