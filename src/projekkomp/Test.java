/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekkomp;
import java.util.*;
import projekkomp.graf.JGraph;
/**
 *
 * @author dawid
 */
public class Test {
    

    /**
     * @param args the command line arguments
     */
    private static final LinkedList<LinkedList> drzewo=new LinkedList<>();
    private static LinkedList<Formula> poziom= new LinkedList<>();
    private static int nrFormuly;
    private static boolean flaga=true;
    
    public static void main(String[] args) {
        // TODO code application logic here
        String formula="~(p&q)";
        Formula root= new Formula(formula,0,0);
        poziom.add(root);
        drzewo.add(poziom);
        int i=0;
        while(flaga)
        {
            System.out.println("~"+"dekomponowanie pozom"+(i));
            poziom=dekomponujF(drzewo.get(i));
            if(poziom.isEmpty())
            {
                flaga=false;
            }
            drzewo.add(poziom);
            i++;
        
        }
        while(drzewo.getLast().isEmpty())
                {
                    drzewo.removeLast();
                }
        System.out.println(drzewo.size()+"   "+i);
        new JGraph().rysuj(drzewo); 
    }
    static String redukcjaN(String f)
    {
        if(f.matches("^\\(.+\\)$"))
            f=f.substring(1,f.length()-1);
            
       return f; 
    }
    static Boolean sprawdzN(String formula)
    {
        int nawias=0;
        System.out.println(formula);
        char[] tablicaF=formula.toCharArray();
        for(char i: tablicaF){
            if(i=='(')nawias++;
            else if(i==')')nawias--;
        }
        if(nawias==0)return true;
        return false;
    }
    static LinkedList dekomponujF(LinkedList poz)
    {
        LinkedList<Formula> poziomy=new LinkedList<>();
        int i=0;
        int licznik=0;
        for (Iterator it = poz.iterator(); it.hasNext();) 
        {
            Formula formula = (Formula) it.next();
            if(formula.isDekomponowac()){
                System.out.println("formula przekazywana do dzielenia  "+formula.getFormula()+formula.getNR());
                String[] f=dziel(formula.getFormula());
                for(int j=0;j<f.length;j++)
                {
                    poziomy.add(new Formula(f[j],++nrFormuly, formula.getNR()));
                  
                }
            //System.out.println(f[1]+" poziom "+formula.getRodzic()+i);
               
        }
            i++;
        }
        
        return poziomy;
       
    }
    static String[] dziel(String formula){
        String[] formulaTab=formula.split(";");
        String[] newFormula = null;
        String subFormula="";
        int i=0;
        while(i<formulaTab.length)
        {
            if(Formula.dekomposite(formulaTab[i]))
                break;
            i++;
        }
        System.out.println("Formula przekazana do"+formulaTab[i]);
        Operator oper=Formula.getIndexOfOperator(formulaTab[i]);
        String operator=oper.getOperator();
        System.out.println(operator+"tutaj ");
        String f=formulaTab[i];
        System.out.println(f+"tu tez cos zle dziala");
        while(oper.getOperator().equals("~")){
           
            f=f.replaceFirst("~", "");
            System.out.println(operator+" jestem tu +++++++");
            if(f.matches("^[(].+[)]$"))
                f=f.substring(1,f.length()-1);
                System.out.println("formu po redukcji nawiasów  "+f);
                oper=Formula.getIndexOfOperator(f);
                operator+=oper.getOperator();
                if(operator.equals("~~"))operator="";
            
            
        }
        System.out.println("tu doszedlem"+operator);
        String[] operDek=Operatory.getOprDek();
        if(operator.equals(operDek[0])){
            System.out.println("dziele tym symbolem &");
            subFormula+=f.substring(0, oper.getIndexDek());
            subFormula=redukcjaN(subFormula);
            subFormula+=";"+f.substring(oper.getIndexDek()+operator.length(),f.length());
            System.out.println("dobrze podzielilem ide dalej");
            newFormula= new String[1];
           System.out.println(subFormula+"  nowa formula");
           newFormula[0]=subFormula;
        }
        else if(operator.equals(operDek[1])){
            newFormula=new String[2];
            newFormula[0]="~"+f.substring(0, oper.getIndexDek());
            newFormula[1]="~"+f.substring(oper.getIndexDek()+oper.getOperator().length(), f.length());
            
        }
        else if(operator.equals(operDek[2])){
            
        }
        else if(operator.equals(operDek[3])){
            
        }
        else if(operator.equals(operDek[4])){
            
        }
        else if(operator.equals(operDek[5])){
            
        }
        else if(operator.equals(operDek[6])){
            
        }
        else if(operator.equals(operDek[7])){
            
        }
       if(formulaTab.length>1){
           for(int k=0;k<formulaTab.length;k++)
               if(k!=i){
                   for(int j=0;j<newFormula.length;j++)
                   {
                       System.out.println("scalam formule ++++ "+formulaTab[k]+"  ++++++++ ");
                       newFormula[j]+=";"+formulaTab[k];
                   }   
               }
       } 
       return newFormula;
    }
}

