package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.SessionFactory;

import Connect.ConnectDB;
import Custom.ButtonGradient;
import dao.ChiTietBanHangDAO;
import dao.GenerateKeyDAO;
import dao.HangSanXuatDAO;
import dao.HoaDonBanHangDAO;
import dao.KhachHangDAO;
import dao.LoaiSanPhamDAO;
import dao.NhanVienDAO;
import dao.SanPhamDAO;
import dao_imp.ChiTietBanHangImp;
import dao_imp.GenerateKeyImp;
import dao_imp.HangSanXuatImp;
import dao_imp.HoaDonBanHangImp;
import dao_imp.KhachHangImp;
import dao_imp.LoaiSanPhamImp;
import dao_imp.MySessionFactory;
import dao_imp.NhanVienImp;
import dao_imp.SanPhamImp;
import entity.HangSanXuat;
import entity.HoaDonBanHang;
import entity.KhachHang;
import entity.LoaiSanPham;
import entity.NhanVienBanHang;
import entity.SanPham;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import javax.swing.border.TitledBorder;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;

public class QuanLySanPham extends JPanel {

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
	GenerateKeyDAO generateKeyDAO = new GenerateKeyImp(sessionFactory);
	ChiTietBanHangDAO chiTietBanHangDAO = new ChiTietBanHangImp(sessionFactory);
	HoaDonBanHangDAO hoaDonBanHangDAO = new HoaDonBanHangImp(sessionFactory);
	KhachHangDAO khachHangDAO = new KhachHangImp(sessionFactory);
	NhanVienDAO nhanVienDAO = new NhanVienImp(sessionFactory);
	SanPhamDAO sanphamDAO = new SanPhamImp(sessionFactory);
	HangSanXuatDAO hsxDAO = new HangSanXuatImp(sessionFactory);
	LoaiSanPhamDAO loaispDAO = new LoaiSanPhamImp(sessionFactory);
	SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	List<HoaDonBanHang> listHDBH = hoaDonBanHangDAO.layTatCaHoaDonBanHang();
	List<KhachHang> listKH = khachHangDAO.layTatCaKhachHang();
	List<NhanVienBanHang> listNV = nhanVienDAO.layTatCaNhanVien();
	private DefaultTableModel model;
	private JComboBox cboLoaiSP;
	private JComboBox cboHangSanXuat;
	private JTextField txtMaSP;
	private JTextField txtTenSP;
	private JTextField txtSL;
	private JTextField txtGia;
	private JComboBox cboLoai;
	private JComboBox cboHSX;
	private JComboBox cboSize;
	private JComboBox cboMau;
	private JButton btnCN;
	private JButton btnThoat;
	private JLabel lblThongBao;
	private JPanel pCapNhatSP;
	private JPanel pBoLocTimKiem;
	private DefaultComboBoxModel modelComboBox;

