package no.blopp.bloppberrypi.test;

import com.pi4j.io.serial.Serial;
import com.pi4j.io.serial.SerialDataEvent;
import com.pi4j.io.serial.SerialDataListener;
import com.pi4j.io.serial.SerialFactory;
import com.pi4j.io.serial.SerialPortException;

class RFID
{
    // - Get an instance of Serial for COM interaction
    private final Serial serial = SerialFactory.createInstance();

    public RFID()
    {
        // - Change this to the COM port of your RFID reader
        String comPort = "/dev/ttyUSB0";

        // - Create and add a SerialDataListener
        serial.addListener(new SerialDataListener()
        {
            @Override
            public void dataReceived(SerialDataEvent event)
            {
                // - Get byte array from SerialDataEvent
                byte[] data = event.getData().getBytes();
                
                // - Iterate byte array print a readable representation of each byte
                for ( int i=0; i < data.length; i++ )
                {
                    System.out.printf( "0x%02x ", data[i] );
                }

                // - Line break to represent end of data for this event
                System.out.println();
            }
        });


        // - Attempt to open the COM port
        try{
        	serial.open( comPort, 2400 );
        	System.out.println("COM port opened!");
        }catch(SerialPortException e)
        {
        	System.out.println("Could not establish connection");
        }

        
        
         
        

        // - When you are done, ensure you close the port
        // To demonstrate, I am waiting 20 seconds and then closing the port.
        try
        {
            // - Sleep for 20 seconds, (in ms)
            Thread.sleep(60000);

            // - Close port
            serial.close();
            System.out.println("COM port closed.");
        }
        catch ( Exception ex )
        {
            // - I am intentionally ignoring any exception.
        }
        
        // - And terminate
        System.exit(0);
    }

    public static void main( String[] args )
    {
        new RFID();
    }
}
