package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
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

import dao.ChuCuaHangDAO;
import dao.GenerateKeyDAO;
import dao.KhachHangDAO;
import dao.NhanVienDAO;
import dao.TaiKhoanDAO;
import dao_imp.ChuCuaHangImp;
import dao_imp.GenerateKeyForNhanVienBangHang;
import dao_imp.GenerateKeyImp;
import dao_imp.KhachHangImp;
import dao_imp.MySessionFactory;
import dao_imp.NhanVienImp;
import dao_imp.TaiKhoanImp;
import entity.ChuCuaHang;
import entity.KhachHang;
import entity.NhanVienBanHang;
import entity.TaiKhoan;

import javax.swing.border.TitledBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;

import org.json.*;
import org.json.simple.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import com.toedter.calendar.JDateChooser;
import Custom.ButtonGradient;

public class QuanLyNhanVien extends JPanel {

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
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;

	SessionFactory sessionFactory = new MySessionFactory().getSessionFactory();
	NhanVienDAO nhanvienDAO = new NhanVienImp(sessionFactory);
	TaiKhoanDAO taikhoanDAO = new TaiKhoanImp(sessionFactory);
	ChuCuaHangDAO chucuahangDAO = new ChuCuaHangImp(sessionFactory);
	GenerateKeyDAO generateKeyDAO = new GenerateKeyImp(sessionFactory);
	GenerateKeyForNhanVienBangHang gnv = new GenerateKeyForNhanVienBangHang(sessionFactory);
	SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	List<NhanVienBanHang> listNV = nhanvienDAO.layTatCaNhanVien();
	private DefaultTableModel model;
	private JTextField txtTimKiem;
	private JLabel lblTimKiem;
	private JPanel pBoLocNV;
	private JPanel pThemNV;
	private JTextField txtMaNV;
	private JTextField txtSDT;
	private JTextField txtCMND;
	private JTextField txtDC;
	private JTextField txtTP;
	private ButtonGradient btnThoatThemNV;
	private ButtonGradient btnThem;
	private JDateChooser dateChooserNVL_Them;
	private JDateChooser dateChooserNS_Them;
	private JComboBox cboGioiTinh;
	private JTextField txtTenNV;
	private JLabel lblThongBao;
	private JTextField txtMaNV_CN;
	private JTextField txtTenNV_CN;
	private JTextField txtSDT_CN;
	private JTextField txtCMND_CN;
	private JTextField txtDC_CN;
	private JTextField txtTP_CN;
	private JTextField txtQG_CN;
	private ButtonGradient btnCapNhat;
	private ButtonGradient btnThoatCapNhatNV;
	private JPanel pCapNhatNV;
	private JDateChooser dateChooserNS_CN;
	private JDateChooser dateChooserNVL_CN;
	private JComboBox cboGioiTinh_CN;
	private ButtonGradient btnXoaTrang_Them;

