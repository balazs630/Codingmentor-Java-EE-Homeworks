package hu.oktatas.java.ee.dbtest;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Calendar;
import java.util.List;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());
    private static final String DB_PERSISTENCE_NAME
            = "hu.oktatas.java.ee_0731-DbTest_jar_1.0-SNAPSHOTPU";

    private Main() {
    }

    public static void main(String[] args) {
        Calendar birth = Calendar.getInstance();

        EntityManagerFactory emf = Persistence
                .createEntityManagerFactory(DB_PERSISTENCE_NAME);
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Owner owner = new Owner("Balázs");
        Fish fish = new Fish(Color.RED, "Indonesia", owner);
        Dog dog = new Dog();
        Dog dog2 = new Dog(false, birth);
        Cat cat = new Cat(true, 6);
        Cat cat2 = new Cat(false, 15);
        Bird bird = new Bird(true, 50);
        Bird bird2 = new Bird(false, 120, cat2);
        List<String> livesAtContinents = Arrays.asList("Africa", "Europe");
        bird2.setLivesAt(livesAtContinents);

        em.persist(fish);
        em.persist(dog);
        em.persist(dog2);
        em.persist(cat);
        em.persist(cat2);
        em.persist(bird);
        em.persist(bird2);

        owner.getFishes().add(fish);
        fish.setOwner(owner);
        em.merge(fish);

        tx.commit();

        LOGGER.log(Level.INFO, "Cats weight more than 5kg: {0}",
                em.createNamedQuery("Cat.weightMoreThan5Kg", Cat.class).getResultList());
        LOGGER.log(Level.INFO, "Cats not like mouses: {0}",
                em.createNamedQuery("Cat.notLikesMouse", Cat.class).getResultList());
        LOGGER.log(Level.INFO, "Birds have speed over 50 but cannot fly: {0}",
                em.createNamedQuery("Bird.speedOver50AndNotFly", Bird.class).getResultList());
        LOGGER.log(Level.INFO, "Birds lives in Europe: {0}",
                em.createNamedQuery("Bird.livesInEurope", Bird.class).getResultList());
        LOGGER.log(Level.INFO, "Number of red fishes: {0}",
                em.createNamedQuery("Fish.countOfRedFishes", Fish.class).getResultList());

        em.close();
        emf.close();

    }
}
