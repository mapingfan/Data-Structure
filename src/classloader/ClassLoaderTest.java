package classloader;

public class ClassLoaderTest {
    public static void main(String[] args) {
        try {
            System.out.println(System.getProperty("java.class.path"));
            Class<?> aClass = Class.forName("classloader.TestBean");
            System.out.println(aClass.getClassLoader());
            System.out.println(Object.class.getClassLoader());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
