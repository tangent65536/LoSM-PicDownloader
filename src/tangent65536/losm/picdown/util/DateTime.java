package tangent65536.losm.picdown.util;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DateTime
{
	private static final Logger logger = LogManager.getLogger();

	public final int year;
	public final int month;
	public final int day;
	public final int hour;
	public final int minute;
	
	public final boolean isLeap;
	private final int m_day;
	
	private final long numProxy;
	
	private static final int[] m_days = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	
	public DateTime(int _year, int _month, int _day, int _hour, int _min) throws InvalidDayTimeException
	{
		this.year = _year;
		if(this.year > 9999 || this.year < 0)
		{
			throw new InvalidDayTimeException("\"Year\" should be set between 0 and 9999.");
		}
		this.isLeap = (this.year % 4 == 0 && this.year % 100 != 0) || this.year % 400 == 0;
		
		this.month = _month;
		if(this.month > 12 || this.month < 1)
		{
			throw new InvalidDayTimeException("\"Month\" should be set between 1 and 12.");
		}
		
		this.day = _day;
		
		int _m_day = m_days[this.month - 1];
		if(isLeap && this.month == 2)
		{
			_m_day += 1;
		}
		this.m_day = _m_day;
		
		if(this.day < 1 || this.day > this.m_day)
		{
			throw new InvalidDayTimeException("\"Day\" should be set between 1 and " + this.m_day + ".");
		}
		
		this.hour = _hour;
		if(this.hour < 0 || this.hour > 23)
		{
			throw new InvalidDayTimeException("\"Hour\" should be set between 0 and 23.");
		}
		
		this.minute = _min;
		if(this.minute < 0 || this.minute > 59)
		{
			throw new InvalidDayTimeException("\"Minute\" should be set between 0 and 59.");
		}
		
		this.numProxy = (long)this.year * 100000000L + (long)this.month * 1000000L + this.day * 10000 + this.hour * 100 + this.minute;
	}
	
	public DateTime(int[] arrayData) throws InvalidDayTimeException
	{
		this(arrayData[0], arrayData[1], arrayData[2], arrayData[3], arrayData[4]);
	}
	
	public String encode(final String baseStr)
	{
		String superStr = baseStr;
		superStr = superStr.replaceAll("%yyyy%", String.format("%04d", this.year));
		superStr = superStr.replaceAll("%mm%", String.format("%02d", this.month));
		superStr = superStr.replaceAll("%dd%", String.format("%02d", this.day));
		superStr = superStr.replaceAll("%hh%", String.format("%02d", this.hour));
		superStr = superStr.replaceAll("%nn%", String.format("%02d", this.minute));
		return superStr;
	}
	
	public boolean isPriorOrSame(final DateTime priorOrSame)
	{
		return this.numProxy >= priorOrSame.numProxy;
	}
	
	/**
	 * Limited to 1 day as max.
	 */
	public DateTime getNext(final Interval interval)
	{
		if((interval.year > 0 || interval.month > 0 || interval.day > 1) || (interval.day > 0 && (interval.hour > 0 || interval.minute > 0)))
		{
			logger.error("This feature is not implemented yet. Null returned. :(");
			return null;
		}
		else
		{
			int addMin = this.minute + interval.minute;
			int addHour = this.hour + interval.hour;
			int addDay = this.day + interval.day;
			int addMonth = this.month;
			int addYear = this.year;
			
			if(addMin > 59)
			{
				addHour += (addMin / 60);
				addMin = addMin % 60;
			}
			if(addHour > 23)
			{
				addDay += (addHour / 24);
				addHour = addHour % 24;
			}
			if(addDay > this.m_day)
			{
				addMonth += 1;
				addDay -= this.m_day;
			}
			if(addMonth > 12)
			{
				addYear += 1;
				addMonth -= 12;
			}
			
			try
			{
				return new DateTime(addYear, addMonth, addDay, addHour, addMin);
			}
			catch(Exception e)
			{
				e.printStackTrace();
				logger.fatal("Internal Error. This is a bug, please report it. Thanks!");
				return null;
			}
		}
	}
	
	@Override
	protected DateTime clone()
	{
		try
		{
			return new DateTime(this.year, this.month, this.day, this.hour, this.minute);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			logger.fatal("Internal Error. This is a bug, please report it. Thanks!");
			return null;
		}
	}
	
	public static ArrayList<DateTime> getDates(final DateTime start, final DateTime end, final Interval interval)
	{
		ArrayList<DateTime> out = new ArrayList<DateTime>();
		DateTime next = start.clone();
		do
		{
			out.add(next.clone());
			next = next.getNext(interval);
		}
		while(end.isPriorOrSame(next));
		return out;
	}
	
	public static class InvalidDayTimeException extends Exception
	{
		private static final long serialVersionUID = 63556L;
		
		private InvalidDayTimeException(String description)
		{
			super(description);
		}
	}
}
