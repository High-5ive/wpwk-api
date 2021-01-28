package com.ssafy.wpwk.enums;

import lombok.Getter;

@Getter
public enum MessageType {

    ADMIN_MESSAGE("admin_message"),
    LIKE("like"),
    COMMENT("comment"),
    FOLLOW("follow"),
    NEW_CONTENTS("new_contents");

    private String name;

    MessageType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}