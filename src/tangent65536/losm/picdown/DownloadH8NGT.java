/**
 * Copyright (c) 2016-2017 Tangent65536 on GitHub \w Zhen-Yan Zhang
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

public class DownloadH8NGT extends DownloaderBase
{
	protected DownloadH8NGT()
	{
		super("Himawari-8 Night Microphysics RCB", "DownloadPics/Himawari8/NMRCB", "http://www.data.jma.go.jp/mscweb/data/himawari/img/se2/se2_ngt_%hh%%nn%.jpg", CommonIntervals.TEN_MINUTES.interval);
	}

	@Override
	protected DateTime fixStart(DateTime start)
	{
		return CommonIntervals.TEN_MINUTES.alignToInterval(start);
	}
}
