/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;



import Model.Produto;
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
public class ControllerArquivoProduto extends ControllerArquivoBinario {

    private ArrayList<Produto> produtos = new ArrayList<Produto>();

    public boolean lerProduto() {
    try (ObjectInputStream leitor = new ObjectInputStream(new FileInputStream(arquivo))) {
        produtos = new ArrayList<>();
        while (true) {
            Produto prod = new Produto();
            try {
                produtos = (ArrayList<Produto>) leitor.readObject();
                produtos.add(prod);
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
    public boolean escreverProd(boolean append) {
        ObjectOutputStream escritor = null;
        try {
            
            escritor = CriaEscritorObjeto(arquivo);
            escritor.writeObject(produtos);
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
    
    public void removeElemento(Produto prod) {
        produtos.remove(prod);
    }
    
    

    
    
    public Produto atualizaProduto(Produto prod) {
        for (int i = 0; i < produtos.size(); i++) {
            if (prod.getInd() == produtos.get(i).getInd()) {
                produtos.set(i, prod);
                escreverProd(true);
                return produtos.get(i);
            }
        }
        return null;
    }
    
     public Produto consProd(Produto produto) {
    for (int i = 0; i < produtos.size(); i++) {
        if (produto.getDesc().equals(produtos.get(i).getDesc())) {
            return produtos.get(i);
        }
    }
    return null;
}
    
    /**
     * @return the clientes
     */
    public ArrayList<Produto> getProdutos() {
        return produtos;
    }

    /**
     * @param clientes the clientes to set
     */
    public void setProdutos(ArrayList<Produto> produtos) {
        this.produtos = produtos;
    }
    
    

}
