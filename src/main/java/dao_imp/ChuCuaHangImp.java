package dao_imp;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import dao.ChuCuaHangDAO;
import entity.ChuCuaHang;
import entity.KhachHang;

public class ChuCuaHangImp implements ChuCuaHangDAO{

	private SessionFactory sessionFactory;
	public ChuCuaHangImp(SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public ChuCuaHang layThongTinChuCuaHang() {
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		String query = "Select * from ChuCuaHang cch";
		try {
			ChuCuaHang cch = session
					.createNativeQuery(query, ChuCuaHang.class)
					.getSingleResult();
			tr.commit();
			return cch;
		} catch (Exception e) {
			tr.rollback();
		}
		session.close();
		return null;
		
	}

	@Override
	public ChuCuaHang layThongTinChuCuaHangTheoMa(String ma) {
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		String query = "Select * from ChuCuaHang cch where maChuCuaHang like ?1";
		try {
			ChuCuaHang cch = session
					.createNativeQuery(query, ChuCuaHang.class)
					.setParameter(1, ma)
					.getSingleResult();
			tr.commit();
			return cch;
		} catch (Exception e) {
			tr.rollback();
		}
		session.close();
		return null;
		
	}
	
	@Override
	public boolean capnhatThongTinChuCuaHang() {
		// TODO Auto-generated method stub
		return false;
	}

}
