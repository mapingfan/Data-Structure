package ExDay2;

import java.util.LinkedList;

/**
 * 猫狗队列问题
 * 有add方法往里添加猫或者狗.
 * pollDog/pollCat弹出首先加入队列的猫或者狗(O(1)时间返回)
 * pollAll(弹出进入队列的第一个宠物)
 */
public class ex07 {

    public static class Pet {
        private String type;

        public Pet(String type) {
            this.type = type;
        }

        public String getPetType() {
            return this.type;
        }
    }

    public static class Dog extends Pet {
        public Dog() {
            super("dog");
        }
    }

    public static class Cat extends Pet {
        public Cat() {
            super("cat");
        }
    }

    /**
     * 从题目分析,要O(1)返回猫或者狗,那么必然要分别维护两个队列,一个只进猫,一个只进狗.
     * 但是题目还要求按照进队顺序把动物弹出.
     * <p>
     * 有一个策略是维护两个队列,并且入队时候打上标签序号.这样pollAll时,可以根据时间戳决定
     * 谁先出来.
     * <p>
     * 我的策略是维护三个队列,第三个队列不区分猫狗,来了动物随便进.
     * 出队时要注意,要根据取出的类型来同步猫狗队列的出队.
     * <p>
     * 下面两种思路都实现下.
     */

    public static class CatWrapper {
        private Cat cat;
        private int count;

        public CatWrapper(Cat pet, int count) {
            cat = pet;
            count = count;
        }
    }

    public static class DogWrapper {
        private Dog dog;
        private int count;

        public DogWrapper(Dog pet, int count) {
            dog = pet;
            count = count;
        }
    }


    public static class CatDogQueue {
        private LinkedList<CatWrapper> catQueue = new LinkedList<>();
        private LinkedList<DogWrapper> dogQueue = new LinkedList<>();
        private int count = 0;

        public void push(Pet pet) {
            if (pet.getPetType().equals("dog")) {
                dogQueue.addLast(new DogWrapper((Dog) pet, count++));
            } else if (pet.getPetType().equals("cat")) {
                catQueue.addLast(new CatWrapper((Cat) pet, count++));
            } else throw new IllegalStateException("Not cat or dog");
        }

        public Pet pollAll() {
            if (dogQueue.isEmpty() && catQueue.isEmpty()) throw new IllegalStateException("The queue is empty");

            if (dogQueue.isEmpty())
                return pollCat();

            if (catQueue.isEmpty())
                return pollDog();

            int dogCount = dogQueue.peekFirst().count;
            int catCount = catQueue.peekFirst().count;
            if (dogCount < catCount)
                return dogQueue.removeFirst().dog;
            else return catQueue.removeFirst().cat;
        }

        public Pet pollDog() {
            if (dogQueue.isEmpty()) throw new IllegalStateException("The dog queue is empty");
            return dogQueue.removeFirst().dog;
        }

        public Pet pollCat() {
            if (catQueue.isEmpty()) throw new IllegalStateException("The cat queue is empty");
            return catQueue.removeFirst().cat;
        }
    }


    //上面的版本需要包装下猫狗对象,下面直接不包装,采用三个队列实现.
    //实践证明,三个队列无法实现.

//    private static class DogCatQueue {
//        LinkedList<Cat> catQueue = new LinkedList<>();
//        LinkedList<Dog> dogQueue = new LinkedList<>();
//        LinkedList<Pet> petQueue = new LinkedList<>();
//
//        public void push(Pet pet) {
//            if (!pet.getPetType().equals("cat")||!pet.getPetType().equals("dog"))
//                throw new IllegalStateException("Not a dog or cat");
//            if (pet.getPetType().equals("cat")) catQueue.addLast((Cat) pet);
//            else dogQueue.addLast((Dog) pet);
//            petQueue.addLast(pet);
//        }
//
//        public Pet pollDog() {
//            if (dogQueue.isEmpty()) throw new IllegalStateException("No dog exists");
//
//        }
//
//    }


}
