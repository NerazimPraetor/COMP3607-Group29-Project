package com.comp3607project;

public interface Subject {
    
    public void addObserver(Observer o);
    public void removeObserver(Observer o);
    public void notify(LogData log, String question);
}
