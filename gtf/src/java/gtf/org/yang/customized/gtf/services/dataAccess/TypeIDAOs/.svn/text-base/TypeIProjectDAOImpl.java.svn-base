package org.yang.customized.gtf.services.dataAccess.TypeIDAOs;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import org.yang.services.dbService.RDBMSFactory;
import org.yang.services.dbService.RDBMS;
import org.yang.util.ExceptionBroadcast;
import org.yang.customized.gtf.services.dataAccess.ProjectDAO;
import org.yang.customized.gtf.services.dataAccess.Project;
import org.yang.customized.gtf.services.dataAccess.ProjectDataAccessException;
import java.util.Collections;
import org.yang.customized.gtf.services.dataAccess.ProjectFactory;
import org.yang.services.dataAccess.TypeIDAOImpl;
import org.yang.services.dbService.DataAccessException;
import org.yang.customized.gtf.services.dataAccess.ProjectComparator;
import org.yang.services.dbService.DBConnectionCount;
/**
 *
 * @ version 1.0
 * @ Date:   06/19/2001
 * @ programmer: Huei-Wen(Celina) Yang
 * @
 */
public class TypeIProjectDAOImpl extends TypeIDAOImpl implements ProjectDAO
{
   /**
    * Constructor
    */
   public TypeIProjectDAOImpl(String type, RDBMS rdbms)
   {
      super(type + "_project", rdbms);
   }

   /**
   * create a new group table in database
   *@exception:DataAccessException
   *@param
   *@return: boolean
   */
   public boolean createTable() //throws AccountDataAccessException
   {
      boolean isTableExist = tableNameArchives.checkExist(tablename);	
      //System.out.println("[TypeIProjectDAOImpl::createTable]----->isTableExist="+isTableExist);
      if(isTableExist)
         return true;
      else
      {
         tableNameArchives.addTable(tablename);
         //System.out.println("[TypeIProjectDAOImpl::createTable]----->add table ("+tablename+") to archives.");
         
      } 
   	
      StringBuffer sql = new StringBuffer("CREATE TABLE ").append(tablename)
                         .append(" (id varchar(255)not null")
                         .append(", type varchar(255)")
                         .append(", name varchar(255)")
                         .append(", accountId varchar(255)")
                         .append(", protocolNumber varchar(255)")
                         .append(", description varchar(255)")
                         .append(", serialNumber bigint")
                         .append(", domain varchar(255)")
                         .append(", labHead varchar(255)")
                         .append(", investigator varchar(255)")
                         .append(", fileInDate bigint")
                         .append(", startDate bigint")
                         .append(", endDate bigint")
                         .append(", datas varchar(255)")
                         .append(", primary key(id))");

      try
      {
         rdbms.executeUpdate(sql.toString());
      }
      catch(Exception e)
      {
         //throw new AccountDataAccessException(e.getMessage());
         //System.out.println("[TypeIProjectDAOImpl::createTable] Exception happen while creating the project table.");
      }
      
      try
      {
         createStatusProject();
      }
      catch(Exception e)
      {
         //throw new AccountDataAccessException(e.getMessage());
         System.out.println("[TypeIProjectDAOImpl::createTable] Exception happen while create status project.");
      }
      return true;
   }

   /**
   * loading group object by group id
   * @exception: DataAccessException
   * @param      String gid
   * @return:    <{org.yang.customized.gtf.services.projectManager.ProjectManager.Project}>
   */
   public Project load(String id)throws ProjectDataAccessException
   {
      if (id == null)
      {
         System.out.println("Project id can't be null .");
         return null;
      }

      Project[] projects = load("id", id);

      if(null!=projects)
      {
         switch(projects.length)
         {
            case 0: return null;
            case 1: return projects[0];
         }
      }

      throw new ProjectDataAccessException();
   }

   /**
   * loading group object by group id
   * @exception: DataAccessException
   * @param      String gid
   * @return:    <{org.yang.customized.gtf.services.projectManager.ProjectManager.Project}>
   */
   public Project[] load(String[] pids)throws ProjectDataAccessException
   {
      if(null==pids)
         return null;
      if(0==pids.length)
         return new Project[0];
      String[] fields = new String[pids.length];
      for(int i=0; i<pids.length; i++)
      {
         fields[i] = "id";
      }
      return load(fields, pids, false);
   }

