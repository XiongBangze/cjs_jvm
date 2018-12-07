package util;

import org.springframework.stereotype.Component;

@Component
public class VerifyUtil {
    //校验手机号
    public static String verifyPhone(String mobile) {
        if (ParamUtils.isBlank(mobile)) {
            return "请填写手机号码";
        }

        if (mobile.length() != 11 || !isNumeric(mobile)) {
            return "手机号码不正确";
        }

        return "";
    }

    //是否是纯数字，true 纯数字
    public static boolean isNumeric(String str) {
        boolean flag = true;

        if (str.length() == 0) {
            flag = false;
        } else {
            for (int i = str.length(); --i >= 0; ) {
                if (!Character.isDigit(str.charAt(i))) {
                    flag = false;
                }
            }
        }
        return flag;
    }

    /**
     * @param phone 可能有多个手机用,分隔
     * @return
     */
    public static String formatTelphone(String phone) {
        if (ParamUtils.isBlank(phone)) {
            return "";
        }
        if (phone.indexOf(",") >= 0) {
            String[] phoneArray = phone.split(",");
            for (String phonestr : phoneArray) {
                phone = addplusbyPhone(phonestr) + ",";
            }
            phone = phone.substring(0, phone.length() - 1);
        } else {
            phone = addplusbyPhone(phone);
        }


        return phone;
    }

    private static String addplusbyPhone(String phone) {
        if (phone.startsWith("86")) {
            phone = "+" + phone;
        }
        return phone;
    }

    /**
     * @param str
     * @return str是否可用
     */
    public static boolean stringVerify(String str) {
        if (ParamUtils.isBlank(str) || "NULL".equals(str.toUpperCase())) {
            return false;
        } else {
            return true;
        }
    }
    
    public static boolean stringVerify(String... strs) {
        for (String str : strs) {
            if (ParamUtils.isBlank(str) || "NULL".equals(str.toUpperCase())) {
                return false;
            }
        }
        return true;
    }
    
    public static boolean checkMcCodeValidate(String mcCode) {
		if (ParamUtils.isEmpty(mcCode)) {
			return false;
		}
		return true;
	}
}
