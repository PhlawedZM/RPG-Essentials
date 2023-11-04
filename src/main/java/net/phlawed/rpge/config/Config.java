package net.phlawed.rpge.config;

import com.google.gson.stream.JsonWriter;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


public class Config{

     public static Properties prop = new Properties();
     public static String path = "config/rpge.properties";
     public static String comment = "This is rpge config, read carefully";



     public static void load() throws IOException {
          File file = new File(path);
          if(file.exists()) {
               prop.load(new FileInputStream(path));
          }
          else {
               prop.setProperty("RichOres", "false");
               prop.store(new FileOutputStream(path), comment);
          }
     }

     public static void setString(String key, String value) throws IOException {
          prop.setProperty(key, value);
          prop.store(new FileOutputStream(path), comment);
     }

     public static void setBoolean(String key, Boolean value) throws IOException {
          prop.setProperty(key, Boolean.toString(value));
          prop.store(new FileOutputStream(path), comment);
     }

     public static void setInt(String key, Integer value) throws IOException {
          prop.setProperty(key, Integer.toString(value));
          prop.store(new FileOutputStream(path), comment);
     }
     public static String getString(String key){
          return prop.getProperty(key);
     }
     public static Boolean getBoolean(String key){
          return Boolean.getBoolean(prop.getProperty(key));
     }

     public static void reload() {

     }

     public static void defaults() {
          Map<String,String> list = new HashMap<>();
     }
}
