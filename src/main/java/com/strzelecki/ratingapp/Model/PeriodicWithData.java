package com.strzelecki.ratingapp.Model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class PeriodicWithData {

    private String title;
    private String hIndexSciMagor;
    private List<ResurchifyTable> resurchifyData;

}
