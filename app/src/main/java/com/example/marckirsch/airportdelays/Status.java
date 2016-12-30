package com.example.marckirsch.airportdelays;

/**
 * Created by Marc on 9/23/2015.
 */
public class Status {
    private String closureBegin;
    private String trend;
    private String avgDelay;
    private String reason;
    private String minDelay;
    private String closureEnd;
    private String type;
    private String maxDelay;
    private String endTime;

    private Status() {}

    Status(String closureBegin, String trend,String avgDelay, String reason, String minDelay, String closureEnd, String type, String maxDelay,  String endTime) {

        this.closureBegin = closureBegin;
        this.trend = trend;
        this.avgDelay = avgDelay;
        this.reason = reason;
        this.minDelay = minDelay;
        this.closureEnd = closureEnd;
        this.type = type;
        this.maxDelay = maxDelay;
        this.endTime = endTime;
    }
    public String getAvgDelay() {
        return avgDelay;}
    public String getClosureBegin() {
        return closureBegin;}
    public String getClosureEnd() {
        return closureEnd;}
    public String getEndTime() {
        return endTime;}
    public String getMinDelay() {
        return minDelay;}
    public String getMaxDelay() {
        return maxDelay;}
    public String getReason() {
        return reason;}
    public String getTrend() {
        return trend;}
    public String getType() {
        return type;}

}