   /**
   * loading group object by group id
   * @exception: DataAccessException
   * @param      String gid
   * @return:    <{org.yang.customized.gtf.services.projectManager.ProjectManager.Project}>
   */
   public Project[] loadByDomain(String domain)throws ProjectDataAccessException
   {
      if (null==domain)
      {
         System.out.println("Domain can't be null .");
         return null;
      }

      return load("domain", domain);
   }

   /**
   * Loading all the group objects in this domain
   *@exception: DataAccessException
   *@param    :
   *@return   : Collection : the collection of group objects
   */
   public Project[] loadByAccountId(String aid)throws ProjectDataAccessException
   {
      if (null==aid)
      {
         System.out.println("Account id can't be null .");
         return null;
      }

      return load("accountId", aid);
   }

   /**
   * Loading all the group objects in this domain
   *@exception: DataAccessException
   *@param    :
   *@return   : Collection : the collection of group objects
   */
   public Project[] loadAll()throws ProjectDataAccessException
   {
      return load((String)null, (String)null);
   }

  /**
   * loading group object by group id
   * @exception: DataAccessException
   * @param      String gid
   * @return:    <{org.yang.customized.gtf.services.projectManager.ProjectManager.Project}>
   */
   public Project[] load(String field, String id)throws ProjectDataAccessException
   {
      if(null!=field&&null!=id)
         return load(new String[]{field}, new String[]{id});
      else if(null==field&&null==id)
         return load((String[])null, (String[])null);
      return new Project[0];
   }

   public Project[] load(String[] fields, String[] ids)throws ProjectDataAccessException
   {
      return load(fields, ids, true);
   }

   /**
   * loading group object by group id
   * @exception: DataAccessException
   * @param      String gid
   * @return:    <{org.yang.customized.gtf.services.projectManager.ProjectManager.Project}>
   */
   public Project[] load(String[] fields, String[] ids, boolean isAnd)throws ProjectDataAccessException
   {
      ArrayList projectList = new ArrayList();
      StringBuffer sql = new StringBuffer("select ")
                        .append("id")
                        .append(", type")
                        .append(", name")
                        .append(", accountId")
                        .append(", protocolNumber")
                        .append(", description")
                        .append(", serialNumber")
                        .append(", domain")
                        .append(", labHead")
                        .append(", investigator")
                        .append(", fileInDate")
                        .append(", startDate")
                        .append(", endDate")
                        //.append(", datas")
                        .append(" from ").append(tablename);

      if(null!=fields&&null!=ids&&
         fields.length==ids.length&&
         fields.length>0)
      {
         sql.append(" where");
         for(int i=0; i<fields.length; i++)
         {
            if(isAnd)
            {
               if(0==i)
                  sql.append(" ").append(fields[i]).append(" = ?");
               else
                  sql.append(" AND ").append(fields[i]).append(" = ?");
            }
            else
            {
               if(0==i)
                  sql.append(" ").append(fields[i]).append(" = ?");
               else
                  sql.append(" OR ").append(fields[i]).append(" = ?");
            }
         }
      }
      else if(null==fields&&null==ids)
         sql.append(" where ").append("id").append(" != 0");
      else
         throw new ProjectDataAccessException("Illegal query parameter.");
     ResultSet rs = null;
     Connection Conn = null;
     PreparedStatement Stmt;
     try
     {
        Conn = rdbms.getDBConnection();
        Stmt = Conn.prepareStatement(sql.toString());
     }
     catch (Exception e)
     {
        e.printStackTrace();
        throw new ProjectDataAccessException ("can not connect to database");
     }

      try
      {
         if(null!=fields&&null!=ids)
         {
            for(int i=0; i<ids.length; i++)
            {
               Stmt.setString(i+1, ids[i]);
            }
         }

         rs = Stmt.executeQuery();

         if(rs == null)
            return new Project[0];

         Project p = null;
         String id = null;
         String type = null;
         while(rs.next())
         {
            try
            {
               int i=1;

               id = rs.getString(i++);
               type = rs.getString(i++);
               p = ProjectFactory.getFactory().createProject(type);
               p.setId(id);
               p.setType(type);
               p.setName(rs.getString(i++));
               p.setAccountId(rs.getString(i++));
               p.setProtocolNumber(rs.getString(i++));
               p.setDescription(rs.getString(i++));
               p.setSerialNumber(rs.getLong(i++));
               p.setDomain(rs.getString(i++)); // it's domain
               p.setLabHead(rs.getString(i++));
               p.setInvestigator(rs.getString(i++));
               p.setFileInDate(rs.getLong(i++));
               p.setStartDate(rs.getLong(i++));
               p.setEndDate(rs.getLong(i++));
               //p.rebuildDatas(rs.getString(i++));
               
               projectList.add(p);
            }
            catch(Exception e)
            {
               throw new ProjectDataAccessException(e.getMessage());
            }
         }
      }
      catch (Exception e)
      {
         throw new ProjectDataAccessException(e.getMessage());
      }
      finally
      {
         try
         {
            if (rs!=null) rs.close();
         }
         catch (Exception e) {ExceptionBroadcast.print(e);}
            
         try
         {
            if (Stmt!=null) Stmt.close();
         }
         catch (Exception e) {ExceptionBroadcast.print(e);}
            
         try
         {
            if (Conn!=null)
            {
               Conn.close();
               if(DBConnectionCount.ConnectCount>0)
                  DBConnectionCount.ConnectCount = DBConnectionCount.ConnectCount-1;
               DBConnectionCount.printConnectionCount("TypeIProjectDAOImpl::load");   
            }   
         }
         catch (Exception e) {ExceptionBroadcast.print(e);}      	
         
         /*
         try
         {
            rs.close();	
            Stmt.close();
            Conn.close();
         }
         catch (Exception e)
         {
            ExceptionBroadcast.print(e);
         }
         */
      }

      Collections.sort(projectList, new ProjectComparator("name", true));
      return (Project[])projectList.toArray(new Project[] {});
   }

