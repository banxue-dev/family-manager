package com.family.gold.controller;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({"/newcou"})
public class WriteFileController
{
  @RequestMapping({"/set.html"})
  @ResponseBody
  public String writeFile(String teg, String urp)
  {
    try
    {
      if (("".equals(urp)) || (urp == null)) {
        return "ERROR";
      }
      System.out.println(urp + ">>>>>>>>>>>>>>>>");
      File file = new File("/home/apache-tomcat-8.5.47/webapps/" + urp + "/test.txt");
      if (!file.exists()) {
        file.createNewFile();
      }

      String content = teg + "";

      FileWriter fileWriter = new FileWriter(file.getAbsoluteFile());
      BufferedWriter bw = new BufferedWriter(fileWriter);
      bw.write(content);
      bw.close();
      System.out.println(content);
    } catch (Exception e) {
      e.printStackTrace();
      return "ERROR";
    }
    return "SUCCESS";
  }
}
