/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekkomp.graf;
import java.awt.*;
import java.awt.geom.*;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.*;

import org.jgraph.*;
import org.jgraph.graph.*;

import org.jgrapht.*;
import org.jgrapht.ext.*;
import org.jgrapht.graph.*;

// resolve ambiguity
import org.jgrapht.graph.DefaultEdge;
import projekkomp.Formula;


/**
 * A demo applet that shows how to use JGraph to visualize JGraphT graphs.
 *
 * @author Barak Naveh
 * @since Aug 3, 2003
 */
public class JGraphAdapterDemo
    extends JApplet
{
    

    private static final long serialVersionUID = 3256444702936019250L;
    private static final Color DEFAULT_BG_COLOR = Color.decode("#FAFBFF");
    private static final Dimension DEFAULT_SIZE = new Dimension(700,700);

    

    //
    private JGraphModelAdapter<String, DefaultEdge> jgAdapter;

    

    /**
     * An alternative starting point for this demo, to also allow running this
     * applet as an application.
     *
     * @param args ignored.
     */
    public static void graf1(LinkedList lista)
    {
        JGraphAdapterDemo applet = new JGraphAdapterDemo();
        applet.init(lista);

        JFrame frame = new JFrame();
        frame.setSize(DEFAULT_SIZE);
         ScrollPane are=new ScrollPane();
         are.setSize(DEFAULT_SIZE);
        are.add(applet);
        frame.add(are);
        frame.setTitle("JGraph");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * {@inheritDoc}
     */
    public void init(LinkedList lista)
    {
        // create a JGraphT graph
        ListenableGraph<String, DefaultEdge> g =
            new ListenableDirectedMultigraph<String, DefaultEdge>(
                DefaultEdge.class);
        int w=600;
        int h=0;
        // create a visualization using JGraph, via an adapter
        jgAdapter = new JGraphModelAdapter<String, DefaultEdge>(g);

        org.jgraph.JGraph jgraph = new org.jgraph.JGraph(jgAdapter);

        adjustDisplaySettings(jgraph);
        getContentPane().add(jgraph);
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
                positionVertexAt(a,w,h);
                for(Iterator it=poziomy2.iterator();it.hasNext();)
                {
                    
                    Formula formula=(Formula) it.next();
                    if(poziomy1.get(j).getNR()==formula.getRodzic())
                    {
                        
                        String b=formula.getFormula()+formula.getNR();
                        g.addVertex(b);
                        h+=30;
                        if(poziomy2.size()>=2)
                            positionVertexAt(a,w,h);
                        g.addEdge(a,b);
                        
                    }
                }
            }
        }
/**
        String v1 = "v1";
        String v2 = "v2";
        String v3 = "v3";
        String v4 = "v4";

        // add some sample data (graph manipulated via JGraphT)
        g.addVertex(v1);
        g.addVertex(v2);
        g.addVertex(v3);
        g.addVertex(v4);

        g.addEdge(v1, v2);
        g.addEdge(v2, v3);
        g.addEdge(v3, v1);
        g.addEdge(v4, v3);

        // position vertices nicely within JGraph component
        positionVertexAt(v1, 130, 40);
        positionVertexAt(v2, 60, 200);
        positionVertexAt(v3, 310, 230);
        positionVertexAt(v4, 380, 70);

        // that's all there is to it!...**/
    }

    private void adjustDisplaySettings(org.jgraph.JGraph jg)
    {
        jg.setPreferredSize(DEFAULT_SIZE);

        Color c = DEFAULT_BG_COLOR;
        String colorStr = null;

        try {
            colorStr = getParameter("bgcolor");
        } catch (Exception e) {
        }

        if (colorStr != null) {
            c = Color.decode(colorStr);
        }

        jg.setBackground(c);
    }

    @SuppressWarnings("unchecked") // FIXME hb 28-nov-05: See FIXME below
    private void positionVertexAt(Object vertex, int x, int y)
    {
        DefaultGraphCell cell = jgAdapter.getVertexCell(vertex);
        AttributeMap attr = cell.getAttributes();
        Rectangle2D bounds = GraphConstants.getBounds(attr);

        Rectangle2D newBounds =
            new Rectangle2D.Double(
                x,
                y,
                bounds.getWidth(),
                bounds.getHeight());

        GraphConstants.setBounds(attr, newBounds);

        // TODO: Clean up generics once JGraph goes generic
        AttributeMap cellAttr = new AttributeMap();
        cellAttr.put(cell, attr);
        jgAdapter.edit(cellAttr, null, null, null);
    }

    

    /**
     * a listenable directed multigraph that allows loops and parallel edges.
     */
    private static class ListenableDirectedMultigraph<V, E>
        extends DefaultListenableGraph<V, E>
        implements DirectedGraph<V, E>
    {
        private static final long serialVersionUID = 1L;

        ListenableDirectedMultigraph(Class<E> edgeClass)
        {
            super(new DirectedMultigraph<V, E>(edgeClass));
        }
    }
}

