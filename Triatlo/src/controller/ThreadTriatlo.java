package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class ThreadTriatlo extends Thread{
	private final int TID = (int) getId();
	private Semaphore semaforo;
	Random aleat = new Random();
	static List<Integer> ranking = new ArrayList<Integer>();
	static int[] vetor = new int[ranking.size()];
	
	public ThreadTriatlo(Semaphore semaforo) {
		this.semaforo = semaforo;
		ranking.add(TID);
	}
	
	public void run() {
		corrida();
		
		ranking();
		try {
			semaforo.acquire();
			tiro();
		}catch (InterruptedException e) {
			System.err.println(e.getMessage());
		}finally {
			semaforo.release();
		}
		ciclismo();
		
		
	}
	
	private void corrida() {
		int distancia = 3000;
		int percorreu = 0;
		
		while(percorreu < distancia) {
			percorreu = 20 + aleat.nextInt(5);
			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				System.err.println(e.getMessage());
			}
		}	
		synchronized (ThreadTriatlo.class) {
			System.out.println("O Atleta número " + TID + " finalizou a etapa de corrida!");
			int i = 0;
			for(int tid : ranking) { 
				if(tid == TID) {
					vetor[i] = 250 / (i+1); 
				}
				i++;
			}
		}
	}
	
	private void tiro() {
		int alvos = 3;
		int atirou = 0;
		int pontuou = 0;
		
		while(atirou < alvos) {
			pontuou += aleat.nextInt(11);
			atirou++;
			
			int tempo = 500 + aleat.nextInt(2501);
			
			try {
				Thread.sleep(tempo);
			} catch (InterruptedException e) {
				System.err.println(e.getMessage());
			}
		}
		synchronized (ThreadTriatlo.class) {
			System.out.println("O Atleta número " + TID + " finalizou a etapa de tiro ao alvo!");
			int i = 0;
			for(int tid : ranking) { 
				if(tid == TID) {
					vetor[i] = pontuou; 
				}
				i++;
			}
		}
		
	}
	
	private void ciclismo() {
		int distancia = 5000;
		int percorreu = 0;
		
		while(percorreu < distancia) {
			percorreu = 30 + aleat.nextInt(10);
			try {
				Thread.sleep(40);
			} catch (InterruptedException e) {
				System.err.println(e.getMessage());
			}
		}
		synchronized (ThreadTriatlo.class) {
			System.out.println("O Atleta número " + TID + " finalizou a etapa de ciclismo!");
			int i = 0;
			for(int tid : ranking) { 
				if(tid == TID) {
					vetor[i] = 250 / (i+1);
				}
				i++;
			}
		}
	}
	
	
	private void ranking() {
		int i = 0;
		for(int tid : ranking) {
			System.out.println((i+1) + "° Atleta número " + tid + " fez " + vetor[i] + " pontos.");
		}
	}
}
