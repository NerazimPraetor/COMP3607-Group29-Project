package comp3607.groupproject;

import java.io.File;
import java.util.List;

public interface OutputHandler {
    public void write(List<LogData> entries, File f);
}
