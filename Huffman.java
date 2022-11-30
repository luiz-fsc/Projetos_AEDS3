import java.io.RandomAccessFile;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.io.IOException;
//===========================================================================================
public class Huffman {
    public HashMap<Character,String> huffcode= new HashMap<>();
    No raiz;
    String decodedTxt="";
    private boolean jaRodouCriar = false; //checa se o metodo criar ja rodou dentro do escopo do codigo
    StringBuilder sb = new StringBuilder();
    //Classe no necessaria para criar a arvore de huffman
    class No{
        //valores
        private Character ch;
        private int freq;
        private No noEsq;
        private No noDir;
        //metodo construtor
        No(Character ch,int freq){
            this.ch=ch;
            this.freq=freq;
            this.noEsq=null;
            this.noDir=null;
        }
        //metodo construtor 2
        No(Character ch,int freq,No noEsq, No noDir){
            this.ch=ch;
            this.freq=freq;
            this.noEsq=noEsq;
            this.noDir=noDir;
        }
    }
        
        public void criar(String nomeArquivo)throws IOException{
            jaRodouCriar = true;
            RandomAccessFile raf = new RandomAccessFile(nomeArquivo, "rw");
            String linha;
            String texto=""; //Servira para fazer o stringbuilder
            //mapeamento dos caracteres
            HashMap<Character,Integer> frequencia = new HashMap<>();
            while(raf.getFilePointer()<raf.length()){
                //obtem uma nova linha e ira adicionar cada char presente dentro do map
                linha = raf.readLine();
                texto+=linha;
                for(char c:linha.toCharArray()){
                    frequencia.put(c,frequencia.getOrDefault(c, 0)+1);
                }
            }
            //fecha o arquivo uma vez que nao necessitaremos mais dele
            raf.close();
            //===============================================================================
            //CRIANDO A ARVORE DE HUFFMAN
            //menor frequencia == maior prioridade!!!
            PriorityQueue<No> arvore = new PriorityQueue<>(Comparator.comparingInt(l->l.freq));
            //para cada entrada presente irá criar um novo no
            for(var entrada:frequencia.entrySet()){
                arvore.add(new No(entrada.getKey(),entrada.getValue()));
            }
            //organizando a arvore
            while(arvore.size()!=1){
                //o objetivo aqui é obter o total das frequencias de cada no presente
                No esq = arvore.poll();
                No dir = arvore.poll();
                int soma = esq.freq + dir.freq;
                arvore.add(new No(null,soma,esq,dir));
            }
            //obtendo o no raiz
            this.raiz = arvore.peek();
            codificar(raiz,"",this.huffcode);
            //para checar a arvore de huffman: 
            //System.out.println(huffcode);
            //Adicionando todo o codigo mapeado dentro de uma unica string
            for(char c:texto.toCharArray()){
                //obtem a string codificada
                this.sb.append(huffcode.get(c));
            }
            //adicionara a codificacao dentro de um arquivo txt
            RandomAccessFile rafC = new RandomAccessFile("codificado.txt", "rw");
            rafC.writeUTF(this.sb.toString());
            rafC.close();
        }
        private void codificar(No raiz, String s, HashMap<Character,String> huffman){
            //checa se a arvore esta vazia
            if(raiz==null) return;
            //checa se o no e uma folha ou nao
            if(isFolha(raiz)) huffman.put(raiz.ch,s.length()>0? s:"1");
            //0 para a esquerda, 1 para a direita
            codificar(raiz.noEsq,s+'0',huffman);
            codificar(raiz.noDir,s+'1',huffman);
        }
        public void decodificar()throws IOException{
            RandomAccessFile raf = new RandomAccessFile("decodificado.txt", "rw");
            if (jaRodouCriar = false) criar("banco.db");
            if(isFolha(this.raiz)){
                while(this.raiz.freq-->0){
                    this.decodedTxt+=this.raiz.ch;
                }
            }else{
                int indice=-1;
                while(indice<this.sb.length()-1){
                    indice= decodificar(this.raiz,indice,this.sb);
                }
            }
            raf.writeUTF(decodedTxt);
            raf.close();
        }
        public int decodificar(No raiz, int indice,StringBuilder sb){
            //checa se a arvore está vazia
            if(raiz==null) return indice;
            //checa se o no e folha ou nao
            if(isFolha(raiz)){
                this.decodedTxt+=raiz.ch;
                return indice;
            }
            indice++;
            raiz = (sb.charAt(indice)=='0')?raiz.noEsq : raiz.noDir;
            indice = decodificar(raiz,indice,sb);
            return indice;
        }
        private boolean isFolha(No raiz){
            return raiz.noDir==null && raiz.noEsq==null;
        }
    }
