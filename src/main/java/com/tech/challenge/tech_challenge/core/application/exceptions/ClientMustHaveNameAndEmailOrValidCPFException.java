package com.tech.challenge.tech_challenge.core.application.exceptions;

public class ClientMustHaveNameAndEmailOrValidCPFException extends Error {
    public ClientMustHaveNameAndEmailOrValidCPFException() {
        super("The client must have a name and email or a valid CPF");
    }
}