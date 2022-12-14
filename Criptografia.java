import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io. DataInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream; 
import java.io.InputStream; 
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.spec.AlgorithmParameterSpec;  
import javax.crypto.spec.IvParameterSpec;   
public class Criptografia{
    //Para o caso de criptografia, optamos pelo uso do Data Encryption Standard(DES). Ele vai ler o texto a cada bloco de 64 bits, 
    //criptografar, enviar para outro arquivo e repetir até que todo o arquivo esteja corretamente criptografado
    //Para ajudar no desenvolvimento do codigo, utilizarei os pacotes "javax.crypto" e "java.security"
    
    //Criando duas variaveis estaticas para realizar a codificaçao e a decodificacao
    private static Cipher encript;
    private static Cipher decript;

    //Arquivos a serem manipulados:
    private final String texto = "clientes.db";
    private final String criptografado = "encripted.db";
    private final String descriptografado = "decripted.db";
    private final String chave = "chave.db"; //Esse arquivo aqui é para apenas garantir que a chave seja a mesma e mostrar sua existencia. NAO USE ISSO NA VIDA REAL!!! 

    //Metodo gerador de chaves
    private void gerarChave(){
        try{
            //Chave obtida atraves do metodo KeyGenerator
            SecretKey chave = KeyGenerator.getInstance("DES").generateKey();
            RandomAccessFile raf = new RandomAccessFile(this.chave, "rw");
            byte[] chaveGerada = chave.getEncoded();
            raf.write(chaveGerada); 
            raf.close();

        }catch(Exception e){System.out.println("Erro ao gerar chave!!\n" + e.getStackTrace());}
    }
    
    public void criptografar()throws Exception{
        //Primeiro checa se tem uma chave ja existente
        byte[] chaveGerada;
        do{
            RandomAccessFile rafChave = new RandomAccessFile(this.chave, "rw");
            byte[] ba = new byte[Integer.parseInt(Long.toString(rafChave.length()))];
            ByteArrayInputStream bais = new ByteArrayInputStream(ba);
            DataInputStream dis = new DataInputStream(bais);
            chaveGerada = dis.readAllBytes();
            rafChave.close();
            //Se o arquivo estiver vazio, irá criar uma nova chave e salvar no arquivo
            if(chaveGerada==null){
                gerarChave();
            }
        }while(chaveGerada==null);

        //preparando a encriptacao
        byte[] vetorInicializacao = { 22, 33, 11, 44, 55, 99, 66, 77 };
        AlgorithmParameterSpec aps = new IvParameterSpec(vetorInicializacao);  
        encript = Cipher.getInstance("DES/CBC/PKCS5Padding");
        SecretKey chaveSecreta = new SecretKeySpec(chaveGerada,"DES");
        encript.init(Cipher.ENCRYPT_MODE,chaveSecreta,aps);

        //definindo os arquivos de entrada e saida
        OutputStream output = new FileOutputStream(this.criptografado);
        InputStream input = new FileInputStream(this.texto);

        //realizando criptografia
        output = new CipherOutputStream(output,encript);
        //escrevendo o resultado no arquivo texto
        escreverBytes(input,output);
        output.close();
        input.close();
    }

    public void descriptografar()throws Exception{
        //Se nao houver chave, lancara mensagem de erro, uma vez que nada foi criptografado
        byte[] chaveGerada;
        RandomAccessFile rafChave = new RandomAccessFile(this.chave, "rw");
        byte[] ba = new byte[Integer.parseInt(Long.toString(rafChave.length()))];
        ByteArrayInputStream bais = new ByteArrayInputStream(ba);
        DataInputStream dis = new DataInputStream(bais);
        chaveGerada = dis.readAllBytes();
        rafChave.close();
            
        if(chaveGerada==null){
            throw new Exception("CHAVE NAO ENCONTRADA! NAO E POSSIVEL REALIZAR DECODIFICACAO!\n");
        }
        //preparando a encriptacao
        byte[] vetorInicializacao = { 22, 33, 11, 44, 55, 99, 66, 77 };
        AlgorithmParameterSpec aps = new IvParameterSpec(vetorInicializacao);  
        decript = Cipher.getInstance("DES/CBC/PKCS5Padding");
        SecretKey chaveSecreta = new SecretKeySpec(chaveGerada,"DES");
        decript.init(Cipher.DECRYPT_MODE,chaveSecreta,aps);

        //definindo os arquivos de entrada e saida
        OutputStream output = new FileOutputStream(this.descriptografado);
        InputStream input = new FileInputStream(this.texto);

        //realizando decodificacao
        input = new CipherInputStream(input, decript);
        //escrevendo o resultado no arquivo texto
        escreverBytes(input,output);
        output.close();
        input.close();
        
    }

    private static void escreverBytes(InputStream input, OutputStream output)throws IOException{
        byte[] Buffer = new byte[512];
        int lerBytes = 0;
        while((lerBytes=input.read(Buffer))>=0){
            output.write(Buffer,0,lerBytes);
        }
    }
        
    
    
    
    
}
