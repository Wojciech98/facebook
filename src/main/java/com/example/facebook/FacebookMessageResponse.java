package com.example.facebook;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FacebookMessageResponse implements Serializable {
    private String message_type;
    private Map<String,String> recipient = new HashMap<>();
    private Map<String,String> message = new HashMap<>();
}