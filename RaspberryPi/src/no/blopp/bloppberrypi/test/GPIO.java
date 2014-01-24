package no.blopp.bloppberrypi.test;

import java.io.FileWriter;
import java.io.File;

/**
 *
 * @author hinkmond
 */
public class GPIO {
    
    static final String GPIO_OUT = "out";
    static final String GPIO_ON = "1";
    static final String GPIO_OFF = "0";
    static final String GPIO_CH00="0";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        try {
            
            /*** Init GPIO port for output ***/
            
            // Open file handles to GPIO port unexport and export controls
            FileWriter unexportFile = new FileWriter("/sys/class/gpio/unexport");
            FileWriter exportFile = new FileWriter("/sys/class/gpio/export");

            // Reset the port
            File exportFileCheck = new File("/sys/class/gpio/gpio"+gpioChannel);
            if (exportFileCheck.exists()) {
                unexportFile.write(gpioChannel);
                unexportFile.flush();
            }
            
            
            // Set the port for use
            exportFile.write(GPIO_CH00);   
            exportFile.flush();

            // Open file handle to port input/output control
            FileWriter directionFile =
                    new FileWriter("/sys/class/gpio/gpio"+GPIO_CH00+"/direction");
            
            // Set port for output
            directionFile.write(GPIO_OUT);
            directionFile.flush();
            
            /*** Send commands to GPIO port ***/
            
            // Open file handle to issue commands to GPIO port
            FileWriter commandFile = new FileWriter("/sys/class/gpio/gpio"+GPIO_CH00+
                    "/value");
            
            // Loop forever
            while (true) {
                
                // Set GPIO port ON
                commandFile.write(GPIO_ON);
                commandFile.flush();
        
                // Wait for a while
                java.lang.Thread.sleep(200);
        
                // Set GPIO port OFF
                commandFile.write(GPIO_OFF);
                commandFile.flush();
                
                // Wait for a while
                java.lang.Thread.sleep(200);
            }

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
