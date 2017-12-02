package org.yang.web.services.alertService;
import java.io.Serializable;

public class Alert implements Serializable
{
	static final long serialVersionUID = 4711996382979764969L;

	/**
	 * Get the key of the message.
	 *
	 * return 	key of the message
	 */
   	private String key      = "";
   	public String getKey() { return key; }
   	public void setKey(String key)	{ this.key = key;	}

	/**
	 * Get the type of the message.
	 *
	 * return 	"Information" when type is 0.
	 *		"Warning" when type is 1.
	 *		"Error"	when type is 2.
	 */   	
    private String type     = "";
   	public String getType()	{ return type; }
   	public void setType(String type) { this.type = type; }
   	
	/**
	 * Get the code number of the message.
	 *
	 * return 	code number of the message
	 */   	
   	private String code     = "";
   	public String getCode() { return code;	}
   	public void setCode(String code) { this.code = code; }
   	
   	/**
	 * Get the source id of the message.
	 *
	 * return 	source id of the message
	 */   	
   	private String sourceID = "";
   	public String getSourceID() { return sourceID; }
   	public void setSourceID(String sourceID) { this.sourceID = sourceID;	}

	/**
	 * Get the year of the message.
	 *
	 * return 	year of the message
	 */   	
   	private int year        = -1;
   	public int getYear() { return year; }
   	public void setYear(int aYear) { this.year = year; }

	/**
	 * Get the month of the message.
	 *
	 * return 	month of the message
	 */   	
	private int month       = -1;
	public int getMonth() { return month; }
	public void setMonth(int aMonth) { this.month = month; }

	/**
	 * Get the day of the message.
	 *
	 * return 	day of the message
	 */	
	private int day         = -1;
	public int getDay() { return day;	}
	public void setDay(int day) { this.day = day; }

	/**
	 * Get time of the message.
	 *
	 * return 	time of the message
	 */	
	private int time        = -1;
	public int getTime() { return time; }
	public void setTime(int time) { this.time = time; }

	/**
	 * Get timezone of the message.
	 *
	 * return 	timezone of the message
	 */	
	private String timeZone = "";
	public String getTimeZone() { return timeZone; }
	public void setTimeZone(String timeZone) { timeZone = timeZone; }
	
	/**
	 * Get the actual message of the message.
	 *
	 * return 	message
	 */	
	private String message  = "";
	public String getMessage() {  return message; }
	public void setMessage(String message) { message = message; }
		
	/**
	 * Get the user id who gererate this message.
	 *
	 * return 	user id
	 */	
	private String userID   = "";
	public String getUserID() { return userID; }
	public void setUserID(String userID) { userID = userID;}
}