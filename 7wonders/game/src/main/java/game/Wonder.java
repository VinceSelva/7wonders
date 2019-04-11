package game;

public class Wonder {
    private String name;
    private Enum producedResource;

    public Wonder(String name, Enum producedResource) {
        this.name = name;
        this.producedResource = producedResource;
    }

    public String getName() {
        return name;
    }

    public Enum getWonderResource() {
        return producedResource;
    }

    public String toString() {
        return "[Wonder " + name + ", produces " + producedResource + "]";
    }
}