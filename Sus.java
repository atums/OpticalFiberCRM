// В данном классе будем сохранять архив в файл( вынесено в отдельный класс для того, чтобы можно было сохранять в БД или разбирать архив на лету.) 
import java.io.*;

public class Sus {
	
	private int cableNum;
	private int modulNum;
	private int i;
	private int j;
	private int a;
	private boolean save;// используем состояние переменной для понимания есть такая СУС или ещё нет
	//private int hB = SelectItems.hmEku;
	String name;
	String nameEkuFile;
	String pathTwo;
	String pathThree; //Это у нас путь к файлу с описанием СУС
	String pathFour;
	File fileNew; //Это собственно наш файлик с певичным описанием СУС
	File fileNewEku;
	String[][] archEku;
	PrintWriter writerEku;
	
	
	public void setSus (String eN) {
		name = eN;

		String path = new File("").getAbsolutePath();
		pathTwo = (path+"\\Base\\"+name+"\\");
		pathThree = (pathTwo+"aSystem.sus");
		
		File dir = new File(pathTwo);
		dir.mkdirs();
		
		fileNew = new File(pathThree);
		
		try {
	        //проверяем, что если файл не существует то создаем его
	        if(!fileNew.exists()){
	            fileNew.createNewFile();
	            System.out.println("newFile");
	            save = true;
	        } else {
	        	//Тут необходимо вернуть сообщение о существующем файле !!! И поинтересоваться о замене!!!!
	        	System.out.println("Just");
	        	save = false;
	        }
		} catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public void setEku (String[][] arch) {
		//Сеттер для данных по СУС
		archEku = arch;
		
		if( save == true) {
			saveF();
			System.out.println("Cохраняем");
		} else {
			dialogWindow(); // Вызываем диалоговое окно в вопросом о пересохранении.
		}
	}
	
	public void saveF() {
		// Метод поверяет занные и сохраняет системный файл.
		try {
			PrintWriter writerSus = new PrintWriter(fileNew);
			writerSus.write(name+"\n");
			for(i = 0; i < SelectItems.hmEku; i++) {
				for(j = 0; j < 3; j++) {
					writerSus.write(archEku[j][i]);
					if(j != 2) {
						writerSus.write("/");
					}
				}
				writerSus.write("\n");
			}
			writerSus.close();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		
		saveEku();

	}
	// Делаем метод для заполнения таблиц ЕКУ
	public void saveEku() {
		
		try{
			for(a = 0; a < SelectItems.hmEku; a++) {
				if(archEku[0][a] .equals("")) { 
					System.out.println("Пропускаем");
				} else {
				System.out.println(archEku[0][a]);
				nameEkuFile = archEku[0][a];
				
				pathFour = (pathTwo+nameEkuFile+".eku");

				fileNewEku = new File(pathFour);
				fileNewEku.createNewFile();
				//Дальше нам надо заполнить файлы ЕКУ относительно их кабельных свойств (кол-во волокон) будем делать через ветвление относительно емкости кабеля
				cableNum = Integer.parseInt(archEku[1][a]);
				if(cableNum >= 48) {
					createEku48_316();
				}
				createEku4_24();
				}
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void createEku48_316() {
		//Наполняем для кабелей 48 - 316 (т.е. кабели кратные 12 волокон в трубке)
		try {
			writerEku = new PrintWriter(fileNewEku);
			System.out.println(cableNum);
			modulNum = (cableNum/12)+1;
			for(i = 1; i < modulNum; i++) {
				for(j = 1; j < 13; j++) {
					if(j < 10) {
						writerEku.write(i+"/"+"0"+j+"\n");
					} else {
						writerEku.write(i+"/"+j+"\n");
					}
				}
			}
				
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		writerEku.close();
		return;
	}
	
	public void createEku4_24() {
		//Наполняем для кабелей 4 - 24 (т.е. кабели НЕ кратные 12 волокон в трубке)
		try {
			System.out.println(cableNum);
			
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}	
		
	public void dialogWindow() {
		// вызываем диалоговое окно с вопросом о пересохранении CУС, в случае подтверждения вызываем saveF(), 
		//ПОКА НЕ ИСПОЛЬЗУЕМ ЭТОТ МЕТОД, Т.К. НЕ ПОНЯТНО, ЧТО ДЕЛАТЬ ПРИ ПЕРЕЗАПИСИ С УЖЕ ОПИСАНЫМИ СВАРКАМИ!!!	
		System.out.println("Ни чего не сохраняем");
	}
}
