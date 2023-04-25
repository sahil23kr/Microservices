package com.EmployeeManagement.DTO;

import org.springframework.beans.factory.annotation.Autowired;

public class EmpResponseDTO {

    @Autowired
    private String msg;
    @Autowired
    private Object data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
