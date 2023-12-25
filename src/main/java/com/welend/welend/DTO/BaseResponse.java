package com.welend.welend.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseResponse {
    private String status;
    private String message;
    private String data;

}
