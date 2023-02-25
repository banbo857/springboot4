package com.controller;

import com.sun.deploy.net.HttpResponse;
import com.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;

@RestController
public class TestController {

    @Autowired
    private RedisUtil redisUtil;

    public static String code(){
        String code = "";
        for(int i = 0; i < 4; i++){
            code += (int)(Math.random()*10);
        }
        return code;
    }

    private String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip != null && !"".equals(ip) && !"unknown".equalsIgnoreCase(ip)) {
            if (ip.indexOf (",") > 0) {
                ip = ip.substring (0, ip.indexOf (","));
            }
            if (ip.equals ("127.0.0.1")) {
                //根据网卡取本机配置的IP
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost ();
                } catch (Exception e) {
                    e.printStackTrace ();
                }
                ip = inet.getHostAddress ();
            }
        }

        if (request.getHeader("X-Real-IP") != null && !"".equals(request.getHeader("X-Real-IP")) && !"unknown".equalsIgnoreCase(request.getHeader("X-Real-IP"))) {
            ip = request.getHeader("X-Real-IP");
        }

        if (request.getHeader("Proxy-Client-IP") != null && !"".equals(request.getHeader("Proxy-Client-IP")) && !"unknown".equalsIgnoreCase(request.getHeader("Proxy-Client-IP"))) {
            ip = request.getHeader("Proxy-Client-IP");
        }

        if (request.getHeader("WL-Proxy-Client-IP") != null && !"".equals(request.getHeader("WL-Proxy-Client-IP")) && !"unknown".equalsIgnoreCase(request.getHeader("WL-Proxy-Client-IP"))) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }

        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    @RequestMapping("/index")
    public String test(HttpServletRequest request){
        //获取ip
        String ip =getIpAddress(request);
        System.out.println(ip);
        //获取code
        String code = code();
        System.out.println(code);
        //存入redis
        redisUtil.set(ip,code,20);
        return "success";
    }

    @RequestMapping("/map")
    public String map(){
        Map<String,String> map = new HashMap<>();
        map.put("name","张三");
        map.put("age","18");

        redisUtil.hset("map","name",map);
        return "success";
    }

    @RequestMapping("/getMap")
    public Map<String,String> getMap(){
        Map<String,String> map = (Map<String, String>) redisUtil.hget("map","name");
        return map;
    }

    @RequestMapping("/test/{code}")
    public String test(@PathVariable("code") String code, HttpServletRequest request) {
        //获取ip
        String ip =getIpAddress(request);
        String v = (String) redisUtil.get(ip);
        if (v != null) {
            if (v.equals(code)) {
                return "success";
            } else {
                return "fail";
            }
        }
        return "验证超时";

    }

    @RequestMapping("/get/{key}")
    public String get(@PathVariable("key") String key) {
        return (String) redisUtil.get(key);
    }

    @RequestMapping("/delete/{key}")
    public String delete(@PathVariable("key") String key) {
        redisUtil.del(key);
        return "success";
    }


}
