package tangent65536.losm.picdown;

import tangent65536.losm.picdown.util.DownloaderBase;

public enum EnumDownloaders
{
	FREA02(new DownloadFREA02()),
	IREA(new DownloadIREA()),
	IRTW(new DownloadIRTW()),
	LST(new DownloadLST()),
	PCP(new DownloadPCP()),
	RA(new DownloadRA()),
	REA(new DownloadREA()),
	RTW(new DownloadRTW()),
	SFC(new DownloadSFC()),
	SW(new DownloadSW()),
	VEA(new DownloadVEA()),
	VTW(new DownloadVTW());
	
	public final DownloaderBase downloader;
	
	private EnumDownloaders(DownloaderBase _downloader)
	{
		this.downloader = _downloader;
	}
}
