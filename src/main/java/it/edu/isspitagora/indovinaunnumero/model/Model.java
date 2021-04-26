/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.edu.isspitagora.indovinaunnumero.model;

import java.security.InvalidParameterException;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Ale
 */

public class Model {

    @Override
    public String toString() {
        return "Model{" + '}';
    }
    private final int NMAX = 100 ; //Limite superiore al valore casuale scelto dal programma (1<=segreto<=100)
    private final int TMAX = 8 ; //Numero di tentativi
    private int segreto ; //Conserva il valore del numero scelto a caso
    private int tentativiFatti ; // Mantiene traccia del numero di tentativi fatti dall'utente
    private boolean inGioco = false ; //La partita è in corso??
    
    private Set<Integer> tentativi; // Il Set mi serve a gestire eventuali doppioni inseriti durante la partita

    public int getNMAX() {
        return NMAX;
    }

    public int getTMAX() {
        return TMAX;
    }

    public int getSegreto() {
        return segreto;
    }

    public int getTentativiFatti() {
        return tentativiFatti;
    }

    public boolean isInGioco() {
        return inGioco;
    }
    
    public void nuovaPartita(){
        //Gestione avvio partita
        this.segreto = (int)(Math.random() * NMAX) + 1; //Genero il numero casuale
        this.tentativiFatti = 0;
        this.inGioco = true;
        //Creo il Set per la gestione dei doppioni
        tentativi = new HashSet<Integer>();
    }
    
    public int tentativo(int tentativo){
        //Restituisce 
        // 0 se ha vinto
        //-1 se è BASSO
        // 1 se è ALTO
        
        //Controllo se la partita è in corso
        if(!(this.inGioco)){
            throw new IllegalStateException("La partita è terminata... HAI PERSO! \nIl numero da indovinare era " + this.segreto + " Ritenta...\n");
        }
        
        //Validazione dell'input (controllo range)
        if(!tentativoValido(tentativo)){
            throw new InvalidParameterException("Devi inserire un valore compreso tra 1 e "+this.NMAX+"\n" +
                    "Oppure hai inserito un valore che avevi già scelto in precedenza \n");
        }
        
        //Da qui in poi il tentativo è valido
        this.tentativiFatti++ ;
        //Inserisco il tentativo nel Set
        this.tentativi.add(tentativo);
    
        //Controllo se non ho più tentativi
        if(this.tentativiFatti == this.TMAX-1){
            this.inGioco = false;
        }
        
        
    
        //Controllo se ho vinto
        if(tentativo == this.segreto){
            this.inGioco = false;
            return 0;
        } 
        else if(tentativo < this.segreto){
            return -1;
        } 
        else {
            return 1;
        }
    }
    
    //Metodo PRIVATO per la validazione dell'input (Controllo range ed eventualmente altro...)
    //Controlla anche se ho già fatto un tentativo con lo stesso numero 
    private boolean tentativoValido(int tentativo){
        if(tentativo < 1 || tentativo > this.NMAX)
            return false;
        if(tentativi.contains(tentativo))
            return false;
        else
            return true;
    }
}
