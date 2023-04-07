package edu.sdccd.cisc191.o;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;
/**
 * This class is used to create an object DailyLog
 * The class also includes methods to JSON serialize an object of DailyLog
 * Likewise, it also has a method to deserialize a JSON serialized DailyLog object
 *
 */
public class DailyLog {
    int logDate;
    private int dailyCalories = 0;
    String enteredIngredients; //String or Ingredient?
    String enteredFoods;       //String or Food?

    @JsonIgnore
    private static final ObjectMapper objectMapper = new ObjectMapper();
    public static String toJSON(DailyLog log) throws Exception {
        return objectMapper.writeValueAsString(log);
    }
    public static DailyLog fromJSON(String input) throws Exception{
        return objectMapper.readValue(input, DailyLog.class);
    }


    public void searchFoods(){
        dailyCalories = 0; //use Food class methods to calculate for real
    }
    public void searchByIngredients(){
        dailyCalories = 0; //use Ingredient class methods to calculate for real
    }
    public int getDailyCalories(){
        //if its foods use searchFoods() and return dailyCalories
        //else
        //use ingredients use searchByIngredients() and return dailyCalories
        return 0;
    }

    public void setLogDate(int logDate){
        this.logDate = logDate;
    }


    @Override
    public String toString() {
        return String.format("Daily Calorie amount is %d because you starved today",dailyCalories);
    }
}
