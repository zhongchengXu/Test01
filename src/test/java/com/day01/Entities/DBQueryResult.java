package com.day01.Entities;

import java.util.Map;

public class DBQueryResult {
    private String No;
    private Map<String,Object> columnLabelAndValues;


    public String getNo() {
        return No;
    }

    public void setNo(String no) {
        No = no;
    }

    public Map<String,Object> getColumnLabelAndValues() {
        return columnLabelAndValues;
    }

    public void setColumnLabelAndValues(Map<String,Object> columnLabelAndValues) {
        this.columnLabelAndValues = columnLabelAndValues;
    }
}
