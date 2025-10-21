public class ShowCurrentTime {
    public static void main () {
        long totalMilliseconds = System.currentTimeMillis();
        System.out.println(totalMilliseconds);

        long totalSeconds = totalMilliseconds / 1000;
        System.out.println(totalSeconds);

        long currentSecond = totalSeconds % 60;
        System.out.println(currentSecond);

        long totalMinutes = totalSeconds / 60;
        System.out.println(totalMinutes);

        long currentMinute = totalMinutes % 60;
        System.out.println(currentMinute);

        long totalHours = totalMinutes / 60;
        System.out.println(totalHours);

        long currentHour = totalHours % 24;
        System.out.println(currentHour);

        while (true) {
        System.out.println(
                "The current time is " + currentHour + " H " +
                 totalMinutes + " Mins " + totalSeconds + " Secs " +
                        " and " + totalMilliseconds + " ms ");
        }



    }
}
