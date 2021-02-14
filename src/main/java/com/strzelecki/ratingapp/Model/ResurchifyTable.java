package com.strzelecki.ratingapp.Model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ResurchifyTable {
    Integer year;
    Double impactFactor;
    Integer citations;

}
