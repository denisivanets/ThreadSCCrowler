import com.google.common.reflect.ClassPath;

public class CodeProcessor implements Runnable {
    private ClassPath.ClassInfo clazz;

    public CodeProcessor(final ClassPath.ClassInfo clazz) {
        this.clazz = clazz;
    }

    @Override
    public void run() {
        processClass();
    }

    private void processClass() {
        String parent = clazz.load().getGenericSuperclass().getTypeName();
        if (isRightObject(parent)) {
            return;
        }
        System.out.println(parent + ": " + clazz.getSimpleName());
    }

    private boolean isRightObject(final String className) {
        return className.endsWith("Object");
    }

}
