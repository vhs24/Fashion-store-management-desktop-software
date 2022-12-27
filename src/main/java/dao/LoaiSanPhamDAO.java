package dao;

import java.util.List;

import entity.LoaiSanPham;

public interface LoaiSanPhamDAO {
	public boolean themLoaiSanPham(LoaiSanPham loaiSP);
	public boolean capNhatLoaiSanPham(LoaiSanPham loaiSP);
	public List<LoaiSanPham> layTatCaLoaiSanPham();
	public LoaiSanPham layLoaiSanPhamTheoMa(String ma);
	public LoaiSanPham layLoaiSanPHamTheoTen(String ten);
}
