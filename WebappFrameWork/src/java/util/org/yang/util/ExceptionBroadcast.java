package org.yang.util;

public class ExceptionBroadcast
{
   public static void print(Throwable t) 
   {
      System.out.println("!!!!!!!!!!!!!!!!> This Exception Has been caught Begin <!!!!!!!!!!!!!!!!!!!");
      t.printStackTrace();	
      System.out.println("!!!!!!!!!!!!!!!!!!> This Exception Has been caught end <!!!!!!!!!!!!!!!!!!!");
   }
}