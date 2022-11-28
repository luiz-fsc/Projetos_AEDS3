import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;
import java.util.List;

public class Main {
    
    public static void main(String[] args){

        List<String> email = Arrays.asList("luizfs@gmail.com", "carmoluizfs@gmail.com");
        
        try {

            RandomAccessFile raf = new RandomAccessFile("contasRAF.db", "rwd");
            //raf.writeInt(0);//1º id de cabeçalho;
            
            /*CRUD.create(raf, "Luiz Fernando", "02056147602", "BH", email, "luizada", "1234", (float) 5800.80);
            CRUD.create(raf, "Ana", "01234567890", "BH", email, "analu", "1234", (float) 1000.80);
            CRUD.create(raf, "Antonio", "51380650682", "BH", email, "toninho", "1234", (float) 500800.80);*/
            
            //System.out.println(CRUD.read(raf, 1).toString());

            /*Conta contaAtt = new Conta(3, "Antonio Luiz", email, "toninho", "1234", "51380650682", "BH", (float) 500800.80);
            CRUD.update(contaAtt, raf);

            Conta contaAtt2 = new Conta(1, "Luiz", email, "luizada", "1234", "02056147602", "BH", (float) 5800.80);
            CRUD.update(contaAtt2, raf);*/

            //System.out.println(CRUD.read(raf, 3).toString());

            //System.out.println(CRUD.delete(raf, 1));
            
            
            //CRUD.delete(raf, 3);
            //System.out.println(CRUD.read(raf, 3).toString());

            boolean resp = CRUD.validaNomeUser("analu", raf);
            System.out.println("o nome é: " + (resp));
            raf.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }


        
    }
}

