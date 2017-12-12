/**
 * Copyright (c) 2016-2017 Tangent65536 on GitHub.
 * You're allowed to use this program and source code under Apache License 2.0 overriden by the terms below:
 *   0. I may to change the licensing of this program without any noticing.
 *   1. Any commercial uses are forbidden.
 *   2. Users are NOT allowed to abuse the functions in order to overflow any web service.
 *   3. This project is provided as is without any guarantees or warranty. USE IT ON YOUR OWN RISK!
 */

package tangent65536.losm.picdown;

import tangent65536.losm.picdown.util.DownloaderBase;

public enum EnumDownloaders
{
	VTCEA(new DownloadVTCEA()),
	VTCTW(new DownloadVTCTW()),
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
	VTW(new DownloadVTW()),
	H8B03(new DownloadH8B03()),
	H8B07(new DownloadH8B07()),
	H8B13(new DownloadH8B13()),
	H8NGT(new DownloadH8NGT()),
	RJP(new DownloadRJP());
	
	public final DownloaderBase downloader;
	
	private EnumDownloaders(DownloaderBase _downloader)
	{
		this.downloader = _downloader;
	}
}