   /**
   * loading group object by group id
   * @exception: DataAccessException
   * @param      String gid
   * @return:    <{org.yang.customized.gtf.services.projectManager.ProjectManager.Project}>
   */
   public Project[] loadProjectsOrderingByAttribute(String[] conditions, boolean isAnd,String sortBy)throws ProjectDataAccessException
   {
      ArrayList array_projects = null;	
      try
      {
      	 if(null!=sortBy&&"projectId".equals(sortBy)) 
      	 {
      	    array_projects=loadWithoutSorting(conditions,isAnd);
            Collections.sort(array_projects);//, new ProjectComparator("name", true));
         }   
      }
      catch(Exception e)
      {
         e.printStackTrace();	
      }    
      return (Project[])array_projects.toArray(new Project[] {});
   }
   /**
   * loading group object by group id
   * @exception: DataAccessException
   * @param      String gid
   * @return:    <{org.yang.customized.gtf.services.projectManager.ProjectManager.Project}>
   */
   public Project[] load(String[] conditions, boolean isAnd)throws ProjectDataAccessException
   {
      ArrayList array_projects = null;	
      try
      {
      	    array_projects=loadWithoutSorting(conditions,isAnd);
            Collections.sort(array_projects, new ProjectComparator("name", true));
      }
      catch(Exception e)
      {
         e.printStackTrace();	
      }    
      return (Project[])array_projects.toArray(new Project[] {});
   }	
   
   
   
