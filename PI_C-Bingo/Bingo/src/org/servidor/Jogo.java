package org.servidor;

import java.util.ArrayList;
import java.util.Random;
import org.jogador.Jogador;

public class Jogo extends Thread {

	protected ArrayList<Integer> numerosSorteados;
	protected ArrayList<Jogador> jogador;
	protected int index;
	
	public Jogo() throws Exception{
		
		jogador = new ArrayList<Jogador>();
		numerosSorteados = new ArrayList<Integer>();
		this.index = 0;
		
	}

	public void addJogador(Jogador jogador) throws Exception {
		if (jogador == null)
			throw new Exception("Jogador n�o fornecido");
		
		this.jogador.add(jogador);
		this.index ++;
	}
	
	public void removeJogador(Jogador jogador) throws Exception{
		if (jogador == null)
			throw new Exception("Jogador n�o foi fornecido");
		
		this.jogador.remove(jogador);
		this.index --;
	}

	private int numeroSorteado(){
		int pegue = 0;
		Random rom = new Random();	
		
		do{
			
			pegue = rom.nextInt(76);
			
		}while(numerosSorteados.contains(pegue));
		
		this.numerosSorteados.add(pegue);
		
		return pegue;
    }
    
    public void run(){
    	
    	String elemento;
    	String recebe = null;
    	
    	try {
		
    		for (int i = 0; i < this.index; i++) 
				this.jogador.get(i).envie("jogo come�ara em 10 segundos");
    		Thread.sleep(10*1000);
    			
    		while(true){  			
    			
    			
    			if (numerosSorteados.size() == 76){
    				
    				for (int i = 0; i < index; i++) 
    					this.jogador.get(i).envie("Todos numeros foram sorteados");
    				
    				Thread.sleep(5*1000);
    				
    				for (int i = 0; i < index; i++) {
    					this.jogador.get(i).envie("N�o houve ganhador, o jogo acabou");
    				}
    				for (int i = 0; i < index; i++) {
						removeJogador(jogador.get(i));
					}
    				index = 0;
    				break;
    			}
    			
    			elemento ="" + numeroSorteado();

        		for (int i =0; i<this.index;i++)
    				this.jogador.get(i).envie(elemento);
        		
        		for (int i = 0; i < index; i++) {
        			if (jogador.get(i).pedido()){
        				
        				recebe = jogador.get(i).receba();
        				informacaoCliente(recebe, i);   
        				break;
        			}
        				
				}
        		
        		if (index == 1){
        			System.out.println("excluir");
        			jogador.get(index -1).envie("Jogo ser� excluido por falta de jogador");
        			removeJogador(jogador.get(index-1));
        			break;
        		}
        		if (index == 0)
        			break;
        		
        		Thread.sleep(6*1000);
    		}
		} catch (Exception e) {
			e.printStackTrace();

		}	
    	
    }

	private void informacaoCliente(String recebe, int posicao) throws Exception {
				
		try {
			
			if (recebe.equals("bingo")){
				for (int i = 0; i < index; i++) {
					if (posicao != i){
						System.out.println("aviso");
						jogador.get(i).envie("Voc� perdeu, ja ouve um ganhador");
					}
				}
				for (int i = 0; i < index; i++) {
					System.out.println("remove");
					removeJogador(jogador.get(i));
				}
				index = 0;
			}
			if (recebe.equals("saindo")){
				removeJogador(jogador.get(posicao));	
			}
			
		
			
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	
	}
	
	public boolean equals (Object obj){
		
		if (obj == this)
			return true;
		if (obj == null)
			return false;
		if (this.getClass() != obj.getClass())
			return false;
		
		Jogo jo = (Jogo)obj;
		
		if (this.index != jo.index)
			return false;
		if (!(this.numerosSorteados.equals(jo.numerosSorteados)))
			return false;
		if (!(this.jogador.equals(jo.jogador)))
			return false;
		
		return true;
	}
	
	public String toString(){
		String ret = "{";
		
		ret += this.jogador + "\n";
		ret += this.numerosSorteados + "}";
		
		return ret;
	}
	
	public int hashCode (){
		int hash = super.hashCode();
		
		hash = hash*3 + new Integer(this.index).hashCode();
		hash = hash*3 + this.numerosSorteados.hashCode();
		hash = hash*3 + this.jogador.hashCode();
		
		return hash;
	}
	
	public Jogo (Jogo modelo)throws Exception{
		
		if (modelo == null)
			throw new Exception("Jogo n�o foi fornecio");
		
		this.numerosSorteados.clear();
		this.jogador.clear();
		this.numerosSorteados = modelo.numerosSorteados;
		this.jogador = modelo.jogador;
		this.index = modelo.index;
		
	}
	
	public Object clone (){
		
		Jogo jogo = null;
		
		try {
			jogo = new Jogo(this);
		} catch (Exception e) {}
		
		return jogo;
	}
}
