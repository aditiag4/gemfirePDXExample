// Copyright (c) VMware, Inc. 2022.
// All rights reserved. SPDX-License-Identifier: Apache-2.0

/*
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor license
 * agreements. See the NOTICE file distributed with this work for additional information regarding
 * copyright ownership. The ASF licenses this file to You under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance with the License. You may obtain a
 * copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

import org.apache.geode.cache.Region;
import org.apache.geode.cache.client.ClientCache;
import org.apache.geode.cache.client.ClientCacheFactory;
import org.apache.geode.cache.client.ClientRegionFactory;
import org.apache.geode.cache.client.ClientRegionShortcut;
import org.apache.geode.cache.execute.*;

import java.text.SimpleDateFormat;
import java.util.*;

public class DataLoader implements Function {

   public static final String ID = DataLoader.class.getSimpleName();

   @Override
   public String getId() {
      return ID;
   }

   @Override
   public void execute(FunctionContext context) {
      StringBuffer sb = new StringBuffer();
      try {
         RegionFunctionContext regionContext = (RegionFunctionContext) context;
         Object[] functionArgs = (Object[]) context.getArguments();
         System.out.println("getting func args "+functionArgs);
         Integer limit = Integer.parseInt((String)functionArgs[0]);
         System.out.println("showing limit "+limit);
         context.getResultSender().sendResult(" \n Given Total Records to Insert : "+limit);
         Region<?,?> activeRegion = ((RegionFunctionContext) context).getDataSet();
         System.out.println("loading data in regions");
         loadData(activeRegion,limit);
         System.out.println("completed loading data");
         context.getResultSender().sendResult("\nCompleted Loading Data");
      } catch(Exception e) {
         sb.append("\nError while Loading Data : ");
         sb.append(e.getMessage());
      } finally {
         sb.append("\nCompleted Execution");
         context.getResultSender().lastResult(sb.toString());
      }
   }

   public abstract static class DateTimeUtils {
      public static Calendar createCalendar( final int year,  final int month,  final int day) {
         final Calendar dateTime = Calendar.getInstance();
         dateTime.clear();
         dateTime.set(Calendar.YEAR, year);
         dateTime.set(Calendar.MONTH, month);
         dateTime.set(Calendar.DAY_OF_MONTH, day);
         return dateTime;
      }

      public static Date createDate(final int year, final int month, final int day) {
         return createCalendar(year, month, day).getTime();
      }

      public static String format( final Date dateTime,  final String formatPattern) {
         return (dateTime !=  null ?  new SimpleDateFormat(formatPattern).format(dateTime) :  null);
      }
   }

   private static void loadData(final Region region,Integer limit) {
      System.out.println("inside loadData");
      for(int i=1;i<=limit;i++) {
         int keyOption = new Random().ints(0,4).findFirst().getAsInt();
         ConfigurableObject configObj = new Portfolio();
         configObj.init((int) i);
         region.put(prepareObject(keyOption,i), configObj);
//         int keyOption = new Random().ints(0,4).findFirst().getAsInt();
//         int valueOption = new Random().ints(0,4).findFirst().getAsInt();
//         region.put(prepareObject(keyOption,i),prepareObject(valueOption,i));
      }
   }

   private static Object prepareObject(int option,int index) {
      switch(option) {
         case 0:
            return index;
         case 1:
            return "User Key "+index;
         case 2:
            return new PersonKey((long)index, "User First "+index, "User Midle "+index, "User Last "+index);
         default:
            return new Person((long)index, "User First "+index, "Middle "+index, "Last "+index,
                    14122012L, Gender.MALE);

//         Address address = new Address("Anycity"+index,"TX" +index, ""+index);
//            return new PersonValue((long)index, (long)index, address);
      }
   }
}
