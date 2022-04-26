/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controller;

/**
 *
 * @author USER
 */
public class session {
    private static String user_id;
    private static String level;
    private static String username;
    
    public static String getId(){
    return user_id;
    }
    public static void setGetId(String user_id){
    session.user_id=user_id;
    }
     public static String getLevel(){
        return level;
    }
    public static void setGetLevel(String level){
        session.level = level;
    }
    public static String getUsername(){
        return username;
    }
    public static void setGetUsername(String username){
        session.username = username;
    }
}
