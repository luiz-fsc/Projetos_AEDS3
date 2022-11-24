import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Conta{

    protected byte lapide;
    protected int idConta;
    protected String nomePessoa;
    protected List<String> emails;
    protected String nomeUsuario;
    protected String senha;
    protected String cpf;
    protected String cidade;
    protected int transferenciasRealizadas;
    protected float saldoConta;

    Conta(int idConta, String nome, List<String> emails, String usuario, String senha, String cpf, String cidade, float saldoConta){
        this.lapide = 1;
        this.idConta = idConta;
        this.nomePessoa = nome;
        this.emails = emails;
        this.nomeUsuario = usuario;
        this.senha = senha;
        this.cpf = cpf;
        this.cidade = cidade;
        this.transferenciasRealizadas = 0;
        this.saldoConta = saldoConta;
    }

    Conta(){
        this.idConta = -1;
        this.nomePessoa = "";
        this.nomeUsuario = "";
        this.senha = "";
        this.cpf = "00000000000";
        this.cidade = "";
        this.transferenciasRealizadas = -1;
        this.saldoConta = -1;
    }

    public String toString(){
        return "ID: " + idConta 
        + "\nNome: " + nomePessoa 
        + "\nemails: " + emails.toString() 
        + "\nUsuário: " + nomeUsuario 
        + "\nSenha: " + senha 
        + "\nCPF: " + cpf 
        + "\nCidade: " + cidade 
        + "\nNumero de Transferências: " + transferenciasRealizadas 
        + "\nsaldo: " + saldoConta;
    }

    //transforma em um array de bytes as informações da instância
    public byte[] toByteArray() throws IOException{

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);

        dos.writeByte(lapide);
        dos.writeInt(idConta);
        dos.writeUTF(nomePessoa);
        dos.writeInt(emails.size());// escrevo a quantidade de emails que o usuário tem
        for(String email : emails){
            dos.writeUTF(email);//escrevo todos os emails
        }
        dos.writeUTF(nomeUsuario);
        dos.writeUTF(senha);
        dos.writeUTF(cpf);
        dos.writeUTF(cidade);
        dos.writeInt(transferenciasRealizadas);
        dos.writeFloat(saldoConta);
        
        return baos.toByteArray();
    }

    public void fromByteArray(byte ba[]) throws IOException{

        ByteArrayInputStream bais = new ByteArrayInputStream(ba);
        DataInputStream dis = new DataInputStream(bais);

        List <String> aux = new ArrayList<String>();
        int numEmails;

        lapide = dis.readByte();
        idConta = dis.readInt();
        nomePessoa = dis.readUTF();
        numEmails = dis.readInt();
        for(int i = 0; i < numEmails; i++){
            aux.add(dis.readUTF());
        }
        emails = aux;
        nomeUsuario = dis.readUTF();
        senha = dis.readUTF();
        cpf = dis.readUTF();
        cidade = dis.readUTF();
        transferenciasRealizadas = dis.readInt();
        saldoConta = dis.readFloat();
    
    }
    
    public void realizaTransf(int quantidade){
        if(saldoConta > quantidade){
            saldoConta -= quantidade;
            transferenciasRealizadas++;
        }else{
            System.out.println("Saldo na conta insuficiente para realizar transferencia!");
        }
    }

    public void recebeTransf(int quantidade){
        saldoConta += quantidade;
        transferenciasRealizadas++;
    }



}