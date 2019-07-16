package interviewquestion.juc;

import jdk.nashorn.internal.objects.annotations.Getter;

/**
 * @author wcg
 * @CreateDate 2019/7/8-20:16
 */
public enum CountryEnum {

    ONE(1, "��"),
    TWO(2, "��"),
    THREE(3, "��"),
    FOUR(4, "��"),
    FIVE(5, "κ"),
    SIX(6, "��");

    private Integer returnCode;
    private String returnMsg;

    public Integer getReturnCode() {
        return returnCode;
    }

    public String getReturnMsg() {
        return returnMsg;
    }


    public void setReturnCode(Integer returnCode) {
        this.returnCode = returnCode;
    }

    public void setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg;
    }

    CountryEnum(Integer returnCode, String returnMsg) {
        this.returnCode = returnCode;
        this.returnMsg = returnMsg;
    }

    public static CountryEnum foreach_CountryEnum(int index) {

        CountryEnum[] myArray = CountryEnum.values();

        for (CountryEnum element : myArray) {
            if (index == element.getReturnCode()) {
                return element;
            }

        }
        return null;

    }

}
