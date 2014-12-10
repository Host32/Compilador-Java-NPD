
import npd.scanner.Scanner;
import npd.scanner.Token;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ivan
 */
public class Testes {
    public static void main(String [] args){
        String erro = "Erro, nao encontrou Token ";
        String ok = "Passou no teste do Token ";
        
        try{
            System.out.println("Iniciando teste do arquivo teste1.npd");
            Scanner teste =  new Scanner("teste1.npd");
            Token t;
            
            t=teste.getToken();
            if(t!= Token.BEGIN)System.out.println(erro+"BEGIN");
            else System.out.println(ok+"BEGIN");
            
            t=teste.getToken();
            if(t!= Token.ID) System.out.println(erro+"ID");
            else System.out.println(ok+"ID");
            
            t=teste.getToken();
            if(t!= Token.ASSIGN_OP) System.out.println(erro+"ASSIGN_OP");
            else  System.out.println(ok+"ASSIGN_OP");
            
            t=teste.getToken();
            if(t!= Token.INT_LITERAL) System.out.println(erro+"INT_LITERAL");
            else System.out.println(ok+"INT_LITERAL");
            
            t=teste.getToken();
            if(t!= Token.END) System.out.println(erro+"END");
            else System.out.println(ok+"END");
            System.out.println("teste1.npd concluido");

            t=teste.getToken();
            if(t!= Token.SCAN_EOF) System.out.println(erro+"SCAN_EOF");
            else System.out.println(ok+"SCAN_EOF");
            
            /* teste 2 */
            
            System.out.println("Iniciando teste do arquivo teste2.npd");
            teste =  new Scanner("teste2.npd");
            
            t=teste.getToken();
            if(t!= Token.BEGIN)System.out.println(erro+"BEGIN");
            else System.out.println(ok+"BEGIN");
            
            t=teste.getToken();
            if(t!= Token.ID) System.out.println(erro+"ID");
            else System.out.println(ok+"ID");
            
            t=teste.getToken();
            if(t!= Token.ASSIGN_OP) System.out.println(erro+"ASSIGN_OP");
            else  System.out.println(ok+"ASSIGN_OP");
            
            t=teste.getToken();
            if(t!= Token.INT_LITERAL) System.out.println(erro+"INT_LITERAL");
            else System.out.println(ok+"INT_LITERAL");
            
            t=teste.getToken();
            if(t!= Token.END) System.out.println(erro+"END");
            else System.out.println(ok+"END");
            
            t=teste.getToken();
            if(t!= Token.SCAN_EOF) System.out.println(erro+"SCAN_EOF");
            else System.out.println(ok+"SCAN_EOF");
            System.out.println("teste1.npd concluido");
            
            /* teste 3 */
            
            System.out.println("Iniciando teste do arquivo teste3.npd");
            teste =  new Scanner("teste3.npd");
            
            t=teste.getToken();
            if(t!= Token.BEGIN)System.out.println(erro+"BEGIN");
            else System.out.println(ok+"BEGIN");
            
            t=teste.getToken();
            if(t!= Token.READ) System.out.println(erro+"READ");
            else System.out.println(ok+"READ");
            
            t=teste.getToken();
            if(t!= Token.L_PAREN) System.out.println(erro+"L_PAREN");
            else  System.out.println(ok+"L_PAREN");
            
            t=teste.getToken();
            if(t!= Token.ID) System.out.println(erro+"ID");
            else System.out.println(ok+"ID");
            
            t=teste.getToken();
            if(t!= Token.R_PAREN) System.out.println(erro+"R_PAREN");
            else System.out.println(ok+"R_PAREN");
            
            t=teste.getToken();
            if(t!= Token.SEMICOLON) System.out.println(erro+"SEMICOLON");
            else System.out.println(ok+"SEMICOLON");
            
            t=teste.getToken();
            if(t!= Token.ID) System.out.println(erro+"ID");
            else System.out.println(ok+"ID");
            
            t=teste.getToken();
            if(t!= Token.ASSIGN_OP) System.out.println(erro+"ASSIGN_OP");
            else System.out.println(ok+"ASSIGN_OP");
            
            t=teste.getToken();
            if(t!= Token.ID) System.out.println(erro+"ID");
            else System.out.println(ok+"ID");
            
            t=teste.getToken();
            if(t!= Token.SEMICOLON) System.out.println(erro+"SEMICOLON");
            else System.out.println(ok+"SEMICOLON");
            
            t=teste.getToken();
            if(t!= Token.WRITE) System.out.println(erro+"WRITE");
            else System.out.println(ok+"WRITE");
            
            t=teste.getToken();
            if(t!= Token.L_PAREN) System.out.println(erro+"L_PAREN");
            else  System.out.println(ok+"L_PAREN");
            
            t=teste.getToken();
            if(t!= Token.ID) System.out.println(erro+"ID");
            else System.out.println(ok+"ID");
            
            t=teste.getToken();
            if(t!= Token.R_PAREN) System.out.println(erro+"R_PAREN");
            else System.out.println(ok+"R_PAREN");
            
            t=teste.getToken();
            if(t!= Token.SEMICOLON) System.out.println(erro+"SEMICOLON");
            else System.out.println(ok+"SEMICOLON");
            
            t=teste.getToken();
            if(t!= Token.END) System.out.println(erro+"END");
            else System.out.println(ok+"END");
            
            t=teste.getToken();
            if(t!= Token.SCAN_EOF) System.out.println(erro+"SCAN_EOF");
            else System.out.println(ok+"SCAN_EOF");
            System.out.println("teste3.npd concluido");
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
