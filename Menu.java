import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Menu {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcao = 0;
        System.out.println("Menu Inicial");
        System.out.println("Digite: \n1 - Abrir Conta \n2 - Realizar uma transferencia \n3 - Ler um Registro \n4 - Atualizar um Registro \n5 - Deletar um Registro \n6 - Ordenar Arquivo");
        opcao = Integer.parseInt(sc.nextLine());
        
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
            
        }

        

        sc.close();
    }
}
