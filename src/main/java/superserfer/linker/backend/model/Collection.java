package superserfer.linker.backend.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Collection {
    private String title;

    private List<Link> links;

    public Collection() {
        links = new ArrayList<>();
    }

    public Collection(String title, List<Link> links) {
        this.title = title;
        this.links =links;
    }
}
