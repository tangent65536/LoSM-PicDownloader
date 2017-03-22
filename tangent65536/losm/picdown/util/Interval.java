/**
 * Copyright (c) 2016-2017 Tangent65536 on GitHub.
 * You're allowed to use this program and source code under Apache License 2.0 overriden by the terms below:
 *   0. I may to change the licensing of this program without any noticing.
 *   1. Any commercial uses are forbidden.
 *   2. Users are NOT allowed to abuse the functions in order to overflow any web service.
 *   3. This project is provided as is without any guarantees or warranty. USE IT ON YOUR OWN RISK!
 */

package tangent65536.losm.picdown.util;

public class Interval
{
	public final int year;
	public final int month;
	public final int day;
	public final int hour;
	public final int minute;
	
	public Interval(int _year, int _month, int _day, int _hour, int _min)
	{
		this.year = _year;
		this.month = _month;
		this.day = _day;
		this.hour = _hour;
		this.minute = _min;
	}
}
