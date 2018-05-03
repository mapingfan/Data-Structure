package ExDay2;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 第二天练习题.
 * 比较器学习:
 * 一是类实现Comparable<T>接口.
 * 二是单独实现一个类,用作比较策略,这个类实现Comparator<T>接口.
 * 对于第二种方法,在调用库函数进行比较的时候,需要new一个比较器传入(可以简化为lambada表达式)
 *
 */
public class ex01 {

    public static class Student {
        public String name;
        public int id;
        public int age;

        public Student(String name, int id, int age) {
            this.name = name;
            this.id = id;
            this.age = age;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "name='" + name + '\'' +
                    ", id=" + id +
                    ", age=" + age +
                    '}';
        }
    }

    public static class IdAscendingComparator implements Comparator<Student> {

        @Override
        public int compare(Student o1, Student o2) {
            return o1.id - o2.id;
        }
    }

    public static class IdDescendingComparator implements Comparator<Student> {

        @Override
        public int compare(Student o1, Student o2) {
            return o2.id - o1.id;
        }
    }

    public static class AgeAscendingComparator implements Comparator<Student> {

        @Override
        public int compare(Student o1, Student o2) {
            return o1.age - o2.age;
        }
    }

    public static class AgeDescendingComparator implements Comparator<Student> {

        @Override
        public int compare(Student o1, Student o2) {
            return o2.age - o1.age;
        }
    }

    public static class NameAscendingComparator implements Comparator<Student> {

        @Override
        public int compare(Student o1, Student o2) {
            return o1.name.compareTo(o2.name);
        }
    }

    public static class NameDescendingComparator implements Comparator<Student> {

        @Override
        public int compare(Student o1, Student o2) {
            return o2.name.compareTo(o1.name);
        }
    }

    public static class Cat implements Comparable<Cat> {
        int age;
        int id;

        public Cat(int age, int id) {
            this.age = age;
            this.id = id;
        }

        @Override
        public String toString() {
            return "Cat{" +
                    "age=" + age +
                    ", id=" + id +
                    '}';
        }


        @Override
        public int compareTo(Cat o) {
            return this.id - o.id;
        }
    }


    public static void main(String[] args) {
        Student[] students = {
                new Student("陈颖", 1, 16),
                new Student("马平凡", 2, 23),
                new Student("闫继红", 3, 21),
                new Student("马高群", 4, 18),
        };

        Cat[] cats = {new Cat(1,2),
                new Cat(2,3)};

        Arrays.sort(students, (o1, o2) -> o1.id - o2.id);
        System.out.println(Arrays.toString(students));

//        Arrays.sort(students, new AgeDescendingComparator());
//        Arrays.sort(cats);
//        System.out.println(Arrays.toString(cats));
//        System.out.println(Arrays.toString(students));
//        System.out.println("马平凡".compareTo("马高群"));
    }
}


