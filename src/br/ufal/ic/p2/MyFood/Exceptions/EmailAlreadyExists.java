package br.ufal.ic.p2.MyFood.Exceptions;

public class EmailAlreadyExists extends Exception{
    public EmailAlreadyExists() {
        super("Conta com esse email ja existe");
    }

    public EmailAlreadyExists(String message) {
        super(message);
    }
}
