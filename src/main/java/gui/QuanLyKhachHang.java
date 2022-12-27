package gui;

import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RootPaneContainer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.SessionFactory;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.demo.JCalendarDemo;

import Custom.ButtonGradient;
import dao.GenerateKeyDAO;
import dao.KhachHangDAO;
import dao_imp.GenerateKeyImp;
import dao_imp.KhachHangImp;
import dao_imp.MySessionFactory;
import entity.KhachHang;
import entity.SanPham;

import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDayChooser;
import com.toedter.calendar.JDateChooserBeanInfo;

public class QuanLyKhachHang extends JPanel {

	private JTable table;
	private DefaultTableModel tableKHMode;
	private TableModel tablePQMode;
	private JTable tableKH;
	private JTable table_1;
	private JTable table_2;
	private JTable table_3;
	private JScrollPane scrollPane;
	private JScrollPane scrollPaneKH;
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
	private JTextField txtTimKiem;
	private JTextField txtSDT;
	private JTextField textField_4;
	private JTextField textField_5;
	private DefaultTableModel model;
	private JPanel pCapNhatKH;
	private JPanel contentPane;
	private JTextField txtTenKH;
	private JTextField txtCMND;
	private JTextField txtDiaChi;
	private JTextField txtThanhPho;
	private JDateChooser dateChooser;
	private JTextField txtNgay;

	SessionFactory sessionFactory = new MySessionFactory().getSessionFactory();
	KhachHangDAO khachHangDAO = new KhachHangImp(sessionFactory);
	GenerateKeyDAO generateKeyDAO = new GenerateKeyImp(sessionFactory);
	SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	List<KhachHang> listKH = khachHangDAO.layTatCaKhachHang();
	private JComboBox cboGioiTinh;
	private JTextField txtTenKH_Them;
	private JTextField txtCMND_Them;
	private JTextField txtSDT_Them;
	private JTextField txtDC_Them;
	private JTextField txtTp_Them;
	private JPanel pThemKH;
	private JPanel pBoLocKH;
	private JTextField txtMaKH_CN;
	private JLabel lblThongBao;
	private JTextField txtMaKH;
	private JDateChooser dateChooserNS_Them;
	private JComboBox cboGioiTinh_Them;
	private JButton btnThem;

	public static void main(String[] args) {
		QuanLyKhachHang gui = new QuanLyKhachHang();
		gui.setVisible(true);
	}

