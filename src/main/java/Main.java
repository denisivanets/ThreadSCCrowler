import com.google.common.reflect.ClassPath;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws Exception {
        Main main = new Main();
        main.inspectCode();
    }

    private void inspectCode() throws Exception {
        ClassPath.from(ClassLoader.getSystemClassLoader())
                .getAllClasses()
                .stream()
                .forEach(clazz -> new Thread(new CodeProcessor(clazz)).run());
    }

}