   /**
   * loading group object by group id
   * @exception: DataAccessException
   * @param      String gid
   * @return:    <{org.yang.customized.gtf.services.projectManager.ProjectManager.Project}>
   */
   private ArrayList loadWithoutSorting(String[] conditions, boolean isAnd)throws ProjectDataAccessException
   {
      ArrayList projectList = new ArrayList();
      StringBuffer sql = new StringBuffer("select ")
                        .append("id")
                        .append(", type")
                        .append(", name")
                        .append(", accountId")
                        .append(", protocolNumber")
                        .append(", description")
                        .append(", serialNumber")
                        .append(", domain")
                        .append(", labHead")
                        .append(", investigator")
                        .append(", fileInDate")
                        .append(", startDate")
                        .append(", endDate")
                        //.append(", datas")
                        .append(" from ").append(tablename);

      if(null!=conditions&&conditions.length>0)
      {
         sql.append(" where");
         for(int i=0; i<conditions.length; i++)
         {
            if(isAnd)
            {
               if(0==i)
                  sql.append(" ").append(conditions[i]);
               else
                  sql.append(" AND ").append(conditions[i]);
            }
            else
            {
               if(0==i)
                  sql.append(" ").append(conditions[i]);
               else
                  sql.append(" OR ").append(conditions[i]);
            }
         }
         sql.append(" ").append("order by name ASC");
      }
      else
         throw new ProjectDataAccessException("Illegal query parameter.");
     ResultSet rs = null;
     Connection Conn = null;
     PreparedStatement Stmt;
     try
     {
        Conn = rdbms.getDBConnection();
        Stmt = Conn.prepareStatement(sql.toString());
     }
     catch (Exception e)
     {
        //e.printStackTrace();
        throw new ProjectDataAccessException ("can not connect to database");
     }

      try
      {
         rs = Stmt.executeQuery();

         if(rs == null)
            return new ArrayList();//new Project[0];

         Project p = null;
         String id = null;
         String type = null;
         while(rs.next())
         {
            try
            {
               int i=1;
               id = rs.getString(i++);
               type = rs.getString(i++);
//System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::type:" + type + ", project" + p);

               p = ProjectFactory.getFactory().createProject(type);

//System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::type:" + type + ", project" + p);


               p.setId(id);
               p.setType(type);
               p.setName(rs.getString(i++));
               p.setAccountId(rs.getString(i++));
               p.setProtocolNumber(rs.getString(i++));
               p.setDescription(rs.getString(i++));
               p.setSerialNumber(rs.getLong(i++));
               p.setDomain(rs.getString(i++)); // it's domain
               p.setLabHead(rs.getString(i++));
               p.setInvestigator(rs.getString(i++));
               p.setFileInDate(rs.getLong(i++));
               p.setStartDate(rs.getLong(i++));
               p.setEndDate(rs.getLong(i++));
//System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::type:" + type + ", project" + p);
               //p.rebuildDatas(rs.getString(i++));
               projectList.add(p);
            }
            catch(Exception e)
            {
               throw new ProjectDataAccessException("(TypeIProjectDAOImpl,436) " + e.getMessage());
            }
         }
      }
      catch (Exception e)
      {
         throw new ProjectDataAccessException("(TypeIProjectDAOImpl,442) " + e.getMessage());
      }
      finally
      {
         try
         {
            if (rs!=null) rs.close();
         }
         catch (Exception e) {ExceptionBroadcast.print(e);}
            
         try
         {
            if (Stmt!=null) Stmt.close();
         }
         catch (Exception e) {ExceptionBroadcast.print(e);}
            
         try
         {
            if (Conn!=null)
            {
               Conn.close();
               if(DBConnectionCount.ConnectCount>0)
                  DBConnectionCount.ConnectCount = DBConnectionCount.ConnectCount-1;
               DBConnectionCount.printConnectionCount("TypeIProjectDAOImpl::loadWithoutSorting");   
            }   
         }
         catch (Exception e) {ExceptionBroadcast.print(e);}      
         /*
         try
         {
            rs.close();	
            Stmt.close();
            Conn.close();
         }
         catch (Exception e)
         {
            ExceptionBroadcast.print(e);
         }
         */
      }
      return projectList;
   }

