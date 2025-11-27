package comp3607.groupproject;

import java.io.File;
import java.util.List;

public interface InputHandler {
    public List<GameContent> parse(File f);
}
