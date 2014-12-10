
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
public class MainScanner {
    public static void main(String [] args){
        try{
            Scanner teste =  new Scanner(args[0]);
            Token t;
            do{
                t = teste.getToken();
                System.out.println(t.toString()+": "+teste.buffer);
                //i.nextLine();
            }while(t != Token.INVALIDO && t != Token.SCAN_EOF);

        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
