package com.hsmap.yuezhihui.pay.axshare;

/***
 * 支付参数bean
 */
public class PayBean {
    double amount;
    String phone;
    String name;
    String CPF;
    String pixType;
    String pixKey;
    String customCode;




    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getPixType() {
        return pixType;
    }

    public void setPixType(String pixType) {
        this.pixType = pixType;
    }

    public String getPixKey() {
        return pixKey;
    }

    public void setPixKey(String pixKey) {
        this.pixKey = pixKey;
    }

    public String getCustomCode() {
        return customCode;
    }

    public void setCustomCode(String customCode) {
        this.customCode = customCode;
    }
}
