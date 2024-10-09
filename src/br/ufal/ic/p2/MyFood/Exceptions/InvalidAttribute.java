package br.ufal.ic.p2.MyFood.Exceptions;

public class InvalidAttribute extends Exception {
    public InvalidAttribute() {
        super("Invalid Attribute");
    }

    public InvalidAttribute(String message) {
        super(message);
    }
}
