package com.loserico.retryboot.utils;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;

@Slf4j
public class SmsUtil {

   private static Random random = new Random();
   /**
    * 发送短信
    */
   public static boolean sendSms(int num) {
      // 使用随机数模拟重试场景
      return switch (num) {
         case 0 ->
               // 模拟发生参数异常
               throw new IllegalArgumentException("参数有误！");
         case 1 ->
               // 模拟发生数组越界异常
               throw new ArrayIndexOutOfBoundsException("数组越界！");
         case 2 ->
               // 模拟成功
               true;
         case 3 ->
               // 模拟发生空指针界异常
               throw new NullPointerException();
         default ->
               // 未成功则返回false
               false;
      };

   }
}