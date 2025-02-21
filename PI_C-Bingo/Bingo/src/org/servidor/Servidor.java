package org.servidor;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;

import org.bdo.Valor;
import org.jogador.Jogador;
import org.persistencia.Valores;

public class Servidor{
	
	private static Jogador jog;
	private static int idCartela = 0;
	private static Timer time = new Timer();
	private static TimerTask task1;
	private static Jogo jogo;
	private static int vf = 0;
	private static ServerSocket server;
	
	public static void main(String[] args) throws Exception {
		
		int temp = 30000;
	
	
		task1 = new TimerTask() {	
			@Override
			public void run() {
				if (vf == 1){
					
					try {
						
						if (idCartela > 1){
							System.out.println("ENVIOU 100");
							jogo.start();
							jogo = new Jogo();
							vf = 0;
							idCartela = 0;
							
						}
						else{
							System.out.println("ENVIOU");
							jog.envie("Jogo ser� excluido por falta de jogador");
							jogo.removeJogador(jog);
						}
					
					
				    } catch (Exception e) {
					    e.printStackTrace();
				    }
					
				}
			}
		};		
		
		try {
			
			jogo = new Jogo();	
			server = new ServerSocket(10000);
			time.scheduleAtFixedRate(task1, 0, temp);
			
			while(true){
				
				Socket socket = server.accept();
				idCartela ++;
				jog= new Jogador(socket);
				jogo.addJogador(jog);
				enviecartela(idCartela);
				vf = 1;
				
				if (idCartela >= 4){
					time.cancel();
					jogo.start();
					jogo = new Jogo();
					time.scheduleAtFixedRate(task1, 0, temp);
					idCartela=0;
					vf = 0;
				}
				
			}	
				
		} catch (Exception e) {
			System.out.println("Erro de conex�o");
		}
		

	}

	private static void enviecartela(int id) throws Exception {
		
		try {
			Valor vl;
			Valores vls = new Valores();
			
			for (int i = 0; i < 5; i++) {
				
				for (int j = 0; j < 5; j++) {	
					
					if ((i == 2) && (j == 2)){
						jog.envie("@");
					}
					else{
						vl = vls.consulta(i, j, id);
						jog.envie(""+vl.getValor());
					}
				}
				
			}
			
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		
	}  

}
