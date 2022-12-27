package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Locale;
import java.util.Map.Entry;

import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

import org.hibernate.SessionFactory;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import com.toedter.calendar.JDateChooser;
import dao.ChiTietBanHangDAO;
import dao.ChiTietNhapKhoDAO;
import dao.ChuCuaHangDAO;
import dao.GenerateKeyDAO;
import dao.HoaDonBanHangDAO;
import dao.HoaDonNhapKhoDAO;
import dao.KhachHangDAO;
import dao.NhanVienDAO;
import dao.SanPhamDAO;
import dao.TaiKhoanDAO;
import dao_imp.ChiTietBanHangImp;
import dao_imp.ChiTietNhapKhoImp;
import dao_imp.ChuCuaHangImp;
import dao_imp.GenerateKeyImp;
import dao_imp.HoaDonBanHangImp;
import dao_imp.HoaDonNhapKhoImp;
import dao_imp.KhachHangImp;
import dao_imp.MySessionFactory;
import dao_imp.NhanVienImp;
import dao_imp.SanPhamImp;
import dao_imp.TaiKhoanImp;
import entity.ChiTietHoaDonBanHang;
import entity.ChiTietHoaDonNhapKho;
import entity.HangSanXuat;
import entity.HoaDonBanHang;
import entity.HoaDonNhapKho;
import entity.KhachHang;
import entity.LoaiSanPham;
import entity.NhanVienBanHang;
import entity.SanPham;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import com.toedter.calendar.JMonthChooser;
import com.toedter.calendar.JYearChooser;
import com.toedter.components.JSpinField;

public class QuanLyThongKe extends JPanel {
	private JFreeChart pieChart;
	private ChartPanel chartPanel;

	SessionFactory sessionFactory = new MySessionFactory().getSessionFactory();
	GenerateKeyDAO generateKeyDAO = new GenerateKeyImp(sessionFactory);
	ChiTietBanHangDAO chiTietBanHangDAO = new ChiTietBanHangImp(sessionFactory);
	HoaDonBanHangDAO hoaDonBanHangDAO = new HoaDonBanHangImp(sessionFactory);
	HoaDonNhapKhoDAO hoadonnkDAO = new HoaDonNhapKhoImp(sessionFactory);
	ChiTietNhapKhoDAO cthdnkDAO = new ChiTietNhapKhoImp(sessionFactory);
	KhachHangDAO khachHangDAO = new KhachHangImp(sessionFactory);
	NhanVienDAO nhanVienDAO = new NhanVienImp(sessionFactory);
	SanPhamDAO sanphamDAO = new SanPhamImp(sessionFactory);
	TaiKhoanDAO taikhoanDAO = new TaiKhoanImp(sessionFactory);
	ChuCuaHangDAO chucuahangDAO = new ChuCuaHangImp(sessionFactory);
	SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	List<HoaDonBanHang> listHDBH = hoaDonBanHangDAO.layTatCaHoaDonBanHang();
	List<KhachHang> listKH = khachHangDAO.layTatCaKhachHang();
	List<NhanVienBanHang> listNV = nhanVienDAO.layTatCaNhanVien();
	private JLabel lblSoLuongSP;
	private JLabel lblSoLuongHDNK;
	private JPanel pXemBieuDo;
	private JTable table;
	private DefaultTableModel tableModel;
	private Locale localeVN = new Locale("vi", "VN");
	private NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	// private LocalDate fromDay = LocalDate.of(2021, 11, 13);
	private LocalDate fromDay = LocalDate.now().minusDays(7);
	private LocalDate endDay = LocalDate.now();
	private ChartPanel chartPanelDoanhThu, chartPanelSLHD;
	private JDateChooser dateEnd, dateStart;
	private JTable tableThongKeHoaDon;
	private JDesktopPane desktopPane;
	private BarChart gui;
	private JPanel pXemBieuDoDoangThu;
	private BarChartSL gui_1;
	private JPanel pTheoNgay;
	private JPanel pTheoThang;
	private JPanel pNgay;
	private JPanel pChua;
	private JPanel pThang;
	private JMonthChooser monthChooser;
	private JYearChooser yearChooser;
	DateTimeFormatter monthFormatter = DateTimeFormatter.ofPattern("MM");
	private JComboBox cboThang;
	private JPanel pXemBieuDoSanPham;
	private JPanel pChuaSP;
	private JYearChooser yearChooserSP;
	private JPanel pNgay_SP;
	private JPanel pThang_SP;
	private JComboBox cboThang_SP;
	private JDateChooser dateStart_SP;
	private JDateChooser dateEnd_SP;
	private JTable tableThongKeSanPham;
	private JPanel pTheoThang_SP;
	private BarChartSL gui_SP;
	private JComboBox cboSP;
	private JLabel lblThongBao_Thang_SP;
	private JLabel lblTenBieuDo;
	private JLabel lblSoLuongDoanhThu;

	public static void main(String[] args) {
		QuanLyThongKe gui = new QuanLyThongKe();
		gui.setVisible(true);
	}

