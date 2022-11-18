import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.util.Arrays;
import java.util.List;

public class Main {
    
    public static void main(String[] args) throws FileNotFoundException {

        List<String> email = Arrays.asList("luizfs@gmail.com", "carmoluizfs@gmail.com");
        Contas c1 = new Contas(1, "Luiz Fernando", email, "luizfs448", "1234", "02056147602", "BH", (float) 1000.78);
        Contas c2 = new Contas(2, "Ana Luiza", email, "ana", "1234", "02056147602", "BH", (float) 540);

        RandomAccessFile raf = new RandomAccessFile("contasRAF.db", "rwd");

        byte[] ba;
        int len;

        try {
            raf.writeByte(c1.lapide); // 1 - lapide
            ba = c1.toByteArray(); // tranforma dados de c1 em array de bytes
            raf.writeInt(ba.length); // 2 - tamanho 
            raf.write(ba); // 3 - dados

            //arq2 = new FileInputStream("contas.db");
            //dis = new DataInputStream(arq2);

            /*if(dis.readByte() == 1){
                len = dis.readInt();//inteiro referente ao tamanho do registro
                ba = new byte[len];
                dis.read(ba);
                ctemp.fromByteArray(ba);
                System.out.println(ctemp.toString());
            }*/



        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


        try {
            raf.writeByte(c2.lapide); // 1 - lapide
            ba = c2.toByteArray(); // tranforma dados de c1 em array de bytes
            raf.writeInt(ba.length); // 2 - tamanho 
            raf.write(ba); // 3 - dados
            raf.close();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}

