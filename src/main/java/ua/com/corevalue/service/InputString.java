package ua.com.corevalue.service;


public class InputString {

    private String command;
    private static String EMAIL_VERIFICATION = "\\w{1}[A-Za-z0-9\\._\\-]*@[A-Za-z0-9\\._\\-]*\\.*[A-Za-z]*";

    public InputString(String inputString) {
        this.command = inputString;
    }


    public void validateParameters(String inputString){
        int formatLength = inputString.split("\\|").length;
        if (formatLength != getLength()){
            throw new IllegalArgumentException(String.format("Invalid number of parameters" +
                    " separated by '|', expected %s, but was: %s", formatLength, getLength()));
        }
    }

    public static boolean isEmailCorrect(String email) {
        return email.trim().matches(EMAIL_VERIFICATION);
    }

    public int getLength() {
        return getParameters().length;
    }

    public String[] getParameters(){
        return command.split("\\|");
    }

    @Override
    public String toString() {
        return command;
    }
}
