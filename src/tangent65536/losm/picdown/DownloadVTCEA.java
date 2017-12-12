package tangent65536.losm.picdown;

import tangent65536.losm.picdown.util.CommonIntervals;
import tangent65536.losm.picdown.util.DateTime;
import tangent65536.losm.picdown.util.DownloaderBase;

public class DownloadVTCEA extends DownloaderBase
{
	public DownloadVTCEA()
	{
		super("Himawari True Color East Asia", "DownloadPics/VisTrueColor/EastAsia", "http://www.cwb.gov.tw/V7/observe/satellite/Data/ts1p/ts1p-%yyyy%-%mm%-%dd%-%hh%-%nn%.jpg", CommonIntervals.TEN_MINUTES.interval);
	}

	@Override
	protected DateTime fixStart(DateTime start)
	{
		return CommonIntervals.TEN_MINUTES.alignToInterval(start);
	}
}
