package dao_imp;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import dao.NhanVienDAO;
import entity.NhanVienBanHang;

public class NhanVienImp implements NhanVienDAO{
	private SessionFactory sessionFactory;
	
	public NhanVienImp(SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}

	@Override
	public boolean themNhanVien(NhanVienBanHang nhanVien) {
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		try {
			session.save(nhanVien);
			tr.commit();
			return true;
		} catch (Exception e) {
			tr.rollback();
		}
		session.close();
		return false;
	}

	@Override
	public boolean capNhatNhanVien( NhanVienBanHang nhanVien) {
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		try {
			session.update(nhanVien);
			tr.commit();
			return true;
		} catch (Exception e) {
			tr.rollback();
		}
		session.close();
		return false;
	}

	@Override
	public boolean xoaNhanVien(String ma) {
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		try {
			NhanVienBanHang nv = session.find(NhanVienBanHang.class, ma);
			session.delete(nv);
			tr.commit();
			return true;
		} catch (Exception e) {
			tr.rollback();
		}
		session.close();
		return false;
	}

	@Override
	public List<NhanVienBanHang> layDanhSachNhanVienTheoNamSinh(int nam) {
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		String query = "Select * from NhanVienBanHang nv where year(nv.ngaySinh) = "+nam;
		try {
			List<NhanVienBanHang> danhSachNhanVien = session
					.createNativeQuery(query, NhanVienBanHang.class)
					//.setParameter(1, nam)
					.getResultList();
			tr.commit();
			return danhSachNhanVien;
		} catch (Exception e) {
			tr.rollback();
		}
		session.close();
		return null;
	}

	@Override
	public List<NhanVienBanHang> layDanhSachNhanVienTheoNgayVaoLam(String date) {
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		String query = "Select * from NhanVienBanHang nv where nv.ngayVaoLam like ?1 ";
		try {
			List<NhanVienBanHang> danhSachNhanVien = session
					.createNativeQuery(query, NhanVienBanHang.class)
					.setParameter(1, date)
					.getResultList();
			tr.commit();
			return danhSachNhanVien;
		} catch (Exception e) {
			tr.rollback();
		}
		session.close();
		return null;
	}

	@Override
	public List<NhanVienBanHang> layTatCaNhanVien() {
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		String query = "Select * from NhanVienBanHang nv where tinhTrang = 1 and maNhanVien like 'NV%'";
		try {
			List<NhanVienBanHang> danhSachNhanVien = session
					.createNativeQuery(query, NhanVienBanHang.class)
					.getResultList();
			tr.commit();
			return danhSachNhanVien;
		} catch (Exception e) {
			tr.rollback();
		}
		session.close();
		return null;		
	}

	@Override
	public NhanVienBanHang layNhanVienTheoMa(String ma) {
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		String query = "Select * from NhanVienBanHang nv where nv.maNhanVien like ?1";
		try {
			NhanVienBanHang nv = session
					.createNativeQuery(query, NhanVienBanHang.class)
					.setParameter(1, ma)
					.getSingleResult();
			tr.commit();
			return nv;
		} catch (Exception e) {
			tr.rollback();
		}
		session.close();
		return null;
	}

	@Override
	public List<NhanVienBanHang> layDanhSachNhanVienTheoTen(String ten) {
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		String query = "Select * from NhanVienBanHang nv where nv.tenNhanVien like N'%"+ten+"%'";
		try {
			List<NhanVienBanHang> danhSachNhanVien = session
					.createNativeQuery(query, NhanVienBanHang.class)
					.getResultList();
			tr.commit();
			return danhSachNhanVien;
		} catch (Exception e) {
			tr.rollback();
		}
		session.close();
		return null;
	}

	@Override
	public NhanVienBanHang layNhanVienTheoSoDienthoai(String sdt) {
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		String query = "Select * from NhanVienBanHang nv where nv.soDienThoai like N'%"+sdt+"%'";
		try {
			NhanVienBanHang nhanVien = session
					.createNativeQuery(query, NhanVienBanHang.class)
					.getSingleResult();
			tr.commit();
			return nhanVien;
		} catch (Exception e) {
			tr.rollback();
		}
		session.close();
		return null;
	}
	
	@Override
	public NhanVienBanHang layNhanVienTheoSoCMND(String cmnd) {
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		String query = "Select * from NhanVienBanHang nv where nv.soCMND like '%"+cmnd+"%'";
		try {
			NhanVienBanHang nhanVien = session
					.createNativeQuery(query, NhanVienBanHang.class)
					.getSingleResult();
			tr.commit();
			return nhanVien;
		} catch (Exception e) {
			tr.rollback();
		}
		session.close();
		return null;
	}

	@Override
	public NhanVienBanHang capnhatTinhTrangNhanVien(String ma) {
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		String query = "update NhanVienBanHang set tinhTrang = 0 where maNhanVien = '"+ma+"'";
		try {
			NhanVienBanHang nhanVien = session
					.createNativeQuery(query, NhanVienBanHang.class)
					//.setParameter(1, ma)
					.getSingleResult();
			tr.commit();
			return nhanVien;
		} catch (Exception e) {
			tr.rollback();
		}
		session.close();
		return null;
	}
	
	@Override
	public List<NhanVienBanHang> layDanhSachNhanVienTheoGioiTinh(boolean gt){
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		String query = "Select * from NhanVienBanHang nv where nv.gioiTinh = gt";
		try {
			List<NhanVienBanHang> danhSachNhanVien = session
					.createNativeQuery(query, NhanVienBanHang.class)
					.setParameter(1, gt)
					.getResultList();
			tr.commit();
			return danhSachNhanVien;
		} catch (Exception e) {
			tr.rollback();
		}
		session.close();
		return null;
	}
	
	@Override
	public List<NhanVienBanHang> layDanhSachNhanVienTheoTinhTrang(boolean tinhTrang){
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		String query = "Select * from NhanVienBanHang nv where nv.tinhTrang = gt";
		try {
			List<NhanVienBanHang> danhSachNhanVien = session
					.createNativeQuery(query, NhanVienBanHang.class)
					.setParameter(1, tinhTrang)
					.getResultList();
			tr.commit();
			return danhSachNhanVien;
		} catch (Exception e) {
			tr.rollback();
		}
		session.close();
		return null;
	}

	@Override
	public List<NhanVienBanHang> layDanhSachNhanVienTheoTen_SDT_CMND(String key) {
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		String query = "Select * from NhanVienBanHang nv where ( nv.tenNhanVien like N'%"+key+"%' OR nv.soDienThoai = '"+key+"' OR nv.soCMND = '"+key+"' or nv.soDienThoai like '%"+key+"%' or nv.soCMND like '%"+key+"%') and nv.maNhanVien like 'NV%'";
		try {
			List<NhanVienBanHang> danhSachNhanVien = session
					.createNativeQuery(query, NhanVienBanHang.class)
					.getResultList();
			tr.commit();
			return danhSachNhanVien;
		} catch (Exception e) {
			tr.rollback();
		}
		session.close();
		return null;
	}

}
