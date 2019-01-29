/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.springboot.demospringboot.model;

import java.util.Date;

public class ResponseRest<T> {

    private static final Integer OK = 1;
    private static final Integer NOT_OK = 0;
    private static final String SUCCESS_MESSAGE = "Success";
    private static final String[] SUCCESS_OPERATION = {SUCCESS_MESSAGE};
    private final Integer status;
    private final Date timestamp = new Date();
    private String message;
    private String[] errors;
    private String[] success;
    private String[] warnings;
    private T result;

    public ResponseRest(Integer status) {
        this.status = status;
    }

    public static ResultBuilder status(Integer status) {
        return new DefaultBuilder(status);
    }
    
    public static ResultBuilder success() {
        return status(OK)
                .setMessage(SUCCESS_MESSAGE)
                .setSuccess(SUCCESS_OPERATION);
    }

    public static <T> ResponseRest<T> success(T result) {
        ResultBuilder builder = success();
        return builder.build(result);
    }
    
    public static ResultBuilder error() {
        return status(NOT_OK);
    }

    public static <T> ResponseRest<T> error(T result) {
        ResultBuilder builder = error();
        return builder.build(result);
    }

    public Integer getStatus() {
        return status;
    }
    
    public Date getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String[] getErrors() {
        return errors;
    }

    public String[] getSuccess() {
        return success;
    }

    public String[] getWarnings() {
        return warnings;
    }

    public T getResult() {
        return result;
    }

    public interface ResultBuilder {

        public ResultBuilder setMessage(String message);

        public ResultBuilder setErrors(String... errors);

        public ResultBuilder setSuccess(String... success);

        public ResultBuilder setWarnings(String... warnings);

        public <T> ResponseRest<T> build();

        public <T> ResponseRest<T> build(T result);
    }

    private static class DefaultBuilder implements ResultBuilder {

        private final Integer status;
        private Date timestamp;
        private String message;
        private String[] errors;
        private String[] success;
        private String[] warnings;

        public DefaultBuilder(Integer status) {
            this.status = status;
        }

        @Override
        public <T> ResponseRest<T> build() {
            return build(null);
        }

        @Override
        public <T> ResponseRest<T> build(T result) {
            ResponseRest<T> responseRest2 = new ResponseRest(this.status);
            responseRest2.errors = this.errors;
            responseRest2.success = this.success;
            responseRest2.warnings = this.warnings;
            responseRest2.message = this.message;
            responseRest2.result = result;
            return responseRest2;
        }

        @Override
        public ResultBuilder setMessage(String message) {
            this.message = message;
            return this;
        }

        @Override
        public ResultBuilder setErrors(String... errors) {
            this.errors = errors;
            return this;
        }

        @Override
        public ResultBuilder setSuccess(String... success) {
            this.success = success;
            return this;
        }

        @Override
        public ResultBuilder setWarnings(String... warnings) {
            this.warnings = warnings;
            return this;
        }
    }

}
