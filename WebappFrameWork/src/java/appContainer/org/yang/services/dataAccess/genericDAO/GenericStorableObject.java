/* by Steven Yang */
package org.yang.services.dataAccess.genericDAO;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Iterator;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.Blob;
import org.yang.services.dbService.DataAccessException;

abstract public class GenericStorableObject implements Storable
{
   static final long serialVersionUID = 5574368329731949206L;

   private static Class StringClass = "".getClass();
   private static Class DateClass = (new Date(0)).getClass();
   private static Class TimeClass = (new Time(0)).getClass();
   private static Class TimestampClass = (new Timestamp(0)).getClass();

   transient private HashMap setterMap = null;
   transient private HashMap getterMap = null;
   transient private HashMap fieldMap  = null;
   
   transient private HashMap getterMap2 = null;
   transient private String primaryKeyName = null;

   transient private String[] columnNames = null;
   public void overrideProcessColumns(String[] overrideProcessColumns) { this.columnNames = overrideProcessColumns; }

   protected String tablename = null;
   public String getTablename() { return tablename; }
   public void setTablename(String tablename) { this.tablename = tablename; }

   public GenericStorableObject()
   {
      prepareColumns();
      getterMap2 = new HashMap();
   }

   public final String getPrimaryKey()
   {
      if(null==columnNames)
         prepareColumns();
      return primaryKeyName;
   }

   public String[] getProcessColumns()
   {
      return getAllColumns();
   }

   public final String[] getAllColumns()
   {
      if(null==columnNames)
         prepareColumns();
      return columnNames;
   }

   public String prepareSQLDropTable()
   {
      return "DROP TABLE " + getTablename();
   }

   public String prepareSQLLoad(String conditions)
   {
      if(null==columnNames)
         prepareColumns();

      StringBuffer sql = new StringBuffer("select ");

      if(null!=primaryKeyName)
      {
         sql.append(primaryKeyName).append(", ");
      }

      for(int i=0; i<columnNames.length; i++)
      {
         if(0!=i)
            sql.append(", ");
         sql.append(getTablename()).append(".").append(columnNames[i]);
      }

      sql.append(" from ").append(getTablename());

      if(null!=conditions)
         sql.append(" where ").append(conditions);

      return sql.toString();
   }

   public DBExcutionDirector getLoadDirector()
   {
      return null;
   }

   public String prepareSQLInsert()
   {
      if(null==columnNames)
         prepareColumns();

      StringBuffer sqlHead = new StringBuffer("insert into ").append(getTablename())
                                                             .append(" (");
      StringBuffer sqlTail = new StringBuffer(" values (");

      for(int i=0; i<columnNames.length; i++)
      {
         if(0!=i)
         {
            sqlHead.append(", ");
            sqlTail.append(", ");
         }
         sqlHead.append(this.getTablename()).append(".").append(columnNames[i]);
         sqlTail.append("?");
      }

      if(null!=primaryKeyName)
      {
         sqlHead.append(", ").append(primaryKeyName);
         sqlTail.append(", ?");
      }

      sqlHead.append(")");
      sqlTail.append(")");

      return sqlHead.append(sqlTail).toString();
   }

   public String prepareSQLUpdate()
   {
      return prepareSQLUpdate(null);
   }

   public String prepareSQLUpdate(String conditions)
   {
      if(null==columnNames)
         prepareColumns();

      StringBuffer sql =
        new StringBuffer("update ").append(this.getTablename())
                                   .append(" set ");

      for(int i=0; i<columnNames.length; i++)
      {
         if(0!=i)
         {
            sql.append(", ");
         }
         sql.append(this.getTablename()).append(".").append(columnNames[i]).append(" = ?");
      }

      if(null==conditions)
         sql.append(" where ").append(primaryKeyName).append(" = ?");
      else
         sql.append(" where ").append(conditions);

      return sql.toString();
   }

   public String prepareSQLDelete(String conditions)
   {
      StringBuffer sql = new StringBuffer("delete from ").append(this.getTablename());

      if(null!=conditions)
         sql.append(" where ").append(conditions);

      return sql.toString();
   }

   public String prepareSQLCheckExist(String conditions)
   {
      //SELECT COUNT(*) FROM TEST01_oeemarketorder where orderid='DCPP:AAPL:Long-20050913-0945_single';
      StringBuffer sql = new StringBuffer("select count(*) as ct from ").append(this.getTablename());

      if(null!=conditions)
         sql.append(" where ").append(conditions);

      return sql.toString();
   }

