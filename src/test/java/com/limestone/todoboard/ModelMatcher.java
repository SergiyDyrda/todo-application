package com.limestone.todoboard;

public interface ModelMatcher<Model> {
    boolean match(Model model1, Model model2);
}
