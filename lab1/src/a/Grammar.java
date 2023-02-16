package a;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Grammar {
    private Set<String> vn;
    private Set<String> vt;
    private String s;
    private Map<String, List<String>> p;

    public Grammar(Set<String> vn, Set<String> vt, String s, Map<String, List<String>> p) {
        this.vn = vn;
        this.vt = vt;
        this.s = s;
        this.p = p;
    }

    public Set<String> getVn() {
        return vn;
    }

    public Set<String> getVt() {
        return vt;
    }

    public String getS() {
        return s;
    }

    public Map<String, List<String>> getP() {
        return p;
    }
}
