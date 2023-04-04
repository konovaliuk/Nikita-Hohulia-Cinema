package com.hohulia.cinema.utilities;

import com.hohulia.cinema.entities.User;

import javax.servlet.http.HttpServletRequest;

public class RequestUtils {
    private static final String LOGIN = "!/login";
    public static boolean authorized(HttpServletRequest request){
        User user = (User)request.getSession().getAttribute("currentUser");
        return user != null;
    }
}
