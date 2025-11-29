package com.comp3607project;

import java.io.File;
import java.util.List;

public interface OutputHandler {
    public void write(List<LogData> entries, File f);
}