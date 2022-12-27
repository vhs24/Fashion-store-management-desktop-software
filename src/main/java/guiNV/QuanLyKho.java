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

import org.hibernate.SessionFactory;

import javax.swing.border.EtchedBorder;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;

import Connect.ConnectDB;
import Custom.ButtonGradient;
import dao.ChiTietBanHangDAO;
import dao.ChiTietNhapKhoDAO;
import dao.ChuCuaHangDAO;
import dao.GenerateKeyDAO;
import dao.HangSanXuatDAO;
import dao.HoaDonBanHangDAO;
import dao.HoaDonNhapKhoDAO;
import dao.KhachHangDAO;
import dao.LoaiSanPhamDAO;
import dao.NhanVienDAO;
import dao.SanPhamDAO;
import dao.TaiKhoanDAO;
import dao_imp.ChiTietBanHangImp;
import dao_imp.ChiTietNhapKhoImp;
import dao_imp.ChuCuaHangImp;
import dao_imp.GenerateKeyImp;
import dao_imp.HangSanXuatImp;
import dao_imp.HoaDonBanHangImp;
import dao_imp.HoaDonNhapKhoImp;
import dao_imp.KhachHangImp;
import dao_imp.LoaiSanPhamImp;
import dao_imp.MySessionFactory;
import dao_imp.NhanVienImp;
import dao_imp.SanPhamImp;
import dao_imp.TaiKhoanImp;
import entity.ChiTietHoaDonBanHang;
import entity.ChiTietHoaDonNhapKho;
import entity.ChuCuaHang;
import entity.HangSanXuat;
import entity.HoaDonBanHang;
import entity.HoaDonNhapKho;
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

