package tangent65536.losm.picdown;

import tangent65536.losm.picdown.util.CommonIntervals;
import tangent65536.losm.picdown.util.DateTime;
import tangent65536.losm.picdown.util.DownloaderBase;

public class DownloadPCP extends DownloaderBase
{
	public DownloadPCP()
	{
		super("Daily Accmu. Precip.", "DownloadPics/Precip", "http://www.cwb.gov.tw/V7/observe/rainfall/Data/hkc%dd%%hh%%n%.jpg", CommonIntervals.THIRTY_MINUTES.interval);
	}
	
	@Override
	protected String getURL(final DateTime time)
	{
		return time.encode(this.urlBase).replaceAll("%n%", String.format("%01d", time.minute / 10));
	}

	protected DateTime fixStart(DateTime start)
	{
		return CommonIntervals.THIRTY_MINUTES.alignToInterval(start);
	}
}
