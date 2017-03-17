package tangent65536.losm.picdown;

import tangent65536.losm.picdown.util.CommonIntervals;
import tangent65536.losm.picdown.util.DateTime;
import tangent65536.losm.picdown.util.DownloaderBase;

public class DownloadVTW extends DownloaderBase
{
	public DownloadVTW()
	{
		super("Himawari Visible Taiwan", "DownloadPics/Visible/Taiwan", "http://www.cwb.gov.tw/V7/observe/satellite/Data/sbo/sbo-%yyyy%-%mm%-%dd%-%hh%-%nn%.jpg", CommonIntervals.TEN_MINUTES.interval);
	}

	@Override
	protected DateTime fixStart(DateTime start)
	{
		return CommonIntervals.TEN_MINUTES.alignToInterval(start);
	}
}
