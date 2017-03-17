package tangent65536.losm.picdown;

import tangent65536.losm.picdown.util.CommonIntervals;
import tangent65536.losm.picdown.util.DateTime;
import tangent65536.losm.picdown.util.DownloaderBase;

public class DownloadLST extends DownloaderBase
{
	public DownloadLST()
	{
		super("Land Surface Temperature", "DownloadPics/Temperature", "http://www.cwb.gov.tw/V7/observe/temperature/Data/%yyyy%-%mm%-%dd%_%hh%%nn%.GTP.jpg", CommonIntervals.ONE_HOUR.interval);
	}

	@Override
	protected DateTime fixStart(DateTime start)
	{
		return CommonIntervals.ONE_HOUR.alignToInterval(start);
	}
}
