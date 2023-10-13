package Model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class ConfigHandler {
	private static final String CONFIG_FILE = "src/application/config.properties";
    private static final String TOTAL_DURATION_KEY = "total_usage_hours";
    private static final String USAGE_LIMIT_KEY = "usage_limit";
    private static final String PASSWORD_KEY = "password";
    private static final String RADIUS_KEY = "helezon_radius";
    private static final String ACCESS_FLAG = "continue_flag";

    private Properties properties;
    private boolean continueFlag = false;

    public ConfigHandler() {
        properties = new Properties();
        loadConfig();
        
        if(this.getAccessFlag() == 1) continueFlag = true;
        
    }

    private void loadConfig() {
    	String userDir = System.getProperty("user.dir");
        String fileName = "config.properties";
        File file = new File(userDir, fileName);
        try ( 		
        		InputStream input = new FileInputStream(file.getPath())) {
            properties.load(input);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public long getTotalDuration() {
        String durationStr = properties.getProperty(TOTAL_DURATION_KEY);
        return Long.parseLong(durationStr);
    }

    public void setTotalDuration(long totalDuration) {
        properties.setProperty(TOTAL_DURATION_KEY, String.valueOf(totalDuration));
        saveConfig();
    }
    
    public int getAccessFlag() {
        String durationStr = properties.getProperty(ACCESS_FLAG);
        return Integer.parseInt(durationStr);
    }

    public void setAccessFlag(int flag) {
        properties.setProperty(ACCESS_FLAG, String.valueOf(flag));
        saveConfig();
    }
    
    public long getRadiusSquare() {
        String radiusStr = properties.getProperty(RADIUS_KEY);
        return Long.parseLong(radiusStr);
    }

    public void setRadiusSquare(long radiusSquare) {
        properties.setProperty(TOTAL_DURATION_KEY, String.valueOf(radiusSquare));
        saveConfig();
    }

    public int getUsageLimit() {
        String limitStr = properties.getProperty(USAGE_LIMIT_KEY);
        return Integer.parseInt(limitStr);
    }

    public void setUsageLimit(int limit) {
        properties.setProperty(USAGE_LIMIT_KEY, String.valueOf(limit));
        saveConfig();
    }

    public String getPassword() {
        return properties.getProperty(PASSWORD_KEY, "");
    }

    public void setPassword(String password) {
        properties.setProperty(PASSWORD_KEY, password);
        saveConfig();
    }
    
    public boolean isLimitReached() {
    	return (this.getTotalDuration() >= this.getUsageLimit()) ? true : false;
    }
    private void saveConfig() {
        try (OutputStream output = new FileOutputStream(CONFIG_FILE)) {
            properties.store(output, null);
        } catch (IOException e) {
            // Handle file save exceptions
        }
    }
}
