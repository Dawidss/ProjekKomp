/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekkomp.graf;

import com.mxgraph.layout.*;
import com.mxgraph.swing.*;
import java.awt.*;
import java.util.Iterator;
import java.util.LinkedList;
import javax.swing.*;
import org.jgrapht.*;
import org.jgrapht.demo.JGraphAdapterDemo;
import org.jgrapht.ext.*;
import org.jgrapht.graph.*;
import projekkomp.*;


/**
 * A demo applet that shows how to use JGraphX to visualize JGraphT graphs.
 * Applet based on JGraphAdapterDemo.
 *
 * @since July 9, 2013
 */
public class JGraph
    extends JApplet
{
    

    private static final long serialVersionUID = 2202072534703043194L;
    private static final Dimension DEFAULT_SIZE = new Dimension(700, 700);

    

    private JGraphXAdapter<String, DefaultEdge> jgxAdapter;

    

    /**
     * An alternative starting point for this demo, to also allow running this
     * applet as an application.
     *
     * @param args ignored.
     */
    public  void rysuj(LinkedList lista)
    {
        JGraph applet = new JGraph();
        applet.setMinimumSize(new Dimension(400, 400));
        applet.setGraf(lista);

        JFrame frame = new JFrame();
        frame.getContentPane().add(applet);
        frame.setTitle("JGraphT");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * {@inheritDoc}
     * @param lista
     */
    public void setGraf(LinkedList lista)
    {
        int licznik=0;
        // create a JGraphT graph
        ListenableGraph<String, DefaultEdge> g =
            new ListenableDirectedGraph<>(DefaultEdge.class);

        // create a visualization using JGraph, via an adapter
        jgxAdapter = new JGraphXAdapter<>(g);
        getContentPane().add(new mxGraphComponent(jgxAdapter));
        resize(DEFAULT_SIZE);
        for(int i=0;i<lista.size()-1;i++)
        {
            
            LinkedList<Formula> poziomy1=(LinkedList<Formula>)lista.get(i);
            //if(i==0)g.addVertex(1+"("+poziomy1.getFirst().getFormula()+")");
            LinkedList<Formula> poziomy2=(LinkedList<Formula>)lista.get(i+1);
            for(int j=0;j<poziomy1.size();j++)
            {
                String a=poziomy1.get(j).getFormula()+poziomy1.get(j).getNR();
                g.addVertex(a);
                for(Iterator it=poziomy2.iterator();it.hasNext();)
                {
                    
                    Formula formula=(Formula) it.next();
                    if(poziomy1.get(j).getNR()==formula.getRodzic())
                    {
                        
                        String b=formula.getFormula()+formula.getNR();
                        g.addVertex(b);
                        g.addEdge(a,b);
                        
                    }
                }
                
            }
            
        }
        mxCompactTreeLayout layout = new mxCompactTreeLayout(jgxAdapter);
        layout.setHorizontal(false);
        layout.setMoveTree(true);
        layout.setEdgeRouting(false);
        layout.setNodeDistance(50);
        layout.execute(jgxAdapter.getDefaultParent());
        

        // that's all there is to it!...
    }
}

