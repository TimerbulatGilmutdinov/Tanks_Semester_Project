package protocol;

import java.util.List;

public class Content {
    private List<String> argsFromInputStream;
    public List<String> getContentArgsList(){
        return argsFromInputStream;
    }
}
