package tangent65536.losm.picdown.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class DownloaderBase
{
	private static final Logger logger = LogManager.getLogger();
	
	public final String name;
	protected final String urlBase;
	
	protected final Interval timeInterval;
	
	protected final File saveDir;
	
	protected DownloaderBase(String _name, String _savedir, String _urlBase, Interval _timeInterval)
	{
		this.name = _name;
		this.saveDir = new File(_savedir);
		this.saveDir.mkdirs();
		this.urlBase = _urlBase;
		this.timeInterval = _timeInterval;
	}
	
	protected String getURL(final DateTime time)
	{
		return time.encode(this.urlBase);
	}
	
	protected abstract DateTime fixStart(final DateTime start);
	
	protected final ArrayList<DateTime> getDates(final DateTime input_start, final DateTime end)
	{
		return DateTime.getDates(this.fixStart(input_start), end, this.timeInterval);
	}
	
	public final void downloadFiles(final DateTime start, final DateTime end)
	{
		for(DateTime time : this.getDates(start, end))
		{
			downloadFile(time);
		}
	}
	
	public final void downloadFile(final DateTime date)
	{
		try
		{
			byte[] bytes = new byte[4096];
			int readBytes = -1;
			
			String strUrl = getURL(date);
			URL url = new URL(getURL(date));
			HttpURLConnection.setFollowRedirects(false);
			HttpURLConnection connection = (HttpURLConnection)url.openConnection();
			connection.setRequestMethod("GET");
			connection.connect();
			
			int returnCode = connection.getResponseCode();
			
			String fileName = getFileName(strUrl);
			if(returnCode / 100 == 2)
			{
				logger.info("Downloading " + fileName);
				InputStream inputData = connection.getInputStream();
				FileOutputStream fos = new FileOutputStream(new File(this.saveDir, fileName));
				while((readBytes = inputData.read(bytes)) > -1)
				{
					fos.write(bytes, 0, readBytes);
				}
				inputData.close();
				fos.flush();
				fos.close();
			}
			else
			{
				logger.error("Unable to download " + fileName + "! Is it deleted?");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			logger.fatal("Internal error! Please check your internet connection, or make sure the target server is working, or your working directory is writable.");
		}
	}
	
	private static String getFileName(String url)
	{
		String[] strs = url.split("/");
		return strs[strs.length - 1];
	}
}
