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
        System.out.println("Digite: \n1 - Abrir Conta \n2 - Realizar uma transferencia \n3 - Ler um Registro \n4 - Atualizar um Registro \n5 - Deletar um Registro \n6 - Ordenar Arquivo \n7 - sair");
        opcao = Integer.parseInt(sc.nextLine());

        //limpando console
        //====================================================================================
        if (System.getProperty("os.name").contains("Windows"))
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        else
            Runtime.getRuntime().exec("clear");
        //=====================================================================================

        
        while(opcao != 7){

            if(opcao == 1){
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
                //verificação necessária

                //senha
                System.out.println("Senha: ");
                String senha = sc.nextLine();

                //saldo conta
                System.out.println("Digite o saldo da sua conta: ");
                float saldoConta = Integer.parseInt(sc.nextLine());

                CRUD.create(raf, nome, cpf, cidade, listaEmails, usuario, senha, saldoConta);

            }else if(opcao == 2){
                //realizar uma transferencia
                System.out.println("informe de qual conta: ");
                //int id = Integer.parseInt(sc.nextLine());
            }else if(opcao == 3){
                
            }else if(opcao == 4){

            }else if(opcao == 5){

            }else if(opcao == 6){

            }
            System.out.println("Nova operacao: \n1 - Abrir Conta \n2 - Realizar uma transferencia \n3 - Ler um Registro \n4 - Atualizar um Registro \n5 - Deletar um Registro \n6 - Ordenar Arquivo \n7 - sair");
            opcao = Integer.parseInt(sc.nextLine());
        }

        System.out.println("O banco agradece a sua prioridade");
        raf.close();
        sc.close();
    }
}
