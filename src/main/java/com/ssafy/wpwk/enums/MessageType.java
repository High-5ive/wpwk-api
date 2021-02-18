package com.ssafy.wpwk.enums;

import lombok.Getter;

@Getter
public enum MessageType {

    ADMIN_MESSAGE("ADMIN_MESSAGE"),
    DELETE("DELETE"),
    WARN("WARN"),
    LIKE("LIKE"),
    COMMENT("COMMENT"),
    FOLLOW("FOLLOW"),
    NEW_CONTENTS("NEW_CONTENTS");

    private String name;

    MessageType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}