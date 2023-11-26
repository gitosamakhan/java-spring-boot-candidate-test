package com.test.javaspringbootcandidatetest.domain;

import java.util.List;

public class ProductRequest {
    private String table;
    private List<Product> records;

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public List<Product> getRecords() {
        return records;
    }

    public void setRecords(List<Product> records) {
        this.records = records;
    }
}
