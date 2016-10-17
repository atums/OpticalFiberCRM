import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.*;
import java.awt.*;

//����� �������� �� ���������� ��������� ������ �� ��� : ��������, ���-�� �������, ������ ������.
public class AddSus {
	
	int horizontalBox = SelectItems.hmEku;
	private int i;
	JTextField susName;
	JTextField[] arrEku = new JTextField[horizontalBox];
	JTextField[] arrCable = new JTextField[horizontalBox];
	JTextField[] arrLength = new JTextField[horizontalBox];
	Box[] boxX = new Box[horizontalBox];
	String[][] dataSus = new String[3][horizontalBox];
		
	Sus su = new Sus();
// ����������� ������� ����� - ����������� ���� � �������� �� ���-�� ���
	public JPanel addPanel() {
		//horizontalBox = hB;
		JPanel panel = new JPanel();
		
		for (i = 0; i < horizontalBox; i++) {
			arrEku[i] = new JTextField();
			arrCable[i] = new JTextField();
			arrLength[i] = new JTextField();
		}

		// ����������� �������������� ������ ��� ����� ���
		Box box1 = Box.createHorizontalBox();
		JLabel susNameLabel = new JLabel("�������� ��� :");
		susName = new JTextField(35);
		box1.add(susNameLabel);
		box1.add(Box.createHorizontalStrut(6));
		box1.add(susName);
		
		for( i = 0; i < horizontalBox; i++){
		// ����������� ����� �������������� ������ ��� ����� ���
			boxX[i] = Box.createHorizontalBox();
			JLabel ekuLabel = new JLabel("               ��� #"+(i+1)+":");
			JLabel ekuLabelCable = new JLabel("Fibres");
			JLabel ekuLabelLength = new JLabel("Length");
			arrEku[i] = new JTextField("", 15);
			arrCable[i] = new JTextField("0", 3);
			arrLength[i] = new JTextField("0", 3);
			boxX[i].add(ekuLabel);
			boxX[i].add(Box.createHorizontalStrut(6));
			boxX[i].add(arrEku[i]);
			boxX[i].add(ekuLabelCable);
			boxX[i].add(arrCable[i]);
			boxX[i].add(ekuLabelLength);
			boxX[i].add(arrLength[i]);
		}
			
		// ����������� �������������� ������ � ��������
		Box box8 = Box.createHorizontalBox();
		JButton ok = new JButton("OK");
		JButton cancel = new JButton("������");
		box8.add(Box.createHorizontalGlue());
		box8.add(ok);
		box8.add(Box.createHorizontalStrut(12));
		box8.add(cancel);
		
		// ��������� �������������� ������ �� ����� ������������
		Box mainBox = Box.createVerticalBox();
		mainBox.setBorder(new EmptyBorder(20,12,12,12));
		mainBox.add(box1);
		mainBox.add(Box.createVerticalStrut(25));
		
		for(i = 0; i < horizontalBox; i++) {
			mainBox.add(boxX[i]);
			mainBox.add(Box.createVerticalStrut(17));
		}
		
		mainBox.add(box8);
		panel.add(BorderLayout.WEST, mainBox);
		
		ok.addActionListener(new OkListener());
		cancel.addActionListener(new CancelListener());	
		return panel;
	}

	public void saveSus() {		
		//��� �������� ������ ���� � �������� � Sus ��� �������� "���"		
		su.setSus(susName.getText());		
	}
	
	public void saveEku() {		
		//��� �������� ������ ����� � �������� � Sus ��� �������� ��� ���������� ������� ���		
		for(i = 0; i < horizontalBox; i++) {		
				dataSus[0][i] = arrEku[i].getText();
				dataSus[1][i] = arrCable[i].getText();
				dataSus[2][i] = arrLength[i].getText();	
		}	
		su.setEku(dataSus);
	}	
	public class OkListener implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
		//�������� ������ ��� ����������
		saveSus();
		saveEku();		
		}
	}
	
	public class CancelListener implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			//��� �������� ��� ����
			susName.setText("");	
			for(i = 0; i < horizontalBox; i++) {
				arrEku[i].setText("");
				arrCable[i].setText("");
				arrLength[i].setText("");
			}
		}
	}
}
