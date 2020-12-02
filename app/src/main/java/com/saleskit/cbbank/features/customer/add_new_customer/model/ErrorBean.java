package com.saleskit.cbbank.features.customer.add_new_customer.model;

public class ErrorBean {

    /**
     * errorCode : Unsuccessful
     * errorMessage : Số CMND/Hộ chiếu đã tồn tại trong hệ thống!
     * exception : null
     */

    private String errorCode;
    private String errorMessage;
    private Object exception;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Object getException() {
        return exception;
    }

    public void setException(Object exception) {
        this.exception = exception;
    }
}
