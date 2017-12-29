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
        if (isAnotherClass(clazz)) {
            return;
        }
        String parent = clazz.load().getGenericSuperclass().getTypeName();
        Collector.getInstance().add(parent, clazz.getName());
    }

    private boolean isAnotherClass(final ClassPath.ClassInfo info) {
        return !info.getPackageName().equals("");
    }

}
