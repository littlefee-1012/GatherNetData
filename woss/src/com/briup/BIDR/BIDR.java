package com.briup.BIDR;

import java.sql.Timestamp;

public class BIDR {
    /*AAA_Login_name	String
    Login_ip	String
    login_date	java.sql.Timestamp
    logout_date	java.sql.Timestamp
    NAS_ip		String
    Time_duration	Integer*/
    private String AAA_Login_name;
    private String Login_ip;
    private Timestamp Login_data;
    private Timestamp Logout_data;
    private String NAS_ip;
    private Integer Time_duration;

    public BIDR(){}
    public BIDR(String AAA_Login_name, String login_ip, Timestamp login_data, Timestamp logout_data, String NAS_ip, Integer time_duration) {
        this.AAA_Login_name = AAA_Login_name;
        this.Login_ip = login_ip;
        this.Login_data = login_data;
        this.Logout_data = logout_data;
        this.NAS_ip = NAS_ip;
        this.Time_duration = time_duration;
    }

    public String getAAA_Login_name() {
        return AAA_Login_name;
    }
    public void setAAA_Login_name(String AAA_Login_name) {
        this.AAA_Login_name = AAA_Login_name;
    }

    public String getLogin_ip() {
        return Login_ip;
    }
    public void setLogin_ip(String login_ip) {
        Login_ip = login_ip;
    }

    public Timestamp getLogin_data() {
        return Login_data;
    }
    public void setLogin_data(Timestamp login_data) {
        Login_data = login_data;
    }

    public Timestamp getLogout_data() {
        return Logout_data;
    }
    public void setLogout_data(Timestamp logout_data) {
        Logout_data = logout_data;
    }

    public String getNAS_ip() {
        return NAS_ip;
    }
    public void setNAS_ip(String NAS_ip) {
        this.NAS_ip = NAS_ip;
    }

    public Integer getTime_duration() {
        return Time_duration;
    }
    public void setTime_duration(Integer time_duration) {
        Time_duration = time_duration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BIDR bidr = (BIDR) o;

        if (!AAA_Login_name.equals(bidr.AAA_Login_name)) return false;
        if (!Login_ip.equals(bidr.Login_ip)) return false;
        if (!Login_data.equals(bidr.Login_data)) return false;
        if (!Logout_data.equals(bidr.Logout_data)) return false;
        if (!NAS_ip.equals(bidr.NAS_ip)) return false;
        return Time_duration.equals(bidr.Time_duration);
    }

    @Override
    public int hashCode() {
        int result = AAA_Login_name.hashCode();
        result = 31 * result + Login_ip.hashCode();
        result = 31 * result + Login_data.hashCode();
        result = 31 * result + Logout_data.hashCode();
        result = 31 * result + NAS_ip.hashCode();
        result = 31 * result + Time_duration.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "BIDR{" +
                "AAA_Login_name='" + AAA_Login_name + '\'' +
                ", Login_ip='" + Login_ip + '\'' +
                ", Login_data=" + Login_data +
                ", Logout_data=" + Logout_data +
                ", NAS_ip='" + NAS_ip + '\'' +
                ", Time_duration=" + Time_duration +
                '}';
    }
}
