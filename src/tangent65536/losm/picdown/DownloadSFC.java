package tangent65536.losm.picdown;

import tangent65536.losm.picdown.util.CommonIntervals;
import tangent65536.losm.picdown.util.DateTime;
import tangent65536.losm.picdown.util.DownloaderBase;

public class DownloadSFC extends DownloaderBase
{
	public DownloadSFC()
	{
		super("Surface Analysis", "DownloadPics/SFC", "http://cwb.gov.tw/V7/forecast/fcst/Data/%yyyy%-%mm%%dd%-%hh%%nn%_SFCcombo.jpg", CommonIntervals.SIX_HOUR.interval);
	}

	@Override
	protected DateTime fixStart(DateTime start)
	{
		return CommonIntervals.SIX_HOUR.alignToInterval(start);
	}
}
