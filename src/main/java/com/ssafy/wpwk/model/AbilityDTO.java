package com.ssafy.wpwk.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class AbilityDTO {
    int abilities[] = new int[8];

    public AbilityDTO(Integer ability1,
                      Integer ability2,
                      Integer ability3,
                      Integer ability4,
                      Integer ability5,
                      Integer ability6,
                      Integer ability7,
                      Integer ability8) {

        abilities[0] = ability1;
        abilities[1] = ability2;
        abilities[2] = ability3;
        abilities[3] = ability4;
        abilities[4] = ability5;
        abilities[5] = ability6;
        abilities[6] = ability7;
        abilities[7] = ability8;
    }

}


