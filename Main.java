import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;
import java.util.List;

public class Main {
    
    public static void main(String[] args){

        List<String> email = Arrays.asList("luizfs@gmail.com", "carmoluizfs@gmail.com");
        Conta c1 = new Conta(0, "Luiz Fernando", email, "luizfs448", "1234", "02056147602", "BH", (float) 1000.78);
        Conta c2 = new Conta(0, "Ana Luiza", email, "ana", "1234", "02056147602", "BH", (float) 540);
        Conta c3 = new Conta(0, "Antonio", email, "toin", "1234", "51380650682", "bh", (float)1500000);
        byte[] ba;
        


        try {

            RandomAccessFile raf = new RandomAccessFile("contasRAF.db", "rwd");
            raf.writeInt(0);//1º id de cabeçalho;
            

            raf.seek(0);
            c1.idConta = (raf.readInt() + 1);
            raf.seek(0);
            raf.writeInt(c1.idConta);

            raf.seek(raf.length());
            ba = c1.toByteArray(); // tranforma dados de c1 em array de bytes
            raf.writeInt(ba.length); // 2 - tamanho 
            raf.write(ba); // 3 - dados

            raf.seek(0);
            c2.idConta = (raf.readInt() + 1);
            raf.seek(0);
            raf.writeInt(c2.idConta);

            raf.seek(raf.length());
            ba = c2.toByteArray(); // tranforma dados de c1 em array de bytes
            raf.writeInt(ba.length); // 2 - tamanho 
            raf.write(ba); // 3 - dados

            raf.seek(0);
            c3.idConta = (raf.readInt() + 1);
            raf.seek(0);
            raf.writeInt(c3.idConta);

            raf.seek(raf.length());
            ba = c3.toByteArray(); // tranforma dados de c1 em array de bytes
            raf.writeInt(ba.length); // 2 - tamanho 
            raf.write(ba); // 3 - dados


            raf.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }


        
    }
}

