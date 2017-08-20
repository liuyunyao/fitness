package com.nidone.fitness.service.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Administrator on 2017/06/09.
 * This class used to deserialize some string date format which fastjson can't deserialize
 */
public class DateJsonDeserializer extends JsonDeserializer<Date> {
    /*** Date formater*/
    private SimpleDateFormat dateFormat;

    @Override
    public Date deserialize(JsonParser parser, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        String text = parser.getValueAsString();
        Date date = null;
        try {
            // Format is "yyyy-MM-ddTHH:mm:ssZ" , regex is "\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}Z"
            // This is UTC --- Universal Time Coordinated
            if (text.matches("\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}.\\d{3}\\+\\d{4}")) {
                this.dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.sssZ");
            } else if (text.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}")) {
                this.dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            } else if (text.matches("\\d{4}年\\d{2}月\\d{2}日")) {
                this.dateFormat = new SimpleDateFormat("yyyy年MM月dd日");
            } else if (text.matches("\\d{4}\\.\\d{2}\\.\\d{2}")) {
                this.dateFormat = new SimpleDateFormat("yyyy.MM.dd");
            } else if (text.matches("[A-Za-z]{3} [A-Za-z]{3} \\d{2} \\d{2}:\\d{2}:\\d{2} CST \\d{4}")) {
                // This is CST --- Central Standard Time
                this.dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss 'CST' yyyy", Locale.US);
            } else {
                // Matches nothing , set default
                this.dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            }
            date = dateFormat.parse(text);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
