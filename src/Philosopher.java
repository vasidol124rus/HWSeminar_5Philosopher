
class Philosopher implements Runnable {
    private final int id;
    private final Fork leftFork;
    private final Fork rightFork;
    private int eatCount = 0;

    public Philosopher(int id, Fork leftFork, Fork rightFork) {
        this.id = id;
        this.leftFork = leftFork;
        this.rightFork = rightFork;
    }

    private void think() {
        System.out.println("Философ " + id + " думает.");
        try {
            Thread.sleep(((int) (Math.random() * 300)));
//            Thread.sleep(200);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void eat() {
        System.out.println("Философ " + id + " кушает.");
        try {
            Thread.sleep(((int) (Math.random() * 200)));
//            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void pickUpForks() {
        synchronized (leftFork) {
            System.out.println("Философ " + id + " взял левую вилку " + leftFork.getId());
            synchronized (rightFork) {
                System.out.println("Философ " + id + " взял правую вилку " + rightFork.getId());
            }
        }
    }

    private void putDownForks() {
        synchronized (leftFork) {
            synchronized (rightFork) {
                System.out.println("Философ " + id + " положил вилки " + leftFork.getId() + " и " + rightFork.getId());
            }
        }
    }

    @Override
    public void run() {
        int howManyTimesShouldIEat = 3;
        while (eatCount < howManyTimesShouldIEat) {
            think();
            pickUpForks();
            eat();
            putDownForks();
            eatCount++;
        }
    }
}