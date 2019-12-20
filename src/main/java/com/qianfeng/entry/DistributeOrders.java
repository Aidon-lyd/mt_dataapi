package com.qianfeng.entry;

/**
 * 订单分布实体
 */
public class DistributeOrders {
    private String home_orders;
    private String company_orders;
    private String school_orders;
    private String day_orders;
    private String night_orders;

    public String getHome_orders() {
        return home_orders;
    }

    public void setHome_orders(String home_orders) {
        this.home_orders = home_orders;
    }

    public String getCompany_orders() {
        return company_orders;
    }

    public void setCompany_orders(String company_orders) {
        this.company_orders = company_orders;
    }

    public String getSchool_orders() {
        return school_orders;
    }

    public void setSchool_orders(String school_orders) {
        this.school_orders = school_orders;
    }

    public String getDay_orders() {
        return day_orders;
    }

    public void setDay_orders(String day_orders) {
        this.day_orders = day_orders;
    }

    public String getNight_orders() {
        return night_orders;
    }

    public void setNight_orders(String night_orders) {
        this.night_orders = night_orders;
    }
}
