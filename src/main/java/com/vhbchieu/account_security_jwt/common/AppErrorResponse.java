package com.vhbchieu.account_security_jwt.common;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AppErrorResponse {
    private String requestId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    private Date at;
    private Object data;

    public AppErrorResponse(String requestId, Object data) {
        this.requestId = requestId;
        this.at = new Date();
        this.data = data;
    }

    public AppErrorResponse(String requestId) {
        this.requestId = requestId;
        this.at = new Date();
        this.data = null;
    }
}
