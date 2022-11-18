import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Menu {
    public static void main(String[] args) throws IOException {

        RandomAccessFile raf = new RandomAccessFile("banco.db", "rw");
        raf.writeInt(0);//escrevo zero para auxiliar na geração dos proximos ids das contas

        Scanner sc = new Scanner(System.in);
        int opcao = 0;

        System.out.println("Menu Inicial");
        System.out.println("Digite: \n1 - Abrir Conta \n2 - Realizar uma transferencia \n3 - Ler um Registro \n4 - Atualizar um Registro \n5 - Deletar um Registro \n6 - Ordenar Arquivo \n7 - sair");
        opcao = Integer.parseInt(sc.nextLine());

        while(opcao != 7){
            if(opcao == 1){
                //Criar uma conta
                System.out.println("Digite seu nome completo: ");
                String nome = sc.nextLine();

                System.out.println("CPF: ");
                String cpf = sc.nextLine();
                while(cpf.length() != 11){
                    System.out.println("CPF Invalido! Digite novamente: ");
                    cpf = sc.nextLine();
                }

                System.out.println("Cidade: ");
                String cidade = sc.nextLine();

                System.out.println("Emails (se tiver mais de um, separa por uma virgula): ");
                String emailAux = sc.nextLine();
                List<String> lista = Arrays.asList(emailAux.split(","));

                System.out.println("Nome de Usuário: ");
                String usuario = sc.nextLine();
                //verificação necessária

                System.out.println("Senha: ");
                String senha = sc.nextLine();
            }else if(opcao == 2){
                //realizar uma transferencia
                System.out.println("informe de qual conta: ");
                int id = Integer.parseInt(sc.nextLine());
            }else if(opcao == 3){
                
            }else if(opcao == 4){

            }else if(opcao == 5){

            }else if(opcao == 6){

            }
            System.out.println("Nova operacao: \n1 - Abrir Conta \n2 - Realizar uma transferencia \n3 - Ler um Registro \n4 - Atualizar um Registro \n5 - Deletar um Registro \n6 - Ordenar Arquivo \n7 - sair");
            opcao = Integer.parseInt(sc.nextLine());
        }

        
        raf.close();
        sc.close();
    }
}
