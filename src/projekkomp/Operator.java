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
public class Operator {
    private final String operator;
    private int indexDek;

    public Operator(String operator, int indexDek) {
        this.operator = operator;
        this.indexDek = indexDek;
    }

    public String getOperator() {
        return operator;
    }

    public int getIndexDek() {
        return indexDek;
    }

    public void setIndexDek(int indexDek) {
        this.indexDek = indexDek;
    }
   
}
