package ro.pata.microchipbaudcalculator;

/**
 *
 * @author adi
 */
public class MicrochipBaudCalculator {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if(args.length==2){
            BaudCalculator c=new BaudCalculator(Integer.parseInt(args[0]));
            c.getSPBRG(Integer.parseInt(args[1]));
        } else {
            System.out.println("Usage: MicrochipBaudCalculator FOSC BAUD");
            System.out.println("Ex:    MicrochipBaudCalculator 8000000 9600");
        }
    }
    
}
