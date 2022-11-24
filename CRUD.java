import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.List;

public class CRUD {

    public static void create(RandomAccessFile raf, String nome, String cpf, String cidade, List<String> emails, String usuario, String senha, float saldo){
        Conta cliente = new Conta(0,nome, emails, usuario, senha, cpf, cidade, saldo);
        try {
            //Gerando o novo id para a nova conta e atualizando o cabeçalho
            //===========================================================================
            raf.seek(0);
            cliente.idConta = (raf.readInt() + 1);
            raf.seek(0);
            raf.writeInt(cliente.idConta);
            //===========================================================================
            
            //Gravando os dados do registro
            //===========================================================================
            raf.seek(raf.length());
            byte[] ba = cliente.toByteArray();
            raf.writeInt(ba.length); //tamanho registro
            raf.write(ba); // dados
            //===========================================================================
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Conta read(RandomAccessFile raf,int id){
        Conta resp = null;
        try {
            raf.seek(0);
            raf.readInt();
            long cabecote = raf.getFilePointer();
            while(cabecote < raf.length()){
                int lenReg = raf.readInt();
                byte[] ba = new byte[lenReg];
                raf.read(ba);
                Conta cTemp = new Conta();
                cTemp.fromByteArray(ba);
                if(cTemp.lapide == 1){
                    if(cTemp.idConta == id){
                        resp = cTemp;
                        cabecote = raf.length();
                    }else{
                        cabecote = raf.getFilePointer();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return resp;
    }

}
