package dao;

import java.util.List;

import entity.CaLamViec;
public interface CaLamViecDAO {
	public boolean themCaLamViec(CaLamViec ca);
	public List<CaLamViec> layTatCaCacCaLamViec();
	
}
