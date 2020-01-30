/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demineur;

/**
 *
 * @author kemga
 */
public class MonThread implements Runnable{
    
    private static int cmpt = 0;
    private final Thread t = new Thread();

    public MonThread(Demineur d) {
        t.start();
        d.getPanel().getContenu().getLabelTemps().setText(Integer.toString(cmpt));
        d.getPanel().getContenu().getLabelTemps().revalidate();
    }
    
    @Override
    public void run() {
      for (;;) {
         try {
            cmpt++;
            Thread.sleep(1000);
         } catch (InterruptedException e) {
            System.out.println(e);
         }
      }
   }
    
}
