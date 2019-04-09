package com.senilyo.websever.test;

import java.util.Date;

/**
 * <client id="0001">
 *         <name>Jery</name>
 *         <sex>女</sex>
 *         <number>1234</number>
 *         <days>1</days>
 *         <room>101</room>
 *         <dateBegin>2019-4-3</dateBegin>
 *         <dateEnd>2019-4-4</dateEnd>
 *         <pay>300</pay>
 *     </client>
 */



public class Client {
    private String name;
    private String Sex;
    private long number;
    private int days;
    private int room;
    private String dateBegin;
    private String dateEnd;
    private String pay;

    public Client(){

    }

    public Client(String name, String sex, long number, int days, int room, String dateBegin, String dateEnd, String pay) {
        this.name = name;
        Sex = sex;
        this.number = number;
        this.days = days;
        this.room = room;
        this.dateBegin = dateBegin;
        this.dateEnd = dateEnd;
        this.pay = pay;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSex(String sex) {
        Sex = sex;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    public void setDateBegin(String dateBegin) {
        this.dateBegin = dateBegin;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }

    public void setPay(String pay) {
        this.pay = pay;
    }

    public String getName() {
        return name;
    }

    public String getSex() {
        return Sex;
    }

    public long getNumber() {
        return number;
    }

    public int getDays() {
        return days;
    }

    public int getRoom() {
        return room;
    }

    public String getDateBegin() {
        return dateBegin;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public String getPay() {
        return pay;
    }
}
