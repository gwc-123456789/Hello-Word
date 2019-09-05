/*Ŀ�ģ�
 * ��һ���򵥵�������
 * ˼·��
 * Ūһ�ſհ�ͼƬ����һ�����ʻ������ף������̽�����һ�����鴢�棬��������¼���
 * ������е��ǽ��㣬�������ӣ�����¼��������һ��Ĭ�ϰ��壬ż�����壬��������
 * */

package ������;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageFilter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class myplay {
	private static int Cells = 29 * 29;// �����ܸ���
	private static Image hongzi;
	private static Image baizi;
	private static int i = 0;
	static {
		try {
			hongzi = ImageIO.read(new File("���������/i.jpg"));
			baizi = ImageIO.read(new File("���������/q.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) throws IOException, InterruptedException, NoSuchAlgorithmException {	
		chuangkou chuang = new chuangkou();
		JFrame frame = new JFrame();
		frame.setTitle("���������������޻�");
		frame.setSize(617, 640);
		frame.setLocationRelativeTo(null);

		chuang.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				int col = (e.getX() - 10) / 20;
				int row = (e.getY() - 10) / 20;
				if (i < Cells) {
					if (chuang.yuejie(col, row)) {
						if (i % 2 == 0) {
							Cell cell = new Cell(e.getY(), e.getX(),  baizi);
							if (chuang.putqizi(cell))// 0��ʼ��ǰ������У������� (28+1��,3+1��)
							{
								chuang.repaint();
								i++;
								try {
								if (chuang.mangwu(cell)) {// ����false
                                         
									} else {
										System.out.println("������ʤ��");
										System.exit(0);
									}
								} catch (Exception e1) {
									e1.printStackTrace();
								}
							}
						} else {
							Cell cell = new Cell(e.getY(), e.getX(),hongzi);

							if (chuang.putqizi(cell))// 0��ʼ��ǰ������У������� (28+1��,3+1��)
							{
								chuang.repaint();
								i++;
								try {
									if (chuang.mangwu(cell)) {

									} else {
										System.out.println("������ʤ��");
										System.exit(0);
									}
								} catch (Exception e1) {
									e1.printStackTrace();
								}

							}
						}
					}
				} else {
					i = 0;
					System.out.println("ƽ��");
				}
			}

		});

		chuang.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				int key = e.getKeyCode();
				if (key == KeyEvent.VK_Z) {
					chuang.chongwan();
					chuang.repaint();
					i = 0;
				}
			}
		});
		frame.add(chuang);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		Thread.sleep(500);
		chuang.repaint();
		chuang.requestFocus();
	}

}



// ��һ�����Ӷ��󣬼�¼����λ�ã�����ͼƬ
class Cell {
	private int row;
	private int col;
	private Image image;// ���ӵ���ͼ

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public Cell(int row, int col, Image image) {
		this.row = row;
		this.col = col;
		this.image = image;
	}

}

