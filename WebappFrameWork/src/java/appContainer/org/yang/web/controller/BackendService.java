/* by Steven Yang */

package org.yang.web.controller;
import java.util.Properties;

public interface BackendService
{
   void init(Properties prop);

   void start();

   void stop();

   void destroy();
}
