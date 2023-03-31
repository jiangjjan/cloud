package demo.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import demo.util.DateHelper;

import java.util.Date;

public class Date2Long extends Date {

    public Date2Long() {
    }

    public Date2Long(Date date) {
        super(date.getTime());
    }


    @Override
    public String toString() {
        return String.valueOf(super.getTime());
    }

    @JsonCreator
    public static Date2Long create(String source) {
        return new Date2Long(DateHelper.parse(source));
    }
}
