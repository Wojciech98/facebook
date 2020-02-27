package com.example.facebook;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FacebookHookRequest implements Serializable {
    private String object;
    private List<FacebookEntry> entry = new ArrayList<>();
}
