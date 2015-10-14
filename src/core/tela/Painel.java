package core.tela;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JSeparator;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Toolkit;
import javax.swing.JTextArea;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import java.awt.Window.Type;
import javax.swing.JTextPane;
import javax.swing.DropMode;
import javax.swing.ListSelectionModel;
import javax.swing.AbstractListModel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Painel {

	private JFrame Chat;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Painel window = new Painel();
					window.Chat.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Painel() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		Chat = new JFrame();
		Chat.setTitle("Manga Online");
		Chat.setIconImage(Toolkit.getDefaultToolkit().getImage(Painel.class.getResource("/com/sun/java/swing/plaf/windows/icons/Computer.gif")));
		Chat.setBounds(100, 100, 610, 450);
		Chat.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Chat.getContentPane().setLayout(null);
		
		JLabel lblStatus = new JLabel("Status");
		lblStatus.setBounds(10, 11, 46, 14);
		Chat.getContentPane().add(lblStatus);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 36, 147, 8);
		Chat.getContentPane().add(separator);
		
		JComboBox comboBoxStatus = new JComboBox();
		comboBoxStatus.setModel(new DefaultComboBoxModel(new String[] {"Online", "Offline"}));
		comboBoxStatus.setMaximumRowCount(2);
		comboBoxStatus.setBounds(66, 8, 91, 20);
		Chat.getContentPane().add(comboBoxStatus);
		
		JLabel lblContatos = new JLabel("Contatos");
		lblContatos.setBounds(10, 55, 147, 14);
		Chat.getContentPane().add(lblContatos);
		
		JScrollPane scrollPaneChat = new JScrollPane();
		scrollPaneChat.setBounds(167, 55, 417, 293);
		Chat.getContentPane().add(scrollPaneChat);
		
		JTextArea textAreaChat = new JTextArea();
		textAreaChat.setText("Beatriz\r\nOiii\r\n\r\nClayton\r\nOiii, amor");
		textAreaChat.setLineWrap(true);
		textAreaChat.setEditable(false);
		scrollPaneChat.setViewportView(textAreaChat);
		
		JScrollPane scrollPaneWriting = new JScrollPane();
		scrollPaneWriting.setBounds(167, 359, 344, 42);
		Chat.getContentPane().add(scrollPaneWriting);
		
		JTextArea txtrMessage = new JTextArea();
		txtrMessage.setLineWrap(true);
		scrollPaneWriting.setViewportView(txtrMessage);
		
		JScrollPane scrollPaneContacts = new JScrollPane();
		scrollPaneContacts.setBounds(10, 80, 147, 321);
		Chat.getContentPane().add(scrollPaneContacts);
		
		JList listContatos = new JList();
		listContatos.setModel(new AbstractListModel() {
			String[] values = new String[] {"Beatriz", "Clayton", "Coco", "Xixi"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		listContatos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPaneContacts.setViewportView(listContatos);
		
		JPanel panelContacInfo = new JPanel();
		panelContacInfo.setBounds(167, 11, 417, 33);
		Chat.getContentPane().add(panelContacInfo);
		panelContacInfo.setLayout(null);
		
		JLabel lblNomeDoContato = new JLabel("Nome do contato");
		lblNomeDoContato.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNomeDoContato.setBounds(10, 11, 354, 22);
		panelContacInfo.add(lblNomeDoContato);
		
		JButton btnSend = new JButton("Enviar");
		btnSend.setHorizontalAlignment(SwingConstants.LEFT);
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnSend.setBounds(512, 359, 72, 42);
		Chat.getContentPane().add(btnSend);
	}
}
