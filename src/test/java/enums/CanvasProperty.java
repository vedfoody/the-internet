package enums;

/**
 * Created by thuan on 10/01/2017.
 */
public enum CanvasProperty {
    FONT("font");

    private String name;

    private CanvasProperty(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
