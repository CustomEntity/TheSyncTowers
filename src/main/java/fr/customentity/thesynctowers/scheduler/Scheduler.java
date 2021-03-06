package fr.customentity.thesynctowers.scheduler;


import java.util.Calendar;

public class Scheduler  {

    private String towersync;
    private SchedulerType schedulerType;
    private int month;
    private int dayOfMonth;
    private int dayOfWeek;
    private int timeOfDay;

    private int minutes;

    private transient long lastStart;

    public Scheduler(String towersync, SchedulerType schedulerType, int month, int dayOfMonth, int dayOfWeek, int timeOfDay, int minutes) {
        this.towersync = towersync;
        this.schedulerType = schedulerType;
        this.month = month;
        this.dayOfMonth = dayOfMonth;
        this.dayOfWeek = dayOfWeek;
        this.timeOfDay = timeOfDay;
        this.minutes = minutes;
    }

    public Scheduler(String towersync, SchedulerType schedulerType, int dayOfWeek, int timeOfDay, int minutes) {
        this(towersync, schedulerType, -1, -1, dayOfWeek, timeOfDay, minutes);
    }

    public Scheduler(String towersync, SchedulerType schedulerType, int timeOfDay, int minutes) {
        this(towersync, schedulerType, -1, -1, -1, timeOfDay, minutes);
    }

    public Scheduler(String towersync, SchedulerType schedulerType, int minutes) {
        this(towersync, schedulerType, -1, -1, -1, -1, minutes);
    }

    public Scheduler() {
        this.lastStart = -1;
    }

    public int getDayOfMonth() {
        return this.dayOfMonth;
    }

    public int getDayOfWeek() {
        return this.dayOfWeek;
    }

    public int getMonth() {
        return this.month;
    }

    public int getTimeOfDay() {
        return this.timeOfDay;
    }

    public SchedulerType getSchedulerType() {
        return this.schedulerType;
    }

    public String getTowersync() {
        return towersync;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setLastStart(long lastStart) {
        this.lastStart = lastStart;
    }

    public long getLastStart() {
        return lastStart;
    }

    public boolean canStart() {
        Calendar calendar = Calendar.getInstance();
        switch (schedulerType) {
            case REPEAT:
                if (lastStart <= 0) {
                    this.lastStart = System.currentTimeMillis() + 60000L * this.minutes;
                    return false;
                }
                if (System.currentTimeMillis() > this.lastStart) {
                    this.lastStart = System.currentTimeMillis() + 60000L * this.minutes;
                    return true;
                }
                break;
            case DAILY:
            case WEEKLY:
            case SPECIFIC:
                if ((month == -1 || calendar.get(Calendar.MONTH) == month) &&
                        (dayOfMonth == -1 || calendar.get(Calendar.DAY_OF_MONTH) == dayOfMonth) &&
                        (dayOfWeek == -1 || calendar.get(Calendar.DAY_OF_WEEK) == dayOfWeek) &&
                        (timeOfDay == -1 || calendar.get(Calendar.HOUR_OF_DAY) == timeOfDay) &&
                        (minutes == -1 || calendar.get(Calendar.MINUTE) == minutes) &&
                        calendar.get(Calendar.SECOND) == 0
                )
                    return true;
                break;
        }
        return false;
    }


    public enum SchedulerType {

        REPEAT,
        DAILY,
        WEEKLY,
        SPECIFIC,

    }
}
