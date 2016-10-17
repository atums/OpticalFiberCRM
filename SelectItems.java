import javax.swing.*;

import java.awt.event.*;
import java.awt.*;
//SelectItems.hmEku
//Тут в принципе все просто - заглавная страница в которую по средствал toolBar будем загружать моделируемые интерфейсы.

public class SelectItems {
	
	static int hmEku;
	private int i;
	JFrame mainFrame;
	JLabel cLabel;
	JPanel mainPanel;
	JToolBar toolBar;	
	String headLine;
	JButton[] button = new JButton[8];
	DialogWindows dialogEku;
	AddSus aSus;
	private String[] nameButton = {"Add СУС", "Add ПОК", "Show СУС", "Show ПОК", "Modify СУС", "Modify ПОК", "Create ЕОС", "Show GFL"};
	
	public static void main(String[] args) {
		SelectItems gui = new SelectItems();
		gui.guiPanel();
	}
	
	public void gethmEku() {
		System.out.println("Метод gethmEku()");
		goMore();
	}
	
	public void guiPanel() {
		headLine = ("<html>К этой метке применено " +
				"HTML-форматирование, включая: <ul><li> <i>курсив</i>," +
				"<li><b>полужирный</b> <li><font size = +2> увеличение размера </font>" +
				"<li>маркированный список </ul></html>");
		
		mainFrame = new JFrame("OpticalFiber CRM");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainPanel = new JPanel();
		cLabel = new JLabel(headLine, SwingConstants.CENTER);
		
		// Создаем массив классов JButton, чтобы не плодить кучу переменных экземпляра класса JButton
		for( i = 0; i < 8; i++){
		   button[i] = new JButton(nameButton[i]);  // Из архива nameButton присваеваем имена кнопкам соответственно i 
		}

		toolBar = new JToolBar("Tool Panel");
			// Тут в цикле добавляем кнопки на наш toolBar 
			for( i = 0; i < 8; i++){
				toolBar.add(button[i]);
				// и разделяем их сепаратором в соответствующих местах
				if (i == 1 || i == 3 || i == 5 || i == 6) {
					toolBar.addSeparator();
				}
			}
		
		mainPanel.add(cLabel);
		// ТУТ ПОдумать как назначить слушателей на кнопки в цикле
		button[0].addActionListener(new B1Listener());
		//button[1].addActionListener(new B2Listener());
		//button[2].addActionListener(new B3Listener());
		//button[3].addActionListener(new B4Listener());
		//button[4].addActionListener(new B5Listener());
		//button[5].addActionListener(new B6Listener());
		//button[6].addActionListener(new B7Listener());
		//button[7].addActionListener(new B8Listener());
		
		mainFrame.getContentPane().add(BorderLayout.CENTER, mainPanel);
		mainFrame.getContentPane().add(BorderLayout.NORTH, toolBar);
		mainFrame.setSize(800, 600);
		mainFrame.setVisible(true);		
	}
	

	public void go(){
		try {
			System.out.println("Metod 'go' start!");
			DialogWindows dialogEku = new DialogWindows(); 
			Thread one = new Thread(dialogEku);
			one.setName("one");
			one.start();
			//one.sleep(2000);
			//one.join();
			System.out.println("Metod 'go' stop!");
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
	}

	
	/*public void go() {
		System.out.println("Обрабатываем диалогвое окно");
		dialogEku = new DialogWindows();
		dialogEku.matchEku();
		System.out.println("Принимаем переменную в кол-ва строк:"+hmEku);
		System.out.println("Вызываем перерисовку окна");
		goMore();
	}*/
	
	public void goMore() {
		
		System.out.println("Metod 'goMore' start!");
		//hmEku = dialogEku.match;
		System.out.println("hmEku = "+hmEku);
		aSus = new AddSus();
		System.out.println("Запускаем перерисовку окна");
		mainPanel.removeAll();
		mainPanel.add(aSus.addPanel());
		mainPanel.validate();
		mainPanel.repaint();
		System.out.println("Metod 'goMore' stop!");
	}
	

	public class B1Listener implements ActionListener {	
	
		public void actionPerformed(ActionEvent ev) {

			go();
			//goMore();
			
			}
		}

		
	
	

	
}


