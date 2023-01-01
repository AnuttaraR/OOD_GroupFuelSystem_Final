package GasStationSystem;

public class DateTime {

    //Attributes
    private int day;
    private int month;
    private int year;

    //Constructor
    public DateTime(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    //toString method
    public String toString(){
        return ("Day: "+this.day+" Month: "+this.month+" Year: "+this.year);
    }

    //Getters and setters
    public int getDay() {
        return day;
    }

    public void setDay(int date) {
        this.day = date;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
