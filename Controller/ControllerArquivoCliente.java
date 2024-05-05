package Controller;

import Model.Cliente;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


/**
 *
 * @author Luiz Galhardo
 */

public class ControllerArquivoCliente extends ControllerArquivoBinario{

    private ArrayList<Cliente> clientes = new ArrayList<Cliente>();

    
    
    public void removeElemento(Cliente cli) {
        clientes.remove(cli);
    }
    
    
    public Cliente atualizaCliente(Cliente cliente) {
        for (int i = 0; i < clientes.size(); i++) {
            if (cliente.getInd() == clientes.get(i).getInd()) {
                clientes.set(i, cliente);
                escreverCliente(true);
                return clientes.get(i);
            }
        }
        return null;
    }
    
    public Cliente consCliente(Cliente cliente) {
    for (int i = 0; i < clientes.size(); i++) {
        if (cliente.getCpf().equals(clientes.get(i).getCpf())) {
            return clientes.get(i);
        }
    }
    return null;
}


    /**
     * @return the clientes
     */
    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    /**
     * @param clientes the clientes to set
     */
    public void setClientes(ArrayList<Cliente> clientes) {
        this.clientes = clientes;
    }
    
    
    
    public boolean lerCliente() {
    try (ObjectInputStream leitor = new ObjectInputStream(new FileInputStream(arquivo))) {
        clientes = new ArrayList<>();
        while (true) {
            Cliente cliente = new Cliente();
            try {
                clientes = (ArrayList<Cliente>) leitor.readObject();
                clientes.add(cliente);
            } catch (EOFException e) {
                // Fim do arquivo
                break;
            }
        }
        return true;
    } catch (ClassNotFoundException | IOException erro) {
        System.err.println("Erro ao ler arquivo binário: " + erro.getMessage());
        return false;
    }
}
    public boolean escreverCliente(boolean append) {
        ObjectOutputStream escritor = null;
        try {
            
            escritor = CriaEscritorObjeto(arquivo);
            escritor.writeObject(clientes);
            escritor.close();
            return true;
        } catch (IOException erro) {
            System.err.println("Erro ao escrever arquivo binário: " + erro.getMessage());
            return false;
        }
    }

    public static ObjectOutputStream CriaEscritorObjeto(File arquivo) {
        ObjectOutputStream out = null;
        try {
            if (arquivo.exists()) {
                FileOutputStream fos = new FileOutputStream(arquivo, true);
                out = new ObjectOutputStream(fos) {

                    @Override
                    protected void writeStreamHeader() {  // do not write a header //nao escreve cabecalho

                    }
                };
            } else {
                FileOutputStream fos = new FileOutputStream(arquivo, true);
                out = new ObjectOutputStream(fos);
            }
        } catch (IOException erro) {
            System.out.println("Erro ao criar arquivo. " + erro);
        }
        return out;
    }
   

}
