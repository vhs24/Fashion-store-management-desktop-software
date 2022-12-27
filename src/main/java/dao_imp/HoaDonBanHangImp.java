package dao_imp;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.TreeMap;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import Connect.ConnectDB;
import dao.HoaDonBanHangDAO;
import entity.ChiTietHoaDonBanHang;
import entity.HoaDonBanHang;
import entity.SanPham;

public class HoaDonBanHangImp implements HoaDonBanHangDAO {
	private SessionFactory sessionFactory;

	public HoaDonBanHangImp(SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}

	@Override
	public boolean themHoaDonBanHang(HoaDonBanHang hoaDonBanHang) {
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		try {
			session.save(hoaDonBanHang);
			tr.commit();
			return true;
		} catch (Exception e) {
			tr.rollback();
		}
		session.close();
		return false;
	}

	@Override
	public boolean capNhatHoaDonBanHang(HoaDonBanHang hoaDonBanHang) {
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		try {
			session.update(hoaDonBanHang);
			tr.commit();
			return true;
		} catch (Exception e) {
			tr.rollback();
		}
		session.close();
		return false;
	}

	@Override
	public List<HoaDonBanHang> layTatCaHoaDonBanHang() {
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		String query = "Select * from HoaDonBanHang hd";
		try {
			List<HoaDonBanHang> danhSachHoaDonBanHang = session.createNativeQuery(query, HoaDonBanHang.class)
					.getResultList();
			tr.commit();
			return danhSachHoaDonBanHang;
		} catch (Exception e) {
			tr.rollback();
		}
		session.close();
		return null;
	}

	@Override
	public HoaDonBanHang layHoaDonTheoMa(String ma) {
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		try {
			HoaDonBanHang hoaDonBanHang = session.find(HoaDonBanHang.class, ma);
			tr.commit();
			return hoaDonBanHang;
		} catch (Exception e) {
			tr.rollback();
		}
		session.close();
		return null;
	}

	@Override
	public List<HoaDonBanHang> layDanhSachHoaDonBanHangTheoNgay(int year, int month, int day) {
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		String query = "Select * from HoaDonBanHang hd where YEAR(ngayLapHoaDon) = " + year
				+ " AND MONTH(ngayLapHoaDon) = " + month + " AND DAY(ngayLapHoaDon) = " + day + "";
		try {
			List<HoaDonBanHang> danhSachHoaDonBanHang = session.createNativeQuery(query, HoaDonBanHang.class)
					.getResultList();
			tr.commit();
			return danhSachHoaDonBanHang;
		} catch (Exception e) {
			tr.rollback();
		}
		session.close();
		return null;
	}
	
	@Override
	public List<HoaDonBanHang> layDanhSachHoaDonBanHangTheoThang(int year, int month) {
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		String query = "Select * from HoaDonBanHang hd where YEAR(ngayLapHoaDon) = " + year
				+ " AND MONTH(ngayLapHoaDon) = " + month + "";
		try {
			List<HoaDonBanHang> danhSachHoaDonBanHang = session.createNativeQuery(query, HoaDonBanHang.class)
					.getResultList();
			tr.commit();
			return danhSachHoaDonBanHang;
		} catch (Exception e) {
			tr.rollback();
		}
		session.close();
		return null;
	}
	
	@Override
	public List<HoaDonBanHang> layDanhSachHoaDonBanHangTheoNam(int year) {
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		String query = "Select * from HoaDonBanHang hd where YEAR(ngayLapHoaDon) = " + year
				+ "";
		try {
			List<HoaDonBanHang> danhSachHoaDonBanHang = session.createNativeQuery(query, HoaDonBanHang.class)
					.getResultList();
			tr.commit();
			return danhSachHoaDonBanHang;
		} catch (Exception e) {
			tr.rollback();
		}
		session.close();
		return null;
	}

	@Override
	public List<HoaDonBanHang> layDanhSachHoaDonBanHangTheoTenKhachHang(String tenKH) {
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		String query = "Select * from HoaDonBanHang hd inner join KhachHang kh on hd.maKhachHang = kh.maKhachHang where kh.tenKhachHang like ?1";
		try {
			List<HoaDonBanHang> danhSachHoaDonBanHang = session.createNativeQuery(query, HoaDonBanHang.class)
					.setParameter(1, tenKH).getResultList();
			tr.commit();
			return danhSachHoaDonBanHang;
		} catch (Exception e) {
			tr.rollback();
		}
		session.close();
		return null;

	}

