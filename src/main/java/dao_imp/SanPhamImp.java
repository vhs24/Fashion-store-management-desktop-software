package dao_imp;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import dao.SanPhamDAO;
import entity.SanPham;

public class SanPhamImp implements SanPhamDAO{
	private SessionFactory sessionFactory;
	public SanPhamImp(SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}

	@Override
	public boolean themSanPham(SanPham sanPham) {
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		try {
			session.save(sanPham);
			tr.commit();
			return true;
		} catch (Exception e) {
			tr.rollback();
		}
		session.close();
		return false;
	}

	@Override
	public boolean capNhatSanPham(SanPham sanPham) {
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		try {
			session.update(sanPham);
			tr.commit();
			return true;
		} catch (Exception e) {
			tr.rollback();
		}
		session.close();
		return false;
	}

	@Override
	public List<SanPham> layTatCaSanPham() {
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		String query = "Select * from SanPham sp";
		try {
			List<SanPham> danhSachSanPham = session
					.createNativeQuery(query, SanPham.class)
					.getResultList();
			tr.commit();
			return danhSachSanPham;
		} catch (Exception e) {
			tr.rollback();
		}
		session.close();
		return null;
	}
	
	@Override
	public List<SanPham> layTatCaSanPhamKhacKhong() {
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		String query = "Select * from SanPham sp where sp.soLuong > 0";
		try {
			List<SanPham> danhSachSanPham = session
					.createNativeQuery(query, SanPham.class)
					.getResultList();
			tr.commit();
			return danhSachSanPham;
		} catch (Exception e) {
			tr.rollback();
		}
		session.close();
		return null;
	}

	@Override
	public SanPham laySanPhamTheoMa(String ma) {
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		String query = "select * from SanPham sp where sp.maSanPham like ?1";
		try {
			SanPham sp = session
					.createNativeQuery(query, SanPham.class)
					.setParameter(1, ma)
					.getSingleResult();
			tr.commit();
			return sp;
		} catch (Exception e) {
			tr.rollback();
		}
		session.close();
		return null;
	}

	@Override
	public List<SanPham> layDanhSachSanPhamTheoLoai(String loai) {
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		String query = "Select * from SanPham sp inner join LoaiSanPham lsp on sp.maLoaiSanPham = lsp.maLoaiSanPham where lsp.maLoaiSanPham = '"+loai+"'";
		try {
			List<SanPham> danhSachSanPham = session
					.createNativeQuery(query, SanPham.class)
					.getResultList();
			tr.commit();
			return danhSachSanPham;
		} catch (Exception e) {
			tr.rollback();
		}
		session.close();
		return null;
		
	}
	
	@Override
	public List<SanPham> layDanhSachSanPhamTheoHSX(String hsx) {
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		String query = "Select * from SanPham sp inner join HangSanXuat lsp on sp.maHangSanXuat = lsp.maHangSanXuat where lsp.maHangSanXuat = '"+hsx+"'";
		try {
			List<SanPham> danhSachSanPham = session
					.createNativeQuery(query, SanPham.class)
					
					.getResultList();
			tr.commit();
			return danhSachSanPham;
		} catch (Exception e) {
			tr.rollback();
		}
		session.close();
		return null;
		
	}

	@Override
	public List<SanPham> laySanPhamTheoSoLuong(int thapNhat, int caoNhat) {
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		String query = "select * from SanPham sp where sp.SoLuong < ?1 and sp.SoLuong > ?2";
		try {
			List<SanPham> danhSachSanPham = session
					.createNativeQuery(query, SanPham.class)
					.setParameter(1, caoNhat)
					.setParameter(2, thapNhat)
					.getResultList();

			tr.commit();
			
			return danhSachSanPham;
		} catch (Exception e) {
			tr.rollback();
		}
		session.close();
		return null;
	}

	@Override
	public SanPham capnhatSoLuong(int sl) {
		
		return null;
	}

	@Override
	public int demTatCaSanPham() {
		/*Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		String query = "select count(maSanPham) from SanPham";
		try {
			int sl = session
					.createNativeQuery(query, SanPham.class)
					.getSingleResult();
			tr.commit();
			return sl;
		} catch (Exception e) {
			tr.rollback();
		}
		session.close();*/
		return 0;
	}

	@Override
	public List<SanPham> layDanhSachSanPhamTheoHSX_Loai(String keyHSX, String keyLoai) {
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		String query = "Select * from SanPham sp where sp.maLoaiSanPham = '"+keyLoai+"' and sp.maHangSanXuat = '"+keyHSX+"'";
		try {
			List<SanPham> danhSachSanPham = session
					.createNativeQuery(query, SanPham.class)
					.getResultList();
			tr.commit();
			return danhSachSanPham;
		} catch (Exception e) {
			tr.rollback();
		}
		session.close();
		return null;
	}

}
