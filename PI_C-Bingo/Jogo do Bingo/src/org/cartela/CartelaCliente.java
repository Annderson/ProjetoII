package org.cartela;

/**Esta interface CartelaCliente � a cartela do jogador propriamente dita com 24 n�meros v�lidos,
 * *tamb�m se encontra 3 (tr�s) bot�es bingo que se for pressionado dar� respectivas
 * mensagens de vencedor ou perdedor, o bot�o sair, o jogador pode abandonar o jogo
 * em andamento, quando bem entender e. O bot�o voltar que retorna ao para classe TelaInicial, 
 * o jogador pode jogar novamente.
 * @author Anderson Rodrigues Lima.
 * @since 2016.
 * @version 1.0.
  */

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.Color;
import javax.swing.SwingConstants;

import org.jogador.JogadorCliente;

import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CartelaCliente extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;
	protected JogadorCliente jogador;
	protected List<Integer> escolhidos;
	protected List<Integer> recebidos;
    protected String nome;
    
	protected JPanel painel;
	protected JLabel lblNumeroSorteado = new JLabel();
	protected JButton btnBingo = new JButton("Bingo");
	protected JButton btnSair = new JButton("Sair");
	protected JButton btnVoltar = new JButton("Voltar");
	
	protected JButton[][] botoes;
	protected JButton btn10 = new JButton();
	protected JButton btn11 = new JButton();
	protected JButton btn12 = new JButton();
	protected JButton btn13 = new JButton();
	protected JButton btn14 = new JButton();
	protected JButton btn20 = new JButton();
	protected JButton btn30 = new JButton();
	protected JButton btn40 = new JButton();
	protected JButton btn50 = new JButton();
	protected JButton btn21 = new JButton();
	protected JButton btn22 = new JButton();
	protected JButton btn23 = new JButton();
	protected JButton btn24 = new JButton();
	protected JButton btn31 = new JButton();
	protected JButton btnNulo = new JButton();
	protected JButton btn33 = new JButton();
	protected JButton btn34 = new JButton();
	protected JButton btn41 = new JButton();
	protected JButton btn42 = new JButton();
	protected JButton btn43 = new JButton();
	protected JButton btn44 = new JButton();
	protected JButton btn51 = new JButton();
	protected JButton btn52 = new JButton();
	protected JButton btn53 = new JButton();
	protected JButton btn54 = new JButton();
	
	public CartelaCliente(String nome) throws Exception {
		
		super("Cartela");
		this.setSize(500, 400);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
		painel = new JPanel();
		painel.setLayout(null);
		getContentPane().add(painel);
		
		inserirComponentes();
		mapearComponentes();
		
		this.nome = nome;
		escolhidos = new ArrayList<Integer>();
		recebidos = new ArrayList<Integer>();
		botoes = new JButton[][]{{btn10,btn20,btn30,btn40,btn50},
				                 {btn11,btn21,btn31,btn41,btn51},
				                 {btn12,btn22,btnNulo,btn42,btn52},
				                 {btn13,btn23,btn33,btn43,btn53},
				                 {btn14,btn24,btn34,btn44,btn54}}; 
		
		pegarCartela();
		trateEventos();
		
		painel.repaint();
			
	}

	protected void pegarCartela() throws Exception {
    	
    	jogador = new JogadorCliente("192.168.221.59", 10000);    	
    	jogador.envie("");
    	new Jogo().start();
    	
	}
	
	
	protected class Jogo extends Thread{
		public void run() {		
			int vf = 0;
			try{
				while(true){
					if (vf == 0){
						for (int i = 0; i < 5; i++) 
							for (int j = 0; j < 5; j++) 
								 botoes[i][j].setText(jogador.receber());						
					} 
					else{
						if (jogador.pedido()){
		    			    String recepcao = jogador.receber();
						    lblNumeroSorteado.setText(recepcao);
						   
						    if(recepcao.equals("Voc� perdeu, ja ouve um ganhador"))
						    	btnBingo.setEnabled(false);
						    
						    if (recepcao.length() <= 2)
						        recebidos.add(Integer.parseInt(recepcao));
						}
					}
					vf = 1;
				}
			}catch (Exception e){
				e.printStackTrace();
			}
		}	
	}
	
	protected void trateEventos() {
    	    	
    	btn10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn10.setForeground(Color.white);
				btn10.setBackground( new Color(0,70,0));
				btn10.setEnabled(false);
				escolhidos.add(Integer.parseInt(btn10.getText()));
			}
		});
    	
    	btn11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn11.setForeground(Color.white);
				btn11.setBackground( new Color(0,70,0));
				btn11.setEnabled(false);
				escolhidos.add(Integer.parseInt(btn11.getText()));
			}
		});
    	
    	btn12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn12.setForeground(Color.white);
				btn12.setBackground( new Color(0,70,0));
				btn12.setEnabled(false);
				escolhidos.add(Integer.parseInt(btn12.getText()));
			}
		});
    	
    	btn13.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn13.setForeground(Color.white);
				btn13.setBackground( new Color(0,70,0));
				btn13.setEnabled(false);
				escolhidos.add(Integer.parseInt(btn13.getText()));
			}
		});
    	
    	btn14.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn14.setForeground(Color.white);
				btn14.setBackground( new Color(0,70,0));
				btn14.setEnabled(false);
				escolhidos.add(Integer.parseInt(btn14.getText()));
			}
		});
	
    	btn20.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn20.setForeground(Color.white);
				btn20.setBackground( new Color(0,70,0));
				btn20.setEnabled(false);
				escolhidos.add(Integer.parseInt(btn20.getText()));
			
			}
		});
    	
    	btn21.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn21.setForeground(Color.white);
				btn21.setBackground( new Color(0,70,0));
				btn21.setEnabled(false);
				escolhidos.add(Integer.parseInt(btn21.getText()));
			}
		});
    	
    	btn22.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn22.setForeground(Color.white);
				btn22.setBackground( new Color(0,70,0));
				btn22.setEnabled(false);
				escolhidos.add(Integer.parseInt(btn22.getText()));
			}
		});
    	
    	btn23.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn23.setForeground(Color.white);
				btn23.setBackground( new Color(0,70,0));
				btn23.setEnabled(false);
				escolhidos.add(Integer.parseInt(btn23.getText()));
			}
		});
    	
    	btn24.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn24.setForeground(Color.white);
				btn24.setBackground( new Color(0,70,0));
				btn24.setEnabled(false);
				escolhidos.add(Integer.parseInt(btn24.getText()));
			}
		});

    	btn30.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn30.setForeground(Color.white);
				btn30.setBackground( new Color(0,70,0));
				btn30.setEnabled(false);
				escolhidos.add(Integer.parseInt(btn30.getText()));
			}
		});
    	
		btn31.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn31.setForeground(Color.white);
				btn31.setBackground( new Color(0,70,0));
				btn31.setEnabled(false);
				escolhidos.add(Integer.parseInt(btn31.getText()));
			}
		});
		
		btn33.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn33.setForeground(Color.white);
				btn33.setBackground( new Color(0,70,0));
				btn33.setEnabled(false);
				escolhidos.add(Integer.parseInt(btn33.getText()));
			}
		});
		
		btn34.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn34.setForeground(Color.white);
				btn34.setBackground( new Color(0,70,0));
				btn34.setEnabled(false);
				escolhidos.add(Integer.parseInt(btn34.getText()));
			}
		});

		btn40.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn40.setForeground(Color.white);
				btn40.setBackground( new Color(0,70,0));
				btn40.setEnabled(false);
				escolhidos.add(Integer.parseInt(btn40.getText()));
			}
		});
		
		btn41.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn41.setForeground(Color.white);
				btn41.setBackground( new Color(0,70,0));
				btn41.setEnabled(false);
				escolhidos.add(Integer.parseInt(btn41.getText()));
			}
		});
		
		btn42.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn42.setForeground(Color.white);
				btn42.setBackground( new Color(0,70,0));
				btn42.setEnabled(false);
				escolhidos.add(Integer.parseInt(btn42.getText()));
			}
		});
		
		btn43.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn43.setForeground(Color.white);
				btn43.setBackground( new Color(0,70,0));
				btn43.setEnabled(false);
				escolhidos.add(Integer.parseInt(btn43.getText()));
			}
		});
		
		btn44.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn44.setForeground(Color.white);
				btn44.setBackground( new Color(0,70,0));
				btn44.setEnabled(false);
				escolhidos.add(Integer.parseInt(btn44.getText()));
			}
		});

		btn50.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn50.setForeground(Color.white);
				btn50.setBackground( new Color(0,70,0));
				btn50.setEnabled(false);
				escolhidos.add(Integer.parseInt(btn50.getText()));
			}
		});
		
		btn51.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn51.setForeground(Color.white);
				btn51.setBackground( new Color(0,70,0));
				btn51.setEnabled(false);
				escolhidos.add(Integer.parseInt(btn51.getText()));
			}
		});
		
		btn52.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn52.setForeground(Color.white);
				btn52.setBackground( new Color(0,70,0));
				btn52.setEnabled(false);
				escolhidos.add(Integer.parseInt(btn52.getText()));
			}
		});
		
		btn53.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn53.setForeground(Color.white);
				btn53.setBackground( new Color(0,70,0));
				btn53.setEnabled(false);
				escolhidos.add(Integer.parseInt(btn53.getText()));
			}
		});
		
		btn54.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn54.setForeground(Color.white);
				btn54.setBackground( new Color(0,70,0));
				btn54.setEnabled(false);
				escolhidos.add(Integer.parseInt(btn54.getText()));
			}
		});
		
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					jogador.envie("saindo");
					CartelaCliente.this.dispose();
					new TelaInicial();
				} catch (Exception e2) {
					e2.printStackTrace();
				}	
			}
		});
		
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					jogador.envie("saindo");
					CartelaCliente.this.dispose();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
    
		btnBingo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				try {
					if (verificarBingo()){
						jogador.envie("bingo");
						JOptionPane.showMessageDialog(null, "Parab�ns " + nome + " voc� ganhou");	
						lblNumeroSorteado.setText("Bingooo Parab�ns " + nome);
					}
					else
						JOptionPane.showMessageDialog(null, "Xiii " + nome + " voc� n�o fez bingo, otario!");
				} catch (Exception e2) {
					 e2.printStackTrace();
				}
												
			}

			private boolean verificarBingo() {
				
				for (int i = 0; i < 5; i++) {
					for (int j = 0; j < 5; j++) {
						if (botoes[i][j].isEnabled() == true)
							return false;
					}
				}

				if (!(recebidos.containsAll(escolhidos)))
					return false;
					
				return true;
			}
		});

    }

	protected void inserirComponentes() {
		
    	painel.add(lblNumeroSorteado);
		painel.add(btnBingo);
		painel.add(btnSair);
		painel.add(btnVoltar);
		
		painel.add(btn10);
		painel.add(btn11);
		painel.add(btn12);	
		painel.add(btn13);
		painel.add(btn14);	
		
		painel.add(btn20);	
		painel.add(btn21);	
		painel.add(btn22);
		painel.add(btn23);
		painel.add(btn24);
		
		painel.add(btn30);
		painel.add(btn31);	
		painel.add(btnNulo);	
		painel.add(btn33);
		painel.add(btn34);	
		
		painel.add(btn40);
		painel.add(btn41);
		painel.add(btn42);
		painel.add(btn43);
		painel.add(btn44);	
		
		painel.add(btn50);
		painel.add(btn51);	
		painel.add(btn52);
		painel.add(btn53);
		painel.add(btn54);
		
	}
	
    protected void mapearComponentes() {
    	
    	lblNumeroSorteado.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNumeroSorteado.setForeground(new Color(0, 0, 0));
		lblNumeroSorteado.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumeroSorteado.setBackground(new Color(0, 200, 200));
		lblNumeroSorteado.setBounds(10, 11, 474, 40);
		
		btnBingo.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnBingo.setBackground(Color.RED);
		btnBingo.setForeground(Color.white);
		btnBingo.setBounds(200, 297, 99, 35);
		
		btnSair.setForeground(Color.WHITE);
		btnSair.setBackground(Color.BLACK);
		btnSair.setBounds(401, 331, 83, 29);
		
		btnVoltar.setForeground(Color.WHITE);
		btnVoltar.setBackground(Color.BLACK);
		btnVoltar.setBounds(10, 334, 83, 29);
		
		btnNulo.setForeground(new Color(0, 100, 0));
		btnNulo.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnNulo.setBackground(new Color(0, 0, 0));
		btnNulo.setEnabled(false);
		btnNulo.setBounds(218, 160, 55, 35);
		
		btn10.setBounds(78, 80, 55, 35);
		btn11.setBounds(78, 120, 55, 35);
		btn12.setBounds(78, 160, 55, 35);
		btn13.setBounds(78, 200, 55, 35);
		btn14.setBounds(78, 240, 55, 35);
		
		btn20.setBounds(148, 80, 55, 35);
		btn21.setBounds(148, 120, 55, 35);
		btn22.setBounds(148, 160, 55, 35);
		btn23.setBounds(148, 200, 55, 35);
		btn24.setBounds(148, 240, 55, 35);
		
		btn30.setBounds(218, 80, 55, 35);
		btn31.setBounds(218, 120, 55, 35);
		btn33.setBounds(218, 200, 55, 35);
		btn34.setBounds(218, 240, 55, 35);
		
		btn40.setBounds(288, 80, 55, 35);
		btn41.setBounds(288, 120, 55, 35);
		btn42.setBounds(288, 160, 55, 35);
		btn43.setBounds(288, 200, 55, 35);
		btn44.setBounds(288, 240, 55, 35);
		
		btn50.setBounds(358, 80, 55, 35);
		btn51.setBounds(358, 120, 55, 35);
		btn52.setBounds(358, 160, 55, 35);
		btn53.setBounds(358, 200, 55, 35);
		btn54.setBounds(358, 240, 55, 35);
		
	}
}