	@Override
	public List<HoaDonBanHang> layDanhSachHoaDonBanHangTheoMaKhachHang(String maKH) {
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		String query = "Select * from HoaDonBanHang hd where hd.maKhachHang like ?1";
		try {
			List<HoaDonBanHang> danhSachHoaDonBanHang = session.createNativeQuery(query, HoaDonBanHang.class)
					.setParameter(1, maKH).getResultList();
			tr.commit();
			return danhSachHoaDonBanHang;
		} catch (Exception e) {
			tr.rollback();
		}
		session.close();
		return null;
	}

	@Override
	public List<HoaDonBanHang> layDanhSachHoaDonBanHangTheoMaNhanVien(String maNV) {
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		String query = "Select * from HoaDonBanHang hd where hd.maNhanVien like ?1";
		try {
			List<HoaDonBanHang> danhSachHoaDonBanHang = session.createNativeQuery(query, HoaDonBanHang.class)
					.setParameter(1, maNV).getResultList();
			tr.commit();
			return danhSachHoaDonBanHang;
		} catch (Exception e) {
			tr.rollback();
		}
		session.close();
		return null;
	}

	@Override
	public List<HoaDonBanHang> layDanhSachHoaDonBanHangTheoTenNhanVien(String tenNV) {
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		String query = "Select * from HoaDonBanHang hd inner join NhanVienBanHang nv on hd.maNhanVien = nv.maNhanVien where nv.tenNhanVien like ?1";
		try {
			List<HoaDonBanHang> danhSachHoaDonBanHang = session.createNativeQuery(query, HoaDonBanHang.class)
					.setParameter(1, tenNV).getResultList();
			tr.commit();
			return danhSachHoaDonBanHang;
		} catch (Exception e) {
			tr.rollback();
		}
		session.close();
		return null;
	}

	@Override
	public double tinhTongTien(String ma) {
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		String query = "select * from ChiTietHoaDonBanHang  cthd where cthd.maHoaDonBanHang like ?1";
		double tongTien = 0;
		try {
			List<ChiTietHoaDonBanHang> danhSachChiTietBanHang = session
					.createNativeQuery(query, ChiTietHoaDonBanHang.class).setParameter(1, ma).getResultList();
			tr.commit();
			for (ChiTietHoaDonBanHang chiTietHoaDon : danhSachChiTietBanHang) {
				int soLuong = chiTietHoaDon.getSoLuong();
				SanPham sanPham = chiTietHoaDon.getSanPham();
				double gia = sanPham.getGiaSanPham();
				if (soLuong > 0.0 && gia > 0)
					tongTien += soLuong * gia;
			}
			return tongTien;
		} catch (Exception e) {
			tr.rollback();
		}
		return tongTien;
	}

	@Override
	public List<HoaDonBanHang> layDanhSachHoaDonBanHangTheoTenKhachHang_SDT_CMND(String key) {
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		String query = "Select * from HoaDonBanHang hd inner join KhachHang kh on hd.maKhachHang = kh.maKhachHang where kh.tenKhachHang like N'%"
				+ key + "%' OR kh.soDienThoai like '" + key + "' OR kh.soCMND like '" + key + "'";
		try {
			List<HoaDonBanHang> danhSachHoaDonBanHang = session.createNativeQuery(query, HoaDonBanHang.class)
					.getResultList();
			tr.commit();
			return danhSachHoaDonBanHang;
		} catch (Exception e) {
			tr.rollback();
		}
		session.close();
		return null;
	}

