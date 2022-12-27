package gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.hibernate.SessionFactory;

import entity.ChuCuaHang;
import entity.NhanVienBanHang;
import entity.TaiKhoan;
import guiNV.GD_DoiMatKhau_NhanVienMoi;
import dao.ChuCuaHangDAO;
import dao.NhanVienDAO;
import dao.TaiKhoanDAO;
import dao_imp.ChuCuaHangImp;
import dao_imp.MySessionFactory;
import dao_imp.NhanVienImp;
import dao_imp.TaiKhoanImp;
import javax.swing.JPasswordField;

public class DangNhap extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtLogin;
	private JButton btnDangNhap;
	private ImageIcon iConDangNhap = new ImageIcon(DangNhap.class.getResource("/img/enter.png"));
	private ImageIcon iConExit = new ImageIcon(DangNhap.class.getResource("/img/exit_login.png"));
	private ImageIcon iConuser = new ImageIcon(DangNhap.class.getResource("/img/user.png"));
	private ImageIcon iConpass = new ImageIcon(DangNhap.class.getResource("/img/padlock.png"));
	private ImageIcon iConlogin = new ImageIcon(DangNhap.class.getResource("/img/login.png"));

	SessionFactory sessionFactory = new MySessionFactory().getSessionFactory();
	TaiKhoanDAO taiKhoanDAO = new TaiKhoanImp(sessionFactory);
	NhanVienDAO nhanvienDAO = new NhanVienImp(sessionFactory);
	ChuCuaHangDAO chucuahangDAO = new ChuCuaHangImp(sessionFactory);
	private JLabel lblThongBao;
	private JPasswordField passwordField;
	private JPasswordField txtPassword;

	public DangNhap() {
		setFocusCycleRoot(true);
		setFocusableWindowState(true);
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		// setAlwaysOnTop(true);
		setBounds(500, 200, 680, 400);
		// setSize(600, 400);
		getContentPane().setLayout(null);
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(GiaoDienChinh_ChuCuaHang.class.getResource("/img/icons8_fire_50px.png")));
		giaoDien();
	}

	void giaoDien() {
		JPanel pDangNhap = new JPanel();
		pDangNhap.setBackground(new Color(154, 205, 50));
		pDangNhap.setBounds(0, 0, 680, 400);
		getContentPane().add(pDangNhap);
		pDangNhap.setLayout(null);

		JLabel lblLogin = new JLabel("T\u00EAn T\u00E0i Kho\u1EA3n");
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogin.setFont(new Font("Verdana", Font.PLAIN, 14));
		lblLogin.setBounds(156, 180, 120, 30);
		pDangNhap.add(lblLogin);

		JLabel lblPassWord = new JLabel("M\u1EADt Kh\u1EA9u");
		lblPassWord.setFont(new Font("Verdana", Font.PLAIN, 14));
		lblPassWord.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassWord.setBounds(156, 230, 120, 30);
		pDangNhap.add(lblPassWord);

		txtLogin = new JTextField();
		txtLogin.setText("CCH001");
		txtLogin.setToolTipText("Nhập tên đăng nhập");
		// txtLogin.setText("CCH001");
		txtLogin.setFont(new Font("Verdana", Font.PLAIN, 14));
		txtLogin.setBounds(286, 180, 300, 30);
		pDangNhap.add(txtLogin);
		txtLogin.setColumns(10);

		JPanel pThaoTac = new JPanel();
		pThaoTac.setBackground(new Color(0, 255, 255));
		pThaoTac.setBounds(0, 300, 680, 100);
		pDangNhap.add(pThaoTac);
		pThaoTac.setLayout(null);

		btnDangNhap = new JButton("\u0110\u0103ng Nh\u1EADp");
		btnDangNhap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (kiemtraTenDangNhap() == 0) {
					if (txtLogin.getText().length() == 6) {
						GiaoDienChinh_ChuCuaHang gdChinhChuCuaHang = new GiaoDienChinh_ChuCuaHang();
						gdChinhChuCuaHang.setVisible(true);
						setVisible(false);
						// taiKhoanDAO.kiemtraTinhTrangDangNhap(true, txtLogin.getText());
					} else if (txtLogin.getText().length() == 5) {
						if (!txtPassword.getText().trim().equalsIgnoreCase("123456")) {
							kiemtraTaiKhoanDaDangNhap();
							// taiKhoanDAO.kiemtraTinhTrangDangNhap(true, txtLogin.getText());
							ChuCuaHang cch = new ChuCuaHang("CCH001");
							NhanVienBanHang nv = new NhanVienBanHang(txtLogin.getText());
							TaiKhoan tk = taiKhoanDAO.layThongTinTaiKhoanTheoTenDangNhap(txtLogin.getText());
							TaiKhoan taiKhoan = new TaiKhoan(tk.getTenDangNhap(), tk.getMatKhau(), tk.getQuyen(), true,
									true, cch, nv);
							taiKhoanDAO.cpanhatTaiKhoan(taiKhoan);
							NhanVienBanHang nvbh = nhanvienDAO.layNhanVienTheoMa(txtLogin.getText());
							NhanVienBanHang nhanVien = new NhanVienBanHang(nvbh.getMaNhanVien(), nvbh.getTenNhanVien(),
									nvbh.isGioiTinh(), nvbh.getSoCMND(), nvbh.getSoDienThoai(), nvbh.getDiaChi(),
									nvbh.getThanhPho(), nvbh.getNgaySinh(), nvbh.getNgayVaoLam(), true);
							nhanvienDAO.capNhatNhanVien(nhanVien);
							setVisible(false);

							guiNV.GiaoDienChinh_NhanVienBanHang gui = new guiNV.GiaoDienChinh_NhanVienBanHang();
							gui.setVisible(true);
							// setVisible(false);
						} else {
							ChuCuaHang cch = new ChuCuaHang("CCH001");
							NhanVienBanHang nv = new NhanVienBanHang(txtLogin.getText());
							TaiKhoan tk = taiKhoanDAO.layThongTinTaiKhoanTheoTenDangNhap(txtLogin.getText());
							TaiKhoan taiKhoan = new TaiKhoan(tk.getTenDangNhap(), tk.getMatKhau(), tk.getQuyen(), true,
									true, cch, nv);
							taiKhoanDAO.cpanhatTaiKhoan(taiKhoan);
							NhanVienBanHang nvbh = nhanvienDAO.layNhanVienTheoMa(txtLogin.getText());
							NhanVienBanHang nhanVien = new NhanVienBanHang(nvbh.getMaNhanVien(), nvbh.getTenNhanVien(),
									nvbh.isGioiTinh(), nvbh.getSoCMND(), nvbh.getSoDienThoai(), nvbh.getDiaChi(),
									nvbh.getThanhPho(), nvbh.getNgaySinh(), nvbh.getNgayVaoLam(), true);
							nhanvienDAO.capNhatNhanVien(nhanVien);
							setVisible(false);

							GD_DoiMatKhau_NhanVienMoi gui = new GD_DoiMatKhau_NhanVienMoi();
							gui.setVisible(true);
						}

					}

				} else if (kiemtraTenDangNhap() == -1) {
					lblThongBao.setText("Sai tên đăng nhập");
				} else
					lblThongBao.setText("Sai mật khẩu");
			}
		});
		btnDangNhap.setIcon(iConDangNhap);
		btnDangNhap.setBackground(new Color(240, 248, 255));
		btnDangNhap.setFont(new Font("Verdana", Font.PLAIN, 14));
		btnDangNhap.setBounds(448, 33, 150, 40);
		pThaoTac.add(btnDangNhap);
		
		JButton btnThoat = new JButton("Thoát");
		btnThoat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnThoat.setIcon(new ImageIcon(DangNhap.class.getResource("/img/cancel.png")));
		btnThoat.setFont(new Font("Verdana", Font.PLAIN, 14));
		btnThoat.setBackground(new Color(240, 248, 255));
		btnThoat.setBounds(69, 33, 150, 40);
		pThaoTac.add(btnThoat);

		JLabel lblIconTK = new JLabel("");
		lblIconTK.setIcon(iConuser);
		lblIconTK.setBounds(125, 178, 40, 32);
		pDangNhap.add(lblIconTK);

		JLabel lblIconMK = new JLabel("");
		lblIconMK.setIcon(iConpass);
		lblIconMK.setBounds(135, 228, 32, 32);
		pDangNhap.add(lblIconMK);

		JLabel lblIconLogin = new JLabel("");
		lblIconLogin.setIcon(iConlogin);
		lblIconLogin.setBounds(60, 10, 124, 124);
		pDangNhap.add(lblIconLogin);

		JLabel lblNewLabel = new JLabel("Phần mềm quản lý cửa hàng ");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel.setBounds(211, 10, 433, 73);
		pDangNhap.add(lblNewLabel);

		JLabel lblThiTrangSspn = new JLabel("bán đồ thời trang SSPN12");
		lblThiTrangSspn.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblThiTrangSspn.setBounds(230, 63, 356, 73);
		pDangNhap.add(lblThiTrangSspn);

		lblThongBao = new JLabel("");
		lblThongBao.setForeground(Color.RED);
		lblThongBao.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblThongBao.setBounds(230, 138, 300, 30);
		pDangNhap.add(lblThongBao);

		passwordField = new JPasswordField();
		passwordField.setBounds(211, 271, -134, 19);
		pDangNhap.add(passwordField);

		txtPassword = new JPasswordField();
		txtPassword.setText("987654");
		txtPassword.setToolTipText("Nhập mật khẩu");
		txtPassword.setFont(new Font("Verdana", Font.PLAIN, 14));
		txtPassword.setBounds(286, 232, 300, 30);
		pDangNhap.add(txtPassword);

	}

	protected int kiemtraTenDangNhap() {
		String tenDangNhap = txtLogin.getText();
		String pass = txtPassword.getText().trim();
		System.out.println(pass);

		int s = (int) taiKhoanDAO.kiemTraTaiKhoan(tenDangNhap, pass);

		return s;
		/*
		 * ArrayList<TaiKhoan> listTK = taiKhoanDAO.kiemTraTaiKhoan(tenDangNhap, pass);
		 * for(TaiKhoan tk:listTK) { if(tk.getTenTaiKhoan().equals(tenDN) &&
		 * tk.getMatKhau().equals(matKhau)) { return true; } }return false;
		 */

	}

	public static void main(String[] args) {
		new DangNhap().setVisible(true);
	}

	public void kiemtraTaiKhoanDaDangNhap() {
		List<TaiKhoan> listTK = taiKhoanDAO.layDanhSachTaiKhoan();
		
		int n = 0;
		for (TaiKhoan tk : listTK) {
			TaiKhoan t = new TaiKhoan(tk.getTenDangNhap());
			if (tk.isTinhTrang()) {
				ChuCuaHang chu = chucuahangDAO.layThongTinChuCuaHangTheoMa("CCH001");
				ChuCuaHang cch = new ChuCuaHang(chu.getMaChuCuaHang(), chu.getTenChuCuaHang(), chu.isGioiTinh(), chu.getSoCMND(), 
						chu.getSoDienThoai(), chu.getDiaChi(), chu.getThanhPho(), chu.getNgaySinh());
				//NhanVienBanHang nvbh = nhanvienDAO.layDanhSachNhanVienTheoTinhTrang(true);
				//NhanVienBanHang nv = new NhanVienBanHang()));
				String ma = txtLogin.getText();
				NhanVienBanHang nv = nhanvienDAO.layNhanVienTheoMa(ma);
				//nhanvienDAO.capnhatTinhTrangNhanVien(ma);
				NhanVienBanHang nhanVien = new NhanVienBanHang(nv.getMaNhanVien(), nv.getTenNhanVien(), nv.isGioiTinh(), nv.getSoCMND(), nv.getSoDienThoai(), nv.getDiaChi(), nv.getThanhPho(), nv.getNgaySinh(),  nv.getNgayVaoLam(), nv.isTinhTrang());
				//TaiKhoan ta = taiKhoanDAO.layThongTinTaiKhoanTheoTrangThai(true);
				TaiKhoan taiKhoan = new TaiKhoan(tk.getTenDangNhap(), tk.getMatKhau(), tk.getQuyen(), true, false, cch, nhanVien);
				System.out.println(tk.getTenDangNhap());
				System.out.println(tk.isTinhTrang());
				taiKhoan.setTinhTrang(false);
				System.out.println(tk.isTinhTrang());
				taiKhoanDAO.cpanhatTaiKhoan(taiKhoan);
			}
				
		}
	}
}
