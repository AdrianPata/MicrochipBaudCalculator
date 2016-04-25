/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.pata.microchipbaudcalculator;

import java.text.DecimalFormat;

/**
 *
 * @author adi
 */
public class BaudCalculator {
    int fosc;
    
    public BaudCalculator(int fosc){
        this.fosc=fosc;
    }
    
    public byte getBit(byte v,byte position)
    {
       return (byte)((v >> position) & 1);
    }
    
    public String getParams(byte p){
        return "SYNC="+getBit(p,(byte)2)+" BRGH="+getBit(p,(byte)1)+" BRG16="+getBit(p,(byte)0);
    }
    
    public int getBaud(byte sync,byte brgh, byte brg16,int spbrg){
        int formulaVar=4;
        
        if(sync==0 && brg16==0 && brgh==0) formulaVar=64;
        if(sync==0 && brg16==0 && brgh==1) formulaVar=16;
        if(sync==0 && brg16==1 && brgh==0) formulaVar=16;
        
        return fosc/(formulaVar*(spbrg+1));
    }
    
    public String getErr(int baud, int calcBaud){
        float err;
        DecimalFormat df = new DecimalFormat("#.##");
        err=((float)calcBaud*100/(float)baud)-100;
        return err+"%";
    }
    
    public void getSPBRG(int baud){
        int spbrg;
        int calcBaud;
        for(byte a=0;a<=3;a++){
            System.out.println(getParams(a));
            spbrg=calc(getBit(a,(byte)2),getBit(a,(byte)1),getBit(a,(byte)0),baud);
            calcBaud=getBaud(getBit(a,(byte)2),getBit(a,(byte)1),getBit(a,(byte)0),spbrg);
            System.out.println("SPBRG="+spbrg+" for Baud="+calcBaud+" Error="+getErr(baud, calcBaud));
        }
    }
    
    public int calc(byte sync,byte brgh, byte brg16,int baud){
        int formulaVar=4;
        float spbrg;
        
        if(sync==0 && brg16==0 && brgh==0) formulaVar=64;
        if(sync==0 && brg16==0 && brgh==1) formulaVar=16;
        if(sync==0 && brg16==1 && brgh==0) formulaVar=16;
        
        spbrg=(fosc/(baud*formulaVar))-1;
        
        return Math.round(spbrg);
    }
}