	@Override
	public List<HoaDonBanHang> layDanhSachHoaDonBanHangTheoTenKhachHang_SDT_CMND_Ngay(String key, int year, int month,
			int day) {
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		String query = "Select * from HoaDonBanHang hd inner join KhachHang kh on hd.maKhachHang = kh.maKhachHang where ( kh.tenKhachHang like N'%"
				+ key + "%' OR kh.soDienThoai like '" + key + "' OR kh.soCMND like '" + key
				+ "' ) AND ( YEAR(ngayLapHoaDon) = " + year + " AND MONTH(ngayLapHoaDon) = " + month
				+ " AND DAY(ngayLapHoaDon) = " + day + ")";
		try {
			List<HoaDonBanHang> danhSachHoaDonBanHang = session.createNativeQuery(query, HoaDonBanHang.class)
					.getResultList();
			tr.commit();
			return danhSachHoaDonBanHang;
		} catch (Exception e) {
			tr.rollback();
		}
		session.close();
		return null;
	}

	@Override
	public List<HoaDonBanHang> layDanhSachHoaDonTheoCumNgay(Date from, Date and) {
		// TreeMap<String, Integer> dsThongKe = new TreeMap<String,Integer>();
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		String query = "select * from HoaDonBanHang where ngayLapHoaDon between '" + from.toString() + "' and '"
				+ and.toString() + "'";
		try {
			List<HoaDonBanHang> danhSachHoaDonBanHang = session.createNativeQuery(query, HoaDonBanHang.class)
					.getResultList();

			tr.commit();
			return danhSachHoaDonBanHang;
		} catch (Exception e) {
			tr.rollback();
		}
		session.close();
		return null;
	}
	ResultSet rs;
	PreparedStatement preStm;
	Connection conn;
	private void closeConnection() throws SQLException {
		
		if (rs != null) {
			rs.close();
		}
		if (preStm != null) {
			preStm.close();
		}
		if (conn != null) {
			conn.close();
		}
	}

