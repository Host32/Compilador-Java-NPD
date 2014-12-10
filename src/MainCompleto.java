
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
public class MainCompleto {
    public static void main(String [] args){
        try{
            Parser p = new Parser("teste3.npd");
            
            p.systemGoal();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