// ������Ϸ����,������
class chuangkou extends JPanel {
	private static Image hongzi;
	private static Image baizi;
	// һ������20����һ�����㣨10,10�� ,29��29��
	static int r = 29, l = 29;
	public static Cell[][] cells = new Cell[r][l];// ��������λ��
	public static Image qipan;
	public static int count = 1;// ӛ����Ӹ���
	public static int count1 = 1;// ӛ����Ӹ���
	public static int count2 = 1;// ӛ����Ӹ���
	public static int count3 = 1;// ӛ����Ӹ���
	public static int count4 = 1;// ӛ����Ӹ���
	public static int count5 = 1;// ӛ����Ӹ���
	public static int count6 = 1;// ӛ����Ӹ���
	public static int count7= 1;// ӛ����Ӹ���
	static {
		try {
			qipan = ImageIO.read(new File("���������/c.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void paint(Graphics g) {
		g.drawImage(qipan, 0, 0, null);// ʹ��this ��Ϊ�۲���,����ͼ
		paintqipan(g);// ������������
	}

	public void paintqipan(Graphics g) {
		for (int x = 0; x < 29; x++) {
			for (int y = 0; y < 29; y++) {
				if (cells[x][y] != null) {
					Image img = cells[x][y].getImage();
					int a = x * 20 + 10;
					int b = y * 20 + 10;
					g.drawImage(img, a, b, 18, 18, null);
				}
			}
		}
	}

	// ���غ����¾�������� �������false
	public boolean putqizi(Cell cell) {
		int x = (cell.getCol() - 10) / 20;
		int y = (cell.getRow() - 10) / 20;
		if (chonghe(cell)) {
			cells[x][y] = cell;
			return true;
		} else {
			return false;
		}
	}

	// �ж������غϣ�������������²���
	public boolean chonghe(Cell cell) {
		int x = (cell.getCol() - 10) / 20;
		int y = (cell.getRow() - 10) / 20;
		if (cells[x][y] == null) {
			return true;
		}
		return false;
	}

	// �ж�����Խ�磬ûԽ��true
	public boolean yuejie(int x, int y) {
		if ((0 <= x && x < 29) && (0 <= y && y < 29)) {
			return true;
		}
		return false;
	}
//dongmian(cell)&&ximian(cell)&&nanmian(cell)&&beimian(cell)&&dongnanmian(cell)&&nanximian(cell)&&xibeimian(cell)&&beidongmian(cell)
	// �ж���һ������Ϊ���꣬��������false����־��Ϸ����
	public boolean mangwu(Cell cell) throws IOException, NoSuchAlgorithmException {
		int a=dongmian(cell);
		int b=ximian(cell);	
		int c=nanmian(cell);
		int d=beimian(cell);
		int e=dongnanmian(cell);
		int f=xibeimian(cell);   
		int g=nanximian(cell);
     	int h=beidongmian(cell);
	if(((a+b-1)>=5)||((c+d-1)>=5)||((e+f-1)>=5)||((g+h-1)>=5)){		
			count=1;
			count1=1;
			count2=1;
			count3=1;
		    count4=1;
		    count5=1;
		    count6=1;
		    count7=1;
			return false;
		}else {
			count=1;
			count1=1;
			count2=1;
			count3=1;
			count4=1;
			count5=1;
			count6=1;
		    count7=1;
		} 
		return true;
	}

	// ��¼�������� ��ͬ���Ӹ���
	public int dongmian(Cell cell) throws IOException, NoSuchAlgorithmException {
		int x=(cell.getRow()-10)/20;//��
		int y=(cell.getCol()-10)/20;//��
		Image img=cell.getImage();
		y++;
		if( yuejie(x,y)) {
		if(cells[y][x]!=null) {			
			judgeimage judge=new judgeimage();
			Image img1=cells[y][x].getImage();
			judge.imgtofile(img,img1);
			if(judge. panduan()) {
				count++;
				 dongmian(cells[y][x]);
			}
		}
		}
		return count;
	}

	// �������� ��ͬ���Ӹ���
	public int ximian(Cell cell) throws IOException, NoSuchAlgorithmException {
		int x=(cell.getRow()-10)/20;//��
		int y=(cell.getCol()-10)/20;//��
		Image img=cell.getImage();
		y--;
		if( yuejie(x,y)) {
		if(cells[y][x]!=null) {			
			judgeimage judge=new judgeimage();
			Image img1=cells[y][x].getImage();
			judge.imgtofile(img,img1);
			if(judge. panduan()) {
				count1++;
				ximian(cells[y][x]);
			}
		}
		}
		return count1;
	}

	// ����������ͬ���Ӹ���
	public int nanmian(Cell cell) throws IOException, NoSuchAlgorithmException {
		int x=(cell.getRow()-10)/20;//��
		int y=(cell.getCol()-10)/20;//��
		Image img=cell.getImage();
		x++;
		if( yuejie(x,y)) {
		if(cells[y][x]!=null) {			
			judgeimage judge=new judgeimage();
			Image img1=cells[y][x].getImage();
			judge.imgtofile(img,img1);
			if(judge. panduan()) {
				count2++;
				nanmian(cells[y][x]);
			}
		}
		}
		return count2;
	}

	// ����������ͬ���Ӹ���
	public int beimian(Cell cell) throws IOException, NoSuchAlgorithmException {
		int x=(cell.getRow()-10)/20;//��
		int y=(cell.getCol()-10)/20;//��
		Image img=cell.getImage();
		x--;
		if( yuejie(x,y)) {
		if(cells[y][x]!=null) {			
			judgeimage judge=new judgeimage();
			Image img1=cells[y][x].getImage();
			judge.imgtofile(img,img1);
			if(judge. panduan()) {
				count3++;
				beimian(cells[y][x]);
			}
		}
		}
		return count3;
	}

	// ��������,�򻹻�false
	public int dongnanmian(Cell cell) throws IOException, NoSuchAlgorithmException {
		int x=(cell.getRow()-10)/20;//��
		int y=(cell.getCol()-10)/20;//��
		Image img=cell.getImage();
		x++;
		y++;
		if( yuejie(x,y)) {
		if(cells[y][x]!=null) {			
			judgeimage judge=new judgeimage();
			Image img1=cells[y][x].getImage();
			judge.imgtofile(img,img1);
			if(judge. panduan()) {
				count4++;
				dongnanmian(cells[y][x]);
			}
		}
		}
		return count4;
	}

	// ��������,�򻹻�false
	public int nanximian(Cell cell) throws IOException, NoSuchAlgorithmException {
		int x=(cell.getRow()-10)/20;//��
		int y=(cell.getCol()-10)/20;//��
		Image img=cell.getImage();
		x++;
		y--;
		if( yuejie(x,y)) {
		if(cells[y][x]!=null) {			
			judgeimage judge=new judgeimage();
			Image img1=cells[y][x].getImage();
			judge.imgtofile(img,img1);
			if(judge. panduan()) {
				count6++;
				nanximian(cells[y][x]);
			}
		}
		}
		return count6;
	}

	// ��������,�򻹻�false
	public int xibeimian(Cell cell) throws IOException, NoSuchAlgorithmException {
		int x=(cell.getRow()-10)/20;//��
		int y=(cell.getCol()-10)/20;//��
		Image img=cell.getImage();
		x--;
		y--;
		if( yuejie(x,y)) {
		if(cells[y][x]!=null) {			
			judgeimage judge=new judgeimage();
			Image img1=cells[y][x].getImage();
			judge.imgtofile(img,img1);
			if(judge. panduan()) {
				count5++;
				xibeimian(cells[y][x]);
			}
		}
		}
		return count5;
	}

	// ��������,�򻹻�false
	public int beidongmian(Cell cell) throws IOException, NoSuchAlgorithmException {
		int x=(cell.getRow()-10)/20;//��
		int y=(cell.getCol()-10)/20;//��
		Image img=cell.getImage();
		x--;
		y++;
		if( yuejie(x,y)) {
		if(cells[y][x]!=null) {	
			judgeimage judge=new judgeimage();
			Image img1=cells[y][x].getImage();
			judge.imgtofile(img,img1);
			boolean d=judge. panduan();
			if(d) {
				count7++;
				beidongmian(cells[y][x]);
			}
		}
		}
		return count7;
	}

	// ��Ϸ������
	public void chongwan() {
		for (int x = 0; x < 29; x++) {
			for (int y = 0; y < 29; y++) {
				cells[x][y] = null;
			}
		}
	}
}

class judgeimage {
	// �����ַ���ж�������Ƭ��ͬ,���ͬ�򷵻�true
	public boolean panduan() throws NoSuchAlgorithmException, IOException {

		byte[] str1 = identicalimage("img/a.jpg");
		String t1 = bytetostr(str1);
		byte[] str2 = identicalimage("img/c.jpg");
		String t2 = bytetostr(str2);
		if (image(t1, t2)) {
			return true;

		} else {
			return false;
		}

	}

	// �ж�������Ƭ��ͬ,���ͬ�򷵻�true
	public boolean image(String mymd5, String mymd51) {

		if (mymd5.equals(mymd51)) {
			return true;
		} else {
			return false;
		}
	}

	// ��ȡͼƬλ�ã���ȡͼƬд������,����һ��MD5���ܺ�String
	public static byte[] identicalimage(String model) throws NoSuchAlgorithmException, IOException {
		InputStream in = new FileInputStream(new File(model));
		ByteArrayOutputStream btin = new ByteArrayOutputStream();
		byte[] bt = new byte[1024];
		int num = -1;
		while ((num = in.read(bt)) != -1) {
			btin.write(bt, 0, num);
		}
		byte[] by = btin.toByteArray();

		// ��ȡMD5�㷨
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		// ����Ҫ��ȡժҪ������
		md5.update(by, 0, by.length);
		// ��ȡ���ݵ���ϢժҪ,�ֽ�����
		byte[] resultBytes = md5.digest();
		return resultBytes;
	}

	static String bytetostr(byte[] rs) {

		StringBuilder sb = new StringBuilder();
		for (byte b : rs) {
			int a = b < 0 ? 256 + b : b;//
			String str = Integer.toHexString(a);
			if (a < 16) {
				sb.append("0");
			}
			sb.append(str);
		}
		return sb.toString();
	}

	// ��ͼƬд���ļ�
	public void imgtofile(Image img, Image img1) throws IOException {
		BufferedImage bfimg = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_RGB);
		Graphics g = bfimg.getGraphics();
		g.drawImage(img, 0, 0, null);
		ImageIO.write(bfimg, "jpg", new File("img/a.jpg"));// ���
		BufferedImage bfimg1 = new BufferedImage(img1.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_RGB);
		Graphics g1 = bfimg1.getGraphics();
		g1.drawImage(img1, 0, 0, null);
		ImageIO.write(bfimg1, "jpg", new File("img/c.jpg"));

	}

}
