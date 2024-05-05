/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Luiz Galhardo
 */

public class Produto implements java.io.Serializable {

    private static long serialVersionUID = 1L;
    private String desc;
    private String quantidade;
    private String pavista;
    private String paprazo;
    private int ind;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(String quantidade) {
        this.quantidade = quantidade;
    }

    public String getPavista() {
        return pavista;
    }

    public void setPavista(String pavista) {
        this.pavista = pavista;
    }

    public String getPaprazo() {
        return paprazo;
    }

    public void setPaprazo(String paprazo) {
        this.paprazo = paprazo;
    }

    public int getInd() {
        return ind;
    }

    public void setInd(int ind) {
        this.ind = ind;
    }
    
    
    
}
