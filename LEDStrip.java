import java.io.IOException;
import java.util.Scanner;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

public class LEDStrip {

    public static void main(String[] args) throws InterruptedException, IOException {
        System.out.println("Press Enter to start shining the LED strip");
        new Scanner(System.in).nextLine();
        
        // create gpio controller
        final GpioController gpio = GpioFactory.getInstance();
        
        // provision gpio pin #01 as an output pin and turn it off
        final GpioPinDigitalOutput ledStrip = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, "LED Strip", PinState.LOW);
        ledStrip.setShutdownOptions(true, PinState.LOW);
        
        // turn on the LED strip
        ledStrip.high();
        System.out.println("LED strip is shining");
        
        // wait for 10 seconds
        Thread.sleep(10000);
        
        // turn off the LED strip
        ledStrip.low();
        System.out.println("LED strip is turned off");
        
        // release the GPIO controller resources
        gpio.shutdown();
    }
}
