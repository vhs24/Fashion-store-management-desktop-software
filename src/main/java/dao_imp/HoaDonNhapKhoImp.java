package dao_imp;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import dao.HoaDonNhapKhoDAO;
import entity.ChiTietHoaDonNhapKho;
import entity.HoaDonNhapKho;

public class HoaDonNhapKhoImp implements HoaDonNhapKhoDAO{
	private SessionFactory sessionFactory;
	public HoaDonNhapKhoImp(SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}
	@Override
	public boolean themHoaDon(HoaDonNhapKho hoaDonNK) {
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		try {
			session.save(hoaDonNK);
			tr.commit();
			return true;
		} catch (Exception e) {
			tr.rollback();
		}
		session.close();
		return false;
	}


	@Override
	public boolean capnhatHoaDon(HoaDonNhapKho hoaDonNK) {
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		try {
			session.update(hoaDonNK);
			tr.commit();
			return true;
		} catch (Exception e) {
			tr.rollback();
		}
		session.close();
		return false;
	}

	@Override
	public List<HoaDonNhapKho> layTatCaHoaDonNhapKho() {
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		String query = "select * from HoaDonNhapKho hdnk";
		try {
			List<HoaDonNhapKho> danhSachHoaDonNK = session
					.createNativeQuery(query, HoaDonNhapKho.class)
					.getResultList();
			tr.commit();
			return danhSachHoaDonNK;
		} catch (Exception e) {
			tr.rollback();
		}
		session.close();
		return null;
	}

	@Override
	public HoaDonNhapKho layHoaDonNhapKhoTheoMa(String ma) {
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		String query = "select * from HoaDonNhapKho hdnk where hdnk.maHoaDonNhapKho like ?1";
		try {
			HoaDonNhapKho hoaDonNK = session
					.createNativeQuery(query, HoaDonNhapKho.class)
					.setParameter(1, ma)
					.getSingleResult();
			tr.commit();
			return hoaDonNK;
		} catch (Exception e) {
			tr.rollback();
		}
		session.close();
		return null;
	}

	@Override
	public List<HoaDonNhapKho> layDanhSachHoaDonNhapKhoTheoNgay(String date) {
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		String query = "select * from HoaDonNhapKho hdnk where hdnk.ngayNhapKho = '"+date+"'";
		try {
			List<HoaDonNhapKho> danhSachHoaDonNK = session
					.createNativeQuery(query, HoaDonNhapKho.class)
					.getResultList();
			tr.commit();
			return danhSachHoaDonNK;
		} catch (Exception e) {
			tr.rollback();
		}
		session.close();
		return null;
	}

	@Override
	public List<HoaDonNhapKho> layDanhSachHoaDonNhapKhoTheoMaNhanVien(String maNV) {
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		String query = "select * from HoaDonNhapKho hdnk where hdnk.maNhanVien like ?1";
		try {
			List<HoaDonNhapKho> danhSachHoaDonNK = session
					.createNativeQuery(query, HoaDonNhapKho.class)
					.setParameter(1, maNV)
					.getResultList();
			tr.commit();
			return danhSachHoaDonNK;
		} catch (Exception e) {
			tr.rollback();
		}
		session.close();
		return null;
	}

	@Override
	public List<HoaDonNhapKho> layDanhSachHoaDonNhapKhoTheoTenNhanVien(String tenNV) {
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		String query = "select * from HoaDonNhapKho hdnk inner join NhanVienBanHang nv on hdnk.maNhanVien = nv.maNhanVien where nv.tenNhanVien like N'%"+tenNV+"%'";
		try {
			List<HoaDonNhapKho> danhSachHoaDonNK = session
					.createNativeQuery(query, HoaDonNhapKho.class)
					.getResultList();
			tr.commit();
			return danhSachHoaDonNK;
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
		String query = "select * from ChiTietHoaDonNhapKho ctnk where ctnk.maHoaDonNhapKho like ?1 ";
		double tongTien = 0;
		try {
			List<ChiTietHoaDonNhapKho> danhSachChiTietNhapKho = session
					.createNativeQuery(query, ChiTietHoaDonNhapKho.class)
					.setParameter(1, ma)
					.getResultList();
			for (ChiTietHoaDonNhapKho ctnk : danhSachChiTietNhapKho) {
				tongTien += ctnk.thanhTien();
			}
			tr.commit();
			return tongTien;
		} catch (Exception e) {
			tr.rollback();
		}
		session.close();
		return tongTien;
	}
	@Override
	public List<HoaDonNhapKho> layDanhSachHoaDonNhapKhoTheoNgay_TenNV(String date, String tenNV) {
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		String query = "select * from HoaDonNhapKho hdnk inner join NhanVienBanHang nv on hdnk.maNhanVien = nv.maNhanVien where nv.tenNhanVien like N'%"+tenNV+"%' AND hdnk.ngayNhapKho = '"+date+"'";
		try {
			List<HoaDonNhapKho> danhSachHoaDonNK = session
					.createNativeQuery(query, HoaDonNhapKho.class)
					.getResultList();
			tr.commit();
			return danhSachHoaDonNK;
		} catch (Exception e) {
			tr.rollback();
		}
		session.close();
		return null;
	}
	@Override
	public List<HoaDonNhapKho> layDanhSachHoaDonNhapKhoTheoTenNV_SDT_CMND(String key) {
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		String query = "select * from HoaDonNhapKho hdnk inner join NhanVienBanHang nv on hdnk.maNhanVien = nv.maNhanVien where nv.tenNhanVien like N'%"+key+"%' OR nv.soDienThoai like '%"+key+"%' OR nv.soCMND like N'%"+key+"%'";
		try {
			List<HoaDonNhapKho> danhSachHoaDonNK = session
					.createNativeQuery(query, HoaDonNhapKho.class)
					.getResultList();
			tr.commit();
			return danhSachHoaDonNK;
		} catch (Exception e) {
			tr.rollback();
		}
		session.close();
		return null;
	}
	@Override
	public List<HoaDonNhapKho> layDanhSachHoaDonNhapKhoTheoTenNV_SDT_CMND_Ngay(String key, String date) {
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		String query = "select * from HoaDonNhapKho hdnk inner join NhanVienBanHang nv on hdnk.maNhanVien = nv.maNhanVien where ( nv.tenNhanVien like N'%"+key+"%' OR nv.soDienThoai = N'%"+key+"%' OR nv.soCMND like N'%"+key+"%') AND hdnk.ngayNhapKho = '"+date+"'";
		try {
			List<HoaDonNhapKho> danhSachHoaDonNK = session
					.createNativeQuery(query, HoaDonNhapKho.class)
					.getResultList();
			tr.commit();
			return danhSachHoaDonNK;
		} catch (Exception e) {
			tr.rollback();
		}
		session.close();
		return null;
	}

}
