package sample;

import java.io.Serializable;
import java.time.LocalDate;

public class Container implements Serializable {
    public String title;
    public String field;
    public LocalDate expdate;
    public Priority priority;

    public Container(){
        this.title = "task";
        this.field = "opis";
        this.priority = Priority.ASAP;
        this.expdate=LocalDate.of(2005,3,4);
    }

    public Container(String title, String field, Priority priority, LocalDate expdate) {
        this.title = title;
        this.field = field;
        this.priority = priority;
        this.expdate = expdate;
    }

    @Override
    public String toString() {
        return title;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return field;
    }

    public LocalDate getDate() {
        return expdate;
    }

    public Priority getPriority() {
        return priority;
    }

    public int getYear() {
        return expdate.getYear();
    }

    public int getMonth(){
        return expdate.getMonthValue();
    }

    public int getDay(){
        return expdate.getDayOfMonth();
    }
}