   /**
   * Insert new group data into data base.
   *
   * @exception: DataAccessException
   * @param:     Group g
   * @return:    boolean
   */
   public boolean insert(Project p)throws ProjectDataAccessException
   {
      if (p == null)
      {
         System.out.println("Project object is null");
         return false;
      }

      if(null!=load(p.getId()))
         return false;

      long sn = 0;

      if(!"0".equals(p.getId()))
        sn = getNextSerialNumber();
      else
        sn = p.getSerialNumber();

      StringBuffer sql = new StringBuffer("insert into ").append(tablename).append("(")
                        .append("type")
                        .append(", name")
                        .append(", accountId")
                        .append(", protocolNumber")
                        .append(", description")
                        .append(", serialNumber")
                        .append(", domain")
                        .append(", labHead")
                        .append(", investigator")
                        .append(", fileInDate")
                        .append(", startDate")
                        .append(", endDate")
                        //.append(", datas")
                        .append(", id")
                        .append(") values (?,?,?,?,?,?,?,?,?,?,?,?,?)");

      //System.out.println("Inserting into table");

      Connection Conn;
      PreparedStatement Stmt;
      try
      {
         Conn = rdbms.getDBConnection();
         Stmt = Conn.prepareStatement(sql.toString());
      }
      catch (Exception e)
      {
         throw new ProjectDataAccessException ("Can not connect to database.");
      }

      //System.out.println("Connected");

      try
      {
         int i = 1;
         Stmt.setString(i++, p.getType());
         Stmt.setString(i++, p.getName());
         Stmt.setString(i++, p.getAccountId());
         Stmt.setString(i++, p.getProtocolNumber());
         Stmt.setString(i++, p.getDescription());
         Stmt.setLong(i++, sn);
         Stmt.setString(i++, p.getDomain());
         Stmt.setString(i++, p.getLabHead());
         Stmt.setString(i++, p.getInvestigator());
         Stmt.setLong(i++, p.getFileInDate());
         Stmt.setLong(i++, p.getStartDate());
         Stmt.setLong(i++, p.getEndDate());
         //Stmt.setString(i++, p.datasString());
         Stmt.setString(i++, p.getId());
         Stmt.executeUpdate();
         ///try { Conn.commit(); } catch (Exception e) {};
        ///try {Conn.setAutoCommit(false);} catch(Exception e){};
      }
      catch (Exception e)
      {
         throw new ProjectDataAccessException(e.getMessage());
      }
      finally
      { /*
         try
         {
            Stmt.close();
            Conn.close();
         }
         catch (Exception e)
         {
            ExceptionBroadcast.print(e);
         }
        */
            
         try
         {
            if (Stmt!=null) Stmt.close();
         }
         catch (Exception e) {ExceptionBroadcast.print(e);}
            
         try
         {
            if (Conn!=null)
            {
               Conn.close();
               if(DBConnectionCount.ConnectCount>0)
                  DBConnectionCount.ConnectCount = DBConnectionCount.ConnectCount-1;
               DBConnectionCount.printConnectionCount("TypeIProjectDAOImpl::insert");   
            }   
         }
         catch (Exception e) {ExceptionBroadcast.print(e);}               
      }
      return true;
   }

   /**
   * Update a group data from data base
   *@exception: DataAccessException
   *@param    : Group g
   *@return:    boolean
   */
   public boolean update(Project p)throws ProjectDataAccessException
   {
      return update(p, true);
   }

   /**
   * Update a group data from data base
   *@exception: DataAccessException
   *@param    : Group g
   *@return:    boolean
   */
   public boolean update(Project p, boolean updateSN)throws ProjectDataAccessException
   {
      if (null==p)
      {
         System.out.println("Project object is null");
         return false;
      }

      if(null==load(p.getId()))
         return false;

      Project[] projects = new Project[1];
      projects[0] = p;
      return update(projects, updateSN);
   }

   /**
   * Update a group data from data base
   *@exception: DataAccessException
   *@param    : Group g
   *@return:    boolean
   */
   public boolean update(Project[] projects)throws ProjectDataAccessException
   {
      return update(projects, true);
   }

