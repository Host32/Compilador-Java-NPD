/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package npd.parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import npd.scanner.Scanner;
import npd.scanner.Token;

/**
 * Recursivo descendente em busca da gramatica na serie de tokens encontrados
 */
public class Parser {
    public Scanner scanner;
    public java.util.Scanner input;
    
    public Token cacheToken;
    
    public Map<String,Integer> symbolTable;
    
    /**
     * Inicia o Scanner e prepara o arquivo para leitura
     * 
     * @param nomeArquivo
     * @throws ScannerException 
     */
    public Parser(String nomeArquivo) throws ScannerException{
        try {
            scanner = new Scanner(nomeArquivo);
            symbolTable = new HashMap();
            
            input = new java.util.Scanner(System.in);
        } catch (IOException ex) {
            throw new ScannerException(ex.getMessage());
        }
    }
    
    /**
     * Obtem o proximo token lido pelo scanner e o armazena em cache, a partir
     * da primeira leitura, retorna sempre o token que está em cache.
     * 
     * @return 
     */
    public Token getToken(){
        if(cacheToken == null){
            cacheToken = scanner.getToken();
        }
        return cacheToken;
    }
    
    /**
     * Compara o token passado com o token que esta em cache, se forem iguais
     * ele le o proximo token do scanner e substitui o que esta em cache por ele
     * 
     * @param t
     * @return
     * @throws ParserException em caso do token passado nao for igual ao token que esta em cache
     */
    public Token match(Token t) throws ParserException{
        if(t.equals(cacheToken)){
            cacheToken = scanner.getToken();
            return t;
        }
        else{
            throw new ParserException("Tentativa de encontrar " + t.toString() + " mas foi encontrado " + cacheToken.toString());
        }
    }
    
    /**
     * Controla o acesso a symbol table, se a ID nao existir ela cria o espaço para ela
     * e o inicia com 0
     * 
     * @param id
     * @return 
     */
    public Integer getFromTable(String id){
        Integer retorno = symbolTable.get(id);
        
        if(retorno == null){
            symbolTable.put(id,0);
            retorno = 0;
        }
        
        return retorno;
    }
    
    /**
     * (id) -> ID
     * 
     * @return Integer salvo no symbolTable para aquele ID
     * @throws ParserException 
     */
    public Integer id() throws ParserException{
        Token t = getToken();
        if(t.equals(Token.ID)){
            Integer retorno = getFromTable(scanner.buffer);
            
            match(Token.ID);
            
            return retorno;
        }
        else{
            throw new ParserException( "ID não encontrado: " + t.toString() );
        }
    }
    
    /**
     * (op) -> PLUS_OP;
     * (op) -> MINUS_OP;
     * 
     * @throws ParserException 
     * @return  o token
     */
    public Token op() throws ParserException{
        Token t = getToken();
        if(t.equals(Token.MINUS_OP)){
            match(Token.MINUS_OP);
            return Token.MINUS_OP;
        }
        else if(t.equals(Token.PLUS_OP)){
            match(Token.PLUS_OP);
            return Token.PLUS_OP;
        }
        else{
            throw new ParserException( "OP não encontrado: " + t.toString() );
        }
    }
    
    /**
     * (primary) -> L_PAREN (expression) R_PAREN;
     * (primary) -> (id);
     * (primary) -> INT_LITERAL;
     * 
     * @throws ParserException 
     * @return 
     */
    public Integer primary() throws ParserException{
        Token t = getToken();
        
        Integer retorno = null;
        
        if(t.equals(Token.L_PAREN)){
            match(Token.L_PAREN); retorno = expression(); match(Token.R_PAREN);
        }
        else if(t.equals(Token.ID)){
            retorno = id();
        }
        else if(t.equals(Token.INT_LITERAL)){
            retorno = Integer.parseInt(scanner.buffer);
            match(Token.INT_LITERAL);
        }
        else{
            throw new ParserException( "Primary não encontrado: " + t.toString() );
        }
        
        return retorno;
    }
    
