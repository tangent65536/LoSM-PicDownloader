package tangent65536.losm.picdown;

import tangent65536.losm.picdown.util.CommonIntervals;
import tangent65536.losm.picdown.util.DateTime;
import tangent65536.losm.picdown.util.DownloaderBase;

public class DownloadRTW extends DownloaderBase
{
	public DownloadRTW()
	{
		super("Radar Taiwan", "DownloadPics/Radar/Taiwan", "http://www.cwb.gov.tw/V7/observe/radar/Data/HD_Radar/CV1_TW_3600_%yyyy%%mm%%dd%%hh%%nn%.png", CommonIntervals.TEN_MINUTES.interval);
	}

	@Override
	protected DateTime fixStart(DateTime start)
	{
		return CommonIntervals.TEN_MINUTES.alignToInterval(start);
	}
}
