package datn.interfaces.util;

public class FormatSearchInput {
    public static String formatSearchInput(String searchInput){
        StringBuffer formatSearchInput = new StringBuffer();
        String[] arrText = searchInput.trim().split(" ");
        formatSearchInput.append("%");
        for (int i = 0; i < arrText.length; i++){
            formatSearchInput.append(arrText[i].toLowerCase());
            formatSearchInput.append("%");
        }

        return  formatSearchInput.toString();
    }
}
