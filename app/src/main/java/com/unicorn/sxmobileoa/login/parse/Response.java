package com.unicorn.sxmobileoa.login.parse;

public class Response {

    private String code;
    private String msg;
    private String randCode;
    private String randCodeSec;
    private String loginBusiType;
    private String time;
    private String seqM;
    private String secM;
    private String seqD;
    private String seqR;
    private Parameters parameters;

    //

    public Parameters getParameters() {
        return parameters;
    }

    public void setParameters(Parameters parameters) {
        this.parameters = parameters;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getRandCode() {
        return randCode;
    }

    public void setRandCode(String randCode) {
        this.randCode = randCode;
    }

    public String getRandCodeSec() {
        return randCodeSec;
    }

    public void setRandCodeSec(String randCodeSec) {
        this.randCodeSec = randCodeSec;
    }

    public String getLoginBusiType() {
        return loginBusiType;
    }

    public void setLoginBusiType(String loginBusiType) {
        this.loginBusiType = loginBusiType;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSeqM() {
        return seqM;
    }

    public void setSeqM(String seqM) {
        this.seqM = seqM;
    }

    public String getSecM() {
        return secM;
    }

    public void setSecM(String secM) {
        this.secM = secM;
    }

    public String getSeqD() {
        return seqD;
    }

    public void setSeqD(String seqD) {
        this.seqD = seqD;
    }

    public String getSeqR() {
        return seqR;
    }

    public void setSeqR(String seqR) {
        this.seqR = seqR;
    }
}
