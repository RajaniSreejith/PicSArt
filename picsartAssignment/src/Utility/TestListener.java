package Utility;

import java.util.logging.Logger;

import org.testng.ITestContext;
import org.testng.ITestListener;

public class TestListener implements ITestListener {
	private static final Logger LOGGER = Logger.getLogger("TestListener.class");
	
	@Override		
    public void onFinish(ITestContext arg0) {					
        // TODO Auto-generated method stub		
		System.out.println("Starting test Method"+arg0.getName());
		LOGGER.info("Starting test Method"+arg0.getName());
        		
    }		

    @Override		
    public void onStart(ITestContext arg0) {					
        // TODO Auto-generated method stub		
    	LOGGER.info("End of the test Method"+arg0.getName());
        		
    }		

}