   public void exportData(PreparedStatement ps)
   {
      if(null==columnNames)
         prepareColumns();
      try
      {
         for(int i=0; i<columnNames.length; i++)
         {
            //System.out.println("->" + columnNames[i]);
            setData(ps, columnNames[i], i+1);
         }

         if(null!=primaryKeyName)
            setData(ps, primaryKeyName, columnNames.length+1);
      }
      catch(Exception e)
      {
         e.printStackTrace();
         //throw new DataAccessException(e.getMessage());
      }
   }

   public void importData(ResultSet rs) throws DataAccessException
   {
      if(null==columnNames)
         prepareColumns();
      try
      {
         int length = columnNames.length;
         int rLength = rs.getFetchSize();
         if(null!=primaryKeyName)
         {
            //if((length+1)!=rLength)
            //   throw new DataAccessException("Number of return column doesn't match: expecting "+(length+1)+ ", return " + rLength);
            setData(rs, primaryKeyName);
         }
         //else
         //{
         //   if(length!=rLength)
         //      throw new DataAccessException("Number of return column doesn't match: expecting "+(length+1)+ ", return " + rLength);
         //}

         for(int i=0; i<columnNames.length; i++)
         {
            setData(rs, columnNames[i]);
         }
      }
      catch(DataAccessException dae)
      {
         throw dae;
      }
      catch(Exception e)
      {
         //e.printStackTrace();
         throw new DataAccessException(e.getMessage());
      }
   }

   public void importData(HashMap dataMap)
   {
      if(null==columnNames)
         prepareColumns();
      try
      {
         if(null!=primaryKeyName)
         {
            setData((String)dataMap.get(primaryKeyName), primaryKeyName);
         }

         for(int i=0; i<columnNames.length; i++)
         {
            setData((String)dataMap.get(columnNames[i]), columnNames[i]);
         }
      }
      catch(Exception e)
      {
         e.printStackTrace();
         //throw new DataAccessException(e.getMessage());
      }
   }

   public void set(String columnName, Object value)throws IllegalArgumentException
   {
      try
      {
         ((Method)setterMap.get(columnName)).invoke(this, new Object[]{value});
      }
      catch(InvocationTargetException ite)
      {
         System.out.println("Invoke 'set field' fail : " + columnName);
      }
      catch(IllegalAccessException e)
      {
         System.out.println("Illegal access 'set field' : " + columnName);
      }
      catch(IllegalArgumentException iae)
      {
         throw iae;
      }
      catch(Exception e)
      {
         System.out.println("Unexpected Exception happen when set column :" + columnName + ", error : " + e.getMessage());
      }
   }

   public Object get(String columnName)throws IllegalArgumentException
   {
      if(null==columnName)
         return null;

      try
      {
         Method getter =(Method)getterMap.get(columnName);

         if(null==getter)
            getter = (Method)getterMap2.get(columnName);
            
         if(null==getter)
         {
            getter = (Method)this.getClass()
                                 .getDeclaredMethod(generateMethodName(columnName)[1], new Class[]{});
            getterMap2.put(columnName, getter);
         }
         
         return getter.invoke(this, (Object[])null);//null);
      }
      catch(InvocationTargetException ite)
      {
         System.out.println("Invoke 'get field' fail : " + columnName);
      }
      catch(IllegalAccessException e)
      {
         System.out.println("Illegal access 'get field' : " + columnName);
      }
      catch(IllegalArgumentException iae)
      {
         throw iae;
      }
      catch(Exception e)
      {
         System.out.println("Unexpected Exception happen when get column :" + columnName + ", error : " + e.getMessage());
      }

      return null;
   }

   public String toString()
   {
      StringBuffer sb = new StringBuffer();
      Iterator it = fieldMap.keySet().iterator();
      String key = null;
      Object value = null;
      while(it.hasNext())
      {
         key = (String)it.next();
         try
         {
            if(null==(value=get(key)))
               value = "NULL Field";
            sb.append(key).append("=").append(value).append("\r\n");
         }catch(Exception e) { e.printStackTrace(); }
      }
      return sb.toString();
   }

