package core.crawler;

import java.util.HashSet;
import java.util.Set;

public class Crawler {
    private int depth;
    private String seed;
    private Set<String> links;

    private static Crawler ourInstance = new Crawler();

    public void setParams(int depth, String seed) {
        this.depth = depth;
        this.seed = seed;
        this.links = new HashSet<>();
    }

    public static Crawler getInstance() {
        return ourInstance;
    }

    private Crawler() {
    }

    public int getDepth() {
        return depth;
    }

    public String getSeed() {
        return seed;
    }

    public Set<String> getLinks() {
        return links;
    }
}
