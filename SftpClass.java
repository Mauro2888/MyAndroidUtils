
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import org.apache.commons.io.FileUtils;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

public class TestSftp {
	

	public final static String USERS_LISTS = String.valueOf(System.getProperty("Props"));
	public final static String DMZ_HOST = String.valueOf(System.getProperty("Props"));
	public final static String DMZ_FOLDER_PATH = String.valueOf(System.getProperty("Props"));
	public final static String DMZ_PASSWORD = "Props";
	
	static SftpDto sftpDtoInstance = new SftpDto();
	
	public TestSftp(){
		
	}
	
	/**
	 * Get inputStream from Jms and copy to Tmp file 
     * 
     * @param inputStream jms zip flow
     * @param fileName get file name from jms zip file 
     * @throws IOException
     */	
	
	public static void zippedFlowToSftp(InputStream inputStream ,String fileName) throws IOException {
        File flowZipped = new File("//tmp//" + fileName);
        checkUserName(flowZipped.getName());
        FileUtils.copyInputStreamToFile(inputStream, flowZipped);
        sftpDmzConnection(flowZipped,fileName);
    }
	
	
	/**
	    * Get username according nomenclature country instance file
	    * 
	    * @param fileName get source filename
	    * 
	    */
	
	private static void checkUserName(String fileName) {
		String[] whitoutFileName = fileName.split("_");

        String marketName = whitoutFileName[(whitoutFileName.length-3)] + "_" + whitoutFileName[(whitoutFileName.length-2)];

        String[]splitUsers = USERS_LISTS.split(";");
        String[]elements = null;
        HashMap<String,String> mapUsersCountry = new HashMap<>();
        for (String folderType : splitUsers) {
            elements = folderType.split(":");
            mapUsersCountry.put(elements[0].toUpperCase(),elements[1].toLowerCase());
        }

        
        Iterator iterator = mapUsersCountry.entrySet().iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }

        String dmzUserName = null;
        if (mapUsersCountry.containsKey(marketName.toUpperCase())){
            dmzUserName = mapUsersCountry.get(marketName.toUpperCase());
            sftpDtoInstance.setUserName(dmzUserName);
            sftpDtoInstance.setDmzHost(DMZ_HOST); 
        }	
	}

	/**
	 * Create connection to SFTP and save file in a dmz folder mock/i301
	 * 
	 * @param zipFile input
	 * @param fileName zipFileName
	 * 
	 */
	public static void sftpDmzConnection (File file,String fileName) {
		
		String SFTPUSER = sftpDtoInstance.getUserName();
        String SFTPHOST = DMZ_HOST;
        int SFTPPORT = 22;
        String SFTPPASS = DMZ_PASSWORD;
        String SFTPWORKINGDIR = DMZ_FOLDER_PATH;
        
        System.out.println(SFTPUSER + " " +SFTPHOST + " " + SFTPPASS);

        Session session = null;
        Channel channel = null;
        ChannelSftp channelSftp = null;
        System.out.println("Preparo Host");
        try {
            JSch jsch = new JSch();
            session = jsch.getSession(SFTPUSER, SFTPHOST, SFTPPORT);
            session.setPassword(SFTPPASS);
            /*java.util.Properties config = new java.util.Properties();
            session.setConfig(config);*/
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();           
            System.out.println("Host connected.");
            channel = session.openChannel("sftp");
            channel.connect();
            System.out.println("sftp channel opened and connected.");
            channelSftp = (ChannelSftp) channel;            
            channelSftp.cd(SFTPWORKINGDIR);        
            //put file to dmz 
            channelSftp.put(new FileInputStream(file),fileName);
            System.out.println("FILE " + file + " " + fileName);
            System.out.println("File transfered successfully to host");
        } catch (Exception ex) {
             System.out.println("Exception found while tranfer the response." + ex.toString());
        }
        finally{
            channelSftp.exit();
            System.out.println("sftp Channel exited.");
            channel.disconnect();
            System.out.println("Channel disconnected.");
            session.disconnect();
            System.out.println("Host Session disconnected.");
        }
    }   
}
