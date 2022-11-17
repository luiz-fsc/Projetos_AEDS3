import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.List;

public class Main {
    
    public static void main(String[] args) {

        List<String> email = Arrays.asList("luizfs@gmail.com", "carmoluizfs@gmail.com");
        Contas c1 = new Contas(1, "Luiz Fernando", email, "luizfs448", "1234", "02056147602", "BH", (float) 1000.78);
        Contas ctemp = new Contas();

        FileOutputStream arq;
        DataOutputStream dos;

        FileInputStream arq2;
        DataInputStream dis;

        byte[] ba;
        int len;

        try {
            
            arq = new FileOutputStream("contas.db");
            dos = new DataOutputStream(arq);

            dos.writeByte(c1.lapide); // 1 - lapide
            ba = c1.toByteArray(); // tranforma dados de c1 em array de bytes
            dos.writeInt(ba.length); // 2 - tamanho 
            dos.write(ba); // 3 - dados
            dos.close();

            arq2 = new FileInputStream("contas.db");
            dis = new DataInputStream(arq2);

            if(dis.readByte() == 1){
                len = dis.readInt();//inteiro referente ao tamanho do registro
                ba = new byte[len];
                dis.read(ba);
                ctemp.fromByteArray(ba);
                System.out.println(ctemp.toString());
            }



        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

