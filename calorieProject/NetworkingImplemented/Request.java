package edu.sdccd.cisc191.o;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * This class is used to create an object Request and the static methods are used to JSON serialize the Request object that is to be outputted
 * Also contains a method to deserialize a JSON serialized object
 */
public class Request {
    private Integer logEntryDay;

    @JsonIgnore
    private static final ObjectMapper objectMapper = new ObjectMapper();
    public static String toJSON(Request log) throws Exception {
        return objectMapper.writeValueAsString(log);
    }
    public static Request fromJSON(String input) throws Exception{
        return objectMapper.readValue(input, Request.class);
    }
    protected Request(){

    }
    public Request(Integer logEntryDay) {
        this.logEntryDay = logEntryDay;
    }

    public Integer getLogEntryDay(){
        return logEntryDay;
    }

    @Override
    public String toString() {
        return String.format("Day[%d] ", logEntryDay);
    }



}
