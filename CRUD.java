import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.List;

public class CRUD {

    public static void create(RandomAccessFile raf, String nome, String cpf, String cidade, List<String> emails, String usuario, String senha, float saldo){
        Conta cliente = new Conta(0,nome, emails, usuario, senha, cpf, cidade, saldo);
        try {
            raf.seek(0);
            cliente.idConta = (raf.readInt() + 1);
            raf.seek(0);
            raf.writeInt(cliente.idConta);
            byte[] ba = cliente.toByteArray();
            raf.seek(raf.length());
            raf.write(ba);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Conta read(RandomAccessFile raf,int id){
        
        try {
            raf.seek(0);
            raf.readInt();
            while()
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
