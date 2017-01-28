package greentf.trello.gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import greentf.trello.loaders.TrelloKeyLoader;
import greentf.trello.services.UploaderServiceFacade;
import net.miginfocom.swing.MigLayout;
import javax.swing.JProgressBar;

public class MainWindow extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JPanel pnlMain;
	private JPanel pnlStep1Auth;
	private JPanel pnlStep2Tasks;
	private JLabel lblStep1;
	private JPanel panel_2;
	private JLabel lblKey;
	private JTextField txtKey;
	private JLabel lblToken;
	private JTextField txtToken;
	private JButton btnTest;
	private JButton btnNext;
	private JPanel pnlbar;
	private JButton btnPrevious;
	private CardLayout pnlMainCardLayout;
	private JLabel lblStep2;
	private JPanel panel;
	private JButton btnLoadFile;
	private JLabel lblTasks;
	private JLabel lblNewLabel;
	private JLabel lblLblstepdesc;
	private JList listTasks;
	private UploaderServiceFacade usf;
	private File fileToOpen;
	private JPanel pnlStep3Run;
	private JLabel label;
	private JLabel label_1;
	private JButton btnUploadTasks;
	private JPanel panel_1;
	private JProgressBar progressBar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainWindow() 
	{
		this.initModel();
		setTitle("TrelloTaskUploader");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 741, 531);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		this.pnlMain = new JPanel();
		this.contentPane.add(this.pnlMain, BorderLayout.CENTER);
		this.pnlMainCardLayout=new CardLayout(0, 0);
		this.pnlMain.setLayout(this.pnlMainCardLayout);
		
		this.pnlStep1Auth = new JPanel();
		this.pnlStep1Auth.setBackground(Color.WHITE);
		this.pnlMain.add(this.pnlStep1Auth, "name_1125413353783203");
		this.pnlStep1Auth.setLayout(new BorderLayout(0, 0));
		
		this.lblStep1 = new JLabel("Paso 1: Autenticación");
		this.pnlStep1Auth.add(this.lblStep1, BorderLayout.NORTH);
		
		this.panel_2 = new JPanel();
		this.panel_2.setBackground(Color.WHITE);
		this.pnlStep1Auth.add(this.panel_2, BorderLayout.CENTER);
		this.panel_2.setLayout(new MigLayout("", "[42.00][346.00px][386.00px][64.00]", "[173px][26px][26px][29px]"));
		
		this.lblNewLabel = new JLabel("<html><h1>¿Cómo conseguir tu KEY y tu TOKEN de <b>Trello</b></h1><h2>Key</h2><p>El key de trello se puede conseguir entrando a la url <a href=\"https://trello.com/app-key\">https://trello.com/app-key</a></p><h2>Token</h2><p>El token de trello se consigue en la url <a href= \"https://developers.trello.com/authorize\"> https://developers.trello.com/authorize </a></p></html>");
		this.panel_2.add(this.lblNewLabel, "cell 1 0 2 1,growx,aligny center");
		
		this.lblKey = new JLabel("Key");
		this.panel_2.add(this.lblKey, "cell 1 1,grow");
		
		this.txtKey = new JTextField();
		this.panel_2.add(this.txtKey, "cell 2 1,grow");
		this.txtKey.setColumns(2);
		
		this.lblToken = new JLabel("Token");
		this.panel_2.add(this.lblToken, "cell 1 2,grow");
		
		this.txtToken = new JTextField();
		this.panel_2.add(this.txtToken, "cell 2 2,grow");
		this.txtToken.setColumns(10);
		
		this.btnTest = new JButton("Probar");
		this.panel_2.add(this.btnTest, "cell 1 3 2 1,grow");
		this.btnTest.addActionListener(this);
		
		this.pnlStep2Tasks = new JPanel();
		this.pnlStep2Tasks.setBackground(Color.WHITE);
		this.pnlMain.add(this.pnlStep2Tasks, "name_1125413373386031");
		this.pnlStep2Tasks.setLayout(new BorderLayout(0, 0));
		
		this.lblStep2 = new JLabel("Paso 2: Lista de tareas");
		this.pnlStep2Tasks.add(this.lblStep2, BorderLayout.NORTH);
		
		this.panel = new JPanel();
		this.panel.setBackground(Color.WHITE);
		this.pnlStep2Tasks.add(this.panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{17, 552, 0, 0};
		gbl_panel.rowHeights = new int[]{82, 29, 27, 207, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		this.panel.setLayout(gbl_panel);
		
		this.btnLoadFile = new JButton("Cargar Archivo");
		this.btnLoadFile.setSize(new Dimension(200, 50));
		this.btnLoadFile.addActionListener(this);
		
		this.lblLblstepdesc = new JLabel("<html><h1>Archivo de tareas</h1><p>El archivo de tareas es un archivo de tipo csv (Comma separated values). Para que funcione deberá cumplir con la siguiente convención:</p><h3>Equipo,Tablero,NombreTarjeta,plus! @usuariotrello 0/4_Comentario_Comentario,Lista</h3></html>");
		GridBagConstraints gbc_lblLblstepdesc = new GridBagConstraints();
		gbc_lblLblstepdesc.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblLblstepdesc.insets = new Insets(0, 0, 5, 5);
		gbc_lblLblstepdesc.gridx = 1;
		gbc_lblLblstepdesc.gridy = 0;
		this.panel.add(this.lblLblstepdesc, gbc_lblLblstepdesc);
		GridBagConstraints gbc_btnLoadFile = new GridBagConstraints();
		gbc_btnLoadFile.insets = new Insets(0, 0, 5, 5);
		gbc_btnLoadFile.gridx = 1;
		gbc_btnLoadFile.gridy = 1;
		this.panel.add(this.btnLoadFile, gbc_btnLoadFile);
		
		this.lblTasks = new JLabel("Tareas a subir");
		GridBagConstraints gbc_lblTasks = new GridBagConstraints();
		gbc_lblTasks.insets = new Insets(0, 0, 5, 5);
		gbc_lblTasks.anchor = GridBagConstraints.NORTH;
		gbc_lblTasks.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblTasks.gridx = 1;
		gbc_lblTasks.gridy = 2;
		this.panel.add(this.lblTasks, gbc_lblTasks);
		
		this.listTasks = new JList();
		
		GridBagConstraints gbc_listTasks = new GridBagConstraints();
		gbc_listTasks.insets = new Insets(0, 0, 5, 5);
		gbc_listTasks.fill = GridBagConstraints.BOTH;
		gbc_listTasks.gridx = 1;
		gbc_listTasks.gridy = 3;
		this.panel.add(this.listTasks, gbc_listTasks);
		
		this.pnlStep3Run = new JPanel();
		this.pnlStep3Run.setBackground(Color.WHITE);
		this.pnlMain.add(this.pnlStep3Run, "name_1145711585867471");
		this.pnlStep3Run.setLayout(new BorderLayout(0, 0));
		
		this.label = new JLabel("Paso 3: Subir a trello");
		this.pnlStep3Run.add(this.label, BorderLayout.NORTH);
		
		this.btnUploadTasks = new JButton("Subir a Trello");
		this.btnUploadTasks.addActionListener(this);
		this.pnlStep3Run.add(this.btnUploadTasks, BorderLayout.SOUTH);
		
		this.panel_1 = new JPanel();
		this.panel_1.setBackground(Color.WHITE);
		this.pnlStep3Run.add(this.panel_1, BorderLayout.WEST);
		this.panel_1.setLayout(new MigLayout("", "[698.00px]", "[328.00px][35.00]"));
		
		this.label_1 = new JLabel("<html><h1>Subida a trello</h1><p>Si todo fué bien en los pasos anteriores, presione el botón <strong>Subir</strong> y sus tareas estarán en trello en un momento. Recuerde que su usuario debe tener creados previamente los tableros donde se subirán las tarjetas</p></html>");
		this.panel_1.add(this.label_1, "cell 0 0,growx,aligny top");
		this.label_1.setBackground(Color.LIGHT_GRAY);
		
		this.progressBar = new JProgressBar();
		this.panel_1.add(this.progressBar, "cell 0 1,grow");
		
		this.pnlbar = new JPanel();
		this.contentPane.add(this.pnlbar, BorderLayout.SOUTH);
		
		this.btnPrevious = new JButton("Anterior");
		this.btnPrevious.addActionListener(this);
		this.pnlbar.add(this.btnPrevious);
		
		this.btnNext = new JButton("Siguiente");
		this.btnNext.addActionListener(this);
		this.pnlbar.add(this.btnNext);
		
		//
	}

	private void initModel() 
	{
		this.usf= new UploaderServiceFacade();		
	}

	public void actionPerformed(ActionEvent arg0) 
	{
		if (arg0.getSource() == this.btnUploadTasks) {
			do_btnUploadTasks_actionPerformed(arg0);
		}
		if (arg0.getSource() == this.btnLoadFile) {
			do_btnLoadFile_actionPerformed(arg0);
		}
		if (arg0.getSource() == this.btnPrevious) {
			do_btnPrevious_actionPerformed(arg0);
		}
		if (arg0.getSource() == this.btnTest) {
			do_btnTest_actionPerformed(arg0);
		}
		if (arg0.getSource() == this.btnNext) {
			do_btnNext_actionPerformed(arg0);
		}
	}
	protected void do_btnNext_actionPerformed(ActionEvent arg0) 
	{
		this.pnlMainCardLayout.next(this.pnlMain);
		
	}
	protected void do_btnPrevious_actionPerformed(ActionEvent arg0) 
	{
		this.pnlMainCardLayout.previous(this.pnlMain);
	}
	protected void do_btnTest_actionPerformed(ActionEvent arg0) 
	{
		boolean res=this.usf.testAuth(new TrelloKeyLoader(this.txtKey.getText(), this.txtToken.getText()));
		if(res) JOptionPane.showMessageDialog(this,"Autenticación exitosa"); else JOptionPane.showMessageDialog(this,"Error de autenticación");
	}
	protected void do_btnLoadFile_actionPerformed(ActionEvent arg0) 
	{
		JFileChooser fc= new JFileChooser(new File("."));
		int returnVal = fc.showOpenDialog(this);

        if (returnVal == JFileChooser.APPROVE_OPTION) 
        {
            fileToOpen = fc.getSelectedFile();
            System.out.println("Opening: " + fileToOpen.getName() );
            LinkedList<HashMap<String,String>> tasklist=this.usf.getTaskList(fileToOpen.getAbsolutePath());
            DefaultListModel<String> listModel = new DefaultListModel<String>();
            for (HashMap<String, String> task : tasklist) 
            {
				String element = task.get("board")+"->"+task.get("card");
				listModel.addElement(element);
			}
            this.listTasks.setModel(listModel);
        } 
        else 
        {
        	fileToOpen=null;
        	System.out.println("Open command cancelled by user." );
        }
	}
	protected void do_btnUploadTasks_actionPerformed(ActionEvent arg0) 
	{
		this.progressBar.setValue(0);
		if(this.fileToOpen!=null && !this.txtKey.getText().equals("") && !this.txtToken.getText().equals(""))
		{
			TrelloKeyLoader keyLoader = new TrelloKeyLoader(this.txtKey.getText(), this.txtToken.getText());
			boolean res = this.usf.uploadTasks(keyLoader, this.fileToOpen.getAbsolutePath());
			if(res)
			{
				this.progressBar.setValue(100);
				JOptionPane.showMessageDialog(this,"Subida exitosa");
			}
			else
			{
				JOptionPane.showMessageDialog(this,"Fallo en la subida");
			}
		}
		else
		{
			JOptionPane.showMessageDialog(this,"No es posible realizar la acción, compruebe los datos de los pasos anteriores");
		}
		
	}
}
