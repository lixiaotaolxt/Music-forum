package com.github.web.servlet;
/**
 * 个人主页
 * @autor lzy
 */

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.domain.User;
import com.github.domain.follow;
import com.github.domain.simpletext;
import com.github.service.TextService;
import com.github.service.UserService;
import com.github.service.impl.TextServiceImpl;
import com.github.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/usercenter")
public class UserCenter extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        /**
         *
         * 1.获取个人信息（user表所有内容），将其封装json发给前端
         * 2.获取已传的text，collect（标题+封面），传给前端
         * 3....
         *
         **/
        response.setCharacterEncoding("UTF-8");

        Map<String, Object> map = new HashMap<String, Object>();
        TextServiceImpl textService = new TextServiceImpl();
        UserService userService = new UserServiceImpl();
        //获取session数据

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("usermsg");
        map.put("user", user);
        System.out.println(user.getUserid());

        //接收userid

        String userid = request.getParameter("userid");
        System.out.println(userid+"---------------------------");

        //获取发过的 text 和 comment

        List<simpletext> listArtical = textService.getsimpleTextByUserID(userid);
        List<simpletext> listCollection = textService.getcollectionByUserID(userid);

        //查询user信息

        User userByUserID = userService.getUserByUserID(userid);

        //用于传递list集合，将list集合再放入一个json中传给前端


        String isself= "";
        String isfollow = "";
        if(user != null){
            if(userid.equals(user.getUserid())){

                //判断是否为自己的主页
                isself = "true";

            }else{

                //判断是否为他人主页
                isself = "false";

                // 根据userid查询数据

                map.put("user1", userByUserID);

                //判断关注情况

                follow follow = new follow();
                follow.setUserid(user.getUserid());
                follow.setFollowed(userid);
                if(userService.isFollow(follow))
                    isfollow = "true";
                else
                    isfollow = "false";

            }
            //传递list集合
            map.put("listArticle", listArtical);
            map.put("listCollection", listCollection);
            map.put("isself", isself);
            map.put("isfollow", isfollow);
            String s = JSON.toJSONString(map);
            System.out.println(s);
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(response.getWriter(), map);

        }else{
//            response.setContentType("text/html;charset=utf-8");
//            PrintWriter out = response.getWriter();
//            out.print("<script language='javascript'>alert('登录已失效请重新登陆');window.location.href='http://172.20.151.117:8066/Music_forum/login-regist-writeText/enter.html';</script>");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
