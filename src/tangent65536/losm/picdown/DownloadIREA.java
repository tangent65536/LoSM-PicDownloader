package tangent65536.losm.picdown;

import tangent65536.losm.picdown.util.CommonIntervals;
import tangent65536.losm.picdown.util.DateTime;
import tangent65536.losm.picdown.util.DownloaderBase;

public class DownloadIREA extends DownloaderBase
{
	public DownloadIREA()
	{
		super("Himawari Enhanced(IR) East Asia", "DownloadPics/IR/EastAsia", "http://cwb.gov.tw/V7/observe/satellite/Data/s1q/s1q-%yyyy%-%mm%-%dd%-%hh%-%nn%.jpg", CommonIntervals.TEN_MINUTES.interval);
	}

	@Override
	protected DateTime fixStart(DateTime start)
	{
		return CommonIntervals.TEN_MINUTES.alignToInterval(start);
	}
}
