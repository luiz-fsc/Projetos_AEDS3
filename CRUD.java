import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.List;

public class CRUD {

    public static void create(RandomAccessFile raf, String nome, String cpf, String cidade, List<String> emails, String usuario, String senha, float saldo){
        Contas temp = new Contas(0,nome, emails, usuario, senha, cpf, cidade, saldo);
        try {
            raf.seek(0);
            temp.idConta = (raf.readInt() + 1);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
       

    }

}
