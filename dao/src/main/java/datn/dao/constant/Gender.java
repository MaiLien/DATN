package datn.dao.constant;

public enum Gender {

    MALE(0), FEMALE(1);

    private final int value;
    private Gender(int value){
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public static Gender valueOfKey(String key) {
        Gender gender = null;
        try {
            int value = Integer.valueOf(key);
            gender = valueOfKey(value);
        }
        catch (NumberFormatException e) {}
        return gender;
    }

    public static Gender valueOfKey(int key) {
        Gender gender = null;
        try {
            gender = Gender.values()[key];
        }
        catch (ArrayIndexOutOfBoundsException e) {}
        return gender;
    }

}
