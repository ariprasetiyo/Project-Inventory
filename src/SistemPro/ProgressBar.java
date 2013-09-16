/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemPro;

import java.util.List;
import javax.swing.JButton;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;

/**
 *
 * @author LANTAI3
 */

public class ProgressBar extends SwingWorker<Void, Integer>{
    /**
       * Constructs the simulated activity that increments a counter from 0 to a
       * given target.
       * @param t the target value of the counter.
       */
      ProgressBar activity;
      public ProgressBar(int t)
      {
         current = 0;
         target = t;
      }

      protected Void doInBackground() throws Exception
      {
         try
         {
            while (current < target)
            {
               Thread.sleep(100);
               current++;
               publish(current);
            }
         }
         catch (InterruptedException e)
         {
         }
         return null;
      }

      public void process(List<Integer> chunks, JProgressBar progressBar)
      {
         for (Integer chunk : chunks)
         {
            //textArea.append(chunk + "\n");
            progressBar.setValue(chunk);
         }
      }
      
      public void done(JButton startButton)
      {
         startButton.setEnabled(true);
      }
      
      private int current;
      private int target;
   }

