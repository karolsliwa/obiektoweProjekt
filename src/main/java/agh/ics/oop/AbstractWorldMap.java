package agh.ics.oop;

import javafx.application.Platform;

import java.util.*;

public abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver {
    protected Map<Vector2d, LinkedList<Animal>> animals = new HashMap<>();
    protected int mapWidth, mapHeight, jungleWidth, jungleHeight;
    protected int plantEnergy, moveEnergy, startEnergy;
    private int daysPassed = 0;
    protected ArrayList<Animal> animalsList = new ArrayList<Animal>();
    protected LinkedList<Animal> consumers = new LinkedList<Animal>();
    protected ArrayList<Animal> dead = new ArrayList<Animal>();
    Random generator = new Random();
    protected Vector2d jungleLowerLeft;
    protected Vector2d jungleUpperRight;
    protected Map<Vector2d, Grass> grasses = new HashMap<>();
    protected Vector2d upperRight;
    protected Vector2d lowerLeft;

    @Override
    public void positionChanged(Vector2d oldPosition, Animal animal) {
        LinkedList<Animal> l = this.animals.get(oldPosition);
        l.removeIf(a -> a.equals(animal));
        this.placeAnimal(animal);
    }
    @Override
    public boolean canMoveTo(Vector2d position) {
//        return position.precedes(this.upperRight) && position.follows(this.lowerLeft)
//                && !isOccupied(position);
        return position.precedes(this.upperRight) && position.follows(this.lowerLeft);
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        Object o = objectAt(position);
//        o.getClass().getName().equals;
        if (o != null) return o instanceof Grass;
        else return false;
    }
    @Override
    public AbstractWorldMapElement objectAt(Vector2d position) {
        LinkedList<Animal> l = this.animals.get(position);
        Animal alpha = null;
        if (l != null) {
            int max = 0;
            for (Animal animal : l) {
                if (animal.getHealth() > max) {
                    max = animal.getHealth();
                    alpha = animal;
                }
            }
        }
        if (alpha != null) return alpha;
        else return this.grasses.get(position);
    }
    @Override
    public boolean place(IMapElement mapElement) {
        if (mapElement instanceof Grass && canMoveTo(mapElement.getPosition()) &&
                this.objectAt(mapElement.getPosition()) == null) {
            this.grasses.put(mapElement.getPosition(), (Grass) mapElement);
        }
        else if (mapElement instanceof Animal && canMoveTo(mapElement.getPosition())) {
            this.placeAnimal((Animal) mapElement);
            this.animalsList.add((Animal) mapElement);
        }
        else return false;
        return true;
    }

    public void growGrass() {
        int i = 0;
        Vector2d junglePos = generateJunglePosition();
        while (this.objectAt(junglePos) != null
                && i <  2 * this.jungleWidth * this.jungleHeight) {
            junglePos = generateJunglePosition();
            i += 1;
        }
        this.place(new Grass(junglePos));
        i = 0;
        Vector2d steppePos = generateSteppePosition();
        while (this.objectAt(steppePos) != null && i < 2 * (this.mapWidth * this.mapHeight
                - this.jungleHeight * this.jungleWidth)) {
            steppePos = generateSteppePosition();
            i += 1;
        }
        this.place(new Grass(steppePos));

    }

    public void graze(Vector2d position) {
        Grass grass = this.grasses.get(position);
        int max = 0;
        LinkedList<Animal> l = this.animals.get(position);
        if (l == null || grass == null || l.isEmpty()) return;
        for (Animal animal : l) {
            if (animal.getHealth() > max) {
                max = animal.getHealth();
            }
        }
        for (Animal animal : l) {
            if (animal.getHealth() == max) this.consumers.add(animal);
        }
        for (Animal animal : this.consumers) animal.eat(plantEnergy/this.consumers.size());
        this.consumers.clear();
        this.grasses.remove(position);
    }
    public void multiply(Vector2d position) {
        int maxf = 0, maxm = 0;
        Animal father = null, mother = null, creature = null;
        LinkedList<Animal> l = this.animals.get(position);
        if (l == null) return;
        for (int i = 0; i < l.size(); i++) {
            creature = l.get(i);
            if (creature.getPosition().equals(position) && creature.getHealth() >= maxf) {
                father = creature;
                maxf = father.getHealth();
            }
            if (i > 0) {
                creature = this.animalsList.get(i - 1);
                if (!creature.equals(father) && creature.getPosition().equals(position) && creature.getHealth() >= maxm) {
                    mother = creature;
                    maxm = mother.getHealth();
                }
            }
            if (mother != null && father != null && father.getHealth() > 0.5 * this.startEnergy
                    && mother.getHealth() > 0.5 * startEnergy) {
                this.place(father.reproduce(mother));

            }
        }
    }

    public void anotherDay() {
        Platform.runLater(() -> {
            this.migration();
            this.aging();
            this.animalsList.removeIf(Animal::isDead);
            for (int x = 0; x <= this.mapWidth; x++) {
                for (int y = 0; y <= this.mapHeight; y++) {
                    Vector2d position = new Vector2d(x, y);
                    this.removeDead(position);
                    this.graze(position);
                    this.multiply(position);
                }
            }
            this.growGrass();
            this.daysPassed += 1;
        });
    }

    public void placeAnimal(Animal animal) {
        Vector2d p = animal.getPosition();
        LinkedList<Animal> l = this.animals.get(animal.getPosition());
        if (l == null) {
            l = new LinkedList<Animal>();
            l.add(animal);
            this.animals.put(p, l);
        }
        else {
            l.add(animal);
        }
    }

    public void migration() {
        if (this.animals.isEmpty()) return;
        for (Animal animal : this.animalsList) {
            animal.move();
        }
    }

    public void aging() {
        for (Animal animal : this.animalsList) {
            animal.age();
            animal.useEnergy(this.moveEnergy);
        }
    }

    public void removeDead(Vector2d pos) {
        LinkedList<Animal> l = this.animals.get(pos);
        if (l == null) return;
        Iterator<Animal> iterator = l.iterator();
        while (iterator.hasNext()) {
            Animal a = iterator.next();
            if (a.isDead()) {
                iterator.remove();
                this.dead.add(a);
            }
        }
    }

    public int getTime() {
        return this.daysPassed;
    }
    public int animalsNumber() {
        return this.animalsList.size();
    }
    public int grassNumber() {
        return this.grasses.size();
    }

    public int averageEnergy() {
        int energy = 0;
        if (this.animalsList.isEmpty()) return 0;
        for (Animal animal : this.animalsList) {
            energy += animal.getHealth();
        }
        return energy/this.animalsList.size();
    }

    public int averageLifeTime() {
        int age = 0;
        if (this.dead.isEmpty()) return age;
        for (Animal animal : this.dead) {
            age += animal.getAge();
        }
        return age/this.dead.size();
    }

    public int averageChildrenAmount() {
        int c = 0;
        for (Animal animal : this.animalsList) {
            c += animal.getChildrenCounter();
        }
        return c/this.animalsList.size();
    }

    public Vector2d getLowerLeft() {
        return this.lowerLeft;
    }

    public Vector2d getUpperRight() {
        return upperRight;
    }
    public Vector2d getJungleLowerLeft() {
        return this.jungleLowerLeft;
    }
    public Vector2d getJungleUpperRight() {
        return this.jungleUpperRight;
    }

    public int getJungleHeight() {
        return jungleHeight;
    }

    public int getJungleWidth() {
        return jungleWidth;
    }

    public int getMapHeight() {
        return mapHeight;
    }

    public int getMapWidth() {
        return mapWidth;
    }

    public Vector2d generateSteppePosition() {
        Vector2d newPosition = new Vector2d(generator.nextInt(this.mapWidth + 1),
                generator.nextInt(this.mapHeight + 1));
        while (newPosition.precedes(this.jungleUpperRight) && newPosition.follows(this.jungleLowerLeft)) {
            newPosition = new Vector2d(generator.nextInt(this.mapWidth + 1),
                    generator.nextInt(this.mapHeight + 1));
        }
        return newPosition;
    }
    public Vector2d generateJunglePosition() {
        int jx = generator.nextInt(this.jungleWidth + 1);
        int jy = generator.nextInt(this.jungleHeight + 1);
        return new Vector2d(jx + this.jungleLowerLeft.x, jy + this.jungleLowerLeft.y);
    }
}

