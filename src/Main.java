import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {

        // Создаем 5 вилок (семафоров)
        Semaphore[] forks = new Semaphore[5];
        for (int i = 0; i < 5; i++) {
            forks[i] = new Semaphore(1);
        }

        // Создаем 5 философов
        Philosopher[] philosophers = new Philosopher[5];
        philosophers[0] = new Philosopher("Философ 1", forks[0], forks[4]);
        philosophers[1] = new Philosopher("Философ 2", forks[1], forks[0]);
        philosophers[2] = new Philosopher("Философ 3", forks[2], forks[1]);
        philosophers[3] = new Philosopher("Философ 4", forks[3], forks[2]);
        philosophers[4] = new Philosopher("Философ 5", forks[4], forks[3]);

        // Запускаем философов
        for (Philosopher philosopher : philosophers) {
            new Thread(philosopher).start();
        }

        // Ожидаем, пока все философы не поедят три раза
        for (Philosopher philosopher : philosophers) {
            try {
                philosopher.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Все философы поели.");
    }
}