	@Override
	public TreeMap<String, Double> thongkeDoanhThu(Date from, Date and) {
		TreeMap<String,  Double> dsThongKe = new TreeMap<String,  Double>();
		/*Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		String query = "select ngayLapHoaDon, sum(tongTien) as tongThanhTien from HoaDonBanHang where ngayLapHoaDon between '"+from.toString()+"' and '"+and.toString()+"' group by ngayLapHoaDon"
				+ and.toString() + "'";
		try {
			List<HoaDonBanHang> danhSachHoaDonBanHang = session.createNativeQuery(query, HoaDonBanHang.class)
					.getResultList();
			
			tr.commit();
			return dsThongKe;
		} catch (Exception e) {
			tr.rollback();
		}
		session.close();
		return null;*/
		try {
			Connection con = ConnectDB.getConnection();
			String sql = " select ngayLapHoaDon, sum(tongTien) as tongThanhTien from HoaDonBanHang where ngayLapHoaDon between '"+from.toString()+"' and '"+and.toString()+"' group by ngayLapHoaDon";
			java.sql.Statement statement =  con.createStatement();
			ResultSet rs = ((java.sql.Statement) statement).executeQuery(sql);
			while (rs.next()) {
				Date maHD = rs.getDate(1);
				double tongtien = rs.getDouble(2);
				dsThongKe.put(maHD.toString(), tongtien);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				closeConnection();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return dsThongKe;
	}
	
	@Override
	public TreeMap<String, Double> thongkeSLDH(Date from,Date and) throws ClassNotFoundException, SQLException{
		TreeMap<String, Double> doanhthu = new TreeMap<String, Double>();
		try {
			Connection con = ConnectDB.getConnection();
			String sql = "  select NGAYTAO , COUNT(MAHD) as 'TongHD' from HOADON where NGAYTAO BETWEEN '"+from.toString()+"' AND '"+and.toString()+"'  and TRANGTHAI =0 group by NGAYTAO  order by NGAYTAO ASC";
			java.sql.Statement statement =  con.createStatement();
			rs = ((java.sql.Statement) statement).executeQuery(sql);
			while (rs.next()) {
				Date maHD = rs.getDate(1);
				double tongtien = rs.getDouble(2);
				doanhthu.put(maHD.toString(), tongtien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnection();
		}
		return doanhthu;
	}

	@Override
	public List<HoaDonBanHang> layDanhSachHoaDonBanHangTheoThang_SP(int year, int month, String ma) {
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		String query = "SELECT ChiTietHoaDonBanHang.*, HoaDonBanHang.*, SanPham.*"
				+"FROM ChiTietHoaDonBanHang INNER JOIN HoaDonBanHang " 
				+"ON ChiTietHoaDonBanHang.maHoaDonBanHang = HoaDonBanHang.maHoaDonBanHang INNER JOIN SanPham "
				+"ON ChiTietHoaDonBanHang.maSanPham = SanPham.maSanPham "
				+"WHERE YEAR(ngayLapHoaDon) = "+year+" and MONTH(ngayLapHoaDon) = "+month+" and SanPham.maSanPham = '"+ma+"'";
		try {
			List<HoaDonBanHang> danhSachHoaDonBanHang = session.createNativeQuery(query, HoaDonBanHang.class)
					.getResultList();

			tr.commit();
			return danhSachHoaDonBanHang;
		} catch (Exception e) {
			tr.rollback();
		}
		session.close();
		return null;
	}

	@Override
	public List<HoaDonBanHang> layDanhSachHoaDonBanHangTheoNam_SP(int year, String ma) {
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		String query = "SELECT ChiTietHoaDonBanHang.*, HoaDonBanHang.*, SanPham.*"
				+"FROM ChiTietHoaDonBanHang INNER JOIN HoaDonBanHang " 
				+"ON ChiTietHoaDonBanHang.maHoaDonBanHang = HoaDonBanHang.maHoaDonBanHang INNER JOIN SanPham "
				+"ON ChiTietHoaDonBanHang.maSanPham = SanPham.maSanPham "
				+"WHERE YEAR(ngayLapHoaDon) = "+year+" and SanPham.maSanPham = '"+ma+"'";
		try {
			List<HoaDonBanHang> danhSachHoaDonBanHang = session.createNativeQuery(query, HoaDonBanHang.class)
					.getResultList();

			tr.commit();
			return danhSachHoaDonBanHang;
		} catch (Exception e) {
			tr.rollback();
		}
		session.close();
		return null;
	}

	@Override
	public List<HoaDonBanHang> layDanhSachHoaDonTheoCumNgay_SP(Date from, Date and, String ma) {
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		String query = "SELECT ChiTietHoaDonBanHang.*, HoaDonBanHang.*, SanPham.*"
				+"FROM ChiTietHoaDonBanHang INNER JOIN HoaDonBanHang " 
				+"ON ChiTietHoaDonBanHang.maHoaDonBanHang = HoaDonBanHang.maHoaDonBanHang INNER JOIN SanPham "
				+"ON ChiTietHoaDonBanHang.maSanPham = SanPham.maSanPham "
				+"WHERE ngayLapHoaDon between '" +from.toString()+ "' and '"+and.toString()+"' and SanPham.maSanPham = '"+ma+"'";
		try {
			List<HoaDonBanHang> danhSachHoaDonBanHang = session.createNativeQuery(query, HoaDonBanHang.class)
					.getResultList();

			tr.commit();
			return danhSachHoaDonBanHang;
		} catch (Exception e) {
			tr.rollback();
		}
		session.close();
		return null;
	}

	@Override
	public List<HoaDonBanHang> layDanhSachHoaDonBanHangTheoNgay_SP(int year, int month, int day, String ma) {
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		String query = "SELECT ChiTietHoaDonBanHang.*, HoaDonBanHang.*, SanPham.*"
				+"FROM ChiTietHoaDonBanHang INNER JOIN HoaDonBanHang " 
				+"ON ChiTietHoaDonBanHang.maHoaDonBanHang = HoaDonBanHang.maHoaDonBanHang INNER JOIN SanPham "
				+"ON ChiTietHoaDonBanHang.maSanPham = SanPham.maSanPham "
				+"WHERE YEAR(ngayLapHoaDon) = "+year+" and MONTH(ngayLapHoaDon) = "+month+" and day(ngayLapHoaDon)= "+day+" and SanPham.maSanPham = '"+ma+"'";
		try {
			List<HoaDonBanHang> danhSachHoaDonBanHang = session.createNativeQuery(query, HoaDonBanHang.class)
					.getResultList();

			tr.commit();
			return danhSachHoaDonBanHang;
		} catch (Exception e) {
			tr.rollback();
		}
		session.close();
		return null;
	}

}
