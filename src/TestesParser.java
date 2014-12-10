
import npd.parser.Parser;
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
public class TestesParser {
    public static void main(String [] args){
        try{
            Parser p;
            
            p = new Parser("teste1.npd");
            System.out.println("Iniciando parser do arquivo teste1.npd");
            p.systemGoal();
            System.out.println("teste1 executado com sucesso.");
            
            p = new Parser("teste2.npd");
            System.out.println("Iniciando parser do arquivo teste2.npd");
            p.systemGoal();
            System.out.println("teste2 executado com sucesso.");
            
            p = new Parser("teste3.npd");
            System.out.println("Iniciando parser do arquivo teste3.npd");
            p.systemGoal();
            System.out.println("teste3 executado com sucesso.");
            
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
