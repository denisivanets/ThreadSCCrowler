import com.google.common.reflect.ClassPath;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {
    private static ArrayList<Thread> arrThreads = new ArrayList<>();

    public static void main(String[] args) throws Exception {
         ClassPath.from(ClassLoader.getSystemClassLoader())
            .getAllClasses()
            .forEach(
                (clazz) -> {
                    Thread T1 = new Thread(new CodeProcessor(clazz));
                    T1.start();
                    arrThreads.add(T1);
                }
            );

        for (int i = 0; i < arrThreads.size(); i++)
        {
            arrThreads.get(i).join();
        }
        printResult();
    }

    private static void printResult() {
        for (Map.Entry<String, List<String>> entry : CodeProcessor.counter.entrySet()) {
            if (!isRightObject(entry.getKey())) {
                System.out.println(entry.getKey() + ": " + printChild(entry.getValue()));
            }
        }
    }

    private static boolean isRightObject(final String clazz) {
        return clazz.endsWith("Object");
    }

    private static String printChild(final List<String> classes) {
        return String.join(", ", classes);
    }

}

