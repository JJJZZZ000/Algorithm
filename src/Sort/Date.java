package Sort;

public class Date implements Comparable<Date>{
    private final int day;
    private final int month;
    private final int year;

    public Date(int d, int m, int y){
        day = d;
        month = m;
        year = y;
    }

    // compareTo function enable sort
    public int compareTo(Date that){
        if(this.year > that.year) return +1;
        if(this.year < that.year) return -1;
        if(this.month > that.month) return +1;
        if(this.month < that.month) return -1;
        if(this.day > that.day) return +1;
        if(this.day < that.day) return -1;
        return 0;
    }

    public String toString(){
        return month + "/" + day + "/" +year;
    }
}
