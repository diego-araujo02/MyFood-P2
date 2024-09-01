package br.ufal.ic.p2.MyFood;

import br.ufal.ic.p2.MyFood.XML;

public class Facade extends SystemMyFood {
    public Facade() {
        super("src/br/ufal/ic/p2/MyFood/data/users.xml", "users");
    }

    public Facade(String filePathUsers, String rootNameUsers) {
        super(filePathUsers, rootNameUsers);
    }
}
