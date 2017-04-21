package com.me.crm.util;

import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by DJ on 2017/4/21.
 */
public class SessionUtils {
    public static boolean isCheckNum(HttpServletRequest request) {
        HttpSession session=request.getSession(false);
        if (session==null){
            return false;
        }
        String check_number_key=(String)session.getAttribute("CHECK_NUMBER_KEY");
        if (StringUtils.isBlank(check_number_key)){
            return false;
        }
        String saved=request.getParameter("checkNum");
        if (StringUtils.isBlank(saved)){
            return false;
        }
        return check_number_key.equalsIgnoreCase(saved);
    }

}
