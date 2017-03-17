/**
 * Copyright (c) 2016-2017 Tangent65536 on GitHub.
 * You're allowed to use this program and source code under Apache License 2.0 overriden by the terms below:
 *   0. I may to change the licensing of this program without any noticing.
 *   1. Any commercial uses are forbidden.
 *   2. Users are NOT allowed to abuse the functions in order to overflow any web service.
 *   3. This project is provided as is without any guarantees or warranty. USE IT ON YOUR OWN RISK!
 */

package tangent65536.losm.picdown.util;

import tangent65536.losm.picdown.util.DateTime;

public enum CommonIntervals
{
	TEN_MINUTES(0, 0, 0, 0, 10)
	{
		public DateTime alignToInterval(DateTime shifted)
		{
			int left = shifted.minute % this.interval.minute;
			return left != 0 ? shifted.getNext(new Interval(0, 0, 0, 0, this.interval.minute - left)) : shifted;
		}
	},
	THIRTY_MINUTES(0, 0, 0, 0, 30)
	{
		public DateTime alignToInterval(DateTime shifted)
		{
			int left = shifted.minute % this.interval.minute;
			return left != 0 ? shifted.getNext(new Interval(0, 0, 0, 0, this.interval.minute - left)) : shifted;
		}
	},
	ONE_HOUR(0, 0, 0, 1, 0)
	{
		public DateTime alignToInterval(DateTime shifted)
		{
			int left = shifted.hour % this.interval.hour;
			return left != 0 ? shifted.getNext(new Interval(0, 0, 0, this.interval.hour - left - 1, 60 - shifted.minute)) : shifted;
		}
	},
	SIX_HOUR(0, 0, 0, 6, 0)
	{
		public DateTime alignToInterval(DateTime shifted)
		{
			int left = shifted.hour % this.interval.hour;
			return left != 0 ? shifted.getNext(new Interval(0, 0, 0, this.interval.hour - left - 1, 60 - shifted.minute)) : shifted;
		}
	},
	ONE_DAY(0, 0, 1, 0, 0)
	{
		public DateTime alignToInterval(DateTime shifted)
		{
			return (shifted.hour != 0 || shifted.minute != 0) ? shifted.getNext(new Interval(0, 0, 0, 24 - shifted.hour - 1, 60 - shifted.minute)) : shifted;
		}
	};
	
	public final Interval interval;
	
	private CommonIntervals(int y, int mo, int d, int h, int mi)
	{
		this.interval = new Interval(y, mo, d, h, mi);
	}
	
	public abstract DateTime alignToInterval(DateTime shifted);
}
