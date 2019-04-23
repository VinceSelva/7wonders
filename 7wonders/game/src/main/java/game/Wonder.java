package game;

public class Wonder {
    private String name;
    private Enum producedResource;

    /**
     * Modélise une merveille
     *
     * @param name le nom de la merveille
     * @param producedResource la ressource produite par la merveille
     */
    Wonder(String name, Enum producedResource) {
        this.name = name;
        this.producedResource = producedResource;
    }

    /**
     * Récupère le nom de la merveille
     *
     * @return le nom de la merveille
     */
    public String getName() {
        return name;
    }

    /**
     * Récupère la ressource produite par la merveille
     *
     * @return la ressource produite par la merveille
     */
    Enum getWonderResource() {
        return producedResource;
    }

    /**
     * Retourne les informations sur la merveille
     *
     * @return les informations sur la merveille au format [Wonder nomDeLaMerveille, produces ressourceProduite]
     */
    public String toString() {
        return "[Wonder " + name + ", produces " + producedResource + "]";
    }
}