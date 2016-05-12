package datn.interfaces.constant;

public class MessageCodeConstant {

    private MessageCodeConstant(){}

    public static final String SUCCESS = "0";
    public static final String ERROR_INTERNAL_SERVER = "500";
    public static final String ERROR_USER_NOT_FOUND = "1500";
    public static final String ERROR_ACCESS_DENIED = "1501";
    public static final String ERROR_HTTP_MESSAGE_NOT_READABLE = "1502";
    public static final String ERROR_LOGIN = "1053";
    public static final String ERROR_ENTRY_POINT = "1054";
    public static final String ERROR_EXCEL_FILE_NOT_FOUND= "1056";
    public static final String ERROR_EXTENSION_EXCEL_FILE = "1057";
    public static final String ERROR_NOT_FULL_INPUT_AT_ROW = "1058";
    public static final String ERROR_SHEET_NOT_FOUND = "1059";
    public static final String ERROR_USER_EXISTED = "1060";
    public static final String PROJECT_WAVE_NOT_FOUND = "1061";
    public static final String STUDENT_WAVE_IS_EXISTED = "1062";
    public static final String TEACHER_WAVE_IS_EXISTED = "1063";
    public static final String ERROR_STUDENT_CAN_NOT_JOIN_OTHER_WAVE_IN_SAME_TIME = "1064";
    public static final String ERROR_TEACHER_IN_WAVE_EXIST_MORE_ONE = "1065";
    public static final String ERROR_ASSIGNMENT_EXISTED = "1066";
    public static final String ERROR_ASSIGNMENT_NOT_EXISTED = "1067";
    public static final String ERROR_STUDENT_NOT_FOUND = "1068";
    public static final String ERROR_TEACHER_NOT_FOUND = "1069";
    public static final String ERROR_TEACHER_NOT_IN_WAVE = "1070";
    public static final String ERROR_STUDENT_CAN_NOT_REGISTER_MORE_ONE_TEACHER_IN_SAME_WAVE = "1071";

}
