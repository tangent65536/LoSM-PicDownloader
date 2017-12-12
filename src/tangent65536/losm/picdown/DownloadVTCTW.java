package tangent65536.losm.picdown;

import tangent65536.losm.picdown.util.CommonIntervals;
import tangent65536.losm.picdown.util.DateTime;
import tangent65536.losm.picdown.util.DownloaderBase;

public class DownloadVTCTW extends DownloaderBase
{
	public DownloadVTCTW()
	{
		super("Himawari True Color Taiwan", "DownloadPics/VisTrueColor/Taiwan", "http://www.cwb.gov.tw/V7/observe/satellite/Data/ts3p/ts3p-%yyyy%-%mm%-%dd%-%hh%-%nn%.jpg", CommonIntervals.TEN_MINUTES.interval);
	}

	@Override
	protected DateTime fixStart(DateTime start)
	{
		return CommonIntervals.TEN_MINUTES.alignToInterval(start);
	}
}
