package com.welend.welend.DTO;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpHeaders;

@Getter
@Setter
public class BaseRequest {
    private String URL;
    private HttpHeaders headers;
    private Object body;
}
