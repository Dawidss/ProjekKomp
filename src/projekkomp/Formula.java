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
public final class Formula{
    private final String formula;
    private final int rodzic;
    private final int nr;
    private boolean dekomponowac;
    

    public Formula(String formula,int nr, int rodzic) {
        this.dekomponowac = false;
        this.formula = formula;
        this.rodzic = rodzic;
        this.nr=nr;
        this.dekomponowac=dekomposite(this.formula);
        System.out.println("jaki status "+this.dekomponowac);
    }

    public int getNR(){
        return nr;
    }
    public String getFormula() {
        return formula;
    }

    public int getRodzic() {
        return rodzic;
    }

    public boolean isDekomponowac() {
        return dekomponowac;
    }
    public static Operator getIndexOfOperator(String formulaDek){
        char[] chars = formulaDek.toCharArray();
        int nawias = 0;
        int index=0;
        String operAkt="";
        String[] opr=Operatory.getOpr();
        int operIdx=0;
        for(String oper1: opr){
            final char[] oper = oper1.toCharArray();
            System.out.println(oper1+" Sprawdzany operator + formula w ktorej szukamy "+formulaDek);
            operIdx= 0;
            try{
            for (int i = 0; i < chars.length; i++)
            {
                final char cur = chars[i];
                System.out.println(cur);
                if (cur == '(') {
                    ++nawias;
                }
                else if (cur == ')') {
                    --nawias;
                }
                else if (nawias== 0) {
                    if (cur == oper[operIdx++]) {
                        if (operIdx == oper.length) {
                            if("~".equals(cur))
                            {
                                operAkt+=cur;
                            }
                            index=i-oper1.length()+1;
                            operAkt+=oper1;
                        }
                    }
                else{
                    operIdx = 0;
                }
            }
        }}
        catch(ArrayIndexOutOfBoundsException e){  
        }
        }
        System.out.println("pozycja symbolu "+index+" operator "+operAkt);
        return new Operator(operAkt,index );
    }
    public static boolean dekomposite(String formula)
    {
        String[] opr=Operatory.getOperatory();
        String[]formulaTab=formula.split(";");
        for(String formulaTab1 : formulaTab){
            if(formulaTab1.length()<=2)continue;
            for(String opr1: opr){
                System.out.println(opr1+" operator formula="+formulaTab1+formulaTab1.matches("(?).*"+opr1+".*"));
                if(formulaTab1.matches("(?).+["+opr1+"].+"))
                   return true;
            }
        }
        return false;
    } 
    public static boolean sprawdzFormula(String fr)
    {   
        String orgFormua=fr;
        String[] opr=Operatory.getOpr();
        orgFormua=orgFormua.replace("(", "");
        orgFormua=orgFormua.replace(")", "");
        for(int i=0;i<opr.length;i++)
        {
            orgFormua=orgFormua.replace(opr[i], "");
        }
        char[] tabFormula=orgFormua.toCharArray();
        for(int i =0 ;i<tabFormula.length;i++)
        {
            if(tabFormula[i]<65||tabFormula[i]>122||tabFormula[i]==91||tabFormula[i]==91
                    ||tabFormula[i]==92||tabFormula[i]==93||tabFormula[i]==94||tabFormula[i]==95||tabFormula[i]==96)
            {
                System.out.println(orgFormua);
                return false;
            }
        }
        return true;
    }
}

