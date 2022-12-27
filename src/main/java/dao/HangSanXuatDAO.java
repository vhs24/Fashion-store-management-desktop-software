package dao;

import java.util.List;

import entity.HangSanXuat;

public interface HangSanXuatDAO {
	public boolean themHangSanXuat(HangSanXuat hangSanXuat);
	public boolean capNhatHangSanXuat(HangSanXuat hangSanXuat);
	public List<HangSanXuat> layTatCaHangSanXuat();
	public HangSanXuat layHangSanXuatTheoMa(String ma);
	public List<HangSanXuat> layHangSanXuatTheoTen(String ten);
	public HangSanXuat layHangSanXuatTheoTen1(String ten);
}
