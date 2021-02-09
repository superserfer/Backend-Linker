package superserfer.linker.backend.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Link {

    private String name;
    private String url;

    public Link(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public Link() {
    }
}