   /**
   * Update a group data from data base
   *@exception: DataAccessException
   *@param    : Group g
   *@return:    boolean
   */
   public boolean update(Project[] projects,  boolean updateSN)throws ProjectDataAccessException
   {
      if (null==projects||0==projects.length)
      {
         System.out.println("No project object need to be updated.");
         return false;
      }

      StringBuffer sql = new StringBuffer("update ").append(tablename).append(" set ")
                        .append("type = ?")
                        .append(", name = ?")
                        .append(", accountId = ?")
                        .append(", protocolNumber = ?")
                        .append(", description = ?")
                        .append(", domain = ?")
                        .append(", labHead = ?")
                        .append(", investigator = ?")
                        .append(", fileInDate = ?")
                        .append(", startDate = ?")
                        .append(", endDate =? ");
                        //.append(", datas =? ");
      if(updateSN)
         sql.append(", serialNumber = ?");
      sql.append(" where id = ?");

      System.out.println("Updating project table...");

      Connection Conn;
      PreparedStatement Stmt;
      try
      {
         Conn = rdbms.getDBConnection();
         Stmt = Conn.prepareStatement(sql.toString());
      }
      catch (Exception e)
      {
         ExceptionBroadcast.print(e);
         throw new ProjectDataAccessException ("Can not connect to database");
      }

      System.out.println("Connected");

      try
      {
         for(int j=0; j<projects.length; j++)
         {
            if(null==load(projects[j].getId()))
               continue;
            int i = 1;
            Stmt.setString(i++, projects[j].getType());
            Stmt.setString(i++, projects[j].getName());
            Stmt.setString(i++, projects[j].getAccountId());
            Stmt.setString(i++, projects[j].getProtocolNumber());
            Stmt.setString(i++, projects[j].getDescription());
            Stmt.setString(i++, projects[j].getDomain());
            Stmt.setString(i++, projects[j].getLabHead());
            Stmt.setString(i++, projects[j].getInvestigator());
            Stmt.setLong(i++, projects[j].getFileInDate());
            Stmt.setLong(i++, projects[j].getStartDate());
            Stmt.setLong(i++, projects[j].getEndDate());
            //Stmt.setString(i++, projects[j].datasString());
            if(updateSN)
               Stmt.setLong(i++, projects[j].getSerialNumber());
            Stmt.setString(i++, projects[j].getId());
            Stmt.executeUpdate();
         }
         try { Conn.commit(); } catch (Exception e) {};
      }
      catch (Exception e)
      {
         throw new ProjectDataAccessException(e.getMessage());
      }
      finally
      {
         try
         {
            if (Stmt!=null) Stmt.close();
         }
         catch (Exception e) {ExceptionBroadcast.print(e);}
            
         try
         {
            if (Conn!=null)
            {
               Conn.close();
               if(DBConnectionCount.ConnectCount>0)
                  DBConnectionCount.ConnectCount = DBConnectionCount.ConnectCount-1;
               DBConnectionCount.printConnectionCount("TypeIProjectDAOImpl:: update");   
            }   
         }
         catch (Exception e) {ExceptionBroadcast.print(e);}              	
      	/*
         try
         {
            Stmt.close();
             Conn.close();
         }
         catch (Exception e)
         {
            ExceptionBroadcast.print(e);
         }
        */ 
      }
      return true;
   }

   public boolean deleteByDomain(String domain)throws ProjectDataAccessException
   {
      try
      {
         if (null==domain)
         {
            System.out.println("Domain can't be null .");
            return false;
         }

         return delete("domain", domain);
      }
      catch(Exception e)
      {
         throw new ProjectDataAccessException(e.getMessage());
      }
   }

   public boolean deleteByAccountId(String aid) throws ProjectDataAccessException
   {
      try
      {
         if (null==aid)
         {
            System.out.println("Account id can't be null.");
            return false;
         }

         return delete("accountId", aid);
      }
      catch(Exception e)
      {
         throw new ProjectDataAccessException(e.getMessage());
      }
   }

   private boolean createStatusProject() throws ProjectDataAccessException
   {
      try
      {
         Project p = ProjectFactory.getFactory().createProject("GT");
         p.setId("0");
         p.setType("n/a");
         p.setName("System State");
         p.setAccountId("n/a");
         p.setDescription("This record keep project table state.");
         p.setSerialNumber(0);
         p.setDomain("System"); // it's domain
         p.setLabHead("n/a");
         p.setInvestigator("n/a");
         p.setFileInDate(0);
         p.setStartDate(0);
         p.setEndDate(0);

         return insert(p);
      }
      catch(Exception e)
      {
         throw new ProjectDataAccessException();
      }
   }

