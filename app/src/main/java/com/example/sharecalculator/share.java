package com.example.sharecalculator;

public class share {

    String name,quantity,bp,total,per;
    int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public share(int id, String name, String quantity, String bp, String total, String per) {
        this.id=id;
        this.name = name;
        this.quantity = quantity;
        this.bp = bp;
        this.total = total;
        this.per = per;
    }

    public share(String bp, String total, String per) {
        this.bp = bp;
        this.total = total;
        this.per = per;
    }

    public share(String name, String quantity, String bp, String total, String per) {
        this.name = name;
        this.quantity = quantity;
        this.bp = bp;
        this.total = total;
        this.per = per;
    }

    public String getBp() {
        return bp;
    }

    public void setBp(String bp) {
        this.bp = bp;
    }

    public share() {
    }

    public share(String name, String quantity) {
        this.name = name;
        this.quantity = quantity;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getPer() {
        return per;
    }

    public void setPer(String per) {
        this.per = per;
    }
}
