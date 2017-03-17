package tangent65536.losm.picdown;

import tangent65536.losm.picdown.util.CommonIntervals;
import tangent65536.losm.picdown.util.DateTime;
import tangent65536.losm.picdown.util.DownloaderBase;

public class DownloadFREA02 extends DownloaderBase
{
	public DownloadFREA02()
	{
		super("Himawari 0.51um East Asia", "DownloadPics/HimawariFR/Band02", "http://rammb.cira.colostate.edu/ramsdis/online/images/himawari-8/eastern_china_band_02/eastern_china_band_02_%yyyy%%mm%%dd%%hh%%nn%00.gif", CommonIntervals.TEN_MINUTES.interval);
	}
	
	@Override
	protected DateTime fixStart(DateTime start)
	{
		return CommonIntervals.TEN_MINUTES.alignToInterval(start);
	}
}
