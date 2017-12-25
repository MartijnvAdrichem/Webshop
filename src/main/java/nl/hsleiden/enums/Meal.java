package nl.hsleiden.enums;

public enum Meal {
    ONTBIJT("Ontbijt"),
    OCHTEND_TUSSENDOORTJE("Ochtend tussendoortje"),
    MIDDAG_TUSSENDOORTJE("Middag tussendoortje"),
    LUNCH("Lunch"),
    AVONDETEN("Avondeten"),
    SNACK("Snack");

    String name;

    Meal(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
