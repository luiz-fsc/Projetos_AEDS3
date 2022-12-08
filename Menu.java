import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Menu {
    public static void main(String[] args) throws IOException, InterruptedException {
        //Setando o arquivo principal de armazenamento de dados
        final String arquivodb = "clientes.db";

        //Organização inicial para funcionamento do programa
        //====================================================================================
        RandomAccessFile raf = new RandomAccessFile(arquivodb, "rw");
        raf.writeInt(0);//escrevo zero para auxiliar na geração dos proximos ids das contas
        Scanner sc = new Scanner(System.in);
        int opcao = 0;
        Conta conta = null;
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
                    System.out.println("E-mail (se tiver mais de um, separe por uma virgula): ");
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
                    System.out.println("informe o ID da conta a enviar: ");
                    Conta enviar =  CRUD.read(raf, Integer.parseInt(sc.nextLine()));
                    System.out.println("digite o valor desejado: ");
                    float valor = Float.parseFloat(sc.nextLine());
                    System.out.println("Informe o ID da conta a receber: ");
                    Conta receber = CRUD.read(raf, Integer.parseInt(sc.nextLine()));
                    enviar.Transferencia(receber, valor);
                    //atualizando os novos valores
                    CRUD.update(enviar, receber, raf);
                break;
                case 3:
                    //ler um registro
                    //adicionando o algoritmo de casamento de padroes de Knutt-Morris-Pratt (KMP) para obter a pesquisa através do nome de usuario
                    opcao=0;
                    while(opcao!=1 && opcao!=2){
                        System.out.println("Digite o metodo a realizar a pesquisa:\n 1 - ID do usuario (CRUD)\n 2 - Nome de usuario (KMP)\n");
                        opcao = Integer.parseInt(sc.nextLine());
                        if(opcao == 1){
                            System.out.println("informe o ID da conta: ");
                            conta = CRUD.read(raf, Integer.parseInt(sc.nextLine()));
                            System.out.println(conta.toString());
                        }else if(opcao == 2){
                            System.out.println("informe o nome de usuario: ");
                            String username = sc.nextLine();
                            Conta resp = KMP.pesquisa(username);
                            if(resp==null){
                                System.out.println("Resultado nao encontrado!!");
                            }else{
                                System.out.println(resp.toString());
                            }
                        }
                    }
                    
                break;
                case 4:
                    //atualizar um registro
                    System.out.println("informe o ID da conta: ");
                    conta = CRUD.read(raf, Integer.parseInt(sc.nextLine()));
                    int opti=0;
                    while(opti<1 && opti>4){
                        System.out.println("Digite a opcao que deseja alterar:\n 1 - Cidade\n 2 - Email\n 3 - Senha\n 4 - Nome de usuario\n");
                        opti= Integer.parseInt(sc.nextLine());
                        switch(opti){
                            case 1:
                                //Atualizar cidade
                                System.out.println("Digite a nova Cidade: ");
                                String altCidade = sc.nextLine();
                                conta.cidade=altCidade;
                            break;
                            case 2:
                                //Atualizar Email
                                opti=0;
                                while(opti!=1 && opti!=2){
                                    
                                }
                                System.out.println("Deseja adicionar um novo email(1) ou alterar o existente(2)?\n");
                                opti= Integer.parseInt(sc.nextLine());
                                if(opti==1){
                                    System.out.println("Digite o novo email a ser inserido: ");
                                    String newEmail = sc.nextLine();
                                    conta.emails.add(newEmail);
                                }else{
                                    System.out.println("Altere o E-mail (se tiver mais de um, separe por uma virgula): ");
                                    String emailAlt = sc.nextLine();
                                    conta.emails = Arrays.asList(emailAlt.split(","));
                                }
                            break;
                        }
                    }
                    //Atualizando o registro para o arquivo
                    CRUD.update(conta,raf);                    
                break;
                case 5:
                    //Deletar uma conta
                    System.out.println("informe o ID da conta a deletar: ");
                    CRUD.delete(raf, Integer.parseInt(sc.nextLine()));
                break;
                case 6:
                    Intercalacao.IBC();
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
                                    h.criar(arquivodb);
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
