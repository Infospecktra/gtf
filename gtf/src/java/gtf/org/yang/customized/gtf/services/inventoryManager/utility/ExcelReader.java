              /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.yang.customized.gtf.services.inventoryManager.utility;
import jxl.*;
import java.util.Date;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.StringTokenizer;
import org.yang.customized.gtf.services.dataAccess.Storage;
import org.yang.services.dataAccess.genericDAO.StorableList;
/**
 *
 * @author Celina
 */
public class ExcelReader implements Serializable{
    final static long serialVersionUID = 4711296522939761043L;
    
    public String workingDirectory = "";
    public String pathSign = "";
    public String filePathStub = "";
      
    public  ExcelReader(){}
    public  ExcelReader(String excelFilePath)
    {   
        /*
    	if("remote".equals(VariableArchives.workingLocation))
    	{
    	   //System.out.println("[ExcelReader::]---------------------->VariableArchives.workingLocation="+VariableArchives.workingLocation);
    	   workingDirectory = VariableArchives.PATH_REMOTE;
    	   pathSign = "/";
    	   filePathStub = "/";
    	}
    	else
        {
    	   workingDirectory = VariableArchives.PATH_LOCAL;
    	   pathSign = "\\";
    	   filePathStub = "";
    	}
        */
        String userHome=System.getProperty("user.home");
        StringBuffer stringBf_workingDirectory =new StringBuffer(userHome);
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>System.getProperty-user.home="+userHome);
        
        if(userHome.indexOf(":")>-1)
        {    
           stringBf_workingDirectory.append('\\')
                                    .append("gtf_storage_xls").append('\\'); 
    	   workingDirectory =  stringBf_workingDirectory.toString();
    	   pathSign = "\\";
    	   filePathStub = "";
        
        }else{
           stringBf_workingDirectory.append('/')
                                    .append("gtf_storage_xls").append('/'); 
    	   workingDirectory =  stringBf_workingDirectory.toString();
    	   pathSign = "/";
    	   filePathStub = "/";
            
        }
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>workingDirectory="+workingDirectory);
        
    	convertFilePath(excelFilePath);  
    }

    private String inputFilePath= "";
    private String outputDataFileName = "";
    public String getOutputDataFileName(){return outputDataFileName;}
    
    private void convertFilePath(String inputExcelFileName)
    {
       StringBuffer inputFile_sbuffer = new StringBuffer("");	
       StringTokenizer tokenizer = new StringTokenizer(inputExcelFileName , pathSign);        
       while(tokenizer.hasMoreTokens())
       {
          outputDataFileName = tokenizer.nextToken();
          inputFile_sbuffer.append(outputDataFileName).append(pathSign);     	
       }	      	       
       inputFilePath = filePathStub + (inputFile_sbuffer.substring(0,inputFile_sbuffer.length()-1).toString());
       //System.out.println("[ExcelReader::getFilePath]------> inputFilePath="+inputFilePath);
       //System.out.println("[ExcelReader::getFilePath]------> outputDataFileName="+outputDataFileName);
       workingDirectory = inputFilePath.replaceAll(outputDataFileName, "");
       //System.out.println("[ExcelReader::getFilePath]------> workingDirectory="+workingDirectory);
       tokenizer = new StringTokenizer(outputDataFileName,".");
       outputDataFileName = tokenizer.nextToken();
       //System.out.println("[ExcelReader::getFilePath]------> outputDataFileName="+outputDataFileName);
    }
 
