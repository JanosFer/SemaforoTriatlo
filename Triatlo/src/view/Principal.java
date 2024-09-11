package view;

import java.util.concurrent.Semaphore;

import controller.ThreadTriatlo;

public class Principal {

	public static void main(String[] args) {
		Semaphore semaforo = new Semaphore(5);
		for(int i = 0; i < 25; i++) {
			ThreadTriatlo t = new ThreadTriatlo(semaforo);
			t.start();
		}
	}

}
