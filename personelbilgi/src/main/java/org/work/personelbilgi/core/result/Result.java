package org.work.personelbilgi.core.result;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
public class Result {
    private  boolean success;
    private  String message;

    public Result(boolean success) {
        this.success = success;
    }

}