public class QuanLyKho extends JPanel {

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
	HoaDonNhapKhoDAO hoaDonNhapKhoDAO = new HoaDonNhapKhoImp(sessionFactory);
	ChiTietNhapKhoDAO chiTietNhapKhoDAO = new ChiTietNhapKhoImp(sessionFactory);
	KhachHangDAO khachHangDAO = new KhachHangImp(sessionFactory);
	NhanVienDAO nhanVienDAO = new NhanVienImp(sessionFactory);
	SanPhamDAO sanphamDAO = new SanPhamImp(sessionFactory);
	TaiKhoanDAO taikhoanDAO = new TaiKhoanImp(sessionFactory);
	ChuCuaHangDAO chucuahangDAO = new ChuCuaHangImp(sessionFactory);
	HangSanXuatDAO hsxDAO = new HangSanXuatImp(sessionFactory);
	LoaiSanPhamDAO loaispDAO = new LoaiSanPhamImp(sessionFactory);
	SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	List<KhachHang> listKH = khachHangDAO.layTatCaKhachHang();
	List<NhanVienBanHang> listNV = nhanVienDAO.layTatCaNhanVien();
	private JDateChooser dateChooser;
	private JLabel lblTenKH;
	private JPanel pXemThongTonHDBH;
	private JPanel pThemHDBH;
	private JPanel pTaoHoaDonBH;
	private JTable tableCTHDBanHang;
	private JTextField txtTenNV;
	private JTable tableChiTietHDBH;
	private JTextField txtTongTien;
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
	private JButton btnThemSP;
	private JPanel pThemSP;
	private JTextField txtMaSanPham;
	private JTextField txtTenSP;
	private JTextField txtSoLuong;
	private JTextField txtGia;
	private JTextField txtMaHSX;
	private JTextField txtTenHSX;
	private JTextField txtSDT;
	private JTextField txtDC;
	private JTextField txtTP;
	private JTextField txtQuocGia;
	private JTextField txtMaLoai;
	private JTextField txtTenLoai;
	private JTextField txtMoTa;
	private JButton btnThem;
	private JButton btnThoatNhapSP;
	private ButtonGradient btnThemHSX;
	private ButtonGradient btnXoaTrangHSX;
	private ButtonGradient btnThemLoai;
	private ButtonGradient btnXoaTrangLoai;
	private JComboBox cboHSX;
	private JComboBox cboLoai;
	private JComboBox cboKichThuoc;
	private JComboBox cboMau;
	private JLabel lblThongBaoThemSP;
	private JButton btnXoaSP;
	private NumberFormat currentLocale = NumberFormat.getInstance();
	private Locale localeVN = new Locale("vi", "VN");
	private NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);

	/**
	 * Create the panel.
	 */
	public QuanLyKho() {
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
		// Xem thong tin hoa don nhap kho
		pXemThongTonHDBH = new JPanel();
		pXemThongTonHDBH.setBackground(Color.WHITE);
		pXemThongTonHDBH.setBounds(10, 10, 1090, 680);
		add(pXemThongTonHDBH);
		pXemThongTonHDBH.setLayout(null);

		JLabel lblQuanLyBanHang = new JLabel("Quản lý kho");
		lblQuanLyBanHang.setFont(new Font("Tahoma", Font.PLAIN, 40));
		lblQuanLyBanHang.setBounds(20, 21, 358, 60);
		pXemThongTonHDBH.add(lblQuanLyBanHang);

		JScrollPane scrollPaneHDBH = new JScrollPane();
		scrollPaneHDBH.setBounds(20, 298, 1030, 340);
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
				new String[] { "STT", "M\u00E3 h\u00F3a \u0111\u01A1n", "Nh\u00E2n vi\u00EAn",
						"Ng\u00E0y l\u1EADp h\u00F3a \u0111\u01A1n", "T\u1ED5ng ti\u1EC1n", "Ghi ch\u00FA" }));
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

		JLabel lblSinThoi = new JLabel("Số điện thoại hoặc số cmnd");
		lblSinThoi.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSinThoi.setBounds(312, 25, 194, 24);
		pBoLocHDBH.add(lblSinThoi);

		txtSDT_CMND = new JTextField();
		txtSDT_CMND.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblTenKH.setText("");
			}
		});
		txtSDT_CMND.setBounds(500, 21, 140, 35);
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
				timKiemHD();

			}
		});
		btnTimKiem.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnTimKiem.setBounds(145, 90, 171, 47);
		pBoLocHDBH.add(btnTimKiem);

		JButton btnXoa = new ButtonGradient();
		btnXoa.setText("Xóa bộ lọc");
		((ButtonGradient) btnXoa).setColor2(Color.decode("#0099F7"));
		btnXoa.setIcon(new ImageIcon(GiaoDienChinh_ChuCuaHang.class.getResource("/img/cancel.png")));
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
		btnXoa.setBounds(350, 90, 171, 47);
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

		JLabel lblNewLabel = new JLabel("HÓA ĐƠN NHẬP KHO");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 35));
		lblNewLabel.setBounds(10, 0, 393, 49);
		pTaoHoaDonBH.add(lblNewLabel);

		JLabel lblNewLabel_1_1_1 = new JLabel("Tên nhân viên");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_1_1.setBounds(50, 120, 176, 35);
		pTaoHoaDonBH.add(lblNewLabel_1_1_1);

		txtTenNV = new JTextField();
		txtTenNV.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtTenNV.setEditable(false);
		txtTenNV.setColumns(10);
		txtTenNV.setBounds(270, 120, 186, 35);
		pTaoHoaDonBH.add(txtTenNV);

		layThongTinNhanVien();

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(46, 698, 1210, -418);
		pTaoHoaDonBH.add(scrollPane_2);

		btnInHD = new JButton("Xem hóa đơn");
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
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_2.setBounds(720, 70, 176, 35);
		pTaoHoaDonBH.add(lblNewLabel_1_2);

		txtTongTien = new JTextField();
		txtTongTien.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtTongTien.setColumns(10);
		txtTongTien.setBounds(874, 70, 186, 35);
		pTaoHoaDonBH.add(txtTongTien);

		btnTaoHD = new JButton("Lưu hóa đơn");
		btnTaoHD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				luuHDNK();
			}
		});
		btnTaoHD.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnTaoHD.setBounds(480, 100, 186, 35);
		pTaoHoaDonBH.add(btnTaoHD);

		JButton btnThoat = new JButton("Thoát");
		btnThoat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				thoatKhoiTrangTaoHDNK();
			}
		});
		btnThoat.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnThoat.setBounds(480, 140, 186, 35);
		pTaoHoaDonBH.add(btnThoat);

		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(20, 460, 1050, 170);
		pTaoHoaDonBH.add(scrollPane_3);

		tableChiTietHDBH = new JTable();
		tableChiTietHDBH.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = tableChiTietHDBH.getSelectedRow();
				txtSL.setText("1");
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
		scrollPane_4.setBounds(20, 270, 1050, 147);
		tableChiTietHDBH.setDefaultEditor(Object.class, null);
		pTaoHoaDonBH.add(scrollPane_4);

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
				String maSP = txtMaSP.getText();
				try {
					SanPham sp = sanphamDAO.laySanPhamTheoMa(maSP);
					// model = (DefaultTableModel) tableSanPham.getModel();
					DefaultTableModel dm = (DefaultTableModel) tableSanPham.getModel();
					System.out.println(kiemtraDongChuaMaSP(maSP));
					System.out.println(tableSanPham.getSelectedRowCount());
					dm.getDataVector().elementAt(kiemtraDongChuaMaSP(maSP));
					// dm.getDataVector().removeAllElements();
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
				if (!txtMaSP.getText().equals("")) {
					txtMaSP.setText("");
					DefaultTableModel dm = (DefaultTableModel) tableSanPham.getModel();
					dm.getDataVector().removeAllElements();
					model = (DefaultTableModel) tableSanPham.getModel();
					showTableSanPham();
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
									sp.getKichThuoc(), sp.getMauSac(), sl, sp.getGiaSanPham(), thanhTien });
							int newSoLuong = (int) tableSanPham.getValueAt(row, 6) - sl;
							tableSanPham.setValueAt(newSoLuong, row, 6);
							txtTongTien.setText(String.valueOf(tinhTongTien()));
							// txtDonViTien.setText("VND");
						} else if (kiemtraMaSP(maSP) == -1) {
							model.addRow(new Object[] { num + 1, sp.getMaSanPham(), sp.getTenSanPham(),
									sp.getHangSanXuat().getTenHangSanXuat(), sp.getLoaiSanPham().getTenLoai(),
									sp.getKichThuoc(), sp.getMauSac(), sl, sp.getGiaSanPham(), thanhTien });
							int newSoLuong = (int) tableSanPham.getValueAt(row, 6) - sl;
							tableSanPham.setValueAt(newSoLuong, row, 6);
							txtTongTien.setText(String.valueOf(tinhTongTien()));
						} else if (kiemtraMaSP(maSP) != -1) {
							int n = kiemtraMaSP(maSP);

							tableChiTietHDBH.setValueAt((int) tableChiTietHDBH.getValueAt(n, 7) + sl, n, 7);

							// Cập nhật lại số lượng cho bảng sản phẩm
							int newSoLuong = (int) tableSanPham.getValueAt(row, 6) - sl;
							tableSanPham.setValueAt(newSoLuong, row, 6);

							double thanhTienNew = (int) tableChiTietHDBH.getValueAt(n, 7)
									* (double) tableChiTietHDBH.getValueAt(row, 8);
							tableChiTietHDBH.setValueAt((int) tableChiTietHDBH.getValueAt(n, 7) * sp.getGiaSanPham(), n,
									9);

							txtTongTien.setText(String.valueOf(tinhTongTien()));

						}
					}
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
		lblThongBao.setBounds(434, 22, 737, 25);
		pTaoHoaDonBH.add(lblThongBao);

		JLabel lblNewLabel_3 = new JLabel("Mã hóa đơn");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_3.setBounds(50, 70, 101, 13);
		pTaoHoaDonBH.add(lblNewLabel_3);

		lblMaHD = new JLabel("");
		lblMaHD.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMaHD.setBounds(270, 70, 186, 13);
		pTaoHoaDonBH.add(lblMaHD);
		lblMaHD.setText(generateKeyDAO.getKey("HoaDonNhapKho"));

		btnThemVaoCTHDBH = new JButton("");
		btnThemVaoCTHDBH.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				themSPVaoHD();
			}
		});
		btnThemVaoCTHDBH.setIcon(new ImageIcon(QuanLyBanHang.class.getResource("/img/icons8_expand_arrow_25px.png")));
		btnThemVaoCTHDBH.setBounds(420, 425, 25, 25);
		pTaoHoaDonBH.add(btnThemVaoCTHDBH);

		btnCapNhatSoLuong = new JButton("");
		btnCapNhatSoLuong.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
		});
		btnCapNhatSoLuong
				.setIcon(new ImageIcon(QuanLyBanHang.class.getResource("/img/icons8_collapse_arrow_25px.png")));
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

		btnThemSP = new ButtonGradient();
		btnThemSP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				add(pThemSP);
				pThemSP.setVisible(true);
				pTaoHoaDonBH.setVisible(false);

				txtMaSanPham.setText(generateKeyDAO.getKey("SanPham"));
				txtMaHSX.setText(generateKeyDAO.getKey("HangSanXuat"));
				txtMaLoai.setText(generateKeyDAO.getKey("LoaiSanPham"));
			}
		});
		btnThemSP.setText("Thêm sản phẩm");
		((ButtonGradient) btnThemSP).setColor1(Color.decode("#0099F7"));
		// ((ButtonGradient) btnThemSP).setColor2(Color.decode("#f3ff9b"));
		((ButtonGradient) btnThemSP).setColor2(Color.decode("#0099F7"));
		btnThemSP.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnThemSP.setBounds(296, 230, 142, 25);
		pTaoHoaDonBH.add(btnThemSP);

		pThemSP = new JPanel();
		pThemSP.setBackground(Color.WHITE);
		pThemSP.setBounds(10, 10, 1090, 680);
		// add(pThemSP);
		pThemSP.setLayout(null);

		JLabel lblNewLabel0 = new JLabel("Thêm sản phẩm");
		lblNewLabel0.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel0.setBounds(10, 0, 277, 49);
		pThemSP.add(lblNewLabel0);

		JPanel pSanPham = new JPanel();
		pSanPham.setBackground(Color.WHITE);
		pSanPham.setBorder(
				new TitledBorder(null, "S\u1EA3n ph\u1EA9m", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pSanPham.setBounds(20, 59, 494, 588);
		pThemSP.add(pSanPham);
		pSanPham.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Mã sản phẩm");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(10, 40, 91, 25);
		pSanPham.add(lblNewLabel_1);

		txtMaSanPham = new JTextField();
		txtMaSanPham.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtMaSanPham.setEditable(false);
		txtMaSanPham.setBounds(150, 40, 322, 25);
		pSanPham.add(txtMaSanPham);
		txtMaSanPham.setColumns(10);

		JLabel lblNewLabel_1_1 = new JLabel("Tên sản phẩm");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_1.setBounds(10, 90, 91, 25);
		pSanPham.add(lblNewLabel_1_1);

		txtTenSP = new JTextField();
		txtTenSP.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtTenSP.setColumns(10);
		txtTenSP.setBounds(150, 90, 322, 25);
		pSanPham.add(txtTenSP);

		JLabel lblNewLabel_1_1_10 = new JLabel("Hãng sản xuất");
		lblNewLabel_1_1_10.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_1_10.setBounds(10, 140, 91, 25);
		pSanPham.add(lblNewLabel_1_1_10);

		txtSoLuong = new JTextField();
		txtSoLuong.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtSoLuong.setColumns(10);
		txtSoLuong.setBounds(150, 340, 322, 25);
		pSanPham.add(txtSoLuong);

		JLabel lblNewLabel_1_1_1_1 = new JLabel("Loại sản phẩm");
		lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_1_1_1.setBounds(10, 190, 121, 25);
		pSanPham.add(lblNewLabel_1_1_1_1);

		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("Kích thước");
		lblNewLabel_1_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_1_1_1_1.setBounds(10, 240, 121, 25);
		pSanPham.add(lblNewLabel_1_1_1_1_1);

		JLabel lblNewLabel_1_1_1_1_1_1 = new JLabel("Màu săc");
		lblNewLabel_1_1_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_1_1_1_1_1.setBounds(10, 290, 121, 25);
		pSanPham.add(lblNewLabel_1_1_1_1_1_1);

		JLabel lblNewLabel_1_1_1_1_1_1_1 = new JLabel("Số lượng");
		lblNewLabel_1_1_1_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_1_1_1_1_1_1.setBounds(10, 340, 121, 25);
		pSanPham.add(lblNewLabel_1_1_1_1_1_1_1);

		JLabel lblNewLabel_1_1_1_1_1_1_2 = new JLabel("Giá");
		lblNewLabel_1_1_1_1_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_1_1_1_1_1_2.setBounds(10, 390, 121, 25);
		pSanPham.add(lblNewLabel_1_1_1_1_1_1_2);

		txtGia = new JTextField();
		txtGia.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtGia.setColumns(10);
		txtGia.setBounds(150, 390, 322, 25);
		pSanPham.add(txtGia);

		cboHSX = new JComboBox();
		cboHSX.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cboHSX.setBounds(151, 140, 322, 25);
		pSanPham.add(cboHSX);

		cboLoai = new JComboBox();
		cboLoai.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cboLoai.setBounds(150, 190, 322, 25);
		pSanPham.add(cboLoai);

		showComboBox();

		cboKichThuoc = new JComboBox();
		cboKichThuoc.setModel(new DefaultComboBoxModel(new String[] { "S", "M", "L", "XL", "XXL", "XXXL" }));
		cboKichThuoc.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cboKichThuoc.setBounds(150, 240, 322, 25);
		pSanPham.add(cboKichThuoc);

		cboMau = new JComboBox();
		cboMau.setModel(new DefaultComboBoxModel(
				new String[] { "Trắng", "Đen", "Đỏ ", "Xanh dương", "Xanh lá", "Vàng ", "Cam", "Tím", "Tràm", "Nâu" }));
		cboMau.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cboMau.setBounds(150, 290, 322, 25);
		pSanPham.add(cboMau);

		btnThem = new ButtonGradient();
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				themSP();
			}
		});
		btnThem.setText("Thêm sản phẩm");
		((ButtonGradient) btnThem).setColor2(Color.decode("#0099F7"));
		btnThem.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnThem.setBounds(46, 475, 178, 40);
		pSanPham.add(btnThem);

		btnThoatNhapSP = new ButtonGradient();
		btnThoatNhapSP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				thoatKhoiTrangThemSP();
			}
		});
		btnThoatNhapSP.setText("Thoát");
		((ButtonGradient) btnThoatNhapSP).setColor2(Color.decode("#0099F7"));
		btnThoatNhapSP.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnThoatNhapSP.setBounds(273, 475, 178, 40);
		pSanPham.add(btnThoatNhapSP);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBorder(new TitledBorder(null, "H\u00E3ng s\u1EA3n xu\u1EA5t", TitledBorder.LEADING, TitledBorder.TOP,
				null, null));
		panel.setBounds(524, 59, 543, 362);
		pThemSP.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel_1_20 = new JLabel("Mã hãng");
		lblNewLabel_1_20.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_20.setBounds(30, 30, 91, 25);
		panel.add(lblNewLabel_1_20);

		txtMaHSX = new JTextField();
		txtMaHSX.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtMaHSX.setEditable(false);
		txtMaHSX.setColumns(10);
		txtMaHSX.setBounds(189, 30, 322, 25);
		panel.add(txtMaHSX);

		JLabel lblNewLabel_1_2_1 = new JLabel("Tên hãng");
		lblNewLabel_1_2_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_2_1.setBounds(30, 80, 91, 25);
		panel.add(lblNewLabel_1_2_1);

		txtTenHSX = new JTextField();
		txtTenHSX.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtTenHSX.setColumns(10);
		txtTenHSX.setBounds(189, 80, 322, 25);
		panel.add(txtTenHSX);

		JLabel lblNewLabel_1_2_1_1 = new JLabel("Số điện thoại");
		lblNewLabel_1_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_2_1_1.setBounds(30, 130, 91, 25);
		panel.add(lblNewLabel_1_2_1_1);

		txtSDT = new JTextField();
		txtSDT.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtSDT.setColumns(10);
		txtSDT.setBounds(189, 130, 322, 25);
		panel.add(txtSDT);

		JLabel lblNewLabel_1_2_1_1_1 = new JLabel("Địa chỉ");
		lblNewLabel_1_2_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_2_1_1_1.setBounds(30, 180, 91, 25);
		panel.add(lblNewLabel_1_2_1_1_1);

		JLabel lblNewLabel_1_2_1_1_2 = new JLabel("Thành phố");
		lblNewLabel_1_2_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_2_1_1_2.setBounds(30, 230, 91, 25);
		panel.add(lblNewLabel_1_2_1_1_2);

		JLabel lblNewLabel_1_2_1_1_3 = new JLabel("Quốc gia");
		lblNewLabel_1_2_1_1_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_2_1_1_3.setBounds(30, 280, 91, 25);
		panel.add(lblNewLabel_1_2_1_1_3);

		txtDC = new JTextField();
		txtDC.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtDC.setColumns(10);
		txtDC.setBounds(189, 180, 322, 25);
		panel.add(txtDC);

		txtTP = new JTextField();
		txtTP.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtTP.setColumns(10);
		txtTP.setBounds(189, 230, 322, 25);
		panel.add(txtTP);

		txtQuocGia = new JTextField();
		txtQuocGia.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtQuocGia.setColumns(10);
		txtQuocGia.setBounds(189, 280, 322, 25);
		panel.add(txtQuocGia);

		btnThemHSX = new ButtonGradient();
		btnThemHSX.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				themHSX();
			}

		});
		btnThemHSX.setText("Thêm");
		((ButtonGradient) btnThemHSX).setColor2(Color.decode("#0099F7"));
		btnThemHSX.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnThemHSX.setBounds(65, 320, 163, 25);
		panel.add(btnThemHSX);

		btnXoaTrangHSX = new ButtonGradient();
		btnXoaTrangHSX.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				xoaTrangHSX();
			}
		});
		btnXoaTrangHSX.setText("Xóa trắng");
		((ButtonGradient) btnXoaTrangHSX).setColor2(Color.decode("#0099F7"));
		btnXoaTrangHSX.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnXoaTrangHSX.setBounds(305, 320, 163, 25);
		panel.add(btnXoaTrangHSX);

		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Lo\u1EA1i s\u1EA3n ph\u1EA9m", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(524, 431, 543, 216);
		pThemSP.add(panel_1);

		JLabel lblNewLabel_1_2_1_1_3_1 = new JLabel("Mã loại");
		lblNewLabel_1_2_1_1_3_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_2_1_1_3_1.setBounds(30, 30, 91, 25);
		panel_1.add(lblNewLabel_1_2_1_1_3_1);

		txtMaLoai = new JTextField();
		txtMaLoai.setEditable(false);
		txtMaLoai.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtMaLoai.setColumns(10);
		txtMaLoai.setBounds(189, 30, 322, 25);
		panel_1.add(txtMaLoai);

		JLabel lblNewLabel_1_2_1_1_3_1_1 = new JLabel("Tên loại");
		lblNewLabel_1_2_1_1_3_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_2_1_1_3_1_1.setBounds(30, 80, 91, 25);
		panel_1.add(lblNewLabel_1_2_1_1_3_1_1);

		txtTenLoai = new JTextField();
		txtTenLoai.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtTenLoai.setColumns(10);
		txtTenLoai.setBounds(189, 80, 322, 25);
		panel_1.add(txtTenLoai);

		JLabel lblNewLabel_1_2_1_1_3_1_1_1 = new JLabel("Mô tả");
		lblNewLabel_1_2_1_1_3_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_2_1_1_3_1_1_1.setBounds(30, 130, 91, 25);
		panel_1.add(lblNewLabel_1_2_1_1_3_1_1_1);

		txtMoTa = new JTextField();
		txtMoTa.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtMoTa.setColumns(10);
		txtMoTa.setBounds(189, 130, 322, 25);
		panel_1.add(txtMoTa);

		btnThemLoai = new ButtonGradient();
		btnThemLoai.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				themLoaiSP();
			}
		});
		btnThemLoai.setText("Thêm");
		((ButtonGradient) btnThemLoai).setColor2(Color.decode("#0099F7"));
		btnThemLoai.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnThemLoai.setBounds(65, 176, 163, 25);
		panel_1.add(btnThemLoai);

		btnXoaTrangLoai = new ButtonGradient();
		btnXoaTrangLoai.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				xoaTrangLoaiSP();
			}
		});
		btnXoaTrangLoai.setText("Xóa trắng");
		((ButtonGradient) btnXoaTrangLoai).setColor2(Color.decode("#0099F7"));
		btnXoaTrangLoai.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnXoaTrangLoai.setBounds(305, 176, 163, 25);
		panel_1.add(btnXoaTrangLoai);

		lblThongBaoThemSP = new JLabel("");
		lblThongBaoThemSP.setForeground(Color.RED);
		lblThongBaoThemSP.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblThongBaoThemSP.setBounds(327, 10, 365, 29);
		pThemSP.add(lblThongBaoThemSP);

	}

	public void inHoaDon(String maHD) {
		try {
			String sql = "SELECT HoaDonNhapKho.maHoaDonNhapKho, HoaDonNhapKho.ngayNhapKho, SanPham.maSanPham, SanPham.giaSanPham, SanPham.kichThuoc, SanPham.tenSanPham, NhanVienBanHang.maNhanVien, ChuCuaHang.maChuCuaHang, \r\n"
					+ "                  ChuCuaHang.tenChuCuaHang, NhanVienBanHang.tenNhanVien, LoaiSanPham.tenLoai, HangSanXuat.tenHangSanXuat, LoaiSanPham.maLoaiSanPham, HangSanXuat.maHangSanXuat, ChiTietHoaDonNhapKho.soLuong, \r\n"
					+ "                  ChiTietHoaDonNhapKho.giaNhap, sum(ChiTietHoaDonNhapKho.soLuong * ChiTietHoaDonNhapKho.giaNhap) as thanhTien, (select sum(ChiTietHoaDonNhapKho.soLuong * ChiTietHoaDonNhapKho.giaNhap)\r\n"
					+ "				  from ChiTietHoaDonNhapKho\r\n" + "				  where maHoaDonNhapKho = '"
					+ maHD + "') as tongTien\r\n" + "FROM     ChiTietHoaDonNhapKho INNER JOIN\r\n"
					+ "                  HoaDonNhapKho ON ChiTietHoaDonNhapKho.maHoaDonNhapKho = HoaDonNhapKho.maHoaDonNhapKho INNER JOIN\r\n"
					+ "                  SanPham ON ChiTietHoaDonNhapKho.maSanPham = SanPham.maSanPham INNER JOIN\r\n"
					+ "                  ChuCuaHang ON HoaDonNhapKho.maChuCuaHang = ChuCuaHang.maChuCuaHang INNER JOIN\r\n"
					+ "                  NhanVienBanHang ON HoaDonNhapKho.maNhanVien = NhanVienBanHang.maNhanVien INNER JOIN\r\n"
					+ "                  LoaiSanPham ON SanPham.maLoaiSanPham = LoaiSanPham.maLoaiSanPham INNER JOIN\r\n"
					+ "                  HangSanXuat ON SanPham.maHangSanXuat = HangSanXuat.maHangSanXuat\r\n"
					+ "where HoaDonNhapKho.maHoaDonNhapKho = '" + maHD + "'\r\n"
					+ "group by HoaDonNhapKho.maHoaDonNhapKho, HoaDonNhapKho.ngayNhapKho, SanPham.maSanPham, SanPham.giaSanPham, SanPham.kichThuoc, SanPham.tenSanPham, NhanVienBanHang.maNhanVien, ChuCuaHang.maChuCuaHang, \r\n"
					+ "                  ChuCuaHang.tenChuCuaHang, NhanVienBanHang.tenNhanVien, LoaiSanPham.tenLoai, HangSanXuat.tenHangSanXuat, LoaiSanPham.maLoaiSanPham, HangSanXuat.maHangSanXuat, ChiTietHoaDonNhapKho.soLuong, \r\n"
					+ "                  ChiTietHoaDonNhapKho.giaNhap";

			JasperDesign jasperDesign = JRXmlLoader.load("src/main/java/Report/HoaDonNhapKhoRepoter.jrxml");
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
			String sql = "SELECT HoaDonNhapKho.maHoaDonNhapKho, HoaDonNhapKho.ngayNhapKho, SanPham.maSanPham, SanPham.giaSanPham, SanPham.kichThuoc, SanPham.tenSanPham, NhanVienBanHang.maNhanVien, ChuCuaHang.maChuCuaHang, \r\n"
					+ "                  ChuCuaHang.tenChuCuaHang, NhanVienBanHang.tenNhanVien, LoaiSanPham.tenLoai, HangSanXuat.tenHangSanXuat, LoaiSanPham.maLoaiSanPham, HangSanXuat.maHangSanXuat, ChiTietHoaDonNhapKho.soLuong, \r\n"
					+ "                  ChiTietHoaDonNhapKho.giaNhap, sum(ChiTietHoaDonNhapKho.soLuong * ChiTietHoaDonNhapKho.giaNhap) as thanhTien, (select sum(ChiTietHoaDonNhapKho.soLuong * ChiTietHoaDonNhapKho.giaNhap)\r\n"
					+ "				  from ChiTietHoaDonNhapKho\r\n" + "				  where maHoaDonNhapKho = '"
					+ maHD + "') as tongTien\r\n" + "FROM     ChiTietHoaDonNhapKho INNER JOIN\r\n"
					+ "                  HoaDonNhapKho ON ChiTietHoaDonNhapKho.maHoaDonNhapKho = HoaDonNhapKho.maHoaDonNhapKho INNER JOIN\r\n"
					+ "                  SanPham ON ChiTietHoaDonNhapKho.maSanPham = SanPham.maSanPham INNER JOIN\r\n"
					+ "                  ChuCuaHang ON HoaDonNhapKho.maChuCuaHang = ChuCuaHang.maChuCuaHang INNER JOIN\r\n"
					+ "                  NhanVienBanHang ON HoaDonNhapKho.maNhanVien = NhanVienBanHang.maNhanVien INNER JOIN\r\n"
					+ "                  LoaiSanPham ON SanPham.maLoaiSanPham = LoaiSanPham.maLoaiSanPham INNER JOIN\r\n"
					+ "                  HangSanXuat ON SanPham.maHangSanXuat = HangSanXuat.maHangSanXuat\r\n"
					+ "where HoaDonNhapKho.maHoaDonNhapKho = '" + maHD + "'\r\n"
					+ "group by HoaDonNhapKho.maHoaDonNhapKho, HoaDonNhapKho.ngayNhapKho, SanPham.maSanPham, SanPham.giaSanPham, SanPham.kichThuoc, SanPham.tenSanPham, NhanVienBanHang.maNhanVien, ChuCuaHang.maChuCuaHang, \r\n"
					+ "                  ChuCuaHang.tenChuCuaHang, NhanVienBanHang.tenNhanVien, LoaiSanPham.tenLoai, HangSanXuat.tenHangSanXuat, LoaiSanPham.maLoaiSanPham, HangSanXuat.maHangSanXuat, ChiTietHoaDonNhapKho.soLuong, \r\n"
					+ "                  ChiTietHoaDonNhapKho.giaNhap";

			JasperDesign jasperDesign = JRXmlLoader.load("src/main/java/Report/HoaDonNhapKhoRepoter1.jrxml");
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
	
	public void timKiemHD() {
		if (txtSDT_CMND.getText().equals("") && dateChooser.getDate() != null)
			timkiemTheoThoiGian();
		else if (!txtSDT_CMND.getText().equals("") && dateChooser.getDate() == null) {
			timkiemTheoTenNV_SDT_CMND();
		} else if (!txtSDT_CMND.getText().equals("") && dateChooser.getDate() != null) {
			timkiemTheoTenNV_CMND_SDT_Ngay();
		} else
			JOptionPane.showMessageDialog(this, "Lỗi: Vui lòng nhập dữ liệu để tìm kiếm!");
	}

	protected void timkiemTheoThoiGian() {
		Date date = dateChooser.getDate();
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
		int num = 1;
		List<HoaDonNhapKho> listHDNK = hoaDonNhapKhoDAO.layDanhSachHoaDonNhapKhoTheoNgay(df1.format(date));
		if (listHDNK.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Không tìm thấy thông tin hóa đơn, vui lòng nhập lại");
			dateChooser.requestFocus();
		} else if (listHDNK != null) {
			xoaHetDuLieuTrenTableModel();
			model = (DefaultTableModel) tableHDBH.getModel();
			for (HoaDonNhapKho hdnk : listHDNK) {
				Date day = hdnk.getNgayNhapKho();
				double tongTien = tinhTongTienHoaDonNhapKho(hdnk.getMaHoaDonNhapKho());
				model.addRow(new Object[] { num, hdnk.getMaHoaDonNhapKho(), hdnk.getNhanVienBanHang().getTenNhanVien(),
						df.format(day), tongTien });
				num++;
			}
		}

	}

	private void timkiemTheoTenNV() {
		String ma = txtSDT_CMND.getText();
		int num = 1;
		List<HoaDonNhapKho> listHDNK = hoaDonNhapKhoDAO.layDanhSachHoaDonNhapKhoTheoTenNhanVien(ma);
		if (listHDNK != null) {
			xoaHetDuLieuTrenTableModel();
			model = (DefaultTableModel) tableHDBH.getModel();
			for (HoaDonNhapKho hdnk : listHDNK) {
				Date day = hdnk.getNgayNhapKho();
				double tongTien = tinhTongTienHoaDonNhapKho(hdnk.getMaHoaDonNhapKho());
				model.addRow(new Object[] { num, hdnk.getMaHoaDonNhapKho(), hdnk.getNhanVienBanHang().getTenNhanVien(),
						df.format(day), tongTien });
				num++;
			}
		} else {
			JOptionPane.showMessageDialog(this, "Không tìm thấy thông tin hóa đơn, vui lòng nhập lại");
			txtSDT_CMND.selectAll();
			txtSDT_CMND.requestFocus();
		}
	}

	private void timkiemTheoTenNV_SDT_CMND() {
		String ma = txtSDT_CMND.getText();
		int num = 1;
		List<HoaDonNhapKho> listHDNK = hoaDonNhapKhoDAO.layDanhSachHoaDonNhapKhoTheoTenNV_SDT_CMND(ma);
		if (listHDNK.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Không tìm thấy thông tin hóa đơn, vui lòng nhập lại");
			txtSDT_CMND.selectAll();
			txtSDT_CMND.requestFocus();
		} else if (listHDNK != null) {
			xoaHetDuLieuTrenTableModel();
			model = (DefaultTableModel) tableHDBH.getModel();
			for (HoaDonNhapKho hdnk : listHDNK) {
				Date day = hdnk.getNgayNhapKho();
				double tongTien = tinhTongTienHoaDonNhapKho(hdnk.getMaHoaDonNhapKho());
				model.addRow(new Object[] { num, hdnk.getMaHoaDonNhapKho(), hdnk.getNhanVienBanHang().getTenNhanVien(),
						df.format(day), tongTien });
				num++;
			}
		}
	}

	private void timkiemTheoTenNV_Ngay() {
		Date date = dateChooser.getDate();
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
		String ma = txtSDT_CMND.getText();
		int num = 1;
		List<HoaDonNhapKho> listHDNK = hoaDonNhapKhoDAO.layDanhSachHoaDonNhapKhoTheoNgay_TenNV(df1.format(date), ma);
		xoaHetDuLieuTrenTableModel();
		model = (DefaultTableModel) tableHDBH.getModel();
		for (HoaDonNhapKho hdnk : listHDNK) {
			Date day = hdnk.getNgayNhapKho();
			double tongTien = tinhTongTienHoaDonNhapKho(hdnk.getMaHoaDonNhapKho());
			model.addRow(new Object[] { num, hdnk.getMaHoaDonNhapKho(), hdnk.getNhanVienBanHang().getTenNhanVien(),
					df.format(day), tongTien });
			num++;
		}

	}

	private void timkiemTheoTenNV_CMND_SDT_Ngay() {
		Date date = dateChooser.getDate();
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
		String ma = txtSDT_CMND.getText();
		int num = 1;
		List<HoaDonNhapKho> listHDNK = hoaDonNhapKhoDAO.layDanhSachHoaDonNhapKhoTheoTenNV_SDT_CMND_Ngay(ma,
				df1.format(date));
		if (listHDNK.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Không tìm thấy thông tin hóa đơn, vui lòng nhập lại");
			txtSDT_CMND.selectAll();
			txtSDT_CMND.requestFocus();
		} else if (listHDNK != null) {
			xoaHetDuLieuTrenTableModel();
			model = (DefaultTableModel) tableHDBH.getModel();
			for (HoaDonNhapKho hdnk : listHDNK) {
				Date day = hdnk.getNgayNhapKho();
				double tongTien = tinhTongTienHoaDonNhapKho(hdnk.getMaHoaDonNhapKho());
				model.addRow(new Object[] { num, hdnk.getMaHoaDonNhapKho(), hdnk.getNhanVienBanHang().getTenNhanVien(),
						df.format(day), tongTien });
				num++;
			}
		}
	}

	private void xoaHetDuLieuTrenTableModel() {
		DefaultTableModel dm = (DefaultTableModel) tableHDBH.getModel();
		dm.getDataVector().removeAllElements();
	}

	/*
	 * public void showcomboData() { for (HoaDonNhapKho hdnk : listHDNK) {
	 * cboMaHD.addItem(hdbh.getMaHoaDonBanHang()); } }
	 */

	public void showTable() {
		int num = 1;
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		List<HoaDonNhapKho> listHDNK = hoaDonNhapKhoDAO.layTatCaHoaDonNhapKho();
		for (HoaDonNhapKho hdnk : listHDNK) {
			Date day = hdnk.getNgayNhapKho();
			double tongTien = tinhTongTienHoaDonNhapKho(hdnk.getMaHoaDonNhapKho());

			model.addRow(new Object[] { num, hdnk.getMaHoaDonNhapKho(), hdnk.getNhanVienBanHang().getTenNhanVien(),
					df.format(day), currencyVN.format(tongTien), "Nhập mới" });
			num++;
		}
	}

	public double tinhTongTienHoaDonNhapKho(String maHDNK) {
		List<ChiTietHoaDonNhapKho> listCTNK = chiTietNhapKhoDAO.layDanhSachCTHoaDonNhapKhoTheoMaHDBH(maHDNK);
		double tongTien = 0;
		for (ChiTietHoaDonNhapKho ctnk : listCTNK) {
			tongTien += ctnk.getGiaNhap() * ctnk.getSoLuong();
			System.out.println(tongTien);
		}
		System.out.println(tongTien);
		return tongTien;
	}

	public void xoaDataComboBox() {
		DefaultComboBoxModel modelHSX = (DefaultComboBoxModel) cboHSX.getModel();
		modelHSX.removeAllElements();

		DefaultComboBoxModel modelLoaiSP = (DefaultComboBoxModel) cboLoai.getModel();
		modelLoaiSP.removeAllElements();
	}

	public void showComboBox() {
		List<HangSanXuat> listHSX = hsxDAO.layTatCaHangSanXuat();
		DefaultComboBoxModel modelHSX = (DefaultComboBoxModel) cboHSX.getModel();
		for (HangSanXuat hsx : listHSX) {
			modelHSX.addElement(hsx.getTenHangSanXuat());
		}

		List<LoaiSanPham> listLoaiSP = loaispDAO.layTatCaLoaiSanPham();
		DefaultComboBoxModel modelLoaiSP = (DefaultComboBoxModel) cboLoai.getModel();
		for (LoaiSanPham loai : listLoaiSP) {
			modelLoaiSP.addElement(loai.getTenLoai());
		}

		/*
		 * DefaultComboBoxModel modelSize = (DefaultComboBoxModel) cboSize.getModel();
		 * modelSize.addElement(new String[] { "S", "M", "L", "XL", "XXL", "XXXL" });
		 * 
		 * DefaultComboBoxModel modelMau = (DefaultComboBoxModel) cboMau.getModel();
		 * modelMau.addElement(new String[] { "Đỏ", "Xanh Dương", "Xanh Lá", "Vàng",
		 * "Trắng", "Đen", "Cam", "Tràm" });
		 */
	}

	public void showTableCTHDNK(String ma) {
		int num = 1;
		List<ChiTietHoaDonNhapKho> listCTHDNK = chiTietNhapKhoDAO.layDanhSachCTHoaDonNhapKhoTheoMaHDBH(ma);
		for (ChiTietHoaDonNhapKho cthdnk : listCTHDNK) {
			double thanhTien = cthdnk.getSoLuong() * cthdnk.getGiaNhap();
			model.addRow(new Object[] { num, cthdnk.getSanPham().getMaSanPham(), cthdnk.getSanPham().getTenSanPham(),
					cthdnk.getSanPham().getKichThuoc(), cthdnk.getSanPham().getMauSac(),
					cthdnk.getSanPham().getLoaiSanPham().getTenLoai(), cthdnk.getSoLuong(), cthdnk.getGiaNhap(),
					thanhTien, "mua mới" });
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

	private void xoaTrangHSX() {

		txtTenHSX.setText("");
		txtSDT.setText("");
		txtDC.setText("");
		txtTP.setText("");
		txtQuocGia.setText("");

	}

	private void xoaTrangLoaiSP() {

		txtTenLoai.setText("");
		txtMoTa.setText("");

	}

	private void xoaTrangSP() {
		txtTenSP.setText("");
		txtSoLuong.setText("");
		txtGia.setText("");
		cboHSX.setSelectedIndex(0);
		cboLoai.setSelectedIndex(0);
		cboKichThuoc.setSelectedIndex(0);
		cboMau.setSelectedIndex(0);
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
		int n = -1;
		for (int i = 0; i < row; i++) {
			if (tableChiTietHDBH.getValueAt(i, 1).toString().equalsIgnoreCase(ma)) {
				n = i;
				break;
			}
		}
		return n;
	}

	private void luuThongTinChiTietHDBH() {
		int row = tableChiTietHDBH.getRowCount();
		for (int i = 0; i < row; i++) {
			String maSP = tableChiTietHDBH.getValueAt(i, 1).toString();
			SanPham sp = new SanPham(maSP);
			String maHD = lblMaHD.getText();
			HoaDonNhapKho hd = new HoaDonNhapKho(maHD);
			int soLuong = (int) tableChiTietHDBH.getValueAt(i, 7);
			double gia = (double) tableChiTietHDBH.getValueAt(i, 8);

			ChiTietHoaDonNhapKho cthdnk = new ChiTietHoaDonNhapKho(soLuong, gia, sp, hd);
			chiTietNhapKhoDAO.themChiTietNhapKho(cthdnk);
			System.out.println(cthdnk);
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

		if (sl <= 0) {
			JOptionPane.showMessageDialog(this, "Lỗi: Không nhập số âm hoặc số 0.");
			lblThongBao.setText("Lỗi: Không nhập số âm hoặc số 0.");
			txtSL.setText("1");
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

	/*
	 * public void themVaoLuaChonKh(String s) { txtLuaChonKhachHang.setText(s); }
	 */

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

	private boolean validDataSP() {
		String ma = txtMaSP.getText();
		String ten = txtTenSP.getText();
		String tenHSX = cboHSX.getSelectedItem().toString();
		String tenLoai = cboLoai.getSelectedItem().toString();
		String size = cboKichThuoc.getSelectedItem().toString();
		String mau = cboMau.getSelectedItem().toString();

		if (ten.equals("")) {
			/*
			 * showMessage( "Error: Tên khách hàng không được bỏ trống", txtTenKH);
			 */
			// lblThongBaoThemSP.setText("Error: Tên sản phẩm không được bỏ trống");
			JOptionPane.showMessageDialog(this, "Lỗi: Tên sản phẩm không được bỏ trống");
			txtTenSP.requestFocus();
			txtTenSP.selectAll();
			return false;
		}
		if (txtSoLuong.getText().equals("")) {
			// lblThongBaoThemSP.setText("Error: Vui lòng nhập số lượng");
			JOptionPane.showMessageDialog(this, "Lỗi: Vui lòng nhập số lượng");
			txtSoLuong.requestFocus();
			txtSoLuong.selectAll();
			return false;
		}
		if (!txtSoLuong.getText().matches("[0-9]+")) {
			// lblThongBaoThemSP.setText("Error: Vui lòng nhập số lớn hơn 0");
			JOptionPane.showMessageDialog(this, "Lỗi: Số lượng có định dạng là chữ số!");
			txtSoLuong.requestFocus();
			txtSoLuong.selectAll();
			return false;
		}
		int sl = Integer.parseInt(txtSoLuong.getText());
		if (sl <= 0) {
			// lblThongBaoThemSP.setText("Error: Vui lòng nhập số lớn hơn 0");
			JOptionPane.showMessageDialog(this, "Lỗi: Vui lòng nhập số lớn hơn 0");
			txtSoLuong.requestFocus();
			txtSoLuong.selectAll();
			return false;
		}
		if (txtGia.getText().equals("")) {
			// lblThongBaoThemSP.setText("Error: Vui lòng nhập giá sản phẩm");
			JOptionPane.showMessageDialog(this, "Lỗi: Vui lòng nhập giá sản phẩm");
			txtGia.requestFocus();
			txtGia.selectAll();
			return false;
		}
		if (!txtGia.getText().matches("[0-9]+")) {

			JOptionPane.showMessageDialog(this, "Lỗi: Giá sản phẩm có định dạng là chữ số!");
			txtGia.requestFocus();
			txtGia.selectAll();
			return false;
		}
		double gia = Double.parseDouble(txtGia.getText());
		if (gia < 1000) {
			// lblThongBaoThemSP.setText("Error: Giá sản phẩm phải hơn 1000 đ");
			JOptionPane.showMessageDialog(this, "Lỗi: Giá sản phẩm phải hơn 1000 đ");
			txtGia.requestFocus();
			txtGia.selectAll();
			return false;
		}

		return true;
	}

	private boolean validDataHSX() {

		String ma = txtMaHSX.getText();
		String ten = txtTenHSX.getText();
		String sdt = txtSDT.getText();
		String dc = txtDC.getText();
		String tp = txtTP.getText();
		String qg = txtQuocGia.getText();

		if (ten.equals("")) {
			/*
			 * showMessage( "Error: Tên khách hàng không được bỏ trống", txtTenKH);
			 */
			// lblThongBaoThemSP.setText("Error: Tên hãng sản xuất không được bỏ trống");
			JOptionPane.showMessageDialog(this, "Lỗi: Tên hãng sản xuất không được bỏ trống");
			txtTenHSX.requestFocus();
			txtTenHSX.selectAll();
			return false;
		}
		if (sdt.equals("")) {
			// lblThongBaoThemSP.setText("Error: Vui lòng nhập số điện thoại");
			JOptionPane.showMessageDialog(this, "Lỗi: Số điện thoại không được bỏ trống!");
			txtSDT.requestFocus();
			txtSDT.selectAll();
			return false;
		}
		if (!sdt.equals("") && !sdt.matches("[0-9]{6,10}")) {
			// lblThongBaoThemSP.setText("Error: Vui lòng nhập số điện thoại");
			JOptionPane.showMessageDialog(this, "Lỗi: Tên loại phải nhập ký tự, số điện thoại từ 6 đến 10 chữ số!");
			txtSDT.requestFocus();
			txtSDT.selectAll();
			return false;
		}
		if (tp.equals("")) {
			// lblThongBaoThemSP.setText("Error: Vui lòng nhập tên thành phố");
			JOptionPane.showMessageDialog(this, "Lỗi: Vui lòng nhập tên thành phố");
			txtTP.requestFocus();
			txtTP.selectAll();
			return false;
		}
		if (qg.equals("")) {
			// lblThongBaoThemSP.setText("Error: Vui lòng nhập tên quốc gia");
			JOptionPane.showMessageDialog(this, "Lỗi: Vui lòng nhập tên quốc gia");
			txtQuocGia.requestFocus();
			txtQuocGia.selectAll();
			return false;
		}

		return true;
	}

	private boolean validDataLoaiSP() {

		String ma = txtMaLoai.getText();
		String ten = txtTenLoai.getText();
		String moTa = txtMoTa.getText();

		if (ten.equals("")) {
			/*
			 * showMessage( "Error: Tên khách hàng không được bỏ trống", txtTenKH);
			 */
			// lblThongBaoThemSP.setText("Error: Tên loại sản phẩm không được bỏ trống");
			JOptionPane.showMessageDialog(this, "Lỗi: Tên loại sản phẩm không được bỏ trống");
			txtTenLoai.requestFocus();

			txtTenLoai.selectAll();
			return false;
		}
		if (ten.matches("[a-zA-Z]{1,}")) {
			/*
			 * showMessage( "Error: Tên khách hàng không được bỏ trống", txtTenKH);
			 */
			// lblThongBaoThemSP.setText("Error: Tên loại sản phẩm không được bỏ trống");
			JOptionPane.showMessageDialog(this, "Lỗi: Tên loại sản phẩm phải nhập theo ký tự không viết số!");
			txtTenLoai.requestFocus();

			txtTenLoai.selectAll();
			return false;
		}

		return true;
	}

	public void thoatKhoiTrangThemSP() {
		int n = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn thoát khỏi trang thêm sản phẩm?",
				"Thông báo xác nhận thoát khỏi trang thêm sản phẩm", JOptionPane.YES_NO_OPTION);
		if (n == JOptionPane.YES_OPTION) {
			add(pTaoHoaDonBH);
			pTaoHoaDonBH.setVisible(true);
			pThemSP.setVisible(false);

			xoaTrangSP();
			xoaTrangHSX();
			xoaTrangLoaiSP();
			lblThongBaoThemSP.setText("");
		}

	}

	public void themSP() {
		if (validDataSP()) {
			int stt = cboHSX.getSelectedIndex();
			System.out.println(stt);
			String maHSX;
			if (stt < 9)
				maHSX = "HSX00" + String.valueOf(stt + 1);
			else
				maHSX = "HSX0" + String.valueOf(stt + 1);

			int stt1 = cboLoai.getSelectedIndex();
			String maLoai;
			if (stt1 < 9)
				maLoai = "L00" + String.valueOf(stt1 + 1);
			else
				maLoai = "L0" + String.valueOf(stt1 + 1);
			System.out.println(maHSX + "hhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
			String ma = txtMaSanPham.getText();
			String ten = txtTenSP.getText();
			String tenHSX = cboHSX.getSelectedItem().toString();
			// HangSanXuat hsx = hsxDAO.layHangSanXuatTheoTen1(tenHSX);
			// HangSanXuat hang = new HangSanXuat(hsx.getMaHangSanXuat());
			HangSanXuat hang = new HangSanXuat(maHSX);
			String tenLoai = cboLoai.getSelectedItem().toString();
			// LoaiSanPham loai = loaispDAO.layLoaiSanPHamTheoTen(tenLoai);
			// LoaiSanPham loaiSP = new LoaiSanPham(loai.getMaLoaiSanPham());
			LoaiSanPham loaiSP = new LoaiSanPham(maLoai);
			String size = cboKichThuoc.getSelectedItem().toString();
			String mau = cboMau.getSelectedItem().toString();
			int sl = Integer.parseInt(txtSoLuong.getText());
			double gia = Double.parseDouble(txtGia.getText());

			SanPham sp = new SanPham(ma, ten, gia, size, mau, sl, hang, loaiSP);
			boolean rs = sanphamDAO.themSanPham(sp);
			if (rs) {
				JOptionPane.showMessageDialog(this, "Thêm mới sản phẩm thành công.");
				// lblThongBaoThemSP.setText("Thêm mới sản phẩm thành công.");

				DefaultTableModel dm = (DefaultTableModel) tableSanPham.getModel();
				dm.getDataVector().removeAllElements();
				model = (DefaultTableModel) tableSanPham.getModel();
				showTableSanPham();

				model = (DefaultTableModel) tableChiTietHDBH.getModel();
				int n = tableChiTietHDBH.getSelectedColumnCount();
				int row = tableSanPham.getSelectedColumnCount();
				String loaiSanPham = tableSanPham.getValueAt(row, 3).toString();
				String ten_HSX = tableSanPham.getValueAt(row, 2).toString();
				/*
				 * model.addRow(new Object[] { n + 1,sp.getMaSanPham(), sp.getTenSanPham(),
				 * sp.getHangSanXuat().getTenHangSanXuat(), sp.getLoaiSanPham().getTenLoai(),
				 * sp.getKichThuoc(), sp.getMauSac(), sp.getSoLuong(), sp.getGiaSanPham() });
				 */
				model.addRow(new Object[] { n + 1, sp.getMaSanPham(), sp.getTenSanPham(), ten_HSX, loaiSanPham,
						sp.getKichThuoc(), sp.getMauSac(), sp.getSoLuong(), sp.getGiaSanPham(),
						currencyVN.format(sp.getSoLuong() * sp.getGiaSanPham()) });

				xoaTrangSP();
				txtMaSanPham.setText(generateKeyDAO.getKey("SanPham"));
				txtTongTien.setText(currencyVN.format(tinhTongTien()));
			} else
				JOptionPane.showMessageDialog(this, "Thêm mới sản phẩm không thành công");
			// lblThongBaoThemSP.setText("Thêm mới sản phẩm không thành công");
		}
	}

	public void thoatKhoiTrangTaoHDNK() {
		int n = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn thoát khỏi trang thêm hóa đơn nhập kho?",
				"Thông báo xác nhận thoát khỏi trang thêm hóa đơn nhập kho", JOptionPane.YES_NO_OPTION);
		if (n == JOptionPane.YES_OPTION) {
			add(pXemThongTonHDBH);
			pXemThongTonHDBH.setVisible(true);
			pTaoHoaDonBH.setVisible(false);

			xoaHetDuLieuTrenTableModel();
			model = (DefaultTableModel) tableHDBH.getModel();
			showTable();

			DefaultTableModel dm = (DefaultTableModel) tableChiTietHDBH.getModel();
			dm.getDataVector().removeAllElements();
			DefaultTableModel dm1 = (DefaultTableModel) tableSanPham.getModel();
			dm1.getDataVector().removeAllElements();
			model = (DefaultTableModel) tableSanPham.getModel();
			showTableSanPham();
			txtTongTien.setText("");
			lblMaHD.setText(generateKeyDAO.getKey("HoaDonNhapKho"));
			lblThongBao.setText("");
			btnInHD.setEnabled(false);

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

	public void luuHDNK() {
		if (tableChiTietHDBH.getRowCount() == 0) {
			JOptionPane.showMessageDialog(this, "Lỗi: vui lòng chọn và thêm sản phẩm vào hóa đơn!");
			txtMaSP.selectAll();
			txtMaSP.requestFocus();
		} else {
			int rowTableSP = tableSanPham.getSelectedRow();
			int rowTableCTHDBH = tableChiTietHDBH.getSelectedRow();

			// Lưu thông tin hóa đơn
			String maHD = lblMaHD.getText();

			/*
			 * NhanVienBanHang nv = (NhanVienBanHang)
			 * nhanVienDAO.layDanhSachNhanVienTheoTen(lblTenNV.getText()); String maNV =
			 * nv.getMaNhanVien(); NhanVienBanHang nhanVien = new NhanVienBanHang(maNV);
			 */

			ChuCuaHang cch = chucuahangDAO.layThongTinChuCuaHang();
			String maCCH = cch.getMaChuCuaHang();
			ChuCuaHang chu = new ChuCuaHang(maCCH);

			// String ngay = lblNgay.getText();

			TaiKhoan tk = taikhoanDAO.layThongTinTaiKhoanTheoTrangThai(true);
			NhanVienBanHang nv = nhanVienDAO.layNhanVienTheoMa(tk.getNhanVienBanHang().getMaNhanVien());
			String maNV = nv.getMaNhanVien();
			NhanVienBanHang nhanVien = new NhanVienBanHang(maNV);

			try {
				HoaDonNhapKho hdnk = new HoaDonNhapKho(maHD, new java.util.Date(), nhanVien, chu);
				boolean rs = hoaDonNhapKhoDAO.themHoaDon(hdnk);
				luuThongTinChiTietHDBH();
				capnhatSanPham();
				if (rs) {
					JOptionPane.showMessageDialog(this, "Lưu hóa đơn thành công, bây giờ bạn có thể xem hóa đơn");
					// lblThongBao.setText("Tạo hóa đơn thành công");
					btnInHD.setEnabled(true);
				} else
					JOptionPane.showMessageDialog(this, "Lưu mới hóa đơn không thành công");
				// lblThongBao.setText("Tạo hóa đơn không thành công");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			// Lưu thông tin chi tiết hóa đơn
			// String maCTHD = (String) tableChiTietHDBH.getValueAt(rowTableCTHDBH, 1);

			// Cập nhật lại số lượng sản phẩm
		}
	}

	public void themSPVaoHD() {
		if (!txtMaSP.getText().equals("")) {
			int row = kiemtraDongChuaMaSP(txtMaSP.getText());
			// String maSP = tableSanPham.getValueAt(row, 0).toString();
			String maSP = txtMaSP.getText();
			SanPham sp = sanphamDAO.laySanPhamTheoMa(maSP);
			int num = tableChiTietHDBH.getRowCount();
			int sl = Integer.parseInt(txtSL.getText());
			double thanhTien = sl * sp.getGiaSanPham();
			model = (DefaultTableModel) tableChiTietHDBH.getModel();

			if (kiemtraSoLuongMua(sl, row)) {
				if (num == 0) {
					// thanhTien = (double) tableChiTietHDBH.getValueAt(num, 7) *
					// sp.getGiaSanPham();
					// double tien = 0;
					// tien = thanhTien + (double) tableChiTietHDBH.getValueAt(num, 7) *
					// sp.getGiaSanPham();
					model.addRow(new Object[] { num + 1, sp.getMaSanPham(), sp.getTenSanPham(),
							sp.getHangSanXuat().getTenHangSanXuat(), sp.getLoaiSanPham().getTenLoai(),
							sp.getKichThuoc(), sp.getMauSac(), sl, sp.getGiaSanPham(), thanhTien });
					int newSoLuong = (int) tableSanPham.getValueAt(row, 6) + sl;
					tableSanPham.setValueAt(newSoLuong, row, 6);
					// tableChiTietHDBH.setValueAt(tien, num, 9);
					txtTongTien.setText(currencyVN.format(tinhTongTien()));
					// lblDonViTien.setText("VND");
				} else if (kiemtraMaSP(maSP) == -1) {
					model.addRow(new Object[] { num + 1, sp.getMaSanPham(), sp.getTenSanPham(),
							sp.getHangSanXuat().getTenHangSanXuat(), sp.getLoaiSanPham().getTenLoai(),
							sp.getKichThuoc(), sp.getMauSac(), sl, sp.getGiaSanPham(), thanhTien });
					int newSoLuong = (int) tableSanPham.getValueAt(row, 6) + sl;
					tableSanPham.setValueAt(newSoLuong, row, 6);
					txtTongTien.setText(currencyVN.format(tinhTongTien()));
				} else if (kiemtraMaSP(maSP) != -1) {
					int n = kiemtraMaSP(maSP);
					tableChiTietHDBH.setValueAt((int) tableChiTietHDBH.getValueAt(n, 7) + sl, n, 7);
					int newSoLuong = (int) tableSanPham.getValueAt(row, 6) + sl;
					tableSanPham.setValueAt(newSoLuong, row, 6);
					tableChiTietHDBH.setValueAt(currencyVN.format(
							(int) tableChiTietHDBH.getValueAt(n, 7) * (double) tableChiTietHDBH.getValueAt(n, 8)), n,
							9);
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
			int row = tableSanPham.getSelectedRow();
			String maSP = tableSanPham.getValueAt(row, 0).toString();
			SanPham sp = sanphamDAO.laySanPhamTheoMa(maSP);
			// ChiTietHoaDonNhapKho ctnk =
			// chiTietBanHangDAO.layDanhSachCTHoaDonBanHangTheoMaSP(maSP);
			int num = tableChiTietHDBH.getRowCount();
			int sl = Integer.parseInt(txtSL.getText());
			double thanhTien = sl * sp.getGiaSanPham();
			model = (DefaultTableModel) tableChiTietHDBH.getModel();

			if (kiemtraSoLuongMua(sl, row)) {
				if (num == 0) {
					// thanhTien = (double) tableChiTietHDBH.getValueAt(num, 7) *
					// sp.getGiaSanPham();
					// double tien = 0;
					// tien = thanhTien + (double) tableChiTietHDBH.getValueAt(num, 7) *
					// sp.getGiaSanPham();
					model.addRow(new Object[] { num + 1, sp.getMaSanPham(), sp.getTenSanPham(),
							sp.getHangSanXuat().getTenHangSanXuat(), sp.getLoaiSanPham().getTenLoai(),
							sp.getKichThuoc(), sp.getMauSac(), sl, sp.getGiaSanPham(), currencyVN.format(thanhTien) });
					int newSoLuong = (int) tableSanPham.getValueAt(row, 6) + sl;
					tableSanPham.setValueAt(newSoLuong, row, 6);
					// tableChiTietHDBH.setValueAt(tien, num, 9);
					txtTongTien.setText(currencyVN.format(tinhTongTien()));
					// lblDonViTien.setText("VND");
				} else if (kiemtraMaSP(maSP) == -1) {
					model.addRow(new Object[] { num + 1, sp.getMaSanPham(), sp.getTenSanPham(),
							sp.getHangSanXuat().getTenHangSanXuat(), sp.getLoaiSanPham().getTenLoai(),
							sp.getKichThuoc(), sp.getMauSac(), sl, sp.getGiaSanPham(), currencyVN.format(thanhTien) });
					int newSoLuong = (int) tableSanPham.getValueAt(row, 6) + sl;
					tableSanPham.setValueAt(newSoLuong, row, 6);
					txtTongTien.setText(currencyVN.format(tinhTongTien()));
				} else if (kiemtraMaSP(maSP) != -1) {
					int n = kiemtraMaSP(maSP);
					tableChiTietHDBH.setValueAt((int) tableChiTietHDBH.getValueAt(n, 7) + sl, n, 7);
					int newSoLuong = (int) tableSanPham.getValueAt(row, 6) + sl;
					tableSanPham.setValueAt(newSoLuong, row, 6);
					tableChiTietHDBH.setValueAt(currencyVN.format(
							(int) tableChiTietHDBH.getValueAt(n, 7) * (double) tableChiTietHDBH.getValueAt(n, 8)), n,
							9);
					txtTongTien.setText(currencyVN.format(tinhTongTien()));
				}
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

	public void themHSX() {
		if (validDataHSX()) {
			String ma = txtMaHSX.getText();
			String ten = txtTenHSX.getText();
			String sdt = txtSDT.getText();
			String dc = txtDC.getText();
			String tp = txtTP.getText();
			String qg = txtQuocGia.getText();

			HangSanXuat hsx = new HangSanXuat(ma, ten, sdt, dc, tp, qg);
			boolean rs = hsxDAO.themHangSanXuat(hsx);
			if (rs) {
				JOptionPane.showMessageDialog(this, "Thêm thành công một nhà sản xuất.");
				// lblThongBaoThemSP.setText("Thêm thành công một nhà sản xuất.");
				xoaTrangHSX();
				txtMaHSX.setText(generateKeyDAO.getKey("HangSanXuat"));
				showComboBox();
			} else
				JOptionPane.showMessageDialog(this, "Thêm nhà sản xuất không thành công");
			// lblThongBaoThemSP.setText("Thêm nhà sản xuất không thành công");
		}
	}

	public void themLoaiSP() {
		if (validDataLoaiSP()) {
			String ma = txtMaLoai.getText();
			String ten = txtTenLoai.getText();
			String moTa = txtMoTa.getText();

			LoaiSanPham loai = new LoaiSanPham(ma, ten, moTa);
			boolean rs = loaispDAO.themLoaiSanPham(loai);
			if (rs) {
				JOptionPane.showMessageDialog(this, "Thêm mới thành công loại sản phẩm");
				// lblThongBaoThemSP.setText("Thêm mới thành công loại sản phẩm");
				xoaTrangLoaiSP();
				txtMaLoai.setText(generateKeyDAO.getKey("LoaiSanPham"));
				showComboBox();
			} else
				JOptionPane.showMessageDialog(null, "Thêm mới loại sản phẩm không thành công");
			// lblThongBaoThemSP.setText("Thêm mới loại sản phẩm không thành công");
		}
	}

}
