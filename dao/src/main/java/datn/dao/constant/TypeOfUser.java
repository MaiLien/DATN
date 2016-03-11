package datn.dao.constant;

public enum TypeOfUser {

    TEACHER(0), STUDENT(1), OFFICER(2), ADMIN(3);

    private final int value;
    private TypeOfUser(int value){
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public static TypeOfUser valueOfKey(String key) {
        TypeOfUser typeOfUser = null;
        try {
            int value = Integer.valueOf(key);
            typeOfUser = valueOfKey(value);
        }
        catch (NumberFormatException e) {}
        return typeOfUser;
    }

    public static TypeOfUser valueOfKey(int key) {
        TypeOfUser typeOfUser = null;
        try {
            typeOfUser = TypeOfUser.values()[key];
        }
        catch (ArrayIndexOutOfBoundsException e) {}
        return typeOfUser;
    }

}
