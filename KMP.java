import java.io.IOException;
import java.io.RandomAccessFile;

public class KMP {
    //realizara a pesquisa uma pesquisa sequencial, buscando o padrao em caada username presente. OBS.: RETORNARA APENAS O PRIMEIRO RESULTADO!
    public static Conta pesquisa(String entrada)throws IOException{
        Conta resp = null;
        RandomAccessFile raf = new RandomAccessFile("clientes.db", "r");
        int pos = 1; //ponteiro para leitura sequencial
        try{
            resp = kmp(entrada,CRUD.read(raf, pos));
        }catch(Exception e){}//exception ira rolar se alcancarmos o final da posicao
        return resp;
    }
    
    //pesquisa sequencial de todos os usernames presentes no arquivo, retornando null caso nao encontre nada
    private static Conta kmp(String entrada, Conta conta){
        String username = conta.nomeUsuario;
        int tamEntrada = entrada.length();
        int tamUsername = username.length();

        //Pre-processamento do array
        int tamPadrao[] = new int[tamEntrada];
        calculaArray(username, tamEntrada, tamPadrao);

        int pointerE = 0; //ponteiro para a entrada
        int pointerU = 0; //ponteiro para o username
        while((tamUsername-pointerU)>=(tamEntrada-pointerE)){
            if(entrada.charAt(pointerU)==username.charAt(pointerE)){
                pointerE++;
                pointerU++;
            }
            if(pointerE == tamEntrada){ //resultado encontrado
                return conta;
            }else if(pointerU<tamUsername && entrada.charAt(pointerU)!=username.charAt(pointerE)){
                if(tamEntrada !=0){tamEntrada = tamPadrao[tamEntrada-1];}else{pointerU++;}
            }
        }
        //caso nao encontre nada
        return null;
    }

     //checa o tamanho da entrada e compara com o tamanho do username
    private static void calculaArray(String padrao, int tamEntrada, int tamPadrao[]){
        int tam = 0;
        int i=1;
        tamPadrao[0]=0;

        while(i<tamEntrada){
            if(padrao.charAt(i) == padrao.charAt(tam)){
                tam++;
                tamPadrao[i] = tam;
                i++;
            }else{ 
                if(tam !=0){
                    tam = tamPadrao[tam-1];
                }else{
                    tamPadrao[i]=tam;
                    i++;
                }
            }
        }
        
    } 
}
