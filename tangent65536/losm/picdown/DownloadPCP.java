/**
 * Copyright (c) 2016-2017 Tangent65536 on GitHub.
 * You're allowed to use this program and source code under Apache License 2.0 overriden by the terms below:
 *   0. I may to change the licensing of this program without any noticing.
 *   1. Any commercial uses are forbidden.
 *   2. Users are NOT allowed to abuse the functions in order to overflow any web service.
 *   3. This project is provided as is without any guarantees or warranty. USE IT ON YOUR OWN RISK!
 */

package tangent65536.losm.picdown;

import tangent65536.losm.picdown.util.CommonIntervals;
import tangent65536.losm.picdown.util.DateTime;
import tangent65536.losm.picdown.util.DownloaderBase;

public class DownloadPCP extends DownloaderBase
{
	public DownloadPCP()
	{
		super("Daily Accmu. Precip.", "DownloadPics/Precip", "http://www.cwb.gov.tw/V7/observe/rainfall/Data/hkc%dd%%hh%%n%.jpg", CommonIntervals.THIRTY_MINUTES.interval);
	}
	
	@Override
	protected String getURL(final DateTime time)
	{
		return time.encode(this.urlBase).replaceAll("%n%", String.format("%01d", time.minute / 10));
	}

	protected DateTime fixStart(DateTime start)
	{
		return CommonIntervals.THIRTY_MINUTES.alignToInterval(start);
	}
}
