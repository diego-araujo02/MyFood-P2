package br.ufal.ic.p2.MyFood;

public abstract class User {
    private int id;
    private String name;
    private String email;
    private String senha;
    private static int currentId;

    public User(String name, String email, String senha) {
        this.id = currentId;
        this.name = name;
        this.email = email;
        this.senha = senha;
    }
    public abstract String getCpf();

    public abstract String getEndereco();

    public static int getCurrentId() {
        return currentId;
    }

    public static void setCurrentId(int currentId) {
        User.currentId = currentId;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