   private void prepareColumns()
   {
      setterMap = new HashMap();
      getterMap = new HashMap();
      fieldMap = new HashMap();
      
      Field[] fields = this.getClass().getDeclaredFields();
      int fieldSize = fields.length;
      String columnName = null;
      ArrayList columnNamesList = new ArrayList();
      String[] methodNames = null;
      Method method = null;
      for(int i=0; i<fieldSize; i++)
      {
         columnName = fields[i].getName();
         if(columnName.startsWith("col_"))
         {
            columnName = columnName.substring(4);
            columnNamesList.add(columnName);
         }
         else if(columnName.startsWith("key_"))
         {
            primaryKeyName = columnName.substring(4);
            columnName = primaryKeyName;
         }
         else
            continue;

         fieldMap.put(columnName, fields[i]);
         methodNames = generateMethodName(columnName);
         try
         {
            if(null!=(method=this.getClass().getDeclaredMethod(methodNames[0], new Class[]{fields[i].getType()})))
               setterMap.put(columnName, method);
         }
         catch(Exception e) {}

         try
         {
            if(null!=(method=this.getClass().getDeclaredMethod(methodNames[1], new Class[]{})))
               getterMap.put(columnName, method);
         }
         catch(Exception e) {}
      }
      columnNames = (String[])columnNamesList.toArray(new String[]{});
   }

   private void setData(PreparedStatement ps, String columnName, int counter)
   {
      try
      {
         Class fieldType = ((Field)fieldMap.get(columnName)).getType();

         if(fieldType.isAssignableFrom(StringClass))
         {
            ps.setString(counter, (String)get(columnName));
         }
         else if(fieldType.isAssignableFrom(Double.TYPE))
         {
            ps.setDouble(counter, ((Double)get(columnName)).doubleValue());
         }
         else if(fieldType.isAssignableFrom(Float.TYPE))
         {
            ps.setFloat(counter, ((Float)get(columnName)).floatValue());
         }
         else if(fieldType.isAssignableFrom(Integer.TYPE))
         {
            ps.setInt(counter, ((Integer)get(columnName)).intValue());
         }
         else if(fieldType.isAssignableFrom(Long.TYPE))
         {
            ps.setLong(counter, ((Long)get(columnName)).longValue());
         }
         else if(fieldType.isAssignableFrom(DateClass))
         {
            ps.setDate(counter, (Date)get(columnName));
         }
         else if(fieldType.isAssignableFrom(TimeClass))
         {
            ps.setTime(counter, (Time)get(columnName));
         }
         else if(fieldType.isAssignableFrom(TimestampClass))
         {
            ps.setTimestamp(counter, (Timestamp)get(columnName));
         }
         else if(fieldType.isAssignableFrom(Boolean.TYPE))
         {
            ps.setBoolean(counter, ((Boolean)get(columnName)).booleanValue());
         }
      }
      catch(IllegalArgumentException iae)
      {
         System.out.println("[GenericStorableObject::setData] Illegal argument found when populate field : " + columnName);
      }
      catch(Exception e)
      {
         System.out.println("[GenericStorableObject::setData] Unable to populate field " + columnName + ":" + e.getMessage());
      }
   }

   /*
   private void setData(ResultSet rs, String columnName, int counter)
   {
      try
      {
         Class fieldType = ((Field)fieldMap.get(columnName)).getType();

         if(fieldType.isAssignableFrom(StringClass))
         {
            set(columnName, rs.getString(counter));
         }
         else if(fieldType.isAssignableFrom(Double.TYPE))
         {
            set(columnName, new Double(rs.getDouble(counter)));
         }
         else if(fieldType.isAssignableFrom(Float.TYPE))
         {
            set(columnName, new Float(rs.getFloat(counter)));
         }
         else if(fieldType.isAssignableFrom(Integer.TYPE))
         {
            set(columnName, new Integer(rs.getInt(counter)));
         }
         else if(fieldType.isAssignableFrom(Long.TYPE))
         {
            set(columnName, new Long(rs.getLong(counter)));
         }
         else if(fieldType.isAssignableFrom(DateClass))
         {
            set(columnName, rs.getDate(counter));
         }
         else if(fieldType.isAssignableFrom(TimeClass))
         {
            set(columnName, rs.getTime(counter));
         }
         else if(fieldType.isAssignableFrom(TimestampClass))
         {
            set(columnName, rs.getTimestamp(counter));
         }
         else if(fieldType.isAssignableFrom(Boolean.TYPE))
         {
            set(columnName, new Boolean(rs.getBoolean(counter)));
         }
      }
      catch(IllegalArgumentException iae)
      {
         System.out.println("Illegal argument found when set field : " + columnName);
      }
      catch(Exception e)
      {
         e.printStackTrace();
      }
   }
   */

