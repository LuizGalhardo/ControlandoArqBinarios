
package Controller;


import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


/**
 *
 * @author Luiz Galhardo
 */
public class ControllerArquivoBinario extends ControllerArquivo {

   
    ObjectInputStream leitor = null;
    ObjectOutputStream escritor = null;

    

    @Override
    public boolean ler() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean escrever(boolean append) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}

