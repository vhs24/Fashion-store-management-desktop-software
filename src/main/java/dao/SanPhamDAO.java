package dao;

import java.util.List;

import entity.SanPham;
public interface SanPhamDAO {
	public boolean themSanPham(SanPham sanPham);
	public boolean capNhatSanPham(SanPham sanPham);
	public List<SanPham> layTatCaSanPham();
	public SanPham laySanPhamTheoMa(String ma);
	public List<SanPham> layDanhSachSanPhamTheoLoai(String loai);
	public List<SanPham> laySanPhamTheoSoLuong(int thapNhat, int caoNhat);
	public SanPham capnhatSoLuong(int sl);
	public int demTatCaSanPham();
	public List<SanPham> layTatCaSanPhamKhacKhong();
	public List<SanPham> layDanhSachSanPhamTheoHSX(String loai);
	public List<SanPham> layDanhSachSanPhamTheoHSX_Loai(String keyHSX, String keyLoai);
}