   private void setData(ResultSet rs, String columnName, int counter) throws DataAccessException
   {
      setData(rs, columnName);
   }

   private void setData(ResultSet rs, String columnName) throws DataAccessException
   {
      try
      {
         Class fieldType = ((Field)fieldMap.get(columnName)).getType();

         if(fieldType.isAssignableFrom(StringClass))
         {
            set(columnName, rs.getString(columnName));
         }
         else if(fieldType.isAssignableFrom(Double.TYPE))
         {
            set(columnName, new Double(rs.getDouble(columnName)));
         }
         else if(fieldType.isAssignableFrom(Float.TYPE))
         {
            set(columnName, new Float(rs.getFloat(columnName)));
         }
         else if(fieldType.isAssignableFrom(Integer.TYPE))
         {
            set(columnName, new Integer(rs.getInt(columnName)));
         }
         else if(fieldType.isAssignableFrom(Long.TYPE))
         {
            set(columnName, new Long(rs.getLong(columnName)));
         }
         else if(fieldType.isAssignableFrom(DateClass))
         {
            set(columnName, rs.getDate(columnName));
         }
         else if(fieldType.isAssignableFrom(TimeClass))
         {
            set(columnName, rs.getTime(columnName));
         }
         else if(fieldType.isAssignableFrom(TimestampClass))
         {
            set(columnName, rs.getTimestamp(columnName));
         }
         else if(fieldType.isAssignableFrom(Boolean.TYPE))
         {
            set(columnName, new Boolean(rs.getBoolean(columnName)));
         }
      }
      catch(IllegalArgumentException iae)
      {
         throw new DataAccessException("Illegal argument found when set field : " + columnName);
      }
      catch(Exception e)
      {
         throw new DataAccessException("Unable to load field " + columnName + ":" + e.getMessage());
      }
   }

   private void setData(String value, String columnName)
   {
      if("".equals(value))
         return;

      try
      {
         Class fieldType = null;

         if(null==(fieldType=((Field)fieldMap.get(columnName)).getType()))
         {
            System.out.println("[GenericStorableObject::setData] Field is not found:" + columnName);
            return;
         }

         if(fieldType.isAssignableFrom(StringClass))
         {
            set(columnName, value);
         }
         else if(fieldType.isAssignableFrom(Double.TYPE))
         {
            set(columnName, new Double(value));
         }
         else if(fieldType.isAssignableFrom(Float.TYPE))
         {
            set(columnName, new Float(value));
         }
         else if(fieldType.isAssignableFrom(Integer.TYPE))
         {
            set(columnName, new Integer(value));
         }
         else if(fieldType.isAssignableFrom(Long.TYPE))
         {
            set(columnName, new Long(value));
         }
         else if(fieldType.isAssignableFrom(DateClass))
         {
            set(columnName, Date.valueOf(value));
         }
         else if(fieldType.isAssignableFrom(TimeClass))
         {
            set(columnName, Time.valueOf(value));
         }
         else if(fieldType.isAssignableFrom(Boolean.TYPE))
         {
            set(columnName, new Boolean(value));
         }
      }
      catch(IllegalArgumentException iae)
      {
         System.out.println("Illegal argument found when set field : " + columnName);
      }
      catch(Exception e)
      {
         e.printStackTrace();
      }
   }

   public String[] generateMethodName(String fieldName)
   {
      String[] methodNames = new String[2];
      StringBuffer setter = new StringBuffer("set");
      StringBuffer getter = new StringBuffer("get");
      StringBuffer nFieldName =
       new StringBuffer(fieldName.substring(0, 1).toUpperCase()).append(fieldName.substring(1));
      methodNames[0] = setter.append(nFieldName).toString();
      methodNames[1] = getter.append(nFieldName).toString();

      return methodNames;
   }

   //private void writeObject(ObjectOutputStream out) throws IOException
   //{
      // Take care of this class's field first by calling defaultWriteObject
   //   out.defaultWriteObject();
   //}

   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException
   {
      try
      {
         // Take care of this class's field first by calling defaultWriteObject
         in.defaultReadObject();
         prepareColumns();
         getterMap2 = new HashMap();
      }
      catch(Exception e)
      {
         e.printStackTrace();
      }
   }
}