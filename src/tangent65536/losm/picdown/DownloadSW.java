package tangent65536.losm.picdown;

import tangent65536.losm.picdown.util.CommonIntervals;
import tangent65536.losm.picdown.util.DateTime;
import tangent65536.losm.picdown.util.DownloaderBase;

public class DownloadSW extends DownloaderBase
{
	public DownloadSW()
	{
		super("Surface Wind", "DownloadPics/SurfaceWind", "https://watch.ncdr.nat.gov.tw/00_Wxmap/5A7_CWB_WINDMAP/%yyyy%%mm%/windmap_%yyyy%%mm%%dd%%hh%%nn%.png", CommonIntervals.ONE_HOUR.interval);
	}

	@Override
	protected DateTime fixStart(DateTime start)
	{
		return CommonIntervals.ONE_HOUR.alignToInterval(start);
	}
}
