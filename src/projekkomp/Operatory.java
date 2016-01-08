/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekkomp;

/**
 *
 * @author dawid
 */
public class Operatory {
    private static final String[] opr = {"~","&","|","=>","<->"};
    private static final String[] operatory = {"&","|","=>","<->"};
    private static final String[] oprDek={"&","~&","|","~|","=>","~=>","<->","~<->"};

    public static String[] getOperatory() {
        return operatory;
    }
    
    public static String[] getOpr() {
        return opr;
    }

    public static String[] getOprDek() {
        return oprDek;
    }
    
}
