/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.leapfrog.handicraftscrapper;

import com.leapfrog.handicraftscrapper.RegexMatcher;
import java.io.FileWriter;

import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author apple
 */
public class Handicraft {

    public static final String base_url = "http://www.epch.in/index.php?option=com_jumi&fileid=4&Itemid=162&page=";

    public static void main(String[] args) {
        int count = 178;
        String maindata="";
        String empty="";
        RegexMatcher regexMatcher = new RegexMatcher();
       

           
            try {
       FileWriter writer = new FileWriter("/users/apple/desktop/handicraft1.csv");
 for (int i = 178; i <= 1002; i++) {
      String main_url = base_url + i;
                Document allDocs = Jsoup.connect(main_url).timeout(0).get();
                Elements allelements = allDocs.select("table[cellpadding=2][cellspacing=2][width=100%][style=padding:5px;font-family:verdana;]");
                for (Element d : allelements) {
                    StringBuilder builder = new StringBuilder();
                    String content = d.html();
                    Scanner scanner = new Scanner(content);
                    while (scanner.hasNextLine()) {
                        String line = scanner.nextLine();
                        builder.append(line);
                    }

                    // System.out.println(builder.toString());
                    String regex = "class=\"bolder\" colspan=\"2\">(.*?)</td>.*?\"2\">(.*?)</td>.*?\"2\">(.*?)</td>.*?<td>(.*?)</td>.*?to:.*?\">(.*?)</a>.*?<td>(.*?)</td>.*?<td>(.*?)</td>";
                    Matcher matcher = regexMatcher.match(regex, builder.toString());

                    while (matcher.find()) {
                        String CompanyName = matcher.group(1).replaceAll(",", " ");
                       
                        maindata=CompanyName;
                        
                        String Address = matcher.group(2).replaceAll(",", " ");
                        if(Address.isEmpty()){
                            maindata= maindata +","+ empty;
                        }else{
                        maindata=maindata + "," + Address;
                        }
                        
                        String City = matcher.group(3).replaceAll(",", " ");
                         if(City.isEmpty()){
                            maindata= maindata +","+ empty;
                        }else{
                        maindata= maindata + ","+City;
                         }
                         
                         String Telephone = matcher.group(4).replaceAll(",", " ");
                         if(Telephone.isEmpty()){
                            maindata= maindata +","+ empty;
                        }else{
                         
                        maindata=maindata +"," + Telephone;
                         }
                         String Email = matcher.group(5).replaceAll(",", " ");
                         
                        if(Email.isEmpty()){
                            maindata= maindata +","+ empty;
                        }else{
                          
                        maindata=maindata +"," +Email;
                        }
                        
                        String Contact = matcher.group(6).replaceAll(",", " ");
                         if(Contact.isEmpty()){
                            maindata= maindata +","+ empty;
                        }else{
                          
                        maindata = maindata +","+ Contact;
                         }
                         String Products = matcher.group(7).replaceAll(",", " ");
                        if(Products.isEmpty()){
                            maindata= maindata +","+ empty + "\r\n";
                        }else{
                          
                         maindata = maindata +"," + Products + "\r\n";
                        }
                        
                        System.out.println("CompanyName" + CompanyName);
                        System.out.println("Address :" +Address);
                        System.out.println("City:"+City);
                        System.out.println("Telephone:"+Telephone);
                        System.out.println("Email" +Email);
                        System.out.println("Contact" +Contact);
                        System.out.println("Products"+Products);
                        System.out.println("==========" + count + "===============");
                     
                       writer.write(maindata);
                    }
                    
                    
                    

                  } count++;

                }
                  
                  writer.close();
                
            } catch (IOException ioe) {
                System.out.println(ioe.getMessage());
            

        }
    }

}
