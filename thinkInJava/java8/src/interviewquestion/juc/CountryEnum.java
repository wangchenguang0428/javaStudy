package interviewquestion.juc;

import jdk.nashorn.internal.objects.annotations.Getter;

/**
 * @author wcg
 * @CreateDate 2019/7/8-20:16
 */
public enum CountryEnum {

    ONE(1, "Æë"),
    TWO(2, "³þ"),
    THREE(3, "Ñà"),
    FOUR(4, "ÕÔ"),
    FIVE(5, "Îº"),
    SIX(6, "º«");

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
