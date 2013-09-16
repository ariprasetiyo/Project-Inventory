/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package programinvetory;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyVetoException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

//import programinvetory.FrameLogin;
/**
 *
 * @author LANTAI3
 */
public class ProgramInventory extends javax.swing.JFrame {

    /**
     * Creates new form ProgramInventory
     */
 
        //asa.MenuFile.setEnabled(true);\
    private FrameLogin aa= new FrameLogin();
    //form_purchaseorder PO = new form_purchaseorder();
    //private SistemPro.SetAwalPro AwalPo = new SistemPro.SetAwalPro();
    //private form_purchaseorderOpen Bk = new form_purchaseorderOpen();
    public String Ambil = null;
    public JDesktopPane desktop = new JDesktopPane();  
  
    public ProgramInventory() {
        initComponents();
        //desktop.setDesktopManager(new SampleDesktopMgr());
        setContentPane( desktop );
        
        aa.ButtonLogin.addActionListener(new ActionListener (){
            @Override
              public void actionPerformed (ActionEvent e) {
                  String s = e.getActionCommand();
                  if (s.equals("Login")) {
                        ProgramInventory ProInventory = new ProgramInventory();
                         CloseAtauTidak(aa.TextUser.getText(),  aa.TextPass.getText());
                  }
              }
        });
        aa.TextUser.addKeyListener(new TombolLogin());
        aa.TextPass.addKeyListener(new TombolLogin());
        aa.setVisible(true);
        this.add(aa);
        
        /*
         * Bagian menu jika di klik
         */
        
        MenuPurchaseOrder.addActionListener(new ActionListener (){
            @Override
              public void actionPerformed (ActionEvent e) {
                  String s = e.getActionCommand();
                  if (s.equals("Purchase Order")) {
                        form_purchaseorder PO = new form_purchaseorder();
                        setLayout(null);
                        
                        /*
                         * menghapus data tabel di form_purchaseorder
                         */
                        
                        PO.AturanMenu (false);
                        PO.TabelModelOrder.getDataVector().removeAllElements();
                        PO.TabelModelOrder.fireTableDataChanged();
                        PO.setVisible(true);  
                        //MoveToFront(PO);
                        //moveToFront(PO);
                        add(PO);
                        try {
                        /*
                         * biar form berada di depan 
                         * JInternalFrame.moveToFront + JInternalFrame.setSelected(true)
                         */
                        PO.setSelected(true);
                        PO.moveToFront();
                    } catch (PropertyVetoException ex) {
                        Logger.getLogger(ProgramInventory.class.getName()).log(Level.SEVERE, null, ex);
                    }
                        
//                        add(PO);
                  }
              }
        });
        
        MenuAdministrator.addActionListener(new ActionListener(){
            public void actionPerformed (ActionEvent A){
                String Data = A.getActionCommand();
                if ( Data.equals("User Administrator")){
                    sys_UserAdministrator UA = new sys_UserAdministrator();
                    setLayout(null);
                    UA.setVisible(true);
                    add(UA);
                    try {
                        /*
                         * biar form berada di depan 
                         * JInternalFrame.moveToFront + JInternalFrame.setSelected(true)
                         */
                        UA.setSelected(true);
                        UA.moveToFront();
                    } catch (PropertyVetoException ex) {
                        Logger.getLogger(ProgramInventory.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
            }
        });
        /*
         * END
         */
        //this.add(A);
        //this.add(PO);
        //JOptionPane.showMessageDialog(null, PO.GetOpenPo());
        //Bk.setVisible(true);
        /*
         * Full frame layar
         */
        Dimension Dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(Dim.width, Dim.height);
        //add(jPanel1, BorderLayout.SOUTH);
        
        /*
         * Menampilkan status bar
         */
        
        //Container contentPane = this.getContentPane();
        
        //add(contentPane);
        //StatusBar statusBar = new StatusBar();
        
        //add(statusBar, BorderLayout.SOUTH);
        
        //setLayout(new BorderLayout());
         desktop.setLayout(new BorderLayout(100,100));
        setJStatusBar(JPanelStatusBar); 
           
         
            //desktop.add(JPanelStatusBar, BorderLayout.SOUTH);
      
        //getContentPane().add(JPanelStatusBar, BorderLayout.SOUTH);
        
        //StatusBar statusBar = new StatusBar();
        //desktop.add(statusBar, BorderLayout.SOUTH);
       //this.setLayeredPane(jPanel1);
    }
    private void setJStatusBar(JPanel statusbar) {
                add(statusbar,BorderLayout.SOUTH);
        }
    /*
     * Kejadian Login dari framelogin
     */
    int a = 3 ;
    public void CloseAtauTidak(String DapatUser, String DapatPass) {
                   //FrameLogin aa= new FrameLogin();
                   if ("admin".equals(DapatUser) && "admin".equals(DapatPass)){
                       //ProgramInventory ProInventory = new ProgramInventory();
                       
                       MenuFile.setEnabled(true);
                       MenuInventory.setEnabled(true);
                       MenuReport.setEnabled(true);
                       MenuUtility.setEnabled(true);
                       MenuSetup.setEnabled(true);
                       aa.setVisible(false);
                      
                       JLabelHostName.setText(HostName());
                       JLabelIPLocal.setText(IPLocal());
                       JLabelIPPublic.setText(IPPublic());
                       JLabelUserName.setText("admin");
                       JLabelOS.setText(OSName());
                       
                       //System.out.print(OSName() + " " + HostName() + " " + IPLocal() + " " + IPPublic());
                   }
                   else if ( (!"admin".equals(DapatUser) ) || (!"admin".equals(DapatPass))){
                        a = a - 1;
                        JOptionPane.showMessageDialog (null, "Maaf, user atau password salah !! ", "Error", JOptionPane.ERROR_MESSAGE);
                        //LabelPeringatan.setText("User/Password salah !! ( " + a + " )" );
                        if (a == 0){
                            //FrameNotClose.setVisible(rootPaneCheckingEnabled);
                             //initComponents().setVisible(false);
                            
                            System.exit(0);
                        }
                  } 
    }
     class TombolLogin implements KeyListener {
        //FrameLogin aa= new FrameLogin();
         @Override
        public void keyPressed(KeyEvent e) {
                   //frame_popup.setVisible(true);
                    //System.out.println(e);
                    String s = KeyEvent.getKeyText(e.getKeyCode());

                    if (s.equals("Enter")){
                       ProgramInventory ProInventory = new ProgramInventory();
                       CloseAtauTidak( aa.TextUser.getText(),  aa.TextPass.getText());
                    }
            }
         @Override
            public void keyTyped(KeyEvent ke) {
                //throw new UnsupportedOperationException("Not supported yet.");
            }
         @Override
            public void keyReleased(KeyEvent ke) {
               // throw new UnsupportedOperationException("Not supported yet.");
            }
    }
     
     /*
      * Medapatkan versi OS
      */
     String OS;
     public String OSName (){
         OS= System.getProperty("os.name").toLowerCase()
                 +" ("+System.getProperty("os.arch").toLowerCase()
                 +") build "+System.getProperty("os.version").toLowerCase();
         return OS;
     }
     /*
      * Mendapatkan HostName
      */
     String  Host;
     public String HostName (){
         InetAddress IpLocal;
         try {
            IpLocal = InetAddress.getLocalHost();
            Host = IpLocal.getHostName();
            return Host;
        } catch (UnknownHostException ex) {
            Logger.getLogger(ProgramInventory.class.getName()).log(Level.SEVERE, null, ex);
        }
         return Host;
     }
     /*
      * Mendapatkan iplocal
      */
     String  IPLocal;
     public String IPLocal (){
         InetAddress IpLocal;
         try {
            IpLocal = InetAddress.getLocalHost();
            IPLocal = IpLocal.getHostAddress();
            return IPLocal;
        } catch (UnknownHostException ex) {
            Logger.getLogger(ProgramInventory.class.getName()).log(Level.SEVERE, null, ex);
        }
         return IPLocal;
     }
     /*
      * Mendapatkan ippublic
      */
     String IPPublik;
     public String IPPublic () {
        InetAddress IpLocal = null;
        String IpLocalData;
        try {
            // Mendeteksi Ip Public
            URL url= new URL("http://gt-tests.appspot.com/ip");
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            IPPublik = in.readLine();
            //System.out.print(ip);
            return IPPublik;
        } catch (IOException ex) {
            Logger.getLogger(ProgramInventory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return IPPublik;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JPanelStatusBar = new javax.swing.JPanel();
        JPanelHostName = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        JLabelHostName = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        JPanelIPLocal = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        JLabelIPLocal = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        JPanelIPPublic = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        JLabelIPPublic = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        JPanelUserName = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        JLabelUserName = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        jPanel8 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        JLabelDatabase = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        jPanel5 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        HostName3 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jPanel9 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        JLabelServer = new javax.swing.JLabel();
        jSeparator8 = new javax.swing.JSeparator();
        jPanel10 = new javax.swing.JPanel();
        JLabelOS = new javax.swing.JLabel();
        jSeparator9 = new javax.swing.JSeparator();
        jMenuBar1 = new javax.swing.JMenuBar();
        MenuFile = new javax.swing.JMenu();
        MenuLogOut = new javax.swing.JMenuItem();
        MenuExit = new javax.swing.JMenuItem();
        MenuInventory = new javax.swing.JMenu();
        MenuPurchase = new javax.swing.JMenu();
        MenuPurchaseOrder = new javax.swing.JMenuItem();
        MenuPenerimaanBarang = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenuItem12 = new javax.swing.JMenuItem();
        MenuReport = new javax.swing.JMenu();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();
        MenuUtility = new javax.swing.JMenu();
        jMenu9 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem13 = new javax.swing.JMenuItem();
        MenuSetup = new javax.swing.JMenu();
        jMenu10 = new javax.swing.JMenu();
        jMenuItem14 = new javax.swing.JMenuItem();
        MenuAdministrator = new javax.swing.JMenuItem();
        jMenuItem16 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Program Inventory BSP");
        getContentPane().setLayout(null);

        JPanelStatusBar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel3.setText("  Hostname :");

        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);

        javax.swing.GroupLayout JPanelHostNameLayout = new javax.swing.GroupLayout(JPanelHostName);
        JPanelHostName.setLayout(JPanelHostNameLayout);
        JPanelHostNameLayout.setHorizontalGroup(
            JPanelHostNameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPanelHostNameLayout.createSequentialGroup()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JLabelHostName, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        JPanelHostNameLayout.setVerticalGroup(
            JPanelHostNameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPanelHostNameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel3)
                .addComponent(JLabelHostName, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jLabel1.setText("  IP Local :");

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        javax.swing.GroupLayout JPanelIPLocalLayout = new javax.swing.GroupLayout(JPanelIPLocal);
        JPanelIPLocal.setLayout(JPanelIPLocalLayout);
        JPanelIPLocalLayout.setHorizontalGroup(
            JPanelIPLocalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPanelIPLocalLayout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JLabelIPLocal, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        JPanelIPLocalLayout.setVerticalGroup(
            JPanelIPLocalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPanelIPLocalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel1)
                .addComponent(JLabelIPLocal, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jLabel5.setText("IP Public :");

        jSeparator5.setOrientation(javax.swing.SwingConstants.VERTICAL);

        javax.swing.GroupLayout JPanelIPPublicLayout = new javax.swing.GroupLayout(JPanelIPPublic);
        JPanelIPPublic.setLayout(JPanelIPPublicLayout);
        JPanelIPPublicLayout.setHorizontalGroup(
            JPanelIPPublicLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPanelIPPublicLayout.createSequentialGroup()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JLabelIPPublic, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        JPanelIPPublicLayout.setVerticalGroup(
            JPanelIPPublicLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPanelIPPublicLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel5)
                .addComponent(JLabelIPPublic, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jSeparator5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jLabel6.setText("  User Name :");

        jSeparator6.setOrientation(javax.swing.SwingConstants.VERTICAL);

        javax.swing.GroupLayout JPanelUserNameLayout = new javax.swing.GroupLayout(JPanelUserName);
        JPanelUserName.setLayout(JPanelUserNameLayout);
        JPanelUserNameLayout.setHorizontalGroup(
            JPanelUserNameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPanelUserNameLayout.createSequentialGroup()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JLabelUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        JPanelUserNameLayout.setVerticalGroup(
            JPanelUserNameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPanelUserNameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel6)
                .addComponent(JLabelUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jSeparator6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jLabel7.setText("  Database :");

        jSeparator7.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel4.setText("  ");

        jSeparator4.setOrientation(javax.swing.SwingConstants.VERTICAL);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(HostName3, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel4)
                .addComponent(HostName3, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jSeparator4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JLabelDatabase, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel7)
                .addComponent(JLabelDatabase, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jSeparator7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jLabel8.setText("  Server :");

        jSeparator8.setOrientation(javax.swing.SwingConstants.VERTICAL);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JLabelServer, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel8)
                .addComponent(JLabelServer, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jSeparator8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jSeparator9.setOrientation(javax.swing.SwingConstants.VERTICAL);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JLabelOS, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JLabelOS, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jSeparator9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout JPanelStatusBarLayout = new javax.swing.GroupLayout(JPanelStatusBar);
        JPanelStatusBar.setLayout(JPanelStatusBarLayout);
        JPanelStatusBarLayout.setHorizontalGroup(
            JPanelStatusBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPanelStatusBarLayout.createSequentialGroup()
                .addComponent(JPanelHostName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(JPanelIPLocal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(JPanelIPPublic, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(JPanelUserName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        JPanelStatusBarLayout.setVerticalGroup(
            JPanelStatusBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JPanelHostName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(JPanelIPLocal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(JPanelIPPublic, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(JPanelUserName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jPanel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jPanel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        getContentPane().add(JPanelStatusBar);
        JPanelStatusBar.setBounds(0, 276, 1380, 18);

        MenuFile.setText("File");
        MenuFile.setEnabled(false);

        MenuLogOut.setText("Log out");
        MenuLogOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuLogOutActionPerformed(evt);
            }
        });
        MenuFile.add(MenuLogOut);

        MenuExit.setText("Exit");
        MenuFile.add(MenuExit);

        jMenuBar1.add(MenuFile);

        MenuInventory.setText("Inventory");
        MenuInventory.setEnabled(false);

        MenuPurchase.setText("Purchase");

        MenuPurchaseOrder.setText("Purchase Order");
        MenuPurchaseOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuPurchaseOrderActionPerformed(evt);
            }
        });
        MenuPurchase.add(MenuPurchaseOrder);

        MenuPenerimaanBarang.setText("Penerimaan Barang");
        MenuPenerimaanBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuPenerimaanBarangActionPerformed(evt);
            }
        });
        MenuPurchase.add(MenuPenerimaanBarang);

        MenuInventory.add(MenuPurchase);

        jMenu5.setText("Peminjaman");

        jMenuItem7.setText("Pengeluaran Pinjam");
        jMenu5.add(jMenuItem7);

        jMenuItem8.setText("Pengembaliaan");
        jMenu5.add(jMenuItem8);

        MenuInventory.add(jMenu5);

        jMenu6.setText("Pengeluaran Barang");

        jMenuItem11.setText("Pengeluaran");
        jMenu6.add(jMenuItem11);

        jMenuItem12.setText("Surat Jalan");
        jMenu6.add(jMenuItem12);

        MenuInventory.add(jMenu6);

        jMenuBar1.add(MenuInventory);

        MenuReport.setText("Report");
        MenuReport.setEnabled(false);

        jMenuItem9.setText("Stock Movement IT");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        MenuReport.add(jMenuItem9);

        jMenuItem10.setText("Report Peminjaman");
        MenuReport.add(jMenuItem10);

        jMenuBar1.add(MenuReport);

        MenuUtility.setText("Utility");
        MenuUtility.setEnabled(false);

        jMenu9.setText("Periode");

        jMenuItem4.setText("Open Periode");
        jMenu9.add(jMenuItem4);

        jMenuItem13.setText("Close Periode");
        jMenu9.add(jMenuItem13);

        MenuUtility.add(jMenu9);

        jMenuBar1.add(MenuUtility);

        MenuSetup.setText("Setup");
        MenuSetup.setEnabled(false);

        jMenu10.setText("Setup Program");

        jMenuItem14.setText("Program Parameter");
        jMenu10.add(jMenuItem14);

        MenuSetup.add(jMenu10);

        MenuAdministrator.setText("User Administrator");
        MenuSetup.add(MenuAdministrator);

        jMenuItem16.setText("Setup Iventory");
        jMenuItem16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem16ActionPerformed(evt);
            }
        });
        MenuSetup.add(jMenuItem16);

        jMenuBar1.add(MenuSetup);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void MenuLogOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuLogOutActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MenuLogOutActionPerformed

    private void MenuPurchaseOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuPurchaseOrderActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MenuPurchaseOrderActionPerformed

    private void MenuPenerimaanBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuPenerimaanBarangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MenuPenerimaanBarangActionPerformed

    private void jMenuItem16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem16ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem16ActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ProgramInventory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ProgramInventory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ProgramInventory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ProgramInventory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
                ProgramInventory Form_Utama= new ProgramInventory();
                //code dibawah ini yang harus ditambahkan sebelum pemanggilan form.
                Form_Utama.setExtendedState(JFrame.MAXIMIZED_BOTH);
                
                Form_Utama.setVisible(true);
            }
        });
    }
    
/*
 * Membuat Status Bar
 */
class StatusBar extends JPanel {

  public StatusBar() {
    setLayout(new BorderLayout());
    setPreferredSize(new Dimension(10, 23));

    //JPanel rightPanel = new JPanel(new BorderLayout());
    //rightPanel.add(new JLabel(new AngledLinesWindowsCornerIcon()), BorderLayout.SOUTH);
    //rightPanel.setOpaque(false);
    
//***    add(JPanelStatusBar,  BorderLayout.WEST);
    //add(cc);
    /*
     * Mengaktivkan tema statusbar yang lebih baik
     */
    //add(rightPanel, BorderLayout.EAST);
    setBackground(SystemColor.control);
  }

  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    int y = 0;
    g.setColor(new Color(156, 154, 140));
    g.drawLine(0, y, getWidth(), y);
    y++;
    g.setColor(new Color(196, 194, 183));
    g.drawLine(0, y, getWidth(), y);
    y++;
    g.setColor(new Color(218, 215, 201));
    g.drawLine(0, y, getWidth(), y);
    y++;
    g.setColor(new Color(233, 231, 217));
    g.drawLine(0, y, getWidth(), y);

    y = getHeight() - 3;
    g.setColor(new Color(233, 232, 218));
    g.drawLine(0, y, getWidth(), y);
    y++;
    g.setColor(new Color(233, 231, 216));
    g.drawLine(0, y, getWidth(), y);
    y = getHeight() - 1;
    g.setColor(new Color(221, 221, 220));
    g.drawLine(0, y, getWidth(), y);

  }

}

class AngledLinesWindowsCornerIcon implements Icon {
  private  final Color WHITE_LINE_COLOR = new Color(255, 255, 255);

  private  final Color GRAY_LINE_COLOR = new Color(172, 168, 153);
  private static final int WIDTH = 13;

  private static final int HEIGHT = 13;

  public int getIconHeight() {
    return WIDTH;
  }

  public int getIconWidth() {
    return HEIGHT;
  }

  public void paintIcon(Component c, Graphics g, int x, int y) {

    g.setColor(WHITE_LINE_COLOR);
    g.drawLine(0, 12, 12, 0);
    g.drawLine(5, 12, 12, 5);
    g.drawLine(10, 12, 12, 10);

    g.setColor(GRAY_LINE_COLOR);
    g.drawLine(1, 12, 12, 1);
    g.drawLine(2, 12, 12, 2);
    g.drawLine(3, 12, 12, 3);

    g.drawLine(6, 12, 12, 6);
    g.drawLine(7, 12, 12, 7);
    g.drawLine(8, 12, 12, 8);

    g.drawLine(11, 12, 12, 11);
    g.drawLine(12, 12, 12, 12);

  }
}

/*
 * END
 */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel HostName3;
    private javax.swing.JLabel JLabelDatabase;
    private javax.swing.JLabel JLabelHostName;
    private javax.swing.JLabel JLabelIPLocal;
    private javax.swing.JLabel JLabelIPPublic;
    private javax.swing.JLabel JLabelOS;
    private javax.swing.JLabel JLabelServer;
    private javax.swing.JLabel JLabelUserName;
    private javax.swing.JPanel JPanelHostName;
    private javax.swing.JPanel JPanelIPLocal;
    private javax.swing.JPanel JPanelIPPublic;
    private javax.swing.JPanel JPanelStatusBar;
    private javax.swing.JPanel JPanelUserName;
    private javax.swing.JMenuItem MenuAdministrator;
    private javax.swing.JMenuItem MenuExit;
    public javax.swing.JMenu MenuFile;
    public javax.swing.JMenu MenuInventory;
    private javax.swing.JMenuItem MenuLogOut;
    private javax.swing.JMenuItem MenuPenerimaanBarang;
    private javax.swing.JMenu MenuPurchase;
    private javax.swing.JMenuItem MenuPurchaseOrder;
    public javax.swing.JMenu MenuReport;
    public javax.swing.JMenu MenuSetup;
    public javax.swing.JMenu MenuUtility;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JMenu jMenu10;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu9;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem14;
    private javax.swing.JMenuItem jMenuItem16;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    // End of variables declaration//GEN-END:variables
}
