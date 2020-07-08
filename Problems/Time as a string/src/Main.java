
class Time {

    private int hours;
    private int minutes;
    private int seconds;

    public Time(int hours, int minutes, int seconds) {
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
    }

    public String padString(int num){
        return (Integer.toString(num).length() == 1) ?
                "0" + num : Integer.toString(num);
    }

    public String toString(){
        return padString(hours) + ":" + padString(minutes) + ":" + padString(seconds);
    }

}