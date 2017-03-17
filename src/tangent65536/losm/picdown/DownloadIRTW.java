package tangent65536.losm.picdown;

import tangent65536.losm.picdown.util.CommonIntervals;
import tangent65536.losm.picdown.util.DateTime;
import tangent65536.losm.picdown.util.DownloaderBase;

public class DownloadIRTW extends DownloaderBase
{
	public DownloadIRTW()
	{
		super("Himawari Enhanced(IR) Taiwan", "DownloadPics/IR/Taiwan", "http://cwb.gov.tw/V7/observe/satellite/Data/s3q/s3q-%yyyy%-%mm%-%dd%-%hh%-%nn%.jpg", CommonIntervals.TEN_MINUTES.interval);
	}

	@Override
	protected DateTime fixStart(DateTime start)
	{
		return CommonIntervals.TEN_MINUTES.alignToInterval(start);
	}
}
