package dao_imp;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import dao.CaLamViecDAO;
import entity.CaLamViec;
/**
 * Lớp dùng 
 *
 */
public class CaLamViecImp implements CaLamViecDAO{

	private SessionFactory sessionFactory;
	
	public CaLamViecImp(SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}

	@Override
	public boolean themCaLamViec(CaLamViec ca) {
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		try {
			session.save(ca);
			tr.commit();
			return true;
		} catch (Exception e) {
			tr.rollback();
		}
		session.close();
		return false;
		
	}
	@Override
	public List<CaLamViec> layTatCaCacCaLamViec() {
		Session session = sessionFactory.openSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			List<CaLamViec> danhSachCaLamViec = session
					.createNamedQuery("layDanhSachCaLamViec", CaLamViec.class)
					.getResultList();
			tr.commit();
			return danhSachCaLamViec ;
			
		} catch (Exception e) {
			tr.rollback();
		}
		session.close();
		return null;
	}
}
