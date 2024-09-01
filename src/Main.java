import br.ufal.ic.p2.MyFood.Customer;
import br.ufal.ic.p2.MyFood.SystemMyFood;
import br.ufal.ic.p2.MyFood.XML;
import br.ufal.ic.p2.MyFood.XMLUser;
import easyaccept.EasyAccept;

public class Main {
    public static void main(String[] args) {
        String filePathUsers = "src/br/ufal/ic/p2/MyFood/data/users.xml";
        String rootNameUsers = "users";

        SystemMyFood system = new SystemMyFood(filePathUsers, rootNameUsers);

        String[] args2 = {"br.ufal.ic.p2.MyFood.Facade",
                "tests/us1_1.txt", "tests/us1_2.txt",
                "tests/us2_1.txt", "tests/us2_2.txt",
                "tests/us3_1.txt", "tests/us3_2.txt",
                "tests/us4_1.txt", "tests/us4_2.txt",
        };
        EasyAccept.main(args2);
    }
}