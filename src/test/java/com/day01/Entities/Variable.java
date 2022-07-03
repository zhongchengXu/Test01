package com.day01.Entities;

public class Variable {
    private String name;
    private String value;
    private String remark;
    private String reflectClass;
    private String reflectMetod;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getReflectClass() {
        return reflectClass;
    }

    public void setReflectClass(String reflectClass) {
        this.reflectClass = reflectClass;
    }

    public String getReflectMetod() {
        return reflectMetod;
    }

    public void setReflectMetod(String reflectMetod) {
        this.reflectMetod = reflectMetod;
    }
}