    public StorableList readExcelFile(StorableList storages)
    {
      Workbook workbook = null;
      Sheet sheet = null;
      File contentFile = new File(inputFilePath);
      FileInputStream fis = null;
      BufferedInputStream bis = null;
      ByteArrayInputStream is = null;
      InputStreamReader inputStreamReader = null;
      try {
         
         StringBuffer strBuffer = new StringBuffer();
         System.out.println("[ExcelReader::readExcelFile]----->inputFilePath="+   inputFilePath);
         workbook = Workbook.getWorkbook(new File(inputFilePath));
         sheet = workbook.getSheet(0);
         int rowNumber = sheet.getRows();
         //System.out.println("[ExcelReader::readExcelFile]---------------------------->rowNumber="+rowNumber);
         String l_cell = "";
         double n_cell = -9.9;
         Date d_cell = null;
         storages = new StorableList();  
         for(int i=1;i<rowNumber;i++)
         {  
            //Cell ,Investigator,Lab,Project Code,Parental Line,Medium,Drug Selection,Location,Box#,row/column,Freezing Date,Comment,
            int columnNumber =  sheet.getColumns();
         //System.out.println("[ExcelReader::readExcelFile]---------------------->columnNumber="+columnNumber);
            Storage storage = new Storage();	
            for(int j=0;j<columnNumber;j++)
            {
               Cell cell = sheet.getCell(j,i);
               LabelCell lCell = null;
               if(CellType.LABEL.equals(cell.getType()))//Cell ,Investigator,Lab,Parental Line,Medium,Drug Selection,Location,row/column,Comment,
               {
                   lCell = (LabelCell)cell;
                   l_cell = lCell.getString().trim();
                   //System.out.println("[ExcelReader::readExcelFile]--("+j+","+i+")--->l_cell="+l_cell);
                   strBuffer.append(l_cell).append(",");
                   if(j==0)
                   {
                     storage.setCell(l_cell);
                     //System.out.println("[ExcelReader::readExcelFile]--("+j+") storage.getCell="+storage.getCell());
                   }  
                   else if(j==1)
                     storage.setInvestigator(l_cell.toUpperCase());   
                   else if(j==2)
                     storage.setLabName(l_cell.toUpperCase()); 
                   else if(j==3)
                      storage.setProjectCode(l_cell); 	
                   else if(j==4)  
                     storage.setParentalLine(l_cell);
                   else if(j==5)
                     storage.setMedium(l_cell);
                   else if(j==6)
                     storage.setDrugSelection(l_cell);
                   else if(j==7)  
                     storage.setLocation(l_cell);
                   else if(j==9)
                     storage.setRowColumn(l_cell);
                   else if(j==11)    
                     storage.setComment(l_cell);
               }
               else if(CellType.NUMBER.equals(cell.getType()))//Project Code,Box#,Freezing Date
               {
                   NumberCell nCell = (NumberCell)cell;
                   n_cell = nCell.getValue();
                   //NumberFormat nFormate = nCell.getNumberFormat();
                   //System.out.println("[ExcelReader::readExcelFile]--("+j+","+i+")--->n_cell="+n_cell);
                   strBuffer.append(n_cell+"").append(",");
                   if(j==8)
                     storage.setBoxNumber(new Integer((n_cell+"").substring(0,(n_cell+"").length()-2)).intValue());
                   else if(j==3)
                   {
                   	
                     String[] str_cell = Utility.parsingDateString(".",n_cell+""); 	
                     String projectCode = "";
                     if(str_cell!=null&&str_cell.length!=0)
                        projectCode=str_cell[0]; // trim the data after "."
                     //System.out.println("[ExcelReader::readExcelFile]--projectCode="+projectCode);	
                     storage.setProjectCode(projectCode); 
                   } 
                   else if(j==10) //Freezing Date
                   {
                     String[] dateArray = new String[3];                    	
                     String[] str_cell = Utility.parsingDateString(".",n_cell+""); 	
                     String freezingDate = "";
                     
                     if(str_cell!=null&&str_cell.length!=0)
                        freezingDate=str_cell[0]; // trim the data after "."
                     //System.out.println("[ExcelReader::readExcelFile]--freezingDate="+freezingDate);
                       
                     if(freezingDate.indexOf("/")!=-1)
                     {
                        dateArray = Utility.parsingDateString("/",freezingDate);
                     }		
                     else if(freezingDate.indexOf("-")!=-1)
                     {
                        dateArray = Utility.parsingDateString("-",freezingDate);
                     }		
                     else
                     {  
                     	dateArray[2] = freezingDate;
                     }	
                     
                     if(dateArray[0]==null||"".equals(dateArray[0]))
                        dateArray[0] = "01";
                     if(dateArray[1]==null||"".equals(dateArray[1]))
                        dateArray[1] = "01";                     
                     storage.setFreezingDate(Utility.stringDateConvertToDate(dateArray[2],dateArray[0],dateArray[1]));
                   }   
               }
               else if(CellType.DATE.equals(cell.getType()))
               {
                   DateCell nCell = (DateCell)cell;
                   d_cell = nCell.getDate();
                   
                   //NumberFormat nFormate = nCell.getNumberFormat();
                   //System.out.println("[ExcelReader::readExcelFile]--("+j+","+i+")--->d_cell="+d_cell);
                   //System.out.println("[ExcelReader::readExcelFile]--d_cell.toString()--->"+d_cell.toString());

                   if(j==3)
                   {
                      storage.setProjectCode(d_cell.toString()); 	
                   }	
                   else if(j==10) //Freezing Date
                   {
                      storage.setFreezingDate(new java.sql.Date(d_cell.getTime()));	
                   }	
                   
                   strBuffer.append(d_cell).append(",");

               }
               else //if(CellType.DATE.equals(cell.getType()))
               {
                   String str_content = cell.getContents();
                   //System.out.println("[ExcelReader::readExcelFile]--("+j+","+i+")--->str_content="+str_content);
                   if(null==str_content||"".equals(str_content))
                      strBuffer.append(str_content).append(",");
               }

            }//the end of columnNumber
            if(!"".equals(storage.getCell()))
               storages.add(storage);
            //System.out.println("[ExcelReader::readExcelFile]////----->strBuffer.toString()="+strBuffer.toString());
            //System.out.println("[ExcelReader::readExcelFile]////--------------------->storages.size()="+storages.size());
            totalDataSize = storages.size();
            strBuffer.append("\r");
            is = new ByteArrayInputStream(strBuffer.toString().getBytes("ISO-8859-1"));

            inputStreamReader =
                  new InputStreamReader(is,"ISO-8859-1");
            byte[] buffer = new byte[10240];
            int chunksize = 0;
            ///fw.write(strBuffer.toString());
            strBuffer = new StringBuffer();
           
         }//the end of rowNumber
      }
      catch(Exception e)
      {
         e.printStackTrace();
      }
      finally
      {
         try
         {
            inputStreamReader.close();
            //fw.close();
         }
         catch(Exception e)
         {
            e.printStackTrace();
         }
      }
      return storages;
    }   
    
    private int totalDataSize = 0;
    public int getTotalDataSize(){return totalDataSize;}  
    
    public static void main(String[] args)
    {
       try
       {
          ExcelReader reader = new ExcelReader();
          //reader.setReadFileName("LNTANK.xls");
       }
       catch(Exception e)
       {
          e.printStackTrace();
       }
    }
}