    /**
     * (expression) -> (primary) { (op) (primary) };
     * 
     * @throws ParserException 
     * @return
     */
    public Integer expression() throws ParserException{
        return expression(false);
    }
    public Integer expression(boolean subtrai) throws ParserException{
        Integer retorno;
        
        retorno = primary();
        
        if(subtrai){
            retorno *= -1;
        }
        
        try{
            Token op = op(); 
            Integer nextValue;
            
            if(op.equals(Token.MINUS_OP))
                nextValue = expression(true);
            else
                nextValue = expression();
                
            retorno += nextValue;
        }
        catch(ParserException e){}
        
        return retorno;
    }
    
    /**
     * (exprList) -> (expression) { COMMA (exprList) };
     * 
     * @throws ParserException 
     * @return 
     */
    public List<Integer> exprList() throws ParserException{
        List<Integer> retorno = new ArrayList();
        retorno.add(expression());
        
        try{
            Token t = getToken();
            if(t.equals(Token.COMMA)){
                match(Token.COMMA); 
                retorno.addAll(exprList());
            }
        }
        catch(ParserException e){}
        
        return retorno;
    }
    
    /**
     * (idList) -> (id) { COMMA (idList) };
     * 
     * @throws ParserException 
     * @return 
     */
    public List<String> idList() throws ParserException{
        Token t = getToken();
        
        List<String> retorno = new ArrayList();
        
        if(t.equals(Token.ID)){
            retorno.add(scanner.buffer);
            id();
            
            try{
                t = getToken();
                if(t.equals(Token.COMMA)){
                    match(Token.COMMA); 
                    retorno.addAll(idList());
                }
            }
            catch(ParserException e){}
        }
        else{
            throw new ParserException( "ID não encontrado: " + t.toString() );
        }
        
        return retorno;
    }
    
    /**
     * (statement) -> (id) ASSIGN_OP (expression);
     * (statement) -> READ L_PAREN (idList) R_PAREN;
     * (statement) -> WRITE L_PAREN (exprList) R_PAREN;
     * 
     * @throws ParserException 
     */
    public void statement() throws ParserException{
        Token t = getToken();
        
        if(t.equals(Token.ID)){
            String var = scanner.buffer;
            match(Token.ID); 
            match(Token.ASSIGN_OP); 
            Integer val = expression();
            
            symbolTable.put(var, val);
        }
        else if(t.equals(Token.READ)){
            match(Token.READ); 
            match(Token.L_PAREN); 
            List<String> ids = idList(); 
            match(Token.R_PAREN);
            for( String id : ids){
                System.out.print("Digite um valor para " + id + ": ");
                int val = input.nextInt();
                
                symbolTable.put(id, val);
            }
        }
        else if(t.equals(Token.WRITE)){
            match(Token.WRITE); 
            match(Token.L_PAREN); 
            List<Integer> values = exprList(); 
            match(Token.R_PAREN);
            
            for(Integer val : values)
                System.out.println(val);
        }
        else{
            throw new ParserException( "Statement não encontrado: " + t.toString() );
        }
    }
    
    /**
     * (statementList) -> (statement) SEMICOLON { (statementList) };
     * 
     * @throws ParserException 
     */
    public void statementList() throws ParserException{
        statement();
        match(Token.SEMICOLON);
        
        try{
            statementList();
        }
        catch(ParserException e){}
    }
    
    /**
     * (program) -> BEGIN (statementList) END;
     * 
     * @throws ParserException 
     */
    public void program() throws ParserException{
        Token t = getToken();
        
        if(t.equals(Token.BEGIN)){
            match(Token.BEGIN); statementList(); match(Token.END);
        }
        else{
            throw new ParserException( "Program não encontrado: " + t.toString() );
        }
    }
    
    /**
     * (systemGoal) -> (programa) SCAN_EOF;
     * 
     * @throws ParserException 
     */
    public void systemGoal() throws ParserException{
        program();
        match(Token.SCAN_EOF);
    }
}