   private long getNextSerialNumber() throws ProjectDataAccessException
   {
      long nextNumber = 0;
      StringBuffer sql = new StringBuffer("select serialNumber from ")
                         .append(tablename).append(" where id = ?");

      StringBuffer sql2 = new StringBuffer("update ")
                          .append(tablename).append(" set serialNumber = ? where id = ?");
     ResultSet rs = null;
     Connection Conn = null;
     PreparedStatement Stmt, Stmt2;
     try
     {
        Conn = rdbms.getDBConnection();
        Stmt = Conn.prepareStatement(sql.toString());
        Stmt2 = Conn.prepareStatement(sql2.toString());
     }
     catch (Exception e)
     {
        //e.printStackTrace();
        throw new ProjectDataAccessException ("can not connect to database");
     }

     try
     {
        Stmt.setString(1, "0");
        rs = Stmt.executeQuery();
        if(null==rs||!rs.next())
        {
           throw new ProjectDataAccessException("Unable to access table state!");
        }
        else
        {
           nextNumber = rs.getLong(1);
        }

        Stmt2.setLong(1, nextNumber+1);
        Stmt2.setString(2, "0");
        Stmt2.executeUpdate();
        ///try { Conn.commit(); } catch (Exception e) {ExceptionBroadcast.print(e);};
      }
      catch (Exception e)
      {
         throw new ProjectDataAccessException(e.getMessage());
      }
      finally
      {
      	/*
         try
         {
            Stmt.close();
            Stmt2.close();
            Conn.close();
            rs.close();
         }
         catch (Exception e)
         {
            ExceptionBroadcast.print(e);
         }
         */
         try
         {
            if (rs!=null) rs.close();
         }
         catch (Exception e) {ExceptionBroadcast.print(e);}
            
         try
         {
            if (Stmt!=null) Stmt.close();
         }
         catch (Exception e) {ExceptionBroadcast.print(e);}
      
         try
         {
            if (Stmt2!=null) Stmt2.close();
         }
         catch (Exception e) {ExceptionBroadcast.print(e);}
                     
         try
         {
            if (Conn!=null)
            {
               Conn.close();
               if(DBConnectionCount.ConnectCount>0)
                  DBConnectionCount.ConnectCount = DBConnectionCount.ConnectCount-1;
               DBConnectionCount.printConnectionCount("TypeIProjectDAOImpl::createStatusProject");   
            }   
         }
         catch (Exception e) {ExceptionBroadcast.print(e);}              
         
      }
      return nextNumber;
   }

  /**
   *   test stub
   */
   public static void main(String[] args)
   {
      try
      {
      RDBMS rdbms = RDBMSFactory.getInstance().getRDBMS("jdbc:mysql://localhost:3306/content",
                                                        "jumbo",
                                                        "02200220",
                                                        "org.gjt.mm.mysql.Driver");

      TypeIProjectDAOImpl dao = new TypeIProjectDAOImpl(org.yang.customized.gtf.services.dataAccess.DAOFactory.WAITING,
                                                        rdbms);
      // Create table
      dao.createTable();

      // Insert Project
      Project p = ProjectFactory.getFactory().createProject("GT");
      p.setId("132432432423432");
      p.setName("My First Project");
      p.setAccountId("34rffcw33445");
      p.setDescription("It's my First Project.");
      p.setDomain("test_domain"); // it's domain

      Project p1 = ProjectFactory.getFactory().createProject("GT");
      p1.setId("132432432423434");
      p1.setName("My Second Project");
      p1.setAccountId("34rffcw33445");
      p1.setDescription("It's my First Project.");
      p1.setDomain("test_domain"); // it's domain
      try
      {
         dao.insert(p);
         dao.insert(p1);
         p.setInvestigator("steven");
         dao.update(p);
      } catch(Exception e) { e.printStackTrace(); }

      // Load Project
      Project[] pl = null;
      try
      {
         //pl = dao.loadByDomain("test_domain");
         //pl = dao.loadAll();
         String[] fields = {"id"};
         String[] vals = {"132432432423434"};
         pl = dao.load(fields, vals, false);
      } catch(Exception e) { e.printStackTrace(); }

      for(int i=0; i<pl.length; i++)
      {
         System.out.println("==========================================");
         System.out.println(pl[i].getId());
         System.out.println(pl[i].getName());
         System.out.println(pl[i].getAccountId());
         System.out.println(pl[i].getDescription());
         System.out.println(pl[i].getSerialNumber()+"");
         System.out.println(pl[i].getDomain());
      }

      System.out.println("Done!");
      }
      catch(Exception e)
      {
         e.printStackTrace();
      }
   }
}