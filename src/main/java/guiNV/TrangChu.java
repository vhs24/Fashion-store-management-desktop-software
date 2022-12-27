package guiNV;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import java.awt.Font;

public class TrangChu extends JPanel {

	/**
	 * Create the panel.
	 */
	public TrangChu() {
		setBackground(new Color(255, 255, 255));
		setBounds(240, 0, 1100, 700);
		setLayout(null);
		
		JLabel l = new JLabel();
		l.setIcon(new ImageIcon(TrangChu.class.getResource("/img/thiet-ke-thi-cong-shop-thoi-trang-hinh-2.jpg")));
		l.setBounds(0, 0, 1100, 700);
		add(l);
		
		JLabel tieuDe = new JLabel("SHOP THỜI TRANG SSPN12");
		tieuDe.setForeground(Color.CYAN);
		l.add(tieuDe);
		tieuDe.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 50));
		tieuDe.setBounds(230, 135, 700, 50);
		
		JLabel diaChi = new JLabel("Địa chỉ: 12 Nguyễn Văn Bảo, Phường 4, Gò Vấp, Thành phố Hồ Chí Minh");
		diaChi.setForeground(Color.ORANGE);
		diaChi.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 15));
		diaChi.setBounds(290, 574, 700, 50);
		l.add(diaChi);
		
		
	}

}
