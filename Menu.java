import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Menu {
    public static void main(String[] args) throws IOException, InterruptedException {

        //Organização inicial para funcionamento do programa
        //====================================================================================
        RandomAccessFile raf = new RandomAccessFile("banco.db", "rw");
        raf.writeInt(0);//escrevo zero para auxiliar na geração dos proximos ids das contas
        Scanner sc = new Scanner(System.in);
        int opcao = 0;
        //====================================================================================


        System.out.println("Menu Inicial");
        System.out.println("Digite: \n1 - Abrir Conta \n2 - Realizar uma transferencia \n3 - Ler um Registro \n4 - Atualizar um Registro \n5 - Deletar um Registro \n6 - Ordenar Arquivo \n7 - Codificar/descodificar\n8 - sair");
        opcao = Integer.parseInt(sc.nextLine());

        //limpando console
        //====================================================================================
        if (System.getProperty("os.name").contains("Windows"))
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        else
            Runtime.getRuntime().exec("clear");
        //=====================================================================================

        
        while(opcao != 8){
            switch(opcao){ 
                case 1:
                    //Criar uma conta
                    //nome
                    System.out.println("Digite seu nome completo: ");
                    String nome = sc.nextLine();

                    //CPF
                    System.out.println("CPF: ");
                    String cpf = sc.nextLine();
                    while(cpf.length() != 11){
                        System.out.println("CPF Invalido! Digite novamente: ");
                        cpf = sc.nextLine();
                    }

                    //Cidade
                    System.out.println("Cidade: ");
                    String cidade = sc.nextLine();

                    //emails
                    System.out.println("Emails (se tiver mais de um, separa por uma virgula): ");
                    String emailAux = sc.nextLine();
                    List<String> listaEmails = Arrays.asList(emailAux.split(","));

                    //usuario
                    System.out.println("Nome de Usuário: ");
                    String usuario = sc.nextLine();
                    while(CRUD.validaNomeUser(usuario, raf) == false){
                        System.out.println("Este nome de usuário já está sendo usado, por favor digite outro: ");
                        usuario = sc.nextLine();
                    }
                    //verificação necessária

                    //senha
                    System.out.println("Digite uma senha: ");
                    String senha = sc.nextLine();

                    //saldo conta
                    System.out.println("Digite o saldo da sua conta: ");
                    float saldoConta = Integer.parseInt(sc.nextLine());

                    CRUD.create(raf, nome, cpf, cidade, listaEmails, usuario, senha, saldoConta);
                break;
                case 2:
                    //realizar uma transferencia
                    System.out.println("informe de qual conta: ");
                    //int id = Integer.parseInt(sc.nextLine());
                break;
                case 3:
                    //ler um registro
                    System.out.println("informe de qual conta: ");
                break;
                case 4:
                break;
                case 5:
                break;
                case 6:
                break;
                case 7:
                    int opt=0;
                    RandomAccessFile codedRaf = new RandomAccessFile("codificado.txt", "rw");
                    while(opt!=3){
                        System.out.println("Digite a opcao desejada:\n 1-Codificar \n 2-Descodificar \n 3-Sair");
                        opt=Integer.parseInt(sc.nextLine());
                        try{
                            Huffman h = new Huffman();
                            if(opt == 1){
                                System.out.println("Selecione o método:\n 1-Huffman\n 2-LZW\n");
                                opt=Integer.parseInt(sc.nextLine());
                                if(opt==1){
                                    h.criar("banco.db");
                                }else if(opt==2){
                                    //adicione o LZW aqui
                                }
                            }else if(opt == 2){
                                if(codedRaf.readLine()!=""){
                                    System.out.println("Selecione o método:\n 1-Huffman\n 2-LZW\n");
                                    if(opt==1){
                                        h.decodificar();
                                    }else if(opt==2){
                                        //adicione o LZW aqui
                                    }
                                }
                            }
                        }catch(IOException e){System.out.println(e);}
                        
                    }
                   codedRaf.close();

                break;
            }
            System.out.println("Nova Operacao: \n1 - Abrir Conta \n2 - Realizar uma transferencia \n3 - Ler um Registro \n4 - Atualizar um Registro \n5 - Deletar um Registro \n6 - Ordenar Arquivo \n7 - Codificar/descodificar\n8 - sair");
            opcao = Integer.parseInt(sc.nextLine());
        }
             
        System.out.println("O banco agradece a sua prioridade");
        raf.close();
        sc.close();
    }        
}