	/**
	 * Create the panel.
	 */
	public QuanLySanPham() {

		// ------------------------------------------------------------------------------------------------------------------------------------------
		// Chức năng quản lý sản phẩm

		setBounds(240, 0, 1100, 700);
		setLayout(null);
		setBackground(Color.WHITE);

		JScrollPane scrollPaneSanPham = new JScrollPane();
		scrollPaneSanPham.setBounds(15, 277, 1060, 394);
		add(scrollPaneSanPham);

		tableSanPham = new JTable();
		tableSanPham.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = tableSanPham.getSelectedRow();

				SanPham sp = sanphamDAO.laySanPhamTheoMa(tableSanPham.getValueAt(row, 1).toString());
				txtMaSP.setText(tableSanPham.getValueAt(row, 1).toString());
				txtTenSP.setText(tableSanPham.getValueAt(row, 2).toString());
				// cboHSX.setSelectedItem(tableSanPham.getValueAt(row, 3).toString());
				// cboLoai.setSelectedItem(tableSanPham.getValueAt(row, 4).toString());
				cboHSX.setSelectedItem(sp.getHangSanXuat().getTenHangSanXuat());
				cboLoai.setSelectedItem(sp.getLoaiSanPham().getTenLoai());
				cboSize.setSelectedItem(tableSanPham.getValueAt(row, 5).toString());
				cboMau.setSelectedItem(tableSanPham.getValueAt(row, 6).toString());
				txtSL.setText(tableSanPham.getValueAt(row, 7).toString());
				txtGia.setText(tableSanPham.getValueAt(row, 8).toString());

			}
		});
		tableSanPham.setFont(new Font("Tahoma", Font.PLAIN, 15));
		scrollPaneSanPham.setViewportView(tableSanPham);
		tableSanPham.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "STT", "M\u00E3 s\u1EA3n ph\u1EA9m", "T\u00EAn s\u1EA3n ph\u1EA9m",
						"H\u00E3ng s\u1EA3n xu\u1EA5t", "Lo\u1EA1i", "Size", "M\u00E0u", "S\u1ED1 l\u01B0\u1EE3ng",
						"Gi\u00E1" }));
		tableSanPham.getColumnModel().getColumn(0).setPreferredWidth(15);
		tableSanPham.getColumnModel().getColumn(5).setPreferredWidth(15);

		tableSanPham.setAutoCreateRowSorter(true);
		tableSanPham.setRowHeight(tableSanPham.getRowHeight() + 10);
		tableSanPham.setDefaultEditor(Object.class, null);

		model = (DefaultTableModel) tableSanPham.getModel();
		showTableSanPham();

		JPanel pBoLocTimKiem = new JPanel();
		pBoLocTimKiem.setBorder(new TitledBorder(null, "B\u1ED9 l\u1ECDc t\u00ECm ki\u1EBFm", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		pBoLocTimKiem.setBackground(Color.WHITE);
		pBoLocTimKiem.setBounds(279, 76, 796, 191);
		add(pBoLocTimKiem);
		pBoLocTimKiem.setLayout(null);

		// Combobox hãng sản xuất
		cboHangSanXuat = new JComboBox();
		cboHangSanXuat.setModel(new DefaultComboBoxModel(new String[] { "" }));
		cboHangSanXuat.setFont(new Font("Tahoma", Font.BOLD, 12));
		cboHangSanXuat.setBounds(140, 50, 160, 30);
		pBoLocTimKiem.add(cboHangSanXuat);

		JLabel lblHangSanXuat;
		lblHangSanXuat = new JLabel("Hãng sản xuất");
		lblHangSanXuat.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblHangSanXuat.setBounds(30, 50, 90, 30);
		pBoLocTimKiem.add(lblHangSanXuat);

		// Combobox loại sản phẩm
		cboLoaiSP = new JComboBox();
		cboLoaiSP.setModel(new DefaultComboBoxModel(new String[] { "" }));
		cboLoaiSP.setFont(new Font("Tahoma", Font.BOLD, 12));
		cboLoaiSP.setBounds(440, 50, 160, 30);
		pBoLocTimKiem.add(cboLoaiSP);

		showComboBox();

		JLabel lblLoaiSP;
		lblLoaiSP = new JLabel("Loại sản phẩm");
		lblLoaiSP.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblLoaiSP.setBounds(330, 50, 90, 30);
		pBoLocTimKiem.add(lblLoaiSP);

		JButton btnNewButton = new ButtonGradient();
		btnNewButton.setText("Tìm kiếm");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				timKiemSP();

			}
		});
		((ButtonGradient) btnNewButton).setColor2(Color.decode("#0099F7"));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton.setBounds(120, 110, 175, 50);
		pBoLocTimKiem.add(btnNewButton);

		JLabel lblNewLabel_13 = new JLabel("Quản lý sản phẩm");
		lblNewLabel_13.setFont(new Font("Tahoma", Font.PLAIN, 40));
		lblNewLabel_13.setBounds(20, 21, 358, 60);
		add(lblNewLabel_13);

		JButton btnXuatFileExcel = new ButtonGradient();
		btnXuatFileExcel.setText("Xuất Excel");
		btnXuatFileExcel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//xuatFileExcel();
				try {
					xuatDS_Excel();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		((ButtonGradient) btnXuatFileExcel).setColor2(Color.decode("#00AF00"));
		((ButtonGradient) btnXuatFileExcel).setColor1(Color.decode("#00AF00"));
		btnXuatFileExcel.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnXuatFileExcel.setBounds(920, 40, 150, 37);
		add(btnXuatFileExcel);

		JButton btnNewButton_1 = new ButtonGradient();
		btnNewButton_1.setText("Xóa bộ lọc");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cboHangSanXuat.setSelectedIndex(0);
				cboLoaiSP.setSelectedIndex(0);

				xoaHetDuLieuTrenTableModel();
				showTableSanPham();
			}
		});
		((ButtonGradient) btnNewButton_1).setColor2(Color.decode("#0099F7"));
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton_1.setIcon(new ImageIcon(GiaoDienChinh_ChuCuaHang.class.getResource("/img/cancel.png")));
		btnNewButton.setIcon(new ImageIcon(GiaoDienChinh_ChuCuaHang.class.getResource("/img/icons8_search_40px.png")));
		btnNewButton_1.setBounds(330, 110, 196, 50);
		pBoLocTimKiem.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("Cập nhật sản phẩm");
		btnNewButton_2.setBackground(Color.CYAN);
		btnNewButton_2.addActionListener(new ActionListener() {
			// private CapNhatSanPham capnhatSP;

			public void actionPerformed(ActionEvent e) {
				// capnhatSP = new CapNhatSanPham();
				// capnhatSP.setVisible(true);
				// capnhatSP.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				// capnhatSP.setLocationRelativeTo(null);
				// capnhatSP.setAlwaysOnTop(true);
				// capnhatSP.setResizable(false);

				// int row = tableSanPham.getSelectedRow();
				// String maSP = (String) tableSanPham.getValueAt(row, 1);
				// capnhatSP.showData(maSP);

				add(pCapNhatSP);
				pCapNhatSP.setVisible(true);
				pBoLocTimKiem.setVisible(false);

				int row = tableSanPham.getSelectedRow();

				SanPham sp = sanphamDAO.laySanPhamTheoMa(tableSanPham.getValueAt(row, 1).toString());
				txtMaSP.setText(tableSanPham.getValueAt(row, 1).toString());
				txtTenSP.setText(tableSanPham.getValueAt(row, 2).toString());
				// cboHSX.setSelectedItem(tableSanPham.getValueAt(row, 3).toString());
				// cboLoai.setSelectedItem(tableSanPham.getValueAt(row, 4).toString());
				cboHSX.setSelectedItem(sp.getHangSanXuat().getTenHangSanXuat());
				cboLoai.setSelectedItem(sp.getLoaiSanPham().getTenLoai());
				cboSize.setSelectedItem(tableSanPham.getValueAt(row, 5).toString());
				cboMau.setSelectedItem(tableSanPham.getValueAt(row, 6).toString());
				txtSL.setText(tableSanPham.getValueAt(row, 7).toString());
				txtGia.setText(tableSanPham.getValueAt(row, 8).toString());

				// lblTenHSX.setText(tableSanPham.getValueAt(row, 3).toString());
				// lblTenLoai.setText(sp.getLoaiSanPham().getMaLoaiSanPham());
				// lblTenHSX.setText(tableSanPham.getValueAt(row, 3).toString());
				// lblTenLoai.setText(tableSanPham.getValueAt(row, 4).toString());

			}
		});
		btnNewButton_2.setIcon(new ImageIcon(QuanLySanPham.class.getResource("/img/icons8_update_40px_2.png")));
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_2.setBounds(15, 140, 240, 60);
		add(btnNewButton_2);

		pCapNhatSP = new JPanel();
		pCapNhatSP.setBorder(new TitledBorder(null, "C\u1EADp nh\u1EADt kh\u00E1ch h\u00E0ng", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		pCapNhatSP.setBackground(Color.WHITE);
		pCapNhatSP.setBounds(280, 77, 800, 190);
		// add(pCapNhatSP);
		pCapNhatSP.setLayout(null);

		JLabel lblNewLabel = new JLabel("Mã sản phẩm");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 20, 101, 25);
		pCapNhatSP.add(lblNewLabel);

		txtMaSP = new JTextField();
		txtMaSP.setBounds(121, 20, 170, 25);
		pCapNhatSP.add(txtMaSP);
		txtMaSP.setColumns(10);
		txtMaSP.setEditable(false);

		JLabel lblTnSnPhm = new JLabel("Tên sản phẩm");
		lblTnSnPhm.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTnSnPhm.setBounds(10, 60, 101, 25);
		pCapNhatSP.add(lblTnSnPhm);

		txtTenSP = new JTextField();
		txtTenSP.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtTenSP.setColumns(10);
		txtTenSP.setBounds(121, 60, 170, 25);
		pCapNhatSP.add(txtTenSP);

		JLabel lblSCmnd = new JLabel("Loại sản phẩm");
		lblSCmnd.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSCmnd.setBounds(10, 100, 130, 25);
		pCapNhatSP.add(lblSCmnd);

		cboLoai = new JComboBox();
		cboLoai.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nhap = cboLoai.getSelectedItem().toString();
				LoaiSanPham lsp = loaispDAO.layLoaiSanPhamTheoMa(nhap);

				// lblTenLoai.setText(lsp.getTenLoai());
			}
		});
		cboLoai.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cboLoai.setEditable(true);
		cboLoai.setBounds(122, 100, 170, 25);
		pCapNhatSP.add(cboLoai);

		JLabel lblNewLabel_1_1 = new JLabel("Hãng sản xuất");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_1.setBounds(10, 140, 113, 25);
		pCapNhatSP.add(lblNewLabel_1_1);

		cboHSX = new JComboBox();
		cboHSX.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nhap = cboHSX.getSelectedItem().toString();
				System.out.println(nhap);
				HangSanXuat hsx = hsxDAO.layHangSanXuatTheoMa(nhap);
				// lblTenHSX.setText(hsx.getTenHangSanXuat());

			}
		});
		cboHSX.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cboHSX.setEditable(true);
		cboHSX.setBounds(122, 140, 170, 25);
		pCapNhatSP.add(cboHSX);

		JLabel lblNewLabel_1_1_1 = new JLabel("Size:");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_1_1.setBounds(330, 20, 75, 25);
		pCapNhatSP.add(lblNewLabel_1_1_1);

		cboSize = new JComboBox();
		cboSize.setModel(new DefaultComboBoxModel(new String[] { "S", "M", "L", "XL", "XXL", "XXXL" }));
		cboSize.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cboSize.setBounds(450, 20, 170, 25);
		pCapNhatSP.add(cboSize);

		JLabel lblNewLabel_1_1_1_1 = new JLabel("Màu sắc");
		lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_1_1_1.setBounds(330, 60, 75, 25);
		pCapNhatSP.add(lblNewLabel_1_1_1_1);

		cboMau = new JComboBox();
		cboMau.setModel(new DefaultComboBoxModel(new String[] { "Đen ", "Trắng ", "Đỏ", "Xanh Dương", "Xanh Lá", "Cam",
				"Vàng", "Tràm ", "Tím", "Nâu" }));
		cboMau.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cboMau.setEditable(true);
		cboMau.setBounds(450, 60, 170, 25);
		pCapNhatSP.add(cboMau);

		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("Số lượng");
		lblNewLabel_1_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_1_1_1_1.setBounds(330, 100, 130, 25);
		pCapNhatSP.add(lblNewLabel_1_1_1_1_1);

		txtSL = new JTextField();
		txtSL.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtSL.setColumns(10);
		txtSL.setBounds(450, 100, 170, 25);
		pCapNhatSP.add(txtSL);

		JLabel lblNewLabel_1_1_1_1_1_1 = new JLabel("Giá sản phẩm");
		lblNewLabel_1_1_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_1_1_1_1_1.setBounds(330, 140, 130, 25);
		pCapNhatSP.add(lblNewLabel_1_1_1_1_1_1);

		txtGia = new JTextField();
		txtGia.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtGia.setColumns(10);
		txtGia.setBounds(450, 145, 170, 25);
		pCapNhatSP.add(txtGia);

		btnCN = new JButton("Cập nhật");
		btnCN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				kiemtraUpdate();

			}
		});
		btnCN.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCN.setBounds(660, 60, 123, 25);
		pCapNhatSP.add(btnCN);

		btnThoat = new JButton("Thoát");
		btnThoat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblThongBao.setText("");

				add(pBoLocTimKiem);
				pBoLocTimKiem.setVisible(true);
				pCapNhatSP.setVisible(false);
			}
		});
		btnThoat.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnThoat.setBounds(660, 100, 123, 25);
		pCapNhatSP.add(btnThoat);

		lblThongBao = new JLabel("");
		lblThongBao.setForeground(Color.RED);
		lblThongBao.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblThongBao.setBounds(373, 34, 500, 33);
		add(lblThongBao);

		showComboBoxCapNhat();
	}

	public void xuatFileExcel() {
		try {
			String sql = "SELECT SanPham.maSanPham, SanPham.giaSanPham, SanPham.kichThuoc, SanPham.soLuong, SanPham.tenSanPham, SanPham.maLoaiSanPham, SanPham.maHangSanXuat, SanPham.mauSac, LoaiSanPham.tenLoai, \r\n"
					+ "                  HangSanXuat.tenHangSanXuat\r\n" + "FROM     SanPham INNER JOIN\r\n"
					+ "                  LoaiSanPham ON SanPham.maLoaiSanPham = LoaiSanPham.maLoaiSanPham INNER JOIN\r\n"
					+ "                  HangSanXuat ON SanPham.maHangSanXuat = HangSanXuat.maHangSanXuat";

			JasperDesign jasperDesign = JRXmlLoader.load("src/main/java/Report/DanhSachSanPham.jrxml");
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
			XSSFSheet spreadsheet = workbook.createSheet("Sản phẩm");
			XSSFRow row = null;
            Cell cell = null;
            row = spreadsheet.createRow((short) 1);
            row.setHeight((short) 500);
            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue("DANH SÁCH SẢN PHẨM");
            
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
            cell.setCellValue("Mã sản phẩm");
            cell.setCellStyle(cellStyle);
            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue("Tên sản phẩm");
            cell.setCellStyle(cellStyle);
            cell = row.createCell(3, CellType.STRING);
            cell.setCellValue("Hãng sản xuất");
            cell.setCellStyle(cellStyle);
            cell = row.createCell(4, CellType.STRING);
            cell.setCellValue("Loại sản phẩm");
            cell.setCellStyle(cellStyle);
            cell = row.createCell(5, CellType.STRING);
            cell.setCellValue("Kích thước");
            cell.setCellStyle(cellStyle);
            cell = row.createCell(6, CellType.STRING);
            cell.setCellValue("Màu");
            cell.setCellStyle(cellStyle);
            cell = row.createCell(7, CellType.STRING);
            cell.setCellValue("Số lượng");
            cell.setCellStyle(cellStyle);
            cell = row.createCell(8, CellType.STRING);
            cell.setCellValue("Giá");
            cell.setCellStyle(cellStyle);
            
            List<SanPham> listSP = sanphamDAO.layTatCaSanPham();
            int i =0;
            int tongSL = 0;
            for (SanPham sp : listSP) {
            	  row = spreadsheet.createRow((short) 4 + i);
                  row.setHeight((short) 400);
                  row.createCell(0).setCellValue(i + 1);
                  row.createCell(1).setCellValue(sp.getMaSanPham());
                  row.createCell(2).setCellValue(sp.getTenSanPham());
                  row.createCell(3).setCellValue(sp.getHangSanXuat().getTenHangSanXuat());
                  row.createCell(4).setCellValue(sp.getLoaiSanPham().getTenLoai());
                  row.createCell(5).setCellValue(sp.getKichThuoc());
                  row.createCell(6).setCellValue(sp.getMauSac());
                  row.createCell(7).setCellValue(sp.getSoLuong());
                  row.createCell(8).setCellValue(sp.getGiaSanPham());
                  i++;
                  tongSL += sp.getSoLuong();
                   
			}
            for (int columnIndex = 0; columnIndex < 9; columnIndex++) {
            	spreadsheet.autoSizeColumn(columnIndex);
            }
            row = spreadsheet.createRow((short) i + 6);
            row.setHeight((short) 500);
            row.createCell(0).setCellValue("Tổng cộng");
            row.createCell(1).setCellValue(i);
            row.createCell(7).setCellValue(tongSL);
            
			FileOutputStream out = new FileOutputStream(new File(saveFile.toString()));
			workbook.write(out);
			workbook.close();
			out.close();
			openFile(saveFile.toString());

		} else {
			Date date = new Date();
			
			saveFile = new File("DanhSachSanPhamTheoNgay" +date.toString()+ ".xlsx");
		}

		//XSSFWorkbook workbook = new XSSFWorkbook();
		//XSSFSheet spreadsheet = workbook.createSheet("Sản phẩm");

	}
	
	public void timKiemSP() {
		if (cboHangSanXuat.getSelectedIndex() == 0 && cboLoaiSP.getSelectedIndex() == 0)
			JOptionPane.showMessageDialog(this, "Vui lòng chọn dữ liệu tìm kiếm!");
		if (cboHangSanXuat.getSelectedIndex() != 0 && cboLoaiSP.getSelectedIndex() != 0)
			timkiemTheoLoai_HSX();
		else if (cboLoaiSP.getSelectedIndex() == 0 && cboHangSanXuat.getSelectedIndex() != 0)
			timkiemTheoHSX();
		else if (cboHangSanXuat.getSelectedIndex() == 0 && cboLoaiSP.getSelectedIndex() != 0)
			timkiemTheoLoaiSP();
		
	}

	public void xoaDataComboBox() {
		DefaultComboBoxModel modelHSX = (DefaultComboBoxModel) cboHSX.getModel();
		modelHSX.removeAllElements();

		DefaultComboBoxModel modelLoaiSP = (DefaultComboBoxModel) cboLoai.getModel();
		modelLoaiSP.removeAllElements();
	}

	public void showComboBoxCapNhat() {
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

	private void xoaHetDuLieuTrenTableModel() {
		DefaultTableModel dm = (DefaultTableModel) tableSanPham.getModel();
		dm.getDataVector().removeAllElements();
	}

	public void showTableSanPham() {
		int num = 1;
		List<SanPham> listSP = sanphamDAO.layTatCaSanPham();
		for (SanPham sp : listSP) {
			model.addRow(new Object[] { num, sp.getMaSanPham(), sp.getTenSanPham(),
					sp.getHangSanXuat().getTenHangSanXuat(), sp.getLoaiSanPham().getTenLoai(), sp.getKichThuoc(),
					sp.getMauSac(), sp.getSoLuong(), sp.getGiaSanPham() });
			num++;
		}
	}

	public void timkiemTheoHSX() {
		int stt = cboHangSanXuat.getSelectedIndex();
		System.out.println(stt);
		String maHSX;
		if (stt < 10)
			maHSX = "HSX00" + String.valueOf(stt);
		else
			maHSX = "HSX0" + String.valueOf(stt);

		// String hang = cboHangSanXuat.getSelectedItem().toString();
		int num = 1;
		List<SanPham> listSP = sanphamDAO.layDanhSachSanPhamTheoHSX(maHSX);
		xoaHetDuLieuTrenTableModel();
		for (SanPham sp : listSP) {
			model.addRow(new Object[] { num, sp.getMaSanPham(), sp.getTenSanPham(),
					sp.getHangSanXuat().getTenHangSanXuat(), sp.getLoaiSanPham().getTenLoai(), sp.getKichThuoc(),
					sp.getMauSac(), sp.getSoLuong(), sp.getGiaSanPham() });
			num++;
		}
	}

	public void timkiemTheoLoaiSP() {
		int stt1 = cboLoaiSP.getSelectedIndex();
		String maLoai;
		if (stt1 < 10)
			maLoai = "L00" + String.valueOf(stt1);
		else
			maLoai = "L0" + String.valueOf(stt1);
		// String hang = cboHangSanXuat.getSelectedItem().toString();
		int num = 1;
		List<SanPham> listSP = sanphamDAO.layDanhSachSanPhamTheoLoai(maLoai);
		xoaHetDuLieuTrenTableModel();
		for (SanPham sp : listSP) {
			model.addRow(new Object[] { num, sp.getMaSanPham(), sp.getTenSanPham(),
					sp.getHangSanXuat().getTenHangSanXuat(), sp.getLoaiSanPham().getTenLoai(), sp.getKichThuoc(),
					sp.getMauSac(), sp.getSoLuong(), sp.getGiaSanPham() });
			num++;
		}
	}

	public void timkiemTheoLoai_HSX() {
		int stt = cboHangSanXuat.getSelectedIndex();
		System.out.println(stt);
		String maHSX;
		if (stt < 10)
			maHSX = "HSX00" + String.valueOf(stt);
		else
			maHSX = "HSX0" + String.valueOf(stt);
		int stt1 = cboLoaiSP.getSelectedIndex();
		String maLoai;
		if (stt1 < 10)
			maLoai = "L00" + String.valueOf(stt1);
		else
			maLoai = "L0" + String.valueOf(stt1);
		String hang = cboHangSanXuat.getSelectedItem().toString();
		int num = 1;
		List<SanPham> listSP = sanphamDAO.layDanhSachSanPhamTheoHSX_Loai(maHSX, maLoai);
		xoaHetDuLieuTrenTableModel();
		for (SanPham sp : listSP) {
			model.addRow(new Object[] { num, sp.getMaSanPham(), sp.getTenSanPham(),
					sp.getHangSanXuat().getTenHangSanXuat(), sp.getLoaiSanPham().getTenLoai(), sp.getKichThuoc(),
					sp.getMauSac(), sp.getSoLuong(), sp.getGiaSanPham() });
			num++;
		}
	}

	public void showComboBox() {
		List<HangSanXuat> listHSX = hsxDAO.layTatCaHangSanXuat();
		DefaultComboBoxModel modelHSX = (DefaultComboBoxModel) cboHangSanXuat.getModel();
		for (HangSanXuat hsx : listHSX) {
			modelHSX.addElement(hsx.getTenHangSanXuat());
		}

		List<LoaiSanPham> listLoaiSP = loaispDAO.layTatCaLoaiSanPham();
		DefaultComboBoxModel modelLoaiSP = (DefaultComboBoxModel) cboLoaiSP.getModel();
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
	
	private boolean validDataSP() {
		String ma = txtMaSP.getText();
		String ten = txtTenSP.getText();
		String tenHSX = cboHSX.getSelectedItem().toString();
		String tenLoai = cboLoai.getSelectedItem().toString();
		String size = cboSize.getSelectedItem().toString();
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
		if (txtSL.getText().equals("")) {
			// lblThongBaoThemSP.setText("Error: Vui lòng nhập số lượng");
			JOptionPane.showMessageDialog(this, "Lỗi: Vui lòng nhập số lượng");
			txtSL.requestFocus();
			txtSL.selectAll();
			return false;
		}
		if (!txtSL.getText().matches("[0-9]+")) {
			// lblThongBaoThemSP.setText("Error: Vui lòng nhập số lớn hơn 0");
			JOptionPane.showMessageDialog(this, "Lỗi: Số lượng có định dạng là chữ số!");
			txtSL.requestFocus();
			txtSL.selectAll();
			return false;
		}
		int sl = Integer.parseInt(txtSL.getText());
		if (sl <= 0) {
			// lblThongBaoThemSP.setText("Error: Vui lòng nhập số lớn hơn 0");
			JOptionPane.showMessageDialog(this, "Lỗi: Vui lòng nhập số lớn hơn 0");
			txtSL.requestFocus();
			txtSL.selectAll();
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

	public void kiemtraUpdate() {
		if(validDataSP()) {
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

		String maSP = txtMaSP.getText();
		String tenSP = txtTenSP.getText();
		// String loai = String.valueOf(lblTenLoai);
		LoaiSanPham loaiSP = new LoaiSanPham(maLoai);
		String hsx = String.valueOf(maHSX);
		HangSanXuat hang = new HangSanXuat(maHSX);
		String size = String.valueOf(cboSize.getSelectedItem());
		String mau = String.valueOf(cboMau.getSelectedItem());
		int sl = Integer.parseInt(txtSL.getText());
		double gia = Double.parseDouble(txtGia.getText());

		SanPham sp = new SanPham(maSP, tenSP, gia, size, mau, sl, hang, loaiSP);

		boolean rs = sanphamDAO.capNhatSanPham(sp);

		if (sanphamDAO.capNhatSanPham(sp)) {
			// lblThongBao.setText("Cập nhật sản phẩm thành công");
			JOptionPane.showMessageDialog(this, "Cập nhật sản phẩm thành công");
			xoaHetDuLieuTrenTableModel();

			showTableSanPham();
		} else
			// lblThongBao.setText("Cập nhật sản phẩm không thành công");
			JOptionPane.showMessageDialog(this, "Cập nhật sản phẩm không thành công");
		}
	}

	public void resetTableSP() {
		xoaHetDuLieuTrenTableModel();
		showTableSanPham();
	}

}
