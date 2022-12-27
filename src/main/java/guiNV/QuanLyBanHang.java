package guiNV;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.text.MaskFormatter;

import org.hibernate.SessionFactory;

import javax.swing.border.EtchedBorder;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;

import Connect.ConnectDB;
import Custom.ButtonGradient;
import dao.ChiTietBanHangDAO;
import dao.ChuCuaHangDAO;
import dao.GenerateKeyDAO;
import dao.HangSanXuatDAO;
import dao.HoaDonBanHangDAO;
import dao.KhachHangDAO;
import dao.LoaiSanPhamDAO;
import dao.NhanVienDAO;
import dao.SanPhamDAO;
import dao.TaiKhoanDAO;
import dao_imp.ChiTietBanHangImp;
import dao_imp.ChuCuaHangImp;
import dao_imp.GenerateKeyImp;
import dao_imp.HoaDonBanHangImp;
import dao_imp.KhachHangImp;
import dao_imp.MySessionFactory;
import dao_imp.NhanVienImp;
import dao_imp.SanPhamImp;
import dao_imp.TaiKhoanImp;
import entity.ChiTietHoaDonBanHang;
import entity.ChiTietHoaDonNhapKho;
import entity.ChuCuaHang;
import entity.HangSanXuat;
import entity.HoaDonBanHang;
import entity.KhachHang;
import entity.LoaiSanPham;
import entity.NhanVienBanHang;
import entity.SanPham;
import entity.TaiKhoan;
import gui.GiaoDienChinh_ChuCuaHang;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class QuanLyBanHang extends JPanel {

	private JTable table;
	private DefaultTableModel tableKHMode;
	private TableModel tablePQMode;
	private JTable tableKH;
	private JTable table_1;
	private JTable table_2;
	private JTable table_3;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;
	private JTable tableHDBH;
	private JTable tableSanPham;
	private JLabel lblHangSanXuat;
	private JTextField textField;
	private JTextField textField_1;
	private JPanel pAnBoLocHDBH;
	private JPanel pBoLocHDBH;
	private JTable tableNhanVien;
	private JTable tableHDKho;
	private JPanel pBoLocHDKho;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField txtSDT_CMND;
	private DefaultTableModel model;

	SessionFactory sessionFactory = new MySessionFactory().getSessionFactory();
	GenerateKeyDAO generateKeyDAO = new GenerateKeyImp(sessionFactory);
	ChiTietBanHangDAO chiTietBanHangDAO = new ChiTietBanHangImp(sessionFactory);
	HoaDonBanHangDAO hoaDonBanHangDAO = new HoaDonBanHangImp(sessionFactory);
	KhachHangDAO khachHangDAO = new KhachHangImp(sessionFactory);
	NhanVienDAO nhanVienDAO = new NhanVienImp(sessionFactory);
	SanPhamDAO sanphamDAO = new SanPhamImp(sessionFactory);
	TaiKhoanDAO taikhoanDAO = new TaiKhoanImp(sessionFactory);
	ChuCuaHangDAO chucuahangDAO = new ChuCuaHangImp(sessionFactory);
	SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	List<HoaDonBanHang> listHDBH = hoaDonBanHangDAO.layTatCaHoaDonBanHang();
	List<KhachHang> listKH = khachHangDAO.layTatCaKhachHang();
	List<NhanVienBanHang> listNV = nhanVienDAO.layTatCaNhanVien();
	private JDateChooser dateChooser;
	private JLabel lblTenKH;
	private JPanel pXemThongTonHDBH;
	private JPanel pThemHDBH;
	private JPanel pTaoHoaDonBH;
	private JTextField txtLuaChonKhachHang;
	private JTextField txtTenKH;
	private JTable tableCTHDBanHang;
	private JTextField txtTenNV;
	private JTable tableChiTietHDBH;
	private JTextField txtTongTien;
	private JTextField txtTienKhachDua;
	private JTextField txtTienTraLai;
	private JTable tableSP;
	private JTextField txtMaSP;
	private JTextField txtSL;
	private JPanel pXemThongTinBH;
	private JLabel lblThongBao;
	private JLabel lblMaHD;
	private JButton btnXoa;
	private JButton btnTaoHD;
	private JButton btnCapNhatHD;
	private JPanel pCapNhatHoaDonBH;
	private JButton btnThemVaoCTHDBH;
	private JButton btnCapNhatSoLuong;
	private JButton btnInHD;
	private JButton btnXoaSP;
	private NumberFormat currentLocale = NumberFormat.getInstance();
	private Locale localeVN = new Locale("vi", "VN");
	private NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
	private JTable tableTimKH;
	private DefaultTableModel tableModel1;
	private JScrollPane scpTimKH;
	private static int Enter, Enter1, soLuongCuaSP;

	/**
	 * Create the panel.
	 * 
	 * @throws ParseException
	 */
	public QuanLyBanHang() throws ParseException {
		setLayout(null);

		// Chức năng quản lý hóa đơn
		// ------------------------------------------------------------------------------------------------------------------------------------------

		setLayout(null);
		setBackground(new Color(255, 255, 255));
		setBounds(240, 0, 1100, 700);

		/*
		 * pThemHDBH = new JPanel(); pThemHDBH.setBackground(Color.WHITE);
		 * pThemHDBH.setBounds(10, 10, 1090, 680); add(pThemHDBH);
		 * pThemHDBH.setLayout(null);
		 */

		// -------------------------------------------------------------------------------------------------------
		// Xem thong tin hoa don ban hang
		pXemThongTonHDBH = new JPanel();
		pXemThongTonHDBH.setBackground(Color.WHITE);
		pXemThongTonHDBH.setBounds(10, 10, 1090, 680);
		add(pXemThongTonHDBH);
		pXemThongTonHDBH.setLayout(null);

		JLabel lblQuanLyBanHang = new JLabel("Quản lý bán hàng");
		lblQuanLyBanHang.setFont(new Font("Tahoma", Font.PLAIN, 40));
		lblQuanLyBanHang.setBounds(20, 21, 358, 60);
		pXemThongTonHDBH.add(lblQuanLyBanHang);

		JScrollPane scrollPaneHDBH = new JScrollPane();
		scrollPaneHDBH.setBounds(20, 298, 1040, 350);
		pXemThongTonHDBH.add(scrollPaneHDBH);

		tableHDBH = new JTable();
		tableHDBH.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = tableHDBH.getSelectedRow();
				String maHD = tableHDBH.getValueAt(row, 1).toString();
				// model = (DefaultTableModel) tableCTHDBH.getModel();
				// model.getDataVector().removeAllElements();
				// model.fireTableDataChanged();
				/*
				 * if (tableCTHDBanHang.getRowCount() != 0) { for (int i = 0; i <=
				 * tableCTHDBanHang.getRowCount(); i++) { model.removeRow(i); } } else
				 */
				// showTableCTHDBH(maHD);

			}
		});
		tableHDBH.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "STT", "M\u00E3 h\u00F3a \u0111\u01A1n", "Kh\u00E1ch h\u00E0ng", "Nh\u00E2n vi\u00EAn",
						"Ng\u00E0y l\u1EADp h\u00F3a \u0111\u01A1n", "T\u1ED5ng ti\u1EC1n", "Ghi ch\u00FA",
						"Tiền khách đưa" }));
		scrollPaneHDBH.setViewportView(tableHDBH);
		tableHDBH.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tableHDBH.setBackground(Color.WHITE);

		tableHDBH.setAutoCreateRowSorter(true);
		tableHDBH.setRowHeight(tableHDBH.getRowHeight() + 10);
		tableHDBH.setDefaultEditor(Object.class, null);

		model = (DefaultTableModel) tableHDBH.getModel();
		showTable();

		/*
		 * pAnBoLocHDBH = new JPanel(); pAnBoLocHDBH.setBackground(Color.WHITE);
		 * pAnBoLocHDBH.setBounds(907, 430, 300, 301); pQuanLyHoaDon.add(pAnBoLocHDBH);
		 * pAnBoLocHDBH.setLayout(null);
		 */

		pBoLocHDBH = new JPanel();
		pBoLocHDBH.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"B\u1ED9 l\u1ECDc t\u00ECm ki\u1EBFm", TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(0, 0, 0)));
		pBoLocHDBH.setBackground(Color.WHITE);
		pBoLocHDBH.setBounds(291, 106, 770, 152);
		pXemThongTonHDBH.add(pBoLocHDBH);
		pBoLocHDBH.setLayout(null);

		JLabel lblNgayLapHoaDon = new JLabel("Ngày lập hóa đơn");
		lblNgayLapHoaDon.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNgayLapHoaDon.setBounds(10, 20, 125, 35);
		pBoLocHDBH.add(lblNgayLapHoaDon);

		JLabel lblSinThoi = new JLabel("Số điện thoại, số cmnd, tên khách hàng");
		lblSinThoi.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSinThoi.setBounds(312, 25, 260, 24);
		pBoLocHDBH.add(lblSinThoi);

		txtSDT_CMND = new JTextField();
		txtSDT_CMND.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblTenKH.setText("");
			}
		});
		txtSDT_CMND.setBounds(580, 21, 140, 35);
		pBoLocHDBH.add(txtSDT_CMND);
		txtSDT_CMND.setColumns(10);

		dateChooser = new JDateChooser();
		dateChooser.setBounds(145, 20, 140, 35);
		pBoLocHDBH.add(dateChooser);
		dateChooser.setDateFormatString("dd/MM/yyyy");

		JButton btnTimKiem = new ButtonGradient();
		btnTimKiem.setText("Tìm kiếm");
		((ButtonGradient) btnTimKiem).setColor2(Color.decode("#0099F7"));
		btnTimKiem.setIcon(new ImageIcon(GiaoDienChinh_ChuCuaHang.class.getResource("/img/icons8_search_40px.png")));
		btnTimKiem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				timKiem();

			}
		});
		btnTimKiem.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnTimKiem.setBounds(145, 80, 171, 47);
		pBoLocHDBH.add(btnTimKiem);

		JButton btnXoa = new ButtonGradient();
		btnXoa.setText("Xóa bộ lọc");
		((ButtonGradient) btnXoa).setColor2(Color.decode("#0099F7"));
		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtSDT_CMND.setText("");
				dateChooser.setDate(null);

				xoaHetDuLieuTrenTableModel();
				model = (DefaultTableModel) tableHDBH.getModel();
				showTable();
			}
		});
		btnXoa.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnXoa.setBounds(350, 80, 171, 47);
		btnXoa.setIcon(new ImageIcon(GiaoDienChinh_ChuCuaHang.class.getResource("/img/cancel.png")));
		pBoLocHDBH.add(btnXoa);

		lblTenKH = new JLabel("");
		lblTenKH.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTenKH.setBounds(145, 148, 140, 27);
		pBoLocHDBH.add(lblTenKH);

		JButton btnThemHD = new JButton("Thêm hóa đơn");
		btnThemHD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * ThemHoaDonBanHang themHDBH = new ThemHoaDonBanHang();
				 * themHDBH.setVisible(true); themHDBH.setLocationRelativeTo(null);
				 * themHDBH.setAlwaysOnTop(true); themHDBH.setResizable(false);
				 */
				pTaoHoaDonBH.setVisible(false);
				add(pTaoHoaDonBH);
				pTaoHoaDonBH.setVisible(true);
				pXemThongTonHDBH.setVisible(false);
			}
		});
		btnThemHD.setBackground(Color.CYAN);
		btnThemHD.setIcon(new ImageIcon(GiaoDienChinh_ChuCuaHang.class.getResource("/img/icons8_add_40px.png")));
		btnThemHD.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnThemHD.setBounds(20, 106, 240, 60);
		pXemThongTonHDBH.add(btnThemHD);

		JButton btnXemHan = new JButton("Xem hóa đơn");
		btnXemHan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = tableHDBH.getSelectedRow();
				String maHD = tableHDBH.getValueAt(row, 1).toString();
				xemHoaDon(maHD);
			}
		});
		btnXemHan.setIcon(new ImageIcon(QuanLyBanHang.class.getResource("/img/icons8_purchase_order_60px_1.png")));
		btnXemHan.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnXemHan.setBackground(Color.CYAN);
		btnXemHan.setBounds(20, 198, 240, 60);
		pXemThongTonHDBH.add(btnXemHan);

		// -----------------------------------------------------------------------------------------------------------------
		// Tao hoá đơn bán hàng
		pTaoHoaDonBH = new JPanel();
		pTaoHoaDonBH.setBackground(Color.WHITE);
		pTaoHoaDonBH.setBounds(10, 10, 1090, 690);
		// add(pTaoHoaDonBH);
		pTaoHoaDonBH.setLayout(null);

		JLabel lblNewLabel = new JLabel("HÓA ĐƠN BÁN HÀNG");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 35));
		lblNewLabel.setBounds(10, 0, 393, 49);
		pTaoHoaDonBH.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Số điện thoại \nhoặc số cmnd");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(50, 60, 211, 35);
		pTaoHoaDonBH.add(lblNewLabel_1);

		// MaskFormatter mf1 = new MaskFormatter("#### #### ####");

		// txtLuaChonKhachHang = new JFormattedTextField(mf1);
		txtLuaChonKhachHang = new JTextField();
		txtLuaChonKhachHang.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (Enter == 0) {
					String key = txtLuaChonKhachHang.getText();
					try {
						showTimKiemKH(key);
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else
					Enter = 0;
			}
		});
		txtLuaChonKhachHang.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtLuaChonKhachHang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * String nhap = txtLuaChonKhachHang.getText().trim(); System.out.println(nhap);
				 * KhachHang kh; if (nhap.length() == 10 || nhap.length() == 11) { kh =
				 * khachHangDAO.layKhachHangTheoDienthoai(txtLuaChonKhachHang.getText()); if
				 * (kh!= null) { System.out.println(kh.getTenKhachHang());
				 * txtTenKH.setText(kh.getTenKhachHang()); } else {
				 * JOptionPane.showMessageDialog(this,
				 * "Lỗi nhập dữ liệu: không có tìm thấy thông ti khách hàng, vui lòng nhập lại "
				 * ); txtLuaChonKhachHang.selectAll(); txtLuaChonKhachHang.requestFocus(); } }
				 * else if (nhap.length() == 9 || nhap.length() == 12) { kh =
				 * khachHangDAO.layKhachhangTheoCMND(nhap);
				 * 
				 * txtTenKH.setText(kh.getTenKhachHang()); } if (txtTenKH.getText().equals(""))
				 * lblThongBao.setText("Lỗi nhập dữ liệu");
				 */
				// timkiemKhachHang();
				scpTimKH.setVisible(true);
				Enter = 1;
			}
		});
		txtLuaChonKhachHang.setBounds(270, 60, 186, 35);
		pTaoHoaDonBH.add(txtLuaChonKhachHang);
		txtLuaChonKhachHang.setColumns(10);

		String col1[] = { "Mã KH", "Tên KH", "SDT", "CMND" };
		tableModel1 = new DefaultTableModel(col1, 0);
		tableTimKH = new JTable(tableModel1);
		tableTimKH.getColumnModel().getColumn(0).setPreferredWidth(10);
		tableTimKH.getColumnModel().getColumn(1).setPreferredWidth(45);
		tableTimKH.getColumnModel().getColumn(2).setPreferredWidth(15);
		tableTimKH.getColumnModel().getColumn(3).setPreferredWidth(15);
		tableTimKH.setDefaultEditor(Object.class, null);
		tableTimKH.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int rowSelect = tableTimKH.getSelectedRow();
				String maKH = (String) tableTimKH.getValueAt(rowSelect, 0);
				String tenKH = (String) tableTimKH.getValueAt(rowSelect, 1);
				String soDT = (String) tableTimKH.getValueAt(rowSelect, 2);
				String cmnd = (String) tableTimKH.getValueAt(rowSelect, 3);
				txtTenKH.setText(tenKH);
				scpTimKH.setVisible(false);
				txtTenKH.setEditable(false);
				txtLuaChonKhachHang.setText(soDT);
			}
		});
		scpTimKH = new JScrollPane(tableTimKH);
		scpTimKH.setVisible(false);
		scpTimKH.setBounds(270, 95, 438, 124);
		pTaoHoaDonBH.add(scpTimKH);

		JLabel lblNewLabel_1_1 = new JLabel("Tên khách hàng");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_1.setBounds(50, 100, 176, 35);
		pTaoHoaDonBH.add(lblNewLabel_1_1);

		txtTenKH = new JTextField();
		txtTenKH.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtTenKH.setEditable(false);
		txtTenKH.setColumns(10);
		txtTenKH.setBounds(270, 100, 186, 35);
		pTaoHoaDonBH.add(txtTenKH);

		JLabel lblNewLabel_1_1_1 = new JLabel("Tên nhân viên");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_1_1.setBounds(50, 140, 176, 35);
		pTaoHoaDonBH.add(lblNewLabel_1_1_1);

		txtTenNV = new JTextField();
		txtTenNV.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtTenNV.setEditable(false);
		txtTenNV.setColumns(10);
		txtTenNV.setBounds(270, 140, 186, 35);
		pTaoHoaDonBH.add(txtTenNV);
		layThongTinNhanVien();
		/*ChuCuaHang cch;
		try {
			cch = chucuahangDAO.layThongTinChuCuaHang();
			txtTenNV.setText(cch.getTenChuCuaHang());
		} catch (Exception e1) {
			
			e1.printStackTrace();
		}*/

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(46, 698, 1210, -418);
		pTaoHoaDonBH.add(scrollPane_2);

		btnInHD = new JButton("In hóa đơn");
		btnInHD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inHoaDon(lblMaHD.getText());
			}

		});
		btnInHD.setEnabled(false);
		btnInHD.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnInHD.setBounds(480, 60, 186, 35);
		pTaoHoaDonBH.add(btnInHD);

		JLabel lblNewLabel_1_2 = new JLabel("Tổng tiền");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_2.setBounds(690, 60, 176, 35);
		pTaoHoaDonBH.add(lblNewLabel_1_2);

		txtTongTien = new JTextField();
		txtTongTien.setEditable(false);
		txtTongTien.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtTongTien.setColumns(10);
		txtTongTien.setBounds(830, 60, 186, 35);
		pTaoHoaDonBH.add(txtTongTien);

		JLabel lblNewLabel_1_2_1 = new JLabel("Tiền khách đưa");
		lblNewLabel_1_2_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_2_1.setBounds(690, 100, 176, 35);
		pTaoHoaDonBH.add(lblNewLabel_1_2_1);

		txtTienKhachDua = new JTextField();
		txtTienKhachDua.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtTienKhachDua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tinhTienTraLai();

			}
		});
		txtTienKhachDua.setColumns(10);
		txtTienKhachDua.setBounds(830, 100, 186, 35);
		pTaoHoaDonBH.add(txtTienKhachDua);

		JLabel lblNewLabel_1_2_1_1 = new JLabel("Tiền trả lại");
		lblNewLabel_1_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_2_1_1.setBounds(690, 140, 180, 35);
		pTaoHoaDonBH.add(lblNewLabel_1_2_1_1);

		txtTienTraLai = new JTextField();
		txtTienTraLai.setEditable(false);
		txtTienTraLai.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtTienTraLai.setColumns(10);

		txtTienTraLai.setBounds(830, 140, 186, 35);
		pTaoHoaDonBH.add(txtTienTraLai);

		btnTaoHD = new JButton("Thanh toán\r\n");
		btnTaoHD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					kiemtraHoaDonLuu();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnTaoHD.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnTaoHD.setBounds(480, 100, 186, 35);
		pTaoHoaDonBH.add(btnTaoHD);

		JButton btnThoat = new JButton("Thoát");
		btnThoat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				thoatKhoiTrangThemHoaDon();
			}
		});
		btnThoat.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnThoat.setBounds(480, 140, 186, 35);
		pTaoHoaDonBH.add(btnThoat);

		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(10, 460, 1050, 170);
		pTaoHoaDonBH.add(scrollPane_3);

		tableChiTietHDBH = new JTable();
		tableChiTietHDBH.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = tableChiTietHDBH.getSelectedRow();
				txtSL.setText(tableChiTietHDBH.getValueAt(row, 7).toString());
				txtSL.selectAll();
				txtSL.requestFocus();
			}
		});
		scrollPane_3.setViewportView(tableChiTietHDBH);
		tableChiTietHDBH.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "STT", "M\u00E3 s\u1EA3n ph\u1EA9m", "T\u00EAn s\u1EA3n ph\u1EA9m", "H\u00E3ng",
						"Lo\u1EA1i", "Size", "M\u00E0u s\u1EAFc", "S\u1ED1 l\u01B0\u1EE3ng", "Gi\u00E1",
						"Th\u00E0nh ti\u1EC1n" }));
		tableChiTietHDBH.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(10, 270, 1050, 147);
		pTaoHoaDonBH.add(scrollPane_4);
		tableChiTietHDBH.setDefaultEditor(Object.class, null);

		tableSanPham = new JTable();
		tableSanPham.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = tableSanPham.getSelectedRow();
				int soLuong = (int) tableSanPham.getValueAt(row, 6);
				txtSL.setText("1");
				txtSL.selectAll();
				txtSL.requestFocus();
			}
		});
		tableSanPham.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "M\u00E3 s\u1EA3n ph\u1EA9m", "T\u00EAn s\u1EA3n ph\u1EA9m", "H\u00E3ng", "Lo\u1EA1i",
						"Size", "M\u00E0u s\u1EAFc", "S\u1ED1 l\u01B0\u1EE3ng", "Gi\u00E1" }));
		tableSanPham.setFont(new Font("Tahoma", Font.PLAIN, 14));
		scrollPane_4.setViewportView(tableSanPham);
		tableSanPham.setDefaultEditor(Object.class, null);

		model = (DefaultTableModel) tableSanPham.getModel();
		showTableSanPham();

		txtMaSP = new JTextField();
		txtMaSP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					String maSP = txtMaSP.getText();
					SanPham sp = sanphamDAO.laySanPhamTheoMa(maSP);
					// model = (DefaultTableModel) tableSanPham.getModel();
					DefaultTableModel dm = (DefaultTableModel) tableSanPham.getModel();
					// dm.getDataVector().removeAllElements();
					// for(int i=0;i<maSP.length();i++)
					System.out.println(kiemtraDongChuaMaSP(maSP));
					System.out.println(tableSanPham.getSelectedRowCount());
					dm.getDataVector().elementAt(kiemtraDongChuaMaSP(maSP));
					// tableSanPham.setRowSelectionInterval(kiemtraMaSP(maSP), -1);

					/*
					 * model.addRow(new Object[] { sp.getMaSanPham(), sp.getTenSanPham(),
					 * sp.getHangSanXuat().getTenHangSanXuat(), sp.getLoaiSanPham().getTenLoai(),
					 * sp.getKichThuoc(), sp.getMauSac(), sp.getSoLuong(), sp.getGiaSanPham() });
					 */
					txtSL.setText("1");
					txtSL.selectAll();
					txtSL.requestFocus();

					// HangSanXuat hsx =
					// hsxDAO.layHangSanXuatTheoMa(sp.getHangSanXuat().getMaHangSanXuat());
					// LoaiSanPham loai =
					// loaispDAO.layLoaiSanPhamTheoMa(sp.getLoaiSanPham().getMaLoaiSanPham());

					// DefaultTableModel dm = (DefaultTableModel) tableSaP.getModel();
					// dm.getDataVector().removeAllElements();

					/*
					 * List<SanPham> listSP = sanphamDAO.layTatCaSanPham(); boolean rs = false; for
					 * (SanPham sanPham : listSP) { if (sanPham.getMaSanPham().equals(maSP)) { rs =
					 * true; break; } }
					 */
					/*
					 * if (rs) {
					 * 
					 * model.addRow(new Object[] {sp.getMaSanPham(), sp.getTenSanPham(),
					 * hsx.getTenHangSanXuat(), loai.getTenLoai(), sp.getKichThuoc(),
					 * sp.getMauSac(), sp.getSoLuong(), sp.getGiaSanPham() });
					 * 
					 * int row = tableSanPham.getSelectedRowCount(); for (int i = 0; i < row; i++) {
					 * if (tableSanPham.getValueAt(i, 0).toString().equals(maSP)) {
					 * tableSanPham.setRowSelectionInterval(i, i);
					 * 
					 * } } txtSL.setText("1"); txtSL.selectAll(); txtSL.requestFocus(); } else {
					 * lblThongBao.setText("Mã sản phẩm không tồn tại"); txtMaSP.selectAll();
					 * txtMaSP.requestFocus(); }
					 */
				} catch (Exception e1) {

					e1.printStackTrace();
				}
			}
		});
		txtMaSP.setBounds(155, 230, 96, 25);
		pTaoHoaDonBH.add(txtMaSP);
		txtMaSP.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Nhập mã sản phẩm\r\n");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(28, 230, 123, 25);
		pTaoHoaDonBH.add(lblNewLabel_2);

		txtSL = new JTextField();
		txtSL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					themSPVaoHDBH();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		txtSL.setColumns(10);
		txtSL.setBounds(460, 425, 96, 25);
		pTaoHoaDonBH.add(txtSL);

		btnXoa = new JButton("Xóa");
		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = tableChiTietHDBH.getSelectedRow();
				int sl = (int) tableChiTietHDBH.getValueAt(row, 7);
				String maSP = tableChiTietHDBH.getValueAt(row, 1).toString();
				try {
					SanPhamDAO sanphamDAO = null;
					try {
						sanphamDAO = (SanPhamDAO) Naming.lookup("rmi://localhost:9999/sanpham");
					} catch (MalformedURLException | NotBoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					SanPham sp = sanphamDAO.laySanPhamTheoMa(maSP);
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				model.removeRow(row);
				if (kiemtraDongChuaMaSP(maSP) != -1) {
					int n = kiemtraDongChuaMaSP(maSP);
					tableSanPham.setValueAt((int) tableSanPham.getValueAt(n, 6) + sl, n, 6);
					// int newSoLuong = (int) tableSanPham.getValueAt(row, 6) - sl;
					// tableSanPham.setValueAt(newSoLuong, row, 6);
					// tableChiTietHDBH.setValueAt((int) tableChiTietHDBH.getValueAt(row, 7)*
					// (double) tableChiTietHDBH.getValueAt(row, 8), n, 9);
					// double capnhatTongTien = tableChiTietHDBH.getValueAt(row, 7)
					txtTongTien.setText(String.valueOf(tinhTongTien()));
					int num = tableChiTietHDBH.getRowCount();
					for (int i = 0; i < num; i++) {
						tableChiTietHDBH.setValueAt(i + 1, i, 0);
					}
				}

			}
		});
		btnXoa.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnXoa.setBackground(new Color(255, 0, 0));
		btnXoa.setBounds(1151, 501, 85, 25);
		pTaoHoaDonBH.add(btnXoa);

		lblThongBao = new JLabel("");
		lblThongBao.setForeground(Color.RED);
		lblThongBao.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblThongBao.setBounds(434, 22, 656, 25);
		pTaoHoaDonBH.add(lblThongBao);

		JLabel lblNewLabel_3 = new JLabel("Mã hóa đơn");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(50, 190, 101, 13);
		pTaoHoaDonBH.add(lblNewLabel_3);

		lblMaHD = new JLabel("");
		lblMaHD.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMaHD.setBounds(270, 190, 186, 13);
		pTaoHoaDonBH.add(lblMaHD);
		lblMaHD.setText(generateKeyDAO.getKey("HoaDonBanHang"));

		btnThemVaoCTHDBH = new JButton("");
		btnThemVaoCTHDBH.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					themSPVaoHDBH();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnThemVaoCTHDBH.setIcon(new ImageIcon(QuanLyBanHang.class.getResource("/img/icons8_expand_arrow_25px.png")));
		btnThemVaoCTHDBH.setBounds(420, 425, 25, 25);
		pTaoHoaDonBH.add(btnThemVaoCTHDBH);

		btnCapNhatSoLuong = new JButton("");
		btnCapNhatSoLuong.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				capnhatSLSP();
			}
		});
		btnCapNhatSoLuong.setIcon(new ImageIcon(QuanLyBanHang.class.getResource("/img/icons8_update_25px.png")));
		btnCapNhatSoLuong.setBounds(571, 425, 25, 25);
		pTaoHoaDonBH.add(btnCapNhatSoLuong);

		btnXoaSP = new JButton("");
		btnXoaSP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				xoaSPFromCTBH();
			}
		});
		btnXoaSP.setIcon(new ImageIcon(QuanLyBanHang.class.getResource("/img/icons8_cancel_25px.png")));
		btnXoaSP.setBounds(623, 425, 25, 25);
		pTaoHoaDonBH.add(btnXoaSP);

	}

	public void inHoaDon(String maHD) {
		try {
			String sql = "SELECT HoaDonBanHang.maHoaDonBanHang, HoaDonBanHang.ngayLapHoaDon, KhachHang.tenKhachHang, KhachHang.gioiTinh,KhachHang.diaChi, NhanVienBanHang.tenNhanVien, SanPham.maSanPham, SanPham.tenSanPham, HangSanXuat.tenHangSanXuat, \r\n"
					+ "                  LoaiSanPham.tenLoai, SanPham.kichThuoc, SanPham.mauSac, ChiTietHoaDonBanHang.soLuong,SanPham.giaSanPham, KhachHang.soCMND, KhachHang.soDienThoai, ChiTietHoaDonBanHang.thanhTien, HoaDonBanHang.tongTien, HoaDonBanHang.tienKhachDua,HoaDonBanHang.tienTraLai\r\n"
					+ "FROM     ChiTietHoaDonBanHang INNER JOIN\r\n"
					+ "                  HoaDonBanHang ON ChiTietHoaDonBanHang.maHoaDonBanHang = HoaDonBanHang.maHoaDonBanHang INNER JOIN\r\n"
					+ "                  KhachHang ON HoaDonBanHang.maKhachHang = KhachHang.maKhachHang INNER JOIN\r\n"
					+ "                  NhanVienBanHang ON HoaDonBanHang.maNhanVien = NhanVienBanHang.maNhanVien INNER JOIN\r\n"
					+ "                  SanPham ON ChiTietHoaDonBanHang.maSanPham = SanPham.maSanPham INNER JOIN\r\n"
					+ "                  HangSanXuat ON SanPham.maHangSanXuat = HangSanXuat.maHangSanXuat INNER JOIN\r\n"
					+ "                  LoaiSanPham ON SanPham.maLoaiSanPham = LoaiSanPham.maLoaiSanPham\r\n"
					+ "WHERE HoaDonBanHang.maHoaDonBanHang = '" + maHD + "'";

			JasperDesign jasperDesign = JRXmlLoader.load("src/main/java/Report/HoaDonRepoter.jrxml");
			JRDesignQuery updateQuery = new JRDesignQuery();
			updateQuery.setText(sql);
			jasperDesign.setQuery(updateQuery);

			JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
			JasperPrint p = JasperFillManager.fillReport(jasperReport, null, ConnectDB.getConnection());
			JasperViewer.viewReport(p, false);

			System.out.println("hello");
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public void xemHoaDon(String maHD) {
		try {
			String sql = "SELECT HoaDonBanHang.maHoaDonBanHang, HoaDonBanHang.ngayLapHoaDon, KhachHang.tenKhachHang, KhachHang.gioiTinh,KhachHang.diaChi, NhanVienBanHang.tenNhanVien, SanPham.maSanPham, SanPham.tenSanPham, HangSanXuat.tenHangSanXuat, \r\n"
					+ "                  LoaiSanPham.tenLoai, SanPham.kichThuoc, SanPham.mauSac, ChiTietHoaDonBanHang.soLuong,SanPham.giaSanPham, KhachHang.soCMND, KhachHang.soDienThoai, ChiTietHoaDonBanHang.thanhTien, HoaDonBanHang.tongTien, HoaDonBanHang.tienKhachDua,HoaDonBanHang.tienTraLai\r\n"
					+ "FROM     ChiTietHoaDonBanHang INNER JOIN\r\n"
					+ "                  HoaDonBanHang ON ChiTietHoaDonBanHang.maHoaDonBanHang = HoaDonBanHang.maHoaDonBanHang INNER JOIN\r\n"
					+ "                  KhachHang ON HoaDonBanHang.maKhachHang = KhachHang.maKhachHang INNER JOIN\r\n"
					+ "                  NhanVienBanHang ON HoaDonBanHang.maNhanVien = NhanVienBanHang.maNhanVien INNER JOIN\r\n"
					+ "                  SanPham ON ChiTietHoaDonBanHang.maSanPham = SanPham.maSanPham INNER JOIN\r\n"
					+ "                  HangSanXuat ON SanPham.maHangSanXuat = HangSanXuat.maHangSanXuat INNER JOIN\r\n"
					+ "                  LoaiSanPham ON SanPham.maLoaiSanPham = LoaiSanPham.maLoaiSanPham\r\n"
					+ "WHERE HoaDonBanHang.maHoaDonBanHang = '" + maHD + "'";

			JasperDesign jasperDesign = JRXmlLoader.load("src/main/java/Report/HoaDonRepoter1.jrxml");
			// JasperDesign jasperDesign = JRXmlLoader.load("C:\\Users\\voson\\OneDrive -
			// Industrial University of HoChiMinh
			// City\\Documents\\TH\\PTUD\\Nhom12PTUD.BTL_QuanLyCuaHangBanDoThoiTrangSSPN12\\src\\main\\java\\Report/HoaDonRepoter1.jrxml");
			JRDesignQuery updateQuery = new JRDesignQuery();
			updateQuery.setText(sql);
			jasperDesign.setQuery(updateQuery);

			JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
			JasperPrint p = JasperFillManager.fillReport(jasperReport, null, ConnectDB.getConnection());
			JasperViewer.viewReport(p, false);

			System.out.println("hello");
		} catch (Exception e) {
			// TODO: handle exception
		}

	}
	
	public void timKiem() {
		if (txtSDT_CMND.getText().equals("") && dateChooser.getDate() != null) {
			timkiemTheoThoiGian();
		} else if (!txtSDT_CMND.getText().equals("") && dateChooser.getDate() == null) {
			timkiemTheoCMND_Ten_SDT();
		} else if (!txtSDT_CMND.getText().equals("") && dateChooser.getDate() != null) {
			timkiemTheoCMND_Ten_SDT_Ngay();
		} else {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập thông tin tìm kiếm!");
		}
	}

	protected void timkiemTheoSDTorCMND() {
		if (txtSDT_CMND.getText().length() == 10)
			timkiemTheoDienThoai();
		else if (txtSDT_CMND.getText().length() == 12)
			timkiemTheoCMND();

	}

	private void timkiemTheoDienThoai() {
		String sdt = txtSDT_CMND.getText().trim();
		int num = 1;
		// List<KhachHang> listKH = khachHangDAO.
		KhachHang kh = khachHangDAO.layKhachHangTheoDienthoai(sdt);
		xoaHetDuLieuTrenTableModel();
		List<HoaDonBanHang> listHDBH = hoaDonBanHangDAO.layDanhSachHoaDonBanHangTheoMaKhachHang(kh.getMaKhachHang());
		for (HoaDonBanHang hdbh : listHDBH) {
			Date day = hdbh.getNgayLapHoaDon();
			double tongTien = tinhTongTienHoaDonBanHang(hdbh.getMaHoaDonBanHang());
			model.addRow(new Object[] { num, hdbh.getMaHoaDonBanHang(), hdbh.getKhachHang().getMaKhachHang(),
					hdbh.getNhanVienBanHang().getMaNhanVien(), df.format(day), tongTien, hdbh.getGhiChu(),
					hdbh.getTienKhachDua() });
			num++;
		}

	}

	private void timkiemTheoCMND() {
		String sdt = txtSDT_CMND.getText().trim();
		int num = 1;
		// List<KhachHang> listKH = khachHangDAO.
		KhachHang kh = khachHangDAO.layKhachhangTheoCMND(sdt);
		xoaHetDuLieuTrenTableModel();
		List<HoaDonBanHang> listHDBH = hoaDonBanHangDAO.layDanhSachHoaDonBanHangTheoMaKhachHang(kh.getMaKhachHang());
		for (HoaDonBanHang hdbh : listHDBH) {
			Date day = hdbh.getNgayLapHoaDon();
			double tongTien = tinhTongTienHoaDonBanHang(hdbh.getMaHoaDonBanHang());
			model.addRow(new Object[] { num, hdbh.getMaHoaDonBanHang(), hdbh.getKhachHang().getMaKhachHang(),
					hdbh.getNhanVienBanHang().getMaNhanVien(), df.format(day), tongTien, hdbh.getGhiChu(),
					hdbh.getTienKhachDua() });
			num++;
		}
	}

	protected void timkiemTheoThoiGian() {
		Date date = dateChooser.getDate();
		df = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat df1 = new SimpleDateFormat("dd/MM/yyyy");
		int num = 1;
		List<HoaDonBanHang> listHDBH = hoaDonBanHangDAO.layDanhSachHoaDonBanHangTheoNgay(date.getYear() + 1900,
				date.getMonth() + 1, date.getDate());
		if(listHDBH.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Không tìm thấy thông tin hóa đơn, vui lòng nhập lại");
			dateChooser.requestFocus();
		}
		else if (listHDBH != null) {
			xoaHetDuLieuTrenTableModel();
			model = (DefaultTableModel) tableHDBH.getModel();
			for (HoaDonBanHang hdbh : listHDBH) {
				Date day = hdbh.getNgayLapHoaDon();
				double tongTien = tinhTongTienHoaDonBanHang(hdbh.getMaHoaDonBanHang());
				model.addRow(new Object[] { num, hdbh.getMaHoaDonBanHang(), hdbh.getKhachHang().getTenKhachHang(),
						hdbh.getNhanVienBanHang().getTenNhanVien(), df1.format(day), tongTien, hdbh.getGhiChu(),
						hdbh.getTienKhachDua() });
				num++;
			}
		} 
	}

	private void timkiemTheoCMND_Ten_SDT() {
		String key = txtSDT_CMND.getText();
		int num = 1;
		SimpleDateFormat df1 = new SimpleDateFormat("dd/MM/yyyy");
		List<HoaDonBanHang> listHDBH = hoaDonBanHangDAO.layDanhSachHoaDonBanHangTheoTenKhachHang_SDT_CMND(key);
		if(listHDBH.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Không tìm thấy thông tin hóa đơn, vui lòng nhập lại");
			txtSDT_CMND.selectAll();
			txtSDT_CMND.requestFocus();
		}
		else if (listHDBH != null) {
			xoaHetDuLieuTrenTableModel();
			model = (DefaultTableModel) tableHDBH.getModel();
			for (HoaDonBanHang hdbh : listHDBH) {
				Date day = hdbh.getNgayLapHoaDon();
				double tongTien = tinhTongTienHoaDonBanHang(hdbh.getMaHoaDonBanHang());
				model.addRow(new Object[] { num, hdbh.getMaHoaDonBanHang(), hdbh.getKhachHang().getTenKhachHang(),
						hdbh.getNhanVienBanHang().getTenNhanVien(), df1.format(day), tongTien, hdbh.getGhiChu(),
						hdbh.getTienKhachDua() });
				num++;
			}
		}
	}

	private void timkiemTheoCMND_Ten_SDT_Ngay() {
		String key = txtSDT_CMND.getText();
		int num = 0;
		Date date = dateChooser.getDate();
		df = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat df1 = new SimpleDateFormat("dd/MM/yyyy");
		List<HoaDonBanHang> listHDBH = hoaDonBanHangDAO.layDanhSachHoaDonBanHangTheoTenKhachHang_SDT_CMND_Ngay(key,
				date.getYear() + 1900, date.getMonth() + 1, date.getDate());
		System.out.println(listHDBH);
		if(listHDBH.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Không tìm thấy thông tin hóa đơn, vui lòng nhập lại");
			dateChooser.setDate(null);
			txtSDT_CMND.selectAll();
			txtSDT_CMND.requestFocus();
		}
		else if (listHDBH != null) {
			xoaHetDuLieuTrenTableModel();
			model = (DefaultTableModel) tableHDBH.getModel();
			for (HoaDonBanHang hdbh : listHDBH) {
				Date day = hdbh.getNgayLapHoaDon();
				double tongTien = tinhTongTienHoaDonBanHang(hdbh.getMaHoaDonBanHang());
				model.addRow(new Object[] { num +1, hdbh.getMaHoaDonBanHang(), hdbh.getKhachHang().getTenKhachHang(),
						hdbh.getNhanVienBanHang().getTenNhanVien(), df1.format(day), tongTien, hdbh.getGhiChu(),
						hdbh.getTienKhachDua() });
				num++;
			}
		}
	}

	public void timkiemKhachHang() {
		String key = txtLuaChonKhachHang.getText();
		List<KhachHang> listKH = khachHangDAO.layDanhSachKhachHangTheoTen_SDT_CMND(key);
		if (listKH != null) {
			for (KhachHang kh : listKH) {
				txtTenKH.setText(kh.getTenKhachHang());
			}

		} else {
			JOptionPane.showMessageDialog(this,
					"Không tìm thấy thông tin khách hàng, vui lòng nhập lại thông tin khách hàng");
			txtLuaChonKhachHang.selectAll();
			txtLuaChonKhachHang.requestFocus();
		}
	}

	public void showTimKiemKH(String key) throws ClassNotFoundException, SQLException {
		List<KhachHang> listKH = khachHangDAO.layDanhSachKhachHangTheoTen_SDT_CMND(key);
		int tblRow = tableModel1.getRowCount();
		for (int i = tblRow - 1; i >= 0; i--) {
			tableModel1.removeRow(i);
		}
		for (KhachHang kh : listKH) {
			tableModel1.addRow(
					new Object[] { kh.getMaKhachHang(), kh.getTenKhachHang(), kh.getSoDienThoai(), kh.getSoCMND() });
		}
	}

	private void xoaHetDuLieuTrenTableModel() {
		DefaultTableModel dm = (DefaultTableModel) tableHDBH.getModel();
		dm.getDataVector().removeAllElements();
	}

	/*
	 * public void showcomboData() { for (HoaDonBanHang hdbh : listHDBH) {
	 * cboMaHD.addItem(hdbh.getMaHoaDonBanHang()); } }
	 */

	public void showTable() {
		int num = 1;
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		List<HoaDonBanHang> listHDBH = hoaDonBanHangDAO.layTatCaHoaDonBanHang();
		for (HoaDonBanHang hdbh : listHDBH) {
			Date day = hdbh.getNgayLapHoaDon();
			double tongTien = tinhTongTienHoaDonBanHang(hdbh.getMaHoaDonBanHang());
			model.addRow(new Object[] { num, hdbh.getMaHoaDonBanHang(), hdbh.getKhachHang().getTenKhachHang(),
					hdbh.getNhanVienBanHang().getTenNhanVien(), df.format(day), currencyVN.format(tongTien),
					hdbh.getGhiChu(), currencyVN.format(hdbh.getTienKhachDua()) });
			num++;
		}
	}

	public double tinhTongTienHoaDonBanHang(String maHDBH) {
		List<ChiTietHoaDonBanHang> listCTBH = chiTietBanHangDAO.layDanhSachCTHoaDonBanHangTheoMaHDBH(maHDBH);
		double tongTien = 0;
		for (ChiTietHoaDonBanHang ctbh : listCTBH) {
			tongTien += ctbh.getSanPham().getGiaSanPham() * ctbh.getSoLuong();
			System.out.println(tongTien);
		}
		System.out.println(tongTien);
		return tongTien;
	}

	public void showTableCTHDBH(String ma) {
		int num = 1;
		List<ChiTietHoaDonBanHang> listCTHDBH = chiTietBanHangDAO.layDanhSachCTHoaDonBanHangTheoMaHDBH(ma);
		for (ChiTietHoaDonBanHang cthdbh : listCTHDBH) {
			double thanhTien = cthdbh.getSoLuong() * cthdbh.getSanPham().getGiaSanPham();
			model.addRow(new Object[] { num, cthdbh.getSanPham().getMaSanPham(), cthdbh.getSanPham().getTenSanPham(),
					cthdbh.getSanPham().getKichThuoc(), cthdbh.getSanPham().getMauSac(),
					cthdbh.getSanPham().getLoaiSanPham().getTenLoai(), cthdbh.getSoLuong(),
					cthdbh.getSanPham().getGiaSanPham(), thanhTien, "mua mới" });
			num++;
		}
	}

	public void showTableSanPham() {
		int num = 1;
		List<SanPham> listSP = sanphamDAO.layTatCaSanPhamKhacKhong();
		for (SanPham sp : listSP) {
			model.addRow(new Object[] { sp.getMaSanPham(), sp.getTenSanPham(), sp.getHangSanXuat().getTenHangSanXuat(),
					sp.getLoaiSanPham().getTenLoai(), sp.getKichThuoc(), sp.getMauSac(), sp.getSoLuong(),
					currencyVN.format(sp.getGiaSanPham()) });
			num++;
		}
	}

	/*
	 * private void showComboBox() { List<KhachHang> listKH =
	 * khachHangDAO.layTatCaKhachHang(); for (KhachHang kh : listKH) {
	 * model.addElement(kh.getMaKhachHang());
	 * 
	 * } }
	 */

	private int kiemtraMaSP(String ma) {
		int row = tableChiTietHDBH.getRowCount();
		System.out.println(row);
		int n = -1;
		for (int i = 0; i < row; i++) {
			if (tableChiTietHDBH.getValueAt(i, 1).toString().equalsIgnoreCase(ma)) {
				n = i;
				break;
			}
		}
		System.out.println(n);
		return n;

	}

	private void luuThongTinChiTietHDBH() {
		int row = tableChiTietHDBH.getRowCount();
		for (int i = 0; i < row; i++) {
			String maSP = tableChiTietHDBH.getValueAt(i, 1).toString();
			SanPham sp = new SanPham(maSP);
			String maHD = lblMaHD.getText();
			HoaDonBanHang hd = new HoaDonBanHang(maHD);
			int soLuong = (int) tableChiTietHDBH.getValueAt(i, 7);

			ChiTietHoaDonBanHang cthdbh = new ChiTietHoaDonBanHang(soLuong, sp, hd);
			try {
				cthdbh.setThanhTien(currencyVN.parse(tableChiTietHDBH.getValueAt(i, 9).toString()).doubleValue());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			chiTietBanHangDAO.themChiTietHoaDon(cthdbh);
			System.out.println(cthdbh);
		}
	}

	private void capnhatSanPham() {
		int row = tableChiTietHDBH.getRowCount();
		for (int i = 0; i < row; i++) {
			String maSP = tableChiTietHDBH.getValueAt(i, 1).toString();
			SanPham sp = sanphamDAO.laySanPhamTheoMa(maSP);
			int soLuongMua = (int) tableChiTietHDBH.getValueAt(i, 7);
			int soLuongConLai = sp.getSoLuong() - soLuongMua;
			HangSanXuat hsx = new HangSanXuat(sp.getHangSanXuat().getMaHangSanXuat());
			LoaiSanPham loai = new LoaiSanPham(sp.getLoaiSanPham().getMaLoaiSanPham());
			SanPham sanPham = new SanPham(maSP, sp.getTenSanPham(), sp.getGiaSanPham(), sp.getKichThuoc(),
					sp.getMauSac(), soLuongConLai, hsx, loai);
			// SanPham sanPham = new SanPham(maSP, soLuongConLai);
			System.out.println(sanPham);
			boolean rs = sanphamDAO.capNhatSanPham(sanPham);
			System.out.println(rs);
			/*
			 * if (rs) { JOptionPane.showMessageDialog(rootPane,
			 * "Tạo hóa đơn  thành công"); } else JOptionPane.showMessageDialog(rootPane,
			 * "Tạo mới hóa đơn không thành công");
			 */
		}
	}

	private boolean kiemtraSoLuongMua(int sl, int row) {
		boolean rs = false;
		sl = Integer.parseInt(txtSL.getText());
		int numMax = (int) tableSanPham.getValueAt(row, 6);
		if (numMax == 0) {
			JOptionPane.showMessageDialog(this, "Lỗi: Sản phẩm đã hết, vui lòng chọn sản phẩm khác.");
			// lblThongBao.setText("Lỗi: Sản phẩm đã hết, vui lòng chọn sản phẩm khác.");
			return rs;
		} else if (sl <= 0) {
			JOptionPane.showMessageDialog(this, "Lỗi: Không nhập số âm hoặc số 0.");
			// lblThongBao.setText("Lỗi: Không nhập số âm hoặc số 0.");
			txtSL.setText("1");
			txtSL.selectAll();
			txtSL.requestFocus();
			return rs;
		} else if (sl > numMax) {
			JOptionPane.showMessageDialog(this, "Lỗi: Bạn đã nhập quá số lượng của sản phẩm.");
			// lblThongBao.setText("Lỗi: Bạn đã nhập quá số lượng của sản phẩm.");
			txtSL.setText(String.valueOf(numMax));
			txtSL.selectAll();
			txtSL.requestFocus();
			return rs;
		}
		return true;
	}

	private boolean kiemtraSoLuongMua_TKSP(int sl, int row) {
		boolean rs = false;
		sl = Integer.parseInt(txtSL.getText());
		int numMax = (int) tableSanPham.getValueAt(row, 6);
		if (numMax == 0) {
			// JOptionPane.showMessageDialog(rootPane, "Lỗi: Sản phẩm đã hết, vui lòng chọn
			// sản phẩm khác.");
			lblThongBao.setText("Lỗi: Sản phẩm đã hết, vui lòng chọn sản phẩm khác.");
			return rs;
		} else if (sl <= 0) {
			// JOptionPane.showMessageDialog(rootPane, "Lỗi: Không nhập số âm hoặc số 0.");
			lblThongBao.setText("Lỗi: Không nhập số âm hoặc số 0.");
			txtSL.setText("1");
			txtSL.selectAll();
			txtSL.requestFocus();
			return rs;
		} else if (sl > numMax) {
			// JOptionPane.showMessageDialog(rootPane, "Lỗi: Bạn đã nhập quá số lượng của
			// sản phẩm.");
			lblThongBao.setText("Lỗi: Bạn đã nhập quá số lượng của sản phẩm.");
			txtSL.setText(String.valueOf(numMax));
			txtSL.selectAll();
			txtSL.requestFocus();
			return rs;
		}
		return true;
	}

	private double tinhTongTien() {
		double tongTien = 0;
		int row = tableChiTietHDBH.getRowCount();
		for (int i = 0; i < row; i++) {
			try {
				tongTien += (Double) currencyVN.parse(tableChiTietHDBH.getValueAt(i, 9).toString()).doubleValue();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return tongTien;
	}

	public void themVaoLuaChonKh(String s) {
		txtLuaChonKhachHang.setText(s);
	}

	private int kiemtraDongChuaMaSP(String ma) {
		int row = tableSanPham.getRowCount();
		int n = -1;
		for (int i = 0; i < row; i++) {
			if (tableSanPham.getValueAt(i, 0).toString().equalsIgnoreCase(ma)) {
				n = i;
				break;
			}
		}
		return n;
	}

	public void kiemtraHoaDonLuu() throws ParseException {
		if (txtLuaChonKhachHang.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "Lỗi vui lòng nhập thông tin khách hàng");
			txtLuaChonKhachHang.selectAll();
			txtLuaChonKhachHang.requestFocus();
		} else if (txtTongTien.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "Lỗi vui lòng chọn và thêm sản phẩm vào hóa đơn mua hàng");
			txtMaSP.selectAll();
			txtMaSP.requestFocus();
		} else if (txtTienKhachDua.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "Lỗi vui lòng nhập tiền khách đưa");
			txtTienKhachDua.selectAll();
			txtTienKhachDua.requestFocus();
		} else {
			int rowTableSP = tableSanPham.getSelectedRow();
			int rowTableCTHDBH = tableChiTietHDBH.getSelectedRow();

			// Lưu thông tin hóa đơn
			KhachHang kh;
			String maHD = lblMaHD.getText();
			// double tienKhachDua = Double.parseDouble(txtTienKhachDua.getText());

			double tienKhachDua = currencyVN.parse(txtTienKhachDua.getText()).doubleValue();

			String thongtinKH = txtLuaChonKhachHang.getText();
			String maKH = "";
			if (thongtinKH.length() == 10 || thongtinKH.length() == 11) {
				kh = (KhachHang) khachHangDAO.layKhachHangTheoDienthoai(thongtinKH);
				maKH = kh.getMaKhachHang();
			} else if (thongtinKH.length() == 9 || thongtinKH.length() == 12) {
				kh = (KhachHang) khachHangDAO.layKhachhangTheoCMND(thongtinKH);
				maKH = kh.getMaKhachHang();
			}

			KhachHang khachHang = new KhachHang(maKH);

			/*
			 * NhanVienBanHang nv = (NhanVienBanHang)
			 * nhanVienDAO.layDanhSachNhanVienTheoTen(lblTenNV.getText()); String maNV =
			 * nv.getMaNhanVien(); NhanVienBanHang nhanVien = new NhanVienBanHang(maNV);
			 */

			ChuCuaHang cch = chucuahangDAO.layThongTinChuCuaHang();
			String maCCH = cch.getMaChuCuaHang();
			ChuCuaHang chu = new ChuCuaHang(maCCH);

			// String ngay = lblNgay.getText();
			SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy, hh:mm:ss");
			String ngay = df.format(new java.util.Date());
			System.out.println(ngay);
			TaiKhoan tk = taikhoanDAO.layThongTinTaiKhoanTheoTrangThai(true);
			NhanVienBanHang nv = nhanVienDAO.layNhanVienTheoMa(tk.getNhanVienBanHang().getMaNhanVien());
			String maNV = nv.getMaNhanVien();
			NhanVienBanHang nhanVien = new NhanVienBanHang(maNV);

			try {
				HoaDonBanHang hdbh = new HoaDonBanHang(maHD, df.parse(ngay), "Mua mới", tienKhachDua, khachHang,
						nhanVien, chu);
				hdbh.setTongTien(currencyVN.parse(txtTongTien.getText()).doubleValue());
				// hdbh.setTongTien(currencyVN.parse(txtTongTien.getText()).doubleValue());
				// currencyVN.parse(txtTongTien.getText()).intValue()
				// hdbh.setTienTraLai(Double.parseDouble(txtTienTraLai.getText()));
				hdbh.setTienTraLai(tienKhachDua - currencyVN.parse(txtTongTien.getText()).doubleValue());
				boolean rs = hoaDonBanHangDAO.themHoaDonBanHang(hdbh);
				luuThongTinChiTietHDBH();
				capnhatSanPham();
				if (rs) {
					JOptionPane.showMessageDialog(this, "Lưu hóa đơn thành công, bây giờ có thể in hóa đơn");
					// lblThongBao.setText("Tạo hóa đơn thành công");
					btnInHD.setEnabled(true);
				} else
					JOptionPane.showMessageDialog(this, "Lưu mới hóa đơn không thành công");
				// lblThongBao.setText("Tạo hóa đơn không thành công");
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			// Lưu thông tin chi tiết hóa đơn
			// String maCTHD = (String) tableChiTietHDBH.getValueAt(rowTableCTHDBH, 1);

			// Cập nhật lại số lượng sản phẩm
		}
	}

	public void xoaSPFromCTBH() {
		int row = tableChiTietHDBH.getSelectedRow();
		int sl = (int) tableChiTietHDBH.getValueAt(row, 7);
		String maSP = tableChiTietHDBH.getValueAt(row, 1).toString();
		SanPham sp = sanphamDAO.laySanPhamTheoMa(maSP);
		int dialog = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa sản phẩm này ra khỏi hóa đơn?",
				"Thông báo xác nhận xóa sản phẩm ra khỏi hóa đơn", JOptionPane.YES_NO_OPTION);
		if (dialog == JOptionPane.YES_OPTION) {
			model = (DefaultTableModel) tableChiTietHDBH.getModel();
			model.removeRow(row);
			if (kiemtraDongChuaMaSP(maSP) != -1) {
				int n = kiemtraDongChuaMaSP(maSP);
				tableSanPham.setValueAt((int) tableSanPham.getValueAt(n, 6) + sl, n, 6);
				// int newSoLuong = (int) tableSanPham.getValueAt(row, 6) - sl;
				// tableSanPham.setValueAt(newSoLuong, row, 6);
				// tableChiTietHDBH.setValueAt((int) tableChiTietHDBH.getValueAt(row, 7)*
				// (double) tableChiTietHDBH.getValueAt(row, 8), n, 9);
				// double capnhatTongTien = tableChiTietHDBH.getValueAt(row, 7)
				txtTongTien.setText(currencyVN.format(tinhTongTien()));
				int num = tableChiTietHDBH.getRowCount();
				for (int i = 0; i < num; i++) {
					tableChiTietHDBH.setValueAt(i + 1, i, 0);
				}
			}
		}

	}

	public void thoatKhoiTrangThemHoaDon() {
		int n = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn thoát khỏi trang thêm hóa đơn?",
				"Thông báo xác nhận thoát khỏi trang thêm hóa đơn", JOptionPane.YES_NO_OPTION);
		if (n == JOptionPane.YES_OPTION) {
			add(pXemThongTonHDBH);
			pXemThongTonHDBH.setVisible(true);
			pTaoHoaDonBH.setVisible(false);

			xoaHetDuLieuTrenTableModel();
			model = (DefaultTableModel) tableHDBH.getModel();
			showTable();
			DefaultTableModel dm1 = (DefaultTableModel) tableSanPham.getModel();
			dm1.getDataVector().removeAllElements();
			model = (DefaultTableModel) tableSanPham.getModel();
			showTableSanPham();

			DefaultTableModel dm = (DefaultTableModel) tableChiTietHDBH.getModel();
			dm.getDataVector().removeAllElements();
			txtLuaChonKhachHang.setText("");
			txtTenKH.setText("");
			txtTienKhachDua.setText("");
			txtTienTraLai.setText("");
			txtTongTien.setText("");
			lblMaHD.setText(generateKeyDAO.getKey("HoaDonBanHang"));
			lblThongBao.setText("");
			txtMaSP.setText("");
			btnInHD.setEnabled(false);
		}

	}

	public void themSPVaoHDBH() throws ParseException {
		if (!txtMaSP.getText().equals("")) {
			int row = kiemtraDongChuaMaSP(txtMaSP.getText());
			// String maSP = tableSanPham.getValueAt(row, 0).toString();
			String maSP = txtMaSP.getText();
			SanPham sp = sanphamDAO.laySanPhamTheoMa(maSP);
			int num = tableChiTietHDBH.getRowCount();
			int sl = Integer.parseInt(txtSL.getText());
			double thanhTien = sl * sp.getGiaSanPham();
			model = (DefaultTableModel) tableChiTietHDBH.getModel();
			if (row != -1) {
				if (kiemtraSoLuongMua(sl, row)) {
					if (num == 0) {
						model.addRow(new Object[] { num + 1, sp.getMaSanPham(), sp.getTenSanPham(),
								sp.getHangSanXuat().getTenHangSanXuat(), sp.getLoaiSanPham().getTenLoai(),
								sp.getKichThuoc(), sp.getMauSac(), sl, sp.getGiaSanPham(),
								currencyVN.format(thanhTien) });
						int newSoLuong = (int) tableSanPham.getValueAt(row, 6) - sl;
						tableSanPham.setValueAt(newSoLuong, row, 6);
						txtTongTien.setText(currencyVN.format(tinhTongTien()));
						// txtDonViTien.setText("VND");
					} else if (kiemtraMaSP(maSP) == -1) {
						model.addRow(new Object[] { num + 1, sp.getMaSanPham(), sp.getTenSanPham(),
								sp.getHangSanXuat().getTenHangSanXuat(), sp.getLoaiSanPham().getTenLoai(),
								sp.getKichThuoc(), sp.getMauSac(), sl, sp.getGiaSanPham(),
								currencyVN.format(thanhTien) });
						int newSoLuong = (int) tableSanPham.getValueAt(row, 6) - sl;
						tableSanPham.setValueAt(newSoLuong, row, 6);
						txtTongTien.setText(currencyVN.format(tinhTongTien()));
					} else if (kiemtraMaSP(maSP) != -1) {
						int n = kiemtraMaSP(maSP);

						tableChiTietHDBH.setValueAt((int) tableChiTietHDBH.getValueAt(n, 7) + sl, n, 7);

						// Cập nhật lại số lượng cho bảng sản phẩm
						int newSoLuong = (int) tableSanPham.getValueAt(row, 6) - sl;
						tableSanPham.setValueAt(newSoLuong, row, 6);

						double thanhTienNew = (int) tableChiTietHDBH.getValueAt(n, 7)
								* (double) tableChiTietHDBH.getValueAt(n, 8);
						tableChiTietHDBH.setValueAt(
								currencyVN.format((int) tableChiTietHDBH.getValueAt(n, 7) * sp.getGiaSanPham()), n, 9);

						txtTongTien.setText(currencyVN.format(tinhTongTien()));

					}
				}

				/*
				 * DefaultTableModel dm = (DefaultTableModel) tableSanPham.getModel();
				 * dm.getDataVector().removeAllElements(); model = (DefaultTableModel)
				 * tableSanPham.getModel(); showTableSanPham(); kiemtraDongChuaMaSP(maSP);
				 */
				// tableSanPham.setValueAt(table, kiemtraDongChuaMaSP(maSP), 6);
				txtMaSP.setText("");
				txtMaSP.requestFocus();
			} else {
				JOptionPane.showMessageDialog(this, "Sản phẩm đã hết vui lòng nhập sản phẩm khác!");
				txtMaSP.selectAll();
				txtMaSP.requestFocus();
			}
		} else {

			int row = tableSanPham.getSelectedRow();
			String maSP = tableSanPham.getValueAt(row, 0).toString();
			SanPham sp = sanphamDAO.laySanPhamTheoMa(maSP);
			int num = tableChiTietHDBH.getRowCount();
			int sl = Integer.parseInt(txtSL.getText());
			double thanhTien = sl * sp.getGiaSanPham();
			model = (DefaultTableModel) tableChiTietHDBH.getModel();

			if (kiemtraSoLuongMua(sl, row)) {
				if (num == 0) {
					model.addRow(new Object[] { num + 1, sp.getMaSanPham(), sp.getTenSanPham(),
							sp.getHangSanXuat().getTenHangSanXuat(), sp.getLoaiSanPham().getTenLoai(),
							sp.getKichThuoc(), sp.getMauSac(), sl, sp.getGiaSanPham(), currencyVN.format(thanhTien) });
					int newSoLuong = (int) tableSanPham.getValueAt(row, 6) - sl;
					tableSanPham.setValueAt(newSoLuong, row, 6);
					txtTongTien.setText(currencyVN.format(tinhTongTien()));
					// txtDonViTien.setText("VND");
				} else if (kiemtraMaSP(maSP) == -1) {
					model.addRow(new Object[] { num + 1, sp.getMaSanPham(), sp.getTenSanPham(),
							sp.getHangSanXuat().getTenHangSanXuat(), sp.getLoaiSanPham().getTenLoai(),
							sp.getKichThuoc(), sp.getMauSac(), sl, sp.getGiaSanPham(), currencyVN.format(thanhTien) });
					int newSoLuong = (int) tableSanPham.getValueAt(row, 6) - sl;
					tableSanPham.setValueAt(newSoLuong, row, 6);
					txtTongTien.setText(currencyVN.format(tinhTongTien()));
				} else if (kiemtraMaSP(maSP) != -1) {
					int n = kiemtraMaSP(maSP);

					tableChiTietHDBH.setValueAt((int) tableChiTietHDBH.getValueAt(n, 7) + sl, n, 7);

					// Cập nhật lại số lượng cho bảng sản phẩm
					int newSoLuong = (int) tableSanPham.getValueAt(row, 6) - sl;
					tableSanPham.setValueAt(newSoLuong, row, 6);

					double thanhTienNew = (int) tableChiTietHDBH.getValueAt(n, 7)
							* (double) tableChiTietHDBH.getValueAt(n, 8);
					tableChiTietHDBH.setValueAt(
							currencyVN.format((int) tableChiTietHDBH.getValueAt(n, 7) * sp.getGiaSanPham()), n, 9);

					txtTongTien.setText(currencyVN.format(tinhTongTien()));

				}
			}
		}
	}

	public void capnhatSLSP() {
		int row = tableChiTietHDBH.getSelectedRow();
		int sl = (int) tableChiTietHDBH.getValueAt(row, 7);
		int capnhatSL = Integer.parseInt(txtSL.getText());
		int capnhatSlChoBangSP = (int) tableChiTietHDBH.getValueAt(row, 7) - capnhatSL;
		int num = tableSanPham.getRowCount();
		String maSP = tableChiTietHDBH.getValueAt(row, 1).toString();
		SanPham sp = sanphamDAO.laySanPhamTheoMa(maSP);
		if (capnhatSL >= 1 && capnhatSlChoBangSP > 0) {
			tableChiTietHDBH.setValueAt(capnhatSL, row, 7);
			double capnhatThanhTien = capnhatSL * (double) tableChiTietHDBH.getValueAt(row, 8);
			tableChiTietHDBH.setValueAt(currencyVN.format(capnhatThanhTien), row, 9);
			if (kiemtraDongChuaMaSP(maSP) != -1) {
				int n = kiemtraDongChuaMaSP(maSP);
				tableSanPham.setValueAt((int) tableSanPham.getValueAt(n, 6) + capnhatSlChoBangSP, n, 6);
				// int newSoLuong = (int) tableSanPham.getValueAt(row, 6) - sl;
				// tableSanPham.setValueAt(newSoLuong, row, 6);
				// tableChiTietHDBH.setValueAt((int) tableChiTietHDBH.getValueAt(row, 7)*
				// (double) tableChiTietHDBH.getValueAt(row, 8), n, 9);
				// double capnhatTongTien = tableChiTietHDBH.getValueAt(row, 7)
				txtTongTien.setText(currencyVN.format(tinhTongTien()));
			}
		}
	}

	public void layThongTinNhanVien() {
		ChuCuaHang cch = new ChuCuaHang("CCH001");
		// NhanVienBanHang nvbh = nhanvienDAO.layDanhSachNhanVienTheoTinhTrang(true);
		// NhanVienBanHang nv = new NhanVienBanHang()));
		TaiKhoan tk = taikhoanDAO.layThongTinTaiKhoanTheoTrangThai(true);
		// System.out.println(tk);
		NhanVienBanHang nv = nhanVienDAO.layNhanVienTheoMa(tk.getNhanVienBanHang().getMaNhanVien());

		txtTenNV.setText(nv.getTenNhanVien());
	}

	public void tinhTienTraLai() {
		double tienKhachDua = Double.parseDouble(txtTienKhachDua.getText());
		txtTienKhachDua.setText(currencyVN.format(Double.valueOf(tienKhachDua)));
		// double tongTien = Double.parseDouble(txtTongTien.getText());
		double tienTraLai;
		try {
			tienTraLai = tienKhachDua - currencyVN.parse(txtTongTien.getText()).doubleValue();
			NumberFormat formatter = new DecimalFormat("#,##0 đ");
			formatter.format(tienTraLai);
			System.out.println(tienTraLai);
			if (tienTraLai < 0) {
				txtTienKhachDua.selectAll();
				txtTienKhachDua.requestFocus();
				JOptionPane.showMessageDialog(this, "Số tiền mà khách hàng trả không đủ");
				// lblThongBao.setText("Số tiền mà khách hàng trả không đủ");
			} else {
				txtTienTraLai.setText(String.valueOf(formatter.format(tienTraLai)));
				// lblDonViTien1.setText("VND");
			}
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
