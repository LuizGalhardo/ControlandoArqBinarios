/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;


import Model.Fornecedor;
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
public class ControllerArquivoFornecedor extends ControllerArquivoBinario{
    
    private ArrayList<Fornecedor> fornes = new ArrayList<Fornecedor>();

    public boolean lerFornecedor() {
    try (ObjectInputStream leitor = new ObjectInputStream(new FileInputStream(arquivo))) {
        fornes = new ArrayList<>();
        while (true) {
            Fornecedor forn = new Fornecedor();
            try {
                fornes = (ArrayList<Fornecedor>) leitor.readObject();
                fornes.add(forn);
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
    public boolean escreverFornecedor(boolean append) {
        ObjectOutputStream escritor = null;
        try {
            
            escritor = CriaEscritorObjeto(arquivo);
            escritor.writeObject(fornes);
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
    
    public void removeElemento(Fornecedor forn) {
        fornes.remove(forn);
    }
    
     public Fornecedor atualizaForn(Fornecedor forn) {
        for (int i = 0; i < fornes.size(); i++) {
            if (forn.getInd() == fornes.get(i).getInd()) {
                fornes.set(i, forn);
                escreverFornecedor(true);
                return fornes.get(i);
            }
        }
        return null;
    }
    
    public Fornecedor consForn(Fornecedor forn) {
    for (int i = 0; i < fornes.size(); i++) {
        if (forn.getCnpj().equals(fornes.get(i).getCnpj())) {
            return fornes.get(i);
        }
    }
    return null;
}

    /**
     * @return the clientes
     */
    public ArrayList<Fornecedor> getFornes() {
        return fornes;
    }

    /**
     * @param clientes the clientes to set
     */
    public void setFornes(ArrayList<Fornecedor> fornes) {
        this.fornes = fornes;
    }
    
}
