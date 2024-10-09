package br.ufal.ic.p2.MyFood.Exceptions;

public class UserNotRegistered extends Exception {
    public UserNotRegistered() {
        super("Usuario nao cadastrado.");
    }
}
