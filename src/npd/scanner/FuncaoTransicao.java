/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package npd.scanner;

/**
 * Determina as mudanças da maquina
 */
public class FuncaoTransicao {
    /**
     * estado inicial a ser comparado
     */
    Estado estadoAtual;
    
    /**
     * Objeto contenedor do método isValidChar(char c) que fica com a 
     * responsabilidade de testar se o 'c' passado torna essa regra aplicavel
     */
    Transicao transicao;
    
    /**
     * Apos passar no teste de transicao, este será o estado que a maquina estará
     */
    Estado estadoDestino;
    
    /**
     * Construtor
     * 
     * @param atual
     * @param funcao
     * @param destino 
     */
    public FuncaoTransicao(Estado atual, Transicao funcao, Estado destino){
        estadoAtual = atual;
        transicao = funcao;
        estadoDestino = destino;
    }
    
    /**
     * @param c character a ser testado
     * @return se esta funcao de transicao é valida para o c indicado
     */
    public boolean isCharacterTransicao(char c){
        return transicao.isValidChar(c);
    }
}