	/**
	 * Create the panel.
	 */
	public QuanLyKhachHang() {
		// ----------------------------------------------------------------------------------------------------------------------------------------------
		// Chức năng quản lý khách hàng
		setBackground(new Color(255, 255, 255));
		setBounds(240, 0, 1100, 700);
		setLayout(null);

		JLabel lblQuanLyKhachHang = new JLabel("Quản lý khách hàng");
		lblQuanLyKhachHang.setFont(new Font("Tahoma", Font.PLAIN, 40));
		lblQuanLyKhachHang.setBounds(20, 21, 358, 60);
		add(lblQuanLyKhachHang);

		// Form cập nhật dữ liệu
		// ---------------------------------------------------------------------------------------------------------------------------------------

		// pCapNhatKH.setFocusCycleRoot(true);
		// pCapNhatKH.setFocusableWindowState(true);
		// pCapNhatKH.setUndecorated(true);
		// pCapNhatKH.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pCapNhatKH = new JPanel();
		// add(pCapNhatKH);
		pCapNhatKH.setBounds(290, 100, 786, 220);
		pCapNhatKH.setVisible(false);
		pCapNhatKH.setLayout(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBounds(0, 0, 786, 220);
		contentPane.setBorder(new TitledBorder(null, "C\u1EACP NH\u1EACT TH\u00D4NG TIN KH\u00C1CH H\u00C0NG",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pCapNhatKH.add(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Tên khách hàng:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(10, 80, 130, 25);
		contentPane.add(lblNewLabel);

		txtTenKH = new JTextField();
		txtTenKH.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtTenKH.setBounds(150, 80, 160, 25);
		contentPane.add(txtTenKH);
		txtTenKH.setColumns(10);

		JLabel lblSCmnd = new JLabel("Số CMND");
		lblSCmnd.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSCmnd.setBounds(10, 130, 130, 25);
		contentPane.add(lblSCmnd);

		txtCMND = new JTextField();
		txtCMND.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtCMND.setColumns(10);
		txtCMND.setBounds(150, 130, 160, 25);
		contentPane.add(txtCMND);

		JLabel lblNewLabel_1_1 = new JLabel("Số điện thoại");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_1.setBounds(10, 180, 130, 25);
		contentPane.add(lblNewLabel_1_1);

		txtSDT = new JTextField();
		txtSDT.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtSDT.setColumns(10);
		txtSDT.setBounds(150, 179, 160, 25);
		contentPane.add(txtSDT);

		JLabel lblNewLabel_1_1_1 = new JLabel("Địa chỉ");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_1_1.setBounds(320, 80, 130, 25);
		contentPane.add(lblNewLabel_1_1_1);

		txtDiaChi = new JTextField();
		txtDiaChi.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtDiaChi.setColumns(10);
		txtDiaChi.setBounds(430, 80, 160, 25);

		contentPane.add(txtDiaChi);

		JLabel lblNewLabel_1_1_1_1 = new JLabel("Thành phố");
		lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_1_1_1.setBounds(320, 130, 130, 25);
		contentPane.add(lblNewLabel_1_1_1_1);

		txtThanhPho = new JTextField();
		txtThanhPho.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtThanhPho.setColumns(10);
		txtThanhPho.setBounds(430, 130, 160, 25);
		contentPane.add(txtThanhPho);

		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("Ngày sinh:");
		lblNewLabel_1_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_1_1_1_1.setBounds(320, 180, 130, 25);
		contentPane.add(lblNewLabel_1_1_1_1_1);

		JCalendarDemo calendarDemo = new JCalendarDemo();
		calendarDemo.setBounds(500, 180, 160, -14);
		contentPane.add(calendarDemo);

		dateChooser = new JDateChooser();
		dateChooser.setBounds(430, 180, 160, 25);
		contentPane.add(dateChooser);
		dateChooser.setDateFormatString("dd/MM/yyyy");

		JLabel lblNewLabel_1_1_1_1_1_1 = new JLabel("Giới tính:");
		lblNewLabel_1_1_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_1_1_1_1_1.setBounds(320, 35, 130, 25);
		contentPane.add(lblNewLabel_1_1_1_1_1_1);

		cboGioiTinh = new JComboBox();
		cboGioiTinh.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cboGioiTinh.setModel(new DefaultComboBoxModel(new String[] { "Nam", "Nữ" }));
		cboGioiTinh.setBounds(430, 35, 160, 25);
		contentPane.add(cboGioiTinh);

		JButton btnCapNhat = new ButtonGradient();
		btnCapNhat.setText("Cập nhật");
		btnCapNhat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (validData()) {
					kiemtraUpdate();
					xoaHetDuLieuTrenTableModel();
					model = (DefaultTableModel) tableKH.getModel();
					showTable();
				}
			}
		});
		/*
		 * btnNewButton.addActionListener(new ActionListener() { public void
		 * actionPerformed(ActionEvent e) {
		 * 
		 * } });
		 */
		((ButtonGradient) btnCapNhat).setColor2(Color.decode("#0099F7"));
		btnCapNhat.setIcon(new ImageIcon(GiaoDienChinh_ChuCuaHang.class.getResource("/img/updateKH.png")));
		btnCapNhat.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCapNhat.setBounds(600, 72, 167, 40);
		contentPane.add(btnCapNhat);

		JButton btnThot = new ButtonGradient();
		btnThot.setText("Thoát");
		btnThot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pBoLocKH.setVisible(true);
				pCapNhatKH.setVisible(false);
				pThemKH.setVisible(false);

			}
		});
		((ButtonGradient) btnThot).setColor2(Color.decode("#0099F7"));
		btnThot.setIcon(new ImageIcon(GiaoDienChinh_ChuCuaHang.class.getResource("/img/cancel.png")));
		btnThot.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnThot.setBounds(600, 130, 167, 40);
		contentPane.add(btnThot);

		JLabel lblMKhchHng = new JLabel("Mã khách hàng");
		lblMKhchHng.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMKhchHng.setBounds(10, 30, 130, 25);
		contentPane.add(lblMKhchHng);

		txtMaKH_CN = new JTextField();
		txtMaKH_CN.setEditable(false);
		txtMaKH_CN.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtMaKH_CN.setColumns(10);
		txtMaKH_CN.setBounds(150, 30, 160, 25);
		contentPane.add(txtMaKH_CN);

		// ----------------------------------------------------------------------------------------------------
		// Panel thêm khách hàng
		pThemKH = new JPanel();
		add(pThemKH);
		pThemKH.setBounds(290, 100, 786, 220);
		pThemKH.setVisible(false);
		pThemKH.setLayout(null);

		JPanel contentPane_1 = new JPanel();
		contentPane_1.setBackground(Color.WHITE);
		contentPane_1.setLayout(null);
		contentPane_1.setBorder(new TitledBorder(null, "TH\u00CAM KH\u00C1CH H\u00C0NG", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		contentPane_1.setBounds(0, 0, 786, 220);
		pThemKH.add(contentPane_1);

		JLabel lblNewLabel_1 = new JLabel("Tên khách hàng:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(10, 80, 130, 25);
		contentPane_1.add(lblNewLabel_1);

		txtTenKH_Them = new JTextField();
		txtTenKH_Them.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtTenKH_Them.setColumns(10);
		txtTenKH_Them.setBounds(150, 80, 160, 25);
		contentPane_1.add(txtTenKH_Them);

		JLabel lblSCmnd_1 = new JLabel("Số CMND");
		lblSCmnd_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSCmnd_1.setBounds(10, 130, 130, 25);
		contentPane_1.add(lblSCmnd_1);

		txtCMND_Them = new JTextField();
		txtCMND_Them.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtCMND_Them.setColumns(10);
		txtCMND_Them.setBounds(150, 130, 160, 25);
		contentPane_1.add(txtCMND_Them);

		JLabel lblNewLabel_1_1_2 = new JLabel("Số điện thoại");
		lblNewLabel_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_1_2.setBounds(10, 180, 130, 25);
		contentPane_1.add(lblNewLabel_1_1_2);

		txtSDT_Them = new JTextField();
		txtSDT_Them.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtSDT_Them.setColumns(10);
		txtSDT_Them.setBounds(150, 179, 160, 25);
		contentPane_1.add(txtSDT_Them);

		JLabel lblNewLabel_1_1_1_2 = new JLabel("Địa chỉ");
		lblNewLabel_1_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_1_1_2.setBounds(320, 80, 130, 25);
		contentPane_1.add(lblNewLabel_1_1_1_2);

		txtDC_Them = new JTextField();
		txtDC_Them.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtDC_Them.setColumns(10);
		txtDC_Them.setBounds(430, 80, 160, 25);
		contentPane_1.add(txtDC_Them);

		JLabel lblNewLabel_1_1_1_1_2 = new JLabel("Thành phố");
		lblNewLabel_1_1_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_1_1_1_2.setBounds(320, 130, 130, 25);
		contentPane_1.add(lblNewLabel_1_1_1_1_2);

		txtTp_Them = new JTextField();
		txtTp_Them.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtTp_Them.setColumns(10);
		txtTp_Them.setBounds(430, 130, 160, 25);
		contentPane_1.add(txtTp_Them);

		JLabel lblNewLabel_1_1_1_1_1_2 = new JLabel("Ngày sinh:");
		lblNewLabel_1_1_1_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_1_1_1_1_2.setBounds(320, 180, 130, 25);
		contentPane_1.add(lblNewLabel_1_1_1_1_1_2);

		JCalendarDemo calendarDemo_1 = new JCalendarDemo();
		calendarDemo_1.setBounds(500, 180, 160, -14);
		contentPane_1.add(calendarDemo_1);

		dateChooserNS_Them = new JDateChooser();
		dateChooserNS_Them.setBounds(430, 180, 160, 25);
		dateChooserNS_Them.setDateFormatString("dd/MM/yyyy");
		contentPane_1.add(dateChooserNS_Them);

		JLabel lblNewLabel_1_1_1_1_1_1_1 = new JLabel("Giới tính:");
		lblNewLabel_1_1_1_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_1_1_1_1_1_1.setBounds(320, 30, 130, 25);
		contentPane_1.add(lblNewLabel_1_1_1_1_1_1_1);

		cboGioiTinh_Them = new JComboBox();
		cboGioiTinh_Them.setModel(new DefaultComboBoxModel(new String[] { "Nam", "Nữ" }));
		cboGioiTinh_Them.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cboGioiTinh_Them.setBounds(430, 30, 160, 25);
		contentPane_1.add(cboGioiTinh_Them);

		btnThem = new ButtonGradient();
		btnThem.setText("Thêm");
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				validDataThem();

				kiemTraAdd();

				xoaHetDuLieuTrenTableModel();
				model = (DefaultTableModel) tableKH.getModel();
				showTable();

			}
		});
		((ButtonGradient) btnThem).setColor2(Color.decode("#0099F7"));
		btnThem.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnThem.setBounds(600, 45, 167, 40);
		contentPane_1.add(btnThem);

		JButton btnThoat = new ButtonGradient();
		btnThoat.setText("Thoát");
		btnThoat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				add(pBoLocKH);
				pBoLocKH.setVisible(true);
				pThemKH.setVisible(false);

				xoaTrangThongTinKH();
			}
		});
		((ButtonGradient) btnThoat).setColor2(Color.decode("#0099F7"));
		btnThoat.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnThoat.setBounds(600, 145, 167, 40);
		contentPane_1.add(btnThoat);

		JLabel lblNewLabel_1_2 = new JLabel("Mã khách hàng:");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_2.setBounds(10, 30, 130, 25);
		contentPane_1.add(lblNewLabel_1_2);

		txtMaKH = new JTextField();
		txtMaKH.setEditable(false);
		txtMaKH.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtMaKH.setColumns(10);
		txtMaKH.setBounds(150, 30, 160, 25);
		contentPane_1.add(txtMaKH);

		JButton btnXoaTrang = new ButtonGradient();
		btnXoaTrang.setText("Xóa trắng");
		((ButtonGradient) btnXoaTrang).setColor2(Color.decode("#0099F7"));
		btnXoaTrang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				xoaTrangThongTinKH();
			}
		});
		btnXoaTrang.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnXoaTrang.setBounds(600, 95, 167, 40);
		contentPane_1.add(btnXoaTrang);

		scrollPaneKH = new JScrollPane();
		scrollPaneKH.setBounds(20, 345, 1055, 300);
		add(scrollPaneKH);

		tableKH = new JTable();
		tableKH.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				pCapNhatKH.setVisible(true);
				add(pCapNhatKH);
				pThemKH.setVisible(false);
				pBoLocKH.setVisible(false);

				int row = tableKH.getSelectedRow();
				txtMaKH_CN.setText(tableKH.getValueAt(row, 1).toString());
				txtTenKH.setText(tableKH.getValueAt(row, 2).toString());
				cboGioiTinh.setSelectedItem(tableKH.getValueAt(row, 3));
				txtCMND.setText(tableKH.getValueAt(row, 4).toString());
				txtSDT.setText(tableKH.getValueAt(row, 5).toString());
				txtDiaChi.setText(tableKH.getValueAt(row, 6).toString());
				txtThanhPho.setText(tableKH.getValueAt(row, 7).toString());
				try {
					Date date = new SimpleDateFormat("dd/MM/yyyy").parse((String) tableKH.getValueAt(row, 8));
					dateChooser.setDate(date);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		scrollPaneKH.setViewportView(tableKH);
		tableKH.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tableKH.setBackground(Color.WHITE);
		tableKH.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "STT", "M\u00E3 kh\u00E1ch h\u00E0ng", "T\u00EAn kh\u00E1ch h\u00E0ng",
						"Gi\u1EDBi t\u00EDnh", "S\u1ED1 CMND", "S\u1ED1 \u0111i\u1EC7n tho\u1EA1i",
						"\u0110\u1ECBa ch\u1EC9", "Th\u00E0nh ph\u1ED1", "Ng\u00E0y sinh" }) {
		});

		tableKH.setAutoCreateRowSorter(true);
		tableKH.setRowHeight(tableKH.getRowHeight() + 10);
		tableKH.setDefaultEditor(Object.class, null);

		model = (DefaultTableModel) tableKH.getModel();
		showTable();

		// ---------------------------------------------------------------------------------------------------------
		// Bo loc tim kiem khach hang
		pBoLocKH = new JPanel();
		pBoLocKH.setBorder(new TitledBorder(null, "B\u1ED9 l\u1ECDc t\u00ECm ki\u1EBFm", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		pBoLocKH.setBackground(new Color(255, 255, 255));
		pBoLocKH.setBounds(290, 100, 786, 220);
		add(pBoLocKH);
		pBoLocKH.setLayout(null);
		String[] s = new String[122];
		String n = "1930";
		s[1] = "";
		for (int i = 1; i < 92; i++) {
			s[i] = n;
			int num = Integer.parseInt(n);
			System.out.println(num);
			num = num + 1;
			n = Integer.toString(num);
			System.out.println(num);
		}

		// Nút tìm kiếm khách hàng

		JButton btnTimKiemKH = new ButtonGradient();
		btnTimKiemKH.setText("Tìm kiếm");
		((ButtonGradient) btnTimKiemKH).setColor2(Color.decode("#0099F7"));
		btnTimKiemKH.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnTimKiemKH.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * if(!txtCMND.getText().equals("")) { timKiemTheoSoCMND(); } else if
				 * (!txtSDT.getText().equals("")){ timkiemTheoDienThoai(); }
				 */
				/*
				 * if (txtTimKiem.getText().length() == 10) timkiemTheoDienThoai(); else if
				 * (txtTimKiem.getText().length() == 12) timKiemTheoSoCMND(); else
				 * timkiemTheoTen();
				 */
				timkiemTheoTen_DienThoai_CMND();

			}
		});
		btnTimKiemKH.setBackground(Color.GREEN);
		btnTimKiemKH.setBounds(150, 95, 171, 47);
		pBoLocKH.add(btnTimKiemKH);
		btnTimKiemKH.setIcon(new ImageIcon(GiaoDienChinh_ChuCuaHang.class.getResource("/img/icons8_search_40px.png")));

		// Nút xóa tất cả bộ lọc

		JButton btnXoaTatCaBoLoc = new ButtonGradient();
		btnXoaTatCaBoLoc.setText("Xóa trắng");
		((ButtonGradient) btnXoaTatCaBoLoc).setColor2(Color.decode("#0099F7"));
		btnXoaTatCaBoLoc.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnXoaTatCaBoLoc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				xoaTrangBoLoc();

			}
		});
		btnXoaTatCaBoLoc.setBounds(365, 95, 208, 47);
		pBoLocKH.add(btnXoaTatCaBoLoc);
		btnXoaTatCaBoLoc.setIcon(new ImageIcon(GiaoDienChinh_ChuCuaHang.class.getResource("/img/cancel.png")));

		txtTimKiem = new JTextField();
		txtTimKiem.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtTimKiem.setBounds(351, 33, 318, 35);
		pBoLocKH.add(txtTimKiem);
		txtTimKiem.setColumns(10);

		JLabel lblTimKiem = new JLabel("Tìm kiếm theo tên, số CMND, số điện thoại:");
		lblTimKiem.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTimKiem.setBounds(10, 33, 351, 35);
		pBoLocKH.add(lblTimKiem);

		// Điều chỉnh độ rộng của cột trong bản khách hàng

		tableKH.getColumnModel().getColumn(0).setPreferredWidth(20);
		tableKH.getColumnModel().getColumn(1).setResizable(false);
		tableKH.getColumnModel().getColumn(1).setPreferredWidth(74);
		tableKH.getColumnModel().getColumn(3).setPreferredWidth(40);
		tableKH.getColumnModel().getColumn(6).setPreferredWidth(100);

		JButton btnNewButton = new JButton("Thêm khách hàng");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * ThemKhachHang themKH = new ThemKhachHang(); themKH.setVisible(true);
				 * themKH.setLocationRelativeTo(null); themKH.setAlwaysOnTop(true);
				 * themKH.setResizable(false);
				 */
				pThemKH.setVisible(true);
				add(pThemKH);
				pCapNhatKH.setVisible(false);
				pBoLocKH.setVisible(false);
				txtMaKH.setText(generateKeyDAO.getKey("KhachHang"));
			}
		});
		btnNewButton.setBackground(Color.CYAN);
		btnNewButton.setIcon(
				new ImageIcon(QuanLyKhachHang.class.getResource("/img/icons8_add_user_female_skin_type_7_40px.png")));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.setBounds(20, 180, 240, 60);
		add(btnNewButton);

		JButton btnCapNhatKhach = new JButton("Cập nhật khách hàng");
		btnCapNhatKhach.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * CapNhatKhachHang capnhatKH = new CapNhatKhachHang();
				 * capnhatKH.setVisible(true); capnhatKH.setLocationRelativeTo(null);
				 * capnhatKH.setAlwaysOnTop(true); capnhatKH.setResizable(false);
				 * 
				 * KhachHang kh = khachHangDAO.layKhachHangTheoMa(tableKH.getColumnName(1));
				 * capnhatKH.layMa(tableKH.getColumnName(1));
				 */
				pCapNhatKH.setVisible(true);
				add(pCapNhatKH);
				// pCapNhatKH.setLocation(null);
				pBoLocKH.setVisible(false);
				pThemKH.setVisible(false);
				// scrollPane.setVisible(false);
				// tableKH.setVisible(false);

				// apnhatKH = new FormCapNhatKH();
				// capnhatKH.setVisible(true);
				/*
				 * capnhatKH.setLocationRelativeTo(null); capnhatKH.setAlwaysOnTop(true);
				 * capnhatKH.setResizable(false);
				 */
				// capnhatKH.txtKh.setText("1");

				/*
				 * CapNhatKhachHang capnhatKH = new CapNhatKhachHang();
				 * capnhatKH.setVisible(true); capnhatKH.setLocationRelativeTo(null);
				 * capnhatKH.setAlwaysOnTop(true); capnhatKH.setResizable(false); int row =
				 * tableKH.getSelectedRow(); String ma = tableKH.getValueAt(row, 1).toString();
				 * capnhatKH.showData(ma); if(!capnhatKH.isFocusableWindow()) {
				 * xoaHetDuLieuTrenTableModel(); showTable(); } QuanLyKhachHang gui = new
				 * QuanLyKhachHang(); gui.setVisible(true);
				 */

			}
		});
		btnCapNhatKhach.setBackground(Color.CYAN);
		btnCapNhatKhach.setIcon(new ImageIcon(QuanLyKhachHang.class.getResource("/img/icons8_update_40px_2.png")));
		btnCapNhatKhach.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnCapNhatKhach.setBounds(20, 260, 240, 60);
		add(btnCapNhatKhach);

		JButton btnBoLocTK = new JButton("Bộ lọc tìm kiếm");
		btnBoLocTK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pBoLocKH.setVisible(true);
				add(pBoLocKH);
				pCapNhatKH.setVisible(false);
				pThemKH.setVisible(false);
				// display();
			}
		});
		btnBoLocTK.setIcon(new ImageIcon(QuanLyKhachHang.class.getResource("/img/boLoc.png")));
		btnBoLocTK.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnBoLocTK.setBackground(Color.CYAN);
		btnBoLocTK.setBounds(20, 100, 240, 60);
		add(btnBoLocTK);

		lblThongBao = new JLabel("");
		lblThongBao.setForeground(Color.RED);
		lblThongBao.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblThongBao.setBounds(390, 10, 686, 26);
		add(lblThongBao);

		ButtonGradient btnXuatExcel = new ButtonGradient();
		btnXuatExcel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					xuatDS_Excel();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnXuatExcel.setText("Xuất excel");
		btnXuatExcel.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnXuatExcel.setBounds(923, 63, 151, 37);
		((ButtonGradient) btnXuatExcel).setColor2(Color.decode("#00AF00"));
		((ButtonGradient) btnXuatExcel).setColor1(Color.decode("#00AF00"));
		add(btnXuatExcel);
	}

	public void openFile(String file) throws IOException {
		File path = new File(file);
		java.awt.Desktop.getDesktop().open(path);
	}

	public void xuatDS_Excel() throws IOException {
		JFileChooser jFileChooser = new JFileChooser();
		jFileChooser.showSaveDialog(this);
		File saveFile = jFileChooser.getSelectedFile();
		if (saveFile != null) {
			saveFile = new File(saveFile.toString() + ".xlsx");

			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet spreadsheet = workbook.createSheet("Khách hàng");
			XSSFRow row = null;
			Cell cell = null;
			row = spreadsheet.createRow((short) 1);
			row.setHeight((short) 500);
			cell = row.createCell(0, CellType.STRING);
			cell.setCellValue("DANH SÁCH KHÁCH HÀNG");

			// Tạo định dạng: font Times New Roman, in đậm, font-size 14, chữ màu đen
			XSSFFont font = spreadsheet.getWorkbook().createFont();
			font.setFontName("Times New Roman");
			font.setBold(true);
			font.setFontHeightInPoints((short) 14); // font size
			font.setColor(IndexedColors.BLACK.getIndex()); // text color

			// Tạo cell style áp dụng font ở trên
			// Sử dụng màu nền xanh (Blue), định dạng border dưới
			CellStyle cellStyle = spreadsheet.getWorkbook().createCellStyle();
			cellStyle.setFont(font);
			cellStyle.setFillForegroundColor(IndexedColors.BLUE_GREY.getIndex());
			cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			cellStyle.setBorderBottom(BorderStyle.THIN);

			row = spreadsheet.createRow((short) 3);
			row.setHeight((short) 500);
			cell = row.createCell(0, CellType.STRING);
			cell.setCellValue("STT");
			cell.setCellStyle(cellStyle);
			cell = row.createCell(1, CellType.STRING);
			cell.setCellValue("Mã khách hàng");
			cell.setCellStyle(cellStyle);
			cell = row.createCell(2, CellType.STRING);
			cell.setCellValue("Tên khách hàng");
			cell.setCellStyle(cellStyle);
			cell = row.createCell(3, CellType.STRING);
			cell.setCellValue("Số CMND");
			cell.setCellStyle(cellStyle);
			cell = row.createCell(4, CellType.STRING);
			cell.setCellValue("Số điện thoại");
			cell.setCellStyle(cellStyle);
			cell = row.createCell(5, CellType.STRING);
			cell.setCellValue("Giới tính");
			cell.setCellStyle(cellStyle);
			cell = row.createCell(6, CellType.STRING);
			cell.setCellValue("Ngày sinh");
			cell.setCellStyle(cellStyle);
			cell = row.createCell(7, CellType.STRING);
			cell.setCellValue("Địa chỉ");
			cell.setCellStyle(cellStyle);
			cell = row.createCell(8, CellType.STRING);
			cell.setCellValue("Thành phố");
			cell.setCellStyle(cellStyle);

			List<KhachHang> listKH = khachHangDAO.layTatCaKhachHang();
			int i = 0;
			int tongSL = 0;
			SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			for (KhachHang kh : listKH) {
				row = spreadsheet.createRow((short) 4 + i);
				row.setHeight((short) 400);
				row.createCell(0).setCellValue(i + 1);
				row.createCell(1).setCellValue(kh.getMaKhachHang());
				row.createCell(2).setCellValue(kh.getTenKhachHang());
				row.createCell(3).setCellValue(kh.getSoCMND());
				row.createCell(4).setCellValue(kh.getSoDienThoai());
				row.createCell(5).setCellValue(kh.isGioiTinh() ? "Nữ" : "Nam");
				row.createCell(6).setCellValue(df.format(kh.getNgaySinh()));
				row.createCell(7).setCellValue(kh.getDiaChi());
				row.createCell(8).setCellValue(kh.getThanhPho());
				i++;

			}
			for (int columnIndex = 0; columnIndex < 9; columnIndex++) {
				spreadsheet.autoSizeColumn(columnIndex);
			}
			row = spreadsheet.createRow((short) i + 6);
			row.setHeight((short) 500);
			row.createCell(0).setCellValue("Tổng cộng");
			row.createCell(1).setCellValue(i);

			FileOutputStream out = new FileOutputStream(new File(saveFile.toString()));
			workbook.write(out);
			workbook.close();
			out.close();
			openFile(saveFile.toString());

		} else {
			Date date = new Date();

			saveFile = new File("DanhSachKhachHangTheoNgay" + date.toString() + ".xlsx");
		}

		// XSSFWorkbook workbook = new XSSFWorkbook();
		// XSSFSheet spreadsheet = workbook.createSheet("Sản phẩm");

	}

	private void timkiemTheoTen() {
		String ten = txtTimKiem.getText();
		int num = 1;
		List<KhachHang> listKH = khachHangDAO.layDanhSachKhachHangTheoTen(ten);

		if (listKH != null) {
			xoaHetDuLieuTrenTableModel();
			for (KhachHang kh : listKH) {
				Date day = kh.getNgaySinh();
				model.addRow(new Object[] { num, kh.getMaKhachHang(), kh.getTenKhachHang(),
						kh.isGioiTinh() ? "Nữ" : "Nam", kh.getSoCMND(), kh.getSoDienThoai(), kh.getDiaChi(),
						kh.getThanhPho(), df.format(day) });
			}
		} else {
			JOptionPane.showMessageDialog(this, "Không tìm thấy thông tin khách hàng, vui lòng nhập lại");
			txtTimKiem.selectAll();
			txtTimKiem.requestFocus();
		}

	}

	private static void display() {
		String[] items = { "One", "Two", "Three", "Four", "Five" };
		JComboBox<String> combo = new JComboBox<>(items);
		JTextField field1 = new JTextField("1234.56");
		JTextField field2 = new JTextField("9876.54");
		JPanel panel = new JPanel();
		panel.add(combo);
		panel.add(new JLabel("Field 1:"));
		panel.add(field1);
		panel.add(new JLabel("Field 2:"));
		panel.add(field2);
		int result = JOptionPane.showConfirmDialog(null, panel, "Test", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE);
		if (result == JOptionPane.OK_OPTION) {
			System.out.println(combo.getSelectedItem() + " " + field1.getText() + " " + field2.getText());
		} else {
			System.out.println("Cancelled");
		}
	}

	protected void timKiemTheoSoCMND() {
		String cmnd = txtTimKiem.getText().trim();
		int num = 1;
		KhachHang kh = khachHangDAO.layKhachhangTheoCMND(cmnd);

		if (kh != null) {
			xoaHetDuLieuTrenTableModel();
			Date day = kh.getNgaySinh();
			model.addRow(new Object[] { num, kh.getMaKhachHang(), kh.getTenKhachHang(), kh.isGioiTinh() ? "Nữ" : "Nam",
					kh.getSoCMND(), kh.getSoDienThoai(), kh.getDiaChi(), kh.getThanhPho(), df.format(day) });
		} else {
			JOptionPane.showMessageDialog(this, "Không tìm thấy thông tin khách hàng, vui lòng nhập lại");
			txtTimKiem.selectAll();
			txtTimKiem.requestFocus();
		}
	}

	protected void xoaTrangBoLoc() {
		txtTimKiem.setText("");
		txtSDT.setText("");
		txtTenKH.setText("");
		xoaHetDuLieuTrenTableModel();
		showTable();

	}

	protected void timkiemTheoDienThoai() {
		String sdt = txtTimKiem.getText().trim();
		int num = 1;
		// List<KhachHang> listKH = khachHangDAO.
		KhachHang kh = khachHangDAO.layKhachHangTheoDienthoai(sdt);

		if (kh != null) {
			xoaHetDuLieuTrenTableModel();
			Date day = kh.getNgaySinh();
			model.addRow(new Object[] { num, kh.getMaKhachHang(), kh.getTenKhachHang(), kh.isGioiTinh() ? "Nữ" : "Nam",
					kh.getSoCMND(), kh.getSoDienThoai(), kh.getDiaChi(), kh.getThanhPho(), df.format(day) });
		} else {
			JOptionPane.showMessageDialog(this, "Không tìm thấy thông tin khách hàng, vui lòng nhập lại");
			txtTimKiem.selectAll();
			txtTimKiem.requestFocus();
		}
	}

	protected void timkiemTheoTen_DienThoai_CMND() {
		String sdt = txtTimKiem.getText().trim();
		int num = 1;
		if (sdt.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Không tìm thấy thông tin khách hàng, vui lòng nhập lại");
			txtTimKiem.selectAll();
			txtTimKiem.requestFocus();
		} else {
			// List<KhachHang> listKH = khachHangDAO.
			List<KhachHang> listKH = khachHangDAO.layDanhSachKhachHangTheoTen_SDT_CMND(sdt);
			if (listKH.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Không tìm thấy thông tin khách hàng, vui lòng nhập lại");
				txtTimKiem.selectAll();
				txtTimKiem.requestFocus();
			} else if (listKH != null) {
				xoaHetDuLieuTrenTableModel();
				for (KhachHang kh : listKH) {
					Date day = kh.getNgaySinh();
					model.addRow(new Object[] { num, kh.getMaKhachHang(), kh.getTenKhachHang(),
							kh.isGioiTinh() ? "Nữ" : "Nam", kh.getSoCMND(), kh.getSoDienThoai(), kh.getDiaChi(),
							kh.getThanhPho(), df.format(day) });
				}
			}
		}
	}

	public void showTable() {
		int num = 1;
		List<KhachHang> listKH = khachHangDAO.layTatCaKhachHang();
		for (KhachHang kh : listKH) {
			Date day = kh.getNgaySinh();

			model.addRow(new Object[] { num, kh.getMaKhachHang(), kh.getTenKhachHang(), kh.isGioiTinh() ? "Nữ" : "Nam",
					kh.getSoCMND(), kh.getSoDienThoai(), kh.getDiaChi(), kh.getThanhPho(), df.format(day) });
			num++;
		}
	}

	public void xoaHetDuLieuTrenTableModel() {
		DefaultTableModel dm = (DefaultTableModel) tableKH.getModel();
		dm.getDataVector().removeAllElements();
	}

	/*
	 * public void showcomboData() { List<KhachHang> listKH =
	 * khachHangDAO.layTatCaKhachHang(); for (KhachHang kh : listKH) {
	 * cboNamSinh.addItem(kh.getNgaySinh().getYear());
	 * cboGioiTinh.addItem(kh.isGioiTinh() ? "Nữ" : "Nam"); } }
	 */

	/*
	 * public String layMaKH_Update(String maKH, String ten, String sdt, String
	 * cmnd, String diaChi, String thanhPho, String ) { txtTenKH.setText(ten);
	 * return maKH; }
	 */

	public void setVisibleTrang(boolean rs) {
		setVisible(rs);
	}

	private boolean validData() {
		String tenKH = txtTenKH.getText().trim();
		java.sql.Date ns;
		ns = new java.sql.Date(System.currentTimeMillis());
		String sdt = txtSDT.getText().trim();
		String cmnd = txtCMND.getText().trim();
		String dc = txtDiaChi.getText().trim();
		String tp = txtThanhPho.getText().trim();
		Date ngaySinh = dateChooser.getDate();

		if (!(tenKH.length() > 0)) {
			/*
			 * showMessage( "Error: Tên khách hàng không được bỏ trống", txtTenKH);
			 */
			JOptionPane.showMessageDialog(this, "Lỗi: Tên khách hàng không được bỏ trống");
			txtTenKH.requestFocus();
			txtTenKH.selectAll();
			return false;
		}
		if (cmnd.length() == 0) {
			// lblThongBao.setText("Error: Sai cú pháp! Số điện thoại phải nhập theo
			// mẫu: Gồm 10 hoặc 11 chữ số");
			JOptionPane.showMessageDialog(this, "Lỗi: Số CMND không được bỏ trống");
			txtCMND_Them.requestFocus();
			txtCMND_Them.selectAll();
			return false;
		}
		if (!(cmnd.length() >= 0 && (cmnd.matches("[0-9]{12}") || cmnd.matches("[0-9]{9}")))) {
			/*
			 * showMessage("Error: Sai cú pháp! Mã sách phải nhập theo mẫu: gồm 12 chữ số"
			 * , txtCMND);
			 */
			JOptionPane.showMessageDialog(this,
					"Lỗi: Sai cú pháp! Số cmnd phải nhập theo mẫu: gồm 9 hoặc 12 chữ số");
			txtCMND.requestFocus();
			txtCMND.selectAll();
			return false;
		}
		if (sdt.length() == 0) {
			// lblThongBao.setText("Error: Sai cú pháp! Số điện thoại phải nhập theo
			// mẫu: Gồm 10 hoặc 11 chữ số");
			JOptionPane.showMessageDialog(this, "Lỗi: Số điện thoại không được bỏ trống");
			txtSDT_Them.requestFocus();
			txtSDT_Them.selectAll();
			return false;
		}
		if (!((sdt.length() > 0 || sdt.length() == 0) && (sdt.matches("0[1-9][0-9]{8}")))) {
			/*
			 * showMessage("Error: Sai cú pháp! Số điện thoại phải nhập theo mẫu: Gồm 10 hoặc 11 chữ số"
			 * , txtSDT);
			 */
			JOptionPane.showMessageDialog(this,
					"Lỗi: Sai cú pháp! Số điện thoại phải nhập theo mẫu: Gồm 10 chữ số và bắt đầu bằng số 0");
			txtSDT.requestFocus();
			txtSDT.selectAll();
			return false;
		}
		if (tp.length() == 0) {
			/*
			 * showMessage("Error: Sai cú pháp! Thành phố phải nhập theo mẫu: Gồm dãy các chữ cái"
			 * , txtThanhPho);
			 */
			JOptionPane.showMessageDialog(this, "Lỗi: Thành phố không được bỏ trống");
			txtThanhPho.requestFocus();
			txtThanhPho.selectAll();
			return false;
		}
		if (!(tp.length() > 0)) {
			/*
			 * showMessage("Error: Sai cú pháp! Thành phố phải nhập theo mẫu: Gồm dãy các chữ cái"
			 * , txtThanhPho);
			 */
			JOptionPane.showMessageDialog(this,
					"Lỗi: Sai cú pháp! Thành phố phải nhập theo mẫu: Gồm dãy các chữ cái");
			txtThanhPho.requestFocus();
			txtThanhPho.selectAll();
			return false;
		}
		if (ngaySinh == null) {
			JOptionPane.showMessageDialog(this, "Lỗi: Ngày sinh không được để trống và theo định dạng dd/MM/yyyy!");
			dateChooser.requestFocus();
			return false;
		}

		return true;
	}

	private boolean validDataThem() {
		String tenKH = txtTenKH_Them.getText();

		String sdt = txtSDT_Them.getText();
		String cmnd = txtCMND_Them.getText();
		String dc = txtDC_Them.getText();
		String tp = txtTp_Them.getText();
		// String qg = txt

		if (tenKH.equals("")) {
			/*
			 * showMessage( "Error: Tên khách hàng không được bỏ trống", txtTenKH);
			 */
			// lblThongBao.setText("Error: Tên khách hàng không được bỏ trống");
			JOptionPane.showMessageDialog(this, "Lỗi: Tên khách hàng không được bỏ trống");
			txtTenKH_Them.requestFocus();
			txtTenKH_Them.selectAll();
			return false;
		} else if (cmnd.length() == 0) {
			// lblThongBao.setText("Error: Sai cú pháp! Số điện thoại phải nhập theo
			// mẫu: Gồm 10 hoặc 11 chữ số");
			JOptionPane.showMessageDialog(this, "Lỗi: Số CMND không được bỏ trống");
			txtCMND_Them.requestFocus();
			txtCMND_Them.selectAll();
			return false;
		} else if (!(cmnd.length() >= 0 && (cmnd.matches("[0-9]{12}") || cmnd.matches("[0-9]{9}")))) {
			/*
			 * showMessage("Error: Sai cú pháp! Số cmnd phải nhập theo mẫu: gồm 12 chữ số"
			 * , txtCMND);
			 */
			// lblThongBao.setText("Error: Sai cú pháp! Số cmnd phải nhập theo mẫu: gồm
			// 12 chữ số");
			JOptionPane.showMessageDialog(this, "Lỗi: Sai cú pháp! Số CMND có 9 hoặc 12 chữ số");
			txtCMND_Them.requestFocus();
			txtCMND_Them.selectAll();
			return false;
		} else if (sdt.length() == 0) {
			// lblThongBao.setText("Error: Sai cú pháp! Số điện thoại phải nhập theo
			// mẫu: Gồm 10 hoặc 11 chữ số");
			JOptionPane.showMessageDialog(this, "Lỗi: Số điện thoại không được bỏ trống");
			txtSDT_Them.requestFocus();
			txtSDT_Them.selectAll();
			return false;
		} else if (!((sdt.length() > 0 || sdt.length() == 0) && (sdt.matches("0[1-9][0-9]{8}")))) {
			/*
			 * showMessage("Error: Sai cú pháp! Số điện thoại phải nhập theo mẫu: Gồm 10 hoặc 11 chữ số"
			 * , txtSDT);
			 */
			// lblThongBao.setText("Error: Sai cú pháp! Số điện thoại phải nhập theo
			// mẫu: Gồm 10 hoặc 11 chữ số");
			JOptionPane.showMessageDialog(this,
					"Lỗi: Sai cú pháp! Số điện thoại phải nhập theo mẫu: Gồm 10 chữ số và bắt đầu bằng số 0");
			txtSDT_Them.requestFocus();
			txtSDT_Them.selectAll();
			return false;
		} else if (tp.length() == 0) {
			/*
			 * showMessage("Error: Sai cú pháp! Thành phố phải nhập theo mẫu: Gồm dãy các chữ cái"
			 * , txtThanhPho);
			 */
			JOptionPane.showMessageDialog(this, "Lỗi: Thành phố không được bỏ trống");
			txtTp_Them.requestFocus();
			txtTp_Them.selectAll();
			return false;
		} else if (!(tp.length() > 0)) {
			/*
			 * showMessage("Error: Sai cú pháp! Thành phố phải nhập theo mẫu: Gồm dãy các chữ cái"
			 * , txtThanhPho);
			 */
			// lblThongBao.setText("Error: Sai cú pháp! Thành phố phải nhập theo mẫu:
			// Gồm dãy các chữ cái");
			JOptionPane.showMessageDialog(this,
					"Lỗi: Sai cú pháp! Thành phố phải nhập theo mẫu: Gồm dãy các chữ cái");
			txtTp_Them.requestFocus();
			txtTp_Them.selectAll();
			return false;
		}
		java.util.Date ns;
		ns = new java.util.Date(System.currentTimeMillis());
		Date ngaySinh = dateChooserNS_Them.getDate();
		if (ngaySinh == null) {
			// lblThongBao.setText("Lỗi: Ngày sinh không được để trống!");
			JOptionPane.showMessageDialog(this, "Lỗi: Ngày sinh không được để trống và theo định dạng dd/MM/yyyy!");
			dateChooser.requestFocus();
			return false;
		}

		return true;
	}

	public void kiemTraAdd() {
		String tenKH = txtTenKH_Them.getText();
		// String gioiTinh = cboGioiTinh.getSelectedItem().toString();

		// Boolean gioiTinh =
		// Boolean.parseBoolean(cboGioiTinh.getSelectedItem().toString());
		boolean gioiTinh = true;
		if (cboGioiTinh_Them.getSelectedItem().equals("Nam"))
			gioiTinh = false;
		String cmnd = txtCMND_Them.getText();
		String sdt = txtSDT_Them.getText();
		String diaChi = txtDC_Them.getText();
		String thanhPho = txtTp_Them.getText();
		// String ngaySinh = dateChooser.getDateFormatString();
		// String ngaySinh = txtNgaySinh.getText();
		Date ngaySinh = dateChooserNS_Them.getDate();

		df = new SimpleDateFormat("dd/MM/yyyy");
		if (ngaySinh != null) {
			try {
				KhachHang kh = new KhachHang(generateKeyDAO.getKey("KhachHang"), tenKH, cmnd, sdt, diaChi, thanhPho,
						ngaySinh, gioiTinh);
				if (khachHangDAO.themKhachHang(kh)) {
					JOptionPane.showMessageDialog(this, "Thêm một khách hàng thành công");
					xoaTrangThongTinKH();
					txtMaKH.setText(generateKeyDAO.getKey("KhachHang"));
				} else
					JOptionPane.showMessageDialog(this, "Tạo mới không thành công");

			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

	}

	public boolean kiemtraUpdate() {
		String maKH = txtMaKH_CN.getText();
		String tenKH = txtTenKH.getText();
		// String gioiTinh = cboGioiTinh.getSelectedItem().toString();

		// Boolean gioiTinh =
		// Boolean.parseBoolean(cboGioiTinh.getSelectedItem().toString());
		boolean gioiTinh = true;
		if (cboGioiTinh.getSelectedItem().equals("Nam"))
			gioiTinh = false;
		String cmnd = txtCMND.getText();
		String sdt = txtSDT.getText();
		String diaChi = txtDiaChi.getText();
		String thanhPho = txtThanhPho.getText();
		Date ngaySinh = dateChooser.getDate();
		// String ngaySinh = txtNgaySinh.getText();

		df = new SimpleDateFormat("dd/MM/yyyy");
		try {
			if (ngaySinh != null) {
				KhachHang kh = new KhachHang(maKH, tenKH, cmnd, sdt, diaChi, thanhPho, ngaySinh, gioiTinh);
				boolean rs = khachHangDAO.capNhatKhachHang(kh);
				if (khachHangDAO.capNhatKhachHang(kh)) {
					JOptionPane.showMessageDialog(this, "Cập nhật thông tin khách hàng thành công");
					return true;
				} else
					JOptionPane.showMessageDialog(this, "Cập nhật thông tin khách hàng không thành công");
				return false;
			} else
				JOptionPane.showMessageDialog(this, "Sai định dạng: Ngày sinh định dạng dd/MM/yyyy.");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return false;
	}

	public void xoaTrangThongTinKH() {
		txtTenKH_Them.setText("");
		cboGioiTinh_Them.setSelectedIndex(0);
		txtCMND_Them.setText("");
		txtSDT_Them.setText("");
		txtDC_Them.setText("");
		txtTp_Them.setText("");
		dateChooserNS_Them.setDate(null);
		lblThongBao.setText("");
	}

	public void xoaTrangThongTinNV_CN() {
		txtTenKH.setText("");
		cboGioiTinh.setSelectedIndex(0);
		txtCMND.setText("");
		txtSDT.setText("");
		txtDiaChi.setText("");
		txtThanhPho.setText("");
		dateChooser.setDate(null);
		lblThongBao.setText("");
	}
}
