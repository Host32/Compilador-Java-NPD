/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package npd.scanner.transicoes;

import npd.scanner.Transicao;
/**
 *
 * @author Ivan
 */
public class Irrelevant implements Transicao{

    @Override
    public boolean isValidChar(char c) {
        return c == ' ' || c == '\n' || c == '\t' || c == '\r';
    }
    
}
