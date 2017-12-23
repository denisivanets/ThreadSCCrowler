import com.google.common.reflect.ClassPath;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CodeProcessor implements Runnable {
    public static Map<String, List<String>> counter = new ConcurrentHashMap<>();
    private ClassPath.ClassInfo clazz;

    public CodeProcessor(final ClassPath.ClassInfo clazz) {
        this.clazz = clazz;
    }

    @Override
    public void run() {
        processClass();
    }

    private void processClass() {
        if (isAnotherClass(clazz)) {
            return;
        }
        String parent = clazz.load().getGenericSuperclass().getTypeName();
        if (!counter.containsKey(parent)) {
            List<String> childClasses = new ArrayList<>();
            childClasses.add(clazz.getName());
            counter.put(parent, childClasses);
        } else {
            counter.get(parent).add(clazz.getName());
        }
    }

    private boolean isAnotherClass(final ClassPath.ClassInfo info) {
        return !info.getPackageName().equals("");
    }

}