	/**
	 * Create the panel.
	 */
	public QuanLyThongKe() {

		setBackground(Color.WHITE);
		setBounds(240, 0, 1100, 700);

		setLayout(null);

		JLabel lblQunLThng = new JLabel("Quản lý thống kê");
		lblQunLThng.setFont(new Font("Tahoma", Font.PLAIN, 40));
		lblQunLThng.setBounds(10, 20, 358, 60);
		add(lblQunLThng);

		JPanel panel = new JPanel();
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				pXemBieuDo.add(pXemBieuDoDoangThu);
				pXemBieuDoDoangThu.setVisible(true);
				pXemBieuDoSanPham.setVisible(false);
			}
		});
		panel.setBackground(new Color(72, 209, 204));
		panel.setBounds(602, 10, 230, 94);
		add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Thống kê doanh thu");
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				pXemBieuDo.add(pXemBieuDoDoangThu);
				pXemBieuDoDoangThu.setVisible(true);
				pXemBieuDoSanPham.setVisible(false);
			}
		});
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(10, 10, 210, 37);
		panel.add(lblNewLabel);

		lblSoLuongDoanhThu = new JLabel("");
		lblSoLuongDoanhThu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				pXemBieuDo.add(pXemBieuDoDoangThu);
				pXemBieuDoDoangThu.setVisible(true);
				pXemBieuDoSanPham.setVisible(false);
			}
		});
		lblSoLuongDoanhThu.setFont(new Font("Tahoma", Font.BOLD, 32));
		lblSoLuongDoanhThu.setBounds(0, 34, 220, 50);
		panel.add(lblSoLuongDoanhThu);

		lblSoLuongDoanhThu.setText(thongkeDoanhThu());
		System.out.println(thongkeDoanhThu());

		JPanel panel_1_2 = new JPanel();
		panel_1_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				pXemBieuDo.add(pXemBieuDoSanPham);
				pXemBieuDoSanPham.setVisible(true);
				pXemBieuDoDoangThu.setVisible(false);
			}
		});
		panel_1_2.setLayout(null);
		panel_1_2.setBackground(new Color(238, 130, 238));
		panel_1_2.setBounds(835, 10, 230, 94);
		add(panel_1_2);

		JLabel lblSLngHa_1 = new JLabel("Thống kê sản phẩm bán ra");
		lblSLngHa_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				pXemBieuDo.add(pXemBieuDoSanPham);
				pXemBieuDoSanPham.setVisible(true);
				pXemBieuDoDoangThu.setVisible(false);
			}
		});
		lblSLngHa_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblSLngHa_1.setBounds(10, 10, 280, 37);
		panel_1_2.add(lblSLngHa_1);

		lblSoLuongSP = new JLabel(String.valueOf(demSoLuongSanPhamBanRa()));
		lblSoLuongSP.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				pXemBieuDo.add(pXemBieuDoSanPham);
				pXemBieuDoSanPham.setVisible(true);
				pXemBieuDoDoangThu.setVisible(false);
			}
		});
		lblSoLuongSP.setBackground(new Color(238, 130, 238));
		lblSoLuongSP.setFont(new Font("Tahoma", Font.PLAIN, 32));
		lblSoLuongSP.setBounds(79, 34, 151, 50);
		panel_1_2.add(lblSoLuongSP);

		//lblSoLuongHDNK.setText("");

		pXemBieuDo = new JPanel();
		// pXemBieuDo = new JDesktopPane();
		pXemBieuDo.setBounds(10, 120, 1055, 530);
		add(pXemBieuDo);
		pXemBieuDo.setLayout(null);

		// ----------------------------------------------------------------------------------------------------
		// Xem biểu đồ doanh thu
		pXemBieuDoDoangThu = new JPanel();
		pXemBieuDoDoangThu.setLayout(null);
		pXemBieuDoDoangThu.setBounds(0, 0, 1055, 530);
		pXemBieuDo.add(pXemBieuDoDoangThu);

		/*
		 * desktopPane = new JDesktopPane(); desktopPane.setBounds(10, 350, 1055, 305);
		 * add(desktopPane); desktopPane.setLayout(null);
		 */

		pChua = new JPanel();
		pChua.setBounds(10, 50, 490, 110);
		pXemBieuDoDoangThu.add(pChua);
		pChua.setLayout(null);

		pNgay = new JPanel();
		pNgay.setBounds(0, 0, 487, 110);
		pChua.add(pNgay);
		pNgay.setLayout(null);

		pThang = new JPanel();
		pThang.setBounds(0, 0, 487, 110);
		// pChua.add(pThang);
		pThang.setLayout(null);

		/*
		 * monthChooser = new JMonthChooser(); String monthString =
		 * monthFormatter.format(Month.of(monthChooser.getMonth() + 1)); monthChooser.
		 * monthChooser.setBounds(90, 30, 96, 25); pThang.add(monthChooser);
		 */

		yearChooser = new JYearChooser();
		yearChooser.setBounds(90, 70, 96, 25);
		pThang.add(yearChooser);

		JLabel lblNewLabel_4 = new JLabel("Tháng");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_4.setBounds(10, 30, 70, 25);
		pThang.add(lblNewLabel_4);

		JLabel lblNewLabel_4_1 = new JLabel("Năm");
		lblNewLabel_4_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_4_1.setBounds(10, 70, 70, 25);
		pThang.add(lblNewLabel_4_1);

		cboThang = new JComboBox();
		cboThang.setModel(new DefaultComboBoxModel(
				new String[] { "ALL", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));
		cboThang.setFont(new Font("Tahoma", Font.PLAIN, 12));
		cboThang.setBounds(90, 30, 96, 25);
		pThang.add(cboThang);

		JLabel lblNewLabel_1 = new JLabel("Từ ngày :");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_1.setBounds(-12, 30, 78, 25);
		pNgay.add(lblNewLabel_1);

		dateStart = new JDateChooser();
		dateStart.setBounds(98, 30, 180, 25);
		dateStart.setDateFormatString("dd/MM/yyyy");
		dateStart.setDate(Date.valueOf(fromDay));
		pNgay.add(dateStart);

		JLabel lblnNgy = new JLabel("Đến ngày :");
		lblnNgy.setHorizontalAlignment(SwingConstants.TRAILING);
		lblnNgy.setBounds(-36, 70, 102, 25);
		pNgay.add(lblnNgy);

		dateEnd = new JDateChooser();
		dateEnd.setBounds(98, 70, 180, 24);
		dateEnd.setDateFormatString("dd/MM/yyyy");
		dateEnd.setDate(Date.valueOf(endDay));
		pNgay.add(dateEnd);

		gui = new BarChart();
		gui.setBounds(10, 170, 1023, 292);
		pXemBieuDoDoangThu.add(gui);

		gui_1 = new BarChartSL();
		gui_1.setBounds(10, 170, 1023, 279);
		// pXemBieuDoDoangThu.add(gui_1);

		JButton btnXemthongKe = new JButton("Xem theo doanh thu");
		btnXemthongKe.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				java.util.Date dateUtilStart = dateStart.getDate();
				java.util.Date dateSQLStart = new Date(dateUtilStart.getTime());
				java.util.Date dateUtilEnd = dateEnd.getDate();
				java.util.Date dateSQLEnd = new Date(dateUtilEnd.getTime());
				long betweenDate = dateSQLEnd.getTime() - dateSQLStart.getTime();
				float days = (betweenDate / (1000 * 60 * 60 * 24));
				// LocalDateTime date0 = LocalDateTime.ofInstant(dateSQLStart.toInstant(),
				// ZoneId.systemDefault());
				// LocalDateTime date1 = LocalDateTime.ofInstant(dateSQLEnd.toInstant(),
				// ZoneId.systemDefault());
				// long numOfDaysBetween = ChronoUnit.DAYS.between(date0, date1);
				// System.out.println(String.valueOf(date0));
				// System.out.println(String.valueOf(date1));
				// System.out.println(numOfDaysBetween);
				if (checkNgay(dateSQLStart, dateSQLEnd) && days <= 7) {
					try {
						loadHD();
					} catch (ClassNotFoundException | SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					// try {
					// chartPanelDoanhThu.setChart(getChartDoanhThu(dateSQLStart, dateSQLEnd));
					// chartPanelSLHD.setChart(getChartSLHD(dateSQLStart, dateSQLEnd));
					// } catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					// e1.printStackTrace();
					// }
					gui_1.setVisible(false);
					gui.setVisible(false);
					gui = new BarChart();
					gui.setBounds(10, 170, 1023, 292);
					pXemBieuDoDoangThu.add(gui);
					gui.setVisible(true);
					NumberFormat formatter = new DecimalFormat("#,##0 đ");
					// jPaneChartDoanhThu.setVisible(false);
					double dt = 0;
					@SuppressWarnings("deprecation")
					int row = tableThongKeHoaDon.getRowCount();
					String s = tableThongKeHoaDon.getValueAt(1, 4).toString();
					for (int i = 0; i < row; i++) {
						if (tableThongKeHoaDon.getValueAt(i, 4).toString().equalsIgnoreCase(s)) {
							try {
								dt += currencyVN.parse(tableThongKeHoaDon.getValueAt(i, 5).toString()).doubleValue();
							} catch (ParseException e2) {
								// TODO Auto-generated catch block
								e2.printStackTrace();
							}

						} else {

							try {
								gui.addHistogramColumn(s, currencyVN.format(dt), Color.CYAN);
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							try {
								dt = currencyVN.parse(tableThongKeHoaDon.getValueAt(i, 5).toString()).doubleValue();
							} catch (ParseException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							s = tableThongKeHoaDon.getValueAt(i, 4).toString();
						}

					}
					try {
						gui.addHistogramColumn(s, currencyVN.format(dt), Color.CYAN);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					/*
					 * int dayStart = dateStart.getDate().getDate(); int dayEnd =
					 * dateEnd.getDate().getDate(); List<HoaDonBanHang> listHDStart =
					 * hoaDonBanHangDAO.layDanhSachHoaDonBanHangTheoNgay(dateStart.getDate().getYear
					 * () + 1900, dateStart.getDate().getMonth() + 1,
					 * dateStart.getDate().getDate()); List<HoaDonBanHang> listHDEnd =
					 * hoaDonBanHangDAO.layDanhSachHoaDonBanHangTheoNgay(dateStart.getDate().getYear
					 * () + 1900, dateStart.getDate().getMonth() + 1,
					 * dateStart.getDate().getDate()); for (HoaDonBanHang hd : listHDBH) {
					 * gui.addHistogramColumn(hd.getNgayLapHoaDon().toString(), 500, Color.CYAN); }
					 */

					try {
						gui.layoutHistogram();
					} catch (ParseException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}

				} else {
					// LocalDate day = LocalDate.

					// showMessage("Hãy chọn ngày lại đi!!!", dateEnd);
					if (dateSQLEnd.compareTo(dateSQLStart) < 0 && dateSQLStart.compareTo(dateSQLEnd) > 0)
						// if(dateSQLEnd.getDate() - dateSQLStart.getDate() < 0)
						showMessage("Ngày bắt đầu không được lớn hơn ngày kết thúc", dateEnd);
					// else if (dateSQLEnd.getDate() - dateSQLStart.getDate() > 9) {

					else if (days > 7) {
						showMessage("Ngày kết thúc không được quá 7 ngày",
								dateEnd);
						java.util.Date date;
						try {
							date = new SimpleDateFormat("dd/MM/yyyy").parse("18/12/2021");
							dateEnd.setDate(date);
						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

					}
				}
			}
		});
		btnXemthongKe.setBounds(288, 30, 189, 30);
		pNgay.add(btnXemthongKe);

		JButton btnInThongKe = new JButton("Xem theo số lượng");
		btnInThongKe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				java.util.Date dateUtilStart = dateStart.getDate();
				java.util.Date dateSQLStart = new Date(dateUtilStart.getTime());
				java.util.Date dateUtilEnd = dateEnd.getDate();
				java.util.Date dateSQLEnd = new Date(dateUtilEnd.getTime());
				long betweenDate = dateSQLEnd.getTime() - dateSQLStart.getTime();
				float days = (betweenDate / (1000 * 60 * 60 * 24));
				if (checkNgay(dateSQLStart, dateSQLEnd) && days <= 7) {
					try {
						loadHD();
					} catch (ClassNotFoundException | SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					int row = tableThongKeHoaDon.getRowCount();
					// Dem so hoa don
					gui.setVisible(false);
					gui_1.setVisible(false);
					gui_1 = new BarChartSL();
					gui_1.setBounds(10, 170, 1023, 292);
					pXemBieuDoDoangThu.add(gui_1);
					gui_1.setVisible(true);
					gui_1.setHistogramHeight(80);

					String s1 = tableThongKeHoaDon.getValueAt(1, 4).toString();
					int dem = 0;
					for (int i = 0; i < row; i++) {
						if (tableThongKeHoaDon.getValueAt(i, 4).toString().equalsIgnoreCase(s1)) {

							dem++;

						} else {

							try {
								gui_1.addHistogramColumn(s1, dem, Color.ORANGE);
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}

							dem = 1;

							s1 = tableThongKeHoaDon.getValueAt(i, 4).toString();
						}

					}
					try {
						gui_1.addHistogramColumn(s1, dem, Color.ORANGE);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					/*
					 * int dayStart = dateStart.getDate().getDate(); int dayEnd =
					 * dateEnd.getDate().getDate(); List<HoaDonBanHang> listHDStart =
					 * hoaDonBanHangDAO.layDanhSachHoaDonBanHangTheoNgay(dateStart.getDate().getYear
					 * () + 1900, dateStart.getDate().getMonth() + 1,
					 * dateStart.getDate().getDate()); List<HoaDonBanHang> listHDEnd =
					 * hoaDonBanHangDAO.layDanhSachHoaDonBanHangTheoNgay(dateStart.getDate().getYear
					 * () + 1900, dateStart.getDate().getMonth() + 1,
					 * dateStart.getDate().getDate()); for (HoaDonBanHang hd : listHDBH) {
					 * gui.addHistogramColumn(hd.getNgayLapHoaDon().toString(), 500, Color.CYAN); }
					 */

					try {
						gui_1.layoutHistogram();
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					// LocalDate day = LocalDate.

					// showMessage("Hãy chọn ngày lại đi!!!", dateEnd);
					if (dateSQLEnd.compareTo(dateSQLStart) < 0 && dateSQLStart.compareTo(dateSQLEnd) > 0)
						// if(dateSQLEnd.getDate() - dateSQLStart.getDate() < 0)
						showMessage("Ngày bắt đầu không được lớn hơn ngày kết thúc", dateEnd);
					// else if (dateSQLEnd.getDate() - dateSQLStart.getDate() > 9) {

					else if (days > 7) {
						showMessage("Ngày kết thúc không được quá 7 ngày",
								dateEnd);
						java.util.Date date;
						try {
							date = new SimpleDateFormat("dd/MM/yyyy").parse("18/12/2021");
							dateEnd.setDate(date);
						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

					}
				}
			}
		});
		btnInThongKe.setBounds(288, 70, 189, 30);
		pNgay.add(btnInThongKe);

		// jPaneChartDoanhThu = new JPanel();
		// jPaneChartDoanhThu.setBounds(526, 60, 519, 305);
		// pXemBieuDoDoangThu.add(jPaneChartDoanhThu);
		// jPaneChartDoanhThu.setLayout(new BorderLayout(0, 0));

		JButton btnNewButton = new JButton("Xem thống kê doanh thu");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblTenBieuDo.setBounds(280, 486, 625, 13);
				lblTenBieuDo.setText("Biểu đồ thống kê doanh thu (Đơn vị tính theo doanh thu: 1.000 đ)");
				int thang = cboThang.getSelectedIndex();
				int nam = yearChooser.getYear();
				tableModel = (DefaultTableModel) tableThongKeHoaDon.getModel();
				if (thang != 0) {
					int num = 1;
					List<HoaDonBanHang> listHD = hoaDonBanHangDAO.layDanhSachHoaDonBanHangTheoThang(nam, thang);
					xoaHetDuLieuTrenTableModel();
					for (HoaDonBanHang hd : listHD) {
						tableModel.addRow(new Object[] { num, hd.getMaHoaDonBanHang(),
								hd.getKhachHang().getMaKhachHang(), hd.getNhanVienBanHang().getMaNhanVien(),
								sdf.format(hd.getNgayLapHoaDon()), currencyVN.format(hd.getTongTien()) });
						num++;
					}

					gui_1.setVisible(false);
					gui.setVisible(false);
					gui = new BarChart();
					gui.setBounds(10, 170, 1023, 292);
					pXemBieuDoDoangThu.add(gui);
					gui.setVisible(true);
					NumberFormat formatter = new DecimalFormat("#,##0 đ");
					// jPaneChartDoanhThu.setVisible(false);
					double dt = 0;
					YearMonth yearMonthObject = YearMonth.of(nam, thang);
					int daysInMonth = yearMonthObject.lengthOfMonth();

					for (int i = 1; i < daysInMonth + 1; i++) {
						List<HoaDonBanHang> list = hoaDonBanHangDAO.layDanhSachHoaDonBanHangTheoNgay(nam, thang, i);
						for (HoaDonBanHang hd : list) {
							dt += hd.getTongTien();
						}
						try {
							if (dt != 0)
								gui.addHistogramColumn(String.valueOf(i), currencyVN.format(dt / 1000), Color.CYAN);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						dt = 0;
					}
					try {
						gui.layoutHistogram();
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				} else if (cboThang.getSelectedIndex() == 0) {
					lblTenBieuDo.setText("Biểu đồ thống kê doanh thu (Đơn vị tính theo doanh thu: 1.000 đ)");
					int n = 1;
					double tienTheoThang = 0;
					List<HoaDonBanHang> listHD = hoaDonBanHangDAO.layDanhSachHoaDonBanHangTheoNam(nam);
					xoaHetDuLieuTrenTableModel();
					for (HoaDonBanHang hd : listHD) {
						tableModel.addRow(new Object[] { n, hd.getMaHoaDonBanHang(), hd.getKhachHang().getMaKhachHang(),
								hd.getNhanVienBanHang().getMaNhanVien(), sdf.format(hd.getNgayLapHoaDon()),
								currencyVN.format(hd.getTongTien()) });
						n++;
					}

					gui_1.setVisible(false);
					gui.setVisible(false);
					gui = new BarChart();
					gui.setBounds(10, 170, 1023, 292);
					pXemBieuDoDoangThu.add(gui);
					gui.setVisible(true);
					NumberFormat formatter = new DecimalFormat("#,##0 đ");
					for (int i = 1; i < 13; i++) {
						List<HoaDonBanHang> list = hoaDonBanHangDAO.layDanhSachHoaDonBanHangTheoThang(nam, i);
						for (HoaDonBanHang hd : list) {
							tienTheoThang += hd.getTongTien();
						}
						try {
							gui.addHistogramColumn(String.valueOf(i), currencyVN.format(tienTheoThang / 1000),
									Color.CYAN);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						tienTheoThang = 0;
					}
					try {
						gui.layoutHistogram();
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}

			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnNewButton.setBounds(234, 30, 228, 25);
		pThang.add(btnNewButton);

		JButton btnXemThngK = new JButton("Xem thống kê theo số lượng");
		btnXemThngK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblTenBieuDo.setBounds(380, 486, 625, 13);
				lblTenBieuDo.setText("Biểu đồ thống kê theo số lượng");
				int thang = cboThang.getSelectedIndex();
				int nam = yearChooser.getYear();
				tableModel = (DefaultTableModel) tableThongKeHoaDon.getModel();
				if (thang != 0) {
					int num = 1;
					List<HoaDonBanHang> listHD = hoaDonBanHangDAO.layDanhSachHoaDonBanHangTheoThang(nam, thang);
					xoaHetDuLieuTrenTableModel();
					for (HoaDonBanHang hd : listHD) {
						tableModel.addRow(new Object[] { num, hd.getMaHoaDonBanHang(),
								hd.getKhachHang().getMaKhachHang(), hd.getNhanVienBanHang().getMaNhanVien(),
								sdf.format(hd.getNgayLapHoaDon()), currencyVN.format(hd.getTongTien()) });
						num++;
					}

					double dt = 0;
					YearMonth yearMonthObject = YearMonth.of(nam, thang);
					int daysInMonth = yearMonthObject.lengthOfMonth();

					gui.setVisible(false);
					gui_1.setVisible(false);
					gui_1 = new BarChartSL();
					gui_1.setBounds(10, 170, 1023, 292);
					pXemBieuDoDoangThu.add(gui_1);
					gui_1.setVisible(true);
					gui_1.setHistogramHeight(80);
					int dem = 0;
					for (int i = 1; i < daysInMonth + 1; i++) {
						List<HoaDonBanHang> list = hoaDonBanHangDAO.layDanhSachHoaDonBanHangTheoNgay(nam, thang, i);
						for (HoaDonBanHang hd : list) {
							dem++;
						}
						try {
							gui_1.addHistogramColumn(String.valueOf(i), dem, Color.CYAN);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						dem = 0;
					}
					try {
						gui_1.layoutHistogram();
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				} else if (cboThang.getSelectedIndex() == 0) {
					lblTenBieuDo.setBounds(380, 486, 625, 13);
					lblTenBieuDo.setText("Biểu đồ thống kê theo số lượng");
					int n = 1;
					double tienTheoThang = 0;
					List<HoaDonBanHang> listHD = hoaDonBanHangDAO.layDanhSachHoaDonBanHangTheoNam(nam);
					xoaHetDuLieuTrenTableModel();
					for (HoaDonBanHang hd : listHD) {
						tableModel.addRow(new Object[] { n, hd.getMaHoaDonBanHang(), hd.getKhachHang().getMaKhachHang(),
								hd.getNhanVienBanHang().getMaNhanVien(), sdf.format(hd.getNgayLapHoaDon()),
								currencyVN.format(hd.getTongTien()) });
						n++;
					}

					gui.setVisible(false);
					gui_1.setVisible(false);
					gui_1 = new BarChartSL();
					gui_1.setBounds(10, 170, 1023, 292);
					pXemBieuDoDoangThu.add(gui_1);
					gui_1.setVisible(true);
					gui_1.setHistogramHeight(80);
					// NumberFormat formatter = new DecimalFormat("#,##0 đ");
					int dem = 0;
					for (int i = 1; i < 13; i++) {
						List<HoaDonBanHang> list = hoaDonBanHangDAO.layDanhSachHoaDonBanHangTheoThang(nam, i);
						if (list != null) {
							for (HoaDonBanHang hd : list) {
								dem++;
							}
						} else
							dem = 0;
						System.out.println(dem);
						try {
							gui_1.addHistogramColumn(String.valueOf(i), dem, Color.ORANGE);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						dem = 0;

					}
					try {
						gui_1.layoutHistogram();
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btnXemThngK.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnXemThngK.setBounds(234, 70, 228, 25);
		pThang.add(btnXemThngK);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(510, 50, 523, 110);
		pXemBieuDoDoangThu.add(scrollPane_1);

		tableThongKeHoaDon = new JTable();
		scrollPane_1.setViewportView(tableThongKeHoaDon);
		tableThongKeHoaDon.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "STT", "Mã hóa đơn", "Khách hàng", "Nhân viên", "Ngày lập", "Tổng tiền" }));

		lblTenBieuDo = new JLabel("");
		lblTenBieuDo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTenBieuDo.setBounds(380, 486, 625, 13);
		pXemBieuDoDoangThu.add(lblTenBieuDo);
		tableThongKeHoaDon.setDefaultEditor(Object.class, null);

		pTheoNgay = new JPanel();
		pTheoNgay.setBounds(10, 10, 100, 30);
		pXemBieuDoDoangThu.add(pTheoNgay);
		pTheoNgay.setBackground(new Color(143, 188, 143));
		pTheoNgay.setLayout(null);

		JLabel lblNewLabel_3 = new JLabel("    Theo ngày");
		lblNewLabel_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				pChua.add(pNgay);
				pNgay.setVisible(true);
				pThang.setVisible(false);
				dateEnd.setDate(Date.valueOf(endDay));
				dateStart.setDate(Date.valueOf(fromDay));
			}
		});
		lblNewLabel_3.setBounds(0, 0, 100, 30);
		pTheoNgay.add(lblNewLabel_3);
		lblNewLabel_3.setBackground(new Color(143, 188, 143));
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 13));

		pTheoThang = new JPanel();
		pTheoThang.setBounds(120, 10, 100, 30);
		pXemBieuDoDoangThu.add(pTheoThang);
		pTheoThang.setBackground(new Color(143, 188, 143));
		pTheoThang.setLayout(null);

		JLabel lblNewLabel_3_1 = new JLabel("   Theo tháng");
		lblNewLabel_3_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				pChua.add(pThang);
				pThang.setVisible(true);
				pNgay.setVisible(false);
				dateEnd.setDate(null);
				dateStart.setDate(null);
			}
		});
		lblNewLabel_3_1.setBounds(0, 0, 100, 30);
		pTheoThang.add(lblNewLabel_3_1);
		lblNewLabel_3_1.setBackground(new Color(143, 188, 143));
		lblNewLabel_3_1.setFont(new Font("Tahoma", Font.PLAIN, 13));

		// ----------------------------------------------------------------------------------------------------
		// Xem biểu đòo thống kê sản phẩm
		pXemBieuDoSanPham = new JPanel();
		pXemBieuDoSanPham.setLayout(null);
		pXemBieuDoSanPham.setBounds(0, 0, 1055, 530);
		// pXemBieuDo.add(pXemBieuDoSanPham);

		pChuaSP = new JPanel();
		pChuaSP.setBounds(10, 50, 490, 110);
		pXemBieuDoSanPham.add(pChuaSP);
		pChuaSP.setLayout(null);

		// pNgay_SP = new JPanel();
		// pNgay_SP.setBounds(0, 0, 487, 110);
		// pChuaSP.add(pNgay_SP);
		// pNgay_SP.setLayout(null);

		pThang_SP = new JPanel();
		pThang_SP.setBounds(0, 0, 487, 110);
		pChuaSP.add(pThang_SP);
		pThang_SP.setLayout(null);

		/*
		 * monthChooser = new JMonthChooser(); String monthString =
		 * monthFormatter.format(Month.of(monthChooser.getMonth() + 1)); monthChooser.
		 * monthChooser.setBounds(90, 30, 96, 25); pThang.add(monthChooser);
		 */

		yearChooserSP = new JYearChooser();
		yearChooserSP.setBounds(70, 70, 96, 25);
		pThang_SP.add(yearChooserSP);

		JLabel lblThang_SP = new JLabel("Tháng");
		lblThang_SP.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblThang_SP.setBounds(10, 30, 60, 25);
		pThang_SP.add(lblThang_SP);

		JLabel lblNam_SP = new JLabel("Năm");
		lblNam_SP.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNam_SP.setBounds(10, 70, 60, 25);
		pThang_SP.add(lblNam_SP);

		cboThang_SP = new JComboBox();
		cboThang_SP.setModel(new DefaultComboBoxModel(
				new String[] { "ALL", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));
		cboThang_SP.setFont(new Font("Tahoma", Font.PLAIN, 12));
		cboThang_SP.setBounds(70, 30, 96, 25);
		pThang_SP.add(cboThang_SP);

		/*
		 * JLabel lblDayFrom_SP = new JLabel("Từ ngày :");
		 * lblDayFrom_SP.setHorizontalAlignment(SwingConstants.TRAILING);
		 * lblDayFrom_SP.setBounds(-12, 30, 78, 25); pNgay_SP.add(lblDayFrom_SP);
		 */

		/*
		 * dateStart_SP = new JDateChooser(); dateStart_SP.setBounds(98, 30, 180, 25);
		 * dateStart_SP.setDateFormatString("dd/MM/yyyy");
		 * dateStart_SP.setDate(Date.valueOf(fromDay)); pNgay_SP.add(dateStart_SP);
		 */

		/*
		 * JLabel lblDayEnd_SP = new JLabel("Đến ngày :");
		 * lblDayEnd_SP.setHorizontalAlignment(SwingConstants.TRAILING);
		 * lblDayEnd_SP.setBounds(-36, 70, 102, 25); pNgay_SP.add(lblDayEnd_SP);
		 * 
		 * dateEnd_SP = new JDateChooser(); dateEnd_SP.setBounds(98, 70, 180, 24);
		 * dateEnd_SP.setDateFormatString("dd/MM/yyyy");
		 * dateEnd_SP.setDate(Date.valueOf(endDay)); pNgay_SP.add(dateEnd_SP);
		 */

		gui_SP = new BarChartSL();
		gui_SP.setBounds(10, 170, 1023, 279);
		pXemBieuDoSanPham.add(gui_SP);

		/*
		 * gui_1 = new BarChartSL(); gui_1.setBounds(526, 288, 524, 194);
		 * pXemBieuDoSanPham.add(gui_1);
		 */

		JButton btnXemTKTheoThang_SP = new JButton("Xem thống kê");
		btnXemTKTheoThang_SP.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String ma = layThongTinSP();
				int thang = cboThang_SP.getSelectedIndex();
				int nam = yearChooserSP.getYear();
				tableModel = (DefaultTableModel) tableThongKeSanPham.getModel();
				if (thang != 0) {
					int num = 1;
					List<HoaDonBanHang> listHD = hoaDonBanHangDAO.layDanhSachHoaDonBanHangTheoThang_SP(nam, thang, ma);
					xoaHetDuLieuTrenTableModel_SP();
					if (listHD == null)
						// showMessage("Sản phẩm không được bán trong thời gian này",dateEnd);
						lblThongBao_Thang_SP.setText("Sản phẩm " + ma + " không được bán trong thời gian này");
					else {
						for (HoaDonBanHang hd : listHD) {
							lblThongBao_Thang_SP.setText("");
							tableModel.addRow(new Object[] { num, hd.getMaHoaDonBanHang(),
									hd.getKhachHang().getTenKhachHang(), hd.getNhanVienBanHang().getTenNhanVien(),
									sdf.format(hd.getNgayLapHoaDon()), currencyVN.format(hd.getTongTien()) });
							num++;
						}

						gui_SP.setVisible(false);
						gui_SP = new BarChartSL();
						gui_SP.setBounds(10, 170, 1023, 279);
						pXemBieuDoSanPham.add(gui_SP);
						gui_SP.setVisible(true);
						// NumberFormat formatter = new DecimalFormat("#,##0 đ");
						double dt = 0;
						YearMonth yearMonthObject = YearMonth.of(nam, thang);
						int daysInMonth = yearMonthObject.lengthOfMonth();

						int dem = 0;
						for (int i = 1; i < daysInMonth + 1; i++) {
							List<HoaDonBanHang> list = hoaDonBanHangDAO.layDanhSachHoaDonBanHangTheoNgay_SP(nam, thang,
									i, ma);
							for (HoaDonBanHang hd : list) {
								List<ChiTietHoaDonBanHang> listCTBH = chiTietBanHangDAO
										.layDanhSachCTHoaDonBanHangTheoMaHDBH(hd.getMaHoaDonBanHang());
								for (ChiTietHoaDonBanHang ct : listCTBH) {
									SanPham sp = sanphamDAO.laySanPhamTheoMa(ma);
									if (ct.getSanPham().getMaSanPham().equalsIgnoreCase(ma))
										dem += ct.getSoLuong();
									try {
										// gui_SP.addHistogramColumn(String.valueOf(listSP.getMaSanPham()), dem,
										// Color.CYAN);
									} catch (Exception e1) {

										e1.printStackTrace();
									}
								}

								// dem++;
							}
							try {
								gui_SP.addHistogramColumn(String.valueOf(i), dem, Color.CYAN);
							} catch (Exception e1) {

								e1.printStackTrace();
							}
							dem = 0;
						}
						try {
							gui_SP.layoutHistogram();
						} catch (ParseException e1) {

							e1.printStackTrace();
						}
					}

				} else if (cboThang_SP.getSelectedIndex() == 0) {
					int n = 1;
					double tienTheoThang = 0;
					List<HoaDonBanHang> listHD = hoaDonBanHangDAO.layDanhSachHoaDonBanHangTheoNam_SP(nam, ma);
					xoaHetDuLieuTrenTableModel_SP();
					for (HoaDonBanHang hd : listHD) {
						tableModel.addRow(new Object[] { n, hd.getMaHoaDonBanHang(),
								hd.getKhachHang().getTenKhachHang(), hd.getNhanVienBanHang().getTenNhanVien(),
								sdf.format(hd.getNgayLapHoaDon()), currencyVN.format(hd.getTongTien()) });
						n++;
					}

					gui_SP.setVisible(false);
					gui_SP = new BarChartSL();
					gui_SP.setBounds(10, 170, 1023, 279);
					pXemBieuDoSanPham.add(gui_SP);
					gui_SP.setVisible(true);

					// NumberFormat formatter = new DecimalFormat("#,##0 đ");
					int dem = 0;
					for (int i = 1; i < 13; i++) {
						List<HoaDonBanHang> list = hoaDonBanHangDAO.layDanhSachHoaDonBanHangTheoThang_SP(nam, i, ma);
						for (HoaDonBanHang hd : list) {
							List<ChiTietHoaDonBanHang> listCTBH = chiTietBanHangDAO
									.layDanhSachCTHoaDonBanHangTheoMaHDBH(hd.getMaHoaDonBanHang());
							for (ChiTietHoaDonBanHang ct : listCTBH) {
								SanPham sp = sanphamDAO.laySanPhamTheoMa(ma);
								if (ct.getSanPham().getMaSanPham().equalsIgnoreCase(ma))
									dem += ct.getSoLuong();
								try {
									// gui_SP.addHistogramColumn(String.valueOf(listSP.getMaSanPham()), dem,
									// Color.CYAN);
								} catch (Exception e1) {

									e1.printStackTrace();
								}
							}

							// dem++;
						}
						try {
							gui_SP.addHistogramColumn(String.valueOf(i), dem, Color.CYAN);
						} catch (Exception e1) {

							e1.printStackTrace();
						}
						dem = 0;
					}
					try {
						gui_SP.layoutHistogram();
					} catch (ParseException e1) {

						e1.printStackTrace();
					}
				}
			}
		});
		btnXemTKTheoThang_SP.setBounds(264, 70, 137, 25);
		pThang_SP.add(btnXemTKTheoThang_SP);

		// jPaneChartDoanhThu = new JPanel();
		// jPaneChartDoanhThu.setBounds(526, 60, 519, 305);
		// pXemBieuDoDoangThu.add(jPaneChartDoanhThu);
		// jPaneChartDoanhThu.setLayout(new BorderLayout(0, 0));

		cboSP = new JComboBox();
		cboSP.setFont(new Font("Tahoma", Font.PLAIN, 12));
		cboSP.setBounds(176, 30, 301, 25);
		pThang_SP.add(cboSP);

		lblThongBao_Thang_SP = new JLabel("");
		lblThongBao_Thang_SP.setForeground(Color.RED);
		lblThongBao_Thang_SP.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblThongBao_Thang_SP.setBounds(10, 7, 467, 13);
		pThang_SP.add(lblThongBao_Thang_SP);

		loadComboboxSanPham();

		// ...
		JScrollPane scrollPane_SP = new JScrollPane();
		scrollPane_SP.setBounds(510, 50, 523, 110);
		pXemBieuDoSanPham.add(scrollPane_SP);

		tableThongKeSanPham = new JTable();
		scrollPane_SP.setViewportView(tableThongKeSanPham);
		tableThongKeSanPham.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "STT", "Mã hóa đơn", "Khách hàng", "Nhân viên", "Ngày lập", "Tổng tiền" }));

		JLabel lblTenBieuDo_TKDT_SP = new JLabel("Biểu đồ thống kê sản phẩm");
		lblTenBieuDo_TKDT_SP.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTenBieuDo_TKDT_SP.setBounds(346, 477, 331, 13);
		pXemBieuDoSanPham.add(lblTenBieuDo_TKDT_SP);
		tableThongKeSanPham.setDefaultEditor(Object.class, null);

		pTheoThang_SP = new JPanel();
		pTheoThang_SP.setBounds(10, 10, 100, 30);
		pXemBieuDoSanPham.add(pTheoThang_SP);
		pTheoThang_SP.setBackground(new Color(143, 188, 143));
		pTheoThang_SP.setLayout(null);

		JLabel lblTheoThang_SP = new JLabel("   Theo tháng");
		lblTheoThang_SP.setBounds(0, 0, 100, 30);
		pTheoThang_SP.add(lblTheoThang_SP);
		lblTheoThang_SP.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				pChuaSP.add(pThang_SP);
				pThang_SP.setVisible(true);
				// pNgay_SP.setVisible(false);
				dateEnd_SP.setDate(null);
				dateStart_SP.setDate(null);
			}
		});
		lblTheoThang_SP.setBackground(new Color(143, 188, 143));
		lblTheoThang_SP.setFont(new Font("Tahoma", Font.PLAIN, 13));

	}

	private static JFreeChart createChart(PieDataset dataset, String thang, String nam) {
		String tittleChart = "Sản phẩm bán được trong tháng " + thang + " năm " + nam;
		JFreeChart chart = ChartFactory.createPieChart(tittleChart, dataset, true, true, true);
		return chart;
	}

	private static PieDataset createDataset() {
		DefaultPieDataset dataset = new DefaultPieDataset();
		// dataset.setValue("Nhóm 0 - 14", new Double(25.0));
		// dataset.setValue("Nhóm 15 - 59", new Double(66.0));
		// dataset.setValue("Nhóm trên 60", new Double(9.0));

		return dataset;
	}

	/*
	 * void khoiTaoDoanhThu() throws ClassNotFoundException, SQLException {
	 * chartPanelDoanhThu = new ChartPanel( getChartDoanhThu(new
	 * Date(dateStart.getDate().getTime()), new Date(dateEnd.getDate().getTime())));
	 * jPaneChartDoanhThu.add(chartPanelDoanhThu, BorderLayout.CENTER);
	 * chartPanelDoanhThu.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5)); }
	 * 
	 * private JFreeChart getChartDoanhThu(Date from, Date end) throws
	 * ClassNotFoundException, SQLException { return
	 * ChartFactory.createLineChart("Doanh thu", "", "", taoDatasetDoanhThu(from,
	 * end), PlotOrientation.VERTICAL, false, false, false); }
	 * 
	 * private DefaultCategoryDataset taoDatasetDoanhThu(Date from, Date end) throws
	 * ClassNotFoundException, SQLException { DefaultCategoryDataset dataset = new
	 * DefaultCategoryDataset(); for (Entry<String, Double> entry :
	 * hoaDonBanHangDAO.thongkeDoanhThu(from, end).entrySet()) {
	 * dataset.addValue(entry.getValue(), "Doanh thu", entry.getKey()); } return
	 * dataset; }
	 * 
	 * void khoiTaoSLHD() throws ClassNotFoundException, SQLException {
	 * chartPanelSLHD = new ChartPanel( getChartSLHD(new
	 * Date(dateStart.getDate().getTime()), new Date(dateEnd.getDate().getTime())));
	 * jPandeChartSLHD.add(chartPanelSLHD, BorderLayout.CENTER);
	 * chartPanelSLHD.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5)); }
	 * 
	 * private JFreeChart getChartSLHD(Date from, Date end) throws
	 * ClassNotFoundException, SQLException { return
	 * ChartFactory.createLineChart("Số lượng hóa đơn", "", "", taoDatasetSLHD(from,
	 * end), PlotOrientation.VERTICAL, false, false, false); }
	 * 
	 * private DefaultCategoryDataset taoDatasetSLHD(Date from, Date end) throws
	 * ClassNotFoundException, SQLException { DefaultCategoryDataset dataset = new
	 * DefaultCategoryDataset(); for (Entry<String, Double> entry :
	 * hoaDonBanHangDAO.thongkeSLDH(from, end).entrySet()) {
	 * dataset.addValue(entry.getValue(), "số lượng", entry.getKey()); } return
	 * dataset; }
	 */

	public void loadComboboxSanPham() {
		List<SanPham> listSP = sanphamDAO.layTatCaSanPham();
		DefaultComboBoxModel modelSP = (DefaultComboBoxModel) cboSP.getModel();
		for (SanPham sp : listSP) {
			modelSP.addElement(sp.getMaSanPham() + " - " + sp.getTenSanPham());
		}
	}

	public void xoaDataComboBox() {
		DefaultComboBoxModel modelSP = (DefaultComboBoxModel) cboSP.getModel();
		modelSP.removeAllElements();
	}

	private void xoaHetDuLieuTrenTableModel() {
		DefaultTableModel dm = (DefaultTableModel) tableThongKeHoaDon.getModel();
		dm.getDataVector().removeAllElements();
	}

	private void xoaHetDuLieuTrenTableModel_SP() {
		DefaultTableModel dm = (DefaultTableModel) tableThongKeSanPham.getModel();
		dm.getDataVector().removeAllElements();
	}

	void loadHD() throws ClassNotFoundException, SQLException {
		java.util.Date dateUtilStart = dateStart.getDate();
		Date dateSQLStart = new Date(dateUtilStart.getTime());
		java.util.Date dateUtilEnd = dateEnd.getDate();
		int tblRow = tableThongKeHoaDon.getRowCount();
		Date dateSQLEnd = new Date(dateUtilEnd.getTime());
		dateSQLEnd.setDate(dateSQLEnd.getDate() + 1);
		tableModel = (DefaultTableModel) tableThongKeHoaDon.getModel();
		int num = 1;
		/*
		 * for (int i = tblRow - 1; i >= 0; i--) { tableModel.removeRow(i); }
		 */
		xoaHetDuLieuTrenTableModel();
		List<HoaDonBanHang> listHD = hoaDonBanHangDAO.layDanhSachHoaDonTheoCumNgay(dateSQLStart, dateSQLEnd);
		for (HoaDonBanHang hd : listHD) {
			tableModel.addRow(new Object[] { num, hd.getMaHoaDonBanHang(), hd.getKhachHang().getMaKhachHang(),
					hd.getNhanVienBanHang().getMaNhanVien(), sdf.format(hd.getNgayLapHoaDon()),
					currencyVN.format(hd.getTongTien()) });
			num++;
		}
	}

	public String layThongTinSP() {
		int stt = cboSP.getSelectedIndex() + 1;
		System.out.println(stt);
		String maSP;
		if (stt < 10)
			maSP = "SP00" + String.valueOf(stt);
		else
			maSP = "SP0" + String.valueOf(stt);
		return maSP;
	}

	void loadHD_SP() throws ClassNotFoundException, SQLException {
		String ma = layThongTinSP();
		java.util.Date dateUtilStart = dateStart_SP.getDate();
		Date dateSQLStart = new Date(dateUtilStart.getTime());
		java.util.Date dateUtilEnd = dateEnd_SP.getDate();
		int tblRow = tableThongKeSanPham.getRowCount();
		Date dateSQLEnd = new Date(dateUtilEnd.getTime());
		dateSQLEnd.setDate(dateSQLEnd.getDate() + 1);
		tableModel = (DefaultTableModel) tableThongKeSanPham.getModel();
		int num = 1;
		/*
		 * for (int i = tblRow - 1; i >= 0; i--) { tableModel.removeRow(i); }
		 */
		xoaHetDuLieuTrenTableModel_SP();
		List<HoaDonBanHang> listHD = hoaDonBanHangDAO.layDanhSachHoaDonTheoCumNgay_SP(dateSQLStart, dateSQLEnd, ma);
		for (HoaDonBanHang hd : listHD) {
			tableModel.addRow(new Object[] { num, hd.getMaHoaDonBanHang(), hd.getKhachHang().getMaKhachHang(),
					hd.getNhanVienBanHang().getMaNhanVien(), sdf.format(hd.getNgayLapHoaDon()),
					currencyVN.format(hd.getTongTien()) });
			num++;
		}
	}

	boolean checkNgay(java.util.Date from, java.util.Date end) {
		// (end.getDate() - from.getDate())
		// LocalDateTime date0 = LocalDateTime.ofInstant(from.toInstant(),
		// ZoneId.systemDefault());
		// LocalDateTime date1 = LocalDateTime.ofInstant(end.toInstant(),
		// ZoneId.systemDefault());
		// long numOfDaysBetween = ChronoUnit.DAYS.between(date0, date1);
		if (end.compareTo(from) > 0 && from.compareTo(end) < 0 && from.compareTo(Date.valueOf(endDay)) < 0)
			return true;
		return false;
	}

	private int demSoLuongSanPham() {
		List<SanPham> listSP = sanphamDAO.layTatCaSanPham();
		int dem = 0;
		for (SanPham sp : listSP) {
			dem++;
		}
		return dem;
	}

	private int demSoLuongSanPhamBanRa() {
		List<SanPham> listSP = sanphamDAO.layTatCaSanPham();

		int dem = 0;
		for (SanPham sp : listSP) {
			List<ChiTietHoaDonBanHang> listCTBH = chiTietBanHangDAO
					.layDanhSachCTHoaDonBanHangTheoMaSP(sp.getMaSanPham());
			for (ChiTietHoaDonBanHang cthdbh : listCTBH) {
				dem += cthdbh.getSoLuong();
			}
		}
		return dem;
	}

	private int demSoLuongSanPhamNhap() {
		List<SanPham> listSP = sanphamDAO.layTatCaSanPham();

		int dem = 0;
		for (SanPham sp : listSP) {
			List<ChiTietHoaDonNhapKho> listCTNK = cthdnkDAO.layDanhSachCTHoaDonNhapKhoTheoMaSP(sp.getMaSanPham());
			for (ChiTietHoaDonNhapKho cthdnk : listCTNK) {
				dem += cthdnk.getSoLuong();
			}
		}
		return dem;
	}

	@SuppressWarnings("deprecation")
	private int demSoLuongSanPhamNhapTheoNgay(int thang, int nam) {
		List<SanPham> listSP = sanphamDAO.layTatCaSanPham();

		int dem = 0;
		for (SanPham sp : listSP) {
			List<ChiTietHoaDonNhapKho> listCTNK = cthdnkDAO.layDanhSachCTHoaDonNhapKhoTheoMaSP(sp.getMaSanPham());
			for (ChiTietHoaDonNhapKho cthdnk : listCTNK) {
				if (cthdnk.getHoaDonNhapKho().getNgayNhapKho().getMonth() == thang - 1)
					dem += cthdnk.getSoLuong();
				else
					continue;
			}
		}
		return dem;
	}

	@SuppressWarnings("deprecation")
	private int demSoLuongSanPhamBanRaTheoNgay(int thang, int nam) {
		List<SanPham> listSP = sanphamDAO.layTatCaSanPham();

		int dem = 0;
		for (SanPham sp : listSP) {
			List<ChiTietHoaDonBanHang> listCTBH = chiTietBanHangDAO
					.layDanhSachCTHoaDonBanHangTheoMaSP(sp.getMaSanPham());
			for (ChiTietHoaDonBanHang cthdbh : listCTBH) {
				if (cthdbh.getHoaDonBanHang().getNgayLapHoaDon().getMonth() == thang - 1)
					dem += cthdbh.getSoLuong();
				else
					continue;
			}
		}
		return dem;
	}

	private int demSoLuongHoaDonBH() {
		List<HoaDonBanHang> listHDBH = hoaDonBanHangDAO.layTatCaHoaDonBanHang();
		int dem = 0;
		for (HoaDonBanHang hoaDonBanHang : listHDBH) {
			dem++;
		}
		return dem;
	}

	private int demSoLuongHoaDonBHTheoThang(int thang, int nam) {
		List<HoaDonBanHang> listHDBH = hoaDonBanHangDAO.layTatCaHoaDonBanHang();
		int dem = 0;
		for (HoaDonBanHang hoaDonBanHang : listHDBH) {
			if (hoaDonBanHang.getNgayLapHoaDon().getMonth() == thang - 1)
				dem++;
			else
				continue;
		}
		return dem;
	}

	private int demSoLuongHoaDonNK() {
		List<HoaDonNhapKho> listHDNK = hoadonnkDAO.layTatCaHoaDonNhapKho();
		int dem = 0;
		for (HoaDonNhapKho donNhapKho : listHDNK) {
			dem++;
		}
		return dem;
	}

	private int demSoLuongHoaDonNKTheoThang(int thang, int nam) {
		List<HoaDonNhapKho> listHDNK = hoadonnkDAO.layTatCaHoaDonNhapKho();
		int dem = 0;
		for (HoaDonNhapKho donNhapKho : listHDNK) {
			if (donNhapKho.getNgayNhapKho().getMonth() == thang - 1)
				dem++;
			else
				continue;
		}
		return dem;
	}

	private int demSoLuongKhachHang() {
		List<KhachHang> listKH = khachHangDAO.layTatCaKhachHang();
		int dem = 0;
		for (KhachHang khachHang : listKH) {
			dem++;
		}
		return dem;
	}

	private int demSoLuongKhachHangTheoThang(int thang, int nam) {
		List<KhachHang> listKH = khachHangDAO.layTatCaKhachHang();
		int dem = 0;
		for (KhachHang khachHang : listKH) {
			dem++;
		}
		return dem;
	}

	private void showMessage(String message, JDateChooser txt) {
		// TODO Auto-generated method stub
		txt.requestFocus();
		JOptionPane.showMessageDialog(this, message);
	}

	public String thongkeDoanhThu() {
		String s = "";
		double t = 0;
		List<HoaDonBanHang> listHD = hoaDonBanHangDAO.layTatCaHoaDonBanHang();
		for (HoaDonBanHang hd : listHD) {
			t += hd.getTongTien();
		}
		s = currencyVN.format(t);
		return s;
	}
	
	public void resetTrang() {
		lblSoLuongSP.setText(String.valueOf(demSoLuongSanPhamBanRa()));
		lblSoLuongDoanhThu.setText(thongkeDoanhThu());
	}
}
