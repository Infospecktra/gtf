package org.yang.services.dataAccess;

import java.util.Iterator;
import java.util.HashSet;

public class CreatedTableNameArchives {

  
    public boolean addTable(String tablename ) {
        if(tablename==null||"".equals(tablename))
            return false;
        System.out.println("[CreatedTableNameSave::addTable]---add new table to archives.");
        tableSet.add(tablename);
        return true;
    }

    public String removeTable(String tablename) {
       
       tableSet.remove(tablename);
       return tablename;
    }

    public int size() {
       return tableSet.size();
    }


    public boolean checkExist(String tablename) {
    	
        return tableSet.contains(tablename);
    }

    public void  archivesPrintOut()
    {
       String tablename = "";
       Iterator iterator = tableSet.iterator();
       while (iterator.hasNext()) 
       {
          tablename = (String)iterator.next();
          System.out.println("=======================================================");	
          System.out.println("[CreatedTableNameSave::archivesPrintOut]--tablename="+tablename);
          System.out.println("=======================================================");	
       }	
    }	
    
    private static HashSet tableSet = new HashSet();;
    private static CreatedTableNameArchives me;
    
    public static CreatedTableNameArchives getInstance() {
        if(null==me)
        {
            synchronized(tableSet)
            {
                if(null==me)
                {
                    me = new CreatedTableNameArchives();
                    
                }
            }
        }
        return me;
    }    
    
}
