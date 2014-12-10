
import npd.parser.Parser;
import npd.parser.ParserException;
import npd.parser.ScannerException;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ivan
 */
public class Main {
    public static void main(String [] args) throws ParserException, ScannerException{
        Parser p = new Parser(args[0]);
        
        p.systemGoal();
    }
}
