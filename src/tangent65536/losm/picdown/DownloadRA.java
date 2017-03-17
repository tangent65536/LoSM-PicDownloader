package tangent65536.losm.picdown;

import tangent65536.losm.picdown.util.CommonIntervals;
import tangent65536.losm.picdown.util.DateTime;
import tangent65536.losm.picdown.util.DownloaderBase;

public class DownloadRA extends DownloaderBase
{
	public DownloadRA()
	{
		super("Model Initialization Field", "DownloadPics/MIF", "https://watch.ncdr.nat.gov.tw/00_Wxmap/5F1_NCDR_WRF_5km_precipitation/05days/%yyyy%%mm%/%mm%%dd%%hh%/surface/d03_sfc-%yyyy%%mm%%dd%%hh%-%mm%%dd%%hh%.gif", CommonIntervals.ONE_DAY.interval);
	}

	@Override
	protected DateTime fixStart(DateTime start)
	{
		return CommonIntervals.ONE_DAY.alignToInterval(start);
	}
}
