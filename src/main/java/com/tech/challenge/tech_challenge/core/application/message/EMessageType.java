package com.tech.challenge.tech_challenge.core.application.message;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum EMessageType {
    ERROR("Error"),
    SUCCESS("Success");

    private final String name;

    @Override
    public String toString(){
        return this.name;
    }
}
