/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package npd.parser;

public class ScannerException extends Exception{
    private final String erroMsg;
    
    public ScannerException(String erroMsg){
        this.erroMsg = erroMsg;
    }
    
    @Override
    public String getMessage(){
        return "Erro de leitura do arquivo: " + erroMsg;
    }
}