	/**
	 * Create the panel.
	 */
	@SuppressWarnings("unchecked")
	public QuanLyNhanVien() {

		setLayout(null);
		setBackground(Color.WHITE);
		setBounds(240, 0, 1100, 700);

		JLabel lblQuanLyNhanVien = new JLabel("Quản lý nhân viên");
		lblQuanLyNhanVien.setFont(new Font("Tahoma", Font.PLAIN, 40));
		lblQuanLyNhanVien.setBounds(20, 21, 358, 60);
		add(lblQuanLyNhanVien);

		ButtonGradient btnXuatExcel = new ButtonGradient();
		btnXuatExcel.setText("Xuất excel");
		btnXuatExcel.setFont(new Font("Tahoma", Font.BOLD, 14));
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
		btnXuatExcel.setBounds(925, 49, 151, 37);
		((ButtonGradient) btnXuatExcel).setColor2(Color.decode("#00AF00"));
		((ButtonGradient) btnXuatExcel).setColor1(Color.decode("#00AF00"));
		add(btnXuatExcel);

		JScrollPane scrollPaneNV = new JScrollPane();
		scrollPaneNV.setBounds(20, 345, 1054, 307);
		add(scrollPaneNV);

		tableNhanVien = new JTable();
		tableNhanVien.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = tableNhanVien.getSelectedRow();
				txtMaNV_CN.setText(tableNhanVien.getValueAt(row, 1).toString());
				txtTenNV_CN.setText(tableNhanVien.getValueAt(row, 2).toString());
				txtSDT_CN.setText(tableNhanVien.getValueAt(row, 5).toString());
				txtCMND_CN.setText(tableNhanVien.getValueAt(row, 4).toString());
				txtDC_CN.setText(tableNhanVien.getValueAt(row, 6).toString());
				txtTP_CN.setText(tableNhanVien.getValueAt(row, 7).toString());
				// txtQG_CN.setText(tableNhanVien.getValueAt(row, 8).toString());
				cboGioiTinh_CN.setSelectedItem(tableNhanVien.getValueAt(row, 3));

				try {
					Date date = new SimpleDateFormat("dd/MM/yyyy").parse((String) tableNhanVien.getValueAt(row, 8));
					dateChooserNS_CN.setDate(date);
					Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse((String) tableNhanVien.getValueAt(row, 9));
					dateChooserNVL_CN.setDate(date);
				} catch (java.text.ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		tableNhanVien.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		scrollPaneNV.setViewportView(tableNhanVien);
		tableNhanVien.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tableNhanVien.setBackground(Color.WHITE);
		tableNhanVien.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "STT", "Mã nhân viên", "Tên nhân viên", "Gi\u1EDBi t\u00EDnh", "S\u1ED1 CMND",
						"S\u1ED1 \u0111i\u1EC7n tho\u1EA1i", "\u0110\u1ECBa ch\u1EC9", "Th\u00E0nh ph\u1ED1",
						"Ng\u00E0y sinh", "Ng\u00E0y v\u00E0o l\u00E0m", "Tình trạng" }) {
		});
		tableNhanVien.getColumnModel().getColumn(0).setPreferredWidth(20);
		tableNhanVien.getColumnModel().getColumn(1).setResizable(false);
		tableNhanVien.getColumnModel().getColumn(1).setPreferredWidth(74);
		tableNhanVien.getColumnModel().getColumn(3).setPreferredWidth(40);
		tableNhanVien.getColumnModel().getColumn(6).setPreferredWidth(100);
		tableNhanVien.setAutoCreateRowSorter(true);
		tableNhanVien.setRowHeight(tableNhanVien.getRowHeight() + 10);
		tableNhanVien.setDefaultEditor(Object.class, null);

		TableColumn colThanhPho = tableNhanVien.getColumnModel().getColumn(7);
		JComboBox cboTablecolumnThanhPho = new JComboBox();
		/*
		 * String strJSON ="{key: value}"; JSONObject obj = new JSONObject(strJSON);
		 * 
		 * String[] dsThanhPho = {"An Giang","Bà Rịa – Vũng Tàu","Bắc Giang",
		 * "Bạc Liêu"}; for(int i = 0; i < 63; i++) { String s = ""; if(i < 10 &&
		 * s.length() < 2) { s = "0" + "i"; } else s = "i"; String username =
		 * obj.getJSONObject(s).getString("name"); }
		 */

		/*
		 * //JSON parser object to parse read file JSONParser jsonParser = new
		 * JSONParser();
		 * 
		 * try (FileReader reader = new FileReader("Tinh_ThanhPho.json")) { //Read JSON
		 * file Object obj = jsonParser.parse(reader); JSONArray employeeList =
		 * (JSONArray) obj; System.out.println(employeeList);
		 * 
		 * 
		 * } catch (FileNotFoundException e) { e.printStackTrace(); } catch (IOException
		 * e) { e.printStackTrace(); } catch (ParseException e) { e.printStackTrace(); }
		 * 
		 * /*cboTablecolumnGioiTinh.addItem("Tp.HCM");
		 * cboTablecolumnGioiTinh.addItem("Tây Ninh"); colGioiTinh.setCellEditor(new
		 * DefaultCellEditor(cboTablecolumnGioiTinh));
		 */

		model = (DefaultTableModel) tableNhanVien.getModel();
		showTable();

		JPanel pLoc = new JPanel();
		pLoc.setBackground(Color.WHITE);
		pLoc.setBounds(305, 80, 766, 255);
		add(pLoc);
		pLoc.setLayout(null);

		pBoLocNV = new JPanel();
		pLoc.add(pBoLocNV);
		pBoLocNV.setBorder(new TitledBorder(null, "B\u1ED9 l\u1ECDc t\u00ECm ki\u1EBFm", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		pBoLocNV.setBackground(Color.WHITE);
		pBoLocNV.setBounds(0, 0, 767, 255);
		pBoLocNV.setLayout(null);
		String[] s = new String[122];
		String n = "1900";
		s[0] = "";
		for (int i = 1; i < 122; i++) {
			s[i] = n;
			int num = Integer.parseInt(n);
			System.out.println(num);
			num = num + 1;
			n = Integer.toString(num);
			System.out.println(num);
		}
		System.out.println(s);

		lblTimKiem = new JLabel("Tìm kiếm theo tên, số cmnd, số điện thoại");
		lblTimKiem.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTimKiem.setBounds(25, 40, 321, 35);
		pBoLocNV.add(lblTimKiem);

		// Nút tìm kiếm nhân viên

		JButton btnTimKiemNV = new ButtonGradient();
		btnTimKiemNV.setText("Tìm kiếm");
		btnTimKiemNV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * boolean rsTinhTrang = cboTinhTrang.getSelectedItem().toString().equals("");
				 * if(cboNamSinhNV.getSelectedItem().toString().equals("") &&
				 * !cboGioiTinhNV.getSelectedItem().toString().equals(""))
				 * timkiemTheoGioiTinh(); else
				 * if(!cboNamSinhNV.getSelectedItem().toString().equals("") &&
				 * cboGioiTinhNV.getSelectedItem().toString().equals("")) timkiemTheoNam(); else
				 * if(cboNamSinhNV.getSelectedItem().toString().equals("") &&
				 * cboGioiTinhNV.getSelectedItem().toString().equals("") && !rsTinhTrang)
				 * timkiemTheoTinhTrang(); else
				 * if(cboNamSinhNV.getSelectedItem().toString().equals("") &&
				 * cboGioiTinhNV.getSelectedItem().toString().equals("") && rsTinhTrang)
				 * JOptionPane.showMessageDialog(null, "Nhập thông tim tìm kiếm");
				 */

				/*
				 * if (txtTimKiem.getText().length() == 10) timKiemTheoSDT(); else if
				 * (txtTimKiem.getText().length() == 12) timKiemTheoSoCMND(); else
				 * timKiemTheoTenNV();
				 */
				timKiemTheoTenNV_SDT_CMND();

			}

		});
		((ButtonGradient) btnTimKiemNV).setColor2(Color.decode("#0099F7"));
		btnTimKiemNV.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnTimKiemNV.setBounds(132, 102, 175, 50);
		pBoLocNV.add(btnTimKiemNV);
		btnTimKiemNV.setIcon(new ImageIcon(GiaoDienChinh_ChuCuaHang.class.getResource("/img/icons8_search_40px.png")));

		// Nút xóa tất cả bộ lọc

		JButton btnXoaTatCaBoLocNV = new ButtonGradient();
		btnXoaTatCaBoLocNV.setText("Xóa trắng");
		btnXoaTatCaBoLocNV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * cboNamSinhNV.setSelectedIndex(0); cboGioiTinhNV.setSelectedIndex(0);
				 * cboTinhTrang.setSelectedIndex(0);
				 */
				txtTimKiem.setText("");
				xoaHetDuLieuTrenTableModel();
				showTable();
			}
		});
		((ButtonGradient) btnXoaTatCaBoLocNV).setColor2(Color.decode("#0099F7"));
		btnXoaTatCaBoLocNV.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnXoaTatCaBoLocNV.setBounds(387, 102, 197, 50);
		pBoLocNV.add(btnXoaTatCaBoLocNV);
		btnXoaTatCaBoLocNV.setIcon(new ImageIcon(GiaoDienChinh_ChuCuaHang.class.getResource("/img/cancel.png")));

		txtTimKiem = new JTextField();
		txtTimKiem.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtTimKiem.setBounds(352, 40, 317, 35);
		pBoLocNV.add(txtTimKiem);
		txtTimKiem.setColumns(10);

		JButton btnThemNV = new JButton("Thêm nhân viên");
		btnThemNV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * ThemNhanVien themNV = new ThemNhanVien(); themNV.setVisible(true);
				 * themNV.setLocationRelativeTo(null); themNV.setAlwaysOnTop(true);
				 * themNV.setResizable(false);
				 */

				pLoc.add(pThemNV);
				pThemNV.setVisible(true);
				pBoLocNV.setVisible(false);
				pCapNhatNV.setVisible(false);
				txtMaNV.setText(gnv.getKey("NhanVienBanHang"));
			}
		});

		btnThemNV.setBackground(Color.CYAN);
		btnThemNV.setIcon(new ImageIcon(QuanLyNhanVien.class.getResource("/img/themKH.png")));
		btnThemNV.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnThemNV.setBounds(20, 100, 240, 60);
		add(btnThemNV);

		JButton btnCapNhatNV = new JButton("Cập nhật nhân viên");
		btnCapNhatNV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * CapNhatNhanVien capnhatNV = new CapNhatNhanVien();
				 * capnhatNV.setVisible(true); capnhatNV.setLocationRelativeTo(null);
				 * capnhatNV.setAlwaysOnTop(true); capnhatNV.setResizable(false);
				 * 
				 * int row = tableNhanVien.getSelectedRow(); String ma =
				 * tableNhanVien.getValueAt(row, 1).toString(); capnhatNV.showData(ma);
				 * if(!capnhatNV.isFocusableWindow()) { xoaHetDuLieuTrenTableModel();
				 * showTable(); } QuanLyKhachHang gui = new QuanLyKhachHang();
				 * gui.setVisible(true);
				 */

				pLoc.add(pCapNhatNV);
				pCapNhatNV.setVisible(true);
				pThemNV.setVisible(false);
				pBoLocNV.setVisible(false);
			}
		});
		btnCapNhatNV.setIcon(new ImageIcon(QuanLyNhanVien.class.getResource("/img/icons8_update_user_48px.png")));
		btnCapNhatNV.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnCapNhatNV.setBackground(Color.CYAN);
		btnCapNhatNV.setBounds(20, 175, 240, 60);
		add(btnCapNhatNV);

		JButton btnXoaNV = new JButton("Xóa nhân viên");
		btnXoaNV.setIcon(new ImageIcon(QuanLyNhanVien.class.getResource("/img/icons8_trash_40px.png")));
		btnXoaNV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				xoaDataNhanVien();
			}
		});
		btnXoaNV.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnXoaNV.setBackground(Color.CYAN);
		btnXoaNV.setBounds(20, 255, 240, 60);
		add(btnXoaNV);

		// ------------------------------------------------------------------------------------------------
		// Thêm thông tin nhân viên
		// ------------------------------------------------------------------------------------------------
		pThemNV = new JPanel();
		// pLoc.add(pThemNV);
		pThemNV.setBorder(new TitledBorder(null, "Thêm nhân viên", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pThemNV.setBackground(Color.WHITE);
		pThemNV.setBounds(0, 0, 767, 255);
		pThemNV.setLayout(null);

		JLabel lblNewLabel = new JLabel("Mã nhân viên");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 25, 133, 25);
		pThemNV.add(lblNewLabel);

		JLabel lblTnNhnVin = new JLabel("Tên nhân viên");
		lblTnNhnVin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTnNhnVin.setBounds(10, 65, 133, 25);
		pThemNV.add(lblTnNhnVin);

		JLabel lblSinThoi = new JLabel("Số điện thoại");
		lblSinThoi.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSinThoi.setBounds(10, 105, 133, 25);
		pThemNV.add(lblSinThoi);

		JLabel lblSCmnd = new JLabel("Số CMND");
		lblSCmnd.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSCmnd.setBounds(10, 145, 133, 25);
		pThemNV.add(lblSCmnd);

		txtMaNV = new JTextField();
		txtMaNV.setEditable(false);
		txtMaNV.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtMaNV.setBounds(130, 25, 153, 25);
		pThemNV.add(txtMaNV);
		txtMaNV.setColumns(10);

		txtTenNV = new JTextField();
		txtTenNV.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtTenNV.setColumns(10);
		txtTenNV.setBounds(130, 65, 153, 25);
		pThemNV.add(txtTenNV);

		txtSDT = new JTextField();
		txtSDT.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtSDT.setColumns(10);
		txtSDT.setBounds(130, 105, 153, 25);
		pThemNV.add(txtSDT);

		txtCMND = new JTextField();
		txtCMND.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtCMND.setColumns(10);
		txtCMND.setBounds(130, 145, 153, 25);
		pThemNV.add(txtCMND);

		JLabel lblaCh = new JLabel("Địa chỉ");
		lblaCh.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblaCh.setBounds(324, 25, 133, 25);
		pThemNV.add(lblaCh);

		txtDC = new JTextField();
		txtDC.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtDC.setColumns(10);
		txtDC.setBounds(430, 25, 153, 25);
		pThemNV.add(txtDC);

		JLabel lblThnhPh = new JLabel("Thành phố");
		lblThnhPh.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblThnhPh.setBounds(324, 65, 133, 25);
		pThemNV.add(lblThnhPh);

		JLabel lblNgySinh = new JLabel("Ngày sinh");
		lblNgySinh.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNgySinh.setBounds(324, 105, 133, 25);
		pThemNV.add(lblNgySinh);

		JLabel lblNgyVoLm = new JLabel("Ngày vào làm");
		lblNgyVoLm.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNgyVoLm.setBounds(324, 145, 133, 25);
		pThemNV.add(lblNgyVoLm);

		txtTP = new JTextField();
		txtTP.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtTP.setColumns(10);
		txtTP.setBounds(430, 65, 153, 25);
		pThemNV.add(txtTP);

		JLabel lblGiiTnh = new JLabel("Giới tính");
		lblGiiTnh.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblGiiTnh.setBounds(10, 185, 133, 25);
		pThemNV.add(lblGiiTnh);

		dateChooserNS_Them = new JDateChooser();
		dateChooserNS_Them.setBounds(430, 105, 153, 25);
		dateChooserNS_Them.setDateFormatString("dd/MM/yyyy");
		pThemNV.add(dateChooserNS_Them);

		dateChooserNVL_Them = new JDateChooser();
		dateChooserNVL_Them.setBounds(430, 145, 153, 25);
		dateChooserNVL_Them.setDateFormatString("dd/MM/yyyy");
		pThemNV.add(dateChooserNVL_Them);

		btnThem = new ButtonGradient();
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				themNV();
			}
		});
		btnThem.setText("Thêm");
		((ButtonGradient) btnThem).setColor2(Color.decode("#0099F7"));
		btnThem.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnThem.setBounds(593, 50, 153, 40);
		pThemNV.add(btnThem);

		btnXoaTrang_Them = new ButtonGradient();
		btnXoaTrang_Them.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				xoaTrangThongTinNV();
			}
		});
		btnXoaTrang_Them.setText("Xóa trắng");
		((ButtonGradient) btnXoaTrang_Them).setColor2(Color.decode("#0099F7"));
		btnXoaTrang_Them.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnXoaTrang_Them.setBounds(593, 105, 153, 40);
		pThemNV.add(btnXoaTrang_Them);

		btnThoatThemNV = new ButtonGradient();
		btnThoatThemNV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pLoc.add(pBoLocNV);
				pBoLocNV.setVisible(true);
				pThemNV.setVisible(false);
				xoaTrangThongTinNV();
			}
		});
		btnThoatThemNV.setText("Thoát");
		((ButtonGradient) btnThoatThemNV).setColor2(Color.decode("#0099F7"));
		btnThoatThemNV.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnThoatThemNV.setBounds(593, 158, 153, 40);
		pThemNV.add(btnThoatThemNV);

		cboGioiTinh = new JComboBox();
		cboGioiTinh.setModel(new DefaultComboBoxModel(new String[] { "Nam", "Nữ" }));
		cboGioiTinh.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cboGioiTinh.setBounds(130, 185, 153, 25);
		pThemNV.add(cboGioiTinh);

		// -------------------------------------------------------------------------------------------------
		// Cập nhật thông tin nhân viên
		pCapNhatNV = new JPanel();
		pCapNhatNV.setLayout(null);
		pCapNhatNV.setBorder(
				new TitledBorder(null, "Cập nhật nhân viên", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pCapNhatNV.setBackground(Color.WHITE);
		pCapNhatNV.setBounds(0, 0, 767, 255);
		// pLoc.add(pCapNhatNV);

		JLabel lblNewLabel_1 = new JLabel("Mã nhân viên");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(10, 25, 133, 25);
		pCapNhatNV.add(lblNewLabel_1);

		JLabel lblTnNhnVin_1 = new JLabel("Tên nhân viên");
		lblTnNhnVin_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTnNhnVin_1.setBounds(10, 65, 133, 25);
		pCapNhatNV.add(lblTnNhnVin_1);

		JLabel lblSinThoi_1 = new JLabel("Số điện thoại");
		lblSinThoi_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSinThoi_1.setBounds(10, 105, 133, 25);
		pCapNhatNV.add(lblSinThoi_1);

		JLabel lblSCmnd_1 = new JLabel("Số CMND");
		lblSCmnd_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSCmnd_1.setBounds(10, 145, 133, 25);
		pCapNhatNV.add(lblSCmnd_1);

		txtMaNV_CN = new JTextField();
		txtMaNV_CN.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtMaNV_CN.setEditable(false);
		txtMaNV_CN.setColumns(10);
		txtMaNV_CN.setBounds(130, 25, 153, 25);
		pCapNhatNV.add(txtMaNV_CN);

		txtTenNV_CN = new JTextField();
		txtTenNV_CN.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtTenNV_CN.setColumns(10);
		txtTenNV_CN.setBounds(130, 65, 153, 25);
		pCapNhatNV.add(txtTenNV_CN);

		txtSDT_CN = new JTextField();
		txtSDT_CN.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtSDT_CN.setColumns(10);
		txtSDT_CN.setBounds(130, 105, 153, 25);
		pCapNhatNV.add(txtSDT_CN);

		txtCMND_CN = new JTextField();
		txtCMND_CN.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtCMND_CN.setColumns(10);
		txtCMND_CN.setBounds(130, 145, 153, 25);
		pCapNhatNV.add(txtCMND_CN);

		JLabel lblaCh_1 = new JLabel("Địa chỉ");
		lblaCh_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblaCh_1.setBounds(324, 25, 133, 25);
		pCapNhatNV.add(lblaCh_1);

		txtDC_CN = new JTextField();
		txtDC_CN.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtDC_CN.setColumns(10);
		txtDC_CN.setBounds(430, 25, 153, 25);
		pCapNhatNV.add(txtDC_CN);

		JLabel lblThnhPh_1 = new JLabel("Thành phố");
		lblThnhPh_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblThnhPh_1.setBounds(324, 65, 133, 25);
		pCapNhatNV.add(lblThnhPh_1);

		JLabel lblNgySinh_1 = new JLabel("Ngày sinh");
		lblNgySinh_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNgySinh_1.setBounds(324, 105, 133, 25);
		pCapNhatNV.add(lblNgySinh_1);

		JLabel lblNgyVoLm_1 = new JLabel("Ngày vào làm");
		lblNgyVoLm_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNgyVoLm_1.setBounds(324, 145, 133, 25);
		pCapNhatNV.add(lblNgyVoLm_1);

		txtTP_CN = new JTextField();
		txtTP_CN.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtTP_CN.setColumns(10);
		txtTP_CN.setBounds(430, 65, 153, 25);
		pCapNhatNV.add(txtTP_CN);

		JLabel lblGiiTnh_1 = new JLabel("Giới tính");
		lblGiiTnh_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblGiiTnh_1.setBounds(10, 185, 133, 25);
		pCapNhatNV.add(lblGiiTnh_1);

		dateChooserNS_CN = new JDateChooser();
		dateChooserNS_CN.setBounds(430, 105, 153, 25);
		dateChooserNS_CN.setDateFormatString("dd/MM/yyyy");
		pCapNhatNV.add(dateChooserNS_CN);

		dateChooserNVL_CN = new JDateChooser();
		dateChooserNVL_CN.setBounds(430, 145, 153, 25);
		dateChooserNVL_CN.setDateFormatString("dd/MM/yyyy");
		pCapNhatNV.add(dateChooserNVL_CN);

		btnCapNhat = new ButtonGradient();
		btnCapNhat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				capnhatNhanVien();
			}
		});
		btnCapNhat.setText("Cập nhật");
		((ButtonGradient) btnCapNhat).setColor2(Color.decode("#0099F7"));
		btnCapNhat.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCapNhat.setBounds(593, 64, 153, 40);
		pCapNhatNV.add(btnCapNhat);

		btnThoatCapNhatNV = new ButtonGradient();
		btnThoatCapNhatNV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pLoc.add(pBoLocNV);
				pBoLocNV.setVisible(true);
				pCapNhatNV.setVisible(false);
				pThemNV.setVisible(false);
				xoaTrangThongTinNV_CN();
			}
		});
		btnThoatCapNhatNV.setText("Thoát");
		((ButtonGradient) btnThoatCapNhatNV).setColor2(Color.decode("#0099F7"));
		btnThoatCapNhatNV.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnThoatCapNhatNV.setBounds(593, 130, 153, 40);
		pCapNhatNV.add(btnThoatCapNhatNV);

		cboGioiTinh_CN = new JComboBox();
		cboGioiTinh_CN.setModel(new DefaultComboBoxModel(new String[] { "Nam", "Nữ" }));
		cboGioiTinh_CN.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cboGioiTinh_CN.setBounds(130, 185, 153, 25);
		pCapNhatNV.add(cboGioiTinh_CN);

		lblThongBao = new JLabel("");
		lblThongBao.setForeground(Color.RED);
		lblThongBao.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblThongBao.setBounds(388, 24, 686, 35);
		add(lblThongBao);

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
			cell.setCellValue("Mã nhân viên");
			cell.setCellStyle(cellStyle);
			cell = row.createCell(2, CellType.STRING);
			cell.setCellValue("Tên nhân viên");
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
			cell.setCellValue("Ngày vào làm");
			cell.setCellStyle(cellStyle);
			cell = row.createCell(8, CellType.STRING);
			cell.setCellValue("Địa chỉ");
			cell.setCellStyle(cellStyle);
			cell = row.createCell(9, CellType.STRING);
			cell.setCellValue("Thành phố");
			cell.setCellStyle(cellStyle);
			cell = row.createCell(10, CellType.STRING);
			cell.setCellValue("Tình trạng");
			cell.setCellStyle(cellStyle);

			List<NhanVienBanHang> listNV = nhanvienDAO.layTatCaNhanVien();
			int i = 0;
			int tongSL = 0;
			SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			for (NhanVienBanHang kh : listNV) {
				row = spreadsheet.createRow((short) 4 + i);
				row.setHeight((short) 400);
				row.createCell(0).setCellValue(i + 1);
				row.createCell(1).setCellValue(kh.getMaNhanVien());
				row.createCell(2).setCellValue(kh.getTenNhanVien());
				row.createCell(3).setCellValue(kh.getSoCMND());
				row.createCell(4).setCellValue(kh.getSoDienThoai());
				row.createCell(5).setCellValue(kh.isGioiTinh() ? "Nữ" : "Nam");
				row.createCell(6).setCellValue(df.format(kh.getNgaySinh()));
				row.createCell(7).setCellValue(df.format(kh.getNgayVaoLam()));
				row.createCell(8).setCellValue(kh.getDiaChi());
				row.createCell(9).setCellValue(kh.getThanhPho());
				row.createCell(10).setCellValue(kh.isTinhTrang() ? "Đang làm" : "Nghỉ");
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

	private void timKiemTheoSoCMND() {
		String cmnd = txtTimKiem.getText();
		int num = 1;
		NhanVienBanHang nv = nhanvienDAO.layNhanVienTheoSoCMND(cmnd);
		xoaHetDuLieuTrenTableModel();

		Date day = nv.getNgaySinh();
		Date day1 = nv.getNgayVaoLam();
		model.addRow(new Object[] { num, nv.getMaNhanVien(), nv.getTenNhanVien(), nv.isGioiTinh(), nv.getSoCMND(),
				nv.getSoDienThoai(), nv.getDiaChi(), nv.getThanhPho(), df.format(day), df.format(day1),
				nv.isTinhTrang() ? "Đang làm" : "Nghỉ" });

	}

	private void timKiemTheoSDT() {
		String sdt = txtTimKiem.getText();
		int num = 1;
		NhanVienBanHang nv = nhanvienDAO.layNhanVienTheoSoDienthoai(sdt);
		xoaHetDuLieuTrenTableModel();

		Date day = nv.getNgaySinh();
		Date day1 = nv.getNgayVaoLam();
		model.addRow(new Object[] { num, nv.getMaNhanVien(), nv.getTenNhanVien(), nv.isGioiTinh(), nv.getSoCMND(),
				nv.getSoDienThoai(), nv.getDiaChi(), nv.getThanhPho(), df.format(day), df.format(day1),
				nv.isTinhTrang() ? "Đang làm" : "Nghỉ" });

	}

	private void timKiemTheoTenNV() {
		String cmnd = txtTimKiem.getText();
		int num = 1;
		List<NhanVienBanHang> listNV = nhanvienDAO.layDanhSachNhanVienTheoTen(cmnd);
		xoaHetDuLieuTrenTableModel();
		for (NhanVienBanHang nv : listNV) {
			Date day = nv.getNgaySinh();
			Date day1 = nv.getNgayVaoLam();
			model.addRow(new Object[] { num, nv.getMaNhanVien(), nv.getTenNhanVien(), nv.isGioiTinh() ? "Nữ" : "Nam",
					nv.getSoCMND(), nv.getSoDienThoai(), nv.getDiaChi(), nv.getThanhPho(), df.format(day),
					df.format(day1), nv.isTinhTrang() ? "Đang làm" : "Nghỉ" });
		}

	}

	private void timKiemTheoTenNV_SDT_CMND() {
		String cmnd = txtTimKiem.getText();
		int num = 1;
		if (cmnd.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Không tìm thấy thông tin nhân viên, vui lòng nhập lại");
			txtTimKiem.selectAll();
			txtTimKiem.requestFocus();
		} else {
			List<NhanVienBanHang> listNV = nhanvienDAO.layDanhSachNhanVienTheoTen_SDT_CMND(cmnd);
			if (listNV.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Không tìm thấy thông tin nhân viên, vui lòng nhập lại");
				txtTimKiem.selectAll();
				txtTimKiem.requestFocus();
			} else if (listNV != null) {
				xoaHetDuLieuTrenTableModel();
				for (NhanVienBanHang nv : listNV) {
					Date day = nv.getNgaySinh();
					Date day1 = nv.getNgayVaoLam();
					model.addRow(
							new Object[] { num, nv.getMaNhanVien(), nv.getTenNhanVien(), nv.isGioiTinh() ? "Nữ" : "Nam",
									nv.getSoCMND(), nv.getSoDienThoai(), nv.getDiaChi(), nv.getThanhPho(),
									df.format(day), df.format(day1), nv.isTinhTrang() ? "Đang làm" : "Nghỉ" });
					num++;
				}
			}
		}

	}

	/*
	 * protected void xoaTrangBoLoc() { cboGioiTinhNV.setSelectedIndex(0);
	 * cboNamSinhNV.setSelectedIndex(0);;
	 * 
	 * }
	 */

	/*
	 * protected void timkiemTheoDienThoai() { String sdt = txtSDT.getText().trim();
	 * int num = 1; // List<KhachHang> listKH = khachHangDAO. KhachHang kh =
	 * khachHangDAO.layKhachHangTheoDienthoai(sdt); xoaHetDuLieuTrenTableModel();
	 * Date day = kh.getNgaySinh(); model.addRow(new Object[] { num,
	 * kh.getMaKhachHang(), kh.getTenKhachHang(), kh.isGioiTinh() ? "Nam" : "Nữ",
	 * kh.getSoCMND(), kh.getSoDienThoai(), kh.getDiaChi(), kh.getThanhPho(),
	 * df.format(day) }); }
	 */

	public void showTable() {
		int num = 1;
		List<NhanVienBanHang> listNV = nhanvienDAO.layTatCaNhanVien();
		for (NhanVienBanHang nv : listNV) {
			Date day = nv.getNgaySinh();
			Date ngayVaoLam = nv.getNgayVaoLam();

			model.addRow(new Object[] { num, nv.getMaNhanVien(), nv.getTenNhanVien(), nv.isGioiTinh() ? "Nữ" : "Nam",
					nv.getSoCMND(), nv.getSoDienThoai(), nv.getDiaChi(), nv.getThanhPho(), df.format(day),
					df.format(ngayVaoLam), nv.isTinhTrang() ? "Đang làm" : "Nghỉ" });
			num++;
		}
	}

	private void xoaHetDuLieuTrenTableModel() {
		DefaultTableModel dm = (DefaultTableModel) tableNhanVien.getModel();
		dm.getDataVector().removeAllElements();
		// dm.fireTableDataChanged();
	}

	/*
	 * public void showcomboData() { List<NhanVienBanHang> listNV =
	 * nhanvienDAO.layTatCaNhanVien(); for (NhanVienBanHang nv : listNV) {
	 * cboNamSinhNV.addItem(nv.getNgaySinh().getYear());
	 * cboGioiTinhNV.addItem(nv.isGioiTinh() ? "Nam" : "Nữ"); } }
	 */

	/*
	 * @SuppressWarnings("deprecation") private void timkiemTheoNam() { String n =
	 * cboNamSinhNV.getSelectedItem().toString(); int nam = Integer.parseInt(n);
	 * System.out.println(nam); int num = 1; List<NhanVienBanHang> listNV =
	 * nhanvienDAO.layDanhSachNhanVienTheoNamSinh(nam);
	 * xoaHetDuLieuTrenTableModel(); for (NhanVienBanHang nv : listNV) {
	 * if(nv.getNgaySinh().getYear() == nam) { model.addRow(new Object[] { num,
	 * nv.getMaNhanVien(), nv.getTenNhanVien(), nv.isGioiTinh() ? "Nữ" : "Nam",
	 * nv.getSoCMND(), nv.getSoDienThoai(), nv.getDiaChi(), nv.getThanhPho(),
	 * nv.getNgaySinh() ,nv.getNgayVaoLam(), nv.isTinhTrang() ? "Đang làm":
	 * "Nghỉ"}); num++; } else continue; } }
	 */

	/*
	 * private void timkiemTheoGioiTinh() { String s =
	 * cboGioiTinhNV.getSelectedItem().toString(); int num = 1; boolean gt = true;
	 * if(s.equals("Nam")) gt = false; List<NhanVienBanHang> listNV =
	 * nhanvienDAO.layDanhSachNhanVienTheoGioiTinh(gt);
	 * xoaHetDuLieuTrenTableModel(); for (NhanVienBanHang nv : listNV) {
	 * 
	 * model.addRow(new Object[] { num, nv.getMaNhanVien(), nv.getTenNhanVien(),
	 * nv.isGioiTinh() ? "Nữ" : "Nam", nv.getSoCMND(), nv.getSoDienThoai(),
	 * nv.getDiaChi(), nv.getThanhPho(), nv.getNgaySinh() ,nv.getNgayVaoLam(),
	 * nv.isTinhTrang() ? "Đang làm": "Nghỉ"}); num++;
	 * 
	 * } }
	 */

	/*
	 * private void timkiemTheoTinhTrang() { int n =
	 * cboTinhTrang.getSelectedIndex(); String s =
	 * cboTinhTrang.getSelectedItem().toString(); int num = 0; boolean gt = true;
	 * if(n == 1) gt = false; List<NhanVienBanHang> listNV =
	 * nhanvienDAO.layDanhSachNhanVienTheoGioiTinh(gt);
	 * xoaHetDuLieuTrenTableModel(); for (NhanVienBanHang nv : listNV) {
	 * 
	 * model.addRow(new Object[] { num, nv.getMaNhanVien(), nv.getTenNhanVien(),
	 * nv.isGioiTinh() ? "Nữ" : "Nam", nv.getSoCMND(), nv.getSoDienThoai(),
	 * nv.getDiaChi(), nv.getThanhPho(), nv.getNgaySinh() ,nv.getNgayVaoLam(),
	 * nv.isTinhTrang() ? "Đang làm": "Nghỉ"}); num++;
	 * 
	 * } }
	 */

	public void xoaDataNhanVien() {
		int row = tableNhanVien.getSelectedRow();

		String maNV = (String) tableNhanVien.getValueAt(row, 1);
		NhanVienBanHang nv = nhanvienDAO.layNhanVienTheoMa(maNV);
		String ten = tableNhanVien.getValueAt(row, 2).toString();
		boolean gioiTinh = true;
		if (tableNhanVien.getValueAt(row, 3).equals("Nam"))
			gioiTinh = false;
		String cmnd = tableNhanVien.getValueAt(row, 4).toString();
		String sdt = tableNhanVien.getValueAt(row, 5).toString();
		String dc = tableNhanVien.getValueAt(row, 6).toString();
		String tp = tableNhanVien.getValueAt(row, 7).toString();
		String ngay = tableNhanVien.getValueAt(row, 8).toString();
		String ngayLam = tableNhanVien.getValueAt(row, 9).toString();

		df = new SimpleDateFormat("dd/MM/yyyy");
		try {
			NhanVienBanHang nhanVien = new NhanVienBanHang(maNV, ten, gioiTinh, cmnd, sdt, dc, tp, df.parse(ngay),
					df.parse(ngayLam), false);
			System.out.println(nhanVien);
			boolean rs = nhanvienDAO.capNhatNhanVien(nhanVien);

			TaiKhoan tk = taikhoanDAO.layThongTinTaiKhoanTheoTenDangNhap(maNV);
			ChuCuaHang cch = chucuahangDAO.layThongTinChuCuaHang();
			ChuCuaHang chu = new ChuCuaHang(cch.getMaChuCuaHang());
			TaiKhoan taiKhoan = new TaiKhoan(maNV, "123456", "Nhân viên", false, false, chu, nhanVien);
			taikhoanDAO.cpanhatTaiKhoan(taiKhoan);
			System.out.println(taiKhoan);
			if (rs) {
				int n = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa nhân viên này?",
						"Thông báo xác nhận xóa nhân viên", JOptionPane.YES_NO_OPTION);

				// JOptionPane.showMessageDialog(null, "Xóa thông tin nhân viên thành công");
				// lblThongBao.setText("Xóa thông tin nhân viên thành công");
				if (n == JOptionPane.YES_OPTION) {
					model = (DefaultTableModel) tableNhanVien.getModel();
					model.removeRow(row);
				}

			} else
				JOptionPane.showMessageDialog(this, "Xóa thông tin nhân viên không thành công");
			// lblThongBao.setText("Xóa thông tin nhân viên không thành công");

		} catch (Exception e1) {
			e1.printStackTrace();
		}
		// NhanVienBanHang nv = new ;
	}

	private static String parseEmployeeObject(JSONObject employee, String key) {

		// Get employee object within list
		JSONObject employeeObject = (JSONObject) employee.get(key);

		// Get employee first name
		String name = (String) employeeObject.get("name");

		return name;
	}

	@SuppressWarnings("deprecation")
	private boolean validData() {
		String tenKH = txtTenNV.getText().trim();
		java.sql.Date ns;
		ns = new java.sql.Date(System.currentTimeMillis());
		String sdt = txtSDT.getText().trim();
		String cmnd = txtCMND.getText().trim();
		String dc = txtDC.getText().trim();
		String tp = txtTP.getText().trim();
		// String qg = txtQG.getText();

		if (!(tenKH.length() > 0)) {
			/*
			 * showMessage( "Error: Tên nhân viên không được bỏ trống", txtTenKH);
			 */
			// lblThongBao.setText("Lỗi: Tên nhân viên không được bỏ trống");
			JOptionPane.showMessageDialog(this, "Lỗi: Tên nhân viên không được bỏ trống");
			txtTenNV.requestFocus();
			txtTenNV.selectAll();
			return false;
		}
		if (sdt.length() == 0) {
			JOptionPane.showMessageDialog(this, "Lỗi: Số điện thoại không được bỏ trống!");
			txtSDT.requestFocus();
			txtSDT.selectAll();
			return false;
		}
		if (!(sdt.length() >= 0 && sdt.matches("0[0-9]{9}"))) {
			/*
			 * showMessage("Error: Sai cú pháp! Số điện thoại phải nhập theo mẫu: Gồm 10 chữ số và bắt đầu bằng số 0"
			 * , txtSDT);
			 */
			// lblThongBao.setText("Error: Sai cú pháp! Số điện thoại phải nhập theo
			// mẫu: Gồm 10 hoặc 11 chữ số");
			JOptionPane.showMessageDialog(this,
					"Lỗi: Sai cú pháp! Số điện thoại phải nhập theo mẫu: Gồm 10 chữ số và bắt đầu bằng số 0");
			txtSDT.requestFocus();
			txtSDT.selectAll();
			return false;
		}
		if (cmnd.length() == 0) {
			JOptionPane.showMessageDialog(this, "Lỗi: Số CMND không được bỏ trống!");
			txtCMND.requestFocus();
			txtCMND.selectAll();
			return false;
		}
		if (!(cmnd.length() >= 0 && (cmnd.matches("[0-9]{12}") || cmnd.matches("[0-9]{9}")))) {
			/*
			 * showMessage("Error: Sai cú pháp! Mã sách phải nhập theo mẫu: gồm 12 chữ số"
			 * , txtCMND);
			 */
			// lblThongBao.setText("Error: Sai cú pháp! Số CMND phải nhập theo mẫu: gồm
			// 12 chữ số");
			JOptionPane.showMessageDialog(this,
					"Lỗi: Sai cú pháp! Số CMND phải nhập theo mẫu: gồm 9 hoặc 12 chữ số");
			txtCMND.requestFocus();
			txtCMND.selectAll();
			return false;
		}
		if (tp.length() == 0) {
			JOptionPane.showMessageDialog(this, "Lỗi: Thành phố không được bỏ trống!");
			txtTP.requestFocus();
			txtTP.selectAll();
			return false;
		}
		if (!(tp.length() > 0)) {
			/*
			 * showMessage("Error: Sai cú pháp! Thành phố phải nhập theo mẫu: Gồm dãy các chữ cái"
			 * , txtThanhPho);
			 */
			// lblThongBao.setText("Error: Sai cú pháp! Thành phố phải nhập theo mẫu:
			// Gồm dãy các chữ cái");
			JOptionPane.showMessageDialog(this,
					"Lỗi: Sai cú pháp! Thành phố phải nhập theo mẫu: Gồm dãy các chữ cái");
			txtTP.requestFocus();
			txtTP.selectAll();
			return false;
		}

		Date ngay = dateChooserNS_Them.getDate();

		if (ngay == null) {
			// lblThongBao.setText("Lỗi: Ngày sinh không được để trống và theo định dạng
			// !");
			JOptionPane.showMessageDialog(this, "Lỗi: Ngày sinh không được để trống và theo định dạng dd/MM/yyyy!");
			dateChooserNS_Them.requestFocus();
			return false;

		}
		if (new java.util.Date().getYear() - ngay.getYear() < 18) {
			System.out.println(new java.util.Date().getYear() - ngay.getYear());
			// lblThongBao.setText("Lỗi: Nhân viên chưa đủ 18 tuổi!");
			JOptionPane.showMessageDialog(this, "Lỗi: Nhân viên chưa đủ 18 tuổi!");
			dateChooserNS_Them.requestFocus();
			return false;
		}
		Date ngay1 = dateChooserNVL_Them.getDate();

		if (ngay1 == null) {
			JOptionPane.showMessageDialog(this, "Lỗi: Ngày vào làm không được để trống và theo định dạng dd/MM/yyyy!!");
			// lblThongBao.setText("Lỗi: Ngày vào làm không được để trống!");
			dateChooserNVL_Them.requestFocus();
			return false;
		}
		Date ngay2 = new Date(ngay1.getTime());
		long ngayChenhLech = (new java.util.Date().getTime() - ngay2.getTime()) / (1000 * 60 * 60 * 24);
		if (ngayChenhLech < 0) {
			// lblThongBao.setText("Lỗi: Ngày vào làm không được lớn hơn ngày hiện tại quá 1
			// ngày!");
			JOptionPane.showMessageDialog(this, "Lỗi: Ngày vào làm không được lớn hơn ngày hiện tại quá 1 ngày");
			dateChooserNVL_Them.requestFocus();
			return false;
		}

		return true;
	}

	@SuppressWarnings("deprecation")
	private boolean validData_CN() {
		String tenKH = txtTenNV_CN.getText().trim();
		java.sql.Date ns;
		ns = new java.sql.Date(System.currentTimeMillis());
		String sdt = txtSDT_CN.getText().trim();
		String cmnd = txtCMND_CN.getText().trim();
		String dc = txtDC_CN.getText().trim();
		String tp = txtTP_CN.getText().trim();
		// String qg = txtQG_CN.getText();

		if (!(tenKH.length() > 0)) {
			/*
			 * showMessage( "Error: Tên nhân viên không được bỏ trống", txtTenKH);
			 */
			// lblThongBao.setText("Lỗi: Tên nhân viên không được bỏ trống");
			JOptionPane.showMessageDialog(this, "Lỗi: Tên nhân viên không được bỏ trống");
			txtTenNV_CN.requestFocus();
			txtTenNV_CN.selectAll();
			return false;
		}
		if (sdt.length() == 0) {
			JOptionPane.showMessageDialog(this, "Lỗi: Số điện thoại không được bỏ trống!");
			txtSDT_CN.requestFocus();
			txtSDT_CN.selectAll();
			return false;
		}
		if (!((sdt.length() > 0 || sdt.length() == 0) && (sdt.matches("[0-9]{10}") || sdt.matches("[0-9]{11}")))) {
			/*
			 * showMessage("Error: Sai cú pháp! Số điện thoại phải nhập theo mẫu: Gồm 10 hoặc 11 chữ số"
			 * , txtSDT);
			 */
			// lblThongBao.setText("Error: Sai cú pháp! Số điện thoại phải nhập theo
			// mẫu: Gồm 10 hoặc 11 chữ số");
			JOptionPane.showMessageDialog(this,
					"Lỗi: Sai cú pháp! Số điện thoại phải nhập theo mẫu: Gồm 10 hoặc 11 chữ số");
			txtSDT_CN.requestFocus();
			txtSDT_CN.selectAll();
			return false;
		}
		if (cmnd.length() == 0) {
			JOptionPane.showMessageDialog(this, "Lỗi: Số cmnd không được để trống!");
			txtCMND_CN.requestFocus();
			txtCMND_CN.selectAll();
			return false;
		}
		if (!(cmnd.length() >= 0 && (cmnd.matches("[0-9]{12}") || cmnd.matches("[0-9]{9}")))) {
			/*
			 * showMessage("Error: Sai cú pháp! Mã sách phải nhập theo mẫu: gồm 12 chữ số"
			 * , txtCMND);
			 */
			// lblThongBao.setText("Error: Sai cú pháp! Số CMND phải nhập theo mẫu: gồm
			// 12 chữ số");
			JOptionPane.showMessageDialog(this,
					"Lỗi: Sai cú pháp! Số CMND phải nhập theo mẫu: gồm 9 hoặc 12 chữ số");
			txtCMND_CN.requestFocus();
			txtCMND_CN.selectAll();
			return false;
		}
		if (tp.length() == 0) {
			JOptionPane.showMessageDialog(this, "Lỗi: Thành phố không được bỏ trống!");
			txtTP_CN.requestFocus();
			txtTP_CN.selectAll();
			return false;
		}
		if (!(tp.length() > 0)) {
			/*
			 * showMessage("Error: Sai cú pháp! Thành phố phải nhập theo mẫu: Gồm dãy các chữ cái"
			 * , txtThanhPho);
			 */
			// lblThongBao.setText("Error: Sai cú pháp! Thành phố phải nhập theo mẫu:
			// Gồm dãy các chữ cái");
			JOptionPane.showMessageDialog(this,
					"Lỗi: Sai cú pháp! Thành phố phải nhập theo mẫu: Gồm dãy các chữ cái");
			txtTP_CN.requestFocus();
			txtTP_CN.selectAll();
			return false;
		}

		Date ngay = dateChooserNS_CN.getDate();

		if (ngay == null) {
			// lblThongBao.setText("Lỗi: Ngày sinh không được để trống!");
			JOptionPane.showMessageDialog(this, "Lỗi: Ngày sinh không được để trống và theo định dạng dd/MM/yyyy!");
			dateChooserNS_CN.requestFocus();
			return false;

		}
		if (new java.util.Date().getYear() - ngay.getYear() < 18) {
			System.out.println(new java.util.Date().getYear() - ngay.getYear());
			// lblThongBao.setText("Lỗi: Nhân viên chưa đủ 18 tuổi!");
			JOptionPane.showMessageDialog(this, "Lỗi: Nhân viên chưa đủ 18 tuổi!");
			dateChooserNS_CN.requestFocus();
			return false;
		}
		Date ngay1 = dateChooserNVL_CN.getDate();

		if (ngay1 == null) {
			JOptionPane.showMessageDialog(this, "Lỗi: Ngày vào làm không được để trống và theo định dạng dd/MM/yyyy!");
			// lblThongBao.setText("Lỗi: Ngày vào làm không được để trống!");
			dateChooserNVL_CN.requestFocus();
			return false;
		}
		Date ngay2 = new Date(ngay1.getTime());
		long ngayChenhLech = (new java.util.Date().getTime() - ngay2.getTime()) / (1000 * 60 * 60 * 24);
		if (ngayChenhLech < 0) {
			// lblThongBao.setText("Lỗi: Ngày vào làm không được lớn hơn ngày hiện tại quá 1
			// ngày!");
			JOptionPane.showMessageDialog(this, "Lỗi: Ngày vào làm không được lớn hơn ngày hiện tại quá 1 ngày");
			dateChooserNVL_CN.requestFocus();
			return false;
		}

		return true;
	}

	public void xoaTrangThongTinNV() {
		txtTenNV.setText("");
		cboGioiTinh.setSelectedIndex(0);
		txtCMND.setText("");
		txtSDT.setText("");
		txtDC.setText("");
		txtTP.setText("");
		// txtQG.setText("");
		dateChooserNS_Them.setDate(null);
		dateChooserNVL_Them.setDate(null);
		lblThongBao.setText("");
	}

	public void xoaTrangThongTinNV_CN() {
		txtTenNV_CN.setText("");
		cboGioiTinh_CN.setSelectedIndex(0);
		txtCMND_CN.setText("");
		txtSDT_CN.setText("");
		txtDC_CN.setText("");
		txtTP_CN.setText("");
		// txtQG_CN.setText("");
		dateChooserNS_CN.setDate(null);
		dateChooserNVL_CN.setDate(null);
		lblThongBao.setText("");
	}

	public void themNV() {
		if (validData()) {
			String tenNV = txtTenNV.getText();
			// String gioiTinh = cboGioiTinh.getSelectedItem().toString();

			// Boolean gioiTinh =
			// Boolean.parseBoolean(cboGioiTinh.getSelectedItem().toString());
			boolean gioiTinh = true;
			if (cboGioiTinh.getSelectedItem().equals("Nam"))
				gioiTinh = false;
			String cmnd = txtCMND.getText();
			String sdt = txtSDT.getText();
			String diaChi = txtDC.getText();
			String thanhPho = txtTP.getText();
			// String ngaySinh = dateChooser.getDateFormatString();
			// String ngaySinh = txtNga.getText();
			// String ngayVaoLam = dateChooser_1.getDateFormatString();
			Date ngaySinh = dateChooserNS_Them.getDate();
			Date ngayVaoLam = dateChooserNVL_Them.getDate();

			df = new SimpleDateFormat("dd/MM/yyyy");
			try {
				NhanVienBanHang nv = new NhanVienBanHang(gnv.getKey("NhanVienBanHang"), tenNV, cmnd, sdt, diaChi,
						thanhPho, ngaySinh, gioiTinh, ngayVaoLam);
				nv.setTinhTrang(true);
				if (nhanvienDAO.themNhanVien(nv)) {
					lblThongBao.setText("");
					DefaultTableModel dm = (DefaultTableModel) tableNhanVien.getModel();
					dm.getDataVector().removeAllElements();
					model = (DefaultTableModel) tableNhanVien.getModel();
					showTable();
					JOptionPane.showMessageDialog(this,
							"Thêm một nhân viên thành công. Tài khoản của nhân viên đã tạo với tên đăng nhập là mã nhân viên và mật khẩu mặcđịnh là 123456");
					// lblThongBao.setText(
					// "Thêm một nhân viên thành công. \n Tài khoản của nhân viên đã tạo với tên
					// đăng nhập là mã nhân viên và mật khẩu mặc định là 123456");
					ChuCuaHang cch = new ChuCuaHang("CCH001");
					TaiKhoan tk = new TaiKhoan(txtMaNV.getText(), "123456", "Nhân viên", false, false, cch, nv);
					taikhoanDAO.themTaiKhoan(tk);
					txtMaNV.setText(gnv.getKey("NhanVienBanHang"));
					xoaTrangThongTinNV();
				} else
					JOptionPane.showMessageDialog(this, "Tạo mới không thành công");
				// lblThongBao.setText("Thêm nhân viên không thành công");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	public void capnhatNhanVien() {
		if (validData_CN()) {
			String maNV = txtMaNV_CN.getText();
			String tenNV = txtTenNV_CN.getText();
			// String gioiTinh = cboGioiTinh.getSelectedItem().toString();

			// Boolean gioiTinh =
			// Boolean.parseBoolean(cboGioiTinh.getSelectedItem().toString());
			boolean gioiTinh = true;
			if (cboGioiTinh_CN.getSelectedItem().equals("Nam"))
				gioiTinh = false;
			String cmnd = txtCMND_CN.getText();
			String sdt = txtSDT_CN.getText();
			String diaChi = txtDC_CN.getText();
			String thanhPho = txtTP_CN.getText();
			Date ngaySinh = dateChooserNS_CN.getDate();
			Date ngayVaoLam = dateChooserNVL_CN.getDate();
			// String ngaySinh = txtNgaySinh.getText();

			df = new SimpleDateFormat("dd/MM/yyyy");
			try {
				NhanVienBanHang nv = new NhanVienBanHang(maNV, tenNV, gioiTinh, cmnd, sdt, diaChi, thanhPho, ngaySinh,
						ngayVaoLam, true);
				boolean rs = nhanvienDAO.capNhatNhanVien(nv);
				if (rs) {
					JOptionPane.showMessageDialog(this, "Cập nhật thông tin nhân viên thành công");
					// lblThongBao.setText("Cập nhật thông tin nhân viên thành công");
					xoaHetDuLieuTrenTableModel();
					model = (DefaultTableModel) tableNhanVien.getModel();
					showTable();

				} else
					JOptionPane.showMessageDialog(this, "Cập nhật thông tin nhân viên không thành công");
				// lblThongBao.setText("Cập nhật thông tin nhân viên không thành công");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

}
