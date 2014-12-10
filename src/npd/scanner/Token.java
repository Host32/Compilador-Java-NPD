/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package npd.scanner;

/**
 * Enum de Tokens
 */
public enum Token {
    BEGIN, // palavra reservada "inicio"
    END, // palavra reservada "fim"
    READ, // palavra reservada "leia"
    WRITE, // palavra reservada "escreva"
    INT_LITERAL, // inteiro
    L_PAREN, // (
    R_PAREN, // )
    ID, // qualquer palavra que nao seja reservada nao iniciada por numero
    SEMICOLON, // ;
    COMMA, // ,
    ASSIGN_OP, // :=
    PLUS_OP, // +
    MINUS_OP, // -
    SCAN_EOF, // fim do arquivo
    INVALIDO; // token invalido
}
