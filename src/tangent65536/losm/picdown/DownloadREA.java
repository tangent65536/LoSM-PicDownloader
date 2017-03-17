package tangent65536.losm.picdown;

import tangent65536.losm.picdown.util.CommonIntervals;
import tangent65536.losm.picdown.util.DateTime;
import tangent65536.losm.picdown.util.DownloaderBase;

public class DownloadREA extends DownloaderBase
{
	public DownloadREA()
	{
		super("Radar East Asia", "DownloadPics/Radar/EastAsia", "http://www.cwb.gov.tw/V7/observe/radar/Data/HD_Radar/CV1_3600_%yyyy%%mm%%dd%%hh%%nn%.png", CommonIntervals.TEN_MINUTES.interval);
	}

	@Override
	protected DateTime fixStart(DateTime start)
	{
		return CommonIntervals.TEN_MINUTES.alignToInterval(start);
	}
}
