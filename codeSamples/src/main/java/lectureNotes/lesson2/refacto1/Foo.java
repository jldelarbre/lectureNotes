package lectureNotes.lesson2.refacto1;

public class Foo {

    private int param1;
    private int param2;
    private String paramString;
    
    public int doWork() {
        // Never do an assignment on param1, param2 or paramString outside setters
        return param1 + param2;
    }
    
    public int doOtherWork() {
        return param1 + paramString.length();
    }
    
    public int doThirdWork() {
        if (paramString != null) {
            return param1 + paramString.length();
        }
        return param1;
    }
    
    public int getParam1() {
        return param1;
    }
    public void setParam1(int param1) {
        this.param1 = param1;
    }
    public int getParam2() {
        return param2;
    }
    public void setParam2(int param2) {
        this.param2 = param2;
    }
    public String getParamString() {
        return paramString;
    }
    public void setParamString(String paramString) {
        this.paramString = paramString;
    }
}
