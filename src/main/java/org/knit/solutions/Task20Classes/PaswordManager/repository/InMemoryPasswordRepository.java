package org.knit.solutions.Task20Classes.PaswordManager.repository;

import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public class InMemoryPasswordRepository {
    private HashMap<String, char[]> data;
}